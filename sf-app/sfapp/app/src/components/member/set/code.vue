<template>
	<div class="code">
		<div class="code_header">
			<x-header :left-options="{showBack:true,backText:''}">我的二维码名片<span class="share" v-model="show" @click="shareShow"><i></i></span></x-header>
		</div>
		<div class="code_content">
			<ul>
				<li class="my_name">{{userInfo&&userInfo.name?userInfo.name:''}}</li>
				<li class="my_code">
          <qrcode :value="value" :fg-color="fgColor"></qrcode>
          <!--<v-qrcode value="hello" />-->
        </li>
				<li class="my_msg">手机扫一扫，劲爆消息触手可得！</li>
			</ul>
		</div>
  <share :url="111" :title="1" :description="2" :imgSrc="3" ref="share"></share>
	</div>
</template>
<script>
	import {XHeader, Group,Qrcode} from 'vux'
  import Share from '../../common/share.vue'

	export default {
		components: {
			XHeader,
			Group,
      Qrcode,Share
		},
		data () {
    		return {
    			show: false,
          userInfo:{},
          value:'',
          fgColor:''
    		}
   		},
	  	methods: {
	  		shareShow () {
		      this.$refs.share.show();
		   }
	  	},
    created(){
      this.userInfo=this.$getStorage(this.$storage_key.USERINFO_STORAGE);
    },
    mounted(){
        this.value='http://m.seebong.com/uid='+this.userInfo.id;
        this.fgColor='#000000';
    }
	}

</script>

<style rel="stylesheet/less" lang="less">
	@import "../../../assets/css/code.less";
	i{
		font-style: normal;
	}
</style>
