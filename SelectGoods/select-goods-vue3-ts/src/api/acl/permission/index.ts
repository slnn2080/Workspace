import service from '@/utils/request'
import type { commonResType } from '@/api/commonTypes'

import { permissionItemType, editFormType } from './type'

enum API {
  // 获取菜单信息
  GET_PERMISSION_LIST = '/admin/acl/permission',

  // 如下的两个接口携带的参数都是一样的 只不过新增的没有id 修改有
  POST_ADD_PERMISSION = '/admin/acl/permission/save',
  PUT_UPDATE_PERMISSION = '/admin/acl/permission/update',
  //删除已有的菜单 /id
  DELETE_PERMISSION = '/admin/acl/permission/remove/'
}

type getPermissionListType = () => Promise<commonResType<permissionItemType[]>>
export const getPermissionListApi: getPermissionListType = () => {
  return service.get(API.GET_PERMISSION_LIST)
}

type saveOrUpdatePermissionApiType = (
  data: editFormType
) => Promise<commonResType<null>>
export const saveOrUpdatePermissionApi: saveOrUpdatePermissionApiType = (
  data
) => {
  if (data.id) {
    return service.put(API.PUT_UPDATE_PERMISSION, data)
  } else {
    return service.post(API.POST_ADD_PERMISSION, data)
  }
}

type removePermissionApiType = (id: number) => Promise<commonResType<null>>
export const removePermissionApi: removePermissionApiType = (id) => {
  return service.delete(`${API.DELETE_PERMISSION}/${id}`)
}
