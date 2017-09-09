import Vue from 'vue'
import Router from 'vue-router'
/*import {$storage_key} from '../common/public-variable'
 import {$getStorage,$setStorage} from '../common/js/storage'*/
import {isLogin} from '../common/js/checkIsLogin'
import Home from '../components/home/index'
import Product from '../components/product/product'
import Login from '../components/login/login'
import Regiter from '../components/login/regiter'
import Member from '../components/member/member'
import Cart from '../components/cart/cart'
import Set from '../components/member/set/set'
import store from '../store'
import * as types from '../store/mutation-types'
import Person from '../components/member/set/person.vue'
import Address from '../components/member/set/address.vue'
import EditAddress from '../components/member/set/editaddress.vue'
import SetPassWord from '../components/login/password'
import Retrieve from '../components/login/retrieve'
import Regarding from '../components/member/set/regarding'
import Reset from '../components/login/reset'
import Safety from '../components/member/set/safety.vue'
import Protocol from '../components/login/protocol.vue'
import FeedBack from '../components/member/set/feedback.vue'
import Code from '../components/member/set/code.vue'
import Collect from '../components/member/collect'
import ShopFollow from '../components/member/shopfollow'
//import Distribution from '../components/login/login_yzm.vue'
import RegisterYZM from '../components/login/register_yzm.vue'
import Goods from '../components/goods/goods.vue'
import OrderSubmit from '../components/order/order_submit.vue'
import Category from '../components/category/index.vue'
import Pay from '../components/order/pay.vue'
import PayMent from '../components/order/payment.vue'
  import OrderList from '../components/member/order/orderlist.vue'
import OrderDetail from '../components/member/order/orderdetail.vue'
/*import GoodsInfo from '../components/goods/goodsInfo.vue'
 import GoodsDetail from '../components/goods/goods_detail.vue'
 import GoodsComment from '../components/goods/goods_comment.vue'*/

import Distribution from '../components/distribution/distribution.vue'
import AboutDistribution from '../components/distribution/distribution_about.vue'
import OrderDistribution from '../components/distribution/distribution_order.vue'
import Distributor from '../components/distribution/distributor.vue'
import DistributorDetail from '../components/distribution/distributor_detail.vue'
import DistributorEdit from '../components/distribution/distributor_edit.vue'
import DistributorSearch from '../components/distribution/distributor_search.vue'
import DistributorAdd from '../components/distribution/distributor_add.vue'
import Cash from '../components/distribution/cash.vue'
import CashNotice from '../components/distribution/cash_notice.vue'
import CommissionDetail from '../components/distribution/commission_detail.vue'
import GetCash from '../components/distribution/get_cash.vue'
import Silver from '../components/distribution/silver.vue'
import SilverNotice from '../components/distribution/silver_notice.vue'


import Test from '../components/mine/test.vue'

Vue.use(Router)

//参数说明：requiresAuth 当前路由是否需要进行登录验证
//footerShow 当前路由是否需要显示底部
//requiresOut：当前路由是否进行登录验证（比如登录页面，如果用户已经登录，直接路由打开页面，跳转到用户中心）
//title：路由标题
//tranInName:进场动画效果
//tranOutName：离场动画效果
const animation = {
  tranInName:'slide-left',//'vux-pop-in',
  tranOutName:'slide-right',//'vux-pop-out',
  tran_in_name:'fadeIn',

}


const routers = new Router({

  mode: 'hash',
  linkActiveClass: 'active',
  routes: [
    {path: '/test', name: 'test', component: Test, title: '首页', meta: {footerShow: true}},
    {path: '/', name: 'home', component: Home, title: '首页', meta: {footerShow: true}},
    {path: '/member', name: 'member', component: Member, title:'个人中心', meta: {footerShow: true}},
    {path: '/cart', name: 'cart', component: Cart,title:'购物车', meta: {footerShow: true}},
    {path: '/login', name: 'login', component: Login,title:'登录', meta: {footerShow: false, requiresOut: true}},
   // {path: '/distribution', name: 'distribution', component: Distribution, title:'分销激活',meta: {footerShow: true}},
    {path: '/category', name: 'Category', component: Category, title:'分类',meta: {footerShow: true}},
    {path: '/register-yzm', name: 'registerYZM', component: RegisterYZM, meta: {footerShow: false}},
    //  商品详情
    {
      path: '/goods/:sku', name: 'Goods', component: Goods,
      meta: {footerShow: false,tranInName: animation.tranInName,tranOutName:animation.tranOutName},
      /*      children: [
       //  商品详情第一页，商品
       {path: '/goods/info', name: 'GoodsInfo', component: GoodsInfo},
       //  商品详情第二页，详情
       {path: '/goods/GoodsDetail', name: 'GoodsDetail', component: GoodsDetail},
       //  商品详情第三页， 评价
       {path: '/goods/GoodsComment', name: 'GoodsComment', component: GoodsComment}
       ]*/
    },
    {
      path: '/protocol',
      name: 'Protocol',
      component: Protocol,
      title:'注册协议',
      meta: {footerShow: false, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/regiter',
      name: 'regiter',
      component: Regiter,
      title:'注册',
      meta: {footerShow: false, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/setpassword',
      name: 'setpassword',
      component: SetPassWord,
      title:'设置密码',
      meta: {footerShow: false, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/member/set',
      name: 'set',
      component: Set,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '设置',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },

    {
      path: '/reset',
      name: 'reset',
      component: Reset,
      meta: {footerShow: true, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/retrieve',
      name: 'retrieve',
      component: Retrieve,
      meta: {footerShow: false, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/member/safety',
      name: 'safety',
      component: Safety,
      meta: {footerShow: false, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/member/regarding',
      name: 'regarding',
      component: Regarding,
      meta: {footerShow: false, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/member/person',
      name: 'person',
      component: Person,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '个人信息',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/member/address',
      name: 'Address',
      component: Address,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '管理收货地址',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/member/editaddress',
      name: 'EditAddress',
      component: EditAddress,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '管理收货地址',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/member/feedback',
      name: 'feedback',
      component: FeedBack,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '意见反馈',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/member/code',
      name: 'code',
      component: Code,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '我的二维码',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/member/collect',
      name: 'collect',
      component: Collect,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '我的收藏',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/member/shopfollow',
      name: 'shopfollow',
      component: ShopFollow,
      meta: {
        requiresAuth: true,
        footerShow: false,
        title: '店铺关注',
        tranInName: animation.tranInName,tranOutName:animation.tranOutName
      }
    },
    {
      path: '/product/:sku',
      name: 'product',
      component: Product,
      title: '商品详情',
      meta: {footerShow: true, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {path: '/order-submit', name: 'orderSubmit', component: OrderSubmit, meta: {footerShow: false,requiresOut:true}},
    {
      path: '/pay',
      name: 'pay',
      component: Pay,
      title: '收银台',
      meta: {footerShow: true, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/payment',
      name: 'payment',
      component: PayMent,
      title: '付款成功',
      meta: {footerShow: true, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/member/orderlist',
      name: 'orderlist',
      component: OrderList,
      title: '订单列表',
      meta: {footerShow: true, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {
      path: '/member/orderdetail',
      name: 'orderdetail',
      component: OrderDetail,
      title: '订单详情',
      meta: {footerShow: true, tranInName: animation.tranInName,tranOutName:animation.tranOutName}
    },
    {path: '/order-submit', name: 'orderSubmit', component: OrderSubmit, meta: {footerShow: false,requiresOut:true}},
    {path: '/order-submit', name: 'orderSubmit', component: OrderSubmit, meta: {footerShow: false,requiresOut:true}},
    {path: '/distribution', name: 'distribution', component: Distribution, meta: {footerShow: true}},
    {path: '/distribution/about', name: 'AboutDistribution', component: AboutDistribution, meta: {footerShow: false}},
    {path: '/distribution/order', name: 'OrderDistribution', component: OrderDistribution, meta: {footerShow: false}},
    {path: '/distribution/distributor', name: 'Distributor', component: Distributor, meta: {footerShow: false}},
    {path: '/distribution/distributor/detail', name: 'DistributorDetail', component: DistributorDetail, meta: {footerShow: false}},
    {path: '/distribution/distributor/edit', name: 'DistributorEdit', component: DistributorEdit, meta: {footerShow: false}},
    {path: '/distribution/distributor/add', name: 'DistributorAdd', component: DistributorAdd, meta: {footerShow: false}},
    {path: '/distribution/distributor/search', name: 'DistributorSearch', component: DistributorSearch, meta: {footerShow: false}},
    {path: '/distribution/cash', name: 'Cash', component: Cash, meta: {footerShow: false}},
    {path: '/distribution/cash/cashNotice', name: 'CashNotice', component: CashNotice, meta: {footerShow: false}},
    {path: '/distribution/cash/commissionDetail', name: 'CommissionDetail', component: CommissionDetail, meta: {footerShow: false}},
    {path: '/distribution/cash/get', name: 'GetCash', component: GetCash, meta: {footerShow: false}},
    {path: '/distribution/silver', name: 'Silver', component: Silver, meta: {footerShow: false}},
    {path: '/distribution/silverNotice', name: 'SilverNotice', component: SilverNotice, meta: {footerShow: false}}
  ]
})


routers.beforeEach((to, from, next) => {//路由拦截
  /* var toPath=$getStorage($storage_key.ROUTER_STORAGE);

   if(toPath&&toPath.length>0){
   if(toPath[0]==to.path){
   //后退
   toPath.shift();
   $setStorage($storage_key.ROUTER_STORAGE, toPath)
   store.commit(types.UPDATEDIRECTION, {value: 'reverse'})
   }
   else{

   toPath.unshift(from.path);
   $setStorage($storage_key.ROUTER_STORAGE, toPath)
   store.commit(types.UPDATEDIRECTION, {value: 'forward'})
   }
   }else {
   var routerArray=[];
   routerArray.unshift(from.path);
   $setStorage($storage_key.ROUTER_STORAGE, routerArray)
   //前进
   store.commit(types.UPDATEDIRECTION, {value: 'forward'})
   }
   console.log(toPath)*/


  //store.commit(types.UPDATELOADINGSTATUS, {value: true})//设置loading状态
  if (to.meta && to.meta.requiresAuth === true && isLogin() == false) {
    next({
      name: 'login',
      query: {
        returnurl: to.fullPath
      }
    });
    //store.commit(types.UPDATELOADINGSTATUS, {value: false})//设置loading状态
  }
  /*else if (to.meta && to.meta.requiresOut === true && isLogin() == true) {
   next({
   name: 'member'
   });
   //store.commit(types.UPDATELOADINGSTATUS, {value: false})//设置loading状态
   }*/
  else {
    next();
  }
});
routers.afterEach(function (to) {
  store.commit(types.CURRENT_ROUTER, {router: to});
  //store.commit(types.UPDATELOADINGSTATUS, {value: false})//设置loading状态
});

export default routers;





