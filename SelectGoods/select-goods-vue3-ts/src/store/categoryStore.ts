// 商品分类全局组件的仓库
import { defineStore } from 'pinia'

import {
  getCategory1ListApi,
  getCategory2ListApi,
  getCategory3ListApi
} from '@/api/product/attr'

import type {
  category1ItemResType,
  category2ItemResType,
  category3ItemResType
} from '@/api/product/attr/type.ts'

type stateType = {
  category1Id: string | number
  category2Id: string | number
  category3Id: string | number
  category1List: category1ItemResType[]
  category2List: category2ItemResType[]
  category3List: category3ItemResType[]
}
const useCategoryStore = defineStore('category', {
  state: (): stateType => {
    return {
      category1List: [],
      category2List: [],
      category3List: [],
      category1Id: '',
      category2Id: '',
      category3Id: ''
    }
  },
  actions: {
    async getCategory1List() {
      const res = await getCategory1ListApi()
      if (res.code === 200) {
        this.category1List = res.data
      }
    },
    async getCategory2List() {
      const res = await getCategory2ListApi(+this.category1Id)
      if (res.code === 200) {
        this.category2List = res.data
      }
    },
    async getCategory3List() {
      const res = await getCategory3ListApi(+this.category2Id)
      if (res.code === 200) {
        this.category3List = res.data
      }
    }
  },
  getters: {}
})

export default useCategoryStore
