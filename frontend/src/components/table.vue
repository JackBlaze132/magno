<script>
defineProps({
    items:{
        type: Array,
        required: true
    }
});
</script>

<template>
    <table>
        <tr v-for="item in items" :key="item.id">
        </tr>
    </table>
</template>