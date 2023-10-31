// 通用的返回字段类型: 定义全部接口返回数据都用户的ts类型, 字段data的内容不一样
type commonResType<T> = {
  code: number
  message: string
  data: T
  ok: boolean
}

export type { commonResType }
