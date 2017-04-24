<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet">
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<div style="text-align: center;" class="cart-info container">
<h1>您參加的團</h1>
	<table class="table table-striped table-bordered">
		<thead>
			<tr>

				<th style="text-align: center;vertical-align: middle;">圖片</th>
				<th style="text-align: center;vertical-align: middle;">創團日期</th>
				<th style="text-align: center;vertical-align: middle;">主揪</th>
				<th style="text-align: center;vertical-align: middle;">主揪評分</th>
				<th style="text-align: center;vertical-align: middle;">團名</th>
				<th style="text-align: center;vertical-align: middle;">狀態</th>
				<th style="text-align: center;vertical-align: middle;">類型</th>
				<th style="text-align: center;vertical-align: middle;">目前產品數量</th>
				<th style="text-align: center;vertical-align: middle;">結束日期</th>
				<th style="text-align: center;vertical-align: middle;">訂單狀態</th>
				<th style="text-align: center;vertical-align: middle;">明細</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${selectMyAttendedGroupInfo}">
				<tr>
					<td style="text-align: center;vertical-align: middle;">
					<a href="#"><img title="product"
						alt="product" src="<c:url value='/eeit9212/getimage?groupInfoNo=${bean.groupInfoNo}'/>" height="50" width="50"></a>	
					</td>
					<td style="text-align: center;vertical-align: middle;">${bean.groupInfoStartDate}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.memberName}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.formatGrouperCredit}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.groupInfoName}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.groupStatus}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.productType}</td>
					<td style="text-align: center;vertical-align: middle;"><c:if test="${empty bean.groupInfoTotalProductQt}">0</c:if>
					<c:if test="${not empty bean.groupInfoTotalProductQt}">${bean.groupInfoTotalProductQt}</c:if>/${bean.groupInfoMinProductQt}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.formatDeadLine}</td>
					<td style="text-align: center;vertical-align: middle;">${bean.orderStatus}</td>
					<td style="text-align: center;vertical-align: middle;"><a
						href="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller?groupInfoNo=${bean.groupInfoNo}&orderInfoNo=${bean.orderInfoNo}'/>">查看明細</a>
						</td>
						
				</tr>
				<%-- 				<c:if test="${bean.groupStatusNo==2||bean.groupStatusNo==6}"> --%>
				<%-- 					<c:set var="groupName" --%>
				<%-- 						value="${groupName}${bean.groupInfoName}，<br>" /> --%>
				<%-- 				</c:if> --%>
				<c:if test="${bean.orderStatusNo==1104}">
					
					<c:set var="notPayGroupName"
						value="${notPayGroupName}${bean.groupInfoName}，匯款時間到:${bean.groupInfoDeadLine}截止。<br>" />
					
				</c:if>

				<%-- 				<c:if test="${bean.orderStatusNo==1006}"> --%>
				<%-- 					<c:set var="groupName1006" --%>
				<%-- 						value="${groupName1006}${bean.groupInfoName}，<br>" /> --%>
				<%-- 				</c:if> --%>

			</c:forEach>
		</tbody>
	</table>
</div>
	<script src="<c:url value='/js/jquery-3.1.1.min.js'></c:url>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>
	<script>
		$(function() {
// 			if(${not empty groupName}){
// 				layer.alert('您參加的團名:<br>${groupName}截止日期已到，等待主揪處理中。', {
// 					  skin: 'layui-layer-molv' //样式类名
// 					  ,closeBtn: 0
// 					});
// 			}
			if(${not empty notPayGroupName}){
				layer.alert('您參加的團名:<br>${notPayGroupName}時間剩不到24H，請盡快匯款。', {
					  skin: 'layui-layer-molv' //样式类名
						  ,btn:'確定'
					  ,closeBtn: 0
					});
			}
			
// 			if(${not empty groupName1006}){
// 				alert('hi');
// 				layer.alert('您參加的團名:<br>${groupName1006}已流團。', {
// 					  skin: 'layui-layer-molv' //样式类名
// 					  ,closeBtn: 0
// 					},function(){
// 						location.replace('myattendedgroupinfo.controller?locationFrom=checkNoExtension');
// 					});
// 			}
			
			
			
		});

</script>


</body>
</html>