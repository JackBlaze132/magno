import { createRouter, createWebHistory } from "vue-router/auto"
import {routes} from 'vue-router/auto-routes'
import { App } from "vue"

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default function (app:App) {
  app.use(router)
}

export { router }
