<script lang="ts">
import { defineComponent } from "vue"

//utils
import API from "@/utils/api";
import { periodActivityFormatter } from "@/utils/formatter";
import { VIcon } from "vuetify/components";
import { RouterLink } from "vue-router";


interface Item {
  id: number,
  name: string,
  coordinator: {
    name: string
  },
  assesmentPeriod:{
    name: string
  }
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
      search: '',
      links: '',
      sortBy:[{key:'assesmentPeriod.name', order:'des'}, {key: 'name', order:'asc'}],
      headers: [
        {title: 'ID', key: 'id'},
        {title: 'Nombre', key: 'name'},
        {title: 'Periodo', key: 'assesmentPeriod.name'},
        {title: 'Coordinador', key: 'coordinator.name'},
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
        this.items = await API.get(API.GET_RESEARCH_SEEDBEDS);
        this.$emit('loaded');
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    periodActivityFormatter,
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
      <VBtn to="addPeriod" class="mx-2" prepend-icon="ri-add-fill"> Agregar</VBtn>
    </VCardTitle>
    <VDataTable
      :items="items"
      :search="search"
      :headers="headers"
      :sort-by="sortBy"

    >
    </VDataTable>
  </VCard>
</template>
