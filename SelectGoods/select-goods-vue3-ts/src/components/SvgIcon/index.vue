<script setup lang="ts">
type IconType = {
  prefix?: string
  name: string
  color: string
  width: string
  height: string
}

withDefaults(defineProps<IconType>(), {
  prefix: '#icon-',
  color: '',
  width: '16px',
  height: '16px'
})
</script>

<template>
  <svg :style="{ width: width, height: height }">
    <use :xlink:href="`${prefix}${name}`" :fill="color" />
  </svg>
</template>

<style scoped lang="scss"></style>
