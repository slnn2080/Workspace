<script setup lang="ts">
import useUserStore from '@/store/userStore'
import useSettingStore from '@/store/settingStore'
import { useRoute } from 'vue-router'

import AppMenu from './AppMenu.vue'

const route = useRoute()
const userStore = useUserStore()

defineOptions({
  name: 'AppSideBar'
})

const settingStore = useSettingStore()
</script>

<template>
  <div class="side-bar__container">
    <!-- 滚动条 限制 side-bar 的高度, 代替浏览器原生的滚动条 -->
    <el-scrollbar>
      <!-- :collapse="true" -->
      <el-menu
        background-color="#001529"
        text-color="#fff"
        :unique-opened="true"
        :default-active="route.path"
        :collapse="settingStore.isCollapsed"
        :collapse-transition="false"
      >
        <AppMenu :menuList="userStore.menuRoutes" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<style scoped lang="scss">
.el-menu {
  border-right: none;
}
.side-bar {
  &__container {
    padding: 20px 0px 20px 20px;
    color: #fff;
  }
}
</style>
