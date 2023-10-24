<script setup lang="ts">
defineOptions({
  name: 'Display'
})
</script>

<template>
  <div class="setting-container">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="never">
          <template #header>
            <div class="setting-container__header">
              <span>テーマ変更</span>
            </div>
          </template>
          <div class="setting-container__content">
            <div class="operator">
              <el-switch />
              <span style="padding-left: 10px">テーマ切替</span>
            </div>
            <div class="tips">
              <span
                >Tips: ライトモード・ダークモードにテーマを切り替えます。</span
              >
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="never">
          <template #header>
            <div class="setting-container__header">
              <span>言語切替</span>
            </div>
          </template>
          <div class="setting-container__content">
            <div class="operator">
              <el-select class="m-2" placeholder="Select">
                <el-option label="日本語" value="jp" />
                <el-option label="English" value="en" />
              </el-select>
            </div>
            <div class="tips">
              <span>Tips: 表示する言語を変更します。</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="scss">
.setting-container {
  height: 100%;
  background: #eee;
  padding: 20px;
  font-size: 16px;

  &__header {
    font-size: 18px;
  }

  &__content {
    height: 100px;
    .tips {
      margin-top: 20px;
      font-size: 12px;
      line-height: 1.5;

      span {
        // transform: scale(0.8);
      }
    }
  }
}
</style>
