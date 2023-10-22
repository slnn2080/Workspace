<script setup lang="ts">
import { ArrowRight } from '@element-plus/icons-vue'
defineOptions({
  name: 'GwesBreadcrumb'
})

type propsType = {
  list: string[]
  fs: string
}
withDefaults(defineProps<propsType>(), {
  list: () => [],
  fs: '16px'
})

//
</script>

<template>
  <el-breadcrumb :separator-icon="ArrowRight">
    <el-breadcrumb-item v-for="(item, index) of list" :key="index">
      {{ item }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<style scoped lang="scss">
.el-breadcrumb__item {
  font-size: v-bind(fs);
}
</style>
