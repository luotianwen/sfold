<template>
  <transition enter-active-class="animated fadeInRight" leave-active-class="animated fadeOutRight" >
  <div class="yy" v-show="isShow" ref='smsCode'>
    <div class="y_header">
      <x-header :left-options="{showBack:true,backText:''}">验证手机</x-header>
    </div>
    <div class="y_content">
      <div class="reminder">
        <ul>
          <li class="y_item_first">我们已经发送了校验码到您的手机：</li>
          <li class="y_item_last">{{ phone.substr(0, 3) + '****' + phone.substr(7)}}</li>
        </ul>
      </div>
      <group>
        <x-input title="验证码"  placeholder="输入短信验证码" v-model="code"></x-input>
        <div class="send_btn">
          <a :class="{'before':sendBtnText=='重新发送'}" @click="sendCode">
            <!--未发送前   ‘发送’ 字体颜色class名'.before' 发送之后 ‘60秒后可重发’ 字体颜色class名'.after' -->
            {{sendBtnText}}
          </a>
        </div>
      </group>
      <div class="y_btn">
        <!--未输入值时禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->

        <x-button @click.native="nextClick" type="primary" plain :class="{'weui-btn_plain-primary_active':code.length==6}">下一步</x-button>
      </div>
    </div>
  </div>
  </transition>
</template>
<script>
  import {XHeader, XButton,XInput,Group,Cell} from 'vux'
  export default {
    props: {
      phone: '',
      sendType:0//1为注册，2为忘记密码
    },
    components: {
      XHeader, XButton, XInput, Group, Cell
    },
    data () {
      return {
        isShow: false,
        sendBtnText: '重新发送',
        anginSendSec: 60,
        setIntervalID: 0,
        code: ''
      }
    },
    methods: {
      show(){
        this.isShow = true;
      },
      hide(){
        this.isShow = false;
      },
      sendCode(){
        var that = this;
        if (that.sendBtnText != '重新发送') {
          return;
        }
        that.setIntervalID = setInterval(function () {//获取setInterval的index，停止该方法时使用
          if (that.anginSendSec > 0) {
            that.anginSendSec--;
            that.$setStorage('smscode', that.anginSendSec)
            that.sendBtnText = that.anginSendSec + '秒后可重发'
          }
          else {
            clearInterval(that.setIntervalID);
            that.$removeStorage('smscode');
            that.sendBtnText = '重新发送';
            that.anginSendSec = 60;
          }
        }, 1000);
        that.$post(that.sendType == 1?that.$interface_url.regiter.sendRegiterCode:
            that.sendType == 2?that.$interface_url.safe.sendFindPwdCode:'',
          {phone: this.phone}, function (data, success) {
          if (!success) {
            that.$vux.toast.show({text: that.$tip_message.send_smscode_err, type: 'text', width: '18em'});
            clearInterval(that.setIntervalID);
            that.$removeStorage('smscode');
            that.sendBtnText = '重新发送';
            that.anginSendSec = 60;
          }
          else {
            that.$vux.toast.show({text: that.$tip_message.send_smscode_success, type: 'text', width: '18em'});
          }
        })
      },
      nextClick(){
        var that = this;
        if (that.code.length != 6)return;

        that.$post(that.sendType == 1 ? that.$interface_url.regiter.checkMobileCode :
            that.sendType == 2 ? that.$interface_url.safe.checkMobileCode : '',
          {authcode: this.code}, function (data, success) {
            if (success) {
                if(that.sendType==1) {
                  that.$router.push({path: '/setpassword'});
                }
                else{
                  that.$router.push({path: '/reset'});
                }
            }
            else {
              that.$vux.toast.show({text: data, type: 'text', width: '18em'});
            }
          })
      }
    },
    created(){
      var sec = this.$getStorage('smscode')
      if (sec) {
        this.anginSendSec = sec;
      }
    }
  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/yy.less";
</style>
