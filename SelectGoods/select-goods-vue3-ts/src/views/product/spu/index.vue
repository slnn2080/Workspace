<script setup lang="ts">
import { watch, reactive, ref } from 'vue'
import useCategoryStore from '@/store/categoryStore.ts'

import { getSpuListApi } from '@/api/product/spu'

import type { spuItemType } from '@/api/product/spu/type.ts'

import SearchCategory from '@/components/SearchCategory/index.vue'
import SpuForm from './components/SpuForm.vue'
import SkuForm from './components/SkuForm.vue'

defineOptions({
  name: 'Spu'
})

const categoryStore = useCategoryStore()

// 获取 spuForm 子组件实例
const spuFormRef = ref<InstanceType<typeof SpuForm>>()

// 获取 spuForm 子组件实例
const skuFormRef = ref()

// SPU管理 界面切换的控制变量
enum SCENE {
  TABLE,
  SPU,
  SKU
}
const switchStructure = ref<SCENE>(SCENE.TABLE)

const tableHeaders = reactive([
  {
    prop: 'spuName',
    label: 'SPU名称'
  },
  {
    prop: 'description',
    label: 'SPU描述'
  }
])
// 表格数据
const tableData = reactive<spuItemType[]>([])

const paginationForm = reactive({
  pageNo: 1,
  pageSize: 5,
  total: 0
})

// ----- methos -----
const getTableList = async () => {
  const res = await getSpuListApi(
    +paginationForm.pageNo,
    +paginationForm.pageSize,
    +categoryStore.category3Id
  )
  if (res.code === 200) {
    tableData.length = 0
    tableData.push(...res.data.records)
    paginationForm.total = res.data.total
  }
}

const changePageSizeHandler = () => {
  // 如果没有 category3Id 的话 不要发起请求
  if (!categoryStore.category3Id) return
  // 每页展示多少条数据 触发回调的话, 让它从第一页显示
  paginationForm.pageNo = 1
  getTableList()
}

// 添加 SPU 回调
const addSpuHandler = () => {
  switchStructure.value = SCENE.SPU
  spuFormRef.value?.initSpuFormData(+categoryStore.category3Id)
}
// 修改 SPU 回调
const updateSpuHandler = (row: spuItemType): void => {
  /*
  row: {
    id?: number | undefined;
    spuName: string;
    description: string;
    category3Id: number | string;
    tmId: number;
    spuSaleAttrList: null;
    spuImageList: null;
    spuPosterList: null;
  }
  */
  switchStructure.value = SCENE.SPU

  // 通过子组件实例调用其 getSpuFormData 方法, 并将row传递过去
  spuFormRef.value?.getSpuFormData(row)
}
// 添加 SKU 回调
const addSkuHandler = () => {
  switchStructure.value = SCENE.SKU
}
// 监听自定义事件
const updateStructure = (structure: SCENE) => {
  switchStructure.value = structure
  // 切换场景后 再次刷新列表
  getTableList()
}

// ----- watch -----
// 监听 store 中 三级分类id 的变化
watch(
  () => categoryStore.category3Id,
  (n) => {
    // 1. 当 3级分类id 有变化之后 我们要先清空表格数据
    tableData.length = 0
    // 2. 确保 3级分类id 有了之后我们再发送请求
    if (n) {
      getTableList()
    }
  }
)
</script>

<template>
  <div class="spu-container">
    <!-- search are -->
    <SearchCategory :switchTableStructure="switchStructure === SCENE.TABLE" />
    <el-card class="spu-main">
      <!-- 表格区域: 切换 -->
      <div v-if="switchStructure === SCENE.TABLE" class="spu-main__list">
        <el-button
          type="primary"
          icon="Plus"
          :disabled="categoryStore.category3Id ? false : true"
          @click="addSpuHandler"
        >
          添加SPU
        </el-button>
        <el-table border :data="tableData">
          <el-table-column
            type="index"
            width="80"
            align="center"
            label="序号"
          />
          <el-table-column
            v-for="(item, index) in tableHeaders"
            :key="index"
            :prop="item.prop"
            :label="item.label"
          >
          </el-table-column>
          <el-table-column label="操作">
            <template #default="{ row }">
              <el-button
                type="primary"
                size="small"
                circle
                icon="Plus"
                title="添加SKU"
                @click="addSkuHandler"
              />
              <el-button
                type="primary"
                size="small"
                circle
                icon="Edit"
                title="修改SPU"
                @click="updateSpuHandler(row)"
              />
              <el-button
                type="primary"
                size="small"
                circle
                icon="View"
                title="查看SPU"
              />
              <el-button
                type="primary"
                size="small"
                circle
                icon="Delete"
                title="删除SPU"
              />
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页器 -->
        <div class="spu-main__pagination">
          <el-pagination
            v-model:current-page="paginationForm.pageNo"
            v-model:page-size="paginationForm.pageSize"
            :page-sizes="[3, 5, 7, 9]"
            :background="true"
            :small="true"
            layout="prev, pager, next, jumper, -> , sizes, total"
            :total="paginationForm.total"
            @current-change="getTableList"
            @size-change="changePageSizeHandler"
          />
        </div>
      </div>
      <!-- 添加 / 修改 表单区域: 切换 -->
      <SpuForm
        ref="spuFormRef"
        v-show="switchStructure === SCENE.SPU"
        @update:switchStructure="updateStructure"
      />
      <!-- 添加 sku 表单区域: 切换 -->
      <SkuForm ref="skuFormRef" v-show="switchStructure === SCENE.SKU" />
    </el-card>
  </div>
</template>

<style scoped lang="scss">
.spu-container {
  .spu-main {
    margin-top: 20px;

    &__list .el-table {
      margin-top: 10px;
    }
    &__pagination {
      margin-top: 10px;
    }
  }
}
</style>
