type permissionItemType = {
  id?: number
  // 表示第几层级的数据 0 表示第一级
  pid: number
  // 表示第几层级的数据 1 表示第一级 一共4级 第4级是按钮的权限
  level: number
  // 默认该节点是否是被选中的状态
  select?: boolean
  children?: permissionItemType[]
  // 展示的title
  name: string
  // 权限值?
  code?: string
  toCode?: number
  type?: number
  status?: number
  craeteTime?: string
  updateTime?: string
}

type editFormType = {
  id?: number
  // 权限值
  code: string
  // 几级菜单 1为1级菜单 2为2级菜单
  level: number
  // 名称
  name: string
  // 父id
  pid: number
}

export type { permissionItemType, editFormType }
