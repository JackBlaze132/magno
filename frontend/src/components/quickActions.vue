<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'quickActions',
    props: {
      toView:{
        type: String,
      },
      toEdit:{
        type: String,
      },
      toDelete:{
        type: String,
      }
    }
  })
</script>

<template>
  <RouterLink :to="toView">
    <VIcon icon="ri-eye-line" class="action view"/>
    <VTooltip activator="parent" location="top">
      Ver
    </VTooltip>
  </RouterLink>
  &nbsp
  <RouterLink :to="toEdit">
    <VIcon icon="ri-edit-box-line" class="action edit"/>
    <VTooltip activator="parent" location="top">
      Editar
    </VTooltip>
  </RouterLink>
  &nbsp
  <RouterLink :to="toDelete">
    <VIcon icon="ri-delete-bin-5-line" class="action delete"/>
    <VTooltip activator="parent" location="top">
      Eliminar
    </VTooltip>
  </RouterLink>
</template>




