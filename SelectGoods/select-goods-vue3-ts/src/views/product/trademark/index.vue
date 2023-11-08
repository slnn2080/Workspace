<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import {
  getTrademarkList,
  saveOrUpdateTrademark,
  deleteTrademarkById
} from '@/api/product/trademark'
import { ElMessage } from 'element-plus'

import type { trademarkItem } from '@/api/product/trademark/type'
import type { UploadProps, FormInstance } from 'element-plus'

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
    res.data.records.forEach((item) => {
      if (!/^http/.test(item.logoUrl)) {
        item.logoUrl = 'http://' + item.logoUrl
      }
    })
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
const formRef = ref<FormInstance>()
// 新增品牌: form
type addFormType = trademarkItem & {
  [_: string]: string
}
let addForm = reactive<addFormType>({
  tmName: '',
  logoUrl: ''
})

// 重置: addForm 数据
const resetAddForm = () => {
  for (const key in addForm) {
    addForm[key] = ''
  }
}

// 重置: addForm 错误信息
const resetAddFormErrMsg = () => {
  // 我们打开 添加品牌 的时候, 还没有 form 的 dom 元素
  // 方式1: 在value的后面加上?, 表示有了我再调用
  formRef.value?.clearValidate('tmName')
  formRef.value?.clearValidate('logoUrl')

  // 方式2: nextTick()
}

// 参数: 校验规则对象, 文件本内容, 放行函数
const checkTmName = (_: any, value: any, callback: any) => {
  if (value.trim().length < 2)
    // 校验未通过返回的错误信息
    return callback(new Error('品牌的名称位数应该大于2位'))
  callback()
}
const checkLogoUrl = (_: any, value: any, callback: any) => {
  // value: 是图片地址
  if (!value) return callback(new Error('必须上传品牌图片'))
  callback()
}
// 验证: addForm
const addFormRules = {
  tmName: [
    { required: true, message: '品牌名称是必传项', trigger: 'blur' },
    { validator: checkTmName, trigger: 'blur' }
  ],
  // 文件上传: 对于文件上传 trigger 不会起作用, 我们需要通过 form身上的validate()方法对整个表单进行校验
  // validate()方法可以实现对所有表单项进行校验, 我们可以在点击确定按钮的时候 对表单进行校验
  logoUrl: [{ required: true }, { validator: checkLogoUrl }]
}

// 添加品牌回调
const addTrademark = () => {
  // 打开对话框
  dialogVisible.value = true
  resetAddForm()
  resetAddFormErrMsg()
}

// 修改品牌回调
const updateTrademark = async (row: trademarkItem) => {
  // 打开对话框
  dialogVisible.value = true
  // 重置表单数据
  resetAddForm()
  resetAddFormErrMsg()

  // 回显数据
  // 方式1: 将 row 合并到 addForm 里: Object.assign(addForm, row)
  // 方式2:
  addForm.tmName = row.tmName
  addForm.logoUrl = row.logoUrl
  // 给 addForm 追加一个 id 字段 (响应式的)
  addForm.id = row.id
}

// 对话框: 确定 取消
const dialogCancelHander = () => {
  dialogVisible.value = false
  // 重置 addForm: 我们在 点击 [添加品牌] 按钮的时候做此操作
  // resetAddForm()
}

// 对话框: 确定 回调
const dialogConfirmHander = async () => {
  // 在发起请求之前 要对表单进行验证
  // Unhandled error during execution of component event handler
  // 使用 try catch 解决
  // validate() 会返回一个 promise, 校验失败返回的失败的promise 校验成功返回的是 fulfilled true
  try {
    // await 等待的是成功的结果 所以如果返回的是失败的饿promise 后续的代码会不执行
    await formRef.value?.validate()
  } catch (err) {
    return
  }

  // 关闭对话框
  dialogVisible.value = false

  // 发起添加品牌的请求
  const res = await saveOrUpdateTrademark(addForm)
  if (res.code === 200) {
    // 给出提示信息
    ElMessage({
      type: 'success',
      message: '修改成功'
    })
  } else {
    ElMessage({
      type: 'error',
      message: `修改失败: ${res.message}`
    })
  }

  // 重置 addForm: 我们在 点击 [添加品牌] 按钮的时候做此操作
  // resetAddForm()

  // 重新获取列表
  getList()
}

// el-upload: 上传图片成功之前的回调
const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  // 上传文件之间我们可以约束文件的类型和大小
  // rawFile: File对象 { size: 字节, type: , name: }

  // 要求: 上传文件的格式为 png|jpg|gif 4M
  const imgTypes = ['image/png', 'image/jpg', 'image/gif']
  // return false 中止上传
  if (!imgTypes.includes(rawFile.type)) {
    ElMessage({
      type: 'error',
      message: '上传的文件必须为 png jpg gif'
    })
    return false
  }

  // 限制文件大小
  if (rawFile.size / 1024 / 1024 > 4) {
    ElMessage({
      type: 'error',
      message: '上传的文件必须在4mb以内'
    })
    return false
  }
}
// 图片上传成功后的回调
/*
  回调参数说明:
    response: 服务器返回的数据
    uploadFile: {
      name: 图片名称.jpg,
      percentage: 100,
      raw: File对象,
      response: 服务器返回的数据,
      status: 'success'
    }
*/
const handleAvatarSuccess: UploadProps['onSuccess'] = (response) => {
  // 将file对象转换为url
  // addForm.logoUrl = URL.createObjectURL(uploadFile.raw!)
  addForm.logoUrl = response.data

  // 图片上传成功 清除掉图片表单项对应的error信息
  formRef.value?.clearValidate('logoUrl')
}

// 删除品牌的回调
const deleteTrademark = async (id: number) => {
  const res = await deleteTrademarkById(id)
  if (res.code === 200) {
    ElMessage({
      type: 'success',
      message: '删除成功'
    })
  } else {
    ElMessage({
      type: 'error',
      message: '删除失败'
    })
  }
  // 如果当前表格中的数据大于1 那么删除后要留在当页
  // 如果当前表格中的数据小于等于1 那么删除后要返回上一页
  // 所以如果我们使用的是老师的逻辑 则需要判断
  // tableData.length > 1 ? pageNo.value : pageNo.value - 1
  await getList()
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
            <template #default="{ row }">
              <el-button
                size="small"
                type="primary"
                icon="Edit"
                circle
                @click="updateTrademark(row)"
              />
              <el-popconfirm
                width="220"
                confirm-button-text="Yes"
                cancel-button-text="No"
                icon="Delete"
                icon-color="#c2185b"
                :hide-after="100"
                :title="`您确定要删除 ${row.tmName} 么?`"
                @confirm="deleteTrademark(row.id)"
              >
                <template #reference>
                  <el-button size="small" type="primary" icon="Delete" circle />
                </template>
              </el-popconfirm>
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
        <div class="dialog-container__title">
          {{ addForm.id ? '修改品牌' : '添加品牌' }}
        </div>
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
        ref="formRef"
        class="dialog-container__form"
        label-position="top"
        :model="addForm"
        :rules="addFormRules"
      >
        <el-form-item label="品牌名称" prop="tmName">
          <el-input v-model="addForm.tmName" placeholder="请输入品牌名称" />
        </el-form-item>
        <el-form-item label="品牌Logo" prop="logoUrl">
          <!-- action url 注意携带 /api -->
          <el-upload
            class="avatar-uploader"
            action="/api/admin/product/fileUpload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <!-- 上传成功展示上传图片 -->
            <img v-if="addForm.logoUrl" :src="addForm.logoUrl" class="avatar" />
            <!-- 没有上传展示 + -->
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
    </el-dialog>
  </section>
</template>

<style scoped lang="scss">
:deep(.el-message__content) {
  font-size: 16px;
}
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

  img {
    width: 150px;
    height: auto;
  }
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
