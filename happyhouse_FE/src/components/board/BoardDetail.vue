<template>
  <!-- ======= Board detail Start ======= -->
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
                    :value="item.boardTitle"
                    class="form-control"
                    id="subject"
                    name="subject"
                    disabled
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
                    :value="item.boardContent"
                    rows="15"
                    id="content"
                    name="content"
                    disabled
                  ></textarea>
                </td>
              </tr>
            </table>
          </div>
          <div>
            <b-button
              class="mx-2 my-2"
              @click="modifyBtn"
              v-if="memberInfo != null && memberInfo.memberId == item.memberId"
              >수정</b-button
            >
            <b-button
              class="mx-2 my-2"
              @click="delBtn"
              v-if="memberInfo != null && memberInfo.memberId == item.memberId"
              >삭제</b-button
            >
            <b-button class="mx-2 my-2" @click="listBtn">목록</b-button>
          </div>
          <div class="mt-3 mb-5 card-body">
            <h3>{{ comments.length }} Comments</h3>
            <div class="mt-2 mb-5">
              <b-list-group>
                <b-list-group-item
                  class="flex-column align-items-start"
                  v-for="(item, index) in comments"
                  :key="index"
                >
                  <div class="d-flex w-100 justify-content-between">
                    <strong>{{ item.memberId }}</strong>
                    <small>{{ item.regTime }}</small>
                  </div>
                  <small class="d-flex w-100 p-3">{{
                    item.commentContent
                  }}</small>
                </b-list-group-item>
              </b-list-group>
            </div>
            <div class="form-group mb-3 comment-input" align="left">
              <input
                type="text"
                id="comment"
                class="form-control"
                v-model="comment"
                placeholder="댓글을 입력해 주세요."
                required="required"
                style="width: 90%; float: left"
              />
              <b-button class="mx-3" @click="writeCommentBtn">입력</b-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Board detail End -->
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      item: {},
      comment: "",
      comments: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["memberInfo"]),
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
          title: "자유게시판 정보 불러오기 에러 발생!!",
          text: "관리자에게 문의해주세요.",
          icon: "error",
        });
      });
    http
      .get(`/board/comment/${this.$route.query.boardId}`)
      .then(({ data }) => {
        this.comments = data;
      })
      .catch(({ error }) => {
        console.log(error);
        swal({
          title: "댓글 불러오기 에러 발생!!",
          text: "관리자에게 문의해주세요.",
          icon: "error",
        });
      });
  },
  methods: {
    listBtn() {
      this.$router.push("/board");
    },
    async delBtn() {
      await swal({
        title: "글을 삭제하시겠습니까???",
        text: "삭제하시면 다시 복구시킬 수 없습니다.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      }).then((willDelete) => {
        if (willDelete) {
          this.deleteConnection();
        }
      });
    },
    async deleteConnection() {
      await http
        .delete(`board/${this.item.boardId}`)
        .then(({ data }) => {
          if (data === "success") {
            swal({
              title: "글 삭제 성공!!",
              icon: "success",
            });
          } else {
            swal({
              title: "글 삭제 실패!!",
              text: "다시 시도해주세요.",
              icon: "error",
            });
          }
        })
        .catch(({ error }) => {
          console.log(error);
          swal({
            title: "글 삭제 실패!!",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        });
      this.$router.push("/board");
    },
    modifyBtn() {
      this.$router.push(`/board/modify?boardId=${this.item.boardId}`);
    },
    writeCommentBtn() {
      if (this.memberInfo == null) {
        swal({
          title: "로그인 후에 이용가능합니다.",
          text: "로그인 해주세요.",
          icon: "warning",
        });
        this.comment = "";
        return;
      }

      http
        .post("/board/comment", {
          boardId: this.item.boardId,
          memberId: this.memberInfo.memberId,
          commentContent: this.comment,
          regTime: "",
        })
        .then(({ data }) => {
          if (data === "success") {
            http
              .get(`/board/comment/${this.$route.query.boardId}`)
              .then(({ data }) => {
                this.comments = data;
              })
              .catch(({ error }) => {
                console.log(error);
                swal({
                  title: "댓글 불러오기 에러 발생!!",
                  text: "관리자에게 문의해주세요.",
                  icon: "error",
                });
              });
            this.comment = "";
            swal({
              title: "댓글 작성 성공!!",
              icon: "success",
            });
          } else {
            swal({
              title: "댓글 작성 실패!!",
              text: "다시 시도해주세요.",
              icon: "error",
            });
          }

          this.$forceUpdate();
        })
        .catch(({ error }) => {
          console.log(error);
          swal({
            title: "댓글 작성 에러 발생!! ",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        });
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

.comment-input {
  height: 30px;
  line-height: 30px;
}
</style>
