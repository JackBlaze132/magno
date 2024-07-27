<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { externalFormatter } from "@/utils/formatter";
import { VIcon } from "vuetify/components";
import { RouterLink } from "vue-router";


interface Item {
  id: number,
  name: string
  userCode: string,
  identificationNumber: string,
  phoneNumber: string,
  semester: number,
  email: string,
  wasActive: boolean,
  sex: string,
  userStudent: {
    isExternalUser: boolean
  }
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
      search: '',
      links: '',
      loaded: false,
      headers: [
        {title: 'ID', key: 'id'},
        {title: 'Nombre', key: 'name'},
        {title: 'Código', key: 'userCode'},
        {title: 'Identificación', key: 'identificationNumber'},
        {title: 'Teléfono', key: 'phoneNumber'},
        {title: 'Semestre', key: 'semester'},
        {title: 'Correo', key: 'email'},
        {title: 'Sexo', key: 'sex'},
        {title: 'Activo', key: 'userStudent.isExternalUser'},
        { key: 'link', sortable: false},
      ],
    }
  },
  // ...
  mounted() {
    this.getSeedBeds();
  },
  methods: {
    async getSeedBeds() {
      try {
        this.items = await get('getStudentProfilesByResearchSeedbedId/' + this.$route.params.idSemillero);
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
    <h2>Estudiantes</h2>
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
      <VBtn to="subir-estudiantes" class="mx-2" prepend-icon="ri-upload-cloud-2-fill" color="black"> Subir</VBtn>
      <VBtn to="agregar-estudiante" class="mx-2" prepend-icon="ri-add-fill"> Agregar</VBtn>

    </VCardTitle>
    <VDataTable
      :items="items"
      :search="search"
      :headers="headers"
    >
    <template v-slot:item.userStudent.isExternalUser="{item}">
      {{ externalFormatter(item.userStudent.isExternalUser)}}

    </template>

      <!--<template v-slot:item.link="{item}">
        <RouterLink :to="item.id.toString()">
          <VIcon icon="ri-search-eye-fill"/>
        </RouterLink>
      </template>-->
    </VDataTable>
  </VCard>
</template>
