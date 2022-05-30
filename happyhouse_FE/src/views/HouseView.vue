<template>
  <div>
    <PageTitleView title="거래가 검색"></PageTitleView>
    <section id="pages-details" class="pages-details">
      <div class="container">
        <div class="col-lg-12" align="center" style="margin: auto">
          <div class="card-body" style="min-width: 500px">
            <div
              class="col-xl-4 col-md-7 mt-5"
              style="
                margin: auto;
                background-color: #e6e4e4;
                color: black;
                font-weight: bold;
                opacity: 0.7;
                border-radius: 15px;
              "
            >
              <LikeRanking></LikeRanking>
            </div>
            <br />
            <HouseMap> </HouseMap>
            <HouseSearchBar></HouseSearchBar>
            <HouseList></HouseList>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import PageTitleView from "@/components/common/PageTitleView.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import LikeRanking from "@/components/house/LikeRanking.vue";

export default {
  name: "HouseView",
  components: {
    PageTitleView,
    HouseMap,
    HouseSearchBar,
    HouseList,
    LikeRanking,
  },
};
</script>
<style scoped></style>
