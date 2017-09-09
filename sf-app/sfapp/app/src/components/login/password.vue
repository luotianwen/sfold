<template>
	<div class="pwd">
		<div class="p_header">
			<x-header :left-options="{showBack:false}"><span class="close_btn"><i></i></span>设置密码</x-header>
		</div>
		<div class="p_content">
			<div class="l_password">
		    	<group>
			      	<x-input title="密码"  placeholder="请输入密码" :type="pwdType" :show-clear="true" v-model="password"></x-input>
			      	<span :class="{'dis_show':pwdType=='password','dis_hide':pwdType!='password'}" @click="changePwdType"><i></i></span>
			   	</group>
		    </div>
		   <p class="p_hint">密码由6-20位英文字母、数字或符号组成</p>
		    <div class="p_btn">
    			<!--未输入值时禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->
				<x-button @click.native="saveRegiter" type="primary" plain
                  :class="{'weui-btn_plain-primary_active':password.trim().length>=6&&password.trim().length<=20}">下一步</x-button>
			</div>
		</div>
	</div>
</template>
<script>
	import {XHeader, XButton,XInput,Group,Cell} from 'vux'


	export default {
    components: {
      XHeader, XButton, XInput, Group, Cell
    },
    data () {
      return {
        password: '',
        pwdType: 'password'
      }
    },
    methods: {
      changePwdType(){
        if (this.pwdType == 'password') {
          this.pwdType = 'text';
        }
        else {
          this.pwdType = 'password';
        }
      },
      saveRegiter(){
        var that = this;
        if (that.password.trim().length >= 6 && that.password.trim().length <= 20) {
          that.$post('reg/register', {password: that.password}, function (data, success) {
            if (success) {
              that.$vux.toast.show({text: that.$tip_message.regiter_success, type: 'text', width: '18em'});

              let today = new Date();
              data["timeout"] = today.setDate(today.getDate() + 1);//一天过期
              data["password"] = that.passWord;
              data.account=data.phone;
              that.$setStorage(that.$storage_key.USERINFO_STORAGE, data);
              that.$setStorage(that.$storage_key.USERACCOUNT_STORAGE, data.phone);
              that.$router.push({path: '/member'});
            }
            else {
              that.$vux.toast.show({text: data, type: 'warn', width: '18em'});
            }
          })
        }
      }
    }
  }
</script>

<style rel="stylesheet/less" lang="less">
	@import "../../assets/css/password.less";
</style>
