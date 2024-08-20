
import { RouteLocationNormalized} from 'vue-router'


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
      {
        path: '/periodos',
        redirect: '/periodos/listar-periodos',
        component: () => import('@/views/assestment-periods/index.vue'),
        children:[
          {
            path:'listar-periodos',
            name:'listar-periodos',
            component: () => import('@/views/assestment-periods/listPeriods.vue')
          },
          {
            path:'agregar-periodo',
            component: () => import ('@/views/assestment-periods/addPeriod.vue')
          },
          {
            path:':idPeriodo/editar-periodo',
            name: 'editar-preiodo',
            component: () => import ('@/views/assestment-periods/editPeriod.vue')
          },
          {
            path:':idPeriodo/grupos-investigacion',
            name: 'grupos-investigacion',
            redirect: (to: RouteLocationNormalized) => {
              return { name: 'listar-grupos', params: { idPeriodo : to.params.idPeriodo } }
            },
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
                path:':idGrupo/editar-grupo',
                name:'editar-grupo',
                component: () => import ('@/views/research-groups/editGroupByPeriod.vue')

              },
              {
                path:':idGrupo/semilleros',
                name:'semilleros',
                redirect: (to: RouteLocationNormalized) => {
                  return { name: 'listar-semilleros', params: { idPeriodo: to.params.idPeriodo, idGrupo : to.params.idGrupo } }
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
                    path:':idSemillero/editar-semillero',
                    name:'editar-semillero',
                    component: () => import ('@/views/seedbeds/editSeedbedByGroup.vue'),
                  },

                  {
                    path:':idSemillero',
                    redirect: (to: RouteLocationNormalized) => {
                      return { name: 'detalles', params: { idPeriodo: to.params.idPeriodo, idGrupo : to.params.idGrupo, idSemillero: to.params.idSemillero } }
                    },
                    component: () => import ('@/views/lounge/index.vue'),
                    children:[
                      {
                        name: 'detalles',
                        path: 'detalles',
                        component: () => import ('@/views/lounge/members.vue'),
                      },
                      {
                        name: 'editar-coordinador',
                        path: 'editar-coordinador',
                        component: () => import ('@/views/lounge/editCoordinator.vue'),
                      },
                      {
                        name: 'editar-tutor',
                        path: 'editar-tutor',
                        component: () => import ('@/views/lounge/editTutor.vue'),
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
      {
        path: '/usuarios',
        redirect:'/usuarios/listar-usuarios',
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
        component: () => import('@/views/functionary/index.vue'),
        redirect: (to: RouteLocationNormalized) => {
          return { name: 'listar-funcionarios' }
        },
        children:[
          {
            path:'listar-funcionarios',
            name:'listar-funcionarios',
            component: () => import ('@/views/functionary/listFunctionaries.vue')
          },
          {
            path: 'agregar-funcionarios',
            name: 'agregar-funcionarios',
            component: () => import ('@/views/functionary/addFunctionary.vue')
          }

        ]
      },
      {
        path: '/estudiantes',
        component: () => import('@/views/students/index.vue'),
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
