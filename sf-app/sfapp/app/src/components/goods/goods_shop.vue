<template>
  <div class="shop-wrapper">
    <div class="shop-desc-wrapper">
      <img :src="shopLogo">
      <div class="shop-desc">
        <span class="name">{{shopName}}</span>
        <p class="desc" :class="{'expand':isExpand}">阿瑞纳诞生于1973年，是以将泳装本身作为武器的水上运动为起源的现代运动休闲品牌，再争夺再争夺再争夺天下无贼</p>
        <div class="expand-hide-wrapper">
          <div class="empty"></div>
          <div class="expand-hide" @click="expandShopDesc">
            <span class="txt">展开</span>
            <img v-if="!isExpand" src="../../assets/image/goods/arrow_down.png">
            <img v-if="isExpand" src="../../assets/image/goods/arrow_up.png">
          </div>
        </div>

      </div>
    </div>
    <div class="shop-info-wrapper">
      <div class="person-num-wrapper">
        <span class="number">6.4万</span><span class="txt">关注人数</span>
      </div>
      <div class="goods-num-wrapper">
        <span class="number">3002</span><span class="txt">全部商品</span>
      </div>
      <div class="rated-wrapper">
        <div class="total-wrapper">
          <div class="item-wrapper">
            <span class="txt-total">综合</span><span class="score-total">9.6</span>
          </div>
          <div class="item-wrapper">
            <span class="txt">商品</span><span class="score">4.9</span>
          </div>
          <div class="item-wrapper">
            <span class="txt">服务</span><span class="score">4.6</span>
          </div>
          <div class="item-wrapper">
            <span class="txt">物流</span><span class="score">4.4</span>
          </div>
        </div>
      </div>
    </div>
    <div class="btn-wrapper">
      <div class="contact-seller" @click="contactSeller">
        <div class="img-wrapper">
          <img src="../../assets/image/goods/icon_customer_service.png">
        </div>
        <span class="txt">联系卖家</span>
      </div>
      <div class="to-shopping" @click="toShopping">
        <div class="img-wrapper">
          <img src="../../assets/image/goods/icon-in-shop.png">
        </div>
        <span class="txt">进店逛逛</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
    export default{
        props:{
            shopid:'',
          shopName:'',
          shopLogo:''
        },
        data(){
            return {}
        },
        components: {},
        methods: {},
        computed: {},
        created(){

        }
    }
</script>

<style lang="less">

</style>
