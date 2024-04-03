<script lang="ts">
import { defineComponent, PropType } from 'vue'
import { RouterLink } from 'vue-router'

interface Item {
  id: number,
  name: string,
  coordinator: {
    name: string
  }
}

export default defineComponent({
  name: 'tableGroups',
  props: {
    items: {
      type: Array as PropType<Item[]>,
      required: true,
    },
  },
});
</script>

<template>
    <table align="center">
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Director</th>
        </tr>
        
        <tr v-for="item in items" :key="item.id">
           <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.id }}
            </td>
            <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            <RouterLink :to="`/seedbeds/`">{{ item.name }}</RouterLink>
            </td>
            <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.coordinator.name}}
            </td>
        </tr>
       
    </table>
    <br>
    <RouterLink to="/seedbeds/:id"><button>+ Agregar</button></RouterLink>
</template>