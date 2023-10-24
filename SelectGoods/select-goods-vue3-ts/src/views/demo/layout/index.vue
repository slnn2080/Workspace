<script setup lang="ts">
import GwesAppHeader from '../components/GwesHeader.vue'
import GwesAppSideBar from '../components/GwesAppSideBar.vue'
import GwesAppMain from '../components/GwesMain.vue'
import ModulePanel from '../components/ModulePanel.vue'

defineOptions({
  name: 'Layout'
})
</script>

<template>
  <div class="demo-container">
    <el-container>
      <el-header height="64px">
        <GwesAppHeader />
      </el-header>
      <el-container class="demo-container__sub">
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
.el-container {
  height: 100%;
}
.demo-container {
  height: 100%;

  &__sub {
    height: calc(100% - 60px);
  }
}

.el-header {
  padding: 0;
  margin: 0;
}

.el-main {
  background-color: #eee;
}

.module-area {
  width: 40%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
