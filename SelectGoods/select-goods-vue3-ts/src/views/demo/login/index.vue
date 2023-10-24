<script setup lang="ts">
defineOptions({
  name: 'Login'
})
</script>

<template>
  <div class="login-container">
    <el-container>
      <el-header>
        <div class="login-header">
          <span>GWES</span>
        </div>
      </el-header>
      <el-main>
        <el-row class="login-main">
          <el-col
            :span="6"
            :xs="16"
            :sm="16"
            :md="8"
            :lg="8"
            :xl="8"
            class="login-main__container"
          >
            <el-card>
              <template #header>
                <h3 class="login-main__title">ユーザーログイン</h3>
              </template>
              <el-form
                ref="formRef"
                class="login-main__form"
                label-position="top"
              >
                <el-form-item label="ユーザーID" prop="username">
                  <el-input autocomplete="off" />
                </el-form-item>
                <el-form-item label="パスワード" prop="password">
                  <el-input type="password" autocomplete="off" />
                </el-form-item>
                <el-form-item>
                  <div class="btn">
                    <a href="#">パスワードの変更はこちら</a
                    ><el-button>ログイン</el-button>
                  </div>
                </el-form-item>
              </el-form>
              <div class="end">@GROUND Inc.</div>
            </el-card>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped lang="scss">
.el-container {
  height: 100%;
}

.el-main {
  margin: 0;
  padding: 0;
}

.login-container {
  font-size: 16px;
  height: 100%;

  .login-header {
    height: 100%;
    display: flex;
    align-items: center;
    font-weight: bold;
  }

  .login-main {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #ddd;
    &__container {
      width: 350px;
    }
    &__form {
      background-color: #fff;
      padding: 0 20px 10px 20px;

      .btn {
        width: 100%;
        padding-top: 20px;
        display: flex;
        justify-content: space-between;

        a {
          font-size: 12px;
          text-decoration: underline;
          color: #888;
        }
      }
    }
    .end {
      width: 100%;
      text-align: center;
      font-size: 12px;
      color: #888;
    }
  }
}
</style>
