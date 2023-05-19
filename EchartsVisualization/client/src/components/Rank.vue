<script>
import {defineComponent} from 'vue'
import { mapState } from 'vuex'
import { getThemeValue } from '@/utils/theme_utils'
export default defineComponent({
  name: "Rank",
  data() {
    return {
      chart: null,
      detailData: null,
      dataZoom: {
        // 区域缩放的起点值
        startValue: 0,
        // 区域缩放的终点值
        endValue: 9
      },
      timer: null
    }
  },
  computed: {
    ...mapState(["theme"]),
    commonContainer() {
      return {
        backgroundColor: getThemeValue(this.theme).itemColor
      }
    }
  },
  watch: {
    theme () {
      console.log('主题切换了')
      this.chart.dispose() // 销毁当前的图表
      this.initChart() // 重新以最新的主题名称初始化图表对象
      this.screenAdapter() // 完成屏幕的适配
      this.updateChart() // 更新图表的展示
    }
  },
  // 注册回调函数
  created() {
    // getData方法做为注册的回调
    this.$socket.registerCallBack("rankData", this.getData)
  },
  mounted() {
    this.initChart()
    // this.getData()
    // 向websocket发送消息
    this.$socket.send({
      action: "getData",
      socketType: "rankData",
      chartName: "rank",
    })
    window.addEventListener("resize", this.screenAdapter)
    this.screenAdapter()
  },
  beforeDestroy() {
    clearInterval(this.timer)
    window.removeEventListener("resize", this.screenAdapter)

    // 取消注册的回调函数
    this.$socket.unRegisterCallBack("trendData")
  },
  methods: {
    initChart() {
      this.chart = this.$echarts.init(this.$refs.chart, this.$echartsTheme[this.theme])
      const initOps = {
        // 标题相关
        title: {
          text: "▎地区销售排行",
          left: "5%",
          top: "3%",
          // textStyle: {
          //   fontSize: 30
          // }
        },
        // 坐标轴相关
        grid: {
          // 位置相关配置
          top: "20%",
          left: "5%",
          right: "5%",
          bottom: "13%",
          // left等位置的基准是包含y轴文字的 可以有效防止内容溢出
          containLabel: true
        },
        // 提示框
        tooltip: {
          show: true,
          // 移入到坐标轴的时候显示提示框
          trigger: "axis",
          // 鼠标移入到坐标轴时展示的样式
          axisPointer: {
            // hover上去的时候是线条
            type: "line",
            // 线条的样式
            lineStyle: {
              width: 66,
              color: "#2D3443"
            },
            // 调整提示框的层级 将它调整为0
            z: 0
          }
        },
        xAxis: {
          type: "category"
        },
        yAxis: {
          type: "value"
        },
        series: [
          {
            type: "bar",
          }
        ]
      }
      this.chart.setOption(initOps)

      // 给图表绑定移入 移出事件 停止定时器 和 开启定时器
      this.chart.on("mouseover", () => {
        clearInterval(this.timer)
      })
      this.chart.on("mouseout", () => {
        this.startInterval()
      })
    },
    // 获取数据
    async getData(data) {
      // const { data: res } = await this.$http({
      //   url: "/api/rank"
      // })
      // 对返回的数据进行排序
      // res.sort((a, b) => b.value - a.value)
      // this.detailData = res
      /*
        [
          { name: "广东", value: 230 },
          { name: "福建", value: 213 },
        ]
      */
      // this.updateChart()

      // 在获取数据之后我们再开始bar的平移效果
      // this.startInterval()
      data.sort((a, b) => b.value - a.value)
      this.detailData = data
      this.updateChart()
      this.startInterval()
    },
    // 处理bar的平移效果的回调
    startInterval() {
      if(this.timer) clearInterval(this.timer)
      this.timer = setInterval(() => {
        this.dataZoom.startValue++
        this.dataZoom.endValue++

        // 边界判断
        if (this.dataZoom.endValue > this.detailData.length - 1) {
          // 设置为初始值
          this.dataZoom.startValue = 0
          this.dataZoom.endValue = 9
        }

        // 修改数据后 我们要修改图表让它发生变化
        this.updateChart()
      }, 5000)
    },
    updateChart() {
      // name组成的数组 做为 类目轴 的数据
      const xAxisData = this.detailData.map(item => item.name)
      // value组成的数组 做为 series.data 的数据
      const seriesData = this.detailData.map(item => item.value)

      // 追加数据到chart
      const dataOps = {
        xAxis: {
          data: xAxisData
        },
        // 配置数据缩放 dataZoom
        dataZoom: {
          // 展示缩放器
          show: false,
          // 在20条数据中展示 0 - 9, 10条数据
          startValue: this.dataZoom.startValue,
          endValue: this.dataZoom.endValue
        },
        series: [
          {
            data: seriesData,
            // 控制每个bar的颜色
            itemStyle: {
              color: param => {
                /*
                param: {
                  borderColor:
                  color:
                  data:
                  value:
                  dataIndex:
                  name:
                }
                */
                // 不同阶段的数值呈现不同的颜色
                const colors = [
                  ["#a8930b", "#4fdef7"],
                  ["#2E72BF", "#23E5E5"],
                  ["#5052EE", "#23E5E5"],
                ]

                let targetColors = null
                if (param.value > 300) {
                  targetColors = colors[0]

                // 相当于 value > 200 && value <= 300
                } else if (param.value > 200) {
                  targetColors = colors[1]
                } else {
                  targetColors = colors[2]
                }

                return new this.$echarts.graphic.LinearGradient(0,0,0,1, [
                  { offset: 0, color: targetColors[0] },
                  { offset: 1, color: targetColors[1] }
                ],false)
              }
            }
          }
        ]
      }
      this.chart.setOption(dataOps)
    },
    screenAdapter() {
      const baseSize = this.$refs.chart.offsetWidth / 100 * 3.6
      const adapterOps = {
        title: {
          textStyle: {
            fontSize: baseSize
          },
        },
        series: [
          {
            barWidth: baseSize,
            itemStyle: {
              barBorderRadius: [baseSize / 2, baseSize / 2, 0, 0],
            }
          }
        ]
      }
      this.chart.setOption(adapterOps)
      this.chart.resize()
    }
  }
})
</script>

<template>
  <div class="common-container" :style="commonContainer">
    <!-- chart容器 -->
    <div class="common-chart" ref="chart"></div>
  </div>
</template>

<style scoped lang="scss">

</style>