<template>
  <div class="discount-info-wrapper">
    <div class="coupon-wrapper">
      <span class="txt">购物券</span>
      <div class="coupon-list">
        <div class="item-wrapper" v-for="coupon in coupons">
          <img src="../../assets/image/goods/coupon_left.png">
          <div class="txt-wrapper">
            <span class="item">{{coupon.name}}</span>
          </div>
          <img src="../../assets/image/goods/coupon_right.png">
        </div>

      </div>
      <span class="btn-get-coupon" @click="getCoupon">领取</span>
    </div>
    <div class="discount-wrapper">
      <span class="discount-txt">促销</span>
      <ul>
        <li class="discount-item" v-for="(item, index) in coupons">
          <span class="title">{{item.name}}</span>
          <span class="content">{{item.name}}</span>
        </li>
      </ul>
      <div class="img-wrapper">
        <img src="../../assets/image/goods/icon_more_content.png">
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
    export default{
        props:{
           sku:'',
          cid:'',
          shopid:''
        },
        data(){
            return {
              coupons:[]
            }
        },
        components: {},
        methods: {
          getCoupon(){

          }
        },
        computed: {},
        created(){
          var that=this;
          that.$post(that.$interface_url.product.getCoupons,{sku:that.sku,shopid:that.shopid,categoryid:that.cid},function (data,success) {
            if(success)
            {
                that.coupons=data;
            }
            else{
                that.$vux.toast.show({text:data,type:'warn'})
            }
          });
        }
    }
</script>

<style lang="less">

</style>
