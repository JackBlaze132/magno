<script setup lang="ts">
import TableTeachers from '@/components/tableTeachers.vue';
import { VCard } from 'vuetify/components';
</script>

<template>
  <VCard class="pa-5" rounded="lg">

  <tableTeachers/>
  <tableMembers/>
  </VCard>
</template>
