<script setup lang="ts">
import { reactive, onMounted } from 'vue'

import { useVuelidate } from '@vuelidate/core'
// 这里有 验证器 相关的东西
import { required } from '@vuelidate/validators'
import axios from 'axios'

defineOptions({
  name: 'SearchArea'
})

const formData = reactive({
  username: '',
  password: ''
})

const rules = {
  username: { required },
  password: { required }
}

const v = useVuelidate(rules, formData)
// console.log(v)

const loginHandler = async () => {
  console.log('loginHandler')
  // 手动验证
  const isFormCorrect = await v.value.$validate()
  console.log('isFormCorrect', isFormCorrect)
}

const cancelHandler = () => {
  v.value.$reset()
}

onMounted(() => {
  const params: any = {
    name: undefined,
    age: 18
  }
  axios
    .post('http://localhost:3333/', params)
    .then((res: any) => console.log(res))
})
</script>

<template>
  <div class="search">
    <div>Form: {{ formData }}</div>
    <div>username dirty: {{ v.username.$dirty }}</div>
    <div>username invalid: {{ v.username.$invalid }}</div>
    <!-- <input v-model="name" @blur="v$.name.$touch"> -->
    <div style="color: red" v-if="v.username.$error">
      UserNameErrorMsg: {{ v.username.$errors[0].$message }}
    </div>
    <input type="file" name="" id="" />
    <hr />
    <div style="color: red" v-if="v.password.$error">
      PassWordErrorMsg: {{ v.password.$errors[0].$message }}
    </div>
    <hr />
    <div v-if="v.username.$error">错误信息</div>
    <el-form class="search-form" label-position="top">
      <el-row :gutter="20">
        <el-col :span="NaN">
          <el-form-item label="UserName">
            <el-input
              v-model="formData.username"
              placeholder="UserName"
              @input="v.$reset()"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="NaN">
          <el-form-item label="Password">
            <el-input
              v-model="formData.password"
              placeholder="PassWord"
              @input="v.$reset()"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col class="search-form__btn">
          <el-button @click="loginHandler">Login</el-button>
          <el-button @click="cancelHandler">cancel</el-button>
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
