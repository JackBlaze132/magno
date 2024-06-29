<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Item {
  id: number,
  name: string,
  userCode: string,
  identificationNumber: string,
  email: string, 
  phoneNumber: string,
  sex: string,
  dependency: {
    id: number,
    name: string
  }
}

export default defineComponent({
  name: 'tableTeachers',
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
          <th>Código</th>
          <th>Número de identificación</th>
          <th>E-mail</th>
          <th>Número telefónico</th>
          <th>Sexo</th>
          <th>dependencia</th>
        </tr>
        <tr v-for="item in items" :key="item.id">
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.id }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.name }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.userCode }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.identificationNumber }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.email}}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.phoneNumber}}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.sex }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.dependency.name }}
          </td>
        </tr>
    </table>
</template>