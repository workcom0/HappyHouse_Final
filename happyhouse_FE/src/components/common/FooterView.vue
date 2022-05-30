<template>
  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6">
            <div class="footer-info">
              <h3>Happy House<span>.</span></h3>
              <p>
                서울시 강남구 <br />
                &emsp;&emsp;&emsp;&emsp;&emsp;테헤란로 멀티스퀘어<br /><br />
                <strong>Phone:</strong> 02-0000-0000<br />
                <strong>Email:</strong> admin@ssafy.com<br />
              </p>
              <div class="social-links mt-3">
                <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
                <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                <a href="#" class="instagram"
                  ><i class="bx bxl-instagram"></i
                ></a>
                <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
                <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
              </div>
            </div>
          </div>

          <div class="col-lg-2 col-md-6 footer-links">
            <h4>Useful Links</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Home</a></li>
              <li>
                <i class="bx bx-chevron-right"></i> <a href="/about">About</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/notice">공지사항</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/board">자유게시판</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/house">거래가검색</a>
              </li>
            </ul>
          </div>

          <div class="col-lg-2 col-md-6 footer-links">
            <h4>Our Services</h4>
            <ul>
              <li>
                <i class="bx bx-chevron-right"></i> <a href="#">Web Design</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="#">Web Development</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="#">Product Management</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i> <a href="#">Marketing</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="#">Graphic Design</a>
              </li>
            </ul>
          </div>

          <div class="col-lg-5 col-md-6 footer-links">
            <div>
              <h4>관련 기사</h4>
              <b-icon
                icon="arrow-clockwise"
                type="button"
                @click="refreshBtn()"
                v-if="this.newsList[0].link"
              >
              </b-icon>
              <button class="btn btn-primary" type="button" disabled v-else>
                <span
                  class="spinner-border spinner-border-sm"
                  role="status"
                  aria-hidden="true"
                ></span>
                Loading...
              </button>
              <span>&emsp;마지막 업데이트 시간 : {{ this.refreshTiem }}</span>
            </div>
            <div v-if="this.newsList[0].link" class="news-class">
              <li>
                <i class="bx bx-chevron-right" style="color: #ffc451"></i>
                <a
                  v-bind:href="this.newsList[0].link"
                  v-html="this.newsList[0].title"
                ></a>
              </li>
              <li>
                <i class="bx bx-chevron-right" style="color: #ffc451"></i>
                <a
                  v-bind:href="this.newsList[1].link"
                  v-html="this.newsList[1].title"
                ></a>
              </li>
              <li>
                <i class="bx bx-chevron-right" style="color: #ffc451"></i>
                <a
                  v-bind:href="this.newsList[2].link"
                  v-html="this.newsList[2].title"
                ></a>
              </li>
              <li>
                <i class="bx bx-chevron-right" style="color: #ffc451"></i>
                <a
                  v-bind:href="this.newsList[3].link"
                  v-html="this.newsList[3].title"
                ></a>
              </li>
              <li>
                <i class="bx bx-chevron-right" style="color: #ffc451"></i>
                <a
                  v-bind:href="this.newsList[4].link"
                  v-html="this.newsList[4].title"
                ></a>
              </li>
            </div>
            <div class="text-center" v-else>
              <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Gp</span></strong
        >. All Rights Reserved
      </div>
      <div class="credits">
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer>
  <!-- End Footer -->
</template>

<script>
import { mapState, mapActions } from "vuex";

const newsStore = "newsStore";

export default {
  data() {
    return {
      news: [{ link: "", title: "" }],
    };
  },
  created() {
    this.getNews;
  },
  computed: {
    ...mapState(newsStore, ["newsList", "refreshTiem"]),
    ...mapActions(newsStore, ["getNews"]),
  },
  methods: {
    refreshBtn() {
      // api에게 최신 뉴스 다시 요청하기
      this.getNews;
    },
  },
};
</script>

<style scoped>
li {
  list-style: none;
  padding-left: 0px;
}

li i {
  margin-right: 7px;
}

.news-class li {
  margin-bottom: 15px;
}

.news-class a {
  text-decoration: underline !important;
  color: white;
}
</style>
