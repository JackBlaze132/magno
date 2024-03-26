<script lang='ts'>
import tableSeedbeds from '../../components/tableSeedbeds.vue';

export default {
  name: 'seedbeds',
  components: {
    tableSeedbeds,
  },
  data() {
    return {
      items: [],
    };
  },
  created() {
    this.getSeedbeds();
  },
  methods: {
    async getSeedbeds() {
      try {
        const response = await fetch('/api/getResearchSeedbeds');
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
    <h1>Tabla Semilleros</h1>
    <div >
    <tableSeedbeds :items="items"></tableSeedbeds>
  </div>
  </div>
</template>