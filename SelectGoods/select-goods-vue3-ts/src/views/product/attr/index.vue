<script setup lang="ts">
import { watch, reactive, ref, nextTick, onBeforeUnmount } from 'vue'
import { ElMessage } from 'element-plus'
import SearchCategory from '@/components/SearchCategory/index.vue'
import useCategoryStore from '@/store/categoryStore.ts'

import { getList, saveOrUpdateAttr, deleteAttrApi } from '@/api/product/attr'
import type {
  listItemType,
  attrValueItemType
} from '@/api/product/attr/type.ts'

defineOptions({
  name: 'Attr'
})

const categoryStore = useCategoryStore()

// 表格列的数据
const tableHeaders = [
  { label: '属性名称', prop: 'attrName', align: 'center', width: '120px' },
  { label: '属性值名称', prop: 'attrVals', align: 'center', desc: 'attrList' }
]

// 表格数据
const tableData = reactive<listItemType[]>([])

// table组件和form切换的变量: true - table, false - form
let switchTableStructure = ref<boolean>(true)

// form: 定义新增属性的数据
type attrFormType = listItemType & {
  [_: string]: any
}
const attrForm = reactive<attrFormType>({
  // 3级分类所属的id
  categoryId: '',
  // 新增 和 修改的时候 都为3 因为我们就是对3级分类进行操作
  categoryLevel: 3,
  // 属性名
  attrName: '',
  // 属性值列表
  attrValueList: []
})

// 添加属性值: el-input 自动获焦 存储页面中 input实例的数组
const elInputInstances = reactive<any>([])

const resetAttrForm = () => {
  for (const key in attrForm) {
    if (key === 'attrValueList') {
      attrForm[key].length = 0
    } else if (key === 'categoryLevel') {
      attrForm[key] = 3
    } else {
      attrForm[key] = ''
    }
  }
}

// 监听 store 中 三级分类id 的变化
watch(
  () => categoryStore.category3Id,
  (n) => {
    // 1. 当 3级分类id 有变化之后 我们要先清空表格数据
    tableData.length = 0
    // 2. 确保 3级分类id 有了之后我们再发送请求
    if (n) {
      getTableList()
    }
  }
)

// 获取table列表数据的方法
const getTableList = async () => {
  const res = await getList(
    +categoryStore.category1Id,
    +categoryStore.category2Id,
    +categoryStore.category3Id
  )
  if (res.code === 200) {
    tableData.length = 0
    tableData.push(...res.data)
    // 丢失响应式: vuedeveloptools上 tabledata的后面不带 reactive 标识了
    // tableData = res.data
  }
}

// 添加属性按钮的回调
const handleAddAttr = () => {
  resetAttrForm()
  switchTableStructure.value = false
}

// 修改属性的回调
const handleUpdateAttr = (row: listItemType) => {
  switchTableStructure.value = false
  /*
  {
    "id": 19179,
    "createTime": null,
    "updateTime": null,
    "attrName": "12321",
    "categoryId": 62,
    "categoryLevel": 3,
    "attrValueList": [
      {
        "id": 175914,
        "createTime": null,
        "updateTime": null,
        "valueName": "2131",
        "attrId": 19179
      }
    ]
  }
  */
  // 将已有的属性对象 赋值给 attrForm 对象
  Object.assign(attrForm, JSON.parse(JSON.stringify(row)))
}

// form界面的取消按钮的回调
const handleCancel = () => {
  switchTableStructure.value = true
}

// 添加属性值按钮的回调
const addAttrValhandler = () => {
  // 点击 添加属性值 按钮的时候 向 attrForm.attrValueList 添加一个属性值对象
  const defaultAttr = {
    // 默认为空 后续通过 row v-model="row.valueName" 进来, 因为我们往表格中push的一个对象, row就是这个对象的引用
    valueName: '',

    // 切换 编辑模式 和 查看模式 的标识变量
    isEdited: true
  }

  attrForm.attrValueList.push(defaultAttr)

  // 获取最后的el-input组件让其聚焦, 也就是新追加的input也需要聚焦
  nextTick(() => {
    elInputInstances[attrForm.attrValueList.length - 1]?.focus()
  })
}

// 保存按钮回调
const saveAttrHandler = async () => {
  // 设置参数
  attrForm.categoryId = categoryStore.category3Id
  /*
    {
      attrName: "颜色",
      attrValueList: [{valueName: "黄色"}],
      categoryId: 61,
      categoryLevel: 3
    }
  */
  // 发起请求
  const res = await saveOrUpdateAttr(attrForm)
  if (res.code === 200) {
    ElMessage({
      type: 'success',
      message: '修改属性成功'
    })

    // 切换场景: 返回属性列表界面
    switchTableStructure.value = true

    // 请求最新列表
    await getTableList()
  } else {
    ElMessage({
      type: 'error',
      message: '修改属性失败'
    })
  }
}

// 删除属性按钮的回调
const deleteAttrHandler = async (row: listItemType) => {
  const res = await deleteAttrApi(row.id as number)
  if (res.code === 200) {
    ElMessage({
      type: 'success',
      message: '删除成功'
    })
    getTableList()
  } else {
    ElMessage({
      type: 'error',
      message: '删除失败'
    })
  }
}

// 属性值 失去焦点的回调
const changeShowMode = (row: attrValueItemType, index: number): void => {
  // 非法判断
  // 1. 属性值必须有值, 没值不能变成div 因为没值变成div div会没有高度 无法再次点击
  // 2. 当我们追加的是一个空值对象的时候, 将这个对象从attrValueList删掉
  // 3. 新增的属性值不能重复, 我们要判断新增属性值 valueName 在 attrValueList 中是否出现 并将这个对象从attrValueList删掉
  if (!row.valueName.trim()) {
    console.log('添加了空值')
    attrForm.attrValueList.splice(index, 1)
    return
  }
  // row对象本身也是数组中的一员, 我们判断的时候要刨除row本身
  const idRepeated = attrForm.attrValueList.find((item) => {
    // 引用值相等判断, 刨除row的情况
    if (item !== row) {
      return item.valueName === row.valueName
    }
  })
  if (idRepeated) {
    console.log('添加了重复值')
    attrForm.attrValueList.splice(index, 1)
    return
  }

  // 都没有问题后 我们再切换成div
  row.isEdited = false
}
const changeEditMode = (row: attrValueItemType, index: number): void => {
  row.isEdited = true

  // 响应式数据发生变化 获取更新后的dom
  nextTick(() => {
    elInputInstances[index]?.focus()
  })
}

// 生命周期: 当路由组件进行切换的时候, 将search仓库中的相关数据进行清空
onBeforeUnmount(() => {
  // 将 仓库中的数据 恢复到起始状态
  categoryStore.$reset()
})
</script>

<template>
  <div class="attr__container">
    <SearchCategory :switchTableStructure="switchTableStructure" />
    <el-card class="attr-list">
      <!-- 表格结构 -->
      <template v-if="switchTableStructure">
        <el-button
          icon="Plus"
          type="primary"
          :disabled="categoryStore.category3Id ? false : true"
          @click="handleAddAttr"
          >添加平台属性</el-button
        >
        <el-table class="attr-list__table" border :data="tableData">
          <!-- 序号列 -->
          <el-table-column
            type="index"
            width="80"
            align="center"
            label="序号"
          />
          <el-table-column
            v-for="(item, index) in tableHeaders"
            :key="index"
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
            :align="item.align"
          >
            <!-- 只有当 desc 标识为 attrList 的时候 为其单独渲染结构 -->
            <template v-if="item.desc === 'attrList'" #default="{ row }">
              <el-tag
                class="tag"
                v-for="attr in row.attrValueList"
                :key="attr.id"
                >{{ attr.valueName }}</el-tag
              >
            </template>
          </el-table-column>
          <!-- 操作列 -->
          <el-table-column label="操作" width="120">
            <template #default="{ row }">
              <el-button
                size="small"
                type="primary"
                icon="Edit"
                circle
                @click="handleUpdateAttr(row)"
              />
              <el-popconfirm
                hide-icon
                width="200"
                :title="`您确定要删除 ${row.attrName} 属性么`"
                @confirm="deleteAttrHandler(row)"
              >
                <template #reference>
                  <el-button size="small" type="primary" icon="Delete" circle />
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <template v-else>
        <div class="attr-edit">
          <el-form :inline="true">
            <el-form-item label="属性名称">
              <el-input
                v-model="attrForm.attrName"
                placeholder="请输入属性名称"
              />
            </el-form-item>
          </el-form>
          <div class="attr-edit__btn">
            <el-button
              type="primary"
              size="default"
              icon="Plus"
              :disabled="attrForm.attrName ? false : true"
              @click="addAttrValhandler"
            >
              添加属性值
            </el-button>
            <el-button type="primary" size="default" @click="handleCancel">
              取消
            </el-button>
          </div>
          <div class="attr-edit__list">
            <el-table border :data="attrForm.attrValueList">
              <el-table-column
                width="80"
                type="index"
                align="center"
                label="序号"
              ></el-table-column>
              <el-table-column label="属性值的名称">
                <template #default="{ row, $index }">
                  <!-- 编辑状态 -->
                  <el-input
                    :ref="(vc: any) => (elInputInstances[$index] = vc)"
                    v-if="row.isEdited"
                    v-model="row.valueName"
                    placeholder="请输入属性值名称"
                    @blur="changeShowMode(row, $index)"
                  />
                  <!-- 查看状态 -->
                  <div v-else @click="changeEditMode(row, $index)">
                    {{ row.valueName }}
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="属性值操作">
                <template #default="{ $index }">
                  <el-button
                    type="primary"
                    size="small"
                    circle
                    icon="Delete"
                    @click="attrForm.attrValueList.splice($index, 1)"
                  />
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="attr-edit__footer">
            <el-button
              type="primary"
              size="default"
              :disabled="attrForm.attrValueList.length > 0 ? false : true"
              @click="saveAttrHandler"
            >
              保存
            </el-button>
            <el-button type="primary" size="default" @click="handleCancel">
              取消
            </el-button>
          </div>
        </div>
      </template>
    </el-card>
  </div>
</template>

<style scoped lang="scss">
.attr {
  &__container {
    font-size: 16px;
  }

  &-search {
  }

  &-list {
    margin-top: 20px;

    &__table {
      margin-top: 20px;

      .tag {
        margin: 0px 5px;
        line-height: 1.5;
      }
    }
  }

  &-edit {
    &__list {
      margin-top: 20px;
    }
    &__footer {
      margin-top: 20px;
    }
  }
}
</style>
