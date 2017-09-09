<template>
  <transition :name="changeIndex==1?'slide-left':'slide-right'">
    <div class="goods-info" v-show="changeIndex==1" ref="goodsinfo">
        <div class="goods-info">
            <!--轮播图-->
            <swiper height="375px" dots-position="center">
                <swiper-item class="swiper-img-list" v-for="(imgPath, index) in good.imgs" :key="index">
                    <img :src="imgPath">
                </swiper-item>
            </swiper>
            <!--商品描述-->
            <div class="goods-desc-wrapper">
                <div class="goods-title-wrapper">
                    <p class="goods-title">{{good.pName}}</p>
                    <div class="shared-wrapper" @click="shared">
                        <img src="../../assets/image/goods/detail_shared.png"><span class="shared-txt">分享</span>
                    </div>
                </div>
                <div class="full-cut-content">{{good.goods_subtitle}}</div>
                <div class="sale-price-wrapper">
                    <span class="rmb">¥</span><span class="sale-price">{{good.price.toFixed(2)}}</span>
                </div>
                <div class="old-price-wrapper">
                    <span class="old-price-txt">价格¥</span><span class="old-price">{{good.marketprice}}</span>
                </div>
                <div class="other-wrapper">
                    <span class="express-fee">快递:0.00</span>
                    <span class="sale-count">月销102</span>
                    <span class="producer" @click="changeAddress">福建泉州
                    <x-address title="只显示省市" v-model="defaultAddress" raw-value :list="addressData" hide-district></x-address>
                    </span>
                </div>
            </div>
            <!--购物券，促销信息-->
            <goods-coupon :sku="good.sku" :shopid="good.shopid" :cid="good.cid"></goods-coupon>
            <!--售后保障-->
            <div class="after-sales-wrapper">
                <span class="txt">售后保障</span>
                <span class="sales-content">{{good.after_service?good.after_service:'正品保证，七天退换货'}}</span>
                <img src="../../assets/image/goods/icon_more_content.png">
            </div>
            <!--产品信息-->
            <goods-param :pid="good.pid" :colorName="good.colorName" :sku="good.sku"
                         :name="good.pName" :specName="good.specName"  v-on:child-update="updateColor" :state="good.state"></goods-param>
            <!--商品评价-->
            <goods-rated :pid="good.pid" v-on:child-update-index="_updateIndex"></goods-rated>
            <!--店家信息-->
            <goods-shop :shopid="good.shopid" :shopName="good.shopName" :shopLogo="good.shopLogo"></goods-shop>
            <!--为你推荐、排行榜-->
            <good-recommend :shopid="good.shopid"></good-recommend>
            <!--上拉查看图文详情-->
            <div class="pull-up-wrapper">
                <span class="txt">上拉查看图文详情</span>
                <img src="../../assets/image/goods/arrow_up.png">
            </div>
        </div>
      <share :url="url" :title="good.pName" :description="good.goods_subtitle" :imgSrc="good.imgs[0]" ref="share"></share>

    </div>
  </transition>
</template>

<script type="text/ecmascript-6">
    import { Swiper, SwiperItem, XButton, Flexbox, FlexboxItem, XAddress, ChinaAddressV3Data, Value2nameFilter as value2name} from 'vux'
    import star from '../star/star.vue'
    import GoodsCoupon from './goods_coupon.vue'
    import GoodsParam from './goods_param.vue'
    import GoodsRated from './goods_rated'
    import GoodsShop from './goods_shop.vue'
    import GoodRecommend from './goods_recommend.vue'
    import Share from '../common/share.vue'

    export default{
      props: {
        changeIndex: '',
        good: {}
      },
      components: {
        Swiper,
        SwiperItem,
        XButton,
        Flexbox,
        FlexboxItem,
        star,
        GoodsCoupon, GoodsParam, GoodsRated, GoodsShop, GoodRecommend, Share,XAddress,ChinaAddressV3Data,value2name
      },
      data(){
        return {
            url:'',
          showAddress:false,
          addressData: ChinaAddressV3Data,
          defaultAddress:[]
        }
      },
      computed: {
      },
      methods: {
        //分享
        shared(){

          this.$refs.share.show();
        },
        updateColor(data){//提供给子组件更新的方法
          this.good.imgs = data.imgs;
          this.good.colorid = data.colorid;
          this.good.specid = data.specid;
          this.good.sku = data.sku;
          this.good.colorName = data.colorName;
          this.good.specName = data.specName;
          this.good.price = data.price;
          this.good.stocks = data.stocks;

        },
        _updateIndex(index){
          this.$emit('child-update_index',index)
        },
        changeAddress(){
            this.showAddress=true;
        }
      },
      created(){

      }
    };
</script>

<style lang="less" rel="stylesheet/less">




</style>
