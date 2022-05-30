<template>
  <div>
    <!-- ======= 페이지 제목 섹션 Start ======= -->
    <PageTitleView title="About"></PageTitleView>
    <!-- 페이지 제목 섹션 End -->

    <!-- ======= about1 Start ======= -->
    <section id="pages-details" class="pages-details">
      <div class="container aos-init aos-animate mb-2 pb-3" data-aos="fade-up">
        <div class="title">
          <h2>Service</h2>
          <p>our Service</p>
        </div>
        <div class="row">
          <div
            class="col-lg-6 order-1 order-lg-2 aos-init aos-animate"
            data-aos="fade-left"
            data-aos-delay="100"
          >
            <img src="@/assets/about1.jpg" class="img-fluid" alt="" />
          </div>
          <div
            class="col-lg-6 pt-4 pt-lg-0 order-2 order-lg-1 content aos-init aos-animate"
            data-aos="fade-right"
            data-aos-delay="100"
          >
            <h3>다양한 아파트 거래 정보를 <br />간편하게 조회할 수 있습니다</h3>
            <p class="fst-italic">
              Happy House를 통해서 아파트 매매 실거래 현황을 확인하세요!
            </p>
            <ul class="about1">
              <li>
                <i class="ri-check-double-line"></i> 직관적이고 단순한 UI를
                제공합니다.
              </li>
              <li>
                <i class="ri-check-double-line"></i> 공공데이터를 활용한
                정확하고 다양한 정보를 제공합니다.
              </li>
              <li>
                <i class="ri-check-double-line"></i> 카카오, 네이버 api를
                활용하여 정보를 한 눈에 알아보기 쉽게 제공합니다.
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
    <!-- about1 End -->

    <!-- ======= about2 Start ======= -->
    <section id="services" class="services">
      <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="title">
          <h2>Content</h2>
          <p>our Content</p>
        </div>
        <div class="row">
          <div
            class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 aos-init aos-animate"
            data-aos="zoom-in"
            data-aos-delay="200"
          >
            <div class="icon-box">
              <div class="icon"><i class="bx bx-file"></i></div>
              <h4><a href="">실거래가 검색</a></h4>
              <p>
                아파트 거래 정보를 간편하게 검색할 수 있습니다.<br />
                찜하기를 통해 관심매물을 관리할 수 있습니다.
              </p>
            </div>
          </div>
          <div
            class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 aos-init aos-animate"
            data-aos="zoom-in"
            data-aos-delay="200"
          >
            <div class="icon-box">
              <div class="icon"><i class="bx bx-slideshow"></i></div>
              <h4><a href="">오늘의 뉴스</a></h4>
              <p>
                부동산 관련 최신 뉴스들을<br />&ensp;&emsp;&emsp;&emsp;&emsp;한
                눈에 확인할 수 있습니다.&emsp;&emsp;&emsp;&emsp;&ensp;
              </p>
            </div>
          </div>
          <div
            class="col-lg-4 col-md-6 d-flex align-items-stretch mt-4 aos-init aos-animate"
            data-aos="zoom-in"
            data-aos-delay="300"
          >
            <div class="icon-box">
              <div class="icon"><i class="bx bx-arch"></i></div>
              <h4><a href="">자유게시판</a></h4>
              <p>
                &emsp;회원들과 소통을 통해서 다양한 정보들을&emsp; <br />공유할
                수 있습니다.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- about2 End -->

    <!-- ======= about3 Start ======= -->
    <section id="team" class="team">
      <div class="container aos-init aos-animate" data-aos="fade-up">
        <div class="title">
          <h2>Team</h2>
          <p>our Team</p>
        </div>
        <div class="row">
          <div class="col-lg-3 col-md-6 d-flex align-items-stretch">
            <div
              class="member aos-init aos-animate"
              data-aos="fade-up"
              data-aos-delay="300"
            >
              <div class="member-img">
                <img src="@/assets/profile.png" class="img-fluid" alt="" />
                <div class="social">
                  <a href=""><i class="bi bi-twitter"></i></a>
                  <a href=""><i class="bi bi-facebook"></i></a>
                  <a href=""><i class="bi bi-instagram"></i></a>
                  <a href=""><i class="bi bi-linkedin"></i></a>
                </div>
              </div>
              <div class="member-info">
                <h4>김지혜</h4>
                <span>팀장 (백엔드 · 프론트엔드)</span>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-6 d-flex align-items-stretch">
            <div
              class="member aos-init aos-animate"
              data-aos="fade-up"
              data-aos-delay="400"
            >
              <div class="member-img">
                <img src="@/assets/profile.png" class="img-fluid" alt="" />
                <div class="social">
                  <a href=""><i class="bi bi-twitter"></i></a>
                  <a href=""><i class="bi bi-facebook"></i></a>
                  <a href=""><i class="bi bi-instagram"></i></a>
                  <a href=""><i class="bi bi-linkedin"></i></a>
                </div>
              </div>
              <div class="member-info">
                <h4>변수경</h4>
                <span>팀원 (백엔드 · 프론트엔드)</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- about3 End -->
  </div>
</template>

<script>
import PageTitleView from "@/components/common/PageTitleView.vue";

export default {
  name: "AboutView",
  components: {
    PageTitleView,
  },
};
</script>

<style>
.ri-check-double-line {
  color: rgb(239, 201, 47);
}

/*--------------------------------------------------------------
# Sections General
--------------------------------------------------------------*/
section {
  padding: 60px 0;
  overflow: hidden;
}

.title {
  padding-bottom: 40px;
}
.title h2 {
  font-size: 14px;
  font-weight: 500;
  padding: 0;
  line-height: 1px;
  margin: 0 0 5px 0;
  letter-spacing: 2px;
  text-transform: uppercase;
  color: #aaaaaa;
  font-family: "Poppins", sans-serif;
}
.title h2::after {
  content: "";
  width: 120px;
  height: 1px;
  display: inline-block;
  background: #ffde9e;
  margin: 4px 10px;
}
.title p {
  margin: 0;
  margin: 0;
  font-size: 36px;
  font-weight: 700;
  text-transform: uppercase;
  font-family: "Poppins", sans-serif;
  color: #151515;
}
.services .icon-box {
  text-align: center;
  border: 1px solid #ebebeb;
  padding: 80px 20px;
  transition: all ease-in-out 0.3s;
  background: #fff;
}
.services .icon-box .icon {
  margin: 0 auto;
  width: 64px;
  height: 64px;
  background: #ffc451;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  transition: 0.3s;
}
.services .icon-box .icon i {
  color: #151515;
  font-size: 28px;
  transition: ease-in-out 0.3s;
}
.services .icon-box h4 {
  font-weight: 700;
  margin-bottom: 15px;
  font-size: 24px;
}
.services .icon-box h4 a {
  color: #151515;
  transition: ease-in-out 0.3s;
}
.services .icon-box h4 a:hover {
  color: #ffc451;
}
.services .icon-box p {
  line-height: 24px;
  font-size: 14px;
  margin-bottom: 0;
}
.services .icon-box:hover {
  border-color: #fff;
  box-shadow: 0px 0 25px 0 rgba(0, 0, 0, 0.1);
  transform: translateY(-10px);
}
.team {
  background: #fff;
  padding: 60px 0;
}
.team .member {
  margin-bottom: 20px;
  overflow: hidden;
  border-radius: 5px;
  background: #fff;
  box-shadow: 0px 2px 15px rgba(0, 0, 0, 0.1);
}
.team .member .member-img {
  position: relative;
  overflow: hidden;
}
.team .member .social {
  position: absolute;
  left: 0;
  bottom: 30px;
  right: 0;
  opacity: 0;
  transition: ease-in-out 0.3s;
  text-align: center;
}
.team .member .social a {
  transition: color 0.3s;
  color: #151515;
  margin: 0 3px;
  border-radius: 4px;
  width: 36px;
  height: 36px;
  background: rgba(255, 255, 255, 0.8);
  transition: ease-in-out 0.3s;
  color: #484848;
  display: inline-flex;
  justify-content: center;
  align-items: center;
}
.team .member .social a:hover {
  color: #151515;
  background: #ffc451;
}
.team .member .social i {
  font-size: 18px;
  line-height: 0;
}
.team .member .member-info {
  padding: 25px 15px;
}
.team .member .member-info h4 {
  font-weight: 700;
  margin-bottom: 5px;
  font-size: 18px;
  color: #151515;
}
.team .member .member-info span {
  display: block;
  font-size: 13px;
  font-weight: 400;
  color: #aaaaaa;
}
.team .member .member-info p {
  font-style: italic;
  font-size: 14px;
  line-height: 26px;
  color: #777777;
}
.team .member:hover .social {
  opacity: 1;
  bottom: 15px;
}

ul {
  list-style: none;
  padding-left: 0px;
}

.about1 li {
  margin-bottom: 40px;
}
</style>
