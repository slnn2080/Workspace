// sku: 平台属性
type attrItemType = {
  attrId: number | string //平台属性的ID
  valueId: number | string //属性值的ID
}
// sku: 销售属性
type saleItemType = {
  saleAttrId: number | string //属性ID
  saleAttrValueId: number | string //属性值的ID
}

type spuImageItem = {
  // 已有的有id, 新增的没有id
  id?: number
  imgName?: string
  imgUrl?: string
  spuId?: number
  // 和 imgName imgUrl 一样 但是upload组件中需要该字段
  name?: string
  url?: string
}

// 新增 sku
type skuDataType = {
  id?: number | string
  // 三级分类的ID
  category3Id: string | number
  // 已有spuid, 往哪个已有的SPU上追加sku
  spuId: string | number
  //SPU品牌的ID
  tmId: string | number
  //sku名字
  skuName: string
  //sku价格
  price: string | number
  //sku重量
  weight: string | number
  //sku的描述
  skuDesc: string
  isSale?: string | number
  // 平台属性
  skuAttrValueList?: attrItemType[]
  skuSaleAttrValueList?: saleItemType[]
  skuDefaultImg: string //sku图片地址,
  skuImageList?: spuImageItem[]
}

type skuResType = {
  records: skuDataType[]
  total: number
  size: number
  current: number
  searchCount: boolean
  pages: number
}

export type { skuDataType, skuResType }
