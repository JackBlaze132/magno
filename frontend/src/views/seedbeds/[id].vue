<script lang='ts'>

import { useRoute } from 'vue-router/auto';
import tableMembers1 from '../../components/tableMembers1.vue';

/*const route = useRoute("/seedbeds/[id]")
console.log(route)*/


export default {
  name: 'members',
  components: {
    tableMembers1,
  },
  data() {
    return {
      students: [],
      teachers:[]

    };
  },
  created() {
    this.getStudents();
    this.getTeachers();
  },
  methods: {
    async getStudents() {
      const route = useRoute("/seedbeds/[id]")
      console.log(route)
      try {
        const response = await fetch(`/api/getStudentProfiles${route.params.id}`);
        console.log(response);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        this.students = data;
        console.log(data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    async getTeachers() {
      const route = useRoute("/seedbeds/[id]")
      console.log(route)
      try {
        const response = await fetch(`/api/getTeacherProfiles${route.params.id}`);
        console.log(response);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        this.teachers = data;
        console.log(data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
  },
};




</script>
<template>
    <h1>Semillero</h1>
    <tableMembers1 :students="students" :teachers="teachers"></tableMembers1>
</template>