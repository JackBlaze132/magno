<script lang='ts'>
import tableGroups from '../../components/tableGroups.vue';

export default {
  name: 'research-groups',
  components: {
    tableGroups,
  },
  data() {
    return {
      items: [],
    };
  },
  created() {
    this.getacademicPrograms();
  },
  methods: {
    async getacademicPrograms() {
      try {
        const response = await fetch('/api/getInvestigationGroups');
        console.log(response);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        this.items = data;
        console.log(data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
  },
};


</script>

<template>
  <div>
    <h1>Grupos de Investigación</h1>
    <div>
    <tableGroups :items="items"></tableGroups>
  </div>
  </div>
</template>