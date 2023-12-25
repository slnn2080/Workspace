<script setup lang="ts"></script>

<template>
  <div class="circular">
    <el-card shadow="never">
      <!-- 标题区 -->
      <el-row>
        <el-col>
          <div class="circular__title">Title</div>
        </el-col>
      </el-row>
      <!-- echart -->
      <el-row>
        <el-col>
          <div ref="chartRef" class="chart"></div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped lang="scss">
.circular {
  font-size: 16px;

  &__title {
    background-color: red;
    font-size: 20px;
    font-weight: 900;
    text-align: center;
    padding: 10px 0px;
  }

  .chart {
    height: 300px;
    width: 100%;
  }
}
</style>
