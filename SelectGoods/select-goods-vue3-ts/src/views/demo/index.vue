<script setup lang="ts">
import AppHeader from './child/AppHeader.vue'
import AppSideBar from './child/AppSideBar.vue'
import AppMain from './child/AppMain.vue'
import ModulePanel from './child/ModulePanel.vue'

defineOptions({
  name: 'Layout'
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header height="64px">
        <AppHeader />
      </el-header>
      <el-container>
        <el-aside width="300px">
          <AppSideBar />
        </el-aside>
        <el-main>
          <AppMain />
        </el-main>
      </el-container>
    </el-container>
    <div class="module-area" v-if="false">
      <ModulePanel />
    </div>
  </div>
</template>

<style scoped lang="scss">
.common-layout {
  height: 100%;
}

.el-header,
.el-aside,
.el-main {
  padding: 0;
  margin: 0;
}

.module-area {
  width: 40%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
