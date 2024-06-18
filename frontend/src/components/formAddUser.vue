<!--<script lang="ts">
import { defineComponent } from 'vue'

interface Item {
  userIdentification: string,
  email: string,
  isExternalUser: boolean
}

export default defineComponent({
  name: 'formAddUser',
  data() {
    return {
      item: {} as Item
    }
  },
  methods: {
    addUser() {
      fetch('/api/addUser', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.item)
      })
      .then(data => {
        console.log(data);

        // Redirigir a la página de usuarios
        this.$router.push('/users')
      })
      .catch(error => {
        console.log(this.item)
        console.error('Error al realizar la solicitud:', error);
      });
    }
  }
});
</script>-->

<script lang="ts">
  import { defineComponent } from 'vue'
  import { post } from "@/utils/api";

  interface Item {
    userIdentification: string,
    email: string,
    isExternalUser: boolean
  }

  export default defineComponent({
    name: 'formAddUser',
    data() {
      return {
        item: {} as Item
      }
  },
  methods: {
    addUser() {
      post('addUser', this.item)
        .then((data) => {
          this.$router.push('/users');
        })
        .catch((error) => {
          console.error('Error al realizar la solicitud:', error);
        });
    }
  }
});
</script>

<template>
  <VForm validate-on="submit" @submit.prevent="addUser">
    <VTextField label="Cedula" type="text" name="identification" id="identification" v-model="item.userIdentification"/>
    <VTextField label="Correo" type="text" name="mail" id="mail"  v-model="item.email"/>
    <VRadioGroup v-model="item.isExternalUser" class="d-flex">
      <VRadio label="Externo" :value="true"/>
      <VRadio label="Interno" :value="false"/>
    </VRadioGroup>
    <VBtn
      text="Agregar"
      prepend-icon="ri-add-fill"
      type="submit"
    />
  </VForm>
</template>
