<script lang="ts" setup>
import verticalNav from './components/verticalNav.vue';
</script>

<template>
  <div class="d-flex">
  <verticalNav/>
  <RouterView class="ma-6"/>
</div>
</template>

<style lang="scss">
// As we are using `layouts` plugin we need its styles to be imported
//@use "@layouts/styles/default-layout";
</style>
