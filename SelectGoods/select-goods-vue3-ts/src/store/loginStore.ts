import { defineStore } from 'pinia'

// 登录api
import { loginApi } from '@/api/user'
import type { loginParamType } from '@/api/user/type'

// 引入操作本地存储的方法
import { GET_TOKEN, SET_TOKEN } from '@/utils/util'

type stateType = {
  token: string | null
}
const useLoginStore = defineStore('login', {
  state: (): stateType => {
    return {
      // 当刷新页面的时候 从本地存储中获取token
      token: GET_TOKEN()
    }
  },
  actions: {
    // 处理用户登录的方法
    async login(loginForm: loginParamType) {
      const res = await loginApi(loginForm)
      // 登录成功: 200 - token 存储token
      // 登录失败: 201 - message
      if (res.code === 200) {
        // res.data.token as string 保证 res.data.token 必须是字符串的时候赋值给this.token
        this.token = res.data.token as string
        // 将token持久化到LocalStorage 保证 res.data.token 必须是字符串的时候我们再存
        // localStorage.setItem('token', res.data.token as string)
        SET_TOKEN(res.data.token as string)
        // async 函数的返回值就是一个promise 我们return ok 让页面获取返回值 判断登录状态
        return 'ok'
      } else {
        return Promise.reject(new Error(res.data.message))
      }
    }
  },
  getters: {}
})

export default useLoginStore
