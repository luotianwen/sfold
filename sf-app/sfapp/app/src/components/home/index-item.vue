<template>
  <div class="a_show_item">
    <div class="a_item_img">
      <a :href="adManage.link">
        <img :src="adManage.imgPath" />
      </a>
    </div>

    <div class="a_item_msg">
      <scroller lock-y scrollbar-x>
      <ul>
        <li  v-for="ware in adManage.wareList">
          <router-link :to="{name:'Goods',params:{sku:ware.sku}}">
            <div class="as_img">
              <h1 class="bc-57cc7f"><!--<img :src="ware.top_pic">-->秒杀</h1>
              <img :src="ware.imgPath"/>
              <span></span>
            </div>
            <div class="shop_msg">{{ware.pname}}</div>
            <div class="shop_price">
              <span class="s_favorable"><i>￥</i>{{ware.price.toFixed(2)}}</span>
              <!--<span class="s_original" v-if="ware.marketprice>0">￥{{ware.marketprice.toFixed(2)}}</span>-->
            </div>
          </router-link>
        </li>
      </ul>
      </scroller>
 <!--     <div class="look_all" @click="showall(adManage.)">
        <p>查看全部</p>
        <span>See more</span>
      </div>-->
    </div>

  </div>
</template>

<script type="text/ecmascript-6">
  import {Scroller } from 'vux'
export default{
  data(){
    return{

    }
  },
  props:{
    adManage:{}
  },
  created(){

  },
  components:{
    Scroller
  }
}





</script>

<style>
</style>
