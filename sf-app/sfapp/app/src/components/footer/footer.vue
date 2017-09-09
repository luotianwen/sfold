<template>
  <div class="f_btn is-fixed">
    <router-link to="/" class="f_btn_item" active-class="selected" :class="{'selected':barSelected==1}" exact>
      <div class="f_item_icon_1">
      </div>
      <div class="f_item_lable">
        首页
      </div>
    </router-link>
    <router-link to="/category" class="f_btn_item" active-class="selected" :class="{'selected':barSelected==2}" exact>
      <div class="f_item_icon_2">
      </div>
      <div class="f_item_lable">
        分类
      </div>
    </router-link>
    <router-link to="/distribution" class="f_btn_item" active-class="selected" :class="{'selected':barSelected==5}" exact>
      <div class="f_item_icon_3">
      </div>
      <div class="f_item_lable">
        分销
      </div>
    </router-link>
    <router-link to="/cart" class="f_btn_item" active-class="selected" :class="{'selected':barSelected==3}" exact>
      <div class="f_item_icon_4">
      </div>
      <div class="f_item_lable">
        购物车
      </div>
      <sup v-show="cartCount>0">
        <badge :text="cartCount"></badge>
      </sup>
    </router-link>
    <router-link to="/member" class="f_btn_item" :class="{'selected':barSelected==4}" active-class="selected" exact>
      <div class="f_item_icon_5">
      </div>
      <div class="f_item_lable">
        我的
      </div>
    </router-link>
  </div>
</template>

<script type="text/ecmascript-6">
  import {mapGetters} from 'vuex'
  import {Badge} from 'vux'
  export  default{
    components:{Badge},
    data(){
      return {
        barSelected: 0
      }
    },
    computed: {
      ...mapGetters({
        cartCount: 'getCartCount',
        currentRouter: 'currentRouter'
      })
    },
    created(){
      this.barSelected = checkRouter(this.currentRouter.path);
    },
    methods: {},
    watch: {
      currentRouter: function (val, oldval) {
        this.barSelected = checkRouter(val.path)
      }
    }
  }



  function checkRouter(path) {
    if ((path.indexOf('login') > -1 || path.indexOf('regiter') > -1 || path.indexOf('member') > -1)) {
      return 4;
    }
    else if(path=='/'||path.indexOf('home')>-1){
      return 1;
    }
    else if(path.indexOf('cart')>-1){
      return 3;
    }
    else if(path.indexOf('category')>-1){
      return 2;
    }
    else {
      return 0;
    }
  }
</script>


