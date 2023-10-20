import service from '@/utils/request'
import type { loginParamType, loginResType, userInfoType } from './type'

// 统一管理接口
enum API {
  LOGIN_URL = '/user/login',
  USER_INFO_URL = '/user/info'
}

// 暴露 登录 的接口方法
type loginFnType = (data: loginParamType) => Promise<loginResType>
export const loginApi: loginFnType = (data) => {
  return service.post(API.LOGIN_URL, data)
}

// 暴露 获取用户信息 的接口方法
type getUserInfoFnType = () => Promise<userInfoType>
export const getUserInfoApi: getUserInfoFnType = () => {
  return service.get('/user/info')
}
