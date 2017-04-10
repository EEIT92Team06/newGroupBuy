<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px">
		<thead>


			<tr>

				<th>圖片</th>
				<th>創團日期</th>
				<th>主揪</th>
				<th>主揪評分</th>
				<th>團名</th>
				<th>狀態</th>
				<th>類型</th>
				<th>目前產品數量</th>
				<th>結束日期</th>
				<th>訂單狀態</th>
				<th>查看訂單明細</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${selectMyAttendedGroupInfo}">
				<tr>

					<td><img
						src="<c:url value='/eeit9212/getimage?groupInfoNo=${bean.groupInfoNo}'/>" /></td>
					<td>${bean.groupInfoStartDate}</td>
					<td>${bean.memberName}</td>
					<c:if test="${bean.grouperCredit!=0}">
					<td>${bean.grouperCredit}</td>
					</c:if>
					<c:if test="${bean.grouperCredit==0}">
					<td>主揪第一次開團</td>
					</c:if>
					<td>${bean.groupInfoName}</td>
					<td>${bean.groupStatus}</td>
					<td>${bean.productType}</td>
					<td>${bean.groupInfoTotalProductQt}/${bean.groupInfoMinProductQt}</td>
					<td>${bean.groupInfoDeadLine}</td>
					<td>${bean.orderStatus}</td>
					<td><a
						href="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller?groupInfoNo=${bean.groupInfoNo}&orderInfoNo=${bean.orderInfoNo}'/>">查看訂單明細</a>
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