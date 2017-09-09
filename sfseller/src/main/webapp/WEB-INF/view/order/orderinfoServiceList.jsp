<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${orderInfoservices }" var="item">
	<tr>
		<td>
			${item.id }
		</td>
		<td>
			<span>${item.receiveName }</span>
			<span>${item.phone }</span>
		</td>
		<td>
			${item.productName }
		</td>
		<td>
			<fmt:formatDate value="${item.lasttime}" pattern="yyyy-MM-dd HH:mm:ss" />
		</td>
		<td>
			${item.orderId }
		</td>
		<td>状态 1：待审核；2：审核失败；3：等待买家发货；4：等待商家收货；5：等待商家发货/退款中；6：商家已发货/已退款；7：完成
			<c:if test="${item.state==1 }">
				待审核
			</c:if>
			<c:if test="${item.state==2 }">
				审核失败
			</c:if>
			<c:if test="${item.state==3 }">
				等待买家发货
			</c:if>
			<c:if test="${item.state==4 }">
				等待商家收货
			</c:if>
			<c:if test="${item.type==1 || item.type==3 }">
				<c:if test="${item.state==5 }">
					等待商家发货
				</c:if>
				<c:if test="${item.state==6 }">
					商家已发货
				</c:if>
			</c:if>
			<c:if test="${item.type==2 }">
				<c:if test="${item.state==5 }">
					退款中
				</c:if>
				<c:if test="${item.state==6 }">
					已退款
				</c:if>
			</c:if>
			<c:if test="${item.state==7 }">
				完成
			</c:if>
		</td>
		<td>
			<c:if test="${item.type==1 }">
				换货单
			</c:if>
			<c:if test="${item.type==2 }">
				退货单
			</c:if>
			<c:if test="${item.type==3 }">
				维修单
			</c:if>
		</td>
		<td>
			<c:if test="${item.state==1 }">
				<a class="cursor" id="audit" data_id="${item.id }">审核</a><br>
			</c:if>
			<c:if test="${item.state==4 }">
				<a class="cursor" id="confirm" data_id="${item.id }">确认收货</a><br>
			</c:if>
			<a class="cursor" id="check" data_id="${item.id }">查看</a><br>
		</td>
	</tr>
</c:forEach>

<script type="text/javascript">
	$(function(){
		setPage("${page.currentPage}","orderInfoservice/findAllOrderInfoService.html","${page.totalPage}","orderList",$("#form").serialize());
	
		//审核
		$("#audit").on("click",function(){
			$.post("orderInfoservice/auditOrderInfoService.html",{id:$(this).attr("data_id")},function(data){
				$("#content").html(data);
			})
		})
		
		//确认收货
		$("#confirm").on("click",function(){
			$.post("orderInfoservice/updateOrderInfoService.html",{id:$(this).attr("data_id"),state:4},function(data){
				if (data.RESPONSE_STATE == '200') {
					layer.msg('确认收货成功', {
						icon : 1,
						time : 1 * 1000
					}, function() {
						parent.self.location.reload();
					});
				} else {
					layer.alert(data.ERROR_INFO, {
						icon : 0
					});
				}
			})
		})
	})
	
	function o_submit(){
		spage("${page.currentPage}","orderInfoservice/findAllOrderInfoService.html","orderList",$("#form").serialize());
	}
</script>