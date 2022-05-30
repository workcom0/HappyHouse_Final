<template>
  <div>
    <!-- ======= 페이지 제목 섹션 Start ======= -->
    <PageTitleView title="공지사항"></PageTitleView>
    <!-- 페이지 제목 섹션 End -->

    <router-view></router-view>
  </div>
</template>

<script>
import PageTitleView from "@/components/common/PageTitleView.vue";

export default {
  name: "NoticeView",
  components: {
    PageTitleView,
  },
};
</script>

<style></style>
