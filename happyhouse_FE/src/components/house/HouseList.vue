<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
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
          v-if="!likes[(currentPage - 1) * perPage + row.index]"
          @click="likedeal(row.index, row.item)"
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
          @click="likedeal(row.index)"
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
    <b-modal :id="infoModal.id" :title="infoModal.title" ok-only size="xl">
      <pre v-html="infoModal.content"></pre>
    </b-modal>
  </b-container>
  <b-container v-else-if="isLoading">
    <div>
      <b-spinner
        style="width: 3rem; height: 3rem"
        label="Large Spinner"
        variant="warning"
      ></b-spinner>
    </div>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col
        ><div class="alert alert-secondary" show>
          주택 목록이 없습니다.
        </div></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import axios from "axios";
import swal from "sweetalert";

export default {
  name: "HouseList",
  components: {},
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      infoModal: {
        id: "info",
        title: "",
        content: "",
      },
      disabled: [],
      deleteLike: {
        aptNo: null,
        memberId: null,
      },
      aptNum: null,
    };
  },
  created() {
    this.CLEAR_HOUSE_LIST();
  },
  watch: {
    houses() {
      this.currentPage = 1;
      this.isLoding = false;
    },
    isLoading() {
      this.isLoding;
    },
  },
  computed: {
    ...mapState("houseStore", ["houses", "curPoint", "likes", "isLoading"]),
    ...mapState("memberStore", ["memberInfo"]),
    rows() {
      return this.items.length;
    },
    items: function () {
      var tmp = new Array();
      for (let i = 0; i < this.houses.length; i++) {
        tmp[i] = {
          번호: i + 1,
          아파트명: this.houses[i].apartmentName,
          주소:
            this.houses[i].sidoName +
            " " +
            this.houses[i].gugunName +
            " " +
            this.houses[i].dongName +
            " " +
            this.houses[i].jibun,
          거래가: this.houses[i].dealAmount,
          거래일자: this.toStringByFormatting(
            new Date(
              this.houses[i].dealYear,
              this.houses[i].dealMonth,
              this.houses[i].dealDay,
            ),
            ".",
          ),
          상세정보: "",
          관심매물: "",
        };
      }
      return tmp;
    },
  },
  methods: {
    ...mapActions("houseStore", ["deleteLikeList", "selectLikeList"]),
    ...mapMutations("houseStore", ["CLEAR_HOUSE_LIST", "INIT_LIKE_LIST"]),
    call(data) {
      this.$store.state.curPoint = data.번호;
    },
    async info(index, item, button) {
      await this.getNaverInfo(item.아파트명);
      this.infoModal.title = `상세보기`;
      this.infoModal.content = `
                                      <iframe class="mb-3" src="https://land.naver.com/info/groundPlanGallery.naver?rletNo=${
                                        this.aptNum
                                      }&ptpId=17&newComplex=Y&expand=false&buildNo=1" width="100%" height="600" align="right">
                                      </iframe>
                                      <table class="table-naver" align="center">
                                          <tr>
                                            <th>아파트명</th>
                                            <td>${item.아파트명} 아파트</td>
                                          </tr>
                                          <tr>
                                            <th>주소</th>
                                            <td>${item.주소}</td>
                                          </tr>
                                          <tr>
                                            <th>건축년도</th>
                                            <td>${
                                              this.houses[
                                                (this.currentPage - 1) *
                                                  this.perPage +
                                                  index
                                              ].buildYear
                                            }</td>
                                          </tr>
                                          <tr>
                                            <th>거래일자</th>
                                            <td>${item.거래일자}</td>
                                          </tr>
                                          <tr>
                                            <th>거래가</th>
                                            <td>${item.거래가}</td>
                                          </tr>
                                          <tr>
                                            <th>전용면적</th>
                                            <td>${
                                              this.houses[
                                                (this.currentPage - 1) *
                                                  this.perPage +
                                                  index
                                              ].area
                                            }</td>
                                          </tr>
                                          <tr>
                                            <th>층</th>
                                            <td>${
                                              this.houses[
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
    likedeal(index, item) {
      if (this.memberInfo == null) {
        swal({
          title: "찜하기 실패!",
          text: "로그인이 필요한 서비스입니다.",
          icon: "info",
        });
      }
      const idx = (this.currentPage - 1) * 10 + index;
      if (this.likes[idx]) {
        this.deleteLike.aptNo = this.houses[idx].no;
        this.deleteLike.memberId = this.memberInfo.memberId;
        this.deleteLikeList(this.deleteLike);
        this.$set(this.likes, idx, !this.likes[idx]);
      } else {
        console.log("item : " + item);
        console.log(item);
        this.deleteLike.aptNo = this.houses[idx].no;
        this.deleteLike.memberId = this.memberInfo.memberId;
        this.selectLikeList(this.deleteLike);
        this.$set(this.likes, idx, !this.likes[idx]);
      }
    },
    async getNaverInfo(aptName) {
      await axios
        .get(
          "https://cors-anywhere.herokuapp.com/https://new.land.naver.com/api/search?keyword=" +
            aptName,
          {
            headers: {
              method: "GET",
              Accept: "application/json",
            },
          },
        )
        .then((response) => {
          this.aptNum = response.data.complexes[0].complexNo;
        });
    },
    leftPad(value) {
      if (value >= 10) {
        return value;
      }
      return `0${value}`;
    },
    toStringByFormatting(source, delimiter = "-") {
      const year = source.getFullYear();
      const month = this.leftPad(source.getMonth() + 1);
      const day = this.leftPad(source.getDate());
      return [year, month, day].join(delimiter);
    },
  },
};
</script>

<style>
#modal-naver {
  width: 100%;
  height: 0;
}
.table-naver {
  width: 100%;
  border-collapse: separate;
  border-radius: 8px;
}
.table-naver th,
.table-naver td {
  border-bottom: 1px solid #c2c1c1;
  padding: 10px;
  text-align: center;
}
.table-naver th {
  background-color: rgb(242, 239, 239);
}
</style>
