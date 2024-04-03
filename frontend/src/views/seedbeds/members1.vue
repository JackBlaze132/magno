<script lang='ts'>
import tableMembers1 from '../../components/tableMembers1.vue';

export default {
  name: 'members1',
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
      try {
        const response = await fetch('/api/getStudentProfiles1');
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
      try {
        const response = await fetch('/api/getTeacherProfiles1');
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
    <h1>Semillero LÚN</h1>
    <tableMembers1 :students="students" :teachers="teachers"></tableMembers1>
</template>