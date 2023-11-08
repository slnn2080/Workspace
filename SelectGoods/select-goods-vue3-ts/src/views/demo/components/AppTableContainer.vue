<script setup lang="ts">
import { ref, reactive, onMounted, onUnmounted, computed } from 'vue'
import NodeListMutation from '@/utils/nodeListMutation'
import type { resultType } from '@/utils/nodeListMutation'

defineOptions({
  name: 'AppTableContainer'
})

// ----- props ------
type paginationFormType = {
  pageSize?: number
  pageNo?: number
  total?: number
}
type editAreaVisiableType = {
  edit: boolean
  add: boolean
}
type propsType = {
  hasPagination?: boolean
  captionText: string
  paginationForm?: paginationFormType
  editAreaVisiable?: editAreaVisiableType
}

const props = withDefaults(defineProps<propsType>(), {
  captionText: '',
  hasPagination: false,
  editAreaVisiable: () => ({
    edit: false,
    add: false
  }),
  paginationForm: () => ({
    pageNo: 1,
    pageSize: 5,
    total: 0
  })
})

// ----- variable -----
const observer = ref<NodeListMutation>()

const result = reactive<resultType>({
  height: 0,
  others: []
})

const pageForm = reactive<paginationFormType>({
  pageNo: 1,
  pageSize: 5,
  total: 0
})
Object.assign(pageForm, props.paginationForm)

const heights = computed(() => {
  const cardPadding = 40
  const pagination = 60
  return props.hasPagination
    ? {
        tableHeight: result.height - cardPadding - pagination,
        editHeight: result.height
      }
    : {
        tableHeight: result.height - cardPadding,
        editHeight: result.height
      }
})

// ----- computed -----
const dynamicColumn = computed(() => {
  const flag = Object.values(props.editAreaVisiable).some((item) => item)
  return flag ? { table: 16, area: 8 } : { table: 24, area: '' }
})

// ----- emits -----
const emit = defineEmits(['changeCurrentPage'])

// ----- methods -----
const collectElResult = (res: resultType): void => {
  Object.assign(result, res)
}

const changeCurrentPageHandler = () => {
  emit('changeCurrentPage', pageForm.pageNo)
}

// ----- lifecycle ------
onMounted(() => {
  observer.value = new NodeListMutation({
    cb: collectElResult
  })
})

onUnmounted(() => {
  observer.value?.destroy()
})
</script>

<template>
  <div class="table__caption target-table-caption">
    <span>{{ captionText }}</span>
  </div>
  <el-row :gutter="16" class="table__content">
    <el-col :span="dynamicColumn.table">
      <el-card shadow="never">
        <slot name="tableArea" :height="heights.tableHeight"></slot>
        <div v-if="hasPagination" class="table__pagination">
          <!-- prev, pager, next, jumper, -> , sizes, total -->
          <el-pagination
            v-model:current-page="pageForm.pageNo"
            v-model:page-size="pageForm.pageSize"
            :background="true"
            :small="true"
            layout="prev, pager, next"
            :total="pageForm.total"
            @current-change="changeCurrentPageHandler"
          />
        </div>
      </el-card>
    </el-col>
    <el-col :span="dynamicColumn.area">
      <el-card
        v-if="props.editAreaVisiable.edit"
        class="edit-slot__edit"
        :style="`height: ${heights.editHeight}px`"
        shadow="never"
      >
        <slot name="addArea"></slot>
      </el-card>
      <el-card
        v-if="props.editAreaVisiable.add"
        class="edit-slot__add"
        :style="`height: ${heights.editHeight}px`"
        shadow="never"
      >
        <el-scrollbar>
          <slot name="editArea"></slot>
        </el-scrollbar>
      </el-card>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">
.table {
  &__caption {
    margin: 32px 0px 16px;
  }

  &__pagination {
    display: flex;
    height: 60px;
    justify-content: center;
  }
}

.edit-slot {
  &__edit,
  &__add {
    :deep(.el-card__body) {
      height: calc(100% - 40px);
    }
  }
  &__edit {
  }
  &__add {
  }
}
</style>
