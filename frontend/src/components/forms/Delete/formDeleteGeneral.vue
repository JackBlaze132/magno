<template>
  <VCard class="pa-5 my-3" color="surface" max-width="600">
    <VCardTitle>
      Eliminar {{content}}
    </VCardTitle>
    <VDivider/>
    <VCardText>
      Esta a punto eliminar {{content}}, si esta seguro de que desea eliminar este elemento por favor ingrese <span class="px-1" style="background-color:rgb(var(--v-theme-grey-300))"> eliminar {{ content }}</span>en el campo de abajo.
    </VCardText>
    <VForm validate-on="submit" @submit.prevent="deleteItem">
      <VTextField  name="field" id="field" v-model="inputValue" :placeholder="`eliminar ${content}`"/>
      <VcardItem class="d-flex justify-end">
        <LoadingBtn icon="ri-delete-bin-5-line" text="Eliminar" :loading="loading" color="error" ></LoadingBtn>
      </VcardItem>
    </VForm>
  </VCard>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import API from "@/utils/api";
import LoadingBtn from '../loadingBtn.vue';
import { VCardText } from 'vuetify/components';

export default defineComponent({
  name: 'formUpdateGroup',
  props: {
    label: {
      type: String,
    },
    content: {
      type: String,
    },
    index: {
      type: Number,
    },
  },
  data() {
    return {
      inputValue: '',
      loading: false,
    };
  },
  methods: {
    deleteItem() {
      const expectedValue = `eliminar ${this.content}`;
      if (this.inputValue !== expectedValue) {
        alert(`Por favor ingrese "${expectedValue}" para confirmar la eliminación.`);
        return;
      }

      this.loading = true;

      if (this.content == 'semillero') {
        API.delete(API.DELETE_RESEARCH_SEEDBED + this.index)
          .then((data) => {
            if (data.error) {
              console.error("Error al realizar la solicitud", data.error);
              this.loading = false;
            } else {
              console.log(data);
              this.$router.push({ path: this.$route.path }).then(() => {
                this.$router.go(0);
              });
            }
          })
          .catch((error) => {
            console.error('Error al realizar la solicitud:', error);
            this.loading = false;
          });
      } else if (this.content == 'periodo') {
        API.delete(API.DELETE_ASSESMENT_PERIOD + this.index)
          .then((data) => {
            if (data.error) {
              console.error("Error al realizar la solicitud", data.error);
              this.loading = false;
            } else {
              console.log(data);
              this.$router.push({ path: this.$route.path }).then(() => {
                this.$router.go(0);
              });
            }
          })
          .catch((error) => {
            console.error('Error al realizar la solicitud:', error);
            this.loading = false;
          });
      }  else if (this.content == 'grupo') {
        API.delete(API.DELETE_INVESTIGATION_GROUP + this.index)
          .then((data) => {
            if (data.error) {
              console.error("Error al realizar la solicitud", data.error);
              this.loading = false;
            } else {
              console.log(data);
              this.$router.push({ path: this.$route.path }).then(() => {
                this.$router.go(0);
              });
            }
          })
          .catch((error) => {
            console.error('Error al realizar la solicitud:', error);
            this.loading = false;
          });
      }
    }
  }
});
</script>

