import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/index'
import { createPinia } from 'pinia'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 暗黑模式
import 'element-plus/theme-chalk/dark/css-vars.css'

import '@/styles/index.scss'

// svg相关
import 'virtual:svg-icons-register'
// 全局组件
import gloablComponent from './components/index'

// 引入 路由鉴权 的文件 permission
import './permission'

import sizeDirect from '@/directive/sizeDirect.ts'

// pinia 相关
const pinia = createPinia()

const app = createApp(App)
app.directive('v-resize', sizeDirect)
app.use(pinia)
app.use(router)
app.use(ElementPlus)
app.use(gloablComponent)
app.mount('#app')
