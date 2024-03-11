<script setup lang="ts">
import HelloWorld from './components/Obsidian.vue'
</script>

<template>
  <div>
    <RouterLink style="margin: 0 5px;" to="/">Home</RouterLink>
    <RouterLink style="margin: 0 5px;" to="/lina">Lina</RouterLink>
    <RouterLink style="margin: 0 5px;color: orange; font-weight: bolder;" to="/test">Test</RouterLink>
    <RouterView></RouterView>
    <a href="https://vitejs.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
  <HelloWorld msg="Vite + Vue" />
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
