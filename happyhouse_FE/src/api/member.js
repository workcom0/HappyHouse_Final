import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(member, success, fail) {
  await api
    .post(`/member/login`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function register(member, success, fail) {
  await api
    .post(`/member/register`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function chageInfo(member, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .put(`/member/info`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function findById(memberId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/info/${memberId}`).then(success).catch(fail);
}

async function findPw(member, success, fail) {
  await api
    .put(`/member/find`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function dropMember(memberId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/member/remove/${memberId}`).then(success).catch(fail);
}

export { login, register, chageInfo, findById, findPw, dropMember };
