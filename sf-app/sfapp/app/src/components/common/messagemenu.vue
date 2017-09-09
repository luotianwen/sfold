<template>
  <transition enter-active-class="animated fadeInRight" leave-active-class="animated fadeOutRight">
	<div class="menu">
		<div class="menu_shade" v-show="isShow" @click="hide"><!--透明遮罩层-->
		</div>
		<div class="menu_msg" v-show="isShow" ref="messagemenu">
			<ul>
				<li @click="aa">
					<img src="../../assets/image/set/news_img.png" />
					<p><i>消息</i>
						<badge text="1"></badge>
					</p>

				</li>
				<li>
          <router-link to="/">
					<img src="../../assets/image/set/home_page_img.png" />
					<p>首页</p>
          </router-link>
				</li>
				<li>
          <router-link to="/member/feedback">
					<img src="../../assets/image/set/retroaction_img.png" />
					<p>我要反馈</p>
          </router-link>
				</li>
				<li>
          <router-link to="/member">
					<img src="../../assets/image/set/mine_img.png" />
					<p>我的</p>
          </router-link>
				</li>
			</ul>
			<span class="dot-top"></span>
		</div>
	</div>
  </transition>
</template>

<script type="text/ecmascript-6">
	import { Badge } from 'vux'
	export default {
		data() {
			return {
				isShow: false
			}
		},
		components: {
			Badge
		},
		methods: {
			show() {
				this.isShow = true;
			},
			hide() {
				this.isShow = false;
			},
      aa(){
			    console.log(111)
      }
		},
		computed: {

		},
		created() {

		}
	}
</script>

<style lang="less">
	@font-size-base: 75;
	.menu_msg {
		width: 268rem/@font-size-base;
		height: auto;
		position: absolute;
		top: 70rem/@font-size-base;
		right: 10rem/@font-size-base;
		z-index: 9999;
		.dot-top {
			position: absolute;
			top: -12rem/@font-size-base;
			right: 12rem/@font-size-base;
			margin-bottom: 23rem/@font-size-base;
			font-size: 0;
			line-height: 0;
			border-width: 16rem/@font-size-base;
			border-color: #7c7c7c;
			border-top-width: 0;
			border-style: dashed;
			border-bottom-style: solid;
			border-left-color: transparent;
			border-right-color: transparent;
		}
		ul {
			background: #7c7c7c;
			width: 100%;
			height: auto;
			border-radius: 10rem/@font-size-base;
			overflow: hidden;
			&:after {
				width: 0;
				height: 0;
				border: 11rem/@font-size-base solid transparent;
				border-bottom: 11rem/@font-size-base solid #0F8055;
				position: absolute;
				top: -22rem/@font-size-base;
				right: 20rem/@font-size-base;
			}
			li {
				width: 100%;
				height: 88rem/@font-size-base;
				border-bottom: 1px solid #a0a0a0;
				img {
					width: 38rem/@font-size-base;
					float: left;
					padding: 0 22rem/@font-size-base;
					vertical-align: middle;
					margin-top: 23rem/@font-size-base;
				}
				p {
					float: left;
					height: 100%;
					width: 180rem/@font-size-base;
					line-height: 88rem/@font-size-base;
					font-size: 28rem/@font-size-base;
					color: #FFFFFF;
					i {
						font-style: normal;
					}
					span {
						width: 38rem/@font-size-base !important;
						height: 38rem/@font-size-base !important;
						border-radius: 50% !important;
						font-size: 24rem/@font-size-base;
						margin-left: 70rem/@font-size-base;
						line-height: 38rem/@font-size-base;
					}
				}
			}
		}
	}

.menu_shade {
	width: 100%;
	position: fixed;
	height: 100%;
	background: red;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	opacity: 0;
}
</style>
