<!--<script lang="ts">
import { defineComponent, PropType } from 'vue';

interface Item {
  id: number,
  userIdentification: string,
  email: string,
  isExternalUser: boolean,
}

export default defineComponent({
  name: 'tableUsers',
  props: {
    items: {
      type: Array as PropType<Item[]>,
      required: true,
    },
  },
  methods:{
    formatType( isExternalUser: boolean){
      return isExternalUser ? 'externo' : 'interno'
    },
  },
});
</script>-->

<!--<script lang="ts">
import { defineComponent } from 'vue'

interface Item {
  id: number,
  userIdentification: string,
  email: string,
  isExternalUser: boolean,
}

export default defineComponent({
  name: 'tableUsers',
  data() {
    return {
      items: [] as Item[],
    };
  },
  created() {
    this.getUsers();
  },
  methods: {
    async getUsers() {
      try {
        const response = await fetch('/api/getUsers');
        console.log(response);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        this.items = data;
        console.log(data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    formatType(isExternalUser: boolean) {
      return isExternalUser ? 'externo' : 'interno'
    },
  },
});
</script>-->

<script lang="ts">
import { get } from "@/utils/api";
import { defineComponent } from "vue"

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
        const data = await get('getUsers');
        this.items = data;
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
    formatType(isExternalUser: boolean) {
      return isExternalUser ? 'externo' : 'interno'
    },
  }
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
            {{ formatType(item.isExternalUser) }}
            </td>
        </tr>
    </table>

</template>
