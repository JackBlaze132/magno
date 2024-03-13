<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Item {
  id: number;
  name: string;
  code: string;
  email: string;
  academicProgram: string;
}

export default defineComponent({
  name: 'infoTable',
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
          <th>id</th>
          <th>name</th>
          <th>code</th>
          <th>email</th>
          <th>academic program</th>
        </tr>
        <tr v-for="item in items" :key="item.id">
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.id }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.name }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.code }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.email }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.academicProgram }}
          </td>
        </tr>
    </table>
</template>