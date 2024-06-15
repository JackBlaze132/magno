<script lang="ts">
import formUpload from '../../components/reports/formUpload.vue';
import { defineComponent } from 'vue'

export default{
    name: 'upload',
    components: {
        formUpload
    },
    data() {
      return {
        item: {} as Item
      }
    },

    methods: {
        addUser() {

            fetch('/api/addUsersByExcel', {
            method: 'POST',
            headers: {
                'Content-Type': 'multipart/form data'
            },
            body: JSON.stringify(this.item)
            })
            .then(data => {
                console.log(data);

                this.$router.push('/reports')
            })
            .catch(error => {
                console.log(this.item)
                console.error('Error al realizar la solicitud:', error);
            });
        }
    }
}
</script>

<template>
    <h1>subir</h1>
    <formUpload />
    <p>hola mundo</p>
</template>
