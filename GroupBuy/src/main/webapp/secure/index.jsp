<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:if test="${empty loginToken}">
  <c:redirect url="http://localhost:8080/GroupBuy/secure/login.jsp"/>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${sessionScope.loginToken.memberNickName}，您好</h1>
	<a href="<c:url value="/overViewMailServlet.do"/>">信箱</a>
	<a href="<c:url value="/secure/logout.jsp"/>">登出</a>
</body>
</html>