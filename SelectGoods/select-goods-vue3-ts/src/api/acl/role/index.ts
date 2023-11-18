// Sku页面 相关接口
import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'

import { roleItemType, getRoleListResType, permissionItemType } from './type'

enum API {
  // 获取角色列表 /{page}/{limit}
  GET_ROLELIST = '/admin/acl/role',
  // 添加角色
  POST_SAVEROLE = '/admin/acl/role/save',
  // 修改角色
  PUT_UPDATEROLE = '/admin/acl/role/update',
  // 根据角色(根据职位id)获取菜单 /roleId
  GET_GETROLE_MENU = '/admin/acl/permission/toAssign',
  // 分配权限
  POST_SETPERMISSION = '/admin/acl/permission/doAssign',
  // 根据id删除已有的职位
  DELETE_REMOVEROLE = '/admin/acl/role/remove'
}

type getRoleListApiType = (
  pageNo: number,
  pageSize: number,
  roleName: string
) => Promise<commonResType<getRoleListResType>>
export const getRoleListApi: getRoleListApiType = (
  pageNo,
  pageSize,
  roleName
) => {
  return service.get(
    `${API.GET_ROLELIST}/${pageNo}/${pageSize}?roleName=${roleName}`
  )
}

// 添加 和 修改 的接口 区别就是 请求体中是否有id
type saveOrUpdateApiType = (data: roleItemType) => Promise<commonResType<null>>
export const saveOrUpdateApi: saveOrUpdateApiType = (data) => {
  if (data.id) {
    return service.put(API.PUT_UPDATEROLE, data)
  } else {
    return service.post(API.POST_SAVEROLE, data)
  }
}

type getPermissionByRoleIdApiType = (
  roleId: number
) => Promise<commonResType<permissionItemType[]>>
export const getPermissionByRoleIdApi: getPermissionByRoleIdApiType = (
  roleId
) => {
  return service.get(`${API.GET_GETROLE_MENU}/${roleId}/`)
}

/*
  需要携带 roleId (职位ID) 标识给哪一个职位分配权限, 通过 query 携带
  需要携带 permissionId 通过query携带 格式是一个数组

  要点:
  当我们在url上方数组的时候, 会变成字符串脱掉[]
*/
type assignPermissionByRoleIdApiType = (
  roleId: number,
  permissionId: number[]
) => Promise<commonResType<null>>
export const assignPermissionByRoleIdApi: assignPermissionByRoleIdApiType = (
  roleId,
  permissionId
) => {
  return service.post(
    `${API.POST_SETPERMISSION}?roleId=${roleId}&permissionId=${permissionId}`
  )
}

type removeRoleByIdApiType = (roleId: number) => Promise<commonResType<null>>
export const removeRoleByIdApi: removeRoleByIdApiType = (roleId) => {
  return service.delete(`${API.DELETE_REMOVEROLE}/${roleId}`)
}
