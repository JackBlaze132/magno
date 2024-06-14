<script lang="ts">
import { defineComponent, PropType } from 'vue';

/*interface Item {
 
  userCode: string,
  email: string,
  isExternalUser: boolean
}*/

export default defineComponent({
  name: 'formLogin',
  /*props: {
    item: {
      type: Object as PropType<Item>,
      required: true,
    },
  },*/
});
</script>

<template>
    <div class="card">
      <h1>Ingresar</h1>
      <br><input type="submit" value="google">
      <br>
      <hr>
      <label for="name">Correo</label>
      <input type="text" name="name" id="name" placeholder="Nombre" />
      <br><label for="name">Contraseña</label>
      <input type="text" name="name" id="name" placeholder="Correo electrónico" />
      <br>
      <a>¿Olvidaste tu Contraseña?</a>
      <br><input type="submit" value="Iniciar Sesion">
      <p>solicita acceso <a>aquí</a></p>
      <a>Aviso de cookies</a>
    </div> 
</template>