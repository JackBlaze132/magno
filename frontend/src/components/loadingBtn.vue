<script lang="ts">
  import { defineComponent } from 'vue';

  export default defineComponent({
    name: 'loadingBtn',
    props: {
      loading:{
        type:Boolean,
      },
      icon: {
        type: String,
      }
    }
  })
</script>



<template>
  <VBtn
    :loading="loading"
    rounded="xl"
    class="my-2 text-capitalize"
    prepend-icon="ri-upload-cloud-2-fill"
    color="black"
    type="submit"
    text="Cargar">
  </VBtn>
</template>
