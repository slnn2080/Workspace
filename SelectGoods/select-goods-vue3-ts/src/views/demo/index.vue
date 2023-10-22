<script setup lang="ts">
import GwesAppHeader from './child/GwesHeader.vue'
import GwesAppSideBar from './child/GwesAppSideBar.vue'
import GwesAppMain from './child/GwesMain.vue'
import ModulePanel from './child/ModulePanel.vue'

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
