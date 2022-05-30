import jwt_decode from "jwt-decode";
import {
  chageInfo,
  login,
  register,
  findPw,
  findById,
  dropMember,
} from "@/api/member.js";
import swal from "sweetalert";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isRegister: false,
    isUpdate: false,
    isLoginError: false,
    memberInfo: null,
  },
  getters: {
    checkMemberInfo: function (state) {
      return state.memberInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_MEMBER_INFO: (state, memberInfo) => {
      state.isLogin = true;
      state.memberInfo = memberInfo;
    },
    SET_MEMBER_REGISTER: (state, isRegister) => {
      state.isRegister = isRegister;
    },
    UPDATE_MEMBER_REGISTER: (state, isUpdate) => {
      state.isUpdate = isUpdate;
    },
  },
  actions: {
    async memberConfirm({ commit }, member) {
      await login(
        member,
        (response) => {
          // 로그인이 성공 했다면
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
            swal({
              title: "로그인 성공!!",
              icon: "success",
            });
          } else {
            // 로그인이 실패했다면
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            swal({
              title: "로그인 실패!!",
              text: "다시 시도해주세요.",
              icon: "error",
            });
          }
        },
        (error) => {
          if (error.response.data) {
            swal({
              title: "로그인 실패!!",
              text: "아이디 또는 비밀번호가 틀렸습니다.",
              icon: "error",
            });
          }
        },
      );
    },
    async memberRegister({ commit }, member) {
      await register(
        member,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_MEMBER_REGISTER", true);
            swal({
              title: "회원가입 성공!!",
              icon: "success",
            });
            location.href = "/login";
          }
        },
        (error) => {
          if (error.response.data) {
            commit("SET_MEMBER_REGISTER", false);
            swal({
              title: "회원가입 실패!!",
              text: "이미 존재하는 회원입니다.",
              icon: "error",
            });
          }
        },
      );
    },
    async updateInfo({ commit }, member) {
      await chageInfo(
        member,
        (response) => {
          if (response.data.message === "success") {
            commit("UPDATE_MEMBER_REGISTER", true);
            swal({
              title: "회원정보 수정 성공!!",
              icon: "success",
            });
          }
        },
        (error) => {
          if (error.response.data) {
            commit("UPDATE_MEMBER_REGISTER", false);
            swal({
              title: "회원정보 수정 실패!!",
              text: "관리자에게 문의해주세요.",
              icon: "error",
            });
          }
        },
      );
    },
    async find({ commit }, member) {
      await findPw(
        member,
        (response) => {
          if (response.data.message === "success") {
            commit("UPDATE_MEMBER_REGISTER");
            swal({
              title: "비밀번호 수정 성공!!",
              text: "변경된 비밀번호로 로그인하세요.",
              icon: "success",
            });
          }
        },
        (error) => {
          if (error.response.data) {
            swal({
              title: "비밀번호 수정 실패!!",
              text: "관리자에게 문의해주세요.",
              icon: "error",
            });
          }
        },
      );
    },
    getMemberInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.memberId,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_MEMBER_INFO", response.data.memberInfo);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async removeMember({ commit }, member) {
      await dropMember(
        member.memberId,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_IS_LOGIN", false);
          }
        },
        (error) => {
          if (error.response.data) {
            swal({
              title: "회원정보 불러오기 실패!!",
              text: "관리자에게 문의해주세요.",
              icon: "error",
            });
          }
        },
      );
    },
  },
};

export default memberStore;
