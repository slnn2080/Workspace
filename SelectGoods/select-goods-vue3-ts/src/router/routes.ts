// 常量路由
export const constantRoutes = [
  {
    path: '/login',
    // name属性用于权限管理, 每条路由都要追加这个命名空间
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    meta: {
      title: '登录',
      hidden: true,
      icon: ''
    }
  },
  // 登录成功以后展示数据的路由
  {
    path: '/',
    name: 'Layout',
    component: () => import('@/views/layout/index.vue'),
    meta: {
      title: '首页',
      hidden: false,
      icon: ''
    },
    // layou组件的默认页面
    redirect: '/home',
    children: [
      {
        path: '/home',
        component: () => import('@/views/home/index.vue'),
        meta: {
          title: '首页',
          hidden: false,
          icon: 'HomeFilled'
        }
      }
    ]
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/404/index.vue'),
    meta: {
      title: '',
      hidden: true,
      icon: ''
    }
  },
  {
    path: '/screen',
    component: () => import('@/views/screen/index.vue'),
    name: 'Screen',
    meta: {
      hidden: false,
      title: '数据大屏',
      icon: 'Platform'
    }
  },
  {
    path: '/test',
    component: () => import('@/views/test/index.vue'),
    name: 'Test',
    meta: {
      hidden: false,
      title: '测试页面'
    }
  }
]

// 异步路由
export const asyncRoutes = [
  // ----- gwes demo start -----
  // {
  //   path: '/gwes',
  //   name: 'Demo',
  //   component: () => import('@/views/demo/layout/index.vue'),
  //   meta: {
  //     title: 'Demo',
  //     icon: '',
  //     hidden: true
  //   }
  // },
  // {
  //   path: '/gwes_login',
  //   name: 'GwesLogin',
  //   component: () => import('@/views/demo/login/index.vue'),
  //   meta: {
  //     title: 'Login',
  //     icon: '',
  //     hidden: true
  //   }
  // },
  // {
  //   path: '/master',
  //   component: () => import('@/views/demo/layout/index.vue'),
  //   meta: {
  //     title: 'Master',
  //     icon: '',
  //     hidden: true
  //   },
  //   children: [
  //     {
  //       path: '/master/worker_master',
  //       component: () => import('@/views/demo/wokerMaster/index.vue'),
  //       meta: {
  //         title: 'WorkerMaster',
  //         icon: '',
  //         hidden: true
  //       }
  //     }
  //   ]
  // },
  // {
  //   path: '/master',
  //   component: () => import('@/views/demo/layout/index.vue'),
  //   meta: {
  //     title: 'Master',
  //     icon: '',
  //     hidden: true
  //   },
  //   children: [
  //     {
  //       path: '/master/woker_throughput_monitor',
  //       component: () =>
  //         import('@/views/demo/wokerThroughputMonitor/index.vue'),
  //       meta: {
  //         title: 'WokerThroughputMonitor',
  //         icon: '',
  //         hidden: true
  //       }
  //     }
  //   ]
  // },
  // {
  //   path: '/gwes_settings',
  //   name: 'Settings',
  //   component: () => import('@/views/demo/layout/index.vue'),
  //   meta: {
  //     title: 'Setting',
  //     icon: '',
  //     hidden: true
  //   },
  //   children: [
  //     {
  //       path: '/gwes_settings/setting',
  //       component: () => import('@/views/demo/setting/Display.vue'),
  //       meta: {
  //         title: 'Dispaly',
  //         icon: '',
  //         hidden: true
  //       }
  //     },
  //     {
  //       path: '/gwes_settings/switch_center',
  //       component: () => import('@/views/demo/setting/SwitchCenter.vue'),
  //       meta: {
  //         title: 'SwitchCenter',
  //         icon: '',
  //         hidden: true
  //       }
  //     }
  //   ]
  // },
  // ----- gwes demo end -----
  // 权限管理路由
  {
    path: '/acl',
    // 权限管理 一级路由 要使用 layout中的路由出口, 也就是说当访问 /acl 的时候展示的是 layout组件 这样它的children里面的二级路由就可以使用 layout/main中的路由出口了
    component: () => import('@/views/layout/index.vue'),
    name: 'Acl',
    meta: {
      title: '权限管理',
      icon: 'Lock'
    },
    redirect: '/acl/user',
    children: [
      {
        path: '/acl/user',
        component: () => import('@/views/acl/user/index.vue'),
        name: 'User',
        meta: {
          title: '用户管理',
          icon: 'User'
        }
      },
      {
        path: '/acl/role',
        component: () => import('@/views/acl/role/index.vue'),
        name: 'Role',
        meta: {
          title: '角色管理',
          icon: 'UserFilled'
        }
      },
      {
        path: '/acl/permission',
        component: () => import('@/views/acl/permission/index.vue'),
        name: 'Permission',
        meta: {
          title: '菜单管理',
          icon: 'Monitor'
        }
      }
    ]
  },
  {
    path: '/product',
    component: () => import('@/views/layout/index.vue'),
    name: 'Product',
    meta: {
      title: '商品管理',
      icon: 'Goods'
    },
    redirect: '/product/trademark',
    children: [
      {
        path: '/product/trademark',
        component: () => import('@/views/product/trademark/index.vue'),
        name: 'Trademark',
        meta: {
          title: '品牌管理',
          icon: 'ShoppingCartFull'
        }
      },
      {
        path: '/product/attr',
        component: () => import('@/views/product/attr/index.vue'),
        name: 'Attr',
        meta: {
          title: '属性管理',
          icon: 'ChromeFilled'
        }
      },
      {
        path: '/product/spu',
        component: () => import('@/views/product/spu/index.vue'),
        name: 'Spu',
        meta: {
          title: 'SPU管理',
          icon: 'Calendar'
        }
      },
      {
        path: '/product/sku',
        component: () => import('@/views/product/sku/index.vue'),
        name: 'Sku',
        meta: {
          title: 'SKU管理',
          icon: 'Orange'
        }
      }
    ]
  }
]

// 任意路由
export const anyRoutes = [
  // 当上面路由都没有匹配上的时候 我们访问的路由
  {
    path: '/:pathMatch(.*)*',
    name: 'Any',
    component: () => import('@/views/404/index.vue'),
    meta: {
      title: '',
      hidden: true,
      icon: ''
    }
  }
]
