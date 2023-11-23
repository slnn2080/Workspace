// 每个dom元素需要对应一个回调 WeakMap的键不会再被垃圾回收器考量 垃圾回收的时候不会考虑我们key的对象是一个对象 由于引用关闭不回收这个对象 当外面的dom消失之后 垃圾回收器看这个dom没有用了 就把它删除掉了 WeakMap中的key的位置的dom也就没有了
const map = new WeakMap()

// 全局 ob, 无论指令执行多少次, 我们使用的都是全局ob对象
const ob = new ResizeObserver((entries) => {
  for (const entry of entries) {
    /*
      entry:
        {
          borderBoxSize: 边框盒的尺寸 数组
          contentBoxSize: 内容盒的尺寸 数组
          contentRect: 内容区域的尺寸
          devicePixelContentBoxSize: DPR的尺寸
          target: 哪个元素发生了变化
        }
    */
    // 获取 dom 中给v-size-ob传入的回调 handler
    const handler = map.get(entry.target)

    if (handler) {
      const box = entry.borderBoxSize[0]
      handler({
        width: box.inlineSize,
        height: box.blockSize
      })
    }
  }
})

export default {
  // 元素挂载的时候运行它
  mounted(el, binding) {
    // 监听尺寸的变化
    ob.observe(el)
    // binding.value 是 v-size-ob="handleSizeChange" dom中传递的回调
    map.set(el, binding.value)
  },
  // 当元素卸载的时候运行它
  unmounted(el) {
    // 取消监听
    ob.unobserve(el)
  }
}
