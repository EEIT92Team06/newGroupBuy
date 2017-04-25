<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>
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

	<div style="text-align: center;" class="cart-info container">
	<h1>您創的團</h1>
		<table class="table table-striped table-bordered">
			<tr>
				<th style="text-align: center;vertical-align: middle;" class="image">圖片</th>
				<th style="text-align: center;vertical-align: middle;" class="name">創團日期</th>
				<th style="text-align: center;vertical-align: middle;" class="model">團名</th>
				<th style="text-align: center;vertical-align: middle;" class="quantity">狀態</th>
				<th style="text-align: center;vertical-align: middle;" class="quantity">類型</th>
				<th style="text-align: center;vertical-align: middle;" class="quantity">目前產品數量</th>
				<th style="text-align: center;vertical-align: middle;" class="quantity">結束日期</th>
				<th style="text-align: center;vertical-align: middle;" class="quantity">明細</th>

			</tr>
		<c:forEach var="bean" items="${selectMyCreatedGroupInfo}">
			
			<tr>
			<input name="groupInfoNo" type="hidden" value="${bean.groupInfoNo}"/>
			<td style="text-align: center;vertical-align: middle;" class="image"><a href="#"><img title="product"
						alt="product" src="<c:url value='/eeit9212/getimage?groupInfoNo=${bean.groupInfoNo}'/>" height="50" width="50"></a></td>
					<td style="text-align: center;vertical-align: middle;" class="name">${bean.formatStartDate}</td>
			<td id="groupInfoNameId${bean.groupInfoNo}" style="text-align: center;vertical-align: middle;" class="model">${bean.groupInfoName}</td>
				<td id="groupStatusId${bean.groupInfoNo}" style="text-align: center;vertical-align: middle;" class="quantity">${bean.groupStatus}</td>
				<td style="text-align: center;vertical-align: middle;" class="quantity">${bean.productType}</td>
				<td style="text-align: center;vertical-align: middle;" class="quantity"><c:if test="${empty bean.groupInfoTotalProductQt}">0</c:if>
					<c:if test="${not empty bean.groupInfoTotalProductQt}">${bean.groupInfoTotalProductQt}</c:if>/${bean.groupInfoMinProductQt}
				</td>
				

				<td style="text-align: center;vertical-align: middle;" class="quantity">${bean.formatDeadLine}
				</td>
				<td style="text-align: center;vertical-align: middle;" class="quantity"><a href="<c:url value='/eeit9212/grouprecord/mycreatedgroupinfo.controller?groupInfoNo=${bean.groupInfoNo}'/>">查看明細
				</a></td>

			<c:if test="${bean.groupStatusNo==1||bean.groupStatusNo==3}">
				<input name='groupInfoDeadLine' type="hidden" value="${bean.groupInfoDeadLine}"/>
				
			</c:if>
			<c:if test="${bean.groupStatusNo==2||bean.groupStatusNo==6}">
				<c:set var="groupName"
					value="${groupName}${bean.groupInfoName}，<br>" />
			</c:if>
			<c:if test="${bean.groupStatusNo==8}">
				<c:set var="needCheckPayGroupName"
					value="${needCheckPayGroupName}${bean.groupInfoName}，<br>" />
			</c:if>
			</tr>
		
		</c:forEach>
		</table>
	</div>
	<script src="<c:url value='/js/jquery-3.1.1.min.js'></c:url>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>
	<script>
				$(function(){
					
					$("input[name='groupInfoDeadLine']").each(function(){
						var groupInfoNo=$(this).parents("tr").find("input[name='groupInfoNo']").val();
						var groupInfoName=$("#groupInfoNameId"+groupInfoNo).html();
						var groupStatus=$("#groupStatusId"+groupInfoNo);
						var deadLine = new Date($(this).val());
						var nowTime = new Date();
						console.log(deadLine - nowTime);
						var timeout1 = setTimeout(function(){
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"timeout","groupInfoNo":groupInfoNo},function(data){					
								groupStatus.empty().append("等待主揪處理中");
								layer.alert('您創的團名:<br>'+groupInfoName+'<br>截止日期已到，請盡快處理。', {
									  skin: 'layui-layer-molv' //样式类名
									  ,btn:'確定'
									  ,closeBtn: 0}
								);
							});	
							
							
						}, deadLine - nowTime);
						
					});
							
					if(${not empty groupName}){

						layer.alert('您創的團名:<br>${groupName}截止日期已到，請盡快處理。', {
							  skin: 'layui-layer-molv' //样式类名
							  ,btn:'確定'
							  ,closeBtn: 0}
						);
					}
					if(${not empty needCheckPayGroupName}){
						layer.alert('您創的團名:<br>${needCheckPayGroupName}匯款截止日期已到，請再次確認匯款資訊後開始寄貨。', {
							  skin: 'layui-layer-molv' //样式类名
								,btn:'確定'
							  ,closeBtn: 0
							});
					}
				});
	</script>
</body>
</html>