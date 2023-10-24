<script setup lang="ts">
import { useRouter } from 'vue-router'
import moment from 'moment'
import { onMounted, onUnmounted, ref } from 'vue'

defineOptions({
  name: 'DataHeader'
})

const router = useRouter()
const backHome = () => {
  router.push({ path: '/' })
}

// 存储当前的时间
let time = ref(moment().format('YYYY-MM-DD HH:mm:ss'))
let timer = ref(0)
// 在 onMounted 中开启定时器
onMounted(() => {
  timer.value = window.setInterval(() => {
    time.value = moment().format('YYYY-MM-DD HH:mm:ss')
  })
})
onUnmounted(() => {
  clearInterval(timer.value)
})
</script>

<template>
  <div class="header">
    <div class="header__left">
      <div class="lbtn" @click="backHome">首页</div>
    </div>
    <div class="header__center">
      <h1 class="title">智慧旅游可视化大数据展示平台</h1>
    </div>
    <div class="header__right">
      <div class="rbtn">统计报告</div>
      <div class="time">当前时间: {{ time }}</div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.header {
  height: 100%;
  display: flex;
  color: #fff;

  &__left {
    flex: 1;
    background: url(../images/dataScreen-header-left-bg.png) no-repeat;
    background-size: cover;

    display: flex;
    justify-content: end;

    .lbtn {
      width: 150px;
      height: 40px;
      text-align: center;
      line-height: 40px;
      background: url(../images/dataScreen-header-btn-bg-l.png) no-repeat;
      background-size: 100% 100%;
      color: #29fcff;
      font-size: 20px;
    }
  }
  &__center {
    flex: 2;

    .title {
      width: 100%;
      height: 74px;
      background: url(../images/dataScreen-header-center-bg.png) no-repeat;
      background-size: 100% 100%;
      text-align: center;
      line-height: 74px;
      color: #29fcff;
      font-size: 30px;
    }
  }
  &__right {
    flex: 1;
    display: flex;
    justify-content: space-between;
    background: url(../images/dataScreen-header-right-bg.png) no-repeat;
    background-size: 100% 100%;

    .rbtn {
      width: 150px;
      height: 40px;
      text-align: center;
      line-height: 40px;
      background: url(../images/dataScreen-header-btn-bg-r.png) no-repeat;
      background-size: 100% 100%;
      color: #29fcff;
      font-size: 20px;
    }

    .time {
      padding-right: 20px;
      line-height: 40px;
      color: #29fcff;
      font-size: 20px;
    }
  }
}
</style>
