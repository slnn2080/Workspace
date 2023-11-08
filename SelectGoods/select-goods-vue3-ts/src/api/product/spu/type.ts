// 图片列表的类型
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

// spu 对象
type spuItemType = {
  // 新增的时候不需要id, 修改的时候需要
  id?: number
  // spu名
  spuName: string
  // spu描述
  description: string
  // spu品牌id, 表示该spu属于哪个品牌 (如: oppo 下的 s1)
  tmId: number | string
  // 3级分类id: 给哪个三级分类追加一个spu
  category3Id: number | string
  // 修改spu界面需要使用到的 spu销售属性的数组 该接口下为null
  spuSaleAttrList: null | spuSaleAttrItem[]
  // 照片墙: 修改spu界面需要使用到的 spu照片 该接口下为null
  spuImageList: null | spuImageItem[]
  spuPosterList?: null
}

// spu一览表的返回值类型
type spuResType = {
  records: spuItemType[]
  total: number
  size: number
  current: number
  searchCount: boolean
  pages: number
}

// 销售属性值的类型
type spuSaleAttrValue = {
  id?: number
  spuId?: number
  // 销售属性的id: 该属性值归属于那个销售属性
  baseSaleAttrId: number | string
  // 销售属性值的名字
  saleAttrValueName: string
  isChecked?: boolean
  saleAttrName?: string
  createTime?: string
  updateTime?: string
}

// 销售属性列表类型
type spuSaleAttrItem = {
  id?: number
  // 销售属性的id
  baseSaleAttrId: number | string
  // 销售属性名字
  saleAttrName: string
  spuSaleAttrValueList: spuSaleAttrValue[]
  spuId?: number
  createTime?: string
  updateTime?: string
  switchStructure?: boolean
  saleAttrValue?: string
}

// spu下所有销售属性列表
type attrItem = {
  id: number
  name: string
}

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

// 新增 sku
type SkuDataType = {
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
  // 平台属性
  skuAttrValueList?: attrItemType[]
  skuSaleAttrValueList?: saleItemType[]
  skuDefaultImg: string //sku图片地址
}

export type {
  spuSaleAttrValue,
  spuItemType,
  spuResType,
  spuImageItem,
  spuSaleAttrItem,
  attrItem,
  SkuDataType,
  attrItemType,
  saleItemType
}
