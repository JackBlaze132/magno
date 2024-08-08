<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { periodActivityFormatter } from "@/utils/formatter";
import { VIcon } from "vuetify/components";
import { RouterLink } from "vue-router";


interface Item {
  id: number,
  name: string,
  coordninator: {
    name: string
  }
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
        {title: 'Director', key: 'coordinator.name'},
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
        this.items = await get('getInvestigationGroupsByAssesmentPeriodId/' + this.$route.params.idPeriodo);
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
  <h1>Grupos de investigación</h1>
  <VCard flat class="pa-5 my-3">
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
      <VBtn to="agregar-grupo" class="mx-2" prepend-icon="ri-add-fill"> Agregar</VBtn>
    </VCardTitle>
    <VDataTable
      :items="items"
      :search="search"
      :headers="headers"
    >
      <!--<template v-slot:item.isActive="{item}">
        <VChip :color="item.isActive ? 'green' : ''" >
          {{ periodActivityFormatter(item.isActive)}}
        </VChip>
      </template>-->

      <template v-slot:item.link="{item}">
        <QuickActions
          :toView="item.id + '/semilleros'"
          :toEdit="item.id + '/editar-grupo'"
          :toDelete="item.id + '/?'"
          ></QuickActions>
      </template>
    </VDataTable>
  </VCard>
</template>
