<script setup lang="ts">
import AppHeader from './AppHeader/index.vue'
import AppMain from './AppMain/index.vue'
import AppSideBar from './AppSideBar/index.vue'

defineOptions({
  name: 'Layout'
})
</script>

<template>
  <div class="layout__container">
    <el-container>
      <el-header>
        <AppHeader />
      </el-header>
      <el-container class="layout__container__sub">
        <el-aside>
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

    .el-aside {
      width: $base-menu-width;
      background: $base-menu-bg;
    }
  }
}
</style>
