<template>
  <div class="mine">
    <div class="mine_user" :class="{'not':!userInfo,'already':userInfo}">
      <!--已登录状态 already 未登录状态 not-->
      <div class="user_msg">
				<span class="head_img">
					<!--<img src="../../../../platforms/android/build/intermediates/res/merged/debug/drawable-land-mdpi/screen.png"/>-->
					<img :src="userInfo&&userInfo.portrait?userInfo.portrait:'../../static/image/friends_img.png'"
               v-show="userInfo&&userInfo.portrait&&userInfo.portrait.trim().length>0"/>

          <!--性别男显示 man 女显示 woman-->
					<i :class="{'woman':userInfo&&userInfo.sex==2,'man':userInfo&&userInfo.sex==1}"></i>
				</span>

        <p class="user_name">
					<span class="n_enter" v-show="!userInfo"><router-link to="/login">登录 </router-link>/<span>
          <router-link to="/regiter"> 注册</router-link></span></span>
					<span class="y_enter" v-show="userInfo">{{userInfo?userInfo.name:''}}</span>
				</p>
      </div>
      <div class="menu">
        <span class="menu_news"></span>
        <router-link to="/member/set"><span class="menu_set"></span></router-link>
      </div>
    </div>
    <div class="browsing_history">
      <ul>
        <li class="integral"><span>111111</span><i>我的积分</i></li>
        <li class="collect"><router-link to="/member/collect"><span>0000</span><i>商品收藏</i></router-link></li>
        <li class="attention"><router-link to="/member/shopfollow"><span>999</span><i>店铺关注</i></router-link></li>
      </ul>
    </div>
    <div class="buy_msg">
      <group>
        <cell title="我的订单" value="查看更多订单" is-link></cell>
      </group>
      <div class="line_item">
        <ul>
          <li class="obligation"><img src="../../assets/image/mine/obligation_img.png"/><i>待付款</i></li>
          <li class="shipments"><img src="../../assets/image/mine/shipments_img.png" alt="" /><i>待发货</i></li>
          <li class="delivery"><img src="../../assets/image/mine/delivery_img.png" alt="" /><i>待收货</i></li>
          <li class="evaluate"><img src="../../assets/image/mine/evaluate_img.png"/><i>待评价</i></li>
          <li class="sales"><img src="../../assets/image/mine/sales_img.png"/><i>退货/售后</i></li>
        </ul>
      </div>
    </div>
    <div class="rest_function">
      <ul>
        <li class="distributor"><img src="../../assets/image/mine/distributor_img.png"/><i>我的分销</i></li>
        <li class="discount_coupon"><img src="../../assets/image/mine/discount_coupon_img.png"/><i>优惠券</i></li>
        <li class="sfcard"><img src="../../assets/image/mine/sfcard_img.png"/><i>世峰E卡</i></li>
        <li class="service"><img src="../../assets/image/mine/service_img.png"/><i>客服</i></li>
      </ul>
    </div>
  </div>
</template>
<script>
  import {Group,Cell} from 'vux'

  export default {
    components: {
      Group,
      Cell
    },
    data () {
      return {
          userInfo:{}
      }
    },
    methods: {

    },
    created(){
        this.userInfo=this.$getStorage(this.$storage_key.USERINFO_STORAGE);

    }

  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/mine.less";
  i{
    font-style: normal;
  }
</style>
