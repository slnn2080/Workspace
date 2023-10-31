import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'
import type { trademarkResType, trademarkItem } from './type'

// 品牌管理模块 接口地址
enum API {
  // 获取 列表 数据 {page}/{limit}
  GET_TRADEMARK_URL = '/admin/product/baseTrademark',
  ADD_TRADEMARK_URL = '/admin/product/baseTrademark/save',
  UPDATE_TRADEMARK_URL = '/admin/product/baseTrademark/update',
  // 需要携带 /{id}
  DELETE_TRADEMARK_URL = '/admin/product/baseTrademark/remove'
}

// 获取 列表 数据的api
/*
  参数: 
    page: 获取第几页数据, 相当于 pageNo, 默认是第一页
    limit: 要拿几条数据, 相当于 pageSize
*/
type getTrademarkListType = (
  page: number,
  limit: number
) => Promise<commonResType<trademarkResType>>
export const getTrademarkList: getTrademarkListType = (page = 1, limit) => {
  return service.get(`${API.GET_TRADEMARK_URL}/${page}/${limit}`)
}

// 修改品牌 和 新增品牌 的接口方法
// 因为 这两个接口携带的参数大致相同 区别仅是是否多携带id 所以这两接口封装到一个方法中
type saveOrUpdateTrademarkType = (
  data: trademarkItem
) => Promise<commonResType<null>>
export const saveOrUpdateTrademark: saveOrUpdateTrademarkType = (data) => {
  // 判断是修改 还是 新增
  if (data.id) {
    // 修改
    return service.put(API.UPDATE_TRADEMARK_URL, data)
  } else {
    // 新增
    return service.post(API.ADD_TRADEMARK_URL, data)
  }
}

// 删除品牌
type deleteTrademarkByIdType = (id: number) => Promise<commonResType<null>>
export const deleteTrademarkById: deleteTrademarkByIdType = (id) => {
  return service.delete(`${API.DELETE_TRADEMARK_URL}/${id}`)
}
