type loginParamType = {
  username: string
  password: string
}

// 获取用户信息 返回值的类型
type userInfoType = {
  // 菜单权限的数组和路由对象.name的值是一致的
  routes: string[]
  // 按钮权限
  buttons: string[]
  // 角色
  roles: string[]
  name: string
  avatar: string
}

export type { loginParamType, userInfoType }
