<!-- <script setup lang="ts">
import GwesAppHeader from './components/GwesHeader.vue'
import GwesAppSideBar from './components/GwesAppSideBar.vue'
import GwesAppMain from './components/GwesMain.vue'
import ModulePanel from './components/ModulePanel.vue'

defineOptions({
  name: 'Layout'
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header height="64px">
        <GwesAppHeader />
      </el-header>
      <el-container>
        <el-aside width="300px">
          <GwesAppSideBar />
        </el-aside>
        <el-main>
          <GwesAppMain />
        </el-main>
      </el-container>
    </el-container>
    <div class="module-area" v-if="true">
      <ModulePanel />
    </div>
  </div>
</template>

<style scoped lang="scss">
.common-layout {
  height: 100%;
}

.el-header,
.el-aside {
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
</style> -->
