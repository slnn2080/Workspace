// 关于 Layout 组件 相关配置的仓库
import { defineStore } from 'pinia'

type stateType = {
  isCollapsed: boolean
  isRefreshed: boolean
  isDark: boolean
}
const useSettingStore = defineStore('setting', {
  state: (): stateType => {
    return {
      // 控制菜单是折叠还是收起
      isCollapsed: false,
      // 用户控制刷新效果
      isRefreshed: false,
      isDark: false
    }
  }
})

export default useSettingStore
