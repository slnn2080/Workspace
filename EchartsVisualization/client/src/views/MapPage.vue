<script>
import {defineComponent} from 'vue'
import Map from "@/components/Map.vue";

export default defineComponent({
  name: "MapPage",
  components: {Map}
})
</script>

<template>
  <div class="common-page">
    <Map />
  </div>
</template>

<style scoped lang="scss">

</style>