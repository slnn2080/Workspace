import { onMounted, onUnmounted, ref } from 'vue'

// 参数为 ContextMenu 元素
export default function (containerRef) {
  const showMenu = ref(false)
  const x = ref(0)
  const y = ref(0)

  const handleContextMenu = (e) => {
    // 阻止事件的默认行为 不然他就有浏览器的默认菜单了
    e.preventDefault()
    // 阻止冒泡 如果有嵌套关系的菜单的时候, 它不仅会触发嵌套菜单 还会触发父元素的菜单
    e.stopPropagation()

    // 展示菜单
    showMenu.value = true

    // 在打开菜单的时候 将鼠标位置交给x y
    x.value = e.clientX
    y.value = e.clientY
  }

  function closeMenu() {
    showMenu.value = false
  }

  onMounted(() => {
    const div = containerRef.value
    // 给菜单容器绑定 contextmenu 事件
    div.addEventListener('contextmenu', handleContextMenu)

    // 监听window的鼠标点击事件, 当触发的时候关闭菜单
    window.addEventListener('click', closeMenu, true)
    window.addEventListener('contextmenu', closeMenu, true)
    /*
      事件的触发是先捕获再冒泡
      我们先看下面的两个事件
      div.addEventListener('contextmenu', handleContextMenu)
      window.addEventListener('contextmenu', closeMenu)

      元素的contextmenu 和 window的contextmenu
      如果我们这么写会导致 底层元素先打开自己的菜单 往上冒泡到window又关闭了菜单

      而我们希望的是
      1. 先全部关闭
      2. 然后再打开一个菜单

      由于window上绑定了'contextmenu', closeMenu 所以 就能保证其它的所有菜单都已经关闭了 然后再打开自己的菜单
      所以我们要设置true, 让它在捕获阶段开始执行
    */
  })

  onUnmounted(() => {
    const div = containerRef.value
    div.removeEventListener('contextmenu', handleContextMenu)

    window.removeEventListener('click', closeMenu, true)
    window.removeEventListener('contextmenu', closeMenu, true)
  })

  return {
    showMenu,
    x,
    y
  }
}
