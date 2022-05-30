import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import AboutView from "@/views/AboutView.vue";
import NoticeView from "@/views/NoticeView.vue";
import MyPageView from "@/views/MyPageView.vue";
import LikeHouseView from "@/views/LikeHouseView.vue";
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";
import NoticeList from "@/components/notice/NoticeList";
import NoticeWrite from "@/components/notice/NoticeWrite";
import NoticeDetail from "@/components/notice/NoticeDetail";
import NoticeModify from "@/components/notice/NoticeModify";
import MyPage from "@/components/mypage/MyPage";
import LikeHouse from "@/components/mypage/LikeHouse";
import BoardView from "@/views/BoardView.vue";
import BoardList from "@/components/board/BoardList";
import BoardWrite from "@/components/board/BoardWrite";
import BoardDetail from "@/components/board/BoardDetail";
import BoardModify from "@/components/board/BoardModify";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: AboutView,
  },
  {
    path: "/notice",
    component: NoticeView,
    children: [
      {
        path: "",
        name: "notice-list",
        component: NoticeList,
      },
      {
        path: "write",
        name: "notice-write",
        component: NoticeWrite,
      },
      {
        path: "detail",
        name: "notice-detail",
        component: NoticeDetail,
      },
      {
        path: "modify",
        name: "notice-modify",
        component: NoticeModify,
      },
    ],
  },
  {
    path: "/board",
    component: BoardView,
    children: [
      {
        path: "",
        name: "board-list",
        component: BoardList,
      },
      {
        path: "write",
        name: "board-write",
        component: BoardWrite,
      },
      {
        path: "detail",
        name: "board-detail",
        component: BoardDetail,
      },
      {
        path: "modify",
        name: "board-modify",
        component: BoardModify,
      },
    ],
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/join",
    name: "join",
    component: JoinView,
  },
  {
    path: "/mypage",
    component: MyPageView,
    children: [
      {
        path: "",
        name: "mypage",
        component: MyPage,
      },
    ],
  },
  {
    path: "/like",
    component: LikeHouseView,
    children: [
      {
        path: "",
        name: "like",
        component: LikeHouse,
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
