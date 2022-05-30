<template>
  <!-- ======= 공지사항 Modify Start ======= -->
  <section id="pages-details" class="pages-details">
    <div class="container">
      <div class="col-lg-12" align="center" style="margin: auto">
        <div class="card-body">
          <div>
            <table class="table">
              <tr>
                <th style="width: 20%; border-right: 1px solid #d3d3d3">
                  작성자
                </th>
                <td>{{ item.memberId }}</td>
              </tr>
              <tr>
                <th style="width: 20%; border-right: 1px solid #d3d3d3">
                  작성일자
                </th>
                <td>{{ item.regTime }}</td>
              </tr>
              <tr>
                <th style="width: 20%; border-right: 1px solid #d3d3d3">
                  제목
                </th>
                <td>
                  <input
                    type="text"
                    v-model="item.boardTitle"
                    class="form-control"
                    id="subject"
                    name="subject"
                  />
                </td>
              </tr>
              <tr style="height: 200px">
                <th style="width: 20%; border-right: 1px solid #d3d3d3">
                  내용
                </th>
                <td>
                  <textarea
                    class="form-control"
                    v-model="item.boardContent"
                    rows="15"
                    id="content"
                    name="content"
                  ></textarea>
                </td>
              </tr>
            </table>
          </div>
          <div>
            <b-button class="mx-2 my-2" @click="modifyBtn">수정</b-button>
            <b-button class="mx-2 my-2" @click="cancelBtn">취소</b-button>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- 공지사항 Modify End -->
</template>

<script>
import http from "@/api/http";
import swal from "sweetalert";

export default {
  name: "BoardModify",
  data() {
    return {
      item: {},
    };
  },
  created() {
    http
      .get(`/board/${this.$route.query.boardId}`)
      .then(({ data }) => {
        this.item = data;
      })
      .catch(({ error }) => {
        console.log(error);
        swal({
          title: "수정 페이지 에러 발생!! ",
          text: "관리자에게 문의해주세요.",
          icon: "error",
        });
      });
  },
  methods: {
    async modifyBtn() {
      await http
        .put(`/board/${this.$route.query.boardId}`, {
          boardId: this.item.boardId,
          memberId: this.item.memberId,
          boardTitle: this.item.boardTitle,
          boardContent: this.item.boardContent,
          regTime: this.regTime,
        })
        .then(({ data }) => {
          if (data === "success") {
            swal({
              title: "글 수정 성공!!",
              icon: "success",
            });
          } else {
            swal({
              title: "글 수정 실패!!",
              text: "다시 시도해주세요.",
              icon: "error",
            });
          }
        })
        .catch(({ error }) => {
          console.log(error);
          swal({
            title: "글 수정 에러 발생!! ",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        });
      this.$router.push(`/board/detail?boardId=${this.item.boardId}`);
    },
    cancelBtn() {
      this.$router.push(`/board/detail?boardId=${this.item.boardId}`);
    },
  },
};
</script>

<style scoped>
table,
tr,
td,
th {
  border: none !important;
  border: 1px lightgray solid !important;
}
</style>
