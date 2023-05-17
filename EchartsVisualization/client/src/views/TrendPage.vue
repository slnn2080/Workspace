<script>
import {defineComponent} from 'vue'
import Trend from "@/components/Trend.vue";

export default defineComponent({
  name: "TrendPage",
  components: {Trend }
})
</script>

<template>
  <div class="common-page">
    <Trend />
  </div>
</template>

<style scoped lang="scss">

</style>