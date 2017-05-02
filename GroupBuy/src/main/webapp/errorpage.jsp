<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />

<title>GroupBuy團購網</title>

<jsp:include page="/headline.jsp"/>
<style type="text/css">
.button_s {
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: #F25C27;
	color: white;
	line-height: 40px;
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
}
.button_b {
	font-size: 30px;
	font-weight: normal;
	border-radius: 10px;
	background-color: gray;
	color: white;
	line-height: 40px;
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
	width: 170px;
	height: 70px;
	
}

</style>
</head>
<body style="background-color:#DDDDDD;">


<div style="text-align:center;margin-top:230px;">
	<c:if test="${statusCode==404}">
		<div style="font-size: 50px">
		很抱歉，找不到您輸入的位置:<br>${requestUri}。<br>
		請重新輸入。
		</div>
	</c:if>
	<c:if test="${statusCode!=404}">
		<div style="font-size: 50px">
		很抱歉，系統忙碌中，請稍後再試。
		</div>
	</c:if>
	<button style="margin-top:20px" class="button_b" onclick="history.back()">上一頁</button>
</div>
	
</body>
</html>