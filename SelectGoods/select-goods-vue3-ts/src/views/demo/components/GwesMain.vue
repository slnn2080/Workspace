<script setup lang="ts">
defineOptions({ name: 'GwesAppMain' })
</script>

<template>
  <div class="main-container">
    <router-view></router-view>
  </div>
</template>

<style scoped lang="scss">
.main-container {
  height: 100%;
}
</style>
