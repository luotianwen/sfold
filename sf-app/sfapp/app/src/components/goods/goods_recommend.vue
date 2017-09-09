<template>
  <div class="recommend-wrapper">
    <div class="recommend-tab">
      <span class="recommend" @click="type=1" :class="{'active': type==1}">为你推荐</span>
      <span class="top-list" @click="type=2" :class="{'active': type==2}">排行榜</span>
    </div>
    <flexbox v-show="type==1" class="list-wrapper" :gutter="0" wrap="wrap">
      <flexbox-item :span="1/3" v-for="item in shopHot">

        <div class="item" @click="go(item.sku)">

          <div class="img-wrapper">
            <img :src="item.colorPic">
          </div>

          <span class="title">{{item.pName}}</span>
          <span class="rmb">¥</span>
          <span class="price">{{item.price.toFixed(2)}}</span>
        </div>

      </flexbox-item>
    </flexbox>
    <flexbox v-show="type==2" class="list-wrapper" :gutter="0" wrap="wrap">
      <flexbox-item :span="1/3" v-for="(o, index) in shopHot" :class="{'line-space':index%3==0}">
        <router-link :to="{name:'Goods',params:{sku:o.sku}}">
        <div class="item">
          <div class="img-wrapper">
            <img :src="o.colorPic">
          </div>
          <span class="title">{{o.pName}}</span>
          <span class="rmb">¥</span>
          <span class="price">{{o.price.toFixed(2)}}</span>
        </div>
        </router-link>
      </flexbox-item>
    </flexbox>
  </div>
</template>

<script type="text/ecmascript-6">
  import {Flexbox, FlexboxItem} from 'vux'
  export default{
      props:{
          shopid:''
      },
    data(){
      return {
        type: 1,
        shopHot:[]
      }
    },
    components: {Flexbox, FlexboxItem},
    methods: {
        go(sku){


            this.$router.push({name:'Goods',params:{sku:sku}});

        }
    },
    computed: {},
    created(){

    },
    mounted(){
      var that=this;
      that.$post(that.$interface_url.shop.hotWare,{shopid:that.shopid},function (data,success) {
        if(success){
            that.shopHot=data;
        }
      });
    }
  }
</script>

<style lang="less">

</style>
