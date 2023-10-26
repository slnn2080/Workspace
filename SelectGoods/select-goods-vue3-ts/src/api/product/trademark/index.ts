import service from '@/utils/request'
import { commonTrademarkResType, trademarkResType } from './type'

// 品牌管理模块 接口地址
enum API {
  // 获取 列表 数据 {page}/{limit}
  TRADEMARK_URL = '/admin/product/baseTrademark/'
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
) => Promise<commonTrademarkResType<trademarkResType>>
export const getTrademarkList: getTrademarkListType = (page = 1, limit) => {
  return service.get(`${API.TRADEMARK_URL}/${page}/${limit}`)
}
