<script lang="ts">
import { defineComponent } from 'vue'
import { get, post } from "@/utils/api";
import { VSelect } from 'vuetify/components';


interface Item {
  name: string,
  coordinator_fp_id: string
}

interface Functionary{
  id: number,
  name: string
}

export default defineComponent({
  name: 'formAddPeriod',
  data() {
    return {
      item: {} as Item,
      functionaries: [] as Functionary[],
      selectedFunctionary: null
    }
  },
  created() {
      this.getFunctionaries();
  },

  methods: {
    async getFunctionaries() {
      try {
        this.functionaries = await get('getFunctionaryProfiles');
        this.$emit('loaded');
        console.log("Hola obtuve los funcionarios")
      } catch (error) {
        console.error('Error fetching users:', error);
        }
    },
    addGroup() {
      post('addInvestigationGroup', this.item)
      .then((data) => {
        this.$router.push('listar-grupos');
      })
      .catch((error) => {
        console.error('Error al realizar la solicitud:', error);
      });
    }
  },
  computed: {
    functionaryId() {
      return this.functionaries.map(functionary => functionary.id);
    },
  }
});
</script>

<template>
  <VForm validate-on="submit" @submit.prevent="addGroup">
    <VTextField label="Nombre" name="name" id="name" v-model="item.name"/>
    <VSelect label="Director" :items="functionaries" item-title="name" item-value="id" v-model="item.coordinator_fp_id"></VSelect>

    <VBtn
      text="Guardar"
      prepend-icon="ri-save-2-line"
      type="submit"
    />
  </VForm>
</template>
