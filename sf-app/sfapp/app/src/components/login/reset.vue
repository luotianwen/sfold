<template>
	<!--重置密码-->
	<!--注：重置成功后进入我的页面，提示弹窗重置密码成功-->
	<div class="reset">
		<div class="rt_header">
			<x-header :left-options="{showBack:false}"><span class="close_btn" @click="hide"><i></i></span>设置密码</x-header>
		</div>
		<div class="rt_content">
			<div class="rt_password">
		    	<group>
			      	<x-input title="输入新密码"  placeholder="请输入新的密码" :type="pwdType" :show-clear="true" v-model="newPassWord"></x-input>
			      	<span :class="{'dis_show':pwdType=='password','dis_hide':pwdType!='password'}" @click="changePwdType(1)"><i></i></span>
			   	</group>
			   	<group>
			      	<x-input title="确认新密码"  placeholder="请再次输入密码" :type="c_pwdType" :show-clear="true" v-model="c_newPassWord" ref="c_pwdTxt"></x-input>
			      	<span :class="{'dis_show':pwdType=='password','dis_hide':pwdType!='password'}" @click="changePwdType(2)"><i></i></span>
			   	</group>
		    </div>
		   <p class="rt_hint">密码由6-20位英文字母、数字或符号组成</p>
		    <div class="rt_btn">
    			<!--未输入值时禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->
				<x-button type="primary" :class="{'weui-btn_plain-primary_active':isClick}" plain @click.native="savePassWord">下一步</x-button>
			</div>
		</div>
	</div>
</template>
<script>
	import {XHeader, XButton,XInput,Toast,Group} from 'vux'
	export default {
    components: {
      XHeader,
      XButton,
      XInput,
      Group,
    },
    data () {
      return {
        pwdType: 'password',
        c_pwdType: 'password',
        newPassWord: '',
        c_newPassWord:'',
        isClick: false
      }
    },
    methods: {
      changePwdType(type){//点击眼睛图标切换文本框 类型
        if (type == 1) {
          if (this.pwdType == 'password') {
            this.pwdType = 'text';
          }
          else {
            this.pwdType = 'password';
          }
        }
        else {
          if (this.c_pwdType == 'password') {
            this.c_pwdType = 'text';
          }
          else {
            this.c_pwdType = 'password';
          }
        }
      },
      savePassWord(){
        var that = this;
        if (!this.isClick)return;
        if (this.newPassWord.trim().length >= 6 && this.newPassWord.length <= 20 && !this.newPassWord==this.c_newPassWord) {
          this.$vux.toast.show({text: '密码输入不一致!', type: 'warn', width: '18em'});
        }
        else {
          that.$post(that.$interface_url.safe.findPwd, {newPassword: that.c_newPassWord}, function (data, success) {
            if (success) {
              that.$vux.toast.show({text: that.$tip_message.change_pwd_success, width: '18em'});
              that.$router.push({path: '/login'});
            } else {
              that.$vux.toast.show({text: data, type: 'warn', width: '18em'});
            }
          });
        }
      },
      hide(){
        this.$router.back();
      }
    },
    watch: {
      'newPassWord': {
        handler: function (val, oldval) {

          if (val.trim().length >= 6 && val.trim().length <= 20 ) {
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
	@import "../../assets/css/reset.less";
</style>
