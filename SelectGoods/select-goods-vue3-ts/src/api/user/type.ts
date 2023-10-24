export type loginParamType = {
  username: string
  password: string
}

// 通用的返回字段类型: 定义全部接口返回数据都用户的ts类型, 字段data的内容不一样
export type commonResType<T> = {
  code: number
  message: string
  data: T
  ok: boolean
}

// 获取用户信息 返回值的类型
export type userInfoType = {
  // 菜单权限的数组和路由对象.name的值是一致的
  routes: string[]
  // 按钮权限
  buttons: string[]
  // 角色
  roles: string[]
  name: string
  avatar: string
}
