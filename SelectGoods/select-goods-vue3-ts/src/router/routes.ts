import type { RouteRecordRaw } from 'vue-router'

export const constantRoutes: RouteRecordRaw[] = [
  {
    path: '/login',
    // name属性用于权限管理, 每条路由都要追加这个命名空间
    name: 'login',
    component: () => import('@/views/login/index.vue')
  },
  // 登录成功以后展示数据的路由
  {
    path: '/',
    name: 'layout',
    component: () => import('@/views/home/index.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/404/index.vue')
  },
  // 当上面路由都没有匹配上的时候 我们访问的路由
  {
    path: '/:pathMatch(.*)*',
    name: 'any',
    component: () => import('@/views/404/index.vue')
  },
  // gwes demo
  {
    path: '/demo',
    name: 'demo',
    component: () => import('@/views/demo/index.vue')
  }
]
