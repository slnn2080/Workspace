<script setup lang="ts">
import Logo from './components/Logo.vue'
defineOptions({
  name: 'AppHeader'
})
</script>

<template>
  <div class="header__container">
    <div class="header__left">
      <!-- Logo区域 -->
      <Logo />
    </div>
    <div class="header__right"></div>
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
  }
}
</style>
