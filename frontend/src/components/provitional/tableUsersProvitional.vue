<script lang="ts">
import { defineComponent } from "vue"

//utils
import API from "@/utils/api";
import Formatter from "@/utils/formatter";

interface Item {
  id: number,
  userIdentification: string,
  email: string,
  isExternalUser: boolean,
}

export default defineComponent({

  data() {
    return {
      items: [] as Item[],
    }
  },
  // ...
  created() {
    this.getUsers();
  },
  methods: {
    async getUsers() {
      try {
        const data = await API.get('getUsers');
        this.items = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    externalFormatter(state: boolean){
      return Formatter.externalFormatter(state)
    }
  },
})
</script>

<template>
    <table>
        <tr>
          <th>ID</th>
          <th>Codigo</th>
          <th>E-mail</th>
          <th>Tipo</th>
        </tr>
        <tr v-for="item in items" :key="item.id">
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.id }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.userIdentification }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ item.email }}
          </td>
          <td style="border: 1px dashed; padding:0 10px; margin: 0 5px;">
            {{ externalFormatter(item.isExternalUser) }}
            </td>
        </tr>
    </table>

</template>
