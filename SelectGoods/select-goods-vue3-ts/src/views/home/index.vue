<script setup lang="ts">
import { onMounted } from 'vue'
import useUserStore from '@/store/userStore'

import { getTimeRange } from '@/utils/util.ts'

defineOptions({
  name: 'Home'
})

const userStore = useUserStore()

onMounted(() => {
  // 首页挂载完毕发起请求获取用户信息
  userStore.getUserInfo()
})
</script>

<template>
  <el-card>
    <div class="box">
      <img :src="userStore.avatar" alt="" class="avatar" />
      <div class="bottom">
        <h3 class="title">
          {{ getTimeRange() }}好呀, {{ userStore.username }}
        </h3>
        <p class="subtitle">硅谷甄选运营平台</p>
      </div>
    </div>
  </el-card>
  <div class="bottoms">
    <svg-icon name="welcome" width="600px" height="300px"></svg-icon>
  </div>
</template>

<style scoped lang="scss">
.box {
  display: flex;

  .avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
  }

  .bottom {
    margin-left: 20px;

    .title {
      font-size: 30px;
      font-weight: 900;
      margin-bottom: 30px;
    }

    .subtitle {
      font-style: italic;
      color: skyblue;
    }
  }
}
.bottoms {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}
</style>
