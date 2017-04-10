<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${statusCode==404}">
		<h1>${statusCode}</h1>
		<h4>找不到您輸入的位置:${requestUri}。</h4>
	</c:if>
	<c:if test="${statusCode!=404}">
		<h1>${statusCode}</h1>
		<h4>系統發生錯誤，請稍後在試。</h4>
	</c:if>


	<a href="<c:url value='/test.jsp'/>">回首頁</a>
</body>
</html>