<template>
	<!--修改手机号码-->
	<!--跳转页面--标题'修改手机号码'-->
	<!--下一步页面--标题'验证手机'-->
	<!--下一步跳转登陆页面-->
	<!--修改登录密码-->
	<!--跳转页面--标题'重置密码'-->
	<!--下一步跳转登陆页面-->
	<div class="sa_address">
		<div class="sa_header">
			<x-header :left-options="{showBack:true,backText:''}">账户与安全</x-header>
		</div>
		<div class="sa_content">
			<group>
				<cell :title="userInfo&&userInfo.phone?'修改手机号':'绑定手机号'" is-link>
					<span>{{ userInfo&&userInfo.phone?(userInfo.phone.substr(0, 3) + '****' + userInfo.phone.substr(7)):''}}</span>
				</cell>
				<cell title="修改登录密码" is-link link="/retrieve/2"></cell>
			</group>
		</div>
	</div>
</template>
<script>
	import {XHeader, Cell, Group} from 'vux'
	export default {
    components: {
      XHeader,
      Cell,
      Group
    },
    data () {
      return {
          userInfo:{}
      }
    },
    methods: {

    },
    created(){
      this.userInfo=this.$getStorage(this.$storage_key.USERINFO_STORAGE);
      console.log(this.userInfo)
    }
  }
</script>

<style rel="stylesheet/less" lang="less">
	@import "../../../assets/css/safety.less";
</style>
