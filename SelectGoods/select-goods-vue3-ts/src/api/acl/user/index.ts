// Sku页面 相关接口
import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'

import {
  assignRoleParamsType,
  getUserinfoResType,
  userInfoType,
  getUserRolesResType
} from './type'

enum API {
  // 获取用户列表 /{page}/{limit}
  GET_USERLIST = '/admin/acl/user',
  // 新增用户
  POST_USER = '/admin/acl/user/save',
  // 修改用户
  PUT_USER = '/admin/acl/user/update',
  // 根据用户id获取 该用户现有的角色 和 所有角色列表 /userId
  GET_ALLROLE = '/admin/acl/user/toAssign',
  // 根据用户 给该用户分配角色
  POST_ASSIGNROLE = '/admin/acl/user/doAssignRole',
  // 根据id删除用户 /userId
  DELETE_USER = '/admin/acl/user/remove',
  // 根据 ids 列表 删除用户
  DELETE_BATCH_USER = '/admin/acl/user/batchRemove'
}

type getUserListApiType = (
  pageNo: number,
  pageSize: number,
  userName: string
) => Promise<commonResType<getUserinfoResType>>
export const getUserListApi: getUserListApiType = (
  pageNo,
  pageSize,
  userName
) => {
  return service.get(
    `${API.GET_USERLIST}/${pageNo}/${pageSize}?username=${userName}`
  )
}

// 合并: 新增 和 修改 用户信息
type saveOrUpdateUserApiType = (
  user: userInfoType
) => Promise<commonResType<null>>
export const saveOrUpdateUserApi: saveOrUpdateUserApiType = (user) => {
  if (user.id) {
    return service.put(API.PUT_USER, user)
  } else {
    return service.post(API.POST_USER, user)
  }
}

type getUserRolesApiType = (
  userId: number
) => Promise<commonResType<getUserRolesResType>>
export const getUserRolesApi: getUserRolesApiType = (userId) => {
  return service.get(`${API.GET_ALLROLE}/${userId}`)
}

type removeUserByIdApiType = (userId: number) => Promise<commonResType<null>>
export const removeUserByIdApi: removeUserByIdApiType = (userId) => {
  return service.delete(`${API.DELETE_USER}/${userId}`)
}
type removeUserBatchApiType = (
  userIds: number[]
) => Promise<commonResType<null>>
export const removeUserBatchApi: removeUserBatchApiType = (userIds) => {
  return service.delete(API.DELETE_BATCH_USER, { data: userIds })
}

/**
 * roleIdList: [0], 角色id数组
 * userId: 0, 给哪个用户分配什么样的岗位
 */
type assginRoleApiType = (
  params: assignRoleParamsType
) => Promise<commonResType<null>>
export const assginRoleApi: assginRoleApiType = (params) => {
  return service.post(API.POST_ASSIGNROLE, params)
}
