import { defineConfig } from 'vite'
import VueRouter from 'unplugin-vue-router/vite'
import vue from '@vitejs/plugin-vue'



// https://vitejs.dev/config/
export default defineConfig({
  plugins: [VueRouter({
    routesFolder:[{
      src: 'src/views'
    }]
  }), vue()],
  server:{
    proxy:{
      '/api':{
        target:  'http://localhost:8080',
        changeOrigin: true,
        rewrite:(path) => path.replace(/^\/api/, '')   
      },
    }
  }
    //host: '0.0.0.0'
})
