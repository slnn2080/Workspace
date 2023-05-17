<script>
import {defineComponent} from 'vue'
// 引入主题
import chalk from "../assets/theme/chalk"

export default defineComponent({
  name: "Stock",
  data() {
    return {
      chart: null,
      detailData: null,
      timer: null,
      // 分屏显示: 当前显示数据的页数
      currentIndex: 0
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
    clearInterval(this.timer)
  },
  methods: {
    initChart() {
      this.chart = this.$echarts.init(this.$refs.chart, chalk)
      const initOps = {
        // 标题相关配置
        title: {
          text: "▎库存和销量分析",
          left: "5%",
          top: "3%",
        },
      }
      this.chart.setOption(initOps)

      // 给图表绑定 鼠标移入移出的事件
      this.chart.on("mouseover", () => {
        clearInterval(this.timer)
      })

      this.chart.on("mouseout", () => {
        this.startInterval()
      })
    },
    async getData() {
      const { data: res } = await this.$http({
        url: "/api/stock"
      })
      this.detailData = res
      this.updateChart()

      // 有数据之后 就要调用定时器方法
      this.startInterval()
    },
    updateChart() {

      const start = this.currentIndex * 5
      const end = (this.currentIndex + 1) * 5

      // 获取前5条数据
      const partialData = this.detailData.slice(start, end)

      // 5个环形图的中心点坐标
      const centers = [
        ["18%", "40%"],
        ["50%", "40%"],
        ["82%", "40%"],
        ["34%", "75%"],
        ["66%", "75%"],
      ]

      const pieLinearColors = [
        ["#4FF778", "#0BA82C"],
        ["#E5DD45", "#E8811C"],
        ["#E8821C", "#E55455"],
        ["#5052EE", "#AB6EE5"],
        ["#23E5E5", "#2E728F"],
      ]

      // 5个饼图 每个饼图都是一个对象 组织每个饼图的series.data
      const pieData = partialData.map((item, index) => {
        return {
          type: "pie",
           // 环形配置 配置内外圆半径
          // radius: [110, 100],
          // 控制中心点坐标
          center: centers[index],

          // 取消鼠标移入到饼图时的动画效果
          hoverAnimation: false,

          // 提示线相关
          labelLine: {
            show: false
          },

          // 提示文字相关: 改变其位置到圆环中心
          label: {
            // center 饼图专属值
            position: "center",
            // 调节标签文本的颜色,
            color: pieLinearColors[index][0]
          },

          /*
            饼图数据包含两部分 一部分是库存 一部分是销量
            data中有两个对象 一个代表销量 一个代表库存
          */
          data: [
            // 饼图所需要的数据结构中可以没有name
            {
              // 销量的部分是需要提示文字的(也就是name的值)
              name: item.name + "\n\n" + item.sales,
              value: item.sales,
              itemStyle: {
                color: new this.$echarts.graphic.LinearGradient(0,1,0,0, [
                  {offset: 0, color: pieLinearColors[index][0]}, {offset: 1, color: pieLinearColors[index][1]}
                ])
              }
            },
            {
              // 第二个数组对象不要设置name 如果设置了name该值也会被当做这标签本文展示在圆环中
              // name: "haha",
              value: item.stock,
              itemStyle: {
                color: "#333843"
              }
            },
          ]
        }
      })
      const dataOps = {
        series: pieData
      }
      this.chart.setOption(dataOps)
    },
    startInterval() {
      if(this.timer) clearInterval(this.timer)
      this.timer = setInterval(() => {
        this.currentIndex++
        if (this.currentIndex > 1) {
          this.currentIndex = 0
        }
        // 更改完currentIndex之后 我们需要调用updateChart()
        this.updateChart()
       }, 5000)
    },
    screenAdapter() {
      const baseSize = this.$refs.chart.offsetWidth / 100 * 3.6

      // 内外圆半径
      const innerRadius = baseSize * 3
      const outerRadius = baseSize * 2.75

      console.log(baseSize, innerRadius, outerRadius)

      const adapterOps = {
        title: {
          textStyle: {
            fontSize: baseSize
          }
        },
        series: [
          // 5个对象就表示5个圆环
          { type: "pie", radius: [outerRadius, innerRadius], label: { fontSize: baseSize / 2 } },
          { type: "pie", radius: [outerRadius, innerRadius], label: { fontSize: baseSize / 2 } },
          { type: "pie", radius: [outerRadius, innerRadius], label: { fontSize: baseSize / 2 } },
          { type: "pie", radius: [outerRadius, innerRadius], label: { fontSize: baseSize / 2 } },
          { type: "pie", radius: [outerRadius, innerRadius], label: { fontSize: baseSize / 2 } },
        ],
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
  </div>
</template>

<style scoped lang="scss">

</style>