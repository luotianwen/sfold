<template>
	<div class="regarding">
		<div class="rega_header">
			<x-header :left-options="{showBack:true,backText:''}">关于<span class="share" v-model="show" @click="shareShow"><i></i></span></x-header>
		</div>
		<div class="rega_content">
			<ul>
				<li class="ra_logo"><img src="../../../assets/image/set/logo_img.png"/></li>
				<li class="ra_versions">当前版本：<i>1.0</i></li>
				<li class="ra_code"><img src="../../../assets/image/set/app_img.png"/></li>
				<li class="ra_msg1">扫描二维码</li>
				<li class="ra_msg2">您的朋友也可以下载世峰户外客户端</li>
			</ul>
		</div>
		<popup v-model="show" >
	        <div class="ditch_msg">
	        	<h1>分享</h1>
	        	<div class="ditch_classify">
	        		<div class="ditch_item1 wxin">
	        			<img src="../../../assets/image/set/weixin_img.png"/>
	        			<span>微信好友</span>
	        		</div>
	        		<div class="ditch_item1 friends">
	        			<img src="../../../assets/image/set/friends_img.png"/>
	        			<span>朋友圈</span>
	        		</div>
	        		<div class="ditch_item1 qq">
	        			<img src="../../../assets/image/set/qq_img.png"/>
	        			<span>QQ好友</span>
	        		</div>
	        		<div class="ditch_item1 sina">
	        			<img src="../../../assets/image/set/sina_img.png"/>
	        			<span>新浪微博</span>
	        		</div>
	        		<div class="ditch_item2 copylink">
	        			<img src="../../../assets/image/set/copy_img.png"/>
	        			<span>复制链接</span>
	        		</div>
	        	</div>
	        </div>
	        <div class="off_btn" @click="shareHide">
	        	<button>取消</button>
	        </div>
	    </popup>
	</div>
</template>
<script>
	import {XHeader, Cell, Group,Popup} from 'vux'
	export default {
		components: {
			XHeader,
			Cell,
			Group,
			Popup
		},
		data () {
    		return {
    			show: false
    		}
   		},
	  	methods: {
	  		shareShow () {
		      this.show=true;
		   },
		   shareHide () {
		      this.show=false;
		    }
	  	}
	}
</script>

<style rel="stylesheet/less" lang="less">
	@import "../../../assets/css/regarding.less";
	i{
		font-style: normal;
	}
</style>
