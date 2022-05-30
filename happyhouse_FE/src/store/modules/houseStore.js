import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  likeList,
  likeCancle,
  selectLike,
  likeRanking,
} from "@/api/house.js";
import swal from "sweetalert";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    likes: [],
    curPoint: 0,
    memberId: null,
    tmp: null,
    isLoading: false,
    ranking: [],
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
      state.isLoading = false;
    },
    INIT_LIKE_LIST: (state) => {
      state.likes = [];
      for (let i = 0; i < state.houses.length; i++) {
        state.likes[i] = false;
      }
    },
    SET_LIKE_LIST: (state, likes) => {
      state.likes = likes;
    },
    SET_MEMBER_ID: (state, memberID) => {
      state.memberID = memberID;
    },
    SET_TMP: (state) => {
      state.tmp = null;
    },
    ON_Loding: (state) => {
      state.isLoading = !state.isLoading;
    },
    SET_RANKING_LIST: (state, rank) => {
      state.ranking = rank;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      houseList(
        params,
        (response) => {
          commit("SET_HOUSE_LIST", response.data);
          commit("INIT_LIKE_LIST");
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getLikeList({ commit }, memberId) {
      likeList(
        memberId,
        (response) => {
          commit("SET_LIKE_LIST", response.data);
        },
        (error) => {
          console.log(error);
          swal({
            title: "관심 매물을 불러오지 못 했습니다.",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        },
      );
    },
    async deleteLikeList({ commit }, deleteLike) {
      await likeCancle(
        deleteLike.aptNo,
        deleteLike.memberId,
        (response) => {
          if (response.data.message === "success") {
            console.log("삭제 완료");
          }
          commit("SET_TMP");
        },
        (error) => {
          console.log(error);
          swal({
            title: "관심 매물을 삭제하지 못 했습니다.",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        },
      );
    },
    selectLikeList({ commit }, deleteLike) {
      selectLike(
        deleteLike,
        (response) => {
          if (response.data.message === "success") {
            console.log("등록 완료");
          } else {
            swal({
              title: "이미 등록한 매물입니다.",
              icon: "info",
            });
          }
          commit("SET_TMP");
        },
        (error) => {
          console.log(error);
          swal({
            title: "관심 매물을 등록하지 못 했습니다.",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        },
      );
    },
    getLikeRankingList({ commit }) {
      likeRanking(
        (response) => {
          commit("SET_RANKING_LIST", response.data.ranking);
        },
        (error) => {
          console.log(error);
          swal({
            title: "랭킹을 불러오지 못 했습니다.",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        },
      );
    },
  },
};

export default houseStore;
