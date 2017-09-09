<template>
	<div class="invoice">
		<div class="i_header">
			<x-header :left-options="{showBack:true}">发票信息</x-header>
		</div>
		<div class="i_content">
			<!--点击明细之后-->
			<div class="genre">
				<div class="title">
					发票类型
				</div>
				<div class="o_name">
					<span>纸质发票</span>
				</div>
			</div>
			<div class="particulars">
				<div class="title">
					发票抬头
				</div>
				<div class="rise">
					<div class="option">
						<span class="personage"><i></i>个人</span>
						<span class="unit active"><i></i>单位</span>
					</div>
					<group>
						<x-input title="发票开头"></x-input>
					</group>
				</div>
			</div>
			<!--最初显示-->
			<div class="whether_bill">
				<group>
					<cell title="发票内容"></cell>
				</group>
				<div class="option">
					<span class="y_bill active"><i></i>明细</span>
					<span class="n_bill"><i></i>不开发票</span>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
	import { XInput,XHeader, Group, Cell } from 'vux'
	export default {
		components: {
			XInput,
			XHeader,
			Group,
			Cell
		},
		data() {
			return {}
		},
		methods: {

		}
	}
</script>

<style rel="stylesheet/less" lang="less">
	@import "../../assets/css/invoice.less";
	i,em {
		font-style: normal;
	}
</style>

