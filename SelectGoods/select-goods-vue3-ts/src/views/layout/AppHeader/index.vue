<script setup lang="ts">
import useSettingStore from '@/store/settingStore'

import Logo from './components/Logo.vue'
import AppBreadcrumb from './components/AppBreadcrumb.vue'
import Settings from './components/Settings.vue'

defineOptions({
  name: 'AppHeader'
})

// 获取 settingStore
const settingStore = useSettingStore()

const changeMenu = () => {
  settingStore.isCollapsed = !settingStore.isCollapsed
}
</script>

<template>
  <div class="header__container">
    <div class="header__left">
      <!-- Logo区域 -->
      <Logo />
      <!-- 左侧菜单栏 控制按钮 -->
      <div class="header__left__menu-controls">
        <el-icon @click="changeMenu">
          <component :is="settingStore.isCollapsed ? 'Fold' : 'Expand'" />
        </el-icon>
      </div>
      <!-- 面包屑组件 -->
      <AppBreadcrumb />
    </div>
    <div class="header__right">
      <Settings />
    </div>
  </div>
</template>

<style scoped lang="scss">
.header {
  &__container {
    height: 100%;
    background-color: rgb(245, 245, 245);
    display: flex;
    justify-content: space-between;
  }

  &__left {
    display: flex;
    align-items: center;

    &__menu-controls {
      padding: 0 20px;
      font-size: 20px;
    }
  }

  &__right {
    display: flex;
    align-items: center;
  }
}
</style>
