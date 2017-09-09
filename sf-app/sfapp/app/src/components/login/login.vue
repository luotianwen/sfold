<template>
  <div class="login">
    <div class="l_header">
      <x-header :left-options="{showBack:true}">世峰户外登录</x-header>
    </div>
    <div class="l_content">
      <group>
        <x-input title="账号"  placeholder="手机号码/邮箱/用户名" v-model="userInfo.userName"></x-input>
      </group>
      <div class="l_password">
        <group>
          <x-input title="密码"  placeholder="请输入密码" :type="pwdType" :show-clear="true" v-model="userInfo.passWord"></x-input>
          <span :class="{'dis_show':pwdType=='text','dis_hide':pwdType=='password'}" @click="showPassword"><i></i></span>
        </group>
      </div>
      <div class="l_btn">
        <!--未输入值时禁止点击，边框字体颜色为#99d9fa,获取焦点后颜色为#009ff2-->
        <x-button @click.native="loginIn" type="primary" plain :class="{'weui-btn_plain-primary_active':isClick}">登录</x-button>
      </div>
      <div class="extras">
        <ul>
          <li class="extras_first"><router-link to="/regiter">新用户注册</router-link></li>
          <li class="extras_last"><router-link to="/retrieve">忘记密码</router-link></li>
        </ul>
      </div>
    </div>
    <div class="other_login">
			<!--点击后效果active-->
			<ul >
				<li class="wx" @click="wechatLogin"><span></span><i>微信登录</i></li>
				<li class="qq" @click="qqLogin"><span></span><i>QQ登录</i></li>
			</ul>
		</div>
  </div>
</template>
<script type="text/ecmascript-6">
import {XHeader, XButton,XInput,Group,Cell} from 'vux'


import * as types from '../../store/mutation-types'
import {mapGetters} from 'vuex'
export default{
  data(){
    return {
      userInfo: {
        userName: "",
        passWord: "",
        type: 1//用户登录类型，1账户密码，2微信，3新浪微博，4qq
      },
      isClick: false,
      pwdType: 'password'
    }
  },
  components: {
    XHeader,
    XButton,
    XInput,
    Group,
    Cell
  },
  created(){
    let account = this.$getStorage(this.$storage_key.USERACCOUNT_STORAGE);
    if (account) {
      this.userInfo.userName = account;
    }
  },
  computed: {
    ...mapGetters({
      currentDevice: 'currentDevice'
    })
  },
  methods: {
    showPassword(){
      if (this.pwdType == 'password') {
        this.pwdType = 'text';
      }
      else {
        this.pwdType = 'password';
      }
    },
    loginIn(){
      if (!this.isClick)return;
      var _this = this;
      _this.$post(_this.$interface_url.login.login,
        {
          account: this.userInfo.userName.trim(),
          password: this.userInfo.passWord.trim()
        },
        function (data, success) {
          if (data && success) {
            let today = new Date()
            data["timeout"] = today.setDate(today.getDate() + 1);//一天过期
            data["password"] = _this.userInfo.passWord;
            data.account=_this.userInfo.userName.trim();

            _this.$setStorage(_this.$storage_key.USERINFO_STORAGE, data);
            _this.$setStorage(_this.$storage_key.USERACCOUNT_STORAGE, _this.userInfo.userName.trim())
            let query = _this.$route.query;
            _this.$vux.toast.show({
              text: _this.$tip_message.login_success,
              type: 'text'
            });
            if (query&&query.returnurl) {
              _this.$router.push({path: query.returnurl});
            } else {
              _this.$router.push({path: '/member'})
            }
          } else {
            _this.$vux.toast.show({
              text: data,
              type: 'text'
            });
          }
        });
    },
    wechatLogin(){
alert('开始执行...');

      Wechat.isInstalled(function (installed) {
        if(installed){
            alert('已安装微信');
          var scope = "snsapi_userinfo",
            state = "_" + (+new Date());
          alert(scope);
          alert(state);
          Wechat.auth(scope, state, function (response) {
            // you may use response.code to get the access token.
            alert(JSON.stringify(response));
            alert(response);
            alert(JSON.stringify(response).code);
          }, function (reason) {
            alert("Failed: " + reason);
          });

        }
        else{
            alert('未安装微信');
        }
      }, function (reason) {
        alert("Failed: " + reason);
      });

    },
    qqLogin(){
        var that=this;
      QQSDK.checkClientInstalled(function () {
        QQSDK.ssoLogin(function (args) {
          alert("token is " + args.access_token);
          alert("userid is " +args.userid);
          alert("expires_time is "+ new Date(parseInt(args.expires_time)) + " TimeStamp is " +args.expires_time);
        }, function (failReason) {
          alert(failReason);
        });
      }, function () {
        // if installed QQ Client version is not supported sso,also will get this error
        that.$vux.toast.show({text:'未安装QQ客户端！',type:'warn'});
      });
    }
  }
  ,
  watch: {
    'userInfo': {//监测用户输入，重置登录按钮状态
      handler: function (val, oldVal) {
        if (val.userName.trim().length > 0 && val.passWord.trim().length > 0) {
          this.isClick = true;
        }
        else {
          this.isClick = false;
        }
      },
      deep: true
    },
    deep: true
  }
}
</script>

<style rel="stylesheet/less" lang="less">
  @import "../../assets/css/login.less";
</style>
