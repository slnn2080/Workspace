// 品牌管理: 列表中每一条记录的数据类型
type trademarkItem = {
  // 新增数据的时候, 我们不需要id, 已有的数据才会返回id, 所以该字段可选
  id?: number
  tmName: string
  logoUrl: string
  createTime?: string
  upadteTime?: string
}

// 品牌管理: 请求table列表接口方法的返回值类型
type trademarkResType = {
  records: trademarkItem[]
  total: number
  size: number
  current: number
  orders?: [] | null
  optimizeCountSql?: boolean
  hitCount?: boolean
  countId?: number
  maxLimit?: null
  searchCount?: boolean
  // 一共多少页
  pages?: number
}

export type { trademarkItem, trademarkResType }
