<template>
  <!-- ======= 페이지 제목 섹션 Start ======= -->
  <section class="PageTitle">
    <div class="container">
      <div
        class="section-title pt-3 pb-0 d-flex justify-content-between align-items-center"
      >
        <h2>{{ title }}</h2>
        <ol>
          <li><router-link to="/">Home</router-link></li>
          <li>{{ title }}</li>
        </ol>
      </div>
    </div>
  </section>
  <!-- 페이지 제목 섹션 End -->
</template>

<script>
export default {
  name: "PageTitleView",
  props: {
    title: String,
  },
};
</script>

<style></style>
