<script setup lang="ts">
import { onMounted } from 'vue'
import useUserStore from '@/store/userStore'

defineOptions({
  name: 'Home'
})

const userStore = useUserStore()

onMounted(() => {
  // 首页挂载完毕发起请求获取用户信息
  userStore.getUserInfo()
})
</script>

<template>
  <div>Home</div>
</template>

<style scoped lang="scss"></style>
