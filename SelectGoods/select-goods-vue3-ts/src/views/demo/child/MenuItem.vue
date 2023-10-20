<script lang="ts">
export default {
  name: 'MenuItem'
}
</script>
<script setup lang="ts">
import type { dataType } from '../../../assets/json/menuList'
import { useRouter } from 'vue-router'
defineOptions({
  name: 'MenuItem'
})

const router = useRouter()

type propsType = {
  list: dataType
}
const props = withDefaults(defineProps<propsType>(), {
  list: () => []
})

const changePage = (to) => {
  router.push(to.index)
}
</script>

<template>
  <template v-for="item in props.list" :key="item.path">
    <el-menu-item
      v-if="item.children.length === 0"
      :index="item.path"
      @click="changePage"
    >
      <template #title> {{ item.meta.title }}</template>
    </el-menu-item>

    <!-- 其它情况, 如果 route 有 children 且 children.length > 1 我们使用带有折叠的菜单组件 -->
    <el-sub-menu
      :index="item.path"
      v-else-if="item.hasSubMenu && item.children.length > 0"
    >
      <template #title> {{ item.meta.title }}</template>
      <!-- 递归调用 -->
      <MenuItem :list="item.children" />
    </el-sub-menu>
  </template>
</template>

<style scoped lang="scss"></style>
