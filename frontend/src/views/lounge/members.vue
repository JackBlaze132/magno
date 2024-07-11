<script setup lang="ts">
import TableCoordinator from '@/components/seedbeds/tableCoordinator.vue';
import { defineComponent } from 'vue';
import { VCard } from 'vuetify/components';
import { get } from "@/utils/api";
import { it } from 'vuetify/locale';

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
      componentsLoaded: 0,
      totalComponents: 4
    }
  },
  // ...
  created() {
    this.getData();
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
      this.componentsLoaded += 1;
      if (this.componentsLoaded === this.totalComponents) {
        this.loading = false
      }
    }
  },

  /*data() {
    return {
      loading: true
    }
  },
  mounted() {
    this.checkLoading()
  },
  methods: {
    checkLoading() {
      const components = [this.$refs.tableCoordinator, this.$refs.tableTeachers]
      const allLoaded = components.every(component => component.loaded)
      if (allLoaded) {
        this.loading = false
      } else {
        setTimeout(this.checkLoading, 100)
      }
    }
  }*/
}
</script>


<template>
  <h1 v-if="items.length != 0">{{items[0].name}}</h1>

  <VCard class="pa-5" rounded="lg">
    <VOverlay :model-value="loading" class="d-flex align-center justify-center" scrim="white" opacity="0.8" persistent contained>
      <v-progress-circular indeterminate color="primary" size="64"/>
    </VOverlay>


    <tableCoordinator @loaded="onChildLoeaded"/>
    <tableTeachers @loaded="onChildLoeaded"/>
    <tableMembers @loaded="onChildLoeaded"/>
    <TableExternal @loaded="onChildLoeaded"/>


  </VCard>
</template>


