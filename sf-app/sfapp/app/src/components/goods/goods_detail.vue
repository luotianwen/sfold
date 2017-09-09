<template>
  <transition :name="changeIndex=='2'?'slide-left':changeIndex==1?'slide-right':'slide-left'">
    <div class="goods-detail" v-show="changeIndex==2" ref="goodsdetail">
      <div class="goods-detail" id="detailHtmlDiv" v-html="detail">

      </div>
    </div>
  </transition>
</template>

<script type="text/ecmascript-6">
  export default{
    props: {
      detail: '',
      changeIndex:''
    },
    data(){
      return {
        isShow: false,
        tranName:'slide-left',
        imgList: ['https://static.vux.li/demo/1.jpg',
          'https://static.vux.li/demo/2.jpg',
          'https://static.vux.li/demo/3.jpg']
      }
    },
    methods: {

    },
    created(){

    },
    mounted(){
      let width = window.screen.width;//如果手机端详情没有，显示pc端详情，适配图片尺寸，（好像没作用，时好时坏）
      let obj = document.getElementById('detailHtmlDiv');
      let imgs = obj.getElementsByTagName('img');
      let tables=obj.getElementsByTagName('table');
      if(tables){
          for(var i=0;i<tables.length;i++){
              tables.width=width;
          }
      }
      for (var i = 0; i < imgs.length; i++) {
        imgs[i].width = width;
      }
    }

  };
</script>

<style lang="less" rel="stylesheet/less">
  @font-size-base : 75;

  .goods-detail {
    position: relative;
    width:100% !important;
    top: 44rem/@font-size-base;

  }
  .goods-detail {
    position: relative;
    width: 100%;
    overflow: hidden;
    margin-bottom: 141rem/@font-size-base;
  }


</style>
