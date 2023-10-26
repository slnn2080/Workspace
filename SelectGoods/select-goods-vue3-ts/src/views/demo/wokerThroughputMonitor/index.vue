<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { getAssetsResource } from '@/utils/util.ts'

import * as echarts from 'echarts'
import chartOptions from './chartData.js'

defineOptions({
  name: 'WokerThroughputMonitor'
})

const lightCollapseUrl = getAssetsResource('acr-menu-light.png')

let datetime = reactive<Date[]>([new Date(), new Date()])

// 获取 chart dom
let chart = ref()

// 页面挂载的死后 初始化 echarts
onMounted(() => {
  const chartInstance = echarts.init(chart.value)
  chartInstance.setOption(chartOptions)
})
</script>

<template>
  <div class="throughput">
    <el-card shadow="never" class="throughput-search">
      <el-collapse class="throughput-search__collapse" accordion>
        <el-collapse-item name="1">
          <!-- 内容区 -->
          <div class="throughput-search__collapse__content">
            <h2 class="form-title">検索条件入力</h2>
            <el-form
              class="throughput-search__collapse__form"
              label-position="top"
            >
              <el-row :gutter="15">
                <el-col span="auto">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">センター</div>
                    </template>
                    <el-select class="m-2" placeholder="Select">
                      <el-option label="item.label" value="item.value" />
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col span="auto">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">日付from ~ to</div>
                    </template>
                    <el-date-picker
                      v-model="datetime"
                      type="datetimerange"
                      start-placeholder="Start Date"
                      end-placeholder="End Date"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="15">
                <el-col span="auto">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">作業分類１：</div>
                    </template>
                    <el-select class="m-2" placeholder="Select">
                      <el-option label="item.label" value="item.value" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col span="auto">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">作業場所２：</div>
                    </template>
                    <el-select class="m-2" placeholder="Select">
                      <el-option label="item.label" value="item.value" />
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row style="margin-top: 20px">
                <el-col class="form-item__btn">
                  <el-button>検索</el-button>
                  <el-button>クリア</el-button>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <!-- 标题区 -->
          <template #title>
            <el-divider>
              <div class="throughput-search__collapse__header">
                <img :src="lightCollapseUrl" alt="" />
              </div>
            </el-divider>
          </template>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <el-card shadow="never" class="throughput-echart">
      <div class="throughput-echart__wrapper">
        <div ref="chart" class="chart"></div>
      </div>
    </el-card>
  </div>
</template>

<style scoped lang="scss">
.throughput {
  height: 100%;
  font-size: 16px;
}

.throughput-search {
  // 减小card周围的padding
  :deep(.el-card__body) {
    --el-card-padding: 0px 20px;
  }

  &__collapse {
    // 去除折叠面板上下的边框
    border: none;

    // 去除折叠面板上下的边框
    :deep(.el-collapse-item__header) {
      border: none;

      // 去除折叠面板右侧的箭头
      .el-collapse-item__arrow {
        display: none;
      }
    }
    // 去除折叠面板上下的边框
    :deep(.el-collapse-item__wrap) {
      border: none;
    }

    // 折叠面板默认是 title区域在上方, 内容区域在下方 我们通过css控制下他们的排列方式
    :deep(.el-collapse-item) {
      display: flex;
      flex-direction: column-reverse;
    }

    &__header {
      width: 15px;
      height: 15px;

      img {
        width: 100%;
        height: auto;
        vertical-align: top;
      }
    }

    &__content {
      padding-top: 20px;

      .form-title {
        font-size: 14px;
        font-weight: bold;
      }
    }

    &__form {
      margin-top: 10px;

      .form-item {
        &__lable {
          font-size: 12px;
          color: #888;
        }

        &__btn {
          display: flex;
          justify-content: end;
        }
      }
    }
  }
}

.throughput-echart {
  margin-top: 20px;

  &__wrapper {
    border: 1px solid #222;

    .chart {
      width: 100%;
      height: 500px;
    }
  }
}
</style>
