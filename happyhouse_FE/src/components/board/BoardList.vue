<template>
  <!-- ======= Board list Start ======= -->
  <section id="pages-details" class="pages-details">
    <div class="container">
      <div class="col-lg-12" style="margin: auto">
        <div class="mt-2 mb-2">
          <div class="card-body">
            <div align="right">
              <form>
                <select
                  class="form-control mx-1 mb-3"
                  id="key"
                  name="key"
                  style="width: 100px; display: inline"
                >
                  <option value="all">전체</option>
                  <option value="subject">제목</option>
                  <option value="content">내용</option>
                </select>
                <input name="word" type="text" id="word" class="mx-1" />
                <button class="btn btn-secondary btn-xs">검색</button>
              </form>
            </div>

            <b-table
              hover
              :items="items"
              :fields="fields"
              :per-page="perPage"
              :current-page="currentPage"
              @row-clicked="detail"
            >
            </b-table>

            <div class="row mt-2 mx-1" style="width: 100px; float: right">
              <button
                type="button"
                @click="WriteBtn"
                class="btn btn-secondary btn-xs"
              >
                글쓰기
              </button>
            </div>
            <br />
            <b-pagination
              align="center"
              v-model="currentPage"
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
            ></b-pagination>
          </div>
        </div>
        <div
          align="center"
          class="m-3 row justify-content-center"
          style="margin: auto"
        ></div>
      </div>
    </div>
  </section>
  <!-- Board list End -->
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      fields: [
        { key: "boardId", label: "No", tdClass: "tdClass" },
        { key: "boardTitle", label: "제목", tdClass: "tdSubject" },
        { key: "memberId", label: "작성자", tdClass: "tdClass" },
        { key: "regTime", label: "작성일자", tdClass: "tdClass" },
      ],
      items: [],
      perPage: 10,
      currentPage: 1,
    };
  },
  created() {
    http
      .get("/board")
      .then(({ data }) => {
        for (let i = 0; i < data.length; i++) {
          delete data[i].boardContent;
        }
        this.items = data;
      })
      .catch(({ error }) => {
        console.log(error);
        swal({
          title: "자유게시판 목록 불러오기 에러 발생!! ",
          text: "관리자에게 문의해주세요.",
          icon: "error",
        });
      });
  },
  computed: {
    rows() {
      return this.items.length;
    },
    ...mapState(memberStore, ["memberInfo"]),
  },
  methods: {
    WriteBtn() {
      if (this.memberInfo == null) {
        swal({
          title: "로그인 후에 이용가능합니다.",
          text: "로그인 해주세요.",
          icon: "warning",
        });
        return;
      }
      this.$router.push("/board/write");
    },
    detail(item) {
      this.$router.push(`board/detail?boardId=${item.boardId}`);
    },
  },
};
</script>

<style></style>
