<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { ElMessage } from 'element-plus'
import type {
  spuItemType,
  spuImageItem,
  attrItem,
  spuSaleAttrItem,
  spuSaleAttrValue
} from '@/api/product/spu/type.ts'
import type { trademarkItem } from '@/api/product/trademark/type.ts'
import type { UploadProps } from 'element-plus'

import {
  getSpuTrademarkListApi,
  getImageListBySpuIdApi,
  getSaleAttrSelectedListBySpuIdApi,
  getSaleAttrListApi,
  saveOrUpdateSpuApi
} from '@/api/product/spu'

defineOptions({
  name: 'SpuForm'
})

// 控制 el-upload 点击图片后展示图片的对话框 控制变量
const picDialogVisible = ref<boolean>(false)
const picDialogImageUrl = ref<string>('')

type echoSpuFormType = {
  trademarkList: trademarkItem[]
  imageList: spuImageItem[]
  saleAttrSelectedList: spuSaleAttrItem[]
  saleAttrList: attrItem[]
  supParams: spuItemType
  unAttrIdAndName: string
}
// 回显spu表单数据
const echoSpuForm = reactive<echoSpuFormType>({
  // 全部的品牌数据: spu品牌的下拉菜单用 收集字段 tmId 用
  trademarkList: [],
  // 照片墙的数据
  imageList: [],
  // 销售属性 - 一览表的数据 (已选)
  saleAttrSelectedList: [],
  // 获取销售属性 - 下拉菜单的数据 (全部, 应为 全部 - 已选)
  saleAttrList: [],
  // 销售属性 下拉列表: 收集还未选择的销售id和属性值名
  unAttrIdAndName: '',
  // 用于存储父组件传递过来的 row (已有的spu数据), 也是提交请求时的参数
  supParams: {
    // id字段: 新增的时候不需要, 修改的时候需要
    // 3级分类id: 给哪个三级分类追加一个spu - 已有row中携带
    category3Id: '',
    // spu名 - 已有row中携带
    spuName: '',
    // spu描述 - 已有row中携带
    description: '',
    // spu品牌id, 表示该spu属于哪个品牌 (如: oppo 下的 s1) - 通过 spu品牌下拉列表收集
    tmId: '',
    // 照片墙数据: 通过请求获取
    spuImageList: [],
    // 销售属性:
    spuSaleAttrList: []
  }
})

enum SCENE {
  TABLE,
  SPU,
  SKU
}
type emitsType = {
  (e: 'update:switchStructure', scene: SCENE): void
}
const emit = defineEmits<emitsType>()

const cancelHandler = () => {
  // 1. 通知父组件 切换为 table 场景
  emit('update:switchStructure', SCENE.TABLE)
}

// 对外暴露: 请求表单数据的方法
const getSpuFormData = async (spuData: spuItemType) => {
  // 将父组件传递过来的 row 存储起来
  echoSpuForm.supParams = spuData

  // 获取全部的品牌数据: spu品牌的下拉菜单用
  const { data: spuTrademarkListRes } = await getSpuTrademarkListApi()
  // console.log('spuTrademarkListRes', spuTrademarkListRes)
  echoSpuForm.trademarkList = spuTrademarkListRes

  // 获取照片墙的数据
  const { data: imageRes } = await getImageListBySpuIdApi(spuData.id as number)
  // console.log('imageRes', imageRes)
  // 加工下 imageRes 使用upload组件展示数据需要name和url字段
  const imageResWithUploadList = imageRes.map((item) => {
    return {
      name: item.imgName,
      url: item.imgUrl
    }
  })
  echoSpuForm.imageList = imageResWithUploadList

  // 获取销售属性 - 一览表的数据 (已选)
  const { data: saleAttrSelectedListRes } =
    await getSaleAttrSelectedListBySpuIdApi(spuData.id as number)
  // console.log('saleAttrSelectedListRes', saleAttrSelectedListRes)
  echoSpuForm.saleAttrSelectedList = saleAttrSelectedListRes

  // 获取销售属性 - 下拉菜单的数据 (全部, 应为 全部 - 已选)
  const { data: saleAttrListRes } = await getSaleAttrListApi()
  // console.log('saleAttrListRes', saleAttrListRes)
  echoSpuForm.saleAttrList = saleAttrListRes
}

// el-upload组件 点击已上传图片的回调 会注入当前点击的图片对象
// uploadFile: {name: , url: }
const picturePreviewHandler: UploadProps['onPreview'] = (uploadFile) => {
  // 1. 打开对话框
  picDialogVisible.value = true
  // 2. 将点击图片的url交给对话框里面的img的src
  picDialogImageUrl.value = uploadFile.url as string
}
// 删除已上传的图片的回调
const pictureRemoveHandler: UploadProps['onRemove'] = () => {}
// 上传文件之前的回调: 约束文件的大小和类型
const pictureBeforeUploadHandler: UploadProps['beforeUpload'] = (rawFile) => {
  // rawFile: File对象 { size: 字节, type: , name: }

  // 要求: 上传文件的格式为 png|jpg|gif 4M
  const imgTypes = ['image/png', 'image/jpg', 'image/gif']
  // return false 中止上传
  if (!imgTypes.includes(rawFile.type)) {
    ElMessage({
      type: 'error',
      message: '上传的文件必须为 png jpg gif'
    })
    return false
  }

  // 限制文件大小
  if (rawFile.size / 1024 / 1024 > 4) {
    ElMessage({
      type: 'error',
      message: '上传的文件必须在4mb以内'
    })
    return false
  }
}

// 删除销售属性的回调
const deleteAttrHandler = (index: number): void => {
  echoSpuForm.saleAttrSelectedList.splice(index, 1)
}

// 添加销售属性
const addAttrHandler = (): void => {
  // 1. 当我们点击 添加销售属性 按钮后, 我们要组织一个对象, 然后push到echoSpuForm.saleAttrSelectedList 数组中, 这样一览表就会追加一条记录
  /*
    记录格式为:
    type spuSaleAttrItem = {
      // 销售属性的id: 下拉菜单中收集
      baseSaleAttrId: number

      // 销售属性名字: 下拉菜单中收集
      saleAttrName: string

      // 属性值
      spuSaleAttrValueList: spuSaleAttrValue[]
    }
  */

  // 2. 销售属性 unAttrIdAndName 它是下拉列表双向绑定的值
  const [baseSaleAttrId, saleAttrName] = echoSpuForm.unAttrIdAndName.split(':')

  // 销售属性对象
  const newSaleAttr = {
    baseSaleAttrId,
    saleAttrName,
    spuSaleAttrValueList: []
  }

  // 追加到一览表中
  echoSpuForm.saleAttrSelectedList.push(newSaleAttr)
  // 清空 销售属性 下拉列表中 的数据
  echoSpuForm.unAttrIdAndName = ''
}

// 计算属性
// 计算出当前spu未选择的销售属性: spu销售属性 - 下拉菜单用
const unSelectSaleAttrList = computed(() => {
  // 类似双重for遍历

  // 1. 过滤 全部属性 的数组
  return echoSpuForm.saleAttrList.filter((item) => {
    // 2. every遍历 已有属性 的数组, 看看
    return echoSpuForm.saleAttrSelectedList.every((val) => {
      return val.saleAttrName !== item.name
    })
  })
})

// 属性一览表中添加属性值按钮的回调
/*
  正常展示 button
  <el-button @click="changeToEditMode"></el-button>
  <el-input v-if="row.switchStructure"/>

  当我们点击 button 按钮的时候, 往row中添加一个 switchStructure 属性
*/
const changeToEditMode = (row: spuSaleAttrItem) => {
  // 展示input组件
  row.switchStructure = true
  // 往 row 中追加 销售属性值(tag) 的字段, 我们收集到 row 里面
  row.saleAttrValue = ''
}
// 输入框失去焦点的回调
const changeToReviewMode = (row: spuSaleAttrItem) => {
  /*
    当 input 失去焦点的时候 我们要整理数据 push 到 spuSaleAttrValueList 数组中

    spuSaleAttrValueList对象的格式为
    {
      saleAttrValueName: string,
      baseSaleAttrId: number
    }
  */
  // 解构
  const { baseSaleAttrId, saleAttrValue } = row
  const newAttrValue: spuSaleAttrValue = {
    baseSaleAttrId,
    saleAttrValueName: saleAttrValue as string
  }

  // 非法情况1: 收集属性值的名字为空 不行
  if (!saleAttrValue?.trim()) {
    ElMessage({
      type: 'error',
      message: '属性值不能为空'
    })
    return
  }
  // 非法情况2: 收集属性值的名字不能重复
  const repeat = row.spuSaleAttrValueList.find((item) => {
    return item.saleAttrValueName === saleAttrValue
  })
  if (repeat) {
    ElMessage({
      type: 'error',
      message: '属性值不能重复'
    })
    return
  }
  // 非法校验通过后 再push到spuSaleAttrValueList
  row.spuSaleAttrValueList.push(newAttrValue)

  // 切换为查看模式
  row.switchStructure = false
}

// 保存按钮的回调
const saveHandler = async (): Promise<void> => {
  // 1. 整理收集到的数据 整理出 params 对象
  // 整理照片墙的数据
  echoSpuForm.supParams.spuImageList = echoSpuForm.imageList.map(
    (item: any) => {
      return {
        imgName: item.name,
        // 已有的图片使用 item.url (服务器返回的存储于服务器的图片的url), 新增的话(item为upload组件处理过的对象)需要使用 item.response.data 中 http的图片url
        /*
      {
        "name": "pic01.png",
        "percentage": 100,
        "status": "success",
        "size": 524446,
        "raw": {
            "uid": 1699188569074
        },
        "uid": 1699188569074,
        "url": "blob:http://localhost:5173/a61384a5-4a4a-49c3-bd9d-1cabd38ba9ea",
        "response": {
          "code": 200,
          "message": "成功",
          "data": "http://139.198.127.41:9000/sph/20231105/pic01.png",
          "ok": true
        }
      }
      */
        imgUrl: (item.response && item.response?.data) || item.url
      }
    }
  )
  // 整理 spuSaleAttrList 销售属性数据
  echoSpuForm.supParams.spuSaleAttrList = echoSpuForm.saleAttrSelectedList

  // 2. 发起请求: 添加 / 修改
  const res = await saveOrUpdateSpuApi(echoSpuForm.supParams)
  console.log(res)
  // 3. 请求后的处理
  if (res.code === 200) {
    ElMessage({
      type: 'success',
      message: '更新成功'
    })

    // 更新成功后, 切换表格组件
    emit('update:switchStructure', SCENE.TABLE)
  } else {
    ElMessage({
      type: 'error',
      message: '更新失败'
    })
  }
}

// 添加一个新的spu初始化请求方法
const initSpuFormData = async (category3Id: number | string) => {
  console.log(initSpuFormData)
  // 1. 清除表单数据
  Object.assign(echoSpuForm.supParams, {
    // 起到标识作用的id也要清空
    id: '',
    category3Id: '',
    spuName: '',
    description: '',
    tmId: '',
    spuImageList: [],
    spuSaleAttrList: []
  })
  echoSpuForm.trademarkList = []
  echoSpuForm.imageList = []
  echoSpuForm.saleAttrList = []
  echoSpuForm.saleAttrSelectedList = []
  echoSpuForm.unAttrIdAndName = ''

  // 2. 存储 3级分类id
  echoSpuForm.supParams.category3Id = category3Id

  // 3. 发起请求
  // 获取全部的品牌数据: spu品牌的下拉菜单用
  const { data: spuTrademarkListRes } = await getSpuTrademarkListApi()
  echoSpuForm.trademarkList = spuTrademarkListRes

  // 获取销售属性 - 下拉菜单的数据 (全部, 应为 全部 - 已选)
  const { data: saleAttrListRes } = await getSaleAttrListApi()
  echoSpuForm.saleAttrList = saleAttrListRes
}

defineExpose({
  getSpuFormData,
  initSpuFormData
})
</script>

<template>
  <div class="spu-form">
    <el-form label-width="100">
      <el-form-item label="SPU名称">
        <!-- 收集 spuName -->
        <el-input
          v-model="echoSpuForm.supParams.spuName"
          placeholder="请你输入SPU名称"
        ></el-input>
      </el-form-item>
      <el-form-item label="SPU品牌">
        <!-- 收集 spu品牌 -->
        <el-select v-model="echoSpuForm.supParams.tmId">
          <el-option
            v-for="item in echoSpuForm.trademarkList"
            :key="item.id"
            :label="item.tmName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="SPU描述">
        <el-input
          v-model="echoSpuForm.supParams.description"
          type="textarea"
          placeholder="请你输入SPU描述"
        ></el-input>
      </el-form-item>
      <el-form-item label="SPU照片">
        <el-upload
          v-model:file-list="echoSpuForm.imageList"
          action="/api/admin/product/fileUpload"
          list-type="picture-card"
          :on-preview="picturePreviewHandler"
          :on-remove="pictureRemoveHandler"
          :before-upload="pictureBeforeUploadHandler"
        >
          <el-icon><Plus /></el-icon>
        </el-upload>

        <el-dialog v-model="picDialogVisible">
          <img w-full :src="picDialogImageUrl" alt="Preview Image" />
        </el-dialog>
      </el-form-item>
      <el-form-item label="SPU销售属性">
        <!-- 展示销售属性的下拉菜单 -->
        <el-select
          v-model="echoSpuForm.unAttrIdAndName"
          :placeholder="
            unSelectSaleAttrList.length
              ? `还未选择的有 ${unSelectSaleAttrList.length} 个`
              : `无`
          "
          :popper-options="{
            modifiers: [{ name: 'computeStyles', options: { adaptive: false } }]
          }"
        >
          <!-- 因为我们要收集的是 id 和 name 两个字段, :value="`${item.id}:${item.name}`" 冒号的作用是作为分隔符的 -->
          <el-option
            v-for="item in unSelectSaleAttrList"
            :key="item.id"
            :label="item.name"
            :value="`${item.id}:${item.name}`"
          ></el-option>
        </el-select>
        <el-button
          style="margin-left: 20px"
          type="primary"
          icon="Plus"
          :disabled="echoSpuForm.unAttrIdAndName ? false : true"
          @click="addAttrHandler"
        >
          添加属性
        </el-button>
        <!-- 展示销售属性值 -->
        <el-table
          style="margin-top: 20px"
          border
          :data="echoSpuForm.saleAttrSelectedList"
        >
          <el-table-column
            label="序号"
            type="index"
            align="center"
            width="80"
          ></el-table-column>
          <el-table-column
            label="销售属性名"
            width="150"
            prop="saleAttrName"
          ></el-table-column>
          <el-table-column label="销售属性值" prop="spuSaleAttrValueList">
            <template #default="{ row }">
              <el-tag
                style="margin-right: 10px"
                v-for="(item, index) in row.spuSaleAttrValueList"
                :key="item.id"
                :closable="true"
                @close="row.spuSaleAttrValueList.splice(index, 1)"
              >
                {{ item.saleAttrValueName }}
              </el-tag>
              <el-input
                v-if="row.switchStructure"
                v-model="row.saleAttrValue"
                style="width: 120px"
                size="small"
                placeholder="请输入属性值"
                @blur="changeToReviewMode(row)"
              ></el-input>
              <el-button
                v-else
                type="primary"
                size="small"
                icon="Plus"
                @click="changeToEditMode(row)"
              ></el-button>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template #default="{ $index }">
              <el-button
                type="primary"
                size="small"
                icon="Delete"
                @click="deleteAttrHandler($index)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          :disabled="echoSpuForm.saleAttrSelectedList.length > 0 ? false : true"
          @click="saveHandler"
          >保存</el-button
        >
        <el-button type="primary" @click="cancelHandler">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped lang="scss"></style>
