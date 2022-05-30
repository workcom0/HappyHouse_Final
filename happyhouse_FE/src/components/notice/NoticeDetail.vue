<template>
  <!-- ======= 공지사항 detail Start ======= -->
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
                    :value="item.subject"
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
                    :value="item.content"
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
              v-if="memberInfo != null && memberInfo.isAdmin == 1"
              >수정</b-button
            >
            <b-button
              class="mx-2 my-2"
              @click="delBtn"
              v-if="memberInfo != null && memberInfo.isAdmin == 1"
              >삭제</b-button
            >
            <b-button class="mx-2 my-2" @click="listBtn">목록</b-button>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- 공지사항 detail End -->
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "NoticeDetail",
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
          title: "공지사항 정보 불러오기 에러 발생!!",
          text: "관리자에게 문의해주세요.",
          icon: "error",
        });
      });
  },
  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },
  methods: {
    listBtn() {
      this.$router.push("/notice");
    },
    async delBtn() {
      await http
        .delete(`notice/${this.item.noticeNo}`)
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

      this.$router.push("/notice");
    },
    modifyBtn() {
      this.$router.push(`/notice/modify?noticeNo=${this.item.noticeNo}`);
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
