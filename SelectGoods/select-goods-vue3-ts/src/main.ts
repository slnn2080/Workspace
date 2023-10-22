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

// pinia 相关
const pinia = createPinia()

createApp(App)
  .use(router)
  .use(pinia)
  .use(ElementPlus)
  .use(gloablComponent)
  .mount('#app')
