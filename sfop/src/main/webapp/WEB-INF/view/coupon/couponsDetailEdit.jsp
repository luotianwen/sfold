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
	
	<title>编辑</title>
	
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<link href="static/css/animate.min.css" rel="stylesheet">
	<link href="static/css/style.min.css" rel="stylesheet">
	<link href="static/css/layerdate/layerdate.css" rel="stylesheet">
	<!-- iCheck -->
	<link href="static/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight" id="vue">
		<div class="row">
			<div id="user-box" class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
						<form id="couponsDetail" method="post" class="form-horizontal">
							<input type="hidden" id="id" name="id" value="${id}"/>
							   <div class="form-group">
									<label class="col-sm-2 control-label">优惠券id</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="优惠券id" id="couponsId" name="couponsId" v-bind:value="couponsDetail!=null?couponsDetail.couponsId:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">用户id</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="用户id" id="user_id" name="user_id" v-bind:value="couponsDetail!=null?couponsDetail.user_id:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">密钥</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="密钥" id="passwords" name="passwords" v-bind:value="couponsDetail!=null?couponsDetail.passwords:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">面值</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="面值" id="money" name="money" v-bind:value="couponsDetail!=null?couponsDetail.money:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">使用面值</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="使用面值" id="useMoney" name="useMoney" v-bind:value="couponsDetail!=null?couponsDetail.useMoney:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">优惠券有效期</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="优惠券有效期" id="startDate" name="startDate" v-bind:value="couponsDetail!=null?couponsDetail.startDate:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">优惠券有效期结束</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="优惠券有效期结束" id="endDate" name="endDate" v-bind:value="couponsDetail!=null?couponsDetail.endDate:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">状态（1：待绑定；2：未使用（已绑定）；3：已使用；4：已作废；5：已过期）</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" placeholder="状态（1：待绑定；2：未使用（已绑定）；3：已使用；4：已作废；5：已过期）" id="state" name="state" v-bind:value="couponsDetail!=null?couponsDetail.state:''">
									</div>
								</div>
							<div class="hr-line-dashed"></div>
					      
							
					 
							
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-2">
									<a class="btn btn-primary" id="submit" @click="submit">保存内容</a>
									<a class="btn btn-white" id="cancel" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name))">取消</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="static/js/vue.js"></script>
	<script src="static/js/jquery-2.1.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/plugins/layer/layer.min.js"></script>
	<!-- iCheck -->
	<script src="static/js/plugins/iCheck/icheck.min.js"></script>
	
	<script type="text/javascript">
		new Vue({
			el : "#vue",
			data : {couponsDetail:null},
			mounted : function(){
				var _this = this;
				$.post("couponsDetail/findCouponsDetailById.json?id="+$("#id").val(),function(data){
					for(var key in data){
						if(_this.key == undefined){
							Vue.set(_this.$data,key,data[key]);
						}else{
							_this.key = data[key];
						}
					}
				})
			},
			methods : {
				submit : function(){
					if (this.check_in()) {
						layer.load(0, {
							shade : 0.3
						});
						var url = "couponsDetail/saveCouponsDetail.json";

						if ($("#id").val().trim()!=null&&$("#id").val().trim()!=''&&$("#id").val().trim()!='0') {
							url = "couponsDetail/updateCouponsDetail.json";
						}
						$.post(url, $('#couponsDetail').serialize(), function(data) {
							if (data.RESPONSE_STATE == '200') {
								layer.msg('保存成功', {
									icon : 1,
									time : 1 * 1000
								}, function() {
									parent.self.location.reload();
								});
							} else {
								layer.closeAll('loading');
								layer.alert(data.ERROR_INFO, {
									icon : 0
								});
							}
						});
					}
				},
				check_in : function(){
					if ($('#id').val().trim() == '') {
						$("#id").val(0);
					}
					if ($('#name').val().trim() == '') {
						layer.tips('名称！！！', '#name', {
							tips : [ 1, '#019F95' ],
							time : 1500
						});
						$('#name').focus();
						return false;
					}
					
					return true;
				}
			}
		})
		
	</script>


</body>


</html>