<template>
  <div id="app">
    <HeaderView></HeaderView>
    <router-view></router-view>
    <FooterView></FooterView>
  </div>
</template>

<script>
import HeaderView from "@/components/common/HeaderView.vue";
import FooterView from "@/components/common/FooterView.vue";

export default {
  name: "App",
  components: {
    HeaderView,
    FooterView,
  },
};
</script>

<style>
a {
  text-decoration: none !important;
}
</style>
