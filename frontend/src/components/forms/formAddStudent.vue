<script lang="ts">
import { defineComponent } from 'vue'
import { get, post } from "@/utils/api";
import { VSelect } from 'vuetify/components';


interface Item {
  identification_number: string,
  research_seedbed_id: string
}

export default defineComponent({
  name: 'formAddPeriod',
  data() {
    return {
      item: {} as Item,
      selectedFunctionary: null
    }
  },
  created() {
      this.item.research_seedbed_id = this.$route.params.idSemillero.toString()
  },

  methods: {
    addSeedbed() {
      post('addStudentProfile', this.item)
      .then((data) => {
        if (data.error) {
          console.error("Error al realizar la solicitud", data.error);
        } else {
          console.log(data);
          this.$router.push('detalles');
        }
      })
      .catch((error) => {
        console.error('Error al realizar la solicitud:', error);
      });
    }
  },
});
</script>

<template>
  <VForm validate-on="submit" @submit.prevent="addSeedbed">
    <VTextField label="Nombre" name="name" id="name" v-model="item.identification_number"/>
    <VBtn
      text="Guardar"
      prepend-icon="ri-save-2-line"
      type="submit"
    />
  </VForm>
</template>
