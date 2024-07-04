<template>
  <div class="auth-wrapper d-flex align-md-end align-sm-center flex-md-column justify-center pa-4">
    <VCol cols=12>
    <v-form ref="form" @submit.prevent="submitFile">
      <v-file-input
        v-model="file"
        label="Archivo de excel"
        type="file"
        accept=".xlsx"
        :rules="rules"
      >
      <template v-slot:selection="{ fileNames }">
        <template v-for="(fileName) in fileNames" :key="fileName">
          <v-chip
            class="me-2 pa-4"
            size="small"
            prepend-icon="ri-file-excel-2-fill"
          >
            {{ fileName }}
          </v-chip>
        </template>
      </template>
      </v-file-input>
      <span class="d-flex justify-center">
        <VBtn
          rounded="xl"
          class="my-2 text-capitalize"
          prepend-icon="ri-upload-cloud-2-fill"
          color="black"
          type="submit"
        >
          Subir
        </VBtn>
      </span>
    </v-form>
  </VCol>
  </div>
</template>


<script lang="ts">
import { file } from '@babel/types';

export default {
  data: () => ({
    file: [],
    rules: [
      (value: File[]) => {
        const fileName = value[0].name;
        console.log(fileName)
        const fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return fileExtension === 'xlsx' || 'Solo se permiten archivos.xlsx';
      },
    ]
  }),
  methods: {
    submitFile(): void {
      const formData = new FormData();
      if (this.file) {
        formData.append('file', this.file[0]);
      }

      fetch('/api/addUsersByExcel', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
    .then(data => {
      if (data.error) {
        console.error("Error al realizar la solicitud", data.error);
      } else {
        console.log(data);
        this.$router.push('/usuarios');
      }
    })
      .catch(error => console.error("Error al realizar la solicitud", error));
    }
  }
}
</script>
