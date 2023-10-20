<script setup lang="ts">
import { User, Lock } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import useLoginStore from '@/store/loginStore'
import { useRouter } from 'vue-router'
import { ElNotification } from 'element-plus'

defineOptions({
  name: 'Login'
})

// 获取 router
const router = useRouter()

// 收集表单数据
type loginFormType = {
  username: string
  password: string
}
const loginForm = reactive<loginFormType>({
  username: 'admin',
  password: '111111'
})

// 定义变量 控制 el-button 的loading功能
let loadingFlag = ref(false)

// 获取 loginStore
const loginStore = useLoginStore()

// 登录按钮 回调
const login = async () => {
  // 点击 登录 回调 展示 el-button 的loading效果
  loadingFlag.value = true

  // await: 也不全是拿异步的数据, 它本身最重要的是拿promise中成功的结果
  try {
    // 1. 通知 store 发起登录请求, 调用store中的方法 (action中的)
    await loginStore.login(loginForm)
    // 请求成功: 跳转到home
    router.push('/')
    // 展示提示信息
    ElNotification({
      type: 'success',
      message: '登录成功'
    })
  } catch (err) {
    // 请求失败: 弹出登录失败信息
    ElNotification({
      type: 'error',
      // (err as Error).message
      message: (err as Error).message
    })
  } finally {
    // 不管登录
    loadingFlag.value = false
  }
}
</script>

<template>
  <section class="login-wrapper">
    <el-row>
      <!-- 正常两列一列占一半, 当 <768 的时候 左侧为0, 右侧自己占一行 -->
      <el-col :span="12" :xs="0"></el-col>
      <el-col :span="12" :xs="24">
        <div class="login-form-wrapper">
          <div class="title">
            <h2>Hello</h2>
            <h3>欢迎来到 甄选平台</h3>
          </div>
          <el-form size="large">
            <!-- 每个表单项应该放在一个 表单容器中 -->
            <el-form-item>
              <el-input
                v-model="loginForm.username"
                :prefix-icon="User"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-input
                v-model="loginForm.password"
                type="password"
                :prefix-icon="Lock"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                class="form-btn"
                :loading="loadingFlag"
                type="primary"
                @click="login"
                >登录</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </section>
</template>

<style scoped lang="scss">
.login-wrapper {
  position: relative;
  width: 100vw;
  height: 100vh;
  background: url('@/assets/images/background.jpg') no-repeat;
  background-size: cover;

  .el-row {
    height: 100%;

    .login-form-wrapper {
      position: relative;
      top: 30%;
      width: 80%;
      background: url('@/assets/images/login_form.png') no-repeat;
      background-size: cover;
      padding: 40px;

      .title {
        h2 {
          color: #fff;
          font-size: 40px;
          margin-bottom: 20px;
        }

        h3 {
          font-size: 20px;
          margin-bottom: 20px;
          color: #fff;
        }
      }

      .form-btn {
        width: 100%;
      }
    }
  }
}
</style>
