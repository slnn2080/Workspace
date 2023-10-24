<script setup lang="ts">
import { ref, onMounted } from 'vue'

import DataHeader from './components/DataHeader.vue'

import DataLTourist from './components/DataLTourist.vue'
import DataLAge from './components/DataLAge.vue'
import DataLSex from './components/DataLSex.vue'

defineOptions({
  name: 'Screen'
})

// 0. 定义大屏缩放的比例, 参数wh为设计稿的宽度
const getScale = (w = 1920, h = 1080): number => {
  const ratioW = window.innerWidth / w
  const raitoH = window.innerHeight / h

  return ratioW < raitoH ? ratioW : raitoH
}

// 1. 获取 数据大屏展示内容的DOM元素
const dataTarget = ref()

// 2. 当组件一挂载 我们就将 &__wrapper 元素往初始位置拽
const adjustDataTargetPosition = () => {
  dataTarget.value.style.transform = `scale(${getScale()}) translate(-50%,-50%)`
}

// 3. 在 onMounted 中进行调用
onMounted(() => {
  // 初始化组件的时候 我们将 &__wrapper 元素往初始位置拽
  adjustDataTargetPosition()
})

// 4. 监听窗口的尺寸变化
window.addEventListener('resize', adjustDataTargetPosition)
</script>

<template>
  <!-- 外层容器: 宽高和视口一样 -->
  <div class="data-container">
    <!-- 数据大屏展示内容的区域 -->
    <div ref="dataTarget" class="data-container__wrapper">
      <!-- 顶部内容 -->
      <div class="data-top">
        <DataHeader />
      </div>
      <!-- 主体内容 -->
      <div class="data-bottom">
        <div class="data-bottom__left">
          <DataLTourist class="data-tourist" />
          <DataLSex class="data-sex" />
          <DataLAge class="data-age" />
        </div>
        <div class="data-bottom__center"></div>
        <div class="data-bottom__right"></div>
      </div>
    </div>
  </div>
</template>

<!-- 
  适配方案: scale
  1. &__wrapper 的 中心点 调节成 左上角
  2. 将 &__wrapper 元素移动到 中心点
  3. 看 js 部分
 -->

<style scoped lang="scss">
// 外层容器: 宽高和视口一样
.data-container {
  font-size: 16px;

  width: 100vw;
  height: 100vh;
  background: url(./images/bg.png) no-repeat;
  background-size: cover;

  // 数据大屏展示内容的区域: scale方案 我们的设计稿是什么样的 直接写样式
  &__wrapper {
    width: 1920px;
    height: 1080px;

    // 适配方案: scale
    // 2. 将 &__wrapper 元素移动到 中心点
    position: fixed;
    left: 50%;
    top: 50%;
    // 1. &__wrapper 的 中心点 调节成 左上角
    transform-origin: left top;

    .data-top {
      width: 100%;
      height: 40px;
    }
    // 主体内容为3列, 我们分别设置为 1:2:1 的比例
    .data-bottom {
      display: flex;

      &__left {
        flex: 1;
        height: 1040px;

        // 左侧区域: 纵向排列 flex-direction
        display: flex;
        flex-direction: column;

        .data-tourist {
          flex: 1.2;
        }
        .data-age {
          flex: 1;
        }
        .data-sex {
          flex: 1;
        }
      }
      &__center {
        flex: 2;
      }
      &__right {
        flex: 1;
        height: 1040px;
      }
    }
  }
}
</style>
