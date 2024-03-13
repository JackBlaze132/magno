<script lang="ts">
import { defineComponent } from 'vue';
import InfoTable from '../components/infoTable.vue';

export default defineComponent({
  name: 'tableView',
  components: {
    InfoTable,
  },
  setup() {
    const items = [
      { id: 1, name: 'Eder', code:'2220211052', email:'eder@g.com', academicProgram:'sys' },
      { id: 2, name: 'Santiago', code:'2220211016', email:'str@g.com', academicProgram:'sys'},
      { id: 3, name: 'Esteban', code:'2220241052', email:'estb@g.com', academicProgram:'sys' },
    ];

    return { items };
  },
});
</script>

<template>
  <div>
    <h1>Table View</h1>
    <InfoTable :items="items" />
  </div>
</template>