<template>
  <div ref="containerRef">
    <slot></slot>
    <Teleport to="body">
      <Transition
        @beforeEnter="handleBeforeEnter"
        @enter="handleEnter"
        @afterEnter="handleAfterEnter"
      >
        <div
          v-if="showMenu"
          class="context-menu"
          :style="{
            left: pos.posX + 'px',
            top: pos.posY + 'px'
          }"
        >
          <div v-resize="handleSizeChange" class="menu-list">
            <div
              @click="handleClick(item)"
              class="menu-item"
              v-for="item in menu"
              :key="item.label"
            >
              {{ item.label }}
            </div>
          </div>
        </div>
      </Transition>
    </Teleport>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import useContextMenu from './useContextMenu'
import useViewport from './useViewport'

defineProps({
  menu: {
    type: Array,
    default: () => []
  }
})
const containerRef = ref(null)
const emit = defineEmits(['select'])
// 鼠标位置
const { x, y, showMenu } = useContextMenu(containerRef)
// 视口尺寸
const { vw, vh } = useViewport()
// 菜单的 宽 高
const w = ref(0)
const h = ref(0)

// 调整 xy的位置 到合适的地方
const pos = computed(() => {
  let posX = x.value
  let posY = y.value

  /*
    调整x y需要知道如下的3个东西
    1. 视口尺寸
    2. 鼠标位置
    3. 右键菜单的尺寸 监听菜单元素的尺寸变化

    如果鼠标点击在屏幕最右边 菜单右侧会被遮挡
      鼠标位置超过了 视口宽度 - 菜单的宽度

    如果鼠标点击在屏幕最下方 菜单下边会被遮挡
      鼠标位置超过了 视口高度 - 菜单的高度
  */

  if (x.value > vw.value - w.value) {
    // 菜单的x位置 鼠标位置的x-菜单的宽度
    posX = x.value - w.value
  }

  if (y.value > vh.value - h.value) {
    // 菜单的y位置 视口的高度 - 菜单高度
    posY = vh.value - h.value
  }

  return {
    posX,
    posY
  }
})

// 当菜单尺寸变化的时候 会执行该函数
function handleSizeChange(sizeInfo) {
  w.value = sizeInfo.width
  h.value = sizeInfo.height
}

function handleClick(item) {
  showMenu.value = false
  emit('select', item)
}

// js控制过度 如果我们使用样式来控制过度的话 会有一个问题 就是0过度到auto是没有任何动画的
function handleBeforeEnter(el) {
  el.style.height = 0
}

function handleEnter(el) {
  // 设置为auto只是为了获取元素的高度
  el.style.height = 'auto'
  // 有了高度之后 再去拿布局属性
  const h = el.clientHeight
  // 然后再将高度变为0 这样界面上不会有任何的表现 但是经过上面的两个步骤我们就拿到了它的具体的高度了
  el.style.height = 0
  // 我们在下一针的时候把高度设置为这个数字
  requestAnimationFrame(() => {
    requestAnimationFrame(() => {
      el.style.height = h + 'px'
      el.style.transition = '0.2s'
    })
  })
}

function handleAfterEnter(el) {
  el.style.transition = 'none'
}
</script>

<style scoped>
.context-menu {
  position: fixed;
  background: #eee;
  box-shadow:
    1px 1px 2px rgba(0, 0, 0, 0.2),
    1px 1px 5px rgba(0, 0, 0, 0.2);
  min-width: 100px;
  border-radius: 5px;
  font-size: 12px;
  color: #1d1d1f;
  line-height: 1.8;
  white-space: nowrap;
  overflow: hidden;
}
.menu-list {
  padding: 5px;
}
.menu-item {
  padding: 0 5px;
  border-radius: 4px;
  cursor: pointer;
  user-select: none;
}
.menu-item:hover {
  background: #3477d9;
  color: #fff;
}
</style>
