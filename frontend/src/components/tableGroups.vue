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
        const data = await get('getInvestigationGroupsByAssesmentPeriodId/' + this.$route.params.id);
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
      <!--<template v-slot:item.isActive="{item}">
        <VChip :color="item.isActive ? 'green' : ''" >
          {{ periodActivityFormatter(item.isActive)}}
        </VChip>
      </template>-->

      <template v-slot:item.link="{item}">
        <RouterLink :to="item.id + '/semilleros'">
          <VIcon icon="ri-search-eye-fill"/>
        </RouterLink>
      </template>
    </v-data-table>
  </vcard>
</template>
