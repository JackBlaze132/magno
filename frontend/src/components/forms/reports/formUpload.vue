<template>
  <div class="auth-wrapper d-flex align-md-end align-sm-center flex-md-column justify-center pa-4">
    <VCol cols=12>
    <VForm ref="form" @submit.prevent="submitFile">
      <VFileInput
        v-model="file"
        label="Archivo de excel"
        type="file"
        accept=".xlsx"
        :rules="rules"
      >
      <template v-slot:selection="{ fileNames }">
        <template v-for="(fileName) in fileNames" :key="fileName">
          <VChip
            class="me-2 pa-4"
            size="small"
            prepend-icon="ri-file-excel-2-fill"
          >
            {{ fileName }}
          </VChip>
        </template>
      </template>
      </VFileInput>
      <span class="d-flex justify-center">
        <VBtn
          :loading = "loading"
          rounded="xl"
          class="my-2 text-capitalize"
          prepend-icon="ri-upload-cloud-2-fill"
          color="black"
          type="submit"
          text="Cargar"
        >
        </VBtn>
      </span>
    </VForm>
  </VCol>
  </div>
</template>


<script lang="ts">
import { file } from '@babel/types';

export default {
  data: () => ({
    file: [],
    loading: false,
    rules: [
      (value: File[]) => {
        if(value.length != 0){
        const fileName = value[0].name;
        const fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        return fileExtension === 'xlsx' || 'Solo se permiten archivos.xlsx';
      }},
    ]
  }),
  methods: {
    submitFile(): void {
      const formData = new FormData();

      if (this.file.length != 0) {
        formData.append('file', this.file[0]);
        this.loading = true
        fetch('/api/addStudentProfileByExcel/' + this.$route.params.idPeriodo + '/' + this.$route.params.idSemillero, {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(data => {
        if (data.error) {
          console.error("Error al realizar la solicitud", data.error);
        } else {
          console.log(data);
          this.$router.push('detalles');
        }
      })
      .catch(error => console.error("Error al realizar la solicitud", error));
      }


    }
  }
}
</script>
