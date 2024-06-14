<script lang="ts">

import formAddUser from '../../components/formAddUser.vue';

interface Item {

userIdentification: string,
email: string,
isExternalUser: boolean
}


export default {
    name: 'AddUser',
    components: {
        formAddUser
    },

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
            
                this.$router.push('/users')
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
    <div>
        <h1>Añadir Usuario</h1>
        <form @submit.prevent="addUser">
        <formAddUser :item="item"></formAddUser>
        </form>
    </div>

</template>