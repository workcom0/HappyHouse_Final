<template>
  <div>
    <PageTitleView title="자유게시판"></PageTitleView>
    <router-view></router-view>
  </div>
</template>

<script>
import PageTitleView from "@/components/common/PageTitleView.vue";

export default {
  name: "BoardView",
  components: {
    PageTitleView,
  },
};
</script>

<style></style>
