<template>
  <!-- ======= 공지사항 list Start ======= -->
  <section id="pages-details" class="pages-details">
    <div class="container">
      <div class="col-lg-12" style="margin: auto">
        <div class="mt-2 mb-2">
          <div class="card-body">
            <div align="right">
              <form>
                <select
                  class="form-control mx-1 mb-3"
                  v-model="key"
                  style="width: 100px; display: inline"
                >
                  <option value="all">전체</option>
                  <option value="subject">제목</option>
                  <option value="content">내용</option>
                </select>
                <input v-model="word" type="text" id="word" class="mx-1" />
                <button
                  class="btn btn-secondary btn-xs"
                  @click.prevent="searchNotice"
                >
                  검색
                </button>
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

            <div
              class="row mt-2 mx-1"
              style="width: 100px; float: right"
              v-if="memberInfo"
            >
              <button
                type="button"
                @click="WriteBtn"
                class="btn btn-secondary btn-xs"
                v-if="memberInfo.isAdmin == 1"
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
  <!-- 공지사항 list End -->
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "NoticeList",
  data() {
    return {
      fields: [
        { key: "noticeNo", label: "No", thClass: "w10" },
        { key: "memberId", label: "작성자", thClass: "w20" },
        { key: "subject", label: "제목", thClass: "w50" },
        { key: "regTime", label: "작성일자", thClass: "w30" },
      ],
      items: [],
      key: "all",
      word: "",
      perPage: 10,
      currentPage: 1,
    };
  },
  created() {
    http
      .get("/notice")
      .then(({ data }) => {
        for (let i = 0; i < data.length; i++) {
          delete data[i].content;
        }
        this.items = data;
      })
      .catch(({ error }) => {
        console.log(error);
        swal({
          title: "공지사항 목록 불러오기 에러 발생!!",
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
      this.$router.push("/notice/write");
    },
    detail(item) {
      this.$router.push(`notice/detail?noticeNo=${item.noticeNo}`);
    },
    searchNotice() {
      http
        .put("/notice", {
          key: this.key,
          word: this.word,
        })
        .then(({ data }) => {
          for (let i = 0; i < data.length; i++) {
            delete data[i].content;
          }
          this.items = data;
        })
        .catch(({ error }) => {
          console.log(error);
          swal({
            title: "검색 에러 발생!!",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        });
    },
  },
};
</script>

<style>
table.b-table thead th.w10 {
  width: 10%;
}
table.b-table thead th.w20 {
  width: 20%;
}
table.b-table thead th.w30 {
  width: 30%;
}
table.b-table thead th.w40 {
  width: 40%;
}
table.b-table thead th.w50 {
  width: 50%;
}
table.b-table thead th.w60 {
  width: 60%;
}
table.b-table thead th.w70 {
  width: 70%;
}
table.b-table thead th.w80 {
  width: 80%;
}
table.b-table thead th.w90 {
  width: 90%;
}
table.b-table thead th.w15 {
  width: 15%;
}
table.b-table thead th.w25 {
  width: 25%;
}
table.b-table thead th.w35 {
  width: 35%;
}
table.b-table thead th.w45 {
  width: 45%;
}
table.b-table thead th.w55 {
  width: 55%;
}
table.b-table thead th.w65 {
  width: 65%;
}
table.b-table thead th.w75 {
  width: 75%;
}
table.b-table thead th.w85 {
  width: 85%;
}
table.b-table thead th.w95 {
  width: 95%;
}

.page-item button {
  color: black;
}

.page-item.active .page-link {
  background-color: lightgray !important;
  border-color: lightgray !important;
}
</style>
