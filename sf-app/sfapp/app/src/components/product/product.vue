<template>
  <div>
<div>
  <div><img width="100px" height="100px" src="https://img11.360buyimg.com/n1/jfs/t3274/148/6282166308/159133/cddf4850/58a3e9abN3c85dfc8.jpg"></div>
  <div>骆驼（CAMEL）户外全自动帐篷 双人 3-4人 野外露营防雨双层帐篷 蓝色 均码</div>
  <div>￥199</div>
  <input type="button" value="加入购物车" style="width: 200px;height: 30px;" @click="addToCart(1)">
</div>
  <div>
    <div><img width="100px" height="100px" src="https://img10.360buyimg.com/n1/jfs/t3280/134/6270906247/83566/1c75958a/58a6796fN3ba99c23.jpg"></div>
    <div>自由之舟骆驼帐篷户外2人野营全自动帐篷户外3-4人 野外双人钓鱼露营帐篷套装 军绿双人单个帐篷</div>
    <div>￥228</div>
    <input type="button" value="加入购物车" style="width: 200px;height: 30px;" @click="addToCart(2)">
  </div>
  <div>
    <div><img width="100px" height="100px" src="https://img10.360buyimg.com/n1/jfs/t4618/364/545769080/216740/656737f1/58d1c6e9N56647a57.jpg"></div>
    <div>自游人TrackMan 防潮垫 气垫床 帐篷充气垫充气床双人防潮垫帐篷垫子 蓝灰蓝 192*132*3cm</div>
    <div>￥139</div>
    <input type="button" value="加入购物车" style="width: 200px;height: 30px;" @click="addToCart(3)">
  </div>
  <div>
    <div><img width="100px" height="100px" src="https://img10.360buyimg.com/n1/jfs/t2764/134/2644878746/42864/9d892f0b/576a5df1Nf6269d70.jpg"></div>
    <div>喜马拉雅全自动速开帐篷户外双人 3-4人旅游家庭休闲 沙滩防雨 野外露营装备帐篷套装 橘色</div>
    <div>￥371.20</div>
    <input type="button" value="加入购物车" style="width: 200px;height: 30px;" @click="addToCart(4)">
  </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import vProductbanner from '../../components/product/product-banner.vue'

  const ERR_OK=0;
export  default{
  data(){
    return {
      sku: '',
      product: {}
    }
  },
  created(){

  },
  components: {
    vProductbanner
  },
  methods: {
    addToCart(val){
      if (val == 1) {
        this.product = {
          sellerId: 10001,
          sellerName: '骆驼(CAMEL)',

          sku: 30001,
          productName: '骆驼（CAMEL）户外全自动帐篷 双人 3-4人 野外露营防雨双层帐篷 蓝色 均码',
          productImage: 'https://img11.360buyimg.com/n1/jfs/t3274/148/6282166308/159133/cddf4850/58a3e9abN3c85dfc8.jpg',
          price: 199.00,
          number: 1
        };
      }
      else if (val == 2) {
        this.product = {
          sellerId: 10002,
          sellerName: '自由之舟',

          sku: 30002,
          productName: '自由之舟骆驼帐篷户外2人野营全自动帐篷户外3-4人 野外双人钓鱼露营帐篷套装 军绿双人单个帐篷',
          productImage: 'https://img10.360buyimg.com/n1/jfs/t3280/134/6270906247/83566/1c75958a/58a6796fN3ba99c23.jpg',
          price: 228.00,
          number: 1
        };
      }
      else if (val == 3) {
        this.product = {
          sellerId: 10003,
          sellerName: '自游人TrackMan',

          sku: 30003,
          productName: '自游人TrackMan 防潮垫 气垫床 帐篷充气垫充气床双人防潮垫帐篷垫子 蓝灰蓝 192*132*3cm',
          productImage: 'https://img10.360buyimg.com/n1/jfs/t4618/364/545769080/216740/656737f1/58d1c6e9N56647a57.jpg',
          price: 139.00,
          number: 1
        };
      }
      else if (val == 4) {
        this.product = {
          sellerId: 10004,
          sellerName: '喜马拉雅',

          sku: 30005,
          productName: '喜马拉雅fjdahj',
          productImage: 'https://img10.360buyimg.com/n1/jfs/t2764/134/2644878746/42864/9d892f0b/576a5df1Nf6269d70.jpg',
          price: 271.20,
          number: 1
        };
      }
      this.$store.dispatch('addToCart', this.product);
      this.$vux.toast.show({text:'添加购物车成功！',width:'18em'});
    }
  }
}
</script>

<style>

</style>
