<template>
  <div id="shopping-cart">
    <div class="shopping-header">
      <img class="back" v-show="" src="../../assets/image/set/Back_btn_img.png">
      <div class="empty"></div>
      <span class="title">购物车</span>
      <span class="edit" @click="isEdit=!isEdit" v-show="hasCart">{{isEdit?'完成':'编辑'}}</span>
      <img class="menu-msg" src="../../assets/image/cart/icon_msg.png" @click="showMessageMenu">
    </div>
    <!--商品列表-->
    <div class="content-wrapper" v-if="hasCart">
      <div class="content-wrapper" v-if="hasCart">
        <ul>
          <li class="goods-group-wrapper" v-for="(item, index) in cartInfo.cartInfo">
            <div class="title-wrapper">
              <img class="img-shop-option" @click="selectedShop(item.shopid)"
                   :src="selectImg(item.selected)">

              <span class="shop-name">{{item.shopName}}</span>
              <span class="get-coupon">领券</span>
            </div>
            <div class="shop-coupon-wrapper" v-if="true">
              <span class="shop-coupon-txt">店铺优惠</span>
              <span class="shop-coupon-content">满200减10元，有赠品</span>
            </div>
            <ul>
              <li v-for="(good, i) in item.wareList">
                <swipeout>
                  <swipeout-item @on-close="handleEvents('on-close')"
                                 @on-open="handleEvents('on-open')"
                                 transition-mode="follow">
                    <div class="swipe-button-wrapper" slot="right-menu">
                      <swipeout-button class="fav" @click.native="collectGoods(good.sku,item.shopid)">移入<br>收藏
                      </swipeout-button>
                      <swipeout-button class="delete" @click.native="deleteGood(good.sku,item.shopid)">删除
                      </swipeout-button>
                    </div>
                    <div class="goods-content-wrapper" slot="content">

                      <img class="img-goods-option" @click="selectedGood(good.sku,item.shopid)"
                           :src="selectImg(good.selected)">
                      <router-link :to="{name:isEdit?'':'Goods',params:{sku:good.sku}}">
                        <div class="goods-wrapper">
                          <img class="goods-img" :src="good.colorPic" width="100" height="100">
                          <div class="goods-desc">
                            <p class="goods-title">{{good.pName}}</p>
                            <span class="info">颜色:{{good.colorName}},规格:{{good.specName}}</span>
                            <div class="price-change-wrapper" v-show="good.price<good.cartPrice">
                              <span>已降</span><span class="rmb">¥</span><span
                              class="price">{{parseFloat(good.cartPrice-good.price).toFixed(2)}}</span>
                            </div>
                            <div class="price-wrapper">
                              <span class="rmb">¥</span><span
                              class="price">{{good.price}}</span>
                            </div>
                          </div>
                        </div>
                      </router-link>
                      <div class="cart-control-wrapper">
                        <cartcontrol :good="good" :shopId="item.shopid"></cartcontrol>
                      </div>
                    </div>
                  </swipeout-item>
                </swipeout>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
    <!--结账-->
    <div class="checkout-wrapper" v-if="!isEdit && hasCart">
      <img class="img-selected-all" @click="checkAll"
           :src="selectImg(cartInfo.isCheckAll)">
      <span class="txt-selected-all" @click="checkAll">全选</span>
      <div class="total-wrapper">
        <div class="total-money-wrapper">
          <span class="txt-total">合计:</span>
          <span class="rmb-total">¥</span><span class="total-money">{{cartInfo.money.toFixed(2)}}</span>
        </div>
        <div class="coupon-wrapper">
          <span class="txt-coupon">已优惠:</span>
          <span class="rmb">¥</span><span class="coupon-money">5</span>
        </div>
      </div>
      <div class="btn-checkout-wrapper" :class="{'selected':cartInfo.count>0}" @click="toSubOrder">
        <span class="btn-checkout">去结算</span><span class="selected-count">({{cartInfo.count}})</span>
      </div>
    </div>
    <!--编辑状态 菜单展示-->
    <div class="edit-menu-wrapper" v-if="isEdit && cartInfo.cartInfo">
      <img class="img-selected-all" @click="checkAll"
           :src="selectImg(cartInfo.isCheckAll)">
      <span class="txt-selected-all" @click="checkAll">全选</span>
      <span class="share" @click="shared">分享</span>
      <!--<span class="favorite" @click="collectGoods" v-if="showCollect">移入收藏</span>-->
      <span class="delete" @click="deleteSelectedGoods">删除</span>
    </div>
    <!--购物车为空时的页面展示-->
    <div v-show="!hasCart" class="empty-tip-wrapper">
      <div class="tip-wrapper">
        <img src="../../assets/image/cart/icon_empty_cart.png">
        <span class="txt">您的购物车是空的</span>
        <span class="stroll-again"><router-link to="/">再去逛逛</router-link></span>
      </div>
    </div>
    <share :url="shareUrl" :title="shareTitle" :description="shareDesc" :imgSrc="shareImg" ref="share"></share>
    <message-menu></message-menu>
    <message-menu ref="messagemenu"></message-menu>
  </div>

</template>

<script type="text/ecmascript-6">
  import {Swipeout, SwipeoutItem, SwipeoutButton} from 'vux'
  import cartcontrol from './cartcontrol.vue'
  import {isLogin} from '../../common/js/checkIsLogin'
  import {mapGetters} from 'vuex'
  import Share from '../common/share.vue'
  import messageMenu from '../common/messagemenu.vue'
  export default{
    computed: {
      ...mapGetters({
        cartInfo: 'getCartInfo',

      })
    },
    data(){
      return {
        userInfo: {},
        isEdit: false,
        showCollect: false,
        shareUrl: '',
        shareTitle: '',
        shareDesc: '',
        shareImg: '',
        hasCart: true
      }
    },
    methods: {
      showMessageMenu(){
        this.$refs.messagemenu.show();
      },
      shared(){
        if (this.cartInfo.count == 0) {
          this.$vux.toast.show({text: '还未选择任何商品', type: 'text'});
          return;
        }
        var cartid = ''
        this.cartInfo.cartInfo.forEach(function (shop, index) {
          shop.wareList.forEach(function (good, _index) {
            if (good.selected) {
              cartid += good.sku + ',';
            }
          })
        })

        this.shareUrl = 'sharecart/token=' + this.userInfo.token + '&cartid=' + cartid.substr(0, cartid.length - 1);
        this.shareTitle = this.userInfo.name + '的购物清单'
        this.shareDesc = '这是' + this.userInfo.name + '在世峰户外的购物清单，都是精选好货，赶快来看看...'
        this.shareImg = this.cartInfo.cartInfo[0].wareList[0].colorPic;
        console.log(this.$refs.share)
        this.$refs.share.show();
      },
      selectImg(val) {
        if (val) {
          return '../../static/image/goods_selected.png';
        } else {
          return '../../static/image/opt_img_2.png';
        }
      },
      checkAll(){
        this.$store.dispatch('selectedAllGood', !this.cartInfo.isCheckAll)
      },
      selectedGood(sku, shopid){
        this.$store.dispatch('selectedGood', {sku: sku, shopid: shopid})
      },
      selectedShop(shopid){
        this.$store.dispatch('selectedShop', shopid)
      },
      sysn_cartInfo(){
        if (isLogin()) {
          var that = this;
          that.$post(that.$interface_url.cart.cartWareList, {token: that.$getStorage(that.$storage_key.USERINFO_STORAGE).token}, function (data, success) {
            if (success) {
              if (data && data.length > 0) {
                that.$store.dispatch('syncCartInfo', data);
                that.hasCart = true;
              }
              else {
                that.hasCart = false;
              }
            }
            else if (data == '10004') {
              var returnurl = that.$route.path;
              that.$router.push({name: 'login', query: {returnurl: returnurl}});
            }
          });
        }
      },
      collectGoods(sku, shopid){//移入关注并将商品从购物车中删除
        if (isLogin()) {
          var that = this;
          this.$vux.confirm.show({
            content: '确定将选中的商品移入收藏?',
            onConfirm(){
              var shopItem = that.cartInfo.cartInfo.find(c => c.shopid == shopid);
              if (shopItem) {
                shopItem.goods.forEach(function (item, index) {
                  if (item.sku == sku) {
                    that.$post(that.$interface_url.user.followWare, {
                      token: that.userInfo.token,
                      sku: sku
                    }, function (data, success) {
                      if (success) {

                        that.$post(that.$interface_url.cart.deleteCartWare,
                          {token: that.userInfo.token, sku: sku}, function (data, success) {
                            if (success) {
                              shopItem.goods.splice(index, 1);
                              that.$setStorage(that.$storage_key.CARTINFO_STORAGE, that.cartInfo.cartInfo);
                              that.$vux.toast.show({text: '移入成功.', width: '16em'});
                            }
                          });
                      }
                    });
                  }
                })
              }
            },
            cancelText: '取消',
            confirmText: '确认'
          });
        }
        else {
          this.$router.push({
            name: 'login',
            query: {
              returnurl: this.$route.fullPath
            }
          });
        }
      },
      deleteSelectedGoods(){//删除选择的商品
        var that = this;
        if (that.cartInfo.count > 0) {
          this.$vux.confirm.show({
            content: '确定删除所选?',
            onConfirm(){
              that.$store.dispatch('deleteToCart');
              that.sysn_cartInfo();
            },
            cancelText: '取消',
            confirmText: '确认'
          });
        }
        else {
          that.$vux.toast.show({text: '未选中任何商品.', type: 'text'});
        }
      },
      deleteGood(sku, shopid){//删除指定商品
        this.$store.dispatch('deleteGood', {sku: sku, shopid: shopid})
      },
      toSubOrder(){
        if (this.cartInfo.count > 0) {

          this.$router.push({name: 'orderSubmit'});
        }
      }
    },
    components: {
      Swipeout,
      SwipeoutItem,
      SwipeoutButton,
      cartcontrol, Share, messageMenu
    },
    mounted(){


    },
    created(){
      this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      var that = this;
      if (isLogin()) {
        that.showCollect = true;
        that.sysn_cartInfo();
      }
      else {
        that.showCollect = false;
      }
    }
  }
</script>

<style lang="less" rel="stylesheet/less">
  @import "../../assets/css/cart.less";
</style>
