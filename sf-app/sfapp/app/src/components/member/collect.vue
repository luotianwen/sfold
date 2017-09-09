<template>

  <div class="mine_collect">
    <!--若有消息提示添加class名a_before-->
    <div class="coll_header">
      <!--点击编辑按钮coll_header影藏display:none,coll_content_yes的top值为0，redact标签显示display:block-->
      <x-header :left-options="{showBack:true}" @on-click-more="showMessageMenu"
                :right-options="{showMore: true}">商品收藏<span class="amend" @click="edit"
                                                            v-show="hasList">{{editText}}</span></x-header>
      <!--点击后显示菜单-->
      <message-menu ref="messagemenu"></message-menu>
    </div>

    <!--没有商品的页面样式-->
    <div class="coll_content_no" v-show="!hasList">
      <img src="../../assets/image/mine/collect_img.png"/>
      <p>暂无收藏的商品</p>
      <button class="leave_btn">再去逛逛</button>
    </div>
    <!--没有商品的页面样式-->
    <scroller lock-x scrollbar-y :use-pullup="true" v-model="scrollerStatus" :pullup-config="pullupConfig"
              @on-pullup-loading="up" ref="scroller" v-show="hasList">
      <div class="coll_content_yes" v-show="hasList">
        <!--<div class="redact">
                  编辑
                  <span class="r_finish">完成</span>
              </div>-->
        <div class="coll_msg">
          <div class="now" v-show="hasNow">
            <p class="recent_favorites" @click="selectedNowAll">
              <i class="select" v-show="isEdit" :class="{'active':nowSelectedAll}"></i>
              最近1个月收藏</p>
            <ul>
              <!--未编辑时右侧宽度为63.4%，点击编辑按钮后宽度为52.8%-->
              <li v-for="(collect,$index) in nowCollectList" :key="collect.fid">
                <router-link :to="{name:isEdit?'':'Goods',params:{sku:collect.sku}}">
                  <i class="select" :class="{'active':collect.selected}" @click="collect.selected=!collect.selected"
                     v-show="isEdit"></i>
                  <img :src="collect.wareImg" alt=""/>
                  <div class="coll_describe" :class="{'editDiv':isEdit,'border_none':$index==nowCollectList.length-1}">
                    <p>{{collect.wareName}}</p>
                    <span class="price"><i>￥</i>{{collect.followPrice.toFixed(2)}}</span>
                  </div>
                </router-link>
              </li>
            </ul>
          </div>
          <div class="before" v-show="hasBefore">
            <p class="before_collection" @click="selectedBeforeAll">
              <i class="select" v-show="isEdit" :class="{'active':beforeSelectedAll}"></i>
              1个月之前收藏</p>
            <ul>
              <!--未编辑时右侧宽度为63.4%，点击编辑按钮后宽度为52.8%-->
              <li v-for="(collect,$index) in beforeCollectList" :key="collect.fid">
                <router-link :to="{name:isEdit?'':'Goods',params:{sku:collect.sku}}">
                  <i class="select" :class="{'active':collect.selected}" @click="collect.selected=!collect.selected"
                     v-show="isEdit"></i>
                  <img :src="collect.wareImg" alt=""/>
                  <div class="coll_describe"
                       :class="{'editDiv':isEdit,'border_none':$index==beforeCollectList.length-1}">
                    <p>{{collect.wareName}}</p>
                    <span class="price"><i>￥</i>{{collect.followPrice.toFixed(2)}}</span>
                  </div>
                </router-link>
              </li>
            </ul>
          </div>
        </div>
        <divider v-show="!hasNextPage">已经到底部了</divider>
      </div>
    </scroller>
    <!--开始为不可点击效果 可点击效果为btn_active-->
    <div class="cancel_btn" v-show="isEdit">
      <!--选中点击删除弹出提示框内容为'删除n个宝贝'n为选中商品个数-->
      <x-button @click.native="deleteEvent">删除</x-button>
    </div>

  </div>

</template>

<script>
  import {Confirm, XHeader, Cell, Group, XButton, Scroller,Divider} from 'vux'
  import messageMenu from '../common/messagemenu.vue'
  export default {
    components: {
      Confirm,
      XHeader,
      Cell,
      Group,
      Scroller,
      XButton,
      messageMenu,
      Divider
    },
    data () {
      return {
        userInfo: {},
        isEdit: false,//编辑状态
        editText: '编辑',//编辑文本
        nowCollectList: [],//最近一个月array
        beforeCollectList: [],//一个月前array
        currentPage: 1,//页码
        hasList: true,//是否有数据
        hasNow: false,//最近一个月是否有数据
        hasBefore: false,//一个月前是否有数据
        nowSelectedAll: false,//最近一个月全选标识
        beforeSelectedAll: false,//一个月前全选标识
        nowCount: 0,
        beforeCount: 0,
        hasNextPage: false,
        scrollerStatus: {
          pullupStatus: 'default',

        },
        pullupConfig: {
          content: '上拉加载内容',
          pullUpHeight: 60,
          height: 40,
          autoRefresh: false,
          downContent: '松开刷新',
          upContent: '上拉加载内容',
          loadingContent: '正在加载',
          clsPrefix: 'xs-plugin-pullup-'
        }

      }
    },
    mounted () {
      this.userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      this.loading();
      this.$nextTick(() => {
        this.$refs.scroller.reset()
      })
    },

    activated () {
      console.log('activated')
      this.$refs.scroller.reset();

    },
    methods: {
      up(){
        var that = this;
        if (!this.hasNextPage) {
          that.$nextTick(() => {
            that.scrollerStatus.pullupStatus = 'default';
            that.$refs.scroller.reset();
          })
          return;
        }
        this.currentPage++;
        this.loading();
      },
      edit(){
        if (!this.hasList)return;
        if (!this.isEdit) {
          this.isEdit = true;
          this.editText = '完成'
        }
        else {
          this.isEdit = false;
          this.editText = '编辑'
        }
      },
      showMessageMenu(){
        this.$refs.messagemenu.show();//显示消息框
      },
      selectedNowAll(){
        if (!this.isEdit)return;//如果不是编辑状态，此处事件不执行
        var that = this;
        if (this.nowCollectList && this.nowCollectList.length > 0) {//全选和取消
          this.nowCollectList.forEach(function (item, index) {
            item.selected = !that.nowSelectedAll;
          })
          that.nowSelectedAll = !that.nowSelectedAll;
        }
      },
      selectedBeforeAll(){
        if (!this.isEdit)return;
        var that = this;
        if (this.beforeCollectList && this.beforeCollectList.length > 0) {//全选和取消
          this.beforeCollectList.forEach(function (item, index) {
            item.selected = !that.beforeSelectedAll;
          })
          that.beforeSelectedAll = !that.beforeSelectedAll;
        }
      },
      deleteEvent(){//删除事件
        var that = this;
        let skus='';

        if (this.nowCollectList) {
          this.nowCollectList.forEach(function (item, index) {
            if(item.selected){
              skus+=item.sku+',';
            }
          })
        }
        if (this.beforeCollectList && this.beforeCollectList.length > 0) {
          this.beforeCollectList.forEach(function (item, index) {
            if(item.selected){
              skus+=item.sku+',';
            }
          })
        }

        if (skus.length > 0) {
          skus = skus.substring(0, fids.length - 1);
            this.$vux.confirm.show({
              content: '确定删除所选?',
              onConfirm(){

                that.$post(that.$interface_url.user.deleteFollowWare, {
                  token: that.userInfo.token,
                  sku: skus
                }, function (data, success) {
                  if (success) {
                    skus.split(',').forEach(function (item,index) {
                      that.nowCollectList.forEach(function (collect,index) {
                        if(collect.sku==item){
                            that.nowCollectList.splice(index,1);
                        }
                      })
                      that.beforeCollectList.forEach(function (collect,index) {
                        if(collect.sku==item){
                          that.beforeCollectList.splice(index,1);
                        }
                      })
                    })
                    that.$vux.toast.show({text: '删除成功！'});
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
            this.$vux.toast.show({text: '未选中任何商品!', type: 'text', width: '10em'});
          }
       /* }
        else {
          this.$vux.toast.show({text: '您当前还未关注任何商品!', type: 'text', width: '10em'});
        }*/
      },
      loading(){
        let userInfo = this.$getStorage(this.$storage_key.USERINFO_STORAGE);
        if (userInfo && userInfo.token) {
          var that = this;
          that.$post(that.$interface_url.user.followWareList, {
            token: userInfo.token,
            currentPage: that.currentPage
          }, function (data, success) {
            if (success) {
              if (data.resultData && data.resultData.length > 0) {
                that.hasList = true;
                that.hasNextPage = data.hasNextPage;
                let today = new Date();
                let beforeDay = today.setDate(today.getDate() - 30);//一个月前
                data.resultData.forEach(function (item, index) {
                  if (new Date(item.followTime) > beforeDay) {//现有页面固定只分最近一个月和一个月之前，使用俩个array对数据进行分组
                    that.hasNow = true;
                    that.nowCollectList.unshift(item);
                    that.nowCount++;
                  }
                  else {
                    that.hasBefore = true;
                    that.beforeCollectList.unshift(item);
                    that.beforeCount++;
                  }
                });
                that.$nextTick(() => {
                    if(!data.hasNextPage){
                        that.$refs.scroller.disablePullup();
                    }
                  that.scrollerStatus.pullupStatus = 'default';
                  that.$refs.scroller.reset();
                })
              }
              else {
                if (!that.hasList) {
                  that.hasList = false;//
                }
              }
            }
            else {
              that.$vux.toast.show({text: data, type: 'warn', width: '16em'});
            }
          });
        }
      }
    },
    created(){

    }

  }
</script>
<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/collect.less";

  i {
    font-style: normal;
  }

  .editDiv {
    width: 52.8% !important;
  }

  .border_none {
    border-bottom: none !important;
  }

</style>
