import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/house/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/house/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/house/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/house/listByDong`, { params: params }).then(success).catch(fail);
}

function houseListByName(params, success, fail) {
  api.get(`/house/listByName`, { params: params }).then(success).catch(fail);
}

function likeList(memberId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/likedeal/${memberId}`).then(success).catch(fail);
}

function likeCancle(an, mi, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api
    .delete(`/likedeal`, {
      data: {
        aptNo: an,
        memberId: mi,
      },
    })
    .then(success)
    .catch(fail);
}

function selectLike(deleteLike, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.post(`/likedeal`, JSON.stringify(deleteLike)).then(success).catch(fail);
}

function likeRanking(success, fail) {
  api.get(`/likedeal`).then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListByName,
  likeList,
  likeCancle,
  selectLike,
  likeRanking,
};
