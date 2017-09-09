<template>
	<!--找回密码-->
  <div>

	<div class="retrieve">
		<div class="re_header">
			<x-header :left-options="{showBack:true,backText:''}">
        {{type==1?'找回密码':type==2?'绑定手机号':'修改密码'}}
        </x-header>
		</div>
		<div class="re_content">
			<!--找回密码-->
			<!--手机号码格式错误 ，点击下一步提示--'手机号码输入有误！'-->
			<!--手机号码格式正确 ，点击下一步提示--'手机验证'，头部标题为'安全检测'-->
			<!--未输入值时button禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->
			<div class="retrieve_password">
				<group>
			      	<x-input title="账号" v-model="phone"  placeholder="请输入手机号码" is-type="china-mobile" :show-clear="true" ref="phoneTxt"></x-input>
			   	</group>
			    <div class="re_btn">
					<x-button type="primary" plain :class="{'weui-btn_plain-primary_active':isClick}" @click.native="nextClick">下一步</x-button>
				</div>
			</div>
		</div>
	</div>
    <sms-code ref="smsCode" :phone="phone" sendType="2" ></sms-code>
  </div>
</template>
<script>
	import {XHeader,XButton,XInput,Group} from 'vux'

  import smsCode from './smscode.vue'
	export default {
    components: {
      XHeader,
      XButton,
      XInput,
      Group,
      smsCode
    },
    data () {
      return {
        isClick: false,
        phone:'',
        type:1,//1为找回密码，2为绑定手机号，3为登录状态下修改密码，默认为1
      }
    },
    methods: {
      nextClick(){
        if (!this.isClick)return;
        var that = this;
        that.$post(that.$interface_url.safe.checkPhoneExists, {phone: that.phone}, function (data, success) {
          if (success) {
            that.$refs.smsCode.show();
            that.$refs.smsCode.sendCode();
          }
          else {
            that.$vux.toast.show({text: data, type: 'warn', width: '18em'});
          }
        })
      }
    },
    watch:{
        'phone':{
          handler:function (val,oldval) {
              if(val.trim().length>0&&this.$refs.phoneTxt.valid){
                  this.isClick=true;
              }
              else{
                  this.isClick=false;
              }
            },
          deep:true
        }
    },
    created(){
        if(this.$route.params&&this.$route.params.type){
            this.type=this.$route.params.type;
        }
    }

  }
</script>

<style rel="stylesheet/less" lang="less">
	@import "../../assets/css/retrieve.less";
	i{
		font-style: normal;
	}
</style>
