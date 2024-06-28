<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Student {
  id: number,
  name: string
  userCode: string,
  identificationNumber: string,
  phoneNumber: string,
  semester: number,
  email: string,
  wasActive: boolean,
  sex: string,
  userStudent: {
    isExternalUser: boolean
  }
}

interface Teacher {
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
  },
  
}

export default defineComponent({
  name: 'tableMembers1',
  props: {
    students: {
      type: Array as PropType<Student[]>,
      required: true,
    },
    teachers: {
      type: Object as PropType<Teacher[]>,
      required: true,
    }
  },
  methods:{
    formatType( isExternalUser: boolean){
      return isExternalUser ? 'externo' : 'interno'
    },
    formatWasActive( wasActive: boolean){
      return wasActive ? 'Estuvo activo' : 'no estuvo activo'
    }
  },
});
</script>

<template>
  <h3>Students</h3>
    <table>
        <tr>
          <th>ID</th>
          <th>Semillero</th>
          <th>Coordinador</th>
        </tr>
        <tr v-for="item in students" :key="item.id">
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
            {{ item.phoneNumber }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.semester }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.email }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ formatWasActive(item.wasActive) }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.sex }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ formatType(item.userStudent.isExternalUser) }}
          </td>
        </tr>
    </table>
    <h3>Teachers</h3>
    <table>
      <tr>
        <th>ID</th>
        <th>Semillero</th>
        <th>Coordinador</th>
      </tr>
      <tr v-for="item in teachers" :key="item.id">
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
          {{ item.phoneNumber }}
        </td>
        <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
          {{ item.email }}
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