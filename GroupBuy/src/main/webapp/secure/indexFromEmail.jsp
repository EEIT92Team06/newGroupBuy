<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 給email開通後跳轉用的網頁 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	 $('#autoclickme').click();
	});
</script>
<jsp:include page="/secure/headlineFromEmail.jsp"/>
<jsp:include page="/secure/loginFromEmail.jsp"/>
<c:if test="${not empty loginToken}">
<h1>${sessionScope.loginToken.memberNickName}，您好</h1>
</c:if>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaafffffffffaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaauuuuuuuuuuaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaassssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
<p>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>

</body>
</html>