<script>
import {defineComponent} from 'vue'
import Rank from "@/components/Rank.vue";

export default defineComponent({
  name: "RankPage",
  components: {Rank}
})
</script>

<template>
  <div class="common-page">
    <Rank />
  </div>
</template>

<style scoped lang="scss">

</style>