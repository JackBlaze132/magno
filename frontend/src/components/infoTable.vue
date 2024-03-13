<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Item {
  id: number, 
  userCode: string, 
  identificationNumber: string,
  firstName: string, 
  middleName: string, 
  lastName: string, 
  secondLastName: string, 
  academicProgram: number, 
  email: string, 
  phoneNumber: string, 
  sex: number 
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
          <th>ID</th>
          <th>Code</th>
          <th>C.C.</th>
          <th>First name</th>
          <th>Middle name</th>
          <th>Last name</th>
          <th>Second last name</th>
          <th>academic program</th>
          <th>E-mail</th>
          <th>Phone number</th>
          <th>Sex</th>
        </tr>
        <tr v-for="item in items" :key="item.id">
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.id }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.userCode }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.identificationNumber }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.firstName }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.middleName }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.lastName }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.secondLastName }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.academicProgram }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.email }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.phoneNumber }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.sex }}
          </td>
        </tr>
    </table>
</template>