<script setup lang="ts">
</script>

<template>
  <div>
    <RouterLink style="margin: 0 5px;" to="/">Inicio</RouterLink>
    <RouterLink style="margin: 0 5px;color: darkorchid; font-weight: bolder;" to="/users">Usuarios</RouterLink>
    <RouterLink style="margin: 0 5px;color: orange; font-weight: bolder;" to="/teachers">Profesores</RouterLink>
    <RouterLink style="margin: 0 5px;color: greenyellow; font-weight: bolder;" to="/seedbeds">Semilleros</RouterLink>
    <RouterLink style="margin: 0 5px;color: red; font-weight: bolder;" to="/academic-programs">Programas</RouterLink>
    
    <RouterView></RouterView>
  
  </div>
  <HelloWorld msg="Vite + Vue" />
</template>

