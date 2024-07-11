<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { periodActivityFormatter } from "@/utils/formatter";
import { VChip, VIcon } from "vuetify/components";
import { RouterLink } from "vue-router";


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
      links: '',
      headers: [
        {title: 'ID', key: 'id'},
        {title: 'Nombre', key: 'name'},
        {title: 'Fecha de inicio', key: 'startDate'},
        {title: 'Fecha de finalización', key: 'endDate'},
        {title: 'Estado', key: 'isActive'},
        { key: 'link', sortable: false},
      ],
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
  <h1>Periodos académicos</h1>
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
      <VBtn to="agregar-periodo" class="mx-2" prepend-icon="ri-add-fill"> Agregar</VBtn>
    </VCardTitle>
    <VDataTable
      :items="items"
      :search="search"
      :headers="headers"
    >
      <template v-slot:item.isActive="{item}">
        <VChip :color="item.isActive ? 'green' : ''" >
          {{ periodActivityFormatter(item.isActive)}}
        </VChip>
      </template>
      <template v-slot:item.link="{item}">
        <RouterLink :to="item.id + '/grupos-investigacion/listar-grupos'">
          <VIcon icon="ri-search-eye-fill"/>
        </RouterLink>
      </template>
    </VDataTable>
  </VCard>
</template>
