<script setup lang="ts">
defineOptions({
  name: 'SwitchCenter'
})
</script>

<template>
  <div class="center-container">
    <el-row>
      <el-col>
        <el-card shadow="never">
          <el-row>
            <el-col :span="12">
              <div class="center-search">
                <h3 class="center-search__title">検索条件入力</h3>
                <h4 class="center-search__label">現作業センター</h4>
                <div class="center-search__content">DEMO01: デモセンター１</div>
              </div>
            </el-col>
            <el-col :span="12">
              <h4 class="center-switch__label">切替先作業センター*</h4>
              <div class="center-switch__btn">
                <div>
                  <el-select>
                    <el-option value="1" />
                  </el-select>
                </div>
                <div><el-button>切替</el-button></div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="scss">
.center-container {
  font-size: 16px;
  height: 100%;
  background: #eee;
  padding: 20px;
  font-size: 16px;
}

.center-search {
  &__title {
    font-size: 14px;
    font-weight: bold;
    padding-bottom: 10px;
  }

  &__label {
    font-size: 12px;
    padding-bottom: 10px;
    color: #888;
  }

  &__content {
    font-size: 14px;
  }
}
.center-switch {
  display: flex;

  &__label {
    font-size: 12px;
    padding-bottom: 10px;
    color: #888;
  }

  &__btn {
    display: flex;
    justify-content: space-between;
  }
}
</style>
