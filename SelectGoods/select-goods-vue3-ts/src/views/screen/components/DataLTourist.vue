<script setup lang="ts">
import { onMounted, ref } from 'vue'
// 引入 echarts
import * as echarts from 'echarts'
// 引入 水球图 插件
import 'echarts-liquidfill'

defineOptions({
  name: 'DataLTourist'
})

const people = ref('216908')

// 1. 获取 echart 容器节点
const chart = ref()

// 2. 当组件挂载完毕后 初始化echarts
onMounted(() => {
  // 3. 获取 echarts 实例对象, 传入dom元素
  const chartInstance = echarts.init(chart.value)

  // 4. 设置实例的配置项
  chartInstance.setOption({
    // 系列: 决定展示什么样的图形图标
    series: {
      type: 'liquidFill',
      data: [0.6],
      // 水球图大小
      radius: '80%'
    }
  })
})
</script>

<template>
  <div class="tourist-container">
    <div class="tourist__title">
      <p>实时游客统计</p>
      <p>可预约总量 <span>999999</span> 人</p>
    </div>
    <div class="tourist__people">
      <span v-for="(item, index) of people" :key="index">{{ item }}</span>
    </div>
    <!-- echarts 容器 -->
    <div ref="chart" class="tourist__chart"></div>
  </div>
</template>

<style scoped lang="scss">
.tourist-container {
  background: url(../images/dataScreen-main-lb.png) no-repeat;
  background-size: 100% 100%;
  margin: 10px 0px 0px 20px;

  .tourist {
    &__title {
      color: #fff;

      p:nth-child(1) {
        font-size: 20px;
        position: relative;

        &::after {
          content: '';
          display: block;
          background: url(../images/dataScreen-title.png) no-repeat;
          width: 68px;
          height: 7px;
          position: absolute;
          top: 30px;
        }
      }

      p:nth-child(2) {
        text-align: right;
        padding: 10px 20px 0px 0px;
        font-size: 20px;

        span {
          color: goldenrod;
        }
      }
    }
    &__people {
      color: #29fcff;
      margin-top: 40px;
      display: flex;
      padding: 0 20px;
      span {
        flex: 1;
        background: url(../images/total.png) no-repeat;
        background-size: 100% 100%;
        height: 60px;
        text-align: center;
        line-height: 60px;
        font-size: 25px;
      }
    }
    &__chart {
      width: 100%;
      height: 226px;
      margin-top: 10px;
    }
  }
}
</style>
