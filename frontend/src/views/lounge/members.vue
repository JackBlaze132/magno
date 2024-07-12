<script setup lang="ts">


import { VCard } from 'vuetify/components';
import { get } from "@/utils/api";
import LoadingManager from '@/utils/loadingManager';


</script>

<script lang="ts">
interface Item{
  name: string
}
export default {
  data(){
    return {
      items: [] as Item[],
      loading: true,
    }
  },
  // ...
  created() {
    this.getData();
    LoadingManager.setTotalComponents(4);
    LoadingManager.reset();
  },
  methods: {
    async getData(){
      try {
        const data = await get('getResearchSeedbedById/' + this.$route.params.idSemillero);
        this.items = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    onChildLoeaded (){
      LoadingManager.onChildLoaded();
      if (LoadingManager. allComponentsLoaded()){
        this.loading = false;
      }

    }
  },
}
</script>


<template>
  <h1 v-if="items.length != 0">{{items[0].name}}</h1>

  <VCard class="pa-5" rounded="lg">
    <VOverlay :model-value="loading" class="d-flex align-center justify-center" scrim="white" opacity="0.85" persistent contained>
      <v-progress-circular indeterminate color="primary" size="64"/>
    </VOverlay>
    <tableCoordinator @loaded="onChildLoeaded"/>
    <tableTeachers @loaded="onChildLoeaded"/>
    <tableMembers @loaded="onChildLoeaded"/>
    <TableExternal @loaded="onChildLoeaded"/>


  </VCard>
</template>


