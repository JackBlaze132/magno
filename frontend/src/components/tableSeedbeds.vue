<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Item {
  id: number,
  name: string,
  coordinator:{
    name: string
  }
}

export default defineComponent({
  name: 'tableSeedbeds',
  props: {
    items: {
      type: Array as PropType<Item[]>,
      required: true,
    },
  },
});
</script>

<template>
    <table>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Coordinador</th>
        </tr>
        <tr v-for="item in items" :key="item.id">
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.id }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.name }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.coordinator.name }}
          </td>
        </tr>
    </table>
</template>