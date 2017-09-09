<template>
  <div class="product-info-wrapper">
    <!--产品参数-->
    <div class="product-param">
      <span class="txt">产品参数</span>
      <img src="../../assets/image/goods/icon_more_content.png">
    </div>
    <!--选择 颜色分类 尺码-->
    <div class="select-color-size">
      <span class="txt">选择</span>
      <span class="color">{{colorName}}</span>
      <span class="size">{{specName}}</span>
      <img src="../../assets/image/goods/icon_more_content.png" @click="change">
    </div>
    <goods-color :pid="pid" :sku="sku" :name="name" ref="goodColor" :state="state" v-on:child-update="updateColor"></goods-color>
  </div>
</template>

<script type="text/ecmascript-6">
  import GoodsColor from './goods_color.vue'
  export default{
    props: {
      pid: '',
      colorName: '',
      specName: '',
      sku:'',
      name:'',
      state:''
    },
    data(){
      return {}
    },
    components: {
      GoodsColor
    },
    methods: {
      change(){
        this.$refs.goodColor.show();
      },
      updateColor(data){
          this.$emit('child-update',data);
      },

    },
    computed: {},
    created(){

    }
  }
</script>

<style lang="less">

</style>
