<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />
<style type="text/css">
.full {
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	margin-left: 0;
	margin-top: 150px;
	text-align: center;
}
</style>
</head>
<body>
<jsp:include page="/headline2.jsp"/>
<jsp:include page="/secure/newLogin.jsp"/>
	<div class="full">
		<h1><font size="12">Hi，親愛的 ${sessionScope.loginToken.memberNickName} 您好!!</font></h1>
		<h2 style="margin-top: 50px">您目前為水桶狀態，您將於  <font color="red">${sessionScope.banT}</font>  後才能繼續使用GroupBuy網站喔!</h2>
	</div>
</body>
</html>