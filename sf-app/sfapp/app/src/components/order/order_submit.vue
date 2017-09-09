<!--订单提交页面-->
<template>

  <div id="order-submit">
    <!--header-->
    <div class="order-submit-header">
      <x-header :left-options="{showBack:true}">填写订单</x-header>
    </div>
    <!--content-->

    <div id="order-content-wrapper">

      <div class="order-content-wrapper">
        <!--地址信息-->
        <router-link :to="{path:'/member/address',query:{type:1}}">
          <div class="address-wrapper">
            <div class="content-wrapper" v-if="defaultAddress">
              <div class="user-info-wrapper">
                <span class="name">{{defaultAddress.contacts}}</span><span class="phone">{{defaultAddress.phone}}</span>
              </div>

              <div class="address-info-wrapper">
                <img class="img-location" src="../../assets/image/cart/icon_location.png">
                <p class="info">{{addressName+defaultAddress.address}}</p>
              </div>
            </div>
            <div class="content-wrapper" v-if="!defaultAddress">
              <div class="user-info-wrapper">
                <span>您还没有收获地址哦，赶快设置一个吧！</span>
              </div>


            </div>
            <div class="img-wrapper">
              <img src="../../assets/image/goods/arrow_right.png">
            </div>
          </div>
        </router-link>
        <!--购买商品信息列表-->
        <ul>
          <li class="goods-wrapper" v-for="orderInfo in orderList">
            <div class="shop-name">{{orderInfo.shopName}}</div>
            <div class="goods-info-wrapper" v-for="good in orderInfo.wareList">

              <img class="goods-img" :src="good.colorPic">
              <div class="goods-desc">
                <p class="goods-title">{{good.pName}}</p>
                <div class="price-wrapper">
                  <span class="rmb">¥</span>
                  <span class="price">{{good.price.toFixed(2)}}</span>
                  <span class="count">X{{good.number}}</span>
                </div>
              </div>

            </div>
            <!--赠品-->
            <!--   <div class="gift-wrapper">
                 <div class="txt-gift">赠品</div>
                 <ul class="gift-list">
                   <li class="gift-item" v-for="(gift,index) in goods.giftList">
                     <div class="gift-desc">
                       <span class="gift-name">{{gift.giftName}}</span>
                       <span class="gift-count">X{{gift.giftCount}}</span>
                     </div>
                   </li>
                 </ul>
               </div>-->
            <!--店铺优惠-->
            <div class="shop-coupon-wrapper">
              <span class="txt">店铺优惠</span>
              <span class="coupon-info">无</span>
              <img src="../../assets/image/goods/arrow_right.png">
            </div>
            <!--运费-->
            <div class="carriage-wrapper">
              <span class="txt">运费</span>
              <!--<span class="txt-courier">快递</span>-->
              <span class="rmb">¥</span>
              <span class="carriage"
                    v-if="orderInfo.shopWareFreights>0">{{orderInfo.shopWareFreights.toFixed(2)}}</span>
              <span style="color:#06b639" class="carriage" v-if="orderInfo.shopWareFreights==0">免运费</span>
            </div>
            <!--发票信息-->
            <div class="invoice-wrapper">
              <span class="txt">发票信息</span>
              <span class="invoice-info">不开发票</span>
              <img src="../../assets/image/goods/arrow_right.png">
            </div>
            <!--买家留言-->
            <div class="buyer-msg-wrapper">
              <span class="txt">买家留言:</span>
              <input class="buyer-msg" type="text" placeholder="对本次交易的说明" v-model="orderInfo.remark">
            </div>
            <!--商品小计-->
            <div class="group-total-wrapper">
              <span class="txt">共{{orderInfo.shopProCount}}件商品，小计:</span>
              <span class="rmb">¥</span>
              <span class="group-total-price">{{orderInfo.shopProMoney.toFixed(2)}}</span>
            </div>
          </li>
        </ul>
        <div class="other-wrapper">
          <!--优惠券-->
          <div class="coupon-wrapper">
            <span class="txt">优惠券</span>
            <span class="coupon-count">3张可用</span>
            <span class="used-state">未使用</span>
            <img src="../../assets/image/goods/arrow_right.png">
          </div>
          <!--世峰E卡-->
          <div class="sf-card-wrapper">
            <span class="txt">世峰E卡</span>
            <span class="coupon-count">1张可用</span>
            <span class="used-state">未使用</span>
            <img src="../../assets/image/goods/arrow_right.png">
          </div>
          <!--匿名购买-->
          <div class="anonymous-wrapper">
            <span class="txt">匿名购买</span>
            <group>
              <x-switch class="anonymous-switcher" title="111" v-model="anonymous"></x-switch>
            </group>
          </div>
        </div>
      </div>

    </div>

    <!--浮动地址提示-->
    <div class="float-address-wrapper" v-if="showBottomAddress&&defaultAddress">
      <span class="txt">送至: {{addressName+defaultAddress.address}}</span>
    </div>
    <!--footer 提交订单-->
    <div class="order-submit-wrapper">
      <div class="total-info-wrapper">
        <span class="buy-count-wrapper">共<span class="buy-count">{{orderCount}}</span>件 , 实付款:</span>
        <span class="rmb">¥</span><span class="total-price">{{orderMoney.toFixed(2)}}</span>
      </div>
      <span class="btn-submit-order" @click="settlementOrder">提交订单</span>
    </div>
  </div>

</template>

<script type="text/ecmascript-6">
  import {XHeader, XSwitch, Group, ChinaAddressData, Value2nameFilter as value2name} from 'vux'
  import {Scroller} from 'vux'
  import {isLogin} from '../../common/js/checkIsLogin'
  import {mapGetters} from 'vuex'
  export  default{
    computed: {
      ...mapGetters({
        cartInfo: 'getCartInfo',

      })
    },
    data(){
      return {
        anonymous: true,
        showBottomAddress: false,
        userInfo: {},
        defaultAddress: Object,
        orderList: {},
        addressName: '',
        orderCount: 0,
        orderMoney: 0,
        subState: false,
      }
    },
    components: {
      XHeader, XSwitch, Group, Scroller, ChinaAddressData, value2name
    },
    methods: {
      initOrder(){
        var that = this;
        var skus = [];
        var sku = that.$route.query.sku;
        if (sku) {
          skus.push({sku: sku, pcount: 1});
        }
        else {
          that.cartInfo.cartInfo.forEach(function (shop, index) {
            shop.wareList.forEach(function (good, _index) {
              if (good.selected) {
                skus.push({sku: good.sku, pcount: good.number});
              }
            })
          })
        }
        that.$post(that.$interface_url.order.preview, {
          token: that.userInfo.token,
          products: JSON.stringify(skus)
        }, function (data, success) {
          if (success) {
            if (!data.products) {
              that.$router.push({name: 'cart'});
              return;
            }
            data.products.forEach(function (shop, index) {
              let shopProCount = 0, shopProMoney = 0;
              shop.wareList.forEach(function (good, _index) {
                shopProCount += good.number;
                shopProMoney += parseInt(good.number) * parseFloat(good.price);
              })
              shop.shopProCount = shopProCount;
              shop.shopProMoney = shopProMoney;
              that.orderCount += shopProCount;

              that.orderMoney += shopProMoney + data.shopWareFreights.find(s => s.shopid == shop.shopid).freight;
              shop.shopWareFreights = data.shopWareFreights.find(s => s.shopid == shop.shopid).freight;
              shop.remark = '';
            })
            that.defaultAddress = data.address;
            that.orderList = data.products;
            that.addressName = that.getAddressName(data.address);

          }
          else {
            that.$vux.toast.show({text: data, type: 'warn'});
          }
        });
      },
      getAddressName(address){
        let addressName = [];
        addressName.push('' + address.province + '');
        addressName.push('' + address.city + '');
        addressName.push('' + address.area + '');
        return value2name(addressName, ChinaAddressData)
      },
      settlementOrder(){
        var that = this;
        if (this.defaultAddress && this.orderList && this.orderList.length > 0 && this.orderCount > 0 && this.orderMoney > 0 && !this.subState) {
          this.subState = true;
          var s_order = {};
          s_order.addressId = this.defaultAddress.id;
          s_order.anonymous = 2;//匿名购买(1:是；2否)
          s_order.couponId = 1;
          s_order.eCardId = 1;
          s_order.shops = [];
          this.orderList.forEach(function (shop, index) {
            var s_shop = {};
            s_shop.shopid = shop.shopid;
            s_shop.wares = [];
            s_shop.remark = shop.remark;
            var cart_shop=that.cartInfo.cartInfo.find(c=>c.shopid==shop.shopid);
            shop.wareList.forEach(function (good, _index) {
              s_shop.wares.push({sku: good.sku, pcount: good.number,cartId:cart_shop?cart_shop.wareList.find(w=>w.sku==good.sku).id:0});
            })
            s_order.shops.push(s_shop);
          })
          if (s_order) {
            that.$post(that.$interface_url.order.settlement, {
              token: that.userInfo.token,
              orderInfo: JSON.stringify(s_order)
            }, function (data, success) {
              if (success) {
                that.$router.push({name:'pay',params:{order:data.orderId}});
              }
              else {
                this.$vux.toast.show({text: data, type: 'warn'});
              }
              that.subState = false;
            });
          }
        }
        else {
          this.$vux.toast.show({text: '无效订单!', type: 'warn'});
        }
      }
    },
    mounted () {
      this.initOrder();
    },
    created(){
      var that = this;
      if (!isLogin()) {
        this.$router.push({
          name: 'login', query: {
            returnurl: this.$route.fullPath
          }
        });
      } else {
        this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      }
      window.onscroll = function () {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        if (t > 80) {
          that.showBottomAddress = true;
        }
        else {
          that.showBottomAddress = false;
        }
      }
    },
    filters: {}
  }
</script>

<style lang="less" rel="stylesheet/less">
  @import "../../assets/css/suborder.less";
</style>
