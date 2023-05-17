import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios"
import * as echarts from "echarts"

// 导入全局样式文件
import "@/assets/css/global.scss"

// 引入字体图标文件
import "@/assets/font/iconfont.css"

Vue.config.productionTip = false

// 将 echarts 挂载到 vue 原型对象上
Vue.prototype.$echarts = echarts
Vue.prototype.$http = axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
