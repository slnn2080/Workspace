import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'
import type { trademarkItem } from '@/api/product/trademark/type.ts'
import {
  spuResType,
  spuImageItem,
  spuSaleAttrItem,
  attrItem,
  spuItemType,
  SkuDataType
} from './type'

enum API {
  // /{page}/{limit}?category3Id=xx'
  GET_TABLEDATA = '/admin/product',
  GET_ALLTRADEMARK = '/admin/product/baseTrademark/getTrademarkList',
  // /{spuId}
  GET_SPU_IMAGE_LIST = '/admin/product/spuImageList',
  // 获取某一个spu下已选择的销售属性接口地址 /{spuId}
  GET_SALEATTR_SELECTED_LIST = '/admin/product/spuSaleAttrList',
  // 获取spu全部的销售属性接口地址 (颜色 版本 尺码)
  GET_SALEATTR_LIST = '/admin/product/baseSaleAttrList',
  // 添加 spu 接口地址
  ADD_SPU = '/admin/product/saveSpuInfo',
  // 修改 spu 接口地址
  UPDATE_SPU = '/admin/product/updateSpuInfo',
  // 给 spu 添加一个 sku 接口
  ADD_SKU = '/admin/product/saveSkuInfo',
  // 展示 sku 列表的接口 /{spuId}
  GET_SKU_LIST = '/admin/product/findBySpuId',
  // 删除 spu 列表的接口 /{spuId}
  DELETE_SPU = '/admin/product/deleteSpu'
}

// 组件初挂载 展示列表数据的接口
type getSpuListApiType = (
  pageNo: number,
  pageSize: number,
  category3Id: number
) => Promise<commonResType<spuResType>>
export const getSpuListApi: getSpuListApiType = (
  pageNo,
  pageSize,
  category3Id
) => {
  return service.get(
    `${API.GET_TABLEDATA}/${pageNo}/${pageSize}?category3Id=${category3Id}`
  )
}

// 获取所有spu品牌的数据
type getSpuTrademarkListApiType = () => Promise<commonResType<trademarkItem[]>>
export const getSpuTrademarkListApi: getSpuTrademarkListApiType = () => {
  return service.get(API.GET_ALLTRADEMARK)
}

// 获取某个spu下的全部的售卖商品的图片的数据
type getImageListBySpuIdType = (
  spuId: number
) => Promise<commonResType<spuImageItem[]>>
export const getImageListBySpuIdApi: getImageListBySpuIdType = (spuId) => {
  return service.get(`${API.GET_SPU_IMAGE_LIST}/${spuId}`)
}

// 获取某一个已有spu已选择的销售属性
type getSaleAttrListBySpuIdType = (
  spuId: number
) => Promise<commonResType<spuSaleAttrItem[]>>
export const getSaleAttrSelectedListBySpuIdApi: getSaleAttrListBySpuIdType = (
  spuId
) => {
  return service.get(`${API.GET_SALEATTR_SELECTED_LIST}/${spuId}`)
}

type getSaleAttrListApiType = () => Promise<commonResType<attrItem[]>>
export const getSaleAttrListApi: getSaleAttrListApiType = () => {
  return service.get(`${API.GET_SALEATTR_LIST}`)
}

// 添加 / 更新 spu 接口方法: 虽然请求地址不一样 但因为携带参数一样(是否有id) 封装到一个方法中
// spuData: 新增 或 已有的spu对象
type saveOrUpdateSpuApiType = (
  spuData: spuItemType
) => Promise<commonResType<null>>
export const saveOrUpdateSpuApi: saveOrUpdateSpuApiType = (spuData) => {
  if (spuData.id) {
    // 更新操作
    return service.post(API.UPDATE_SPU, spuData)
  } else {
    // 添加操作
    return service.post(API.ADD_SPU, spuData)
  }
}

// 给 spu 添加一个 sku 接口
type saveSkuApiType = (skuData: SkuDataType) => Promise<commonResType<null>>
export const saveSkuApi: saveSkuApiType = (skuData) => {
  return service.post(API.ADD_SKU, skuData)
}

// 获取 skuList 的接口
type getSkuListApiType = (
  spuId: number
) => Promise<commonResType<SkuDataType[]>>
export const getSkuListApi: getSkuListApiType = (spuId) => {
  return service.get(`${API.GET_SKU_LIST}/${spuId}`)
}

// 删除 skuList 的接口
type deleteSpuApiType = (spuId: number) => Promise<commonResType<null>>
export const deleteSpuApi: deleteSpuApiType = (spuId) => {
  return service.delete(`${API.DELETE_SPU}/${spuId}`)
}
