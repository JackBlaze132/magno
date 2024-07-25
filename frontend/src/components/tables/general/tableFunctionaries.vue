<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { externalFormatter } from "@/utils/formatter";

interface Item {
  id: number,
  name: string
  userCode: string,
  identificationNumber: string,
  phoneNumber: string,
  email: string,
  sex: string,
  dependency:{
    name:string,
  }
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
      search: '',
      headers: [
        {title: 'ID', key: 'id'},
        {title: 'Nombre', key: 'name'},
        {title: 'Código', key: 'userCode'},
        {title: 'Identificación', key: 'identificationNumber'},
        {title: 'Teléfono', key: 'phoneNumber'},
        {title: 'Correo', key: 'email'},
        {title: 'Sexo', key: 'sex'},
        {title: 'Dependecia', key: 'dependency.name'},
        { key: 'link', sortable: false},
      ],
    }
  },
  // ...
  created() {
    this.getUsers();
  },
  methods: {
    async getUsers() {
      try {
        this.items = await get('getFunctionaryProfiles');
        this.$emit('loaded');
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    externalFormatter,
  },
})


</script>

<template>
  <VCard flat>
    <VCardTitle class="d-flex align-center justify-end">
      <VTextField
        v-model="search"
        density="compact"
        label="Search"
        prepend-inner-icon="ri-search-line"
        variant="outlined"
        hide-details
        single-line
      ></VTextField>
      <VBtn to="agregar-usuarios" class="mx-2" prepend-icon="ri-add-fill"> Agregar</VBtn>
    </VCardTitle>
    <VDataTable
      :items="items"
      :search="search"
      :headers="headers"
    >
      <template v-slot:item.isExternalUser="{item}">
        {{ externalFormatter(item.isExternalUser)}}

      </template>
    </VDataTable>
  </VCard>
</template>

