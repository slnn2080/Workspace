// 用户管理: 用户信息
type userInfoType = {
  id?: number
  createTime?: string
  updateTime?: string
  username: string
  password: string
  // 昵称
  name: string
  phone?: string
  roleName?: string
}

type getUserinfoResType = {
  records: userInfoType[]
  total: number
  size: number
  current: number
}

// 角色信息
type roleItemType = {
  id?: number
  roleName: string
  remark?: string | null
  createTime?: string
  updateTime?: string
}

type getUserRolesResType = {
  assignRoles: roleItemType[]
  allRolesList: roleItemType[]
}

// 分配角色 请求参数
type assignRoleParamsType = {
  roleIdList: number[]
  userId: number
}

export type {
  userInfoType,
  getUserinfoResType,
  roleItemType,
  getUserRolesResType,
  assignRoleParamsType
}
