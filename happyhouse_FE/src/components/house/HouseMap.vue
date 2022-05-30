<template>
  <div>
    <div id="map" style="width: 80%; height: 300px" class="mb-5"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
var map;

export default {
  data() {
    return {
      markers: [],
      positions: [],
    };
  },
  computed: {
    ...mapState("houseStore", ["houses", "curPoint"]),
    curPoint() {
      return this.$store.state.curPoint;
    },
  },
  watch: {
    //houses 정보 바뀌면 마커 표시하도록 함
    houses() {
      var tmp = new Array();
      for (let i = 0; i < this.houses.length; i++) {
        tmp[i] = {
          latlng: new kakao.maps.LatLng(this.houses[i].lat, this.houses[i].lng),
        };
      }
      this.positions = tmp;
      this.displayMarker(this.positions);
    },

    //클릭한 아파트 위치를 중심 좌표로 이동
    curPoint(value) {
      map.panTo(this.positions[value - 1].latlng);
    },
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      map = new kakao.maps.Map(container, options);
    },
    addScript() {
      const script = document.createElement("script");

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=caf24fe8a6037056de5ad4da53f8db01&libraries=services";
      document.head.appendChild(script);
    },
    displayMarker(positions) {
      //기존 마커 지우기
      for (var k = 0; k < this.markers.length; k++) {
        this.markers[k].setMap(null);
      }

      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      var imageSize = new kakao.maps.Size(24, 35);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      for (var i = 0; i < positions.length; i++) {
        var marker = new kakao.maps.Marker({
          map: map,
          position: positions[i].latlng,
          image: markerImage,
        });

        var infowindow = new kakao.maps.InfoWindow({
          content: `<div>${this.houses[i].apartmentName}<div>`, // 인포윈도우에 표시할 내용
        });

        // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
        // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        (function (marker, infowindow) {
          // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
          kakao.maps.event.addListener(marker, "mouseover", function () {
            infowindow.open(map, marker);
          });

          // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
          kakao.maps.event.addListener(marker, "mouseout", function () {
            infowindow.close();
          });
        })(marker, infowindow);

        this.markers[i] = marker;
        if (i == 0) {
          map.setCenter(positions[i].latlng);
        }
      }
    },
  },
};
</script>
