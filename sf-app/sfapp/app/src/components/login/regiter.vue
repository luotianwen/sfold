<template>
  <div>
  <div class="regiter">
    <div class="r_header">
      <x-header :left-options="{showBack:true,backText:''}">注册</x-header>
    </div>
    <div class="r_content">
      <!--手机号-->
      <!--未输入值时button禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->
      <div class="cell_phone">
        <group>
          <x-input title="手机号"  placeholder="请输入手机号" :show-clear="true"
                   is-type="china-mobile" ref="phoneTxt" v-model.trim="phone" keyboard='number'></x-input>
        </group>
        <div class="r_btn">
          <x-button type="primary" plain :class="{'weui-btn_plain-primary-active':isClick}" @click.native="sendCode">下一步</x-button>
          <div class="a_msg_3 ">
            <!--切换样式选中"selected_class",未选中'default_class'-->
            <div :class="{'default_class':!isAgree,'selected_class':isAgree}" @click="isAgree=!isAgree">
              <i class="opt_img"></i>
              <span class="opt_msg">
                已阅读并同意<i><router-link to="/protocol">《世峰户外商城用户注册协议》</router-link></i>
							</span>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
  <sms-code ref="smsCode" :phone="phone" sendType="1"></sms-code>
  </div>
</template>
<script>
  import {XHeader, XButton,XInput,Toast,Group,Cell,Checker, CheckerItem} from 'vux'
  import smsCode from './smscode.vue'
  export default {
    components: {
      Checker, CheckerItem, XHeader, XButton, XInput, Group, Cell,smsCode
    },
    data () {
      return {
        phone: '',
        isClick: false,
        isAgree:true
      }
    },
    methods: {
      showPosition (position) {
        this.position = position;
        this.showPositionValue = true;
      },
      sendCode(){
          var that=this;
        if(!this.isClick)return;
        if(this.phone.trim().length<=0){
          this.$vux.toast.show({text:'请输入手机号',type:'text',width:'18em'});
        }
        else if(!this.isAgree){
          this.$vux.toast.show({text:'请先阅读注册协议',type:'text',width:'18em'});
        }
        else {
           this.$post(this.$interface_url.regiter.checkPhoneExists,{phone:this.phone},function (data,success) {
             if(success){
               that.$refs.smsCode.show();
               that.$refs.smsCode.sendCode();
             }
             else{
               that.$vux.toast.show({text:data,type:'text',width:'18em'});
             }
           })
        }
      }
    },
    watch: {
      'phone': {
        handler: function (val, oldVal) {
          if (val.trim().length > 0 && this.$refs.phoneTxt.valid&&this.isAgree) {
            this.isClick = true;
          }
          else {
            this.isClick = false;
          }
        },
        deep: true
      },
      'isAgree':{
        handler: function (val, oldVal) {
          if (val&&this.phone.trim().length>0 && this.$refs.phoneTxt.valid) {
            this.isClick = true;
          }
          else {
            this.isClick = false;
          }
        },
        deep: true
      }
    }
  }
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/regiter.less";
  i{
    font-style: normal;
  }
</style>
