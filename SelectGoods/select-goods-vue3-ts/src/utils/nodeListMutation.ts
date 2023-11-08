type observerNodesType = Element[]

type observerType = ResizeObserver

type resultType = {
  height: number
  others: number[]
}

type gapType = {
  [_: string]: number
}

type optionsType = {
  nodeList?: observerNodesType
  cb: (res: resultType) => void
}

type fixElementsType = {
  [_: string]: number
}

class NodeResizeObserver {
  // 要监视的DOM元素对象
  observerNodes: observerNodesType = []

  // observer实例
  observer: observerType | null = null

  // 返回的结果
  result: resultType = {
    height: 0,
    others: []
  }

  // 间距
  gap: gapType = {
    level1: 8,
    level2: 16,
    level3: 20,
    level4: 32,
    level5: 48,
    level6: 64
  }

  // 阈值
  deviation: number = 1

  // 固定的DOM元素对象
  fixElements: fixElementsType = {
    // navBar的高度: 固定值
    navBarHeight: 64,
    // mt: 32 mb: 16 content: 16
    tableTitleHeight: 64
  }

  constructor(options: optionsType) {
    const { nodeList, cb } = options
    const observeTarget = document.querySelector('.target-expand-panel')
    if (observeTarget) {
      this.observerNodes.push(observeTarget)
    }
    if (nodeList) {
      const temp = nodeList.filter((node) => node !== observeTarget)
      this.observerNodes = [...this.observerNodes, ...temp]
    }

    this.calcHeight(cb)
  }

  calcHeight(cb: (res: resultType) => void) {
    const { navBarHeight, tableTitleHeight } = this.fixElements
    const documentHeight = document.documentElement.clientHeight

    this.observer = new ResizeObserver((entries) => {
      const targetsHeight: number[] = []
      entries.forEach((entry) => {
        targetsHeight.push(entry.contentRect.height)
      })

      let totalHeights = 0
      while (targetsHeight.length > 0) {
        const h = targetsHeight.shift()
        if (h) {
          totalHeights += h
        }
      }

      this.result.height =
        documentHeight -
        this.gap.level3 * 2 -
        navBarHeight -
        tableTitleHeight -
        this.deviation * 4 -
        totalHeights
      cb && cb(this.result)
    })

    this.observerNodes.forEach((node) => {
      this.observer?.observe(node)
    })
  }

  destroy() {
    this.observer?.disconnect()
  }

  getResults() {
    return this.result
  }
}

export default NodeResizeObserver
export type { resultType, observerType }
