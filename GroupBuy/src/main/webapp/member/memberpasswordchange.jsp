<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MemberInformation</title>
<style>
#table1 {
	width: 400px;
	border: 3px solid #46A3FF;
	margin: auto;
	padding-left: 70px;
}

td {
	border: 1px;
	padding-top: 3px;
}

img {
	width: 250px;
}
</style>

<script src="<c:url value='/js/jquery-3.2.0.min.js'/>"></script>
<script src="<c:url value='/js/layer/layer.js'/>"></script>
<script>
// $(function(){
// 	$("#updatePassword").click(function(){
// 		$("#updateForm").submit();
// 		alert('hi');	
// 	})
// })

</script>

</head>
<body>
<jsp:include page="/Web_02/headline.jsp"></jsp:include>
	<form id="updateForm" action="<c:url value='member.controller'/>">
		<table id="table1">
			<thead>
				<tr>
					<th colspan="2"><h1>Password Changing</h1></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2"><img src="<c:url value='/pictures/${MemberInfo.memberPic}'/>" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>舊密碼</td>
					<td><input type="text" name="memberPassword" value=""></td>
					<td>${statusKey.oldNumEmpty}${statusKey.oldNumError}</td>
				<tr>
				<tr>
					<td>新密碼</td>
					<td><input type="text" name="newMemberPassword1" value=""></td>
					<td>${statusKey.newNum1Empty}${statusKey.newNumSameAsOldNum}</td>
				<tr>
				<tr>
					<td>新密碼</td>
					<td><input type="text" name="newMemberPassword2" value=""></td>
					<td>${statusKey.newNum2Different}</td>
				<tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
				
					<td><input type="button" name="cancel" value="CANCEL"
					onclick=location.replace("<c:url value='/member/member.controller?memberNo=${loginToken.memberNo}'/>")></td>
<%-- 					onclick=location.href="<c:url value='/member/member.controller?memberNo=${myMemberNo}'/>"></td> --%>
<!-- 						onclick="window.history.back()"></td> -->
					<td><input id="updatePassword" type="submit" name="updatePassword" value="UPDATE"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>