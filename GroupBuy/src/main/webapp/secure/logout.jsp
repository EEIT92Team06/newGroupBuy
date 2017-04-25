<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		session.invalidate();
	%>
<%-- 	<jsp:include page="/Web_02/headline.jsp"></jsp:include> --%>
	<%-- 	<c:remove var="loginToken" scope="session" /> --%>


</body>
<script>
	location.replace("${pageContext.request.contextPath}/theindex.jsp");


</script>
</html>