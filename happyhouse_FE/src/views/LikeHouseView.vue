<template>
  <div>
    <!-- ======= 페이지 제목 섹션 Start ======= -->
    <PageTitleView title="관심매물관리"></PageTitleView>
    <!-- 페이지 제목 섹션 End -->

    <router-view></router-view>
  </div>
</template>

<script>
import PageTitleView from "@/components/common/PageTitleView.vue";

export default {
  name: "LikeHouseView",
  components: {
    PageTitleView,
  },
};
</script>

<style></style>
