<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberInformation</title>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet">
<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />
</head>
<body>
	<jsp:include page="/headline.jsp"></jsp:include>
	<style>
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
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: gray;
	color: white;
	line-height: 40px;
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
}
td {
	padding: 2px 22px 7px 0px;
}

.ccle div {
	display: inline-block; /*讓div並排*/
	vertical-align: top;
}
</style>
	<div>
		
			<form action="<c:url value='member.controller'/>">
<div
				style="border-radius: 10px; background-color: #FDEECE; padding: 20px 2px 50px 2px; margin: 20px auto; width: 700px; text-align: center">
				<h1 style="font-size: 30">會員資料</h1>
				<div class="ccle">
					<div>
						<table style="font-size: 18px;">
					<tr>
						<td>舊密碼</td>
						<td><input type="password" name="memberPassword" value=""
							style="margin-top: 15px"></td>
					<tr>
					
					<tr>
						<td>新密碼</td>
						<td><input type="password" name="newMemberPassword1" value=""
							style="margin-top: 15px"></td>
					<tr>
					
					<tr>
						<td>新密碼</td>
						<td><input type="password" name="newMemberPassword2" value=""
							style="margin-top: 15px"></td>
					<tr>
					<tr><td colspan="2">${statusKey.oldNumEmpty}${statusKey.oldNumError}</td></tr>
					<tr><td colspan="2">${statusKey.newNum1Empty} ${statusKey.newNumSameAsOldNum}</td></tr>
					<tr><td colspan="2">${statusKey.newNum2Different}</td></tr>
					
				</table>
					</div>
					<div style="margin: 10px 0px 0px 20px;">
						<img style="border-radius:10px;height:200px;" src="<c:url value='/pictures/${MemberInfo.memberPic}'/>" />
					</div>
				</div>
				</div>
				<div style="text-align: center;">
					<ul class="productpagecart">
						<li><input class="button_b" type="button" name="cancel" value="CANCEL"
					onclick=location.replace("<c:url value='/member/member.controller?memberNo=${loginToken.memberNo}'/>")></li>
					<li><input class="button_s" id="updatePassword" type="submit" name="updatePassword" value="UPDATE"></li>
					</ul>
				</div>
			</form>
		

	</div>
</body>
</html>