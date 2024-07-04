<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { externalFormatter } from "@/utils/formatter";

interface Item {
  id: number,
  userIdentification: string,
  email: string,
  isExternalUser: boolean,
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
      search: '',
      headers: [
        {title: 'ID', key: 'id'},
        {title: 'Número de identificación', key: 'userIdentification'},
        {title: 'Correo electrónico', key: 'email'},
        {title: 'Afiliación', key: 'isExternalUser'}
      ]
    }
  },
  // ...
  created() {
    this.getUsers();
  },
  methods: {
    async getUsers() {
      try {
        const data = await get('getUsers');
        this.items = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    externalFormatter,
  },
})


</script>

<template>
  <v-card flat>
    <v-card-title class="d-flex align-center justify-end">
      <v-text-field
        v-model="search"
        density="compact"
        label="Search"
        prepend-inner-icon="ri-search-line"
        variant="outlined"
        hide-details
        single-line
      ></v-text-field>
      <v-btn to="addUser" class="mx-2" prepend-icon="ri-add-fill"> Agregar</v-btn>
    </v-card-title>
    <v-data-table
      :items="items"
      :search="search"
      :headers="headers"
    >
      <template v-slot:item.isExternalUser="{item}">
        {{ externalFormatter(item.isExternalUser)}}

      </template>
    </v-data-table>
  </v-card>
</template>

