// 一级分类下拉列表
type category1ItemResType = {
  id: number | string
  name: string
}

// 二级分类下拉列表
type category2ItemResType = category1ItemResType & {
  category1Id: number | string
}

type category3ItemResType = category1ItemResType & {
  category2Id: number | string
}

// 添加 和 修改 属性的请求参数类型
// 属性值类型: 表格数据 attrValueList 中的一个item的类型
export type attrValueItemType = {
  id?: number | string
  valueName: string
  attrId?: number | string
  isEdited?: boolean
}

// 表格数组中一个item的类型
type listItemType = {
  id?: number | string
  attrName: string
  categoryId?: number | string
  categoryLevel: number | string
  attrValueList: attrValueItemType[]
}

export type {
  category1ItemResType,
  category2ItemResType,
  category3ItemResType,
  listItemType
}
