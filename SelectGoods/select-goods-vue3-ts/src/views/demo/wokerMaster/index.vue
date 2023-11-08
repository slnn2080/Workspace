<script setup lang="ts">
import { reactive } from 'vue'

import AppExpandPanel from '../components/AppExpandPanel.vue'
import AppTableContainer from '../components/AppTableContainer.vue'
import SearchArea from './SearchArea.vue'

defineOptions({
  name: 'WorkerMaster'
})

// ----- variable -----
type tableItemDataType = {
  centerCode: string
  wokerCode: string
  wokerName: string
}
const tableData = reactive<tableItemDataType[]>([
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  }
])

type paginationFormType = {
  pageSize?: number
  pageNo?: number
  total?: number
}
const pageForm = reactive<paginationFormType>({
  pageSize: 3,
  pageNo: 2,
  total: tableData.length
})

type editAreaVisiableType = {
  edit: boolean
  add: boolean
}
const editAreaVisiable = reactive<editAreaVisiableType>({
  edit: false,
  add: false
})

// ----- methods -----
const changeCurrentPageHandler = (pageNo: number): void => {
  console.log('changeCurrentPageHandler', pageNo)
  pageForm.pageNo = pageNo
}

// 行内点击的回调
const rowClickHandler = (row: tableItemDataType) => {
  console.log(row)
  // 展示: editArea 区域
  editAreaVisiable.add = !editAreaVisiable.add
  console.log(editAreaVisiable)
}

// ----- lifecycle ------
</script>

<template>
  <div class="worker-container">
    <!-- 折り畳み コンポーネント -->
    <AppExpandPanel>
      <SearchArea />
    </AppExpandPanel>

    <!-- テーブルコンテナ コンポーネント -->
    <AppTableContainer
      captionText="Table Area"
      :has-pagination="true"
      :pagination-form="pageForm"
      :edit-area-visiable="editAreaVisiable"
      @change-current-page="changeCurrentPageHandler"
    >
      <template #tableArea="{ height }">
        <!-- Table エリア -->
        <el-table
          :data="tableData"
          :height="height"
          @row-click="rowClickHandler"
        >
          <el-table-column prop="centerCode" label="センタコード" />
          <el-table-column prop="wokerCode" label="作業者コード" />
          <el-table-column prop="wokerName" label="作業者名" />
        </el-table>
      </template>
      <template #editArea>
        <!-- 編集エリア -->
        <div v-for="num in 10" :key="num" class="worker-master-edit-area">
          Edit Area {{ num }}
        </div>
      </template>
    </AppTableContainer>
  </div>
</template>

<style scoped lang="scss">
.worker-container {
  height: 100%;
  font-size: 16px;

  .worker-master {
    &-edit-area {
      background-color: #c2185b;
      padding: 20px 0px;
    }
  }
}
</style>
