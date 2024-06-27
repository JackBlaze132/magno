<script setup lang="ts">
  import magnoDark from '@images/logos/magno-dark.svg?raw'
</script>

<template>
  <v-card >
    <v-layout class="auth-wrapper">
      <v-navigation-drawer class="bg-grey-lighten-2 position-fixed" theme="light" permanent>
        <v-list nav>
          <v-list-item>
            <VImg
              :width="500"
              v-html=magnoDark
            />
          </v-list-item>
          <v-list-item
            prepend-icon="ri-home-3-fill"
            title="Inicio"
            value="inicio"
            to="/inicio">
          </v-list-item>
          <v-list-item
            prepend-icon="ri-calendar-check-fill"
            title="Periodos académicos"
            value="periodos"
            to="/periodos">
          </v-list-item>
          <v-list-item
            prepend-icon="ri-file-2-line"
            title="Informes"
            value="informes"
            to="/informes">
          </v-list-item>
          <VDivider class="px-4 my-4"></VDivider>
          <v-list-item
            prepend-icon="ri-user-settings-fill"
            title="Usuarios"
            value="usuarios"
            to="/usuarios">
          </v-list-item>
        </v-list>
        <template v-slot:append>
          <div class="pa-2">
            <v-btn
            color="black"
            block
            prepend-icon="ri-logout-box-line"
            >
              Cerrar sesión
            </v-btn>
          </div>
        </template>
      </v-navigation-drawer>
      <v-main></v-main>
    </v-layout>
  </v-card>
</template>
