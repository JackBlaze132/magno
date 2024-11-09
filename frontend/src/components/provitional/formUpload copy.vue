<script setup lang="ts">
  //import { useTheme } from 'vuetify';
  import {defineComponent} from 'vue'

</script>

<script lang="ts">
  import { VCol } from 'vuetify/components';

    export default {
      data: () => ({
        files: [],
        rules: [
            (value: File[]) => {
          //const fileType = value[0].type;
          const fileName = value[0].name;
          const fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
          return fileExtension === 'xlsx' || 'Solo se permiten archivos .xlsx';
        },
        ]
      }),
    }
    defineComponent({
      name: 'formUpdate'
    })

</script>

<template>
  <div class="auth-wrapper d-flex align-md-end align-sm-center flex-md-column justify-center pa-4">
    <VCol cols="12" >
      <VForm @submit.prevent>
      <v-file-input
        :rules="rules"
        v-model="files"
        label="Subir archivo"
        prepend-icon="ri-attachment-2"
        variant="outlined"
        type="file"
        accept=".xlsx"
    >
      <template v-slot:selection="{ fileNames }">
        <!--<template v-for="(fileName, index) in fileNames" :key="fileName">-->
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
        prepend-icon="ri-upload-cloud-fill"
        color="black"

      >
        Subir
      </VBtn>
    </span>
  </VForm>
  </VCol>
</div>
</template>



