export const routes = [
  { path: '/', redirect: '/home' },
  {
    path: '/',
    component: () => import('@/layouts/default.vue'),
    children: [
      {
        path: 'home',
        component: () => import('@/views/home/index.vue'),
      },
      {
        path: 'groups',
        component: () => import('@/views/research-groups/index.vue')
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
      {path: '/users', redirect: '/users/listUsers'},
      {
        path: '/users',
        component: () => import('@/views/users/index.vue'),
        children:[
          {
            path: 'listUsers',
            component: () => import('@/views/users/listUser.vue')
          },
          {
            path: 'addUser',
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
