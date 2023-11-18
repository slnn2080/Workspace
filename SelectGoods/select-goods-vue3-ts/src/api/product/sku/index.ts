// Sku页面 相关接口
import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'

import { skuDataType, skuResType } from './type'

enum API {
  // /{page}/{limit}
  GET_SKULIST = '/admin/product/list',
  // 上架 /{skuId}
  GET_ONSALE = '/admin/product/onSale',
  // 下架 /{skuId}
  GET_CANCELSALE = '/admin/product/cancelSale',
  // 获取 sku 信息接口 /{skuId},
  GET_SKUINFO = '/admin/product/getSkuInfo',
  // 删除 sku /{skuId},
  DELETE_SKU = '/admin/product/deleteSku'
}

// 获取已有的sku商品数据
type getSkuListApiType = (
  pageNo: number,
  pageSize: number
) => Promise<commonResType<skuResType>>
export const getSkuListApi: getSkuListApiType = (pageNo, pageSize) => {
  return service.get(`${API.GET_SKULIST}/${pageNo}/${pageSize}`)
}

// 上架
type skuOnSaleApiType = (skuId: number) => Promise<commonResType<null>>
export const skuOnSaleApi: skuOnSaleApiType = (skuId) => {
  return service.get(`${API.GET_ONSALE}/${skuId}`)
}
// 下架
type skuCancelSaleApiType = (skuId: number) => Promise<commonResType<null>>
export const skuCancelSaleApi: skuCancelSaleApiType = (skuId) => {
  return service.get(`${API.GET_CANCELSALE}/${skuId}`)
}

type getSkuInfoApiType = (skuId: number) => Promise<commonResType<skuDataType>>
export const getSkuInfoApi: getSkuInfoApiType = (skuId) => {
  return service.get(`${API.GET_SKUINFO}/${skuId}`)
}

type removeSkuApiType = (skuId: number) => Promise<commonResType<null>>
export const removeSkuApi: removeSkuApiType = (skuId) => {
  return service.delete(`${API.DELETE_SKU}/${skuId}`)
}
