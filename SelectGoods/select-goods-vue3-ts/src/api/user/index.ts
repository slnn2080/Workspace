import service from '@/utils/request'
import type { loginParamType, commonResType, userInfoType } from './type'

// 统一管理接口
enum API {
  LOGIN_URL = '/admin/acl/index/login',
  LOGOUT_URL = '/admin/acl/index/logout',
  USER_INFO_URL = '/admin/acl/index/info',
  MENU = '/admin/acl/index/menu'
}

// 登录:
type loginFnType = (data: loginParamType) => Promise<commonResType<string>>
export const loginApi: loginFnType = (data) => {
  return service.post(API.LOGIN_URL, data)
}

// 退出登录: 没有参数, 但是需要携带token
type logoutResType = () => Promise<commonResType<null>>
export const logoutApi: logoutResType = () => {
  return service.post(API.LOGOUT_URL)
}

// 获取用户信息:
type getUserInfoFnType = () => Promise<commonResType<userInfoType>>
export const getUserInfoApi: getUserInfoFnType = () => {
  return service.get(API.USER_INFO_URL)
}
