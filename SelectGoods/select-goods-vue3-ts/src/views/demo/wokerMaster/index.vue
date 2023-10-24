<script setup lang="ts">
import { getAssetsResource } from '@/utils/util.ts'
defineOptions({
  name: 'WorkerMaster'
})

const lightCollapseUrl = getAssetsResource('acr-menu-light.png')
// const darkCollapseUrl = getAssetsResource('acr-menu-dark.png')

const tableData = [
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  },
  {
    centerCode: 'DEMO01',
    wokerCode: '001',
    wokerName: '作業者００１'
  }
]
</script>

<template>
  <div class="worker-container">
    <el-card shadow="never" class="worker-search">
      <el-collapse class="worker-search__collapse" accordion>
        <el-collapse-item name="1">
          <!-- 内容区 -->
          <div class="worker-search__collapse__content">
            <el-form class="worker-search__collapse__form" label-position="top">
              <el-row :gutter="20">
                <el-col :span="4">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">センター</div>
                    </template>
                    <el-select class="m-2" placeholder="Select">
                      <el-option label="item.label" value="item.value" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">作業者コード</div>
                    </template>
                    <el-select class="m-2" placeholder="Select">
                      <el-option label="item.label" value="item.value" />
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">日付: datetime</div>
                    </template>
                    <el-date-picker
                      type="datetime"
                      placeholder="Pick a Date"
                      format="YYYY-MM-DD HH:mm:ss"
                      date-format="MMM DD, YYYY"
                      time-format="HH:mm"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-form-item>
                    <template #label>
                      <div class="form-item__lable">日付: datetimerange</div>
                    </template>
                    <el-date-picker
                      type="datetimerange"
                      start-placeholder="Start date"
                      end-placeholder="End date"
                      format="YYYY-MM-DD HH:mm:ss"
                      date-format="YYYY/MM/DD ddd"
                      time-format="A hh:mm:ss"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="8"></el-col>
              </el-row>
              <el-row style="margin-top: 20px">
                <el-col class="form-item__btn">
                  <el-button>検索</el-button>
                  <el-button>クリア</el-button>
                  <el-button>CSVダウンロード</el-button>
                  <el-button>CSVアップロード</el-button>
                </el-col>
              </el-row>
            </el-form>
          </div>
          <!-- 标题区 -->
          <template #title>
            <el-divider>
              <div class="collapse-icon">
                <img :src="lightCollapseUrl" alt="" />
              </div>
            </el-divider>
          </template>
        </el-collapse-item>
      </el-collapse>
    </el-card>
    <el-card shadow="never" class="worker-table">
      <el-row :gutter="40">
        <!-- 15 -->
        <el-col :span="24">
          <el-table :data="tableData">
            <el-table-column prop="centerCode" label="センタコード" />
            <el-table-column prop="wokerCode" label="作業者コード" />
            <el-table-column prop="wokerName" label="作業者名" />
          </el-table>
          <div class="worker-table__pagination">
            <el-pagination
              background
              layout="prev, pager, next"
              :total="10"
              :page-size="2"
            />
          </div>
        </el-col>
        <el-col v-if="false" :span="9" style="border-left: 1px solid #ddd">
          編集エリア
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped lang="scss">
.worker-container {
  height: 100%;
  font-size: 16px;
}

.worker-search {
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
    .collapse-icon {
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
    }

    &__form {
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

.worker-table {
  margin-top: 30px;

  &__pagination {
    display: flex;
    height: 60px;
    justify-content: center;
  }
}
</style>
