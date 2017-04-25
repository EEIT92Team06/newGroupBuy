<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>
<style type="text/css">
.full {
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	margin-left: 0;
	margin-top: 100px;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="/headline.jsp" />
	<jsp:include page="/secure/newLogin.jsp"/>
	<div class="full">
		<h1>Hi，親愛的${sessionScope.registryTokenAccount.memberNickName}您好!!</h1>
		您已完成第一部分的註冊，請至您的信箱開通帳號，以成為GroupBuy會員喔!!
	</div>
</body>
</html>