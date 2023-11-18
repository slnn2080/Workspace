<script setup lang="ts">
import { ref, onMounted, reactive, nextTick } from 'vue'
//请求方法
import {
  getRoleListApi,
  saveOrUpdateApi,
  getPermissionByRoleIdApi,
  assignPermissionByRoleIdApi,
  removeRoleByIdApi
} from '@/api/acl/role'
import type { roleItemType, permissionItemType } from '@/api/acl/role/type'

import type { FormInstance } from 'element-plus'
import { ElMessage } from 'element-plus'

import useSettingStore from '@/store/settingStore.ts'

defineOptions({
  name: 'RolePage'
})

// 获取模板setting仓库
let settingStore = useSettingStore()

// 分页器相关数据
const paginationForm = reactive({
  pageNo: 1,
  pageSize: 5,
  total: 0
})

//搜索职位关键字
let keyword = ref<string>('')

// 存储全部已有的职位 allRole
let roleList = reactive<roleItemType[]>([])

//控制对话框的显示与隐藏
let roleDialogVisible = ref<boolean>(false)
//获取form组件实例
let formRef = ref<FormInstance>()
//控制抽屉显示与隐藏
let roleDrawerVisible = ref<boolean>(false)
//收集新增岗位数据 roleForm
let roleForm = reactive<roleItemType>({
  // 添加角色 只需要 roleName 修改的话需要 id 和 roleName
  roleName: ''
})
const resetField = <T extends object, K extends keyof T>(o: T, k: K) => {
  o[k] = '' as T[K]
}
const resetRoleForm = () => {
  for (const key in roleForm) {
    resetField(roleForm, key as keyof roleItemType)
  }
}

//准备一个数组:数组用于存储勾选的节点的ID(四级的)
let selectedRoleMenuList = ref<number[]>([])
//定义数组存储用户权限的数据
let userRoleMenuList = reactive<permissionItemType[]>([])
//获取tree组件实例
let tree = ref<any>()
//组件挂载完毕
onMounted(() => {
  //获取职位请求
  getRoleList()
})
//获取全部用户信息的方法|分页器当前页码发生变化的回调
const getRoleList = async () => {
  //修改当前页码
  let res = await getRoleListApi(
    paginationForm.pageNo,
    paginationForm.pageSize,
    keyword.value
  )
  if (res.code == 200) {
    paginationForm.total = res.data.total
    roleList.length = 0
    roleList.push(...res.data.records)
  }
}

//搜索按钮的回调
const search = () => {
  //再次发请求根据关键字
  getRoleList()
  keyword.value = ''
}
//重置按钮的回调
const reset = () => {
  settingStore.isRefreshed = !settingStore.isRefreshed
}
//添加职位按钮的回调
const addRoleHandler = () => {
  //对话框显示出来
  roleDialogVisible.value = true

  //清空数据
  resetRoleForm()

  //清空上一次表单校验错误结果
  nextTick(() => {
    formRef.value?.clearValidate('roleName')
  })
}
//更新已有的职位按钮的回调
const updateRole = (row: roleItemType) => {
  //显示出对话框
  roleDialogVisible.value = true
  //存储已有的职位----带有ID的
  Object.assign(roleForm, row)
  //清空上一次表单校验错误结果
  nextTick(() => {
    formRef.value?.clearValidate('roleName')
  })
}
//自定义校验规则的回调
const validatorRoleName = (_: any, value: any, callBack: any) => {
  if (value.trim().length >= 2) {
    callBack()
  } else {
    callBack(new Error('职位名称至少两位'))
  }
}
//职位校验规则
const rules = {
  roleName: [{ required: true, trigger: 'blur', validator: validatorRoleName }]
}

// 添加确定按钮的回调
const save = async () => {
  //表单校验结果,结果通过在发请求、结果没有通过不应该在发生请求
  await formRef.value?.validate()
  //添加职位|更新职位的请求
  let result = await saveOrUpdateApi(roleForm)
  if (result.code == 200) {
    //提示文字
    ElMessage({
      type: 'success',
      message: roleForm.id ? '更新成功' : '添加成功'
    })
    //对话框显示
    roleDialogVisible.value = false
    //再次获取全部的已有的职位
    getRoleList()
  }
}

//分配权限按钮的回调
//已有的职位的数据
const setPermisstion = async (row: roleItemType) => {
  //抽屉显示出来
  roleDrawerVisible.value = true
  //收集当前要分类权限的职位的数据
  Object.assign(roleForm, row)
  //根据职位获取权限的数据
  let res = await getPermissionByRoleIdApi(roleForm.id as number)
  if (res.code == 200) {
    console.log('res.data', res.data)
    userRoleMenuList.length = 0
    userRoleMenuList.push(...res.data)
    selectedRoleMenuList.value = filterSelectArr(userRoleMenuList, [])

    // 默认勾选需要手动设置
    nextTick(() => {
      tree.value.setCheckedKeys(selectedRoleMenuList.value)
    })
  }
}

//树形控件的字段映射对象
const defaultProps = {
  children: 'children',
  label: 'name'
}

// 过滤权限列表 获取到应该勾选的数据
/*
  递归遍历数组, 过滤出tree数据中 最深层级的对象中的数据 我们的目标是将4级数据中select: true的数据的id保存起来
*/
const filterSelectArr = (
  allData: permissionItemType[],
  collection: number[]
) => {
  allData.forEach((item: any) => {
    if (item.select && item.level == 4) {
      collection.push(item.id)
    }
    if (item.children && item.children.length > 0) {
      filterSelectArr(item.children, collection)
    }
  })

  return collection
}

//抽屉确定按钮的回调, 当点击确定按钮的时候 带着数据发起请求
const saveHandler = async () => {
  //职位的ID
  const roleId = roleForm.id as number
  //选中节点的ID
  let selectedIds = tree.value.getCheckedKeys()
  //半选的ID (父节点的id)
  let selectedIdsAmbiguity = tree.value.getHalfCheckedKeys()
  let permissionId = selectedIds.concat(selectedIdsAmbiguity)
  //下发权限
  let result = await assignPermissionByRoleIdApi(roleId, permissionId)
  if (result.code == 200) {
    //抽屉关闭
    roleDrawerVisible.value = false
    //提示信息
    ElMessage({ type: 'success', message: '分配权限成功' })
    //页面刷新 因为当前用户的权限发生变化后 应该更新页面
    window.location.reload()
  }
}

//删除已有的职位
const removeRole = async (id: number) => {
  let result: any = await removeRoleByIdApi(id)
  if (result.code == 200) {
    //提示信息
    ElMessage({ type: 'success', message: '删除成功' })
    getRoleList()
  }
}
</script>

<template>
  <el-card>
    <el-form :inline="true" class="form">
      <el-form-item label="职位搜索">
        <el-input
          placeholder="请你输入搜索职位关键字"
          v-model="keyword"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          size="default"
          :disabled="keyword ? false : true"
          @click="search"
          >搜索</el-button
        >
        <el-button type="primary" size="default" @click="reset">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
  <el-card style="margin: 10px 0px">
    <el-button type="primary" size="default" icon="Plus" @click="addRoleHandler"
      >添加职位</el-button
    >
    <el-table border style="margin: 10px 0px" :data="roleList">
      <el-table-column type="index" align="center" label="#"></el-table-column>
      <el-table-column label="ID" align="center" prop="id"></el-table-column>
      <el-table-column
        label="职位名称"
        align="center"
        prop="roleName"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        label="创建世间"
        align="center"
        show-overflow-tooltip
        prop="createTime"
      ></el-table-column>
      <el-table-column
        label="更新时间"
        align="center"
        show-overflow-tooltip
        prop="updateTime"
      ></el-table-column>
      <el-table-column label="操作" width="280px" align="center">
        <!-- row:已有的职位对象 -->
        <template #default="{ row }">
          <el-button
            type="primary"
            size="small"
            icon="User"
            @click="setPermisstion(row)"
            >分配权限</el-button
          >
          <el-button
            type="primary"
            size="small"
            icon="Edit"
            @click="updateRole(row)"
            >编辑</el-button
          >
          <el-popconfirm
            :title="`你确定要删除${row.roleName}?`"
            width="260px"
            @confirm="removeRole(row.id)"
          >
            <template #reference>
              <el-button type="primary" size="small" icon="Delete"
                >删除</el-button
              >
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="paginationForm.pageNo"
      v-model:page-size="paginationForm.pageSize"
      :page-sizes="[3, 5, 7, 9]"
      :background="true"
      layout="prev, pager, next, jumper,->,sizes,total"
      :total="paginationForm.total"
      @current-change="getRoleList"
      @size-change="getRoleList"
    />
  </el-card>
  <!-- 添加职位与更新已有职位的结构:对话框 -->
  <el-dialog
    v-model="roleDialogVisible"
    :title="roleForm.id ? '更新职位' : '添加职位'"
  >
    <el-form :model="roleForm" :rules="rules" ref="formRef">
      <el-form-item label="职位名称" prop="roleName">
        <el-input
          placeholder="请你输入职位名称"
          v-model="roleForm.roleName"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button
        type="primary"
        size="default"
        @click="roleDialogVisible = false"
        >取消</el-button
      >
      <el-button type="primary" size="default" @click="save">确定</el-button>
    </template>
  </el-dialog>
  <!-- 抽屉组件:分配职位的菜单权限与按钮的权限 -->
  <el-drawer v-model="roleDrawerVisible">
    <template #header>
      <h4>分配菜单与按钮的权限</h4>
    </template>
    <template #default>
      <!-- 树形控件 -->
      <el-tree
        ref="tree"
        :data="userRoleMenuList"
        show-checkbox
        node-key="id"
        default-expand-all
        :default-checked-keys="selectedRoleMenuList"
        :props="defaultProps"
      />
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="roleDrawerVisible = false">取消</el-button>
        <el-button type="primary" @click="saveHandler">确定</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<style scoped>
.form {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
}
</style>
