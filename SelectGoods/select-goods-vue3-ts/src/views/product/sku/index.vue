<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
//引入请求
import {
  getSkuListApi,
  skuOnSaleApi,
  skuCancelSaleApi,
  getSkuInfoApi,
  removeSkuApi
} from '@/api/product/sku'

//引入ts类型
import type { skuDataType } from '@/api/product/sku/type'

import { ElMessage } from 'element-plus'

defineExpose({
  name: 'Sku'
})

const drawerVisible = ref<boolean>(false)

// 分页器相关数据
const paginationForm = reactive({
  pageNo: 1,
  pageSize: 5,
  total: 0
})

// 表格数据
let skuTabledata = reactive<skuDataType[]>([])

//控制抽屉显示与隐藏的字段
let skuInfo = reactive<skuDataType | Record<string, any>>({})

//组件挂载完毕
onMounted(() => {
  getSkuList()
})

const getSkuList = async () => {
  //当前分页器的页码
  const res = await getSkuListApi(
    paginationForm.pageNo,
    paginationForm.pageSize
  )

  if (res.code === 200) {
    skuTabledata.length = 0
    skuTabledata.push(...res.data.records)
    paginationForm.total = res.data.total
  }
}

//商品的上架与下架的操作
const updateSkuSale = async (row: skuDataType) => {
  // 如果当前商品的isSale == 1,说明当前商品是上架的状态 -> 点击时更新为下架
  if (row.isSale == 1) {
    //下架操作
    await skuCancelSaleApi(row.id as number)
    //提示信息
    ElMessage({ type: 'success', message: '下架成功' })
  } else {
    //下架操作
    await skuOnSaleApi(row.id as number)
    //提示信息
    ElMessage({ type: 'success', message: '上架成功' })
  }

  //发请求获取当前更新完毕的全部已有的SKU
  getSkuList()
}
// //更新已有的SKU
const updateSku = () => {
  ElMessage({ type: 'success', message: '程序员在努力的更新中....' })
}
//查看商品详情按钮的回调
const showSkuInfo = async (row: skuDataType) => {
  //抽屉展示出来
  drawerVisible.value = true
  //获取已有商品详情数据
  let res = await getSkuInfoApi(row.id as number)

  if (res.code === 200) {
    Object.assign(skuInfo, res.data)
  }
}
// //删除某一个已有的商品
const removeSkuHandler = async (skuId: number) => {
  //删除某一个已有商品的情况
  let result: any = await removeSkuApi(skuId)
  if (result.code == 200) {
    //提示信息
    ElMessage({ type: 'success', message: '删除成功' })
    //获取已有全部商品
    getSkuList()
  } else {
    //删除失败
    ElMessage({ type: 'error', message: '系统数据不能删除' })
  }
}

const currentPageChangeHandler = (): void => {
  getSkuList()
}
const pageSizeChangeHandler = (): void => {
  getSkuList()
}
</script>

<template>
  <el-card>
    <el-table border style="margin: 20px 0px" :data="skuTabledata">
      <el-table-column
        label="序号"
        type="index"
        align="center"
        width="80px"
      ></el-table-column>
      <el-table-column
        label="名称"
        show-overflow-tooltip
        width="150px"
        prop="skuName"
      ></el-table-column>
      <el-table-column
        label="描述"
        show-overflow-tooltip
        width="150px"
        prop="skuDesc"
      ></el-table-column>
      <el-table-column label="图片" width="150px">
        <template #default="{ row }">
          <img
            :src="row.skuDefaultImg"
            alt=""
            style="width: 100px; height: 100px"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="重量"
        width="150px"
        prop="weight"
      ></el-table-column>
      <el-table-column
        label="价格"
        width="150px"
        prop="price"
      ></el-table-column>
      <el-table-column label="操作" width="250px" fixed="right">
        <template #default="{ row }">
          <el-button
            type="primary"
            size="small"
            :icon="row.isSale == 1 ? 'Bottom' : 'Top'"
            @click="updateSkuSale(row)"
          ></el-button>
          <el-button
            type="primary"
            size="small"
            icon="Edit"
            @click="updateSku"
          ></el-button>
          <el-button
            type="primary"
            size="small"
            icon="InfoFilled"
            @click="showSkuInfo(row)"
          ></el-button>
          <el-popconfirm
            :title="`你确定要删除${row.skuName}?`"
            width="200px"
            @confirm="removeSkuHandler(row.id)"
          >
            <template #reference>
              <el-button type="primary" size="small" icon="Delete"></el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="paginationForm.pageNo"
      v-model:page-size="paginationForm.pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :background="true"
      layout="prev, pager, next, jumper,->,sizes,total"
      :total="paginationForm.total"
      @current-change="currentPageChangeHandler"
      @size-change="pageSizeChangeHandler"
    />
    <!-- 抽屉组件:展示商品详情 -->
    <el-drawer v-model="drawerVisible" class="sku-detail-info">
      <!-- 标题部分 -->
      <template #header>
        <h4>查看商品的详情</h4>
      </template>
      <template #default>
        <el-row style="margin: 20px 0px">
          <el-col :span="6">名称</el-col>
          <el-col :span="18" style="line-height: 1.5">{{
            skuInfo.skuName
          }}</el-col>
        </el-row>
        <el-row style="margin: 20px 0px">
          <el-col :span="6">描述</el-col>
          <el-col :span="18" style="line-height: 1.5">{{
            skuInfo.skuDesc
          }}</el-col>
        </el-row>
        <el-row style="margin: 20px 0px">
          <el-col :span="6">价格</el-col>
          <el-col :span="18">{{ skuInfo.price }}</el-col>
        </el-row>
        <el-row style="margin: 20px 0px">
          <el-col :span="6">平台属性</el-col>
          <el-col :span="18" style="display: flex">
            <el-tag
              v-for="item in skuInfo.skuAttrValueList"
              :key="item.id"
              style="margin: 0px 5px"
              >{{ item.valueName }}</el-tag
            >
          </el-col>
        </el-row>
        <el-row style="margin: 20px 0px">
          <el-col :span="6">销售属性</el-col>
          <el-col :span="18" style="display: flex">
            <el-tag
              v-for="item in skuInfo.skuSaleAttrValueList"
              :key="item.id"
              style="margin: 0px 5px"
              >{{ item.saleAttrValueName }}</el-tag
            >
          </el-col>
        </el-row>
        <el-row style="margin: 20px 0px">
          <el-col :span="6">商品图片</el-col>
          <el-col :span="18">
            <el-carousel :interval="4000" type="card" height="200px">
              <el-carousel-item
                v-for="item in skuInfo.skuImageList"
                :key="item.id"
              >
                <img
                  :src="item.imgUrl"
                  alt=""
                  style="width: 100%; height: 100%"
                />
              </el-carousel-item>
            </el-carousel>
          </el-col>
        </el-row>
      </template>
    </el-drawer>
  </el-card>
</template>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
