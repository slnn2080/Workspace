<script setup lang="ts">
import { useRouter } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'
import type { ElMenuItem } from 'element-plus'

defineOptions({
  name: 'AppMenu'
})

const router = useRouter()

type propsType = {
  menuList: RouteRecordRaw[]
}
// ToDo: 路由列表的类型怎么定义
// 要不我们就直接定义: defineProps(['menuList'])
const props = withDefaults(defineProps<propsType>(), {
  menuList: () => [] as RouteRecordRaw[]
})

// 路由跳转
const goToRoute = (to: any) => {
  router.push(to.index)
}
</script>

<template>
  <!-- 因为要遍历多个结构, 所以我们遍历 template -->
  <template v-for="item in props.menuList" :key="item.path">
    <!-- 递归出口1: 菜单项没有子菜单 使用 el-menu-item -->
    <template v-if="!item.children">
      <el-menu-item
        v-if="!item.meta?.hidden"
        :index="item.path"
        @click="goToRoute"
      >
        <el-icon>
          <component :is="item.meta?.icon" />
        </el-icon>
        <template #title>
          <span>{{ item.meta?.title }}</span>
        </template>
      </el-menu-item>
    </template>

    <!-- 递归出口2: 菜单项有子菜单 但只有一个 使用 el-menu-item -->
    <template v-if="item.children && item.children.length === 1">
      <el-menu-item
        v-if="!item.children[0].meta?.hidden"
        :index="item.children[0].path"
        @click="goToRoute"
      >
        <el-icon>
          <component :is="item.children[0].meta?.icon" />
        </el-icon>
        <template #title>
          <span>{{ item.children[0].meta?.title }}</span>
        </template>
      </el-menu-item>
    </template>
    <!-- 其它情况: 菜单项有子菜单 内部递归调用 -->
    <el-sub-menu
      v-if="item.children && item.children.length > 1"
      :index="item.path"
    >
      <template #title>
        <el-icon>
          <component :is="item.meta?.icon" />
        </el-icon>
        <span>{{ item.meta?.title }}</span>
      </template>
      <!-- 递归调用 -->
      <AppMenu :menuList="item.children" />
    </el-sub-menu>
  </template>
</template>
<style scoped lang="scss">
.el-menu-item,
.el-sub-menu {
  --el-menu-item-font-size: 14px;
  --el-menu-item-height: 60px;
  --el-menu-sub-item-height: 60px;
}

.menu {
  &__sub-menu {
    // display: none;
    background-color: red;
  }
}
</style>
