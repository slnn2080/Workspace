<script setup lang="ts">
import AppHeader from './child/AppHeader.vue'
// import SideBar from './child/SideBar.vue'
// import Main from './child/Main.vue'
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header height="64px">
        <AppHeader />
      </el-header>
      <!-- <el-container>
        <el-aside width="200px">
          <SideBar />
        </el-aside>
        <el-main>
          <Main />
        </el-main>
      </el-container> -->
    </el-container>
  </div>
</template>

<style scoped lang="scss">
.el-header,
.el-aside,
.el-main {
  padding: 0;
  margin: 0;
}
</style>
