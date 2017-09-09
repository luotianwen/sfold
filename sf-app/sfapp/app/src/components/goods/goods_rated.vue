<template>
  <div class="comment-wrapper">
    <div class="title-wrapper">
      <span class="comment-txt">商品评价</span>
      <span class="comment-count">({{10999}})</span>
      <span class="praise-txt">好评度</span>
      <span class="praise-rate">96%</span>
      <img src="../../assets/image/goods/arrow_right.png">
    </div>
    <div class="tag-list-wrapper">
      <!--<span class="tag-item" v-for="tag in tagList" :class="{'bg-gray':!tag.bg}">{{tag.title}}({{tag.num}})</span>-->
    </div>
    <div class="star-user-wrapper">
      <star class="star" :score="4"></star>
      <span class="user">{{commendList.resultData[0].receiveName}}</span>
    </div>
    <p class="comment-content">
      {{commendList.resultData[0].content}}
    </p>
    <span class="color-class">颜色分类:{{commendList.resultData[0].colorName}};尺码;{{commendList.resultData[0].specName}}</span>
    <div class="btn-wrapper">
      <x-button plain class="btn-comment" @click.native="allComment">查看全部评价
      </x-button>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import {XButton} from 'vux'
  import Star from '../star/star.vue'
    export default{
      props:{
          pid:''
      },
      data(){
        return {
          commendList:[]
        }
      },
      components: {XButton, Star},
      methods: {
        allComment(){
          this.$emit('child-update-index',3)
        }
      },
      computed: {},
      created(){
          var that=this;
        this.$post(this.$interface_url.product.wareComments,{productId:this.pid,currentPage:1,pageSize:1},function (data,success) {
          if(success){
              that.commendList=data;
          }
        });
      }
    }
</script>

<style lang="less">

</style>
