<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { periodActivityFormatter } from "@/utils/formatter";


interface Item {
  id: number,
  name: string,
  startDate: string,
  endDate: string,
  isActive: boolean,
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
      search: '',
      headers: [
        {title: 'ID', key: 'id'},
        {title: 'Nombre', key: 'name'},
        {title: 'Fecha de inicio', key: 'startDate'},
        {title: 'Fecha de finalización', key: 'endDate'},
        {title: 'Estado', key: 'isActive'},
      ]
    }
  },
  // ...
  created() {
    this.getPeriods();
  },
  methods: {
    async getPeriods() {
      try {
        const data = await get('getAssesmentPeriods');
        this.items = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    periodActivityFormatter,
  },
})
</script>

<template>
  <Vcard flat>
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
      :items="items"
      :search="search"
      :headers="headers"
    >
      <template v-slot:item.isExternalUser="{item}">
        {{ periodActivityFormatter(item.isActive)}}

      </template>
    </v-data-table>
  </vcard>
</template>
