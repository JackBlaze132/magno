
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
                component: () => import ('@/views/research-groups/listGroupsByPeriod.vue'),
              },
              {
                path:'agregar-grupo',
                name:'agregar-grupo',
                component: () => import ('@/views/research-groups/addGroupByPeriod.vue'),
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
                    component: () => import ('@/views/seedbeds/listSeedbedsByGroup.vue'),
                  },
                  {
                    path:'agregar-semillero',
                    name:'agregar-semillero',
                    component: () => import ('@/views/seedbeds/addSeedbedByGroup.vue'),
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
                      },
                      {
                        path: 'agregar-estudiante',
                        component: () => import ('@/views/lounge/addStudent.vue')
                      }
                    ]
                  },
                ]
              }
            ]
          },

        ]
      },
      {
        path: '/semilleros',
        component: () => import('@/views/seedbeds/listSeedbeds.vue'),
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
      {
        path: '/funcionarios',
<<<<<<< Updated upstream
        component: () => import('@/views/functionary/index.vue'),
      },
      {
        path:'/estudiantes',
        component: () => import('@/views/students/index.vue'),
=======
        component: () => import('@/views/employees/index.vue'),
>>>>>>> Stashed changes
      }


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
