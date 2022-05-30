import axios from "axios";

const newsStore = {
  namespaced: true,
  state: {
    newsList: [{ link: "", title: "" }],
    refreshTiem: "",
  },
  getters: {
    getNewsList: function (state) {
      return state.newsList;
    },
  },
  mutations: {
    SET_NEWS_LIST: (state, tmpNewsList) => {
      state.newsList = tmpNewsList;
    },
    SET_REFRESH_TIME: (state, time) => {
      state.refreshTiem = time;
    },
    CLEAR_NEWS_LIST: (state) => {
      state.newsList = [{ link: "", title: "" }];
    },
  },
  actions: {
    async getNews({ commit }) {
      commit("CLEAR_NEWS_LIST");
      let id = "RUKCGv8PxKB2cnn12ld7";
      let pass = "A3Jwc1WkbQ";
      await axios
        .get(
          "https://cors-anywhere.herokuapp.com/https://openapi.naver.com/v1/search/news.json?query=집값&display=10&start=1&sort=sim",
          {
            headers: {
              method: "GET",
              Accept: "application/json",
              "X-Naver-Client-Id": id,
              "X-Naver-Client-Secret": pass,
            },
          },
        )
        .then((response) => {
          commit("SET_NEWS_LIST", response.data.items);
          var nowDate = new Date();
          var time =
            nowDate.toLocaleDateString() + " " + nowDate.toLocaleTimeString();
          commit("SET_REFRESH_TIME", time);
        });
    },
  },
};

export default newsStore;
