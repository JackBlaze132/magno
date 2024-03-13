<script lang='ts'>
import { defineComponent} from 'vue';
import InfoTable from '../components/infoTable.vue';



/*
-------------------------------------------------------------------
TESTING TABLE CREATION
-------------------------------------------------------------------
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
});*/

export default {
  name: 'TableView',
  components: {
    InfoTable,
  },
  data() {
    return {
      items: [],
    };
  },
  created() {
    this.getStudents();
  },
  methods: {
    async getStudents() {
      try {
        const response = await fetch('/api/getUsers');
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


/*
onMounted(async () => {
  const response = await fetch('/api/getUsers')
  console.log(response)
  const data = await response.json
  console.log(data)
})*/
</script>

<template>
  <div>
    <h1>Table View</h1>
    <div >
    <infoTable :items="items"></infoTable>
  </div>
  </div>
</template>