
import { RouteLocationNormalized } from 'vue-router'

export const routes = [
  { path: '/', redirect: '/login' },
  {
    path: '/',
    component: () => import('@/layouts/default.vue'),
    children: [
      {
        path: 'inicio',
        component: () => import('@/views/home/index.vue'),
      },
      {
        path: '/informes',
        component: () => import('@/views/reports/index.vue'),
      },
      {path: '/periodos', redirect: '/periodos/listar-periodos'},
      {
        path: '/periodos',
        component: () => import('@/views/assestment-periods/index.vue'),
        children:[
          {
            path:'listar-periodos',
            component: () => import('@/views/assestment-periods/listPeriods.vue')
          },
          {
            path:'agregar-periodo',
            component: () => import ('@/views/assestment-periods/addPeriod.vue')
          },
          {
            path: ':idPeriodo/grupos-investigacion',
            redirect: (to: RouteLocationNormalized) => {
              return { name: 'listar-grupos', params: { idPeriodo : to.params.idPeriodo } }
            }
          },
          {
            path:':idPeriodo/grupos-investigacion',
            component: () => import ('@/views/research-groups/index.vue'),
            children:[
              {
                path:'listar-grupos',
                name:'listar-grupos',
                component: () => import ('@/views/research-groups/listGroups.vue'),
              },
              {
                path:':idGrupo/semilleros',
                redirect: (to: RouteLocationNormalized) => {
                  return { name: 'listar-semilleros', params: { idGrupo: to.params.idGrupo } }
                },
                component: () => import ('@/views/seedbeds/index.vue'),
                children:[

                  {
                    path:'listar-semilleros',
                    name:'listar-semilleros',
                    component: () => import ('@/views/seedbeds/listSeedbeds.vue'),
                  },
                  {
                    path:':idSemillero',
                    redirect: (to: RouteLocationNormalized) => {
                      return { name: 'detalles', params: { idSemillero: to.params.idSemillero } }
                    },
                    component: () => import ('@/views/lounge/index.vue'),
                    children:[
                      {
                        name: 'detalles',
                        path: 'detalles',
                        component: () => import ('@/views/lounge/members.vue')
                      },
                      {
                        path: 'subir-estudiantes',
                        component: () => import ('@/views/lounge/uploadStudent.vue')
                      }
                    ]
                  },
                  {
                    path:'subir-estudiantes',
                    component: () => import ('@/views/reports/upload.vue')
                  }
                ]
              }
            ]
          },

        ]
      },
      {
        path:'reports',
        component: () => import('@/views/reports/index.vue'),
        children: [
          {
            path: 'upload',
            component: () => import('@/views/reports/upload.vue')
          }
        ]
      },
      {path: '/usuarios', redirect: '/usuarios/listar-usuarios'},
      {
        path: '/usuarios',
        component: () => import('@/views/users/index.vue'),
        children:[
          {
            path: 'listar-usuarios',
            component: () => import('@/views/users/listUser.vue')
          },
          {
            path: 'agregar-usuarios',
            component: () => import('@/views/users/addUser.vue')
          }
        ],
      },


    ],
  },
  {
    path: '/',
    component: () => import('@/layouts/blank.vue'),
    children: [
      {
        path: 'login',
        component: () => import('@/views/login/index.vue'),
      },

    ],
  },
]
