<script setup lang="ts">
import { ref } from 'vue'
const props = defineProps({
  count: Number
})

const testCount = ref(props.count! * 2)
</script>

<template>
  <div>
    <div>{{ count }}</div>
    <div>{{ testCount }}</div>
  </div>
</template>

<style scoped lang="scss"></style>
