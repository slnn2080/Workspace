<script>
import {defineComponent} from 'vue'
// 引入主题
import chalk from "../assets/theme/chalk"
export default defineComponent({
  name: "Hot",
  data() {
    return {
      chart: null,
      detailData: null,
      currentIndex: 0,
      firstCategoryTitle: null,
      baseSize: null,
    }
  },
  mounted() {
    this.initChart()
    this.getData()
    window.addEventListener("resize", this.screenAdapter)
    this.screenAdapter()
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.screenAdapter)
  },
  methods: {
    // 初始化图表
    initChart() {
      this.chart = this.$echarts.init(this.$refs.chart, chalk)
      const initOps = {
        // 标题:
        title: {
          text: "▎热销商品销售金额占比统计",
          left: "5%",
          top: "3%",
          textStyle: {
            fontSize: 30
          }
        },
        // 饼图的位置控制
        series: [
          {
            type: "pie",
            top: "15%",
            // 默认不展示提示文字
            label: {
              show: false
            },
            // 类似设置hover: 当饼图在高亮情况下使用的样式
            emphasis: {
              label: {
                show: true
              },
              labelLine: {
                show: false
              }
            }
          }
        ],
        // 图例的位置控制
        legend: {
          top: "15%",
          icon: "circle"
        }
      }
      this.chart.setOption(initOps)
    },
    // 获取数据
    async getData() {
      const { data: res } = await this.$http({
        url: "/api/hot"
      })
      /*
      [{
          // 我们会通过按钮切换 一级分类
          name: "一级类别",
          children: [
            {
              name: "二级类别",
              value:
              children: [
                {
                  name: "二级类别",
                  value:
                }
              ]
            }
          ]
        }]
      */
      // 我们将3个级别的分类都交给detailData
      this.detailData = res

      // 处理数据 展示图标
      this.updateChart()
    },
    updateChart() {
      // 获取一级分类数据
      const firstCategory = this.detailData[this.currentIndex]

      // 一级类别的标题
      this.firstCategoryTitle = firstCategory.name

      // 饼图需要的数据: 一级类别 下的 用户展现饼图的二级类别的数据
      let secondCategory = firstCategory.children

      /*
      提示框内需要的数据: 二级类别 对应的 三级类别的数据
      let tertiaryCategory = []

      本意是遍历secondCategory数组 来将三级数据拿出来给提示框组件使用
      而提示框组件中我们会使用 formatter函数 它内部的参数 param

      param.data 就能拿到我们交给 series.data 中的数据 而它里面的children就是三级分类需要的数据
      */


      // 组织图例需要的数据 (图例的中的数据 要和饼图的数据的name值保持一致)
      let legendData = secondCategory.map(item => item.name)

      /*
      原来以为饼图要是想显示 只能是如下格式
      {
        name: "",
        value: ""
      }

      但是现在才知道 饼图需要的数据只要有 name 和 value 就可以 但也不限制多了其它的数据
      {
        name: "",
        value: "",
        children: []
      }

      所以 就不用如下的组织数据的方法了 本意是将 元素对象中的 children 拿掉 整理成饼图需要的数据结构
      但是发现不需要

      secondCategory = secondCategory.map((item, index) => {
        console.log(item)
        // 组织提示框内需要的数据 (这里不用这么做)
        tertiaryCategory.push({
          name: item.name,
          data: item.children
        })
        // 组织图例需要的数据
        legendData.push(item.name)

        // 组织饼图需要的数据
        return {
          name: item.name,
          value: item.value,
        }
      })
      */


      // 饼图只需要配饰series就可以了好像
      const updateOps = {
        // 配置平涂需要的需要
        series: [
          {
            data: secondCategory
          }
        ],
        // 配置图例
        legend: {
          data: legendData
        },
        // 提示框:
        tooltip: {
          show: true,
          trigger: "item",
          formatter: param => {
            console.log(param)
            // param.name 就是二级类别中的名字 比如 裤装
            // param.percent 是百分比

            /*
            我们通过 param.data 获取3集分类的数据
            const { data } = tertiaryCategory.find(item => param.name === item.name)
            */

            // 获取三级分类的数据
            const tertiaryCategory = param.data.children

            // 计算出所有三级分类数值的总和才能计算出百分比
            const total = tertiaryCategory.reduce((pre, curr) => pre + curr.value, 0)
            let template = ""

            tertiaryCategory.forEach(item => {
              template += `
                ${item.name}: ${ parseInt(item.value / total * 100) } % <br>
              `
            })

            return template
          }
        }
      }

      this.chart.setOption(updateOps)
    },

    // 箭头的点击事件: 对currentIndex来进行操作
    moveHandler(flag) {
      if (flag) {
        this.currentIndex++
        if(this.currentIndex > this.detailData.length - 1) {
          this.currentIndex = 0
        }
      } else {
        this.currentIndex--
        if(this.currentIndex < 0) {
          this.currentIndex = this.detailData.length - 1
        }
      }

      // 调用下 updateChart 因为图表需要使用最新的index 变更内容
      this.updateChart()

      // 技巧: (++this.currentIndex) % this.detailData.length
    },

    // 适配回调
    screenAdapter() {
      this.baseSize = this.$refs.chart.offsetWidth / 100 * 3.6
      const adapterOps = {
        title: {
          textStyle: {
            fontSize: this.baseSize
          }
        },
        series: [
          {
            // 控制饼图的大小
            radius: this.baseSize * 5,
            // 饼图的位置 是根据圆心点设置的
            center: ["50%", "53%"]
          }
        ],
        legend: {
          // 控制图例的宽度
          itemWidth: this.baseSize / 2,
          itemHeight: this.baseSize / 2,
          // 间隔
          itemGap: this.baseSize / 2,
          // 文字大小
          textStyle: {
            fontSize: this.baseSize / 2
          }
        }
      }
      this.chart.setOption(adapterOps)
      this.chart.resize()
    }
  }
})
</script>

<template>
  <div class="common-container">
    <!-- chart容器 -->
    <div class="common-chart" ref="chart"></div>
    <!-- 两个箭头 -->
    <span
      class="iconfont arrow arrow-left"
      :style="{fontSize: baseSize * 1.5 + 'px'}"
      @click="moveHandler(false)"
    >&#xe6ef;</span>
    <span
      class="iconfont arrow arrow-right"
      :style="{fontSize: baseSize * 1.5 + 'px'}"
      @click="moveHandler(true)"
    >&#xe6ed;</span>
    <h3
      class="first-category-title"
      :style="{fontSize: baseSize + 'px'}"
    >{{firstCategoryTitle}}</h3>
  </div>
</template>

<style scoped lang="scss">
.arrow {
  position: absolute;
  transform: translateY(-50%);
  font-size: 50px;
  color: #fff;
  cursor: pointer;
}

.arrow-left {
  left: 8%;
  top: 50%;
}
.arrow-right {
  right: 8%;
  top: 50%;
}
.first-category-title {
  position: absolute;
  bottom: 5%;
  right: 10%;
  color: #fff;
  font-size: 30px;
}
</style>