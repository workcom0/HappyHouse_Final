<template>
  <!-- ======= Header Start======= -->
  <header id="header" class="fixed-top header-inner-pages">
    <div class="container d-flex align-items-center justify-content-lg-between">
      <h1 class="logo me-auto me-lg-0">
        <router-link to="/">Happy House<span>.</span></router-link>
      </h1>

      <!-- navBar Start -->
      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li>
            <router-link to="/" class="nav-link scrollto">Home</router-link>
          </li>
          <li>
            <router-link to="/about" class="nav-link scrollto"
              >소개</router-link
            >
          </li>
          <li>
            <router-link to="/notice" class="nav-link scrollto"
              >공지사항</router-link
            >
          </li>
          <li>
            <router-link to="/board" class="nav-link scrollto"
              >자유게시판</router-link
            >
          </li>
          <li>
            <router-link to="/house" class="nav-link scrollto"
              >거래가검색</router-link
            >
          </li>

          <li class="dropdown" v-if="memberInfo">
            <a href="#">
              <span>마이페이지</span>
              <i class="bi bi-chevron-down"></i>
            </a>
            <ul>
              <li>
                <router-link to="/mypage" class="nav-link scrollto"
                  >나의정보관리</router-link
                >
              </li>
              <li>
                <router-link to="/like" class="nav-link scrollto"
                  >관심매물관리</router-link
                >
              </li>
            </ul>
          </li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav>
      <!-- navBar End -->

      <!-- 로그인/로그아웃 버튼 Start -->

      <router-link
        to="/"
        @click.native="onClickLogout"
        class="get-started-btn scrollto"
        v-if="memberInfo"
        >로그아웃</router-link
      >
      <router-link to="/login" class="get-started-btn scrollto" v-else
        >로그인</router-link
      >

      <!-- 로그인/로그아웃 버튼 End -->
    </div>
  </header>
  <!-- Header End -->
</template>

<script>
import { mapState, mapMutations } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: `'HeaderView'`,
  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_MEMBER_INFO"]),
    // 로그아웃 클릭 시 토큰 제거
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_MEMBER_INFO(null);
      sessionStorage.removeItem("access-token");
      swal({
        title: "로그아웃 완료!!",
        icon: "success",
      });
    },
  },
};
</script>
