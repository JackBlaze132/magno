<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { externalFormatter } from "@/utils/formatter";
import { VIcon } from "vuetify/components";
import { RouterLink } from "vue-router";


interface Student {
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
      students: [] as Student[],
      search: '',
      links: '',
      headersStudents: [
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
  created() {
    this.getSeedBeds();
  },
  methods: {
    async getSeedBeds() {
      try {
        const data = await get('getStudentProfilesByResearchSeedbedId/' + this.$route.params.id);
        this.students = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    externalFormatter,
  },
})
</script>

<template>
  <Vcard flat>
    <h2>Estudiantes</h2>
    <VCardTitle class="d-flex align-center justify-end">
      <v-text-field
        v-model="search"
        density="compact"
        label="Search"
        prepend-inner-icon="ri-search-line"
        variant="outlined"
        hide-details
        single-line
      ></v-text-field>
      <VBtn to="addPeriod" class="mx-2" prepend-icon="ri-add-fill"> Agregar</VBtn>
    </VCardTitle>
    <v-data-table
      :items="students"
      :search="search"
      :headers="headersStudents"
    >
    <template v-slot:item.userStudent.isExternalUser="{item}">
      {{ externalFormatter(item.userStudent.isExternalUser)}}

    </template>

      <template v-slot:item.link="{item}">
        <RouterLink :to="item.id.toString()">
          <VIcon icon="ri-search-eye-fill"/>
        </RouterLink>
      </template>
    </v-data-table>
  </vcard>
</template>
