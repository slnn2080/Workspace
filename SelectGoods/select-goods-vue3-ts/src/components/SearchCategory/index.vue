<script setup lang="ts">
import { onMounted } from 'vue'
import useCategoryStore from '@/store/categoryStore.ts'

defineOptions({
  name: 'SearchCategory'
})

defineProps(['switchTableStructure'])

const categoryStore = useCategoryStore()

// 当组件挂载完毕后 通知 store 发起请求 获取下拉菜单的数据
onMounted(() => {
  getCategory1List()
})

// 通知仓库获取1级分类数据的方法 同时 el-select v-model 的值 双向绑定到仓库中
const getCategory1List = () => {
  categoryStore.getCategory1List()
}

// 处理下拉菜单选择回调
const handleCategory1 = () => {
  // 当1级分类的值发生变化的时候, 清空下级分类的数据
  categoryStore.category2Id = ''
  categoryStore.category2List = []
  categoryStore.category3Id = ''
  categoryStore.category3List = []

  // 当用户选择了 1级分类 的时候, 我们通知 store 请求2级分类的数据
  categoryStore.getCategory2List()
}
const handleCategory2 = () => {
  categoryStore.category3Id = ''
  categoryStore.category3List = []
  // 当用户选择了 2级分类 的时候, 我们通知 store 请求3级分类的数据
  categoryStore.getCategory3List()
}
const handleCategory3 = () => {
  // ...
}
</script>

<template>
  <el-card class="attr-search">
    <el-form :inline="true">
      <el-form-item label="一级分类">
        <el-select
          v-model="categoryStore.category1Id"
          :disabled="!switchTableStructure"
          :popper-options="{
            modifiers: [{ name: 'computeStyles', options: { adaptive: false } }]
          }"
          @change="handleCategory1"
        >
          <el-option
            v-for="item in categoryStore.category1List"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="二级分类">
        <el-select
          v-model="categoryStore.category2Id"
          :disabled="!switchTableStructure"
          :popper-options="{
            modifiers: [{ name: 'computeStyles', options: { adaptive: false } }]
          }"
          @change="handleCategory2"
        >
          <el-option
            v-for="item in categoryStore.category2List"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="三级分类">
        <el-select
          v-model="categoryStore.category3Id"
          :disabled="!switchTableStructure"
          :popper-options="{
            modifiers: [{ name: 'computeStyles', options: { adaptive: false } }]
          }"
          @change="handleCategory3"
        >
          <el-option
            v-for="item in categoryStore.category3List"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<style scoped lang="scss">
.attr-search {
}
</style>
