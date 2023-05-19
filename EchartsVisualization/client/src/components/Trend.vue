<script>
import {defineComponent} from 'vue'
import {mapState} from "vuex";
import { getThemeValue } from '@/utils/theme_utils'


export default defineComponent({
  name: "Trend",
  data() {
    return {
      chart: null,
      detailData: null,
      timer: null,
      showChoice: false,
      choiceType: "map",
      // 标题的字体大小
      baseSize: 0
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
  computed: {
    commonContainer() {
      return {
        backgroundColor: getThemeValue(this.theme).itemColor
      }
    },
    selectTypes() {
      if (this.detailData.type) {
        /*
        标题下拉中有3个值 如果当前选择的是1 则下拉框里面应该只有2 3
        */
        return this.detailData.type.filter(item => item.key !== this.choiceType)
      } else {
        return []
      }
    },
    selectTile() {
      if (!this.detailData) {
        return ""
      } else {
        return this.detailData[this.choiceType].title
      }
    },
    // 设置给标题的样式
    titleStyle() {
      return {
        fontSize: this.baseSize + "px",
        color: getThemeValue(this.theme).titleColor
      }
    },
    ...mapState(["theme"])
  },
  // 注册回调函数
  created() {
    // getData方法做为注册的回调
    this.$socket.registerCallBack("trendData", this.getData)
  },
  mounted() {
    this.initChart()

    // 向websocket发送消息
    this.$socket.send({
      action: "getData",
      socketType: "trendData",
      chartName: "trend",
    })

    window.addEventListener("resize", this.screenAdapter)
    // 页面加载的时候 主动适配当前的屏幕尺寸
    this.screenAdapter()
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.screenAdapter)
    clearInterval(this.timer)

    // 取消注册的回调函数
    this.$socket.unRegisterCallBack("trendData")
  },
  methods: {
    async getData(data) {
      /*
        http版时的原逻辑
        const { data: res } = await this.$http({
          url: "/api/trend"
        })
        this.detailData = res
        this.updateChart()
      */
      this.detailData = data
      this.updateChart()
    },
    initChart() {
      this.chart = this.$echarts.init(this.$refs.chart, this.$echartsTheme[this.theme])
      const initOps = {
        tooltip: {
          trigger: "axis"
        },
        legend: {
          // 左边20像素
          // left: 20,
          top: "10%",
          // 设置图例的图标
          icon: "circle"
        },
        // 坐标轴位置相关配置
        grid: {
          top: "20%",
          left: "5%",
          right: "5%",
          bottom: "10%",
          // 将坐标轴上的文字 包含在grid配置内, 可以有效防止内容溢出
          containLabel: true
        },
        xAxis: {
          type: "category",
          boundaryGap: false
        },
        yAxis: {
          type: "value"
        }
      }
      this.chart.setOption(initOps)
    },
    updateChart() {
      // 准备 区域颜色部分的 颜色数组 渐变从半透明 - 全透明
      const opacityColors = [
        "rgba(11,168,44,0.5)",
        "rgba(44,110,225,0.5)",
        "rgba(22,242,217,0.5)",
        "rgba(250,105,0,0.5)",
        "rgba(121,0,250,0.5)",
      ]
      const noOpacityColors = [
        "rgba(11,168,44,0)",
        "rgba(44,110,225,0)",
        "rgba(22,242,217,0)",
        "rgba(250,105,0,0)",
        "rgba(121,0,250,0)",
      ]
      // 类目轴数据: x轴上需要
      const xAxisData = this.detailData.common.month

      /*
      处理数据 整理出series中需要的格式
        该折线图可以切换3组数组(对应3个图表) 我们先展示 地区销量趋势 - map

        地区销量趋势 折线图上有很 多组 线条 它们就对应着 series数组中有很多个{}
        我们要组织出 series下的数据结构
      */
      const regionSalesData = this.detailData[this.choiceType].data
      const regionSeriesData = regionSalesData.map((item, index) => {
        // 它是series数组中的一个对象元素
        return {
          type: "line",
          name: item.name,
          data: item.data,
          // 设置堆叠图: 设置相同的 stack 值
          stack: this.choiceType,
          // 设置颜色区域:
          areaStyle: {
            // 图形区域的起始位置
            // origin: 'start',
            // 区域颜色的渐变设置
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              // 0%的位置
              { offset: 0, color: opacityColors[index] },
              // 100%的位置
              { offset: 1, color: noOpacityColors[index] },
            ], false)
          },
        }
      })

      // 设置图例数据: 它需要和 上面regionSeriesData中name属性的值保持一致 用于筛选
      const legendData = regionSalesData.map(item => item.name)

      // 设置option
      const dataOps = {
        xAxis: {
          // 类目轴中的数据
          data: xAxisData
        },
        legend: {
          data: legendData,
        },
        // 地区销量趋势[可切换] 对应图标的数据
        series: regionSeriesData
      }
      this.chart.setOption(dataOps)
    },
    screenAdapter() {
      this.baseSize = this.$refs.chart.offsetWidth / 100 * 3.6
      const adapterOps = {
        // 控制图例的 宽度 和 高度
        legend: {
          itemWidth: this.baseSize,
          itemHeight: this.baseSize,
          itemGap: this.baseSize,
          textStyle: {
            fontSize: this.baseSize / 2
          }
        }
      }
      this.chart.setOption(adapterOps)
      // 自适应后需要手动调用 resize
      this.chart.resize()
    },
    handleSelect(key) {
      this.choiceType = key
      // 修改完后 我们要手动调用 更新图片的updateChart方法
      this.updateChart()
      // 点击后隐藏div
      this.showChoice = false
    }
  }
})
</script>

<template>
  <div class="common-container" :style="commonContainer">
    <!-- 标题部分结构 -->
    <div class="title">
      <span :style="titleStyle">▎{{ selectTile }}</span>
      <span
        class="iconfont title-icon"
        :style="titleStyle"
        @click="showChoice = !showChoice"
      >&#xe6eb;</span>
      <div v-if="showChoice" class="select-container">
        <div
          class="select-item"
          :style="titleStyle"
          v-for="item in selectTypes"
          :key="item.key"
          @click="handleSelect(item.key)"
        >{{item.text}}</div>
      </div>
    </div>
    <!-- chart容器 -->
    <div class="common-chart" ref="chart"></div>
  </div>
</template>

<style scoped lang="scss">
.title {
  position: absolute;
  left: 20px;
  top: 20px;
  z-index: 2;
  color: white;

  .title-icon {
    margin-left: 10px;
    cursor: pointer;
  }

  .select-item {
    margin-top: 5px;
  }

  .select-container {
    position: absolute;
    left: 12.5%;
    // background: #2B3340;
  }
}
</style>