<template>
  <!-- ======= index Start ======= -->
  <section id="index" class="d-flex align-items-center justify-content-center">
    <div class="container" data-aos="fade-up">
      <!-- 대문 글 Start -->
      <div
        class="row justify-content-center"
        data-aos="fade-up"
        data-aos-delay="150"
      >
        <div class="col-xl-8 col-lg-8">
          <h1>Welcome to <span>Happy House</span>!</h1>
          <h2>부동산 실거래가 검색을 간편하게 해보세요!</h2>
        </div>
      </div>
      <!-- 대문 글 End -->

      <!-- 바로가기 버튼 Start -->
      <div
        class="row gy-4 mt-5 justify-content-center"
        data-aos="zoom-in"
        data-aos-delay="250"
      >
        <div class="col-xl-2 col-md-4">
          <div class="icon-box">
            <i class="ri-store-line"></i>
            <h3><router-link to="/about">About</router-link></h3>
          </div>
        </div>
        <div class="col-xl-2 col-md-4">
          <div class="icon-box">
            <i class="ri-bar-chart-box-line"></i>
            <h3><router-link to="/notice">공지사항</router-link></h3>
          </div>
        </div>
        <div class="col-xl-2 col-md-4">
          <div class="icon-box">
            <i class="ri-database-2-line"></i>
            <h3><router-link to="/board">자유게시판</router-link></h3>
          </div>
        </div>
        <div class="col-xl-2 col-md-4">
          <div class="icon-box">
            <i class="ri-calendar-todo-line"></i>
            <h3><router-link to="/house">거래가검색</router-link></h3>
          </div>
        </div>
      </div>
      <div
        class="col-xl-4 col-md-7 mt-5"
        style="
          margin: auto;
          background-color: white;
          opacity: 0.7;
          border-radius: 15px;
        "
      >
        <LikeRanking></LikeRanking>
      </div>
      <!-- 바로가기 버튼 End -->
    </div>
  </section>
  <!-- index End -->
</template>

<script>
import LikeRanking from "@/components/house/LikeRanking.vue";

export default {
  name: "HomeView",
  components: {
    LikeRanking,
  },
};
</script>
