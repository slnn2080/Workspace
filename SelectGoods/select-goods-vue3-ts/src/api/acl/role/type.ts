type roleItemType = {
  id?: number
  roleName: string
  remark?: string | null
  createTime?: string
  updateTime?: string
}

type getRoleListResType = {
  records: roleItemType[]
  total: number
  size: number
  current: number
}

type permissionItemType = {
  id?: number
  // 表示第几层级的数据 0 表示第一级
  pid: number
  // 表示第几层级的数据 1 表示第一级 一共4级 第4级是按钮的权限
  level: number
  // 默认该节点是否是被选中的状态
  select: boolean
  children?: permissionItemType[]
  // 展示的title
  name: string
  code?: number
  toCode?: number
  type: number
  status?: number
  craeteTime?: string
  updateTime?: string
}

export type { roleItemType, getRoleListResType, permissionItemType }
