<template>
  <b-container v-if="likes && likes.length != 0" class="bv-example-row mt-3">
    <b-table
      hover
      :items="items"
      @row-clicked="call"
      :per-page="perPage"
      :current-page="currentPage"
    >
      <template #cell(상세정보)="row">
        <b-button
          size="sm"
          class="mr-1"
          @click="info(row.index, row.item, $event.target)"
        >
          상세정보
        </b-button>
      </template>
      <template #cell(관심매물)="row">
        <button
          size="sm"
          class="btn btn-outline-danger"
          @click="likeBtn(row.index)"
          v-if="disabled[row.index]"
        >
          찜
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-heart"
            viewBox="0 0 16 16"
          >
            <path
              d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"
            />
          </svg>
        </button>
        <button
          size="sm"
          class="btn btn-danger"
          @click="likeBtn(row.index)"
          v-else
        >
          찜
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-heart"
            viewBox="0 0 16 16"
          >
            <path
              d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"
            />
          </svg>
        </button>
      </template>
    </b-table>
    <b-pagination
      align="center"
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>
    <b-modal :id="infoModal.id" :title="infoModal.title" ok-only>
      <pre v-html="infoModal.content"></pre>
    </b-modal>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col
        ><div class="alert alert-secondary" show>
          찜한 매물이 없습니다.
        </div></b-col
      >
    </b-row>

    <b-modal
      :id="infoModal.id"
      :title="infoModal.title"
      ok-only
      @hide="resetInfoModal"
    >
      <pre>{{ infoModal.content }}</pre>
    </b-modal>
  </b-container>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      infoModal: {
        id: "info-modal",
        title: "",
        content: "",
      },
      disabled: [],
      deleteLike: {
        aptNo: null,
        memberId: null,
      },
    };
  },
  created() {
    this.getLikeList(this.memberInfo.memberId);
  },
  watch: {
    houses() {
      this.currentPage = 1;
    },
  },
  computed: {
    ...mapState("houseStore", ["houses", "curPoint", "likes"]),
    ...mapState(memberStore, ["memberInfo"]),
    rows() {
      return this.items.length;
    },
    items: function () {
      var tmp = new Array();
      for (let i = 0; i < this.likes.length; i++) {
        tmp[i] = {
          번호: i + 1,
          아파트명: this.likes[i].apartmentName,
          주소:
            this.likes[i].sidoName +
            " " +
            this.likes[i].gugunName +
            " " +
            this.likes[i].dongName +
            " " +
            this.likes[i].jibun,
          거래가: this.likes[i].dealAmount,
          거래일자:
            this.likes[i].dealYear +
            "." +
            this.likes[i].dealMonth +
            "." +
            this.likes[i].dealDay,
          상세정보: "",
          관심매물: false,
        };
      }
      return tmp;
    },
  },
  methods: {
    ...mapActions("houseStore", [
      "getLikeList",
      "deleteLikeList",
      "selectLikeList",
    ]),
    ...mapMutations("houseStore", ["SET_LIKE_LIST", "SET_MEMBER_ID"]),
    call(data) {
      this.$store.state.curPoint = data.번호;
    },
    info(index, item, button) {
      this.infoModal.title = `상세보기`;
      this.infoModal.content = `
                                  <table class="table-modal" align="center">
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">아파트명</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          item.아파트명
                                        } 아파트</td>
                                      </tr>
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">주소</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          item.주소
                                        }</td>
                                      </tr>
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">건축년도</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          this.likes[
                                            (this.currentPage - 1) *
                                              this.perPage +
                                              index
                                          ].buildYear
                                        }</td>
                                      </tr>
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">거래일자</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          item.거래일자
                                        }</td>
                                      </tr>
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">거래가</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          item.거래가
                                        }</td>
                                      </tr>
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">전용면적</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          this.likes[
                                            (this.currentPage - 1) *
                                              this.perPage +
                                              index
                                          ].area
                                        }</td>
                                      </tr>
                                      <tr>
                                        <td style="border: 1px lightgray solid; padding:10px">층</td>
                                        <td style="border: 1px lightgray solid; padding:10px">${
                                          this.likes[
                                            (this.currentPage - 1) *
                                              this.perPage +
                                              index
                                          ].floor
                                        }</td>
                                      </tr>
                                  </table>
                                `;
      this.$root.$emit("bv::show::modal", this.infoModal.id, button);
    },
    resetInfoModal() {
      this.infoModal.title = "";
      this.infoModal.content = "";
    },
    likeBtn(index) {
      if (!this.disabled[index]) {
        this.deleteLike.aptNo = this.likes[index].no;
        this.deleteLike.memberId = this.memberInfo.memberId;
        this.deleteLikeList(this.deleteLike);
        this.$set(this.disabled, index, !this.disabled[index]);
      } else {
        this.deleteLike.aptNo = this.likes[index].no;
        this.deleteLike.memberId = this.memberInfo.memberId;
        this.selectLikeList(this.deleteLike);
        this.$set(this.disabled, index, !this.disabled[index]);
      }
    },
  },
};
</script>

<style>
.clicked {
  color: red;
}
</style>
