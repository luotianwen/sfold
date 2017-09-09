<template>
  <div class="home">
    <!--header头部-->
    <v-indexheader></v-indexheader>
    <!--louti-->
    <div id="outer" class="outer p_fixed">
      <scroller lock-y :scrollbar-x=false :bounce="true">
        <div class="box">
          <div class="box">
            <div class="box-item" v-for="(item,$index) in adList">
              <a :class="{'active':flag==$index}" @click="changetop($index)">{{item.name}}</a>
            </div>
          </div>
        </div>
      </scroller>
    </div>
    <div class="msg_content">
      <div class="msg_content">
        <!--轮播图-->
        <div class="banner_nav">
          <swiper loop auto dots-position="center">
            <swiper-item class="black" v-for="banner in bannerList" :key="banner.id">
              <a :href="banner.imgLink"><img :src="banner.imgPath"/></a>
            </swiper-item>
          </swiper>
        </div>
        <!--子页面图标-->
        <div class="sf_activity">
          <div class="sf_activity_item">
            <a href="">
              <div class="sf_item_icon"><img src="../../assets/image/home/coupons_img_1.png"/></div>
              <div class="sf_item_lable">世峰领券</div>
            </a>
          </div>
          <div class="sf_activity_item">
            <a href="">
              <div class="sf_item_icon"><img src="../../assets/image/home/seckill_img_1.png"/></div>
              <div class="sf_item_lable">世峰秒杀</div>
            </a>
          </div>
          <div class="sf_activity_item">
            <a href="">
              <div class="sf_item_icon"><img src="../../assets/image/home/knowledge_img_1.png"/></div>
              <div class="sf_item_lable">户外知识</div>
            </a>
          </div>
        </div>
        <!--活动分类展示模板-->
        <div class="activity_show" v-for="item in adList">

          <v-indexitem :adManage="item"></v-indexitem>


          <!--其他分类统一模板-->

        </div>
      </div>
    </div>
    <div class="BackTop" @click="goTop">
      <img src="../../assets/image/home/top_img.png"/>
    </div>
  </div>
</template>

<script>
  import {Swiper, SwiperItem, Scroller, Spinner, XButton} from 'vux'
  import vIndexheader from './index-header.vue'
  import vIndexitem from './index-item.vue'
  import {rsaDes} from '../../common/js/storage'

  export default {
    components: {
      Swiper,
      SwiperItem,
      Scroller,
      Spinner,
      vIndexheader,
      vIndexitem
    },
    data(){
      return {
        bannerList: [],
        adList: [],
        flag: 0
      }
    },
    methods: {
      changetop(index){
        this.flag = index;
      },
      initData(){
        var _this = this;
        _this.$post(_this.$interface_url.query.homePage, {}, function (data, success) {
          if (success) {
            _this.bannerList = data.bannerList;
            _this.adList = data.adList;
            let today = new Date()
            data["timeout"] = today.setMinutes(today.getMinutes() + 10);//10分钟过期
            _this.$setStorage('homeData', data);
          }
          else {
            _this.$vux.toast.show({text: data, type: 'warn', width: '18em'})
          }
        })

      },
      goTop(){
        document.documentElement.scrollTop = document.body.scrollTop =0;
      }
    },
    created(){

    },
    mounted(){

      //首页内容缓存10分钟，如果缓存有内容，首先显示缓存内容，然后判断缓存是否过期，过期重新拉取
      let storage_data = this.$getStorage('homeData');
      if (storage_data) {
        //避免网络不好的情况下，首页显示空白。如果有缓存先加载上次浏览的记录，再去服务器拉取最新的数据
        this.bannerList = storage_data.bannerList;
        this.adList = storage_data.adList;
      }
      else if (storage_data && storage_data.timeout < new Date()) {
        var that = this;
        setTimeout(function () {
          that.initData();
        }, 3000)
      }
      else{
        this.initData();
      }

    }
  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/home.less";
</style>
