<template>
  <div>
    <!-- ======= 마이페이지 섹션 Start ======= -->
    <section id="pages-details" class="pages-details">
      <div class="container">
        <div class="row gy-9 justify-content-center">
          <div class="col-lg-7 pages-details-info p-3">
            <input type="hidden" name="act" value="register" />
            <div class="row">
              <div
                class="box mt-2 mb-4"
                style="background: #bdbdbd; margin: auto"
              >
                <img class="profile" src="@/assets/profile.png" />
              </div>
              <div class="mb-4">
                <label for="id">아이디<span style="color: red"> *</span></label>
                <input
                  type="text"
                  class="form-control"
                  id="memberId"
                  name="memberId"
                  v-model="member.memberId"
                  disabled
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
                  placeholder="******"
                  :disabled="disabled"
                />
              </div>
              <div class="mb-4">
                <label for="name">이름<span style="color: red"> *</span></label>
                <input
                  type="text"
                  class="form-control"
                  id="name"
                  name="name"
                  v-model="member.name"
                  :disabled="disabled"
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
                  :disabled="disabled"
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
                  :disabled="disabled"
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
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
              <b-button
                @click="modifyBtn"
                class="btn btn-warning"
                v-if="disabled"
              >
                수정
              </b-button>
              <b-button @click="dropBtn" class="btn btn-danger" v-if="disabled">
                탈퇴
              </b-button>
              <b-button
                id="finished"
                @click="finished"
                class="btn btn-success"
                v-else
              >
                수정 완료
              </b-button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- 마이페이지 섹션 End -->
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MyPage",
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
      disabled: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },
  created() {
    this.getMemberInfo;
    this.member = this.memberInfo;
  },
  methods: {
    ...mapActions(memberStore, ["getMemberInfo", "updateInfo", "removeMember"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_MEMBER_INFO"]),
    async finished() {
      this.disabled = !this.disabled;
      await this.updateInfo(this.member);
    },
    modifyBtn() {
      this.disabled = !this.disabled;
    },
    async dropBtn() {
      await this.removeMember(this.member);
      this.SET_IS_LOGIN(false);
      this.SET_MEMBER_INFO(null);
      sessionStorage.removeItem("access-token");
      this.$router.push("/login");
    },
  },
};
</script>

<style>
.box {
  width: 200px;
  height: 200px;
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
