<script setup lang="ts">
import { computed } from 'vue'
defineOptions({
  name: 'ModulePanel'
})

type propsType = {
  // 控制 module 选择区域的显示和隐藏的变量
  titleVisible?: boolean
  mode?: 'menu' | 'select'
}

const props = withDefaults(defineProps<propsType>(), {
  titleVisible: false,
  mode: 'menu'
})

const iconSize = computed(() => {
  return props.mode === 'menu'
    ? { width: '48px', height: '48px' }
    : { width: '72px', height: '72px' }
})

const modules = ['pa', 'wa', 'ia', 'so', 'ro', 'me', 'dc', 'bm', 'wf']

const modulesTipLaguage = {
  pa: 'Progress Analyzer',
  wa: 'Workload Analyzer',
  ia: 'Inventory Analyzer',
  so: 'Slotting Optimizer',
  ro: 'Routing Optimizer',
  me: 'Map Editor',
  dc: 'Data Connector',
  bm: 'Base Module',
  wf: 'Workload Forecast'
}

const modulesUrls = modules.map((item) => ({
  name: item,
  dark: `icon_${item}_dark`,
  lignt: `icon_${item}_light`,
  closed: `icon_${item}_closed`
}))

// const visible = ref(false)
// console.log(modules, modulesTipLaguage, modulesUrls)
</script>

<template>
  <el-card v-if="true" class="module-panel__wrapper">
    <!-- title -->
    <template v-if="titleVisible" #header>
      <div class="module-panel__title">
        <h2>GWES</h2>
      </div>
    </template>
    <div class="module-panel__body">
      <el-row class="module-panel__body__container">
        <el-col
          v-for="(item, index) of modulesUrls"
          :key="index"
          class="module-panel__body__item"
          :span="6"
        >
          <el-tooltip :show-arrow="false" :offset="0" :hide-after="100">
            <!-- 提示文本 -->
            <template #content>
              <span>{{ modulesTipLaguage[item.name] }}</span>
            </template>
            <div class="icon">
              <SvgIcon :name="item.lignt" v-bind="{ ...iconSize }" />
            </div>
          </el-tooltip>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<style scoped lang="scss">
.module-panel {
  &__wrapper {
    height: 100%;
  }
  &__title {
    text-align: center;
    font-size: 30px;
    font-weight: bold;
  }
  &__body {
    &__container {
      display: flex;
      justify-content: center;
    }
    &__item {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-content: space-between;
      margin: 10px;

      .icon {
        // border: 1px solid #222;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px 10px;

        &:hover {
          background-color: rgba(0, 0, 0, 0.12);
          cursor: pointer;
        }
      }
    }
  }
}
</style>
