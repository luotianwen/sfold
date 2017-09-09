<template>
  <div class="set">
    <div class="set_header">
      <x-header :left-options="{showBack:true}" :right-options="{showMore: true}">{{$route.meta.title}}</x-header>
      <!--点击后显示的菜单-->
      <!--<div class="menu_msg">
				<ul>
					<li>
						<img src="../../assets/image/set/news_img.png"/>
						<p><i>消息</i><badge text="1"></badge></p>

					</li>
					<li>
						<img src="../../assets/image/set/home_page_img.png"/>
						<p>首页</p>
					</li>
					<li>
						<img src="../../assets/image/set/retroaction_img.png"/>
						<p>我要反馈</p>
					</li>
					<li>
						<img src="../../assets/image/set/mine_img.png"/>
						<p>我的</p>
					</li>
				</ul>
				<span class="dot-top"></span>
			</div>-->
    </div>
    <div class="set_content">
      <group>
        <cell title="个人信息" is-link link="/member/person"></cell>
        <cell title="账户与安全" is-link link="/member/safety"></cell>
        <cell title="帮助中心" is-link></cell>
        <div class="wipe_cache" v-on:click="wipeCache">
          <cell title="清除本地缓存" >
            <span class="wipe_msg"><i>{{cacheSize}}</i>KB</span>
          </cell>
        </div>
      </group>

      <group>
        <cell title="关于世峰户外" is-link link="/member/regarding"></cell>
        <cell title="意见反馈" is-link link="/member/feedback"></cell>
      </group>
      <button class="exit_btn" v-on:click="showPlugin">退出当前账户</button>
    </div>

  </div>
</template>


<script>
  import { Confirm,XHeader, Cell, Group, Badge, XButton,TransferDomDirective as TransferDom } from 'vux'
  export default {
    directives: {
      TransferDom
    },
    components: {
      Confirm,XHeader,Cell,Group,Badge,XButton
    },
    data () {
      return {
        show: false,
        cacheSize:(encodeURIComponent(JSON.stringify(localStorage)).length/1024).toFixed(2)
      }
    },
    methods: {
      showPlugin: function () {
        var that = this;
        this.$vux.confirm.show({
          content: '确定退出登录？',
          cancelText: '取消',
          confirmText: '确定',
          onConfirm () {
            that.$removeStorage(that.$storage_key.USERINFO_STORAGE);
            that.$router.push({path: '/login'});
          }
        })
      },
      wipeCache: function () {
        var that = this;
        this.$vux.confirm.show({
          content: '确定清除本地缓存？',
          cancelText: '取消',
          confirmText: '确定',
          onConfirm () {
              //清除缓存，不清除缓存的用户信息
            let userAccount = that.$getStorage(that.$storage_key.USERACCOUNT_STORAGE);
            let userInfo = that.$getStorage(that.$storage_key.USERINFO_STORAGE);
            that.$clearStorage();
            that.cacheSize = 0;
            that.$setStorage(that.$storage_key.USERINFO_STORAGE, userInfo);
            that.$setStorage(that.$storage_key.USERACCOUNT_STORAGE, userAccount);
          }
        })
      }
    }
  }
</script>
<style rel="stylesheet/less" lang="less">
  @import "../../../assets/css/set.less";
  i {
    font-style: normal;

  }
</style>
