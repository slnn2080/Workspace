<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { computed } from 'vue'

defineOptions({
  name: 'AppBreadcrumb'
})

const route = useRoute()

const breadcrumbItems = computed(() => {
  return route.matched.map((route) => ({
    name: route.meta.title,
    icon: route.meta.icon,
    // 面包屑导航可以点击跳转时用到的路径
    path: route.path
  }))
})
</script>

<template>
  <div class="breadcrumb__container">
    <!-- 面包屑组件需要动态的展示路由的名字和标题 -->
    <el-breadcrumb separator-icon="ArrowRight">
      <el-breadcrumb-item
        v-for="(item, index) in breadcrumbItems"
        v-show="item.name !== ''"
        :key="index"
        :to="item.path"
      >
        {{ item.name }}
      </el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<style scoped lang="scss">
.breadcrumb {
  &__container {
    padding: 0 20px 0 0;
  }
}
</style>
