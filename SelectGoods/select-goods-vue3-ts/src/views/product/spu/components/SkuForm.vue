<script setup lang="ts">
import { ref, reactive } from 'vue'

//获取所有的 平台属性 api
import { getList } from '@/api/product/attr'
import {
  getImageListBySpuIdApi,
  getSaleAttrSelectedListBySpuIdApi,
  saveSkuApi
} from '@/api/product/spu'

import type { listItemType } from '@/api/product/attr/type.ts'
import type {
  spuItemType,
  spuSaleAttrItem,
  spuImageItem,
  SkuDataType
  // attrItemType,
  // saleItemType
} from '@/api/product/spu/type'

import { ElMessage, ElTable } from 'element-plus'

defineOptions({
  name: 'SkuForm'
})

enum SCENE {
  TABLE,
  SPU,
  SKU
}
type emitsType = {
  (e: 'update:switchStructure', scene: SCENE): void
}
const emit = defineEmits<emitsType>()

// ----- variable -----
const skuForm = reactive<SkuDataType>({
  //父组件传递过来的数据
  category3Id: '',
  spuId: '',
  tmId: '',
  // v-model收集
  //sku名字
  skuName: '',
  //sku价格
  price: '',
  //sku重量
  weight: '',
  //sku的描述
  skuDesc: '',
  skuAttrValueList: [],
  skuSaleAttrValueList: [],
  skuDefaultImg: ''
})

//平台属性
type attrArrType = listItemType & { attrIdAndValueId?: string }
let attrArr = reactive<attrArrType[]>([])
//销售属性 saleIdAndValueId
type saleArrType = spuSaleAttrItem & { saleIdAndValueId?: string }
let saleArr = reactive<saleArrType[]>([])
//照片的数据
let imgArr = reactive<spuImageItem[]>([])

//获取table组件实例
let tableRef = ref<InstanceType<typeof ElTable>>()

//当前子组件的方法对外暴露
const getSkuFormData = async (
  category1Id: number,
  category2Id: number,
  spuData: spuItemType
) => {
  //收集数据
  skuForm.category3Id = spuData.category3Id
  skuForm.spuId = spuData.id as number
  skuForm.tmId = spuData.tmId as number

  // 获取 平台属性 数据
  const { data: attrListRes } = await getList(
    category1Id,
    category2Id,
    +spuData.category3Id
  )

  // 获取 销售属性 数据
  let { data: saleAttrRes } = await getSaleAttrSelectedListBySpuIdApi(
    spuData.id as number
  )

  //获取照片墙的数据
  let { data: imageListRes } = await getImageListBySpuIdApi(
    spuData.id as number
  )

  // 保存: 平台属性
  attrArr.length = 0
  attrArr.push(...attrListRes)
  // 保存: 销售属性
  saleArr.length = 0
  saleArr.push(...saleAttrRes)
  // 保存: 图片
  imgArr.length = 0
  imgArr.push(...imageListRes)
}

// ----- methods -----
const resetForm = (): void => {
  Object.assign(skuForm, {
    //父组件传递过来的数据
    category3Id: '',
    spuId: '',
    tmId: '',
    // v-model收集
    //sku名字
    skuName: '',
    //sku价格
    price: '',
    //sku重量
    weight: '',
    //sku的描述
    skuDesc: '',
    skuAttrValueList: [],
    skuSaleAttrValueList: [],
    skuDefaultImg: ''
  })
}
//取消按钮的回调
const cancelHandler = () => {
  emit('update:switchStructure', SCENE.TABLE)
  resetForm()
}

//设置默认图片的方法回调
const handler = (row: spuImageItem) => {
  // 排他思想 方式1: 点击的时候,全部图片的的复选框不勾选
  // imgArr.forEach((item: spuImageItem) => {
  //   tableRef.value?.toggleRowSelection(item, false)
  // })
  // 排他思想 方式2:
  tableRef.value?.clearSelection()
  //选中的图片才勾选
  tableRef.value?.toggleRowSelection(row, true)
  //收集图片地址
  skuForm.skuDefaultImg = row.imgUrl as string
}

//保存按钮的方法
const saveHandler = async () => {
  //整理参数
  //平台属性
  skuForm.skuAttrValueList = attrArr.reduce((prev: any, next: any) => {
    if (next.attrIdAndValueId) {
      let [attrId, valueId] = next.attrIdAndValueId.split(':')
      prev.push({
        attrId,
        valueId
      })
    }
    return prev
  }, [])
  //销售属性
  skuForm.skuSaleAttrValueList = saleArr.reduce((prev: any, next: any) => {
    if (next.saleIdAndValueId) {
      let [saleAttrId, saleAttrValueId] = next.saleIdAndValueId.split(':')
      prev.push({
        saleAttrId,
        saleAttrValueId
      })
    }
    return prev
  }, [])
  //添加SKU的请求
  let result: any = await saveSkuApi(skuForm)
  if (result.code == 200) {
    ElMessage({
      type: 'success',
      message: '添加SKU成功'
    })
    //通知父组件切换场景为零
    emit('update:switchStructure', SCENE.SKU)
    resetForm()
  } else {
    ElMessage({
      type: 'error',
      message: '添加SKU失败'
    })
  }
}

defineExpose({
  getSkuFormData
})
</script>

<template>
  <el-form label-width="100px">
    <el-form-item label="SKU名称">
      <el-input placeholder="SKU名称" v-model="skuForm.skuName"></el-input>
    </el-form-item>
    <el-form-item label="价格(元)">
      <el-input
        placeholder="价格(元)"
        type="number"
        v-model="skuForm.price"
      ></el-input>
    </el-form-item>
    <el-form-item label="重量(g)">
      <el-input
        placeholder="重量(g)"
        type="number"
        v-model="skuForm.weight"
      ></el-input>
    </el-form-item>
    <el-form-item label="SKU描述">
      <el-input
        placeholder="SKU描述"
        type="textarea"
        v-model="skuForm.skuDesc"
      ></el-input>
    </el-form-item>
    <el-form-item label="平台属性">
      <!-- 双重表单 -->
      <el-form :inline="true">
        <el-form-item
          v-for="item in attrArr"
          :key="item.id"
          :label="item.attrName"
        >
          <el-select v-model="item.attrIdAndValueId">
            <el-option
              v-for="attrValue in item.attrValueList"
              :key="attrValue.id"
              :label="attrValue.valueName"
              :value="`${item.id}:${attrValue.id}`"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-form-item>
    <el-form-item label="销售属性">
      <el-form :inline="true">
        <el-form-item
          v-for="item in saleArr"
          :key="item.id"
          :label="item.saleAttrName"
        >
          <el-select v-model="item.saleIdAndValueId">
            <el-option
              v-for="saleAttrValue in item.spuSaleAttrValueList"
              :key="saleAttrValue.id"
              :value="`${item.id}:${saleAttrValue.id}`"
              :label="saleAttrValue.saleAttrValueName"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
    </el-form-item>
    <el-form-item label="图片名称">
      <el-table border :data="imgArr" ref="tableRef">
        <el-table-column
          type="selection"
          width="80px"
          align="center"
        ></el-table-column>
        <el-table-column label="图片">
          <template #default="{ row }">
            <img :src="row.imgUrl" alt="" style="width: 100px; height: 100px" />
          </template>
        </el-table-column>
        <el-table-column label="名称" prop="imgName"></el-table-column>
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="handler(row)"
              >设置默认</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="default" @click="saveHandler"
        >保存</el-button
      >
      <el-button type="primary" size="default" @click="cancelHandler"
        >取消</el-button
      >
    </el-form-item>
  </el-form>
</template>

<style scoped lang="scss"></style>
