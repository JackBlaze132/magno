<script lang="ts">
  import navbar from '../src/components/navbar.vue'
  import sidebar from '../src/components/sidebar.vue'
</script>
<template>
  <VApp>
    <div class="auth-wrapper">
      <navbar v-if="['/', 'news'].includes($route.name)"/>
      <sidebar v-if="!['/', 'news', '/login/'].includes($route.name)"/>
      <RouterView></RouterView>
    </div>
  </VApp>
</template>

<script setup lang="ts">
  //
</script>
