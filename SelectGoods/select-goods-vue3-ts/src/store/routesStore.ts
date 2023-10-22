import { defineStore } from 'pinia'
import { constantRoutes } from '@/router/routes'
import type { RouteRecordRaw } from 'vue-router'

type stateType = {
  meunRoutes: RouteRecordRaw[]
}
const useRoutesStore = defineStore('routes', {
  state: (): stateType => {
    return {
      meunRoutes: constantRoutes
    }
  }
})

export default useRoutesStore
