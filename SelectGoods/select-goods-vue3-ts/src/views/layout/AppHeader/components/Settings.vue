<script setup lang="ts">
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import useSettingStore from '@/store/settingStore'
import useUserStore from '@/store/userStore'

defineOptions({
  name: 'Settings'
})

const router = useRouter()
const route = useRoute()

const settingStore = useSettingStore()
const userStore = useUserStore()

const predefineColors = ref([
  '#ff4500',
  '#ff8c00',
  '#ffd700',
  '#90ee90',
  '#00ced1',
  '#1e90ff',
  '#c71585',
  'rgba(255, 69, 0, 0.68)',
  'rgb(255, 120, 0)',
  'hsv(51, 100, 98)',
  'hsva(120, 40, 94, 0.5)',
  'hsl(181, 100%, 37%)',
  'hsla(209, 100%, 56%, 0.73)',
  '#c7158577'
])

const color = ref('')

const setColor = () => {
  const html = document.documentElement
  // 控制台 去看html标签中有主题色的名
  html.style.setProperty('--el-color-primary', color.value)
}

// 刷新按钮的回调
const refreshHandler = () => {
  settingStore.isRefreshed = !settingStore.isRefreshed
}
// 全屏按钮的回调
const fullScreenHandler = () => {
  // dom对象上的属性 判断是否是全屏状态, 是返回true, 不是返回null
  if (document.fullscreenElement) {
    document.exitFullscreen()
  } else {
    document.documentElement.requestFullscreen()
  }
}

// 退出登录的回调
const logoutHander = async () => {
  // 1. 发起 退出登录 请求, 告诉服务器本地登录获取到的token无效
  // 2. 删除 store 中关于用户的数据 清空掉
  await userStore.userLogout() // await 保证退出之后再跳转

  // 3. 路由跳转回登录页面, 并携带点击退出登录按钮时 所在的页面uri
  router.replace({
    path: '/login',
    query: { redirect: route.path }
  })
}

// 收集 切换暗黑模式 的布尔值
let dark = ref<boolean>(false)
const changeDark = () => {
  //获取HTML根节点
  let html = document.documentElement
  //判断HTML标签是否有类名dark
  dark.value ? (html.className = 'dark') : (html.className = '')
}
</script>

<template>
  <div class="settings__container">
    <el-button
      type="primary"
      icon="Refresh"
      circle
      @click="refreshHandler"
    ></el-button>
    <el-button
      type="primary"
      icon="FullScreen"
      circle
      @click="fullScreenHandler"
    ></el-button>
    <el-popover
      placement="bottom"
      title="主题设置"
      :width="300"
      trigger="click"
    >
      <!-- 表单元素 -->
      <el-form>
        <el-form-item label="主题颜色">
          <el-color-picker
            @change="setColor"
            v-model="color"
            size="small"
            show-alpha
            :predefine="predefineColors"
          />
        </el-form-item>
        <el-form-item label="暗黑模式">
          <el-switch
            @change="changeDark"
            v-model="dark"
            class="mt-2"
            style="margin-left: 24px"
            inline-prompt
            active-icon="MoonNight"
            inactive-icon="Sunny"
          />
        </el-form-item>
      </el-form>
      <!-- 外在体现的结构 -->
      <template #reference>
        <el-button type="primary" icon="Setting" circle></el-button>
      </template>
    </el-popover>
    <!-- 这里其实应该放 img 的 -->
    <el-button type="primary" icon="Avatar" circle></el-button>
    <!-- 退出登录的按钮 -->
    <div class="settings__dropdwon">
      <el-dropdown
        trigger="click"
        :popper-options="{
          modifiers: [{ name: 'computeStyles', options: { adaptive: false } }]
        }"
      >
        <!-- 下拉菜单的内容展示区 -->
        <span class="el-dropdown-link">
          <!-- title部分 -->
          {{ userStore.username }}
          <!-- title部分后面的图标 ↓ 箭头 -->
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
        <!-- 插槽是内容区 -->
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="logoutHander">
              退出登录
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<style scoped lang="scss">
.settings {
  &__container {
    padding: 0 20px;
    height: 100%;
    display: flex;
    align-items: center;
  }

  &__dropdwon {
    padding-left: 20px;
  }
}
</style>
