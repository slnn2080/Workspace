<script setup lang="ts">
const menuList = [{ label: '菜单1' }, { label: '菜单2' }]
import ContextMenu from './components/ContextMenu.vue'
</script>

<template>
  <div>TEST</div>
  <div class="container">
    <ContextMenu class="block" :menu="menuList"> </ContextMenu>
  </div>
</template>

<style scoped lang="scss">
.test {
  font-size: 16px;
}
.container {
  display: grid;
  height: 100vh;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
}
.block {
  background-color: #ccc;
  margin: 10px;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 20px;
  color: #fff;
}
.block:nth-child(1) {
  background: #e9695e;
}
.block:nth-child(2) {
  background: #f4bd4f;
}
.block .block {
  background: #61c454;
  margin-top: 20vh;
  height: 20vh;
  width: 80%;
}
.block h2 {
  font-size: 1.2em;
}
</style>
