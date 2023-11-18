<script setup lang="ts">
import useSettingStore from '@/store/settingStore.ts'
import { ref, onMounted, reactive } from 'vue'

import {
  getUserListApi,
  saveOrUpdateUserApi,
  getUserRolesApi,
  removeUserByIdApi,
  removeUserBatchApi,
  assginRoleApi
} from '@/api/acl/user'
import type { userInfoType, roleItemType } from '@/api/acl/user/type'

import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'

defineOptions({
  name: 'User'
})

// 分页器相关数据
const paginationForm = reactive({
  pageNo: 1,
  pageSize: 5,
  keyword: '',
  total: 0
})

// 搜索关键字
let keyword = ref<string>('')

// 获取模板setting仓库
let settingStore = useSettingStore()

//存储全部用户的数组
const userTableData = reactive<userInfoType[]>([])

// 添加 / 修改 用户信息 抽屉 控制变量
let userDrawerVisible = ref<boolean>(false)

// 添加用户 表单数据 userForm
const userForm = reactive<userInfoType>({
  username: '',
  name: '',
  password: ''
})

const userFormRef = ref<FormInstance>()
const resetUserForm = () => {
  for (const key in userForm) {
    ;(userForm as any)[key] = ''
  }
}

//校验用户名字回调函数
const validatorUsername = (_: any, value: any, callBack: any) => {
  //用户名字|昵称,长度至少五位
  if (value.trim().length >= 5) {
    callBack()
  } else {
    callBack(new Error('用户名字至少五位'))
  }
}
//校验用户名字回调函数
const validatorname = (_: any, value: any, callBack: any) => {
  //用户名字|昵称,长度至少五位
  if (value.trim().length >= 5) {
    callBack()
  } else {
    callBack(new Error('用户昵称至少五位'))
  }
}
const validatorPassword = (_: any, value: any, callBack: any) => {
  //用户名字|昵称,长度至少五位
  if (value.trim().length >= 6) {
    callBack()
  } else {
    callBack(new Error('用户密码至少六位'))
  }
}

//表单校验的规则对象
const userRules = reactive<FormRules<typeof userForm>>({
  //用户名字
  username: [{ required: true, trigger: 'blur', validator: validatorUsername }],
  //用户昵称
  name: [{ required: true, trigger: 'blur', validator: validatorname }],
  //用户的密码
  password: [{ required: true, trigger: 'blur', validator: validatorPassword }]
})

//控制分配角色抽屉显示与隐藏
let roleDrawerVisible = ref<boolean>(false)

//存储全部职位的数据; el-checkbox-group 的话 使用 reactive 不好用
let allRoleList = ref<roleItemType[]>([])
// let allRoleList = reactive<roleItemType[]>([])
//当前用户已有的职位; el-checkbox-group 的话 使用 reactive 不好用
let selectedRoles = ref<roleItemType[]>([])
// let selectedRoles = reactive<roleItemType[]>([])

// //收集顶部复选框全选数据
const checkAll = ref<boolean>(false)
// //控制顶部全选复选框不确定的样式
const isIndeterminate = ref<boolean>(true)

// 批量删除: 用户id数组
let selectedUserIds = reactive<number[]>([])

//组件挂载完毕
onMounted(() => {
  getUserList()
})

//获取全部已有的用户信息
const getUserList = async () => {
  //收集当前页码
  let res = await getUserListApi(
    paginationForm.pageNo,
    paginationForm.pageSize,
    keyword.value
  )
  if (res.code == 200) {
    paginationForm.total = res.data.total
    userTableData.length = 0
    userTableData.push(...res.data.records)
  }
}

// 添加用户按钮的回调
const addUserHandler = () => {
  //清空数据 + 清除上一次的错误的提示信息 (调用form api)
  userFormRef.value?.resetFields()
  resetUserForm()

  // 抽屉显示出来
  userDrawerVisible.value = true

  // 方式2:
  // nextTick(() => {
  //   userFormRef.value?.clearValidate('username')
  //   userFormRef.value?.clearValidate('name')
  //   userFormRef.value?.clearValidate('password')
  // })
}
// 更新已有的用户按钮的回调
const updateUserHandler = (row: userInfoType) => {
  //抽屉显示出来
  userDrawerVisible.value = true
  // 先清空表单验证 ? 和 nextTick 的作用是一样的
  userFormRef.value?.resetFields()

  //存储收集已有的账号信息
  Object.assign(userForm, row)
}

/*
  新增用户 需携带:
    1. username
    2. password
    3. name

  更新用户 需携带:
    1. id
    2. username
    3. password: 这个不用收集, 因为我们点击row后 会给form重新赋值, 这时password字段的值就会被赋值为服务器返回的数据, 而不是我们担心的''串
    4. name
*/

//保存按钮的回调
const saveUserHandler = async () => {
  //点击保存按钮的时候,务必需要保证表单全部复合条件在去发请求
  await userFormRef.value?.validate()

  //保存按钮:添加新的用户|更新已有的用户账号信息
  let result = await saveOrUpdateUserApi(userForm)
  //添加或者更新成功
  if (result.code == 200) {
    //关闭抽屉
    userDrawerVisible.value = false
    //提示消息
    ElMessage({
      type: 'success',
      message: userForm.id ? '更新成功' : '添加成功'
    })
    //获取最新的全部账号的信息
    // getUserList();

    // 如果我们修改的是登录的用户的信息, 需要让浏览器自动刷新一次, 因为getUserList()只会让列表刷新 但是我们的用户信息在Header等地方 所以我们需要让页面刷新重新加载最新的数据
    window.location.reload()
  } else {
    //关闭抽屉
    userDrawerVisible.value = false
    //提示消息
    ElMessage({
      type: 'error',
      message: userForm.id ? '更新失败' : '添加失败'
    })
  }
}

const cancelHandler = (): void => {
  userDrawerVisible.value = false
  resetUserForm()
}

// //分配角色按钮的回调
const assignRole = async (row: userInfoType) => {
  // 存储已有的用户信息
  Object.assign(userForm, row)

  //获取全部的职位的数据与当前用户已有的职位的数据
  let res = await getUserRolesApi(userForm.id as number)
  console.log(res)
  if (res.code == 200) {
    //存储全部的职位
    // allRoleList.length = 0
    // allRoleList.push(...res.data.allRolesList)
    allRoleList.value = res.data.allRolesList

    //存储当前用户已有的职位
    // selectedRoles.length = 0
    // selectedRoles.push(...res.data.assignRoles)
    selectedRoles.value = res.data.assignRoles
    //抽屉显示出来
    roleDrawerVisible.value = true
  }
}

// //顶部的全部复选框的change事件
const checkAllChangeHandler = (val: boolean) => {
  //val:true(全选)|false(没有全选)
  // if (val) {
  //   selectedRoles.length = 0
  //   selectedRoles.push(...allRoleList)
  // } else {
  //   selectedRoles.length = 0
  // }

  selectedRoles.value = val ? allRoleList.value : []
  //不确定的样式(修改为确定样式)
  isIndeterminate.value = false
}

// 顶部全部的复选框的change事件
const checkedItemChangeHandler = (value: string[]) => {
  //顶部复选框的勾选数据
  //代表:勾选上的项目个数与全部的职位个数相等，顶部的复选框勾选上
  // checkAll.value = value.length === allRoleList.length
  checkAll.value = value.length === allRoleList.value.length
  //不确定的样式
  // isIndeterminate.value = value.length !== allRoleList.length
  isIndeterminate.value = value.length !== allRoleList.value.length
}
//确定按钮的回调(分配职位)
const roleConfirmHandler = async () => {
  //收集参数
  let data = {
    userId: userForm.id as number,
    roleIdList: selectedRoles.value.map((item) => {
      return item.id as number
    })
  }
  //分配用户的职位
  let result = await assginRoleApi(data)
  if (result.code == 200) {
    //提示信息
    ElMessage({ type: 'success', message: '分配职务成功' })
    //关闭抽屉
    roleDrawerVisible.value = false
    //获取更新完毕用户的信息,更新完毕留在当前页
    getUserList()
  }
}

// //删除某一个用户
const deleteUserHandler = async (userId: number) => {
  let result = await removeUserByIdApi(userId)
  if (result.code == 200) {
    ElMessage({ type: 'success', message: '删除成功' })
    getUserList()
  }
}
// //table复选框勾选的时候会触发的事件
const selectChange = (rows: userInfoType[]) => {
  const ids = rows.map((item) => item.id as number)
  selectedUserIds.length = 0
  selectedUserIds.push(...ids)
  console.log(selectedUserIds)
}
//批量删除按钮的回调
const deleteUsersHandler = async () => {
  //批量删除的请求
  let result = await removeUserBatchApi(selectedUserIds)
  if (result.code == 200) {
    ElMessage({ type: 'success', message: '删除成功' })
    getUserList()
  }
}

//搜索按钮的回调
const search = () => {
  //根据关键字获取相应的用户数据, username 拼接到了 url 上
  // ${API.GET_USERLIST}/${pageNo}/${pageSize}?username=${userName}
  getUserList()
  //搜索完后 清空关键字
  keyword.value = ''
}
//重置按钮
/*
  重置按钮就是刷新组件 当年我们在 AppMain 中完成过的逻辑
  let flag = ref(true)
  watch(
  () => settingStore.isRefreshed,
  () => {
    // v-if 可以销毁 和 重新创建组件

    // 将 flag 置为 false 销毁组件
    flag.value = false

    // 使用 nextTick, 当响应式数据发生变化后, 可以获取更新后的dom
    nextTick(() => {
      // 当模版渲染完毕后, 重新设置为true, 响应式数据发生变化后, 会再次渲染dom
      flag.value = true
    })
  }
)
*/
const reset = () => {
  settingStore.isRefreshed = !settingStore.isRefreshed
}

const pageSizeChangeHandler = () => {
  getUserList()
}
const pageNoChangeHandler = () => {
  getUserList()
}
</script>

<template>
  <el-card style="height: 80px">
    <!-- el-form开启flex el-form-item 一左一右 -->
    <el-form :inline="true" class="form">
      <el-form-item label="用户名:">
        <el-input placeholder="请你输入搜索用户名" v-model="keyword"></el-input>
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
    <el-button type="primary" size="default" @click="addUserHandler"
      >添加用户</el-button
    >
    <el-button
      type="primary"
      size="default"
      :disabled="selectedUserIds.length ? false : true"
      @click="deleteUsersHandler"
      >批量删除</el-button
    >
    <!-- table展示用户信息 -->
    <el-table
      @selection-change="selectChange"
      style="margin: 10px 0px"
      border
      :data="userTableData"
    >
      <!-- 第一列是选择框 -->
      <el-table-column type="selection" align="center"></el-table-column>
      <el-table-column label="#" align="center" type="index"></el-table-column>
      <el-table-column label="ID" align="center" prop="id"></el-table-column>
      <el-table-column
        label="用户名字"
        align="center"
        prop="username"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        label="用户名称"
        align="center"
        prop="name"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        label="用户角色"
        align="center"
        prop="roleName"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateTime"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column label="操作" width="300px" align="center">
        <template #default="{ row }">
          <el-button
            type="primary"
            size="small"
            icon="User"
            @click="assignRole(row)"
            >分配角色</el-button
          >
          <el-button
            type="primary"
            size="small"
            icon="Edit"
            @click="updateUserHandler(row)"
            >编辑</el-button
          >
          <el-popconfirm
            :title="`你确定要删除${row.username}?`"
            width="260px"
            @confirm="deleteUserHandler(row.id)"
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

    <!-- 分页器 -->
    <el-pagination
      v-model:current-page="paginationForm.pageNo"
      v-model:page-size="paginationForm.pageSize"
      :page-sizes="[5, 7, 9, 11]"
      :background="true"
      layout="prev, pager, next, jumper,->,sizes,total"
      :total="paginationForm.total"
      @current-change="pageNoChangeHandler"
      @size-change="pageSizeChangeHandler"
    />
  </el-card>

  <!-- 抽屉结构: 添加新的用户账号 | 更新已有的账号信息 表单结构 -->
  <el-drawer v-model="userDrawerVisible">
    <!-- 头部标题:将来文字内容应该动态的 -->
    <template #header>
      <h4>{{ userForm.id ? '更新用户' : '添加用户' }}</h4>
    </template>
    <!-- 身体部分 -->
    <template #default>
      <el-form :model="userForm" :rules="userRules" ref="userFormRef">
        <el-form-item label="用户姓名" prop="username">
          <el-input
            placeholder="请您输入用户姓名"
            v-model="userForm.username"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户昵称" prop="name">
          <el-input
            placeholder="请您输入用户昵称"
            v-model="userForm.name"
          ></el-input>
        </el-form-item>
        <!-- 添加需要输入密码项, 修改用户不需要密码项 -->
        <el-form-item v-if="!userForm.id" label="用户密码" prop="password">
          <el-input
            placeholder="请您输入用户密码"
            v-model="userForm.password"
          ></el-input>
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="cancelHandler">取消</el-button>
        <el-button type="primary" @click="saveUserHandler">确定</el-button>
      </div>
    </template>
  </el-drawer>

  <!-- 抽屉结构:用户某一个已有的账号进行职位分配 -->
  <el-drawer v-model="roleDrawerVisible">
    <template #header>
      <h4>分配角色(职位)</h4>
    </template>
    <template #default>
      <el-form>
        <el-form-item label="用户姓名">
          <!-- 回显用户信息, 只是显示用 -->
          <el-input v-model="userForm.username" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="职位列表">
          <el-checkbox
            v-model="checkAll"
            :indeterminate="isIndeterminate"
            @change="checkAllChangeHandler"
            >全选</el-checkbox
          >
          <!-- 显示职位的的复选框 -->
          <el-checkbox-group
            v-model="selectedRoles"
            @change="checkedItemChangeHandler"
          >
            <!-- 收集的是一个 role 对象 -->
            <el-checkbox
              v-for="(role, index) in allRoleList"
              :key="index"
              :label="role"
              >{{ role.roleName }}</el-checkbox
            >
          </el-checkbox-group>
        </el-form-item>
      </el-form>
    </template>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="roleDrawerVisible = false">取消</el-button>
        <el-button type="primary" @click="roleConfirmHandler">确定</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<style scoped>
.form {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
