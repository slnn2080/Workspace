<script>
import {defineComponent} from 'vue'
import Stock from "@/components/Stock.vue";

export default defineComponent({
  name: "StockPage",
  components: {Stock}
})
</script>

<template>
  <div class="common-page">
    <Stock />
  </div>
</template>

<style scoped lang="scss">

</style>