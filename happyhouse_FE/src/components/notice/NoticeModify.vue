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
                    v-model="item.subject"
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
                    v-model="item.content"
                    rows="15"
                    id="content"
                    name="content"
                  ></textarea>
                </td>
              </tr>
            </table>
          </div>
          <div>
            <b-button
              class="mx-2 my-2"
              @click="modifyBtn"
              v-if="memberInfo.isAdmin == 1"
              >수정</b-button
            >
            <b-button
              class="mx-2 my-2"
              @click="cancelBtn"
              v-if="memberInfo.isAdmin == 1"
              >취소</b-button
            >
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- 공지사항 Modify End -->
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "NoticeModify",
  data() {
    return {
      item: {},
    };
  },
  created() {
    http
      .get(`/notice/${this.$route.query.noticeNo}`)
      .then(({ data }) => {
        this.item = data;
      })
      .catch(({ error }) => {
        console.log(error);
        swal({
          title: "공지사항 불러오기 에러 발생!!",
          text: "관리자에게 문의해주세요.",
          icon: "error",
        });
      });
  },
  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },
  methods: {
    async modifyBtn() {
      await http
        .put(`/notice/${this.$route.query.noticeNo}`, {
          noticeNo: this.item.noticeNo,
          userId: this.item.memberId,
          subject: this.item.subject,
          content: this.item.content,
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
            title: "글 수정 에러 발생!!",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        });
      this.$router.push(`/notice/detail?noticeNo=${this.item.noticeNo}`);
    },
    cancelBtn() {
      this.$router.push(`/notice/detail?noticeNo=${this.item.noticeNo}`);
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
