<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>

<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<title>商城后台 - 管理</title>
	
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/font-awesome.min.css" rel="stylesheet">
	<!-- iCheck -->
	<link href="static/css/plugins/iCheck/custom.css" rel="stylesheet">
	<link href="static/css/animate.min.css" rel="stylesheet">
	<link href="static/css/style.min.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight" id="vue">
		<div class="row">
			<div id="user-box" class="col-sm-12">
				<div class="ibox">
					<form id="couponPackage" method="post">
						<div class="ibox-content">
							<h2>优惠券套餐</h2>
							<div class="input-group">
								<div class="col-md-6">
									<input placeholder="名称" name="name" class="input form-control">
								</div>
								<div class="col-md-4">
									<button type="button" @click="submit" class="btn btn btn-primary">
										<i class="fa fa-search"></i> 搜索
									</button>
								</div>
							</div>
							
							<div class="hr-line-dashed"></div>
							<div class="tab-content">
								<div class="tab-pane active">
									<div class="full-height-scroll">
										<div class="table-responsive">
											<table class="table table-striped table-hover">
												<thead>
													<tr>
														<th>名称</th>
														<th>发券数量</th>
														<th>发行金额</th>
														<th>优惠券有效期</th>
														<th>优惠券有效期结束</th>
														<th>优惠券说明</th>
														<th>优惠券</th>
														<th>状态</th>
														<th>最后修改时间</th>
														<th>最后修改人</th>
														<th>备注</th>
														<th>领取地址</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
													<tr v-for="(couponPackage,index) in couponPackages">
														<td>{{couponPackage.name}}</td>
														<td>{{couponPackage.number}}</td>
														<td>{{couponPackage.money}}</td>
														<td>{{couponPackage.startdate}}</td>
														<td>{{couponPackage.enddate}}</td>
														<td>{{couponPackage.note}}</td>
														<td>
															<span v-for="coupons in couponPackage.coupons">
																{{coupons.name}}-满{{coupons.full}}减{{coupons.minus}},剩余{{coupons.surplusNumber}}张
																<br/>
															</span>
														</td>	
														<td>
															<span v-if="couponPackage.state=='1'">正常</span>
															<span v-else-if="couponPackage.state=='2'">过期</span>
															<span v-else-if="couponPackage.state=='3'">过期</span>
														</td>
														<td>{{couponPackage.lasttime}}</td>
														<td>{{couponPackage.updatename}}</td>
														<td>{{couponPackage.remark}}</td>
														<td>{{couponPackage.url}}</td>
														<td>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>

									<div class="ibox-content">
										<p>
											<button type="button" id="add" @click="add" class="btn btn-sm btn-primary">
												<i class="fa fa-plus"></i> 添加
											</button>
										</p>
									</div>

									<div class="hr-line-dashed"></div>
									<div class="text-center" id="page"></div>
								</div>

							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 全局js -->
	<script src="static/js/vue.js"></script>
	<script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
	<script src="static/js/plugins/layer/laypage/laypage.js"></script>
	<script src="static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

	<!-- iCheck -->
	<script src="static/js/plugins/iCheck/icheck.min.js"></script>
	
	<script type="text/javascript">
		new Vue({
			el : "#vue",
			data : {page:null,couponPackages:null},
			mounted : function(){
				spage(1,"couponPackage/findAllCouponPackage.json",this,"page");
			},
			methods : {
				add : function(){
					layer.open({
						type : 2,
						title : "新增",
						shade : 0.2,
						area : [ "100%", "100%" ],
						content : "couponPackage/goCouponPackageEdit.html"
					});
				},
				submit : function(){
					spage(1,"couponPackage/findAllCouponPackage.json",this,"page",$('#couponPackage').serialize());
				},
				del : function(index){
					var _this = this;
					var id = _this.couponPackages[index].id;
					
					layer.confirm("确认停用该优惠券套餐吗?", {
						shade : 0.3,
						btn : [ '确认', '取消' ],
						icon : 3
					}, function(layerIndex) {
						layer.close(layerIndex);
						$.post("couponPackage/deleteCouponPackage.json",{id:id},function(data){
							if (data.RESPONSE_STATE == '200') {
								layer.msg('操作成功!', {icon : 1,time : 1 * 1000}, function() {
									_this.couponPackages.splice(index,1);
								})
							}else{
								layer.closeAll('loading');
								layer.alert(data.ERROR_INFO, {
									icon : 0
								});
							}
						})
					})
				}
			}
		})
	
		$(function() {
			$(".full-height-scroll").slimScroll({
				height : "100%"
			});

			//设置本页layer皮肤
			layer.config({
				skin : 'layui-layer-molv',
			});
		});
	</script>


</body>


</html>