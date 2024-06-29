<script setup lang="ts">
import { VCard } from 'vuetify/components';
</script>

<template>
  <VCard class="pa-5" rounded="lg">
    <tableCoordinator/>
    <tableTeachers/>
    <tableMembers/>
    <TableExternal/>
  </VCard>
</template>
