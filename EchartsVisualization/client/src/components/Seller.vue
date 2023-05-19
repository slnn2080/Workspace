<script>
import {defineComponent} from 'vue'
import {mapState} from "vuex";
import { getThemeValue } from '@/utils/theme_utils'

export default defineComponent({
  name: "Seller",
  data() {
    return {
      // chart数据
      detailData: [],
      // 图表实例
      chart: null,
      // 分页处理逻辑
      limit: {
        // 当前显示的页数 (会通过定时器不断地改变它的值)
        currentPage: 1,
        // 每页显示几条
        pageSize: 5,
        // 总页数(通过计算)
        totalPage: 0
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
    this.$socket.registerCallBack("sellerData", this.getData)
  },
  mounted() {
    // this.getData()
    // 向websocket发送消息
    this.$socket.send({
      action: "getData",
      socketType: "sellerData",
      chartName: "seller",
    })
    this.initChart()

    // 监视窗口改变的事件
    window.addEventListener("resize", this.screenAdapter)
    // 页面加载的时候主动调用下适配的方法
    this.screenAdapter()
  },
  beforeDestroy() {
    clearInterval(this.timer)
    window.removeEventListener("resize", this.screenAdapter)

    // 取消注册的回调函数
    this.$socket.unRegisterCallBack("trendData")
  },
  methods: {
    // window窗口尺寸发生变化时的回调 完成屏幕的适配
    screenAdapter() {
      // 修改图标都是通过 设置option - setOption

      // 1. 获取图表容器的宽度
      if(this.$refs.chart) {
        const chartWidth = this.$refs.chart.offsetWidth

        // 定义标题的大小 分成100份 每份为3.6
        const baseSize = chartWidth / 100 * 3.6

        // 修改图标都是通过 设置option - setOption
        // 创建跟适配相关的option
        const adapterOption = {
          title: {
            textStyle: {
              fontSize: baseSize
            },
          },
          tooltip: {
            axisPointer: {
              lineStyle: {
                width: baseSize,
              },
            }
          },
          series: [
            {
              barWidth: baseSize,
              itemStyle: {
                barBorderRadius: [0, baseSize / 2, baseSize / 2, 0]
              }
            }
          ]
        }
        this.chart.setOption(adapterOption)
        // 当窗口发生变化的时候 我们需要调用resize 图表才会改变
        this.chart.resize()
      }
    },
    // 初始化 echarts 实例对象
    initChart() {
      // 获取 echarts 实例对象
      this.chart = this.$echarts.init(this.$refs.chart, this.$echartsTheme[this.theme])

      // 我们将options配置拆分成如下的几个部分来管理
      // 1. 对图表的初始化配置
      const initOption = {
        // 标题相关配置
        title: {
          text: "▎商家销售统计",
          left: "5%",
          top: "3%",
          // textStyle: {
          //   fontSize: 30
          // }
        },
        grid: {
          // 位置相关配置
          top: "13%",
          left: "5%",
          right: "5%",
          bottom: "13%",
          // left等位置的基准是包含y轴文字的 可以有效防止内容溢出
          containLabel: true
        },
        xAxis: {
          type: "value"
        },
        yAxis: {
          type: "category",
        },
        // 提示框
        tooltip: {
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
        series: [
          {
            type: "bar",
            name: "商家销量统计",
            // 调整柱的宽度
            barWidth: 66,
            // 设置柱之间的间距
            // barCategoryGap: "30px",
            // 提示文件的展示
            label: {
              show: true,
              // 提示文件的位置
              position: "right",
              // 提示文字颜色
              textStyle: {
                color: "#fff"
              }
            },
            // 每一个bar属于 每一个条目使用如下配置
            itemStyle: {
              // 控制柱状条中一个柱条的四个圆角 左上 右上 右下 左下
              // 圆角是柱宽度的一半 柱宽度上面设置了 66 这里所以是33
              barBorderRadius: [0, 33, 33, 0],
              /*
               颜色渐变:
               new this.$echarts.graphic.linearGradient()
               内置的渐变颜色生成器, 它可以生成一个渐变色, 来控制每一个bar的颜色渐变

               1. 指明颜色渐变的方向
                 参数1
                 x1, y1, x2, y2 这是两个点 两个点可以组织成一条线 也就是方向
                 这两个点是相对值 正方向的4个点
                 0,0    1,0

                 0,1    1,1

               2. 指明不同百分比之下颜色的值
                 参数2: 数组对象
                 配置不同百分比之下颜色的具体值
              */
              color: new this.$echarts.graphic.LinearGradient(0, 0, 1, 0, [
                // 0%的状态
                { offset: 0, color: "#5052EE" },
                // 100%的状态
                { offset: 1, color: "#AB6EE5" }
              ], false)
            }
          }
        ]
      }
      // 设置基本数据
      this.chart.setOption(initOption)

      // 监听图表的事件 需要在初始化图表的时候完成
      // 鼠标移入 关闭定时器
      this.chart.on("mouseover", () => {
        clearInterval(this.timer)
      })
      // 鼠标移出 重新启动定时器
      this.chart.on("mouseout", () => {
        this.startInterval()
      })
    },

    // 获取服务器数据
    async getData(data) {
      // const { data: res } = await this.$http({
      //   url: "/api/seller"
      // })

      // 处理请求回来的数据 整理为 从小到大 的顺序
      // res.sort((a, b) => a.value - b.value)

      // this.detailData = res
      // 每5个元素为一页 我们计算总页数
      // 方式1:
      // this.limit.totalPage = Math.ceil(this.detailData.length / this.limit.pageSize)

      // 方式2:
      // this.totalPage = this.detailData.length % this.limit.pageSize === 0 ? this.detailData.length / this.limit.pageSize : this.detailData.length / 5 + 1
      // this.updateChart()

      // 开启定时器 刷新数据
      // this.startInterval()
      data.sort((a, b) => a.value - b.value)
      this.detailData = data
      this.limit.totalPage = Math.ceil(this.detailData.length / this.limit.pageSize)
      this.updateChart()
      this.startInterval()
    },
    // 更新图表
    updateChart() {

      /*
      如果当前页面为1 我们需要从detailData中获取 0 - 5 的数据 来进行展示
      slice() 会返回新的数组

      start = (pageNo - 1) * pageSize
      end = pageNo * pageSize
      */
      const start = (this.limit.currentPage - 1) * this.limit.pageSize
      const end = this.limit.currentPage * this.limit.pageSize
      const source = this.detailData.slice(start, end)
      /*
      数据格式:
      [{ "name": "商家1",  "value": 99}]

      处理数据组织成bar需要的数据格式
      1. 类目轴需要的数据 name数据
      2. series.data 中需要的数据 value数组
      */

      const categoryData = source.map(item => item.name)
      const seriesData = source.map(item => item.value)

      // 更新echarts表中的数据 该方法起到了更新图标的作用
      this.chart.setOption({
        yAxis: { data: categoryData },
        series: [
          { data: seriesData }
        ]
      })
    },
    // 在getData方法的最后调用
    startInterval() {
      if(this.timer) {
        clearInterval(this.timer)
      }

      this.timer = setInterval(() => {
        this.limit.currentPage++

        // 边界判断
        if(this.limit.currentPage > this.limit.totalPage) {
          this.limit.currentPage = 1
        }

        // 当页码发生变化后 我们调用updateChart方法来更新图表
        this.updateChart()
      }, 5000)
    }
  }
})
</script>

<!-- 商家销量统计的横向柱状图 -->
<template>
  <div class="common-container" :style="commonContainer">
    <!-- chart容器 -->
    <div class="common-chart" ref="chart"></div>
  </div>
</template>

<style scoped lang="scss">

</style>