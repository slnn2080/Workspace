<script>
import {defineComponent} from 'vue'
import chalk from "../assets/theme/chalk"
// 引入拼音映射文件
import { getProvinceMapInfo } from "@/utils/map_utils"

export default defineComponent({
  name: "Map",
  data() {
    return {
      chart: null,
      detailData: null,
      mapData: null,
      // 缓存用: 所获取的缓存数据
      selectedMapData: {},
      // 判断请求重复标识
      isRequesting: false
    }
  },
  // 注册回调函数
  created() {
    // getData方法做为注册的回调
    this.$socket.registerCallBack("mapData", this.getData)
  },
  mounted() {
    this.initChart()
    // this.getData()
    // 向websocket发送消息
    this.$socket.send({
      action: "getData",
      socketType: "mapData",
      chartName: "map",
    })

    window.addEventListener("resize", this.screenAdapter)
    this.screenAdapter()
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.screenAdapter)
    // 取消注册的回调函数
    this.$socket.unRegisterCallBack("trendData")
  },
  methods: {
    async initChart() {
      this.chart = this.$echarts.init(this.$refs.chart, chalk)

      // 获取中国地图的矢量数据
      const { data: res } = await this.$http({
        url: "/static/map/china.json"
      })
      // 将获取的地图数据进行注册
      this.$echarts.registerMap("china", res)

      const initOps = {
        // 设置标题
        title: {
          text: "▎商家分布",
          left: "5%",
          top: "3%",
          textStyle: {
            fontSize: 30
          }
        },
        geo: {
          map: "china",

          // 配置地图的位置
          top: "5%",
          bottom: "5%",

          // 控制地图中的颜色
          itemStyle: {
            // 区域的颜色
            areaColor: "#2172BF",
            // 区域的边框
            borderColor: "#333"
          }
        },
        // 配置图例
        legend: {
          left: "5%",
          bottom: "5%",
          // 改变图例的方向
          orient: "vertical",
        }
      }
      this.chart.setOption(initOps)


      // 监听点击事件
      this.chart.on("click", async param => {
        // 根据 param.name 获取中文对应的拼音和json文件的path
        const { key, path } = getProvinceMapInfo(param.name)
        /*
          {
            key: xinjiang,
            path: /static/map/province/xinjiang.json
          }
        */
        // 点击省市地图无效果
        if (!key) {
          return
        }

        // 缓存逻辑: 发送请求前先判断 当前点击的这个省份的数据是否在selectedMapData中
        // 只有当地图的矢量数据不存在selectedMapData中我们才发送ajax请求 注册地图数据
        if (!this.selectedMapData[key]) {
          // 根据 param.name 获取对应省份的数据 res为geo数据
          const { data: res } = await this.$http({
            url: path
          })

          // 缓存用: 将获取到的地图数据保存到data的属性上
          this.selectedMapData[key] = res

          // 将请求回来的数据注册到echarts中
          this.$echarts.registerMap(key, res)
        }

        // 更新地图数据
        const mapOps = {
          geo: {
            map: key
          }
        }
        this.chart.setOption(mapOps)
        this.chart.resize()
      })
    },
    async getData(data) {
      if (this.isRequesting) {
        return
      }
      // const { data: res } = await this.$http({
      //   url: "/api/map"
      // })
      //
      // this.isRequesting = false
      //
      // this.detailData = res
      // this.updateChart()
      this.detailData = data
      this.isRequesting = false
      this.updateChart()
    },
    updateChart() {
      // 处理散点图数据
      const scatterData = this.detailData.map(item => {
        // series数组下的每一个元素对象 一个类别的多个散点
        return {
          type: "effectScatter",
          // 商户的类别 黄金 等
          name: item.name,
          // 散点图数据结构要求是二维数据或者对象数据{name: "", value: []}
          data: item.children,
          // 让散点使用地图坐标 在地图上呈现散点
          coordinateSystem: "geo",
          // 设置涟漪效果
          rippleEffect: {
            scale: 3,
            number: 3,
            brushType: "stroke"
          },
        }
      })

      // 获取图例的数据
      const legendData = this.detailData.map(item => item.name)

      const dataOps = {
        legend: {
          data: legendData
        },
        series: scatterData
      }
      this.chart.setOption(dataOps)
    },
    screenAdapter() {
      const baseSize = this.$refs.chart.offsetWidth / 100 * 3.6
      const adapterOps = {
        title: {
          fontSize: baseSize
        },
        legend: {
          // 图例文字的大小
          textStyle: {
            fontSize: baseSize / 2
          },
          // 图例的宽度
          itemWidth: baseSize / 2,
          itemHeight: baseSize / 2,
          // 图例间隔
          itemGap: baseSize / 3,
        }
      }
      this.chart.setOption(adapterOps)
      this.chart.resize()
    },
    backChinaMap() {
       const ops = {
         geo: {
           map: "china"
         }
       }
       this.chart.setOption(ops)
    }
  }
})
</script>

<template>
  <div class="common-container" @dblclick="backChinaMap">
    <!-- chart容器 -->
    <div class="common-chart" ref="chart"></div>
  </div>
</template>

<style scoped lang="scss">

</style>