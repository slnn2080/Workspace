import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios"
import * as echarts from "echarts"
import store from "./store/index"

// 导入全局样式文件
import "@/assets/css/global.scss"

// 引入字体图标文件
import "@/assets/font/iconfont.css"

// 引入两套主题 并挂载到Vue原型上
import chalk from "./assets/theme/chalk"
import vintage from "./assets/theme/vintage"

// 引入websocketjs文件
import WebSocketService from "@/utils/websocket_client"

// 对服务端进行 websocket 连接
WebSocketService.Instance.connect()

Vue.config.productionTip = false

// 将 echarts 挂载到 vue 原型对象上
Vue.prototype.$echarts = echarts
Vue.prototype.$http = axios

// 将 websocket_client 对象 挂载到vue原型对象上
Vue.prototype.$socket = WebSocketService.Instance
Vue.prototype.$echartsTheme = {}
Vue.prototype.$echartsTheme.chalk = chalk
Vue.prototype.$echartsTheme.vintage = vintage


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
