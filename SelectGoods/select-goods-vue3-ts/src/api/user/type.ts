// 登录接口: 请求参数的类型
export type loginParamType = {
  username: string
  password: string
}

// 登录接口: 响应结果的类型
type dataType = {
  token?: string
  message?: string
}
export type loginResType = {
  code: number
  data: dataType
}

// 获取用户信息: 响应结果的类型
type userType = {
  userId: number
  avatar: string
  username: string
  password: string
  desc: string
  roles: string[]
  buttons: string[]
  routes: string[]
  token: string
}

export type userInfoType = {
  code: number
  data: {
    checkUser: userType
  }
}
