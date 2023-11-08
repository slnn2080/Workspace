import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'
import type {
  category1ItemResType,
  category2ItemResType,
  category3ItemResType,
  listItemType
} from './type'

enum API {
  GET_CATEGORY1 = '/admin/product/getCategory1',
  // /{category1Id}
  GET_CATEGORY2 = '/admin/product/getCategory2',
  // /{category2Id}
  GET_CATEGORY3 = '/admin/product/getCategory3',
  // 获取table数据 /{category1Id}/{category2Id}/{category3Id}
  GET_TABLEDATA = '/admin/product/attrInfoList',
  ADD_UPDATE_ATTR_URL = '/admin/product/saveAttrInfo',
  DELETE_ATTR_URL = '/admin/product/deleteAttr'
}

export const getCategory1ListApi = (): Promise<
  commonResType<category1ItemResType[]>
> => {
  return service.get(API.GET_CATEGORY1)
}
export const getCategory2ListApi = (
  category2Id: number
): Promise<commonResType<category2ItemResType[]>> => {
  return service.get(`${API.GET_CATEGORY2}/${category2Id}`)
}
export const getCategory3ListApi = (
  category3Id: number
): Promise<commonResType<category3ItemResType[]>> => {
  return service.get(`${API.GET_CATEGORY3}/${category3Id}`)
}

// 获取 平台属性 table数据
type getListType = (
  category1Id: number,
  category2Id: number,
  category3Id: number
) => Promise<commonResType<listItemType[]>>
export const getList: getListType = (category1Id, category2Id, category3Id) => {
  return service.get(
    `${API.GET_TABLEDATA}/${category1Id}/${category2Id}/${category3Id}`
  )
}

// 添加 / 修改 已有属性的接口
type saveOrUpdateAttrType = (data: listItemType) => Promise<commonResType<null>>
export const saveOrUpdateAttr: saveOrUpdateAttrType = (data) => {
  // 后台会根据我们传递的参数是否有id来判断该次请求是添加还是修改
  return service.post(API.ADD_UPDATE_ATTR_URL, data)
}

// 删除 已有属性的接口
export const deleteAttrApi = (
  attrId: number | string
): Promise<commonResType<null>> => {
  return service.delete(`${API.DELETE_ATTR_URL}/${attrId}`)
}
