<template>
  <div>
    <!-- ======= 페이지 제목 섹션 Start ======= -->
    <PageTitleView title="나의정보관리"></PageTitleView>
    <!-- 페이지 제목 섹션 End -->

    <router-view></router-view>
  </div>
</template>

<script>
import PageTitleView from "@/components/common/PageTitleView.vue";

export default {
  name: "MyPageView",
  components: {
    PageTitleView,
  },
};
</script>

<style></style>
