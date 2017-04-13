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
	<jsp:include page="/Web_02/headline.jsp"></jsp:include>
	<table border="1px">
		<thead>
			<tr>
				<th>查看明細</th>
				<th>圖片</th>
				<th>創團日期</th>
				<th>團名</th>
				<th>狀態</th>
				<th>類型</th>
				<th>目前產品數量</th>
				<th>結束日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bean" items="${selectMyCreatedGroupInfo}">
				<tr>
					<td><a
						href="<c:url value='/eeit9212/grouprecord/mycreatedgroupinfo.controller?groupInfoNo=${bean.groupInfoNo}'/>">查看明細</a></td>
					<td><img
						src="<c:url value='/eeit9212/getimage?groupInfoNo=${bean.groupInfoNo}'/>" /></td>
					<td>${bean.groupInfoStartDate}</td>
					<td>${bean.groupInfoName}</td>
					<td>${bean.groupStatus}</td>
					<td>${bean.productType}</td>
					
					<td>
					<c:if test="${empty bean.groupInfoTotalProductQt}">0</c:if>
					<c:if test="${not empty bean.groupInfoTotalProductQt}">${bean.groupInfoTotalProductQt}</c:if>/${bean.groupInfoMinProductQt}
					</td>
					<td>${bean.groupInfoDeadLine}</td>

				</tr>
				<c:if test="${bean.groupStatusNo==2||bean.groupStatusNo==6}">
					<c:set var="groupName"
						value="${groupName}${bean.groupInfoName}，<br>" />
				</c:if> 
				<c:if test="${bean.groupStatusNo==8}">
					<c:set var="needCheckPayGroupName"
						value="${needCheckPayGroupName}${bean.groupInfoName}，<br>" />
				</c:if>
			</c:forEach>	
		</tbody>
	</table>
	<script src="<c:url value='/js/jquery-3.1.1.min.js'></c:url>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>
	<script>
		$(function() {
			
			if(${not empty groupName}){
				
				layer.alert('您創的團名:<br>${groupName}截止日期已到，請盡快處理。', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0}
				);
			}
			if(${not empty needCheckPayGroupName}){
				layer.alert('您創的團名:<br>${needCheckPayGroupName}匯款截止日期已到，請再次確認匯款資訊後開始寄貨。', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					});
			}
		});


</script>
</body>
</html>