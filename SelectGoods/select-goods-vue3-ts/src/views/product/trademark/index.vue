<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'

import { getTrademarkList } from '@/api/product/trademark'
import type { trademarkItem } from '@/api/product/trademark/type'

defineOptions({
  name: 'Trademark'
})

onMounted(() => {
  getList()
})

// 表格数据
let tableData = reactive<trademarkItem[]>([])
// 表格列信息
const tableHeaders = reactive([
  {
    prop: 'tmName',
    label: '品牌名称',
    align: 'center',
    width: '100'
  },
  {
    prop: 'logoUrl',
    label: '品牌Logo',
    align: 'center',
    desc: 'img',
    width: '200'
  }
])

// 分页器: 双向绑定的数据
const paginationForm = reactive({
  pageNo: 1,
  pageSize: 5,
  total: 0
})

// 发起请求 - 获取数据 - 更新表格
const getList = async () => {
  /*
    res:
    data.records data.total
  */
  const res = await getTrademarkList(
    paginationForm.pageNo,
    paginationForm.pageSize
  )

  // 判断请求是否成功, 成功的话 进行赋值
  if (res.code === 200) {
    // 总记录数
    paginationForm.total = res.data.total
    tableData.length = 0
    // table list: createTime id logoUrl tmName updateTime
    tableData.push(...res.data.records)
  }
}

// 分页器: 选择每页显示多少条目的下拉菜单触发的回调
const changePageSizeHandler = () => {
  // 需求: 当我们重新选择了条目数后, 让其回到第一页
  // 方式1:
  paginationForm.pageNo = 1
  getList()
  /*
    方式2:
    我们给 getList 一个默认参数
    const getList = async (pageNo = 1) => {
      paginationForm.pageNo = pageNo
    }
    这样当我们调用 getList 不传递参数的时候 它的默认值就是1
    而当页码改变的时候 会触发 current-change 这时它会往getList中传入当前页码
    @current-change="getList"
  */
}

// 对话框 相关: 添加 和 修改 复用了同一个对话框
const dialogVisible = ref<boolean>(false)
// 添加品牌回调
const addTrademark = () => {
  // 打开对话框
  dialogVisible.value = true
}

// 修改品牌回调
const updateTrademark = () => {
  // 打开对话框
  dialogVisible.value = true
}

const dialogCancelHander = () => {
  dialogVisible.value = false
}
const dialogConfirmHander = () => {
  dialogVisible.value = false
}
</script>

<template>
  <section>
    <el-card class="trademark-container">
      <div>
        <el-button type="primary" icon="Plus" @click="addTrademark"
          >添加品牌</el-button
        >
      </div>
      <!-- 表格组件 -->
      <div class="trademark-container__table">
        <el-table :data="tableData" border>
          <el-table-column
            type="index"
            width="80"
            align="center"
            label="序号"
          />
          <el-table-column
            v-for="(item, index) in tableHeaders"
            :key="index"
            :label="item.label"
            :align="item.align"
            :prop="item.prop"
          >
            <template v-if="item.desc === 'img'" #default="{ row }">
              <div class="img-wrapper">
                <img :src="row.logoUrl" alt="" />
              </div>
            </template>
            <template v-else #default="{ row }">
              <div>{{ row.tmName }}</div>
            </template>
          </el-table-column>
          <el-table-column label="品牌操作">
            <template #default>
              <el-button
                size="small"
                type="primary"
                icon="Edit"
                circle
                @click="updateTrademark"
              />
              <el-button size="small" type="primary" icon="Delete" circle />
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!-- 分页器 -->
      <div class="trademark-container__pagination">
        <el-pagination
          v-model:current-page="paginationForm.pageNo"
          v-model:page-size="paginationForm.pageSize"
          :page-sizes="[3, 5, 7, 9]"
          :background="true"
          :small="true"
          layout="prev, pager, next, jumper, -> , sizes, total"
          :total="paginationForm.total"
          @current-change="getList"
          @size-change="changePageSizeHandler"
        />
      </div>
    </el-card>
    <!-- 对话框组件, 添加品牌的时候使用 -->
    <el-dialog class="dialog-container" v-model="dialogVisible">
      <!-- 标题区域 -->
      <template #header>
        <div class="dialog-container__title">添加品牌</div>
        <el-divider />
      </template>
      <template #footer>
        <div class="dialog-container__btn">
          <el-divider style="margin: 12px 0px" border-style="dashed" />
          <el-button type="primary" @click="dialogCancelHander">取消</el-button>
          <el-button type="primary" @click="dialogConfirmHander"
            >确定</el-button
          >
        </div>
      </template>
      <el-form
        ref="dialogRef"
        class="dialog-container__form"
        label-position="top"
      >
        <el-form-item label="品牌名称">
          <el-input placeholder="请输入品牌名称" />
        </el-form-item>
        <el-form-item label="品牌Logo">
          <el-upload
            class="avatar-uploader"
            action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <!-- 上传成功展示上传图片 -->
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <!-- 没有上传展示 + -->
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
    </el-dialog>
  </section>
</template>

<style scoped lang="scss">
.trademark-container {
  &__table {
    margin-top: 20px;

    .img-wrapper {
      width: 100%;
      height: 60px;
      display: flex;
      align-items: center;
      justify-content: center;

      img {
        width: 60px;
        height: auto;
        vertical-align: bottom;
      }
    }
  }
  &__pagination {
    margin-top: 20px;
  }
}

:deep(.dialog-container) {
  padding: 15px 20px;

  #{&}__title {
    font-weight: bold;
  }

  .el-dialog__body {
    padding-top: 0px;
    padding-bottom: 0px;
  }
}

.avatar-uploader :deep(.el-upload) {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader :deep(.el-upload:hover) {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 150px;
  height: 150px;
  text-align: center;
}

.el-divider--horizontal {
  margin: 20px 0px;
}
</style>
