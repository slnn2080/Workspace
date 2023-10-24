<script setup lang="ts">
import useSettingStore from '@/store/settingStore'
import { computed } from 'vue'

import AppHeader from './AppHeader/index.vue'
import AppMain from './AppMain/index.vue'
import AppSideBar from './AppSideBar/index.vue'

defineOptions({
  name: 'Layout'
})

// 引入 setting store
const settingStore = useSettingStore()

// 动态设置 el-aside 组件的类型
const asideName = computed(() => {
  return settingStore.isCollapsed ? 'is-collapsed' : ''
})
</script>

<template>
  <div class="layout__container">
    <el-container>
      <el-header>
        <AppHeader />
      </el-header>
      <el-container class="layout__container__sub">
        <el-aside :class="asideName">
          <AppSideBar />
        </el-aside>
        <el-main>
          <AppMain />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped lang="scss">
.layout {
  &__container {
    width: 100%;
    height: 100%;
    font-size: 16px;

    // 感觉布局容器没有固定高度似的
    .el-container {
      height: 100%;
    }
    // 第二个 .el-container 就不能是 100% 了 它要减去header的部分
    &__sub.el-container {
      height: calc(100% - 60px);
    }

    // 清除边距: element-plus 提供的组件都有padding 和 margin的问题
    .el-header,
    .el-aside {
      padding: 0;
      margin: 0;
    }

    .el-header {
      height: $base-topbar-height;
    }

    // 设置过渡效果 + 设置折叠后的组件宽度
    .el-aside {
      transition: width 0.3s;
      -webkit-transition: width 0.3s;
      -moz-transition: width 0.3s;
      -webkit-transition: width 0.3s;
      -o-transition: width 0.3s;

      width: $base-menu-width;
      background: $base-menu-bg;
      // transition: all 0.5s;

      &.is-collapsed {
        width: $base-menu-width-collapsed;
      }
    }
  }
}
</style>
