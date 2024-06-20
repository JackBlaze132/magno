<script lang="ts">
import { defineComponent } from "vue"

//utils
import { get } from "@/utils/api";
import { periodActivityFormatter } from "@/utils/formatter";


interface Item {
  id: number,
  name: string,
  startDate: string,
  endDate: string,
  isActive: boolean,
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
      search: '',
    }
  },
  // ...
  created() {
    this.getPeriods();
  },
  methods: {
    async getPeriods() {
      try {
        const data = await get('getAssesmentPeriods');
        this.items = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    periodActivityFormatter,
  },
})
</script>

<template>
  <table align="center">
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Fecha de inicio</th>
        <th>Fecha de Finalización</th>
        <th>Actividad</th>
      </tr>


      <tr v-for="item in items" :key="item.id" to="/">
         <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
          {{ item.id }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
          {{ item.name }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
          {{ item.startDate}}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.endDate}}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ periodActivityFormatter(item.isActive)}}
          </td>
      </tr>


  </table>
  <br>
  <RouterLink to="/seedbeds/:id"><button>+ Agregar</button></RouterLink>
</template>
