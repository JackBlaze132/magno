<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Item {
 
  userCode: string,
  email: string,
  isExternalUser: boolean
}

export default defineComponent({
  name: 'formAddUser',
  props: {
    item: {
      type: Object as PropType<Item>,
      required: true,
    },
  },
});
</script>

<template>
      <label for="name">Codigo</label>
      <input type="text" name="name" id="name" placeholder="Nombre" v-model="item.userCode"/>
      <br><label for="name">Correo</label>
      <input type="text" name="name" id="name" placeholder="Correo electrónico" v-model="item.email"/>
      <br><label for="sex">Externo</label>
      <input type="radio" name="isExternal"   v-model="item.isExternalUser" :value="true">
      <label for="sex">Interno</label>
      <input type="radio" name="isInternal"  v-model="item.isExternalUser" :value="false">
      <br><input type="submit" value="submit">
</template>