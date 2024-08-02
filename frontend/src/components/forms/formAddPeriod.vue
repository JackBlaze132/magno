<script lang="ts">
  import { defineComponent } from 'vue'
  import { post } from "@/utils/api";

  interface Item {
    name: string,
    start_date: string,
    end_date: string,
    is_active: boolean,
  }

  export default defineComponent({
    name: 'formAddPeriod',
    data() {
      return {
        item: {} as Item
      }
  },
  methods: {
    addUser() {
      post('addAssesmentPeriod ', this.item)
        .then((data) => {
          console.log(this.item)
          this.$router.push('/periodos');
        })
        .catch((error) => {
          console.error('Error al realizar la solicitud:', error);
        });
    }
  }
});
</script>

<template>
  <VForm validate-on="submit" @submit.prevent="addUser">
    <VTextField label="Nombre" name="name" id="name" v-model="item.name"/>
    <VDateInput label="Fecha de inicio" prepend-inner-icon="ri-calendar-start" prepend-icon="" type="text" name="startDate" id="startDate"  v-model="item.start_date"/>
    <VDateInput label="Fecha de finzalización" prepend-inner-icon="ri-calendar-end" prepend-icon="" type="text" name="endtDate" id="endtDate"  v-model="item.end_date"/>
    <VRadioGroup v-model="item.is_active" class="d-flex" inline>
      <VRadio label="Activo" :value="true"/>
      <VRadio label="Inactivo" :value="false"/>
    </VRadioGroup>
    <VBtn
      text="Guardar"
      prepend-icon="ri-save-2-line"
      type="submit"
    />
  </VForm>
</template>
