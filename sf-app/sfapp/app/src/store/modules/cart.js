/**
 * Created by shen on 2017/3/6.
 * 购物车vuex
 *购物车数据结构
 * [{
 * shopid
 * shopname
 * wareList:[{
 * cartid
 * sku
 * number
 * price
 * cartprice
 * pname
 * colorname
 * specname
 * colorpic
 * },]
 * },]
 */
import * as types from '../mutation-types'
import {$system_key, $interface_url, $storage_key} from '../../common/public-variable'
import {$post} from '../../common/js/public-resource'
import {$getStorage, $setStorage, $removeStorage} from '../../common/js/storage'
import {isLogin} from '../../common/js/checkIsLogin'


import Vue from 'vue'

const carts = $getStorage($system_key.CARTINFO_STORAGE)

const state = {
  cartInfo: carts ? carts : []
}
const mutations = {
  [types.ADD_TO_CART](state, {good}){
    if (state.cartInfo && state.cartInfo.length > 0) {
      var shopItem = state.cartInfo.find(c => c.shopid == good.shopid);//先匹配当前商品所属店铺是不是已经存在购物车
      if (shopItem) {
        let goodItem = shopItem.wareList.find(s => s.sku == good.sku);//查询店铺下的商品集合是否存在当前商品
        if (goodItem) {

          goodItem.number += good.number;//存在直接加数量
        }
        else {
          var good = {//不存在添加商品集合
            sku: good.sku,
            number: good.number,
            price: good.price,
            cartPrice: good.price,
            pName: good.pName,
            colorName: good.colorName,
            specName: good.specName,
            colorPic: good.colorPic,
            selected:false
          };
          shopItem.wareList.push(good)
        }
      }
      else {
        var shopItem = {//添加店铺集合
          shopid: good.shopid,
          shopName: good.shopName,
          selected:false,
          wareList: [
            {
              sku: good.sku,
              number: good.number,
              price: good.price,
              cartPrice: good.price,
              pName: good.pName,
              colorName: good.colorName,
              specName: good.specName,
              colorPic: good.colorPic,
              selected:false
            }
          ]
        };
        state.cartInfo.push(shopItem);
      }
    }
    else {
      var shopItem = {//空购物车情况
        shopid: good.shopid,
        shopName: good.shopName,
        selected:false,
        wareList: [
          {
            sku: good.sku,
            number: good.number,
            price: good.price,
            cartPrice: good.price,
            pName: good.pName,
            colorName: good.colorName,
            specName: good.specName,
            colorPic: good.colorPic,
            selected:false
          }
        ]
      };
      state.cartInfo.push(shopItem);
    }
    if (isLogin()) {//判断用户是否登录，如果登录数据同步到服务器
      $post($interface_url.cart.addCartWare, {
        token: $getStorage($storage_key.USERINFO_STORAGE).token,
        sku: good.sku
      }, function (data,success) {
        if(success) {
          Vue.$vux.toast.show({text: '购物车添加成功', width: '16em'});
        }
        else{
          Vue.$vux.toast.show({text: data, width: '16em'});
        }
      });
    }
    else{
      Vue.$vux.toast.show({text: '购物车添加成功', width: '16em'});
    }
    $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo);//数据放入本地缓存
  },

  [types.ADD_NUMBER_TO_CART](state, {good}){
    if (state.cartInfo && state.cartInfo.length > 0) {
      var shopItem=state.cartInfo.find(c=>c.shopid==good.shopid);
      if(shopItem){
        var goodItem=shopItem.wareList.find(g=>g.sku==good.sku);
        if(goodItem) {
          if (good.type == 'add') {

            if(goodItem.number==1&&good.number==-1){return;}
            goodItem.number+=good.number;
          }
          else if (good.type == 'edit') {
            goodItem.number=good.number;
          }
          if(isLogin()){
            $post($interface_url.cart.editCartWare, {
              token: $getStorage($storage_key.USERINFO_STORAGE).token,
              sku: good.sku,
              wareNumber:goodItem.number
            }, function (data,success) {
              if(!success){
                Vue.$vux.toast.show({text:data, width: '16em',type:'warn'});
              }
            });
          }
          $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)
        }
      }
    }
  },

  [types.DELETE_TO_CART](state){
    if (state.cartInfo && state.cartInfo.length > 0) {
      var skus='';
      state.cartInfo.forEach(function (shopItem,index) {

        shopItem.wareList.forEach(function (goodItem,_index) {
          if(goodItem.selected){
            skus+=goodItem.sku+',';
          }
        });
        skus=skus.substr(0,skus.length);
        var _skus=skus.split(',');
        for(var i=0;i<_skus.length;i++){
          shopItem.wareList.forEach(function (item,_index) {
            if(item.sku==_skus[i]){
              shopItem.wareList.splice(_index,1);
            }
          })
        }

      })
      var shopCount=state.cartInfo.length;
      for(var ii=0;ii<shopCount;ii++){
        state.cartInfo.forEach(function (shopItem,index) {
          if(!(shopItem.wareList&&shopItem.wareList.length>0)){
            state.cartInfo.splice(index,1)
          }
        });
      }
      if (isLogin()) {
        $post($interface_url.cart.deleteCartWare, {
          token: $getStorage($storage_key.USERINFO_STORAGE).token,
          sku: skus.substr(0,skus.length-1)
        }, function (data,success) {
          if(success) {
            Vue.$vux.toast.show({text: '购物车删除成功', width: '16em'});
          }
          else{
            Vue.$vux.toast.show({text: data, width: '16em'});
          }
        });
      }
      else{
        Vue.$vux.toast.show({text: '购物车删除成功', width: '16em'});
      }
      $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)
    }
  },

  [types.SELECT_GOOD](state, {good}){
    let isSelectedShop=true;
    if (state.cartInfo && state.cartInfo.length > 0) {
      let shopItem = state.cartInfo.find(c => c.shopid == good.shopid);
      if (shopItem) {
        let goodItem = shopItem.wareList.find(g => g.sku == good.sku);
        if (goodItem) {
          goodItem.selected = !goodItem.selected;
          $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)

        }
        shopItem.wareList.forEach(function (good,index) {
          if(!good.selected){
            isSelectedShop=false;
          }
        });
        shopItem.selected=isSelectedShop;
      }
    }
  },

  [types.SELECT_SHOP](state,{shopid}){
    if (state.cartInfo && state.cartInfo.length > 0) {
      let shopItem = state.cartInfo.find(c => c.shopid == shopid);
      if (shopItem) {
        shopItem.selected = !shopItem.selected ;
        shopItem.wareList.forEach(function (item, index) {
          item.selected = shopItem.selected;
        })
        $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)
      }
    }
  },

  [types.SELECT_ALL_GOODS](state,{val}){
    if (state.cartInfo && state.cartInfo.length > 0) {
      state.cartInfo.forEach(function (shopItem,index) {
        shopItem.selected=val;
        shopItem.wareList.forEach(function (goodItem,_index) {
          goodItem.selected=val;
        })
      })
      $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)
    }
  },

  [types.SYNC_CARTINFO](state,{cartInfo}){
    state.cartInfo=cartInfo;
    $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)
  },
  [types.DELETE_GOOD](state,{good}){
    if (state.cartInfo && state.cartInfo.length > 0) {
      var shopItem=state.cartInfo.find(c=>c.shopid==good.shopid);
      if(shopItem){
        shopItem.wareList.forEach(function (goodItem,index) {
          if(goodItem.sku==good.sku){
            shopItem.wareList.splice(index,1);

            if (isLogin()) {
              $post($interface_url.cart.deleteCartWare, {
                token: $getStorage($storage_key.USERINFO_STORAGE).token,
                sku: good.sku
              }, function (data,success) {
                if(success) {
                  Vue.$vux.toast.show({text: '删除成功', width: '16em'});
                }
                else{
                  Vue.$vux.toast.show({text: data, width: '16em'});
                }
              });
            }
            else{
              Vue.$vux.toast.show({text: '删除成功', width: '16em'});
            }
            if(shopItem.wareList.length==0){
              state.cartInfo.forEach(function (shop,_index) {
                if(shop.shopid==shopItem.shopid) {
                  state.cartInfo.splice(_index, 1)
                }
              })
            }
            $setStorage($system_key.CARTINFO_STORAGE,state.cartInfo)
          }
        })

      }
    }
  }

}
export default {
  state,
  mutations
}

