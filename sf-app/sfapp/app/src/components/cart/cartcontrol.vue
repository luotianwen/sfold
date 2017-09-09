<template>
    <div id="cart-control">
        <span class="cart-decrease icon-cart-decrease" :class="{'gray':good.number==1}" @click="addNumberToCart(good.sku,shopId,-1,'add')"></span>
        <span class="cart-count">{{good.number}}</span>
        <span class="cart-add icon-cart-plus" @click="addNumberToCart(good.sku,shopId,1,'add')"></span>
    </div>
</template>

<script type="text/ecmascript-6">

    export default {
      props: {
        good: {},
        shopId: ''
      },
      methods: {
        addNumberToCart(sku, shopid, number, type){
          console.log(sku)
          console.log(shopid)
          this.$store.dispatch('addNumberToCart', {shopid: shopid, sku: sku, number: number, type: type})
        },
        updateNumberToCart(sku, shopid, number){

        },
      },
      created(){

      }
    }
</script>

<style lang="less" rel="stylesheet/less">
    @import "../../assets/css/shopping-cart.less";

    @font-size-base: 75;
    #cart-control {
        display: inline-block;
        display: -webkit-box; /* 老版本语法: Safari, iOS, Android browser, older WebKit browsers. */
        display: -moz-box; /* 老版本语法: Firefox (buggy) */
        display: -ms-flexbox; /* 混合版本语法: IE 10 */
        display: -webkit-flex; /* 新版本语法: Chrome 21+ */
        display: flex; /* 新版本语法: Opera 12.1, Firefox 22+ */
        width: 156rem/@font-size-base;
        height: 38rem/@font-size-base;
        border: 1px solid #e0e0e0;
        border-radius: 2px;
        .cart-decrease {
            display: inline-block;
            flex: 0 0 44rem/@font-size-base;
            width: 44rem/@font-size-base;
            height: 38rem/@font-size-base;
            line-height: 40rem/@font-size-base;
            border-right: 1px solid #e0e0e0;
            text-align: center;
            font-size: 22rem/@font-size-base;
            color: #161616;
            font-weight: bold;
            &.gray {
                color: #c9c9c9;
            }
        }
        .cart-count {
            flex: 1;
            display: inline-block;
            height: 38rem/@font-size-base;
            line-height: 40rem/@font-size-base;
            width: 62rem/@font-size-base;
            text-align: center;
            font-size: 24rem/@font-size-base;
            color: #161616;
        }
        .cart-add {
            flex: 0 0 44rem/@font-size-base;
            display: inline-block;
            height: 38rem/@font-size-base;
            line-height: 40rem/@font-size-base;
            width: 44rem/@font-size-base;
            text-align: center;
            border-left: 1px solid #e0e0e0;
            font-size: 22rem/@font-size-base;
            color: #161616;
            font-weight: bold;
        }
    }
</style>
