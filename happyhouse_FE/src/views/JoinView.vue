<template>
  <div>
    <!-- ======= 페이지 제목 섹션 Start ======= -->
    <PageTitleView title="회원가입"></PageTitleView>
    <!-- 페이지 제목 섹션 End -->

    <!-- ======= 회원가입  섹션 Start ======= -->
    <section id="pages-details" class="pages-details">
      <div class="container">
        <div class="row gy-4 justify-content-between">
          <div class="col-lg-7 pages-details-info p-3">
            <form
              method="post"
              action="${root}/join"
              class="bg-white rounded shadow-5-strong p-5"
            >
              <input type="hidden" name="act" value="register" />
              <div class="row">
                <div class="mb-4">
                  <label for="id"
                    >아이디<span style="color: red"> *</span></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="memberId"
                    name="memberId"
                    v-model="member.memberId"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="pw"
                    >비밀번호<span style="color: red"> *</span></label
                  >
                  <input
                    type="password"
                    class="form-control"
                    id="memberPw"
                    name="memberPw"
                    v-model="member.memberPw"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="name"
                    >이름<span style="color: red"> *</span></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="name"
                    name="name"
                    v-model="member.name"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="email"
                    >e-mail<span style="color: red"> *</span></label
                  >
                  <input
                    type="email"
                    class="form-control"
                    id="email"
                    name="email"
                    placeholder="you@example.com"
                    v-model="member.email"
                    required
                  />
                </div>
                <div class="mb-4">
                  <label for="phone"
                    >전화번호<span style="color: red"> *</span></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="tel"
                    name="tel"
                    placeholder="010-xxxx-xxxx"
                    v-model="member.tel"
                    required
                  />
                </div>
              </div>
              <div class="custom-control custom-checkbox">
                <input
                  type="checkbox"
                  class="custom-control-input"
                  id="agreement"
                  checked
                  required
                />
                <label class="custom-control-label" for="aggrement"
                  >개인정보 수집 및 이용에 동의합니다.</label
                >
              </div>
              <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
                <b-button @click="joinBtn" class="btn btn-lg btn-block">
                  가입하기
                </b-button>
              </div>
            </form>
          </div>

          <div class="col-lg-4">
            <div class="pages-details-info p-5 mt-10">
              <h3>■ 개인정보 수집 동의</h3>
              <p>
                <strong>1. 수집 항목</strong><br />
                아이디, 비밀번호, 이름, 이메일, 전화번호, 기타 서비스 이용
                과정에서 생성되는 정보<br /><br />
                <strong>2. 수집 목적</strong><br />
                회원관리(본인확인, 개인 식별, 가입 의사 확인, 가입 및 가입횟수
                제한)<br /><br />
                <strong>3. 보유 및 이용 기간</strong><br />
                회원 탈퇴시까지. 다만, 장기(1년) 미접속의 경우 사전 안내 후 탈회
                및 삭제 처리<br />
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- 회원가입  섹션 End -->
  </div>
</template>

<script>
import PageTitleView from "@/components/common/PageTitleView.vue";
import { mapState, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "JoinView",
  data() {
    return {
      member: {
        memberId: null,
        memberPw: null,
        name: null,
        email: null,
        tel: null,
        isAdmin: null,
      },
    };
  },
  components: {
    PageTitleView,
    ...mapState(memberStore, ["isRegister"]),
  },
  methods: {
    ...mapActions(memberStore, ["memberRegister"]),
    ...mapMutations(memberStore, ["SET_MEMBER_REGISTER"]),
    async joinBtn() {
      await this.memberRegister(this.member);
      if (this.isRegister) {
        this.$router.push("/login");
      }
    },
  },
};
</script>

<style></style>
