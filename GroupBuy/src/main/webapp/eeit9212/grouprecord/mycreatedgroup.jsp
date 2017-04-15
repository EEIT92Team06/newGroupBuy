<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <link href="<c:url value='/css/bootstrap.css'/>" rel="stylesheet"> --%>
<%-- <link href="<c:url value='/css/bootstrap-responsive.css'/>" rel="stylesheet"> --%>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet">
<%-- <link href="<c:url value='/css/flexslider.css'/>" type="text/css" media="screen" rel="stylesheet"  /> --%>
<%-- <link href="<c:url value='/css/jquery.fancybox.css'/>" rel="stylesheet"> --%>
<%-- <link href="<c:url value='/css/cloud-zoom.css'/>" rel="stylesheet"> --%>
<style type="text/css">




</style>

</head>
<body>
	<jsp:include page="/headline.jsp"></jsp:include>

	<div class="cart-info container">
		<table class="table table-striped table-bordered">
			<tr>
				<th class="image">圖片</th>
				<th class="name">創團日期</th>
				<th class="model">團名</th>
				<th class="quantity">狀態</th>
				<th class="quantity">類型</th>
				<th class="quantity">目前產品數量</th>
				<th class="quantity">結束日期</th>

			</tr>
		<c:forEach var="bean" items="${selectMyCreatedGroupInfo}">
			<tr>
			<td class="image"><a href="#"><img title="product"
						alt="product" src="<c:url value='/eeit9212/getimage?groupInfoNo=${bean.groupInfoNo}'/>" height="50" width="50"></a></td>
					<td class="name">${bean.groupInfoStartDate}</td>
			<td class="model">${bean.groupInfoName}</td>
				<td class="quantity">${bean.groupStatus}</td>
				<td class="quantity">${bean.productType}</td>
				<td class="quantity"><c:if test="${empty bean.groupInfoTotalProductQt}">0</c:if>
					<c:if test="${not empty bean.groupInfoTotalProductQt}">${bean.groupInfoTotalProductQt}</c:if>/${bean.groupInfoMinProductQt}
				</td>
				

				<td class="quantity">${bean.groupInfoDeadLine}
				<a href="<c:url value='/eeit9212/grouprecord/mycreatedgroupinfo.controller?groupInfoNo=${bean.groupInfoNo}'/>">查看明細
				</a>
				</td>

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
		</table>
	</div>
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