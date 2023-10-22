<script setup lang="ts">
import AppMenu from './AppMenu.vue'
import useRoutesStore from '@/store/routesStore'

const routesStore = useRoutesStore()

defineOptions({
  name: 'AppSideBar'
})
</script>

<template>
  <div class="side-bar__container">
    <!-- 滚动条 限制 side-bar 的高度, 代替浏览器原生的滚动条 -->
    <el-scrollbar>
      <el-menu background-color="#001529" text-color="#fff">
        <AppMenu :menuList="routesStore.meunRoutes" />
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
