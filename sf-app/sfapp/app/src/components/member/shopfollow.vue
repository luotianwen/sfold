<template>
  <div class="shopfollow">
    <div class="sfw_header">
      <x-header :left-options="{showBack:true}" @on-click-more="showMessageMenu"
                :right-options="{showMore: true}">店铺关注 <span class="amend" @click="edit">{{editText}}</span>
      </x-header>
      <message-menu ref="messagemenu"></message-menu>
    </div>
    <!--没有商品的页面样式-->
    <div class="sfw_content_no" v-if="!(shopList&&shopList.length>0)">
      <img src="../../assets/image/mine/collect_img.png"/>
      <p>暂无关注的店铺</p>
      <button class="leave_btn">再去逛逛</button>
    </div>
    <scroller lock-x scrollbar-y :use-pullup="true" v-model="scrollerStatus" :pullup-config="pullupConfig"
              @on-pullup-loading="upEvent" ref="scroller" v-show="shopList&&shopList.length>0" >
      <div class="sfw_content_yes" v-show="shopList&&shopList.length>0">
        <div class="check_all" @click="selectedAll">
          <i class="select" v-show="isEdit" :class="{'active':isSelectedAll}"></i>
          您已关注的店铺
        </div>
        <ul>
          <li v-for="(shop,$index) in shopList">
            <i class="select" v-show="isEdit" :class="{'active':shop.selected}"
               @click="shop.selected=!shop.selected"></i>
            <img :src="shop.shopLogo"/>
            <p>{{shop.shopName}}</p>
          </li>
        </ul>
        <divider v-show="!hasNextPage" >已经到底部了</divider>
      </div>
    </scroller>

    <div class="cancel_btn" v-show="isEdit">
      <!--选中点击删除弹出提示框内容为'删除n个宝贝'n为选中商品个数-->
      <x-button @click.native="deleteEvent">删除</x-button>
    </div>
  </div>
</template>

<script>
  import {Confirm, XHeader, Cell, Group, XButton, Scroller,Divider } from 'vux'
  import messageMenu from '../common/messagemenu.vue'
  export default {
    components: {
      Confirm,
      XHeader,
      Cell,
      Group,
      XButton,
      messageMenu,
      Scroller,
      Divider
    },
    data() {
      return {
        userInfo: {},
        currentPage: 1,
        shopList: [],
        isEdit: false,
        editText: '编辑',
        isSelectedAll: false,
        hasNextPage: false,
        pullupConfig: {
          content: '上拉加载内容',
          pullUpHeight: 60,
          height: 40,
          autoRefresh: false,
          downContent: '松开刷新',
          upContent: '上拉加载内容',
          loadingContent: '正在加载',
          clsPrefix: 'xs-plugin-pullup-'
        },
        scrollerStatus: {
          pullupStatus: 'default'
        },
      }
    },
    methods: {
      initData(){
        var that = this;
        that.$post(that.$interface_url.user.followVenderList, {
          token: that.userInfo.token,
          currentPage: that.currentPage
        }, function (data, success) {
          if (success) {
            data.resultData.forEach(function (item, index) {
              that.shopList.unshift(item);
            });
            that.hasNextPage = data.hasNextPage;
            if (data.hasNextPage) {
              that.$nextTick(() => {
                that.scrollerStatus.pullupStatus = 'default';
                that.$refs.scroller.reset();
              })
            }
            else {
              that.$nextTick(() => {
                //that.$refs.scroller.disablePullup();
                that.$refs.scroller.disablePullup();
                that.scrollerStatus.pullupStatus = 'default';

                that.$refs.scroller.reset();
              });
            }
          }
          else {
            that.$vux.toast.show({text: data, type: 'warn', width: '10em'});
          }
        });
      },
      edit(){
        this.isEdit = !this.isEdit;
        this.editText = this.isEdit ? '完成' : '编辑';
      },
      selectedAll(){
        if (!this.isEdit)return;
        this.isSelectedAll = !this.isSelectedAll;
        var that = this;
        this.shopList.forEach(function (item, index) {
          item.selected = that.isSelectedAll;
        })
      },
      deleteEvent(){
        var fids = '';
        this.shopList.forEach(function (item, index) {
          if (item.selected) {
            fids += item.fid + ',';
          }
        })
        if (fids && fids.length > 0) {
          var that = this;
          this.$vux.confirm.show({
            content: '确定删除所选?',
            onConfirm(){
              fids = fids.substring(0, fids.length - 1);
              that.$post(that.$interface_url.user.deleteFollowVender, {
                token: that.userInfo.token,
                fid: fids
              }, function (data, success) {
                if (success) {
                  fids.split(',').forEach(function (item, index) {
                    that.shopList.forEach(function (shop, index) {
                      if (shop.fid == item) {
                        that.shopList.splice(index, 1);
                      }
                    })
                  })
                  that.$vux.toast.show({text: '删除成功！'});
                  that.$nextTick(() => {
                    that.scrollerStatus.pullupStatus = 'default';
                    that.$refs.scroller.reset();
                  });
                }
                else {
                  that.$vux.toast.show({text: data, type: 'warn'});
                }
              });
            },
            cancelText: '取消',
            confirmText: '确认'
          });
        }
        else {
          this.$vux.toast.show({text: '请选择要删除的项!', type: 'text', width: '10em'});
        }
      },
      showMessageMenu(){
        this.$refs.messagemenu.show();
      },
      upEvent(){
        if (!this.hasNextPage)
          return;
        this.currentPage++;
        this.initData();

      }
    },
    created(){

    },
    mounted(){
      this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      this.initData();
    }
  }
</script>
<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/shopfollow.less";

  i {
    font-style: normal;
  }
</style>
