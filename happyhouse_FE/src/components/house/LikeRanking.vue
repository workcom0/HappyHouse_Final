<template>
  <vue-swimlane :words="[]" v-if="!this.ranking"></vue-swimlane>
  <vue-swimlane :words="this.rankingList" v-else></vue-swimlane>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  data() {
    return {
      rankingList: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["ranking"]),
  },
  created() {
    this.getLikeRankingList();
    for (var i = 0; i < this.ranking.length; i++) {
      this.rankingList[i] =
        i +
        1 +
        "위 " +
        this.ranking[i].aptName +
        " #" +
        this.ranking[i].aptNo.substr(-7);
    }
  },
  methods: {
    ...mapActions(houseStore, ["getLikeRankingList"]),
  },
};
</script>

<style></style>
