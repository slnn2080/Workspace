<script setup lang="ts">
import { useRoute } from 'vue-router'
import { computed, watch, ref, nextTick } from 'vue'
import useSettingStore from '@/store/settingStore'

defineOptions({
  name: 'AppMain'
})

const route = useRoute()
// 过渡动画的时候不能加key
const key = computed(() => route.path)

// 控制当前组件是否销毁重建, 没点击刷新之前不销毁(v-if=true)
let flag = ref(true)

// 刷新按钮的相关逻辑
const settingStore = useSettingStore()
// 监听 settingStore.isRefreshed 的变化, 如果发生变化说明用户点击过刷新按钮
watch(
  () => settingStore.isRefreshed,
  () => {
    // v-if 可以销毁 和 重新创建组件

    // 将 flag 置为 false 销毁组件
    flag.value = false

    // 使用 nextTick, 当响应式数据发生变化后, 可以获取更新后的dom
    nextTick(() => {
      // 当模版渲染完毕后, 重新设置为true, 响应式数据发生变化后, 会再次渲染dom
      flag.value = true
    })
  }
)
</script>

<template>
  <div class="main__container">
    <!-- 使用 default 查询 解构出 Component, 它就是我们要展示的组件 -->
    <router-view #default="{ Component }">
      <!-- 为Component添加过渡动画的标签 -->
      <transition name="fade">
        <!-- 通过 全局组件 component is 进行渲染 Component -->
        <component v-if="flag" :is="Component" />
      </transition>
    </router-view>
  </div>
</template>

<style scoped lang="scss">
// Vue3的过渡动画写法

// 开始时的状态
.fade-enter-from {
  opacity: 0;
}

// 中间过程
.fade-enter-active {
  transition: all 1s;
}

// 结束时的状态
.fade-enter-to {
  opacity: 1;
}
</style>
