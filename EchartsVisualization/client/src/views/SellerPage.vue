<script>
import {defineComponent} from 'vue'
import Seller from "@/components/Seller.vue";

export default defineComponent({
  name: "SellerPage",
  components: {
    Seller
  }
})
</script>

<!-- 展示路径 /sellerPage -->
<template>
  <div class="common-page">
    <Seller />
  </div>

</template>

<style scoped lang="scss">

</style>