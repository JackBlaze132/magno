

<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer class="bg-grey-lighten-2" theme="light" permanent>
        <v-list nav>
          <v-list-item
            prepend-icon="ri-home-3-fill"
            title="Inicio"
            value="home"
            to="/home">
          </v-list-item>
          <v-list-item
            prepend-icon="ri-gallery-view"
            title="Grupos de investigación"
            value="groups">
          </v-list-item>
          <v-list-item
            prepend-icon="ri-file-2-line"
            title="Informes"
            value="reports"
            to="/reports">
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
      <v-main style="height: 250px"></v-main>
    </v-layout>
  </v-card>
</template>
