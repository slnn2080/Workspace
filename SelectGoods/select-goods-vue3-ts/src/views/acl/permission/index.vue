<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
//引入获取菜单请求API
import {
  getPermissionListApi,
  saveOrUpdatePermissionApi,
  removePermissionApi
} from '@/api/acl/permission'
//引入ts类型
import type {
  permissionItemType,
  editFormType
} from '@/api/acl/permission/type'

import { ElMessage } from 'element-plus'

defineOptions({
  name: 'PermissionPage'
})

// 存储菜单的数据
let permissionList = reactive<permissionItemType[]>([])

//控制对话框的显示与隐藏
let editDialogVisible = ref<boolean>(false)

//携带的参数
let menuData = reactive<editFormType>({
  // 权限值
  code: '',
  // 几级菜单 1为1级菜单 2为2级菜单
  level: 0,
  // 名称
  name: '',
  // 父id
  pid: 0
})
// pid 和 level 的作用就是给谁增加几级菜单

//组件挂载完毕
onMounted(() => {
  getPermissionList()
})

//获取菜单数据的方法
const getPermissionList = async () => {
  let result = await getPermissionListApi()
  if (result.code == 200) {
    permissionList.length = 0
    permissionList.push(...result.data)
  }
}

//添加菜单按钮的回调
const addPermisstion = (row: permissionItemType) => {
  //清空数据
  Object.assign(menuData, {
    id: 0,
    code: '',
    name: '',
    level: 0,
    pid: 0
  })

  //对话框显示出来
  editDialogVisible.value = true

  // row.level是点击按钮该行的数据, 我们要给它添加一个子菜单 所以是 level+1
  menuData.level = row.level + 1

  //给谁新增子菜单 我们给该行数据添加一个子菜单 那么该行菜单就是父菜单
  menuData.pid = row.id as number
}

//编辑已有的菜单
const updatePermisstion = (row: permissionItemType) => {
  editDialogVisible.value = true
  //点击修改按钮:收集已有的菜单的数据进行更新
  Object.assign(menuData, row)
}

// 对话框 确定按钮的回调
const save = async () => {
  //发请求:新增子菜单|更新某一个已有的菜单的数据
  let result: any = await saveOrUpdatePermissionApi(menuData)
  if (result.code == 200) {
    //对话框隐藏
    editDialogVisible.value = false
    //提示信息
    ElMessage({
      type: 'success',
      message: menuData.id ? '更新成功' : '添加成功'
    })
    //再次获取全部最新的菜单的数据
    getPermissionList()
  }
}

//删除按钮回调
const removeMenu = async (id: number) => {
  let result = await removePermissionApi(id)
  if (result.code == 200) {
    ElMessage({ type: 'success', message: '删除成功' })
    getPermissionList()
  }
}
</script>

<template>
  <!-- 
    1级菜单上 不能编辑和删除 我们使用 row.level 来判断
    3级菜单上 不叫 添加菜单 而是叫 添加功能
    4级菜单上 不能添加 只能编辑和删除
   -->
  <el-table
    :data="permissionList"
    style="width: 100%; margin-bottom: 20px"
    row-key="id"
    border
  >
    <el-table-column label="名称" prop="name"></el-table-column>
    <el-table-column label="权限值" prop="code"></el-table-column>
    <el-table-column label="修改时间" prop="updateTime"></el-table-column>
    <el-table-column label="操作">
      <!-- row:即为已有的菜单对象|按钮的对象的数据 -->
      <template #default="{ row }">
        <el-button
          type="primary"
          @click="addPermisstion(row)"
          size="small"
          :disabled="row.level == 4 ? true : false"
          >{{ row.level == 3 ? '添加功能' : '添加菜单' }}</el-button
        >
        <el-button
          type="primary"
          @click="updatePermisstion(row)"
          size="small"
          :disabled="row.level == 1 ? true : false"
          >编辑</el-button
        >
        <el-popconfirm
          :title="`你确定要删除${row.name}?`"
          width="260px"
          @confirm="removeMenu(row.id)"
        >
          <template #reference>
            <el-button
              type="primary"
              size="small"
              :disabled="row.level == 1 ? true : false"
              >删除</el-button
            >
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <!-- 对话框组件:添加或者更新已有的菜单的数据结构 -->
  <el-dialog
    v-model="editDialogVisible"
    :title="menuData.id ? '更新菜单' : '添加菜单'"
  >
    <!-- 表单组件:收集新增与已有的菜单的数据 -->
    <el-form>
      <el-form-item label="名称">
        <el-input
          placeholder="请你输入菜单名称"
          v-model="menuData.name"
        ></el-input>
      </el-form-item>
      <el-form-item label="权限">
        <el-input
          placeholder="请你输入权限数值"
          v-model="menuData.code"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped></style>
