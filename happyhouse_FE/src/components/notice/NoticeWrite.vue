<template>
  <!-- ======= 공지사항 write Start ======= -->
  <section id="pages-details" class="pages-details">
    <div class="container">
      <div class="col-lg-12" align="center" style="margin: auto">
        <h3>공지사항 등록</h3>

        <div class="form-group mb-3 mt-3" align="left">
          <label for="subject"></label>
          <input
            type="text"
            class="form-control"
            v-model="subject"
            placeholder="제목을 입력해 주세요."
            required="required"
          />
        </div>
        <div class="form-group mb-3" align="left">
          <label for="content"></label>
          <textarea
            class="form-control"
            rows="15"
            v-model="content"
            placeholder="내용을 입력해 주세요."
            required="required"
          ></textarea>
        </div>
        <div>
          <button type="button" @click="listBtn" class="btn btn-secondary m-3">
            취소
          </button>
          <button type="button" @click="writeBtn" class="btn btn-warning">
            글작성
          </button>
        </div>
      </div>
    </div>
  </section>
  <!-- 공지사항 write End -->
</template>

<script>
import http from "@/api/http";
import swal from "sweetalert";

export default {
  name: "NoticeWrite",
  data() {
    return {
      subject: "",
      content: "",
    };
  },
  methods: {
    writeBtn() {
      http
        .post("/notice", {
          noticeNo: 0,
          memberId: "admin",
          subject: this.subject,
          content: this.content,
          regTime: "",
        })
        .then(({ data }) => {
          if (data === "success") {
            swal({
              title: "글 삭제 성공!!",
              icon: "success",
            });
          } else {
            swal({
              title: "글 작성 실패!!",
              text: "다시 시도해주세요.",
              icon: "error",
            });
          }

          this.$router.push("/notice");
        })
        .catch(({ error }) => {
          console.log(error);
          swal({
            title: "글 작성 에러 발생!!",
            text: "관리자에게 문의해주세요.",
            icon: "error",
          });
        });
    },
    listBtn() {
      this.$router.push("/notice");
    },
  },
};
</script>

<style></style>
