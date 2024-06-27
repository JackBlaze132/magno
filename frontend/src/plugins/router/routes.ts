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
            path:'añadir-periodo',
            component: () => import ('@/views/assestment-periods/addPeriod.vue')
          },
          {
            path:':id/grupos-investigacion',
            component: () => import ('@/views/research-groups/index.vue'),
            children:[
              {
                path:'listar-grupos',
                component: () => import ('@/views/research-groups/listGroups.vue'),
              },
              {
                path:':id/semilleros',
                component: () => import ('@/views/seedbeds/listSeedbeds.vue')
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
            path: 'añadir-usuario',
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
