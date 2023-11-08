<script setup lang="ts">
import { ref, computed } from 'vue'
import useSettingStore from '@/store/settingStore.ts'
import { getAssetsResource } from '@/utils/util'

defineOptions({
  name: 'AppExpandPanel'
})

const settingStore = useSettingStore()

// ----- variable -----
// 默认展开折叠面板
const activeCollapse = ref<string[]>(['1'])

// ----- methods -----

// ----- computed ------
const collapseUrl = computed(() => {
  return settingStore.isDark
    ? getAssetsResource('acr-menu-dark.png')
    : getAssetsResource('acr-menu-light.png')
})

// ----- lifecycle ------

// ----- export ------
</script>

<template>
  <el-card
    ref="cardRef"
    class="expand-panel target-expand-panel"
    shadow="never"
  >
    <el-collapse
      v-model="activeCollapse"
      class="expand-panel-collapse"
      accordion
    >
      <el-collapse-item name="1">
        <!-- 内容区 -->
        <div class="expand-panel-collapse__content">
          <slot />
        </div>
        <!-- 标题区 -->
        <template #title>
          <el-divider>
            <div class="expand-panel-collapse__icon">
              <img :src="collapseUrl" />
            </div>
          </el-divider>
        </template>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>

<style scoped lang="scss">
.expand-panel {
  // 减小card周围的padding
  :deep(.el-card__body) {
    --el-card-padding: 0px 20px;
  }

  &-collapse {
    // 去除折叠面板上下的边框
    border: none;

    // 去除折叠面板上下的边框
    :deep(.el-collapse-item__header) {
      border: none;

      // 去除折叠面板右侧的箭头
      .el-collapse-item__arrow {
        display: none;
      }
    }
    // 去除折叠面板上下的边框
    :deep(.el-collapse-item__wrap) {
      border: none;
    }

    // 折叠面板默认是 title区域在上方, 内容区域在下方 我们通过css控制下他们的排列方式
    :deep(.el-collapse-item) {
      display: flex;
      flex-direction: column-reverse;
    }

    &__icon {
      width: 15px;
      height: 15px;

      img {
        width: 100%;
        height: auto;
        vertical-align: top;
      }
    }

    &__content {
      padding-top: 20px;
    }
  }
}
</style>
