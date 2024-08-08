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
      },
      text:{
        type:String,
      }
    }
  })
</script>



<template>
  <VBtn
    :loading="loading"
    rounded="xl"
    class="my-2 text-capitalize"
    :prepend-icon="$props.icon"
    color="black"
    type="submit"
    :text="$props.text">
  </VBtn>
</template>
