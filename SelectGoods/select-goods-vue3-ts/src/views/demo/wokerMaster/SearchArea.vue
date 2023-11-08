<script setup lang="ts">
defineOptions({
  name: 'SearchArea'
})
</script>

<template>
  <div class="search">
    <el-form class="search-form" label-position="top">
      <el-row :gutter="20">
        <el-col :span="NaN">
          <el-form-item>
            <template #label>
              <div class="search-form__lable">センター</div>
            </template>
            <el-select class="m-2" placeholder="Select">
              <el-option label="item.label" value="item.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="NaN">
          <el-form-item>
            <template #label>
              <div class="search-form__lable">作業者コード</div>
            </template>
            <el-select class="m-2" placeholder="Select">
              <el-option label="item.label" value="item.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="NaN">
          <el-form-item>
            <template #label>
              <div class="search-form__lable">日付: datetime</div>
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
        <el-col :span="NaN">
          <el-form-item>
            <template #label>
              <div class="search-form__lable">日付: datetimerange</div>
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
        <!-- <el-col :span="8"></el-col> -->
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col class="search-form__btn">
          <el-button>検索</el-button>
          <el-button>クリア</el-button>
          <el-button>CSVダウンロード</el-button>
          <el-button>CSVアップロード</el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<style scoped lang="scss">
.search {
  padding-top: 20px;

  .search-form {
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
</style>
