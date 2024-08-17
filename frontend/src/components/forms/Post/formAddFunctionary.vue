<template>
  <VForm validate-on="submit" @submit.prevent="addItem">
    <VTextField label="Número de identificación" name="name" id="name" v-model="item.identification_number"/>
    <VTextField label="ID periodo academico" name="name" id="name" v-model="item.assesment_period_id"/>
    <LoadingBtn
      text="Guardar"
      icon="ri-save-2-line"
      :loading="loading"
    />
  </VForm>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import API from "@/utils/api";
import LoadingBtn from '../loadingBtn.vue';


interface Item {
    identification_number:string,
    assesment_period_id:string
}

export default defineComponent({
  name: 'formAddFucntionary',
  data() {
    return {
      item: {} as Item,
      selectedFunctionary: null,
      loading: false
    }
  },

  methods: {
    addItem() {
      this.loading=true
      API.post(API.POST_FUNCTIONARY_PROFILE, this.item)
      .then((data) => {
        if (data.error) {
          this.loading=false
          console.error("Error al realizar la solicitud", data.error);
        } else {
          console.log(data);
          this.$router.push('detalles');
        }
      })
      .catch((error) => {
        this.loading=false
        console.error('Error al realizar la solicitud:', error);
      });
    }
  },
});
</script>
