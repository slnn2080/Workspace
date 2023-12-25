<script setup lang="ts">
import { ref, computed } from 'vue'
import Cifcular from './components/Circular.vue'

const dataLoading = ref<boolean>(false)

const test = computed({
  get() {
    return dataLoading.value
  },
  set(n) {
    console.log(n)
    dataLoading.value = n
  }
})

console.log(test.value)
</script>

<template>
  <div class="container">
    <el-row>
      <el-col :span="10">
        <Cifcular />
      </el-col>
    </el-row>
  </div>
</template>

<style scoped lang="scss">
.container {
  font-size: 16px;
}
</style>
