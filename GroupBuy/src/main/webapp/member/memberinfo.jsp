<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


</style>
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
	<form action="<c:url value='member.controller'/>">
	<table id="table1">
		<thead>
			<tr>
				<th colspan="2"><h1>Member Information</h1></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td colspan="2"><img src="<c:url value='/pictures/${MemberInfo.memberPic}'/>" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			<tr>
			<tr>
				<td>Status</td>
				<td>${MemberInfo.memberStatus}</td>
			</tr>
			<tr>
				<td>Account</td>
				<td>${MemberInfo.memberAccount}</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>${MemberInfo.memberName}</td>
			</tr>
			<tr>
				<td>NickName</td>
				<td>${MemberInfo.memberNickName}</td>
			</tr>
			<tr>
				<td>Birth</td>
				<td>${MemberInfo.memberBirth}</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td>${MemberInfo.memberPhone}</td>
			</tr>
			<tr>
				<td>Address</td>
				<td>${MemberInfo.memberAddress}</td>
			</tr>
			<tr>
				<td>Attendance</td>
				<td>${MemberInfo.groupAttendanceTotalSuccess}/${MemberInfo.groupAttendanceTotalQt}</td>
			</tr>
			<tr>
				<td>GroupCredit</td>
				<td>${MemberInfo.groupCredit}</td>
			<tr>
			<tr>
				<td></td>
				<td></td>
			<tr>
			<tr>
				<td><a href="<c:url value='member.controller?x=memberUpdate'/>">Edit</a></td>
				<td><a href="<c:url value='memberpasswordchange.jsp'/>">Change
						Password</a></td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>