<template>
  <div id="goods">
    <div class="goods-header">
      <img class="back" @click="back" src="../../assets/image/set/Back_btn_img.png">
      <div class="tab-wrapper">
        <div class="tab-item">
          <a @click="changeIndex=1" :class="{'active':changeIndex==1}" replace>商品</a>
        </div>
        <div class="tab-item">
          <a @click="changeIndex=2" :class="{'active':changeIndex==2}" replace>详情</a>

        </div>
        <div class="tab-item">
          <a @click="changeIndex=3" :class="{'active':changeIndex==3}" replace>评价</a>

        </div>
      </div>
      <img class="shop-cart" @click="clickShopCart" src="../../assets/image/goods/menu_shopping_cart.png">
      <img class="menu" @click="clickMore" src="../../assets/image/goods/menu_more.png">
    </div>
    <!--<router-view></router-view>-->

    <!--<scroller lock-x scrollbar-y :use-pullup="true" v-model="scrollerStatus" :pullup-config="pullupConfig"
              @on-pullup-loading="up" ref="scroller">-->
    <v-info ref="goodsinfo" :changeIndex="changeIndex" :good="goodInfo" v-on:child-update_index="updateIndex"></v-info>
    <!--</scroller>-->
    <v-detail ref="goodsdetail" :detail="goodInfo.phone_describe?goodInfo.phone_describe:goodInfo.description" :changeIndex="changeIndex"></v-detail>
    <v-comment></v-comment>


    <div class="goods-footer">
      <div class="customer-service" @click="contact">
        <div class="img-wrapper">
          <img src="../../assets/image/goods/footer_customer_service.png">
        </div>
        <span class="txt">客服</span>
      </div>
      <div class="shopping" @click="shopping">
        <div class="img-wrapper">
          <img src="../../assets/image/goods/footer_shopping.png">
        </div>
        <span class="txt">店铺</span>
      </div>
      <div class="mark" @click="mark">
        <div class="img-wrapper">
          <img src="../../assets/image/goods/footer_star.png">
        </div>
        <span class="txt">{{isFollowWare?'已收藏':'收藏'}}</span>
      </div>
      <span class="add-in-cart" @click="addInCart" :class="{'yellow':!(goodInfo.state==1&&goodInfo.stocks>0)}">
        {{goodInfo.state==1&&goodInfo.stocks>0?'加入购物车':goodInfo.stocks<=0?'到货通知':'商品已下架'}}
      </span>
      <span class="buy-now" @click="buyNow" v-if="goodInfo.state==1&&goodInfo.stocks>0">立即购买</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import VComment  from './goods_comment.vue'
  import VDetail from './goods_detail.vue'
  import VInfo from './goodsInfo.vue'
  import {Scroller} from 'vux'
  export default{
    components: {
      VComment, VDetail, VInfo, Scroller
    },
    data(){
      return {
        sku: '',
        changeIndex: 1,
        goodInfo: {description: '', price: 0},
        userInfo: {},
        isFollowWare: false,
        scrollerStatus: {
          pullupStatus: 'default',

        },
        pullupConfig: {
          content: '上拉加载内容',
          pullUpHeight: 60,
          height: 40,
          autoRefresh: false,
          downContent: '松开刷新',
          upContent: '上拉加载内容',
          loadingContent: '正在加载',
          clsPrefix: 'xs-plugin-pullup-'
        }
      }
    },
    methods: {
      initData(){
        var that = this;
        this.$post(this.$interface_url.product.detail, {sku: this.sku}, function (data, success) {
          if (success && data) {
            that.goodInfo = data;
          }
          else {
            that.$vux.toast.show({text: '数据加载失败', type: 'warn'});
          }
        });
        if (that.userInfo && this.userInfo.timeout > Date.now()) {
          this.$post(this.$interface_url.user.isFollowWare, {
            token: that.userInfo.token,
            sku: that.sku
          }, function (data, success) {
            if (success) {
              that.isFollowWare = data;
            }
          });
        }
      },
      up(){

      },
      //返回
      back(){
        history.go(-1);
      },
      //商品/详情/评价
      selectTab(item){
        this.type = item;
      },
      //菜单购物车
      clickShopCart(){
       this.$router.push({name:'cart'});
      },
      //菜单更多
      clickMore(){
        this.$vux.toast.show({
          text: 'click menu more icon',
          type: 'text',
          position: 'middle'
        });
      },
      //联系客服
      contact(){
        this.$vux.toast.show({
          text: '联系客服',
          type: 'text',
          position: 'middle'
        });
      },
      //店铺
      shopping(){

      },
      //收藏
      mark(){//商品收藏
        if (this.userInfo) {
          var that = this;
          if (this.userInfo.timeout && this.userInfo.timeout < Date.now()) {
            this.$router.push({name: 'login'});
          } else {
            this.$post(this.isFollowWare?this.$interface_url.user.deleteFollowWare:this.$interface_url.user.followWare, {
              token: this.userInfo.token,
              sku: this.sku
            }, function (data, success) {
              if (success) {
                that.isFollowWare=!that.isFollowWare;
              }
              else {
                that.$vux.toast.show({text: data, width: '16em'});
              }
            });
          }
        } else {
          this.$router.push({name: 'login'});
        }

      },
      //加入购物车
      addInCart(){
        if(this.goodInfo.state==1&&this.goodInfo.stocks>0) {
          console.log(this.goodInfo)
          var good = {
            shopid: this.goodInfo.shopid,
            shopName: this.goodInfo.shopName,
            sku: this.goodInfo.sku,
            price: this.goodInfo.price,
            pName: this.goodInfo.pName,
            colorName: this.goodInfo.colorName,
            specName: this.goodInfo.specName,
            colorPic: this.goodInfo.imgs?this.goodInfo.imgs[0]:'',
            number:1

          };

          this.$store.dispatch('addToCart', good);
        }
        else if(this.goodInfo.state!=1){
            alert('下架');
        }
        else if(this.goodInfo.stocks<=0){
            alert('缺货');
        }
      },
      updateIndex(index){
          this.changeIndex=index;
      },
      //立即购买
      buyNow(){
       if(this.goodInfo.state==1&&this.goodInfo.stocks>0){
         this.$router.push({name:'orderSubmit',query:{sku:this.goodInfo.sku}});
       }
      }
    },
    computed: {},
    created(){


    },
    mounted(){
      this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      this.sku = this.$route.params.sku;
      this.initData();
    },
    watch: {
      '$route': {//监测用户输入，重置登录按钮状态
        handler: function (val, oldVal) {
         if(val.params.sku!=oldVal.params.sku)
         {
           this.sku = this.$route.params.sku;
           this.initData();
           document.documentElement.scrollTop = document.body.scrollTop =0;
         }
        },
        deep: true
      },
      deep: true
    }

  }

</script>

<style lang="less" rel="stylesheet/less">
@import "../../assets/css/good.less";
</style>

