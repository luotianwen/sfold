<template>
  <transition enter-active-class="animated fadeInRight" leave-active-class="animated fadeOutRight">
  <div class="nickname" v-show="isShow" ref='nickname'>
    <div class="nickname_header">
      <x-header :left-options="{showBack:false,backText:'eee'}">
        <span class="back_btn" @click="hide"><i></i></span>
        修改昵称
      </x-header>
    </div>
    <div class="nickname_content">
      <group>
        <x-input type="text" placeholder="" v-model='userInfo.name'></x-input>
      </group>
      <button class="preserve" @click="saveNickName" :class="{'preserve_active':userInfo.name.trim().length>0}">保存</button>
    </div>
  </div>
  </transition>
</template>
<script type="text/ecmascript-6">
  import {XHeader,XInput,Group,Cell } from 'vux'
  export default {
    props: {
      userInfo: {}
    },
    data(){
      return {
        isShow: false
      }
    },
    components: {
      XHeader, XInput, Group, Cell
    },
    methods: {
      show(){
        this.isShow = true;
      },
      hide(){
        this.isShow = false;
      },
      saveNickName(){
        if (this.userInfo.name.trim().length == 0) {
          this.$vux.toast.show({text: '请填写昵称', type: 'text', width: '18em'});
          return;
        }
        var that = this;
        that.$post(that.$interface_url.user.modifyInfo, {
          token: this.userInfo.token,
          name: this.userInfo.name
        }, function (data, success) {
          if (success) {
            that.$emit('child-update', that.userInfo);//调用父组件方法更新昵称
            that.$vux.toast.show({
              text: that.$tip_message.update_success,
              type: 'success',
              width: '18em'
            })
            that.isShow = false;
          }
          else {
            that.$vux.toast.show({
              text: data,
              type: 'warn',
              width: '18em'
            })
          }
        })
      }
    }
  }
</script>
<style rel="stylesheet/less" lang="less">
  @import "../../../assets/css/nickname.less";
  .move-transition {
    opacity: 0;
    transition: all 0.2s linear;
    -webkit-transform: translate(50px, 0);
    transform: translate3d(100%, 0, 0);
  }
  .move-enter, .move-leave
  {
    opacity: 0;
    -webkit-transform: translate(-50px, 0);
    transform: translate3d(-100%, 0, 0);
  }


</style>
