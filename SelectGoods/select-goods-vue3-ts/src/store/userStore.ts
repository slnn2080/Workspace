import { defineStore } from 'pinia'

// 登录api
import { loginApi, getUserInfoApi, logoutApi } from '@/api/user'
import type { loginParamType } from '@/api/user/type'

// 引入操作本地存储的方法
import { GET_TOKEN, SET_TOKEN, REMOVE_TOKEN } from '@/utils/util'

import { constantRoutes, asyncRoutes, anyRoutes } from '@/router/routes'
import router from '@/router'

// esline-disable-next-line
import cloneDeep from 'lodash/cloneDeep'

// 用户过滤当前用户需要展示的异步路由
const filterAsyncRoutes = (asyncRoutes: any, routes: any): any[] => {
  return asyncRoutes.filter((item: any) => {
    if (routes.includes(item.name)) {
      if (item.children && item.children.length > 0) {
        // item.children 是一级路由中对应的子路由列表, 我们将过滤出来的子路由交给一级路由, 这里需要深拷贝
        item.children = filterAsyncRoutes(item.children, routes)
      }
      return true
    }
  })
}

export type stateType = {
  token: string | null
  username: string
  avatar: string
  // 仓库存储生成菜单需要的数组 (路由)
  menuRoutes: any[]
}
const useUserStore = defineStore('login', {
  state: (): stateType => {
    return {
      // 当刷新页面的时候 从本地存储中获取token
      token: GET_TOKEN(),
      username: '',
      avatar: '',
      menuRoutes: constantRoutes
    }
  },
  actions: {
    // 处理用户登录的方法,
    async login(loginForm: loginParamType) {
      const res = await loginApi(loginForm)
      // 登录成功: 200 - token 存储token
      // 登录失败: 201 - message
      if (res.code === 200) {
        // res.data.token as string 保证 res.data.token 必须是字符串的时候赋值给this.token
        this.token = res.data as string
        // 将token持久化到LocalStorage 保证 res.data.token 必须是字符串的时候我们再存
        // localStorage.setItem('token', res.data.token as string)
        SET_TOKEN(res.data as string)

        // async 函数的返回值就是一个promise 我们return ok 让页面获取返回值 判断登录状态
        return 'ok'
      } else {
        return Promise.reject(new Error(res.data))
      }
    },

    // 获取用户信息的方法
    async getUserInfo() {
      // 获取用户的信息存储到store中
      const res = await getUserInfoApi()
      // 如果获取用户信息成功 存储信息
      if (res.code === 200) {
        this.username = res.data.name
        this.avatar = res.data.avatar

        // 根据后台返回的 权限数组 来过滤前台维护的异步路由表从而拿到该用户可以访问的路由列表
        const userAsyncRoutes = filterAsyncRoutes(
          cloneDeep(asyncRoutes),
          res.data.routes
        )
        // 整理左侧菜单栏需要的数据
        this.menuRoutes = [...constantRoutes, ...userAsyncRoutes, ...anyRoutes]

        // 往路由器中动态追加 异步路由 和 任意路由
        ;[...userAsyncRoutes, ...anyRoutes].forEach((route: any) => {
          router.addRoute(route)
        })

        // async 中 return 一个值 则 getUserInfo 方法 会返回一个成功的promise
        return 'ok'
      } else {
        // async 中 return reject 返回一个失败的promise, 该方法用户 让调用者知道请求成功还是失败
        return Promise.reject(new Error(res.message))
      }
    },

    // 处理用户退出登录的方法
    async userLogout() {
      // 1. 目前没有 mock 退出登录的接口: 该接口的作用通知服务器 用户本次token失效
      const res = await logoutApi()
      if (res.code === 200) {
        // 2. 清除用户数据
        this.token = ''
        this.username = ''
        this.avatar = ''
        // 3. 清除本地存储中的token
        REMOVE_TOKEN()

        // 返回成功的promise, 组件需要判断退出登录是成功还是失败
        return 'ok'
      } else {
        return Promise.reject(new Error(res.message))
      }
    }
  },
  getters: {}
})

export default useUserStore
