<script setup lang="ts">
import TableCoordinator from '@/components/seedbeds/tableCoordinator.vue';
import { VCard } from 'vuetify/components';
</script>

<template>
  <VCard class="pa-5" rounded="lg">
    <div>
      <v-progress-circular indeterminate color="primary"></v-progress-circular>
    </div>
    <tableCoordinator ref="tableCoordinator"/>
    <tableTeachers ref="tableTeachers"/>
    <tableMembers/>
    <TableExternal/>
  </VCard>
</template>

<!--<script lang="ts">
export default {
  data() {
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
  }
}
</script>-->
