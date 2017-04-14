<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OtherMember'sInformation</title>
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
					<td colspan="2"><img
						src="<c:url value='/pictures/${MemberInfo.memberPic}'/>" /></td>
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
					<td>

					<c:if test="${not empty FriendInfo}">
					<input type="hidden" name="memberInfomemberNo" value="${MemberInfo.memberNo}" />
					<input type="hidden" name="FriendInfofdMemberNo" value="${FriendInfo.fdMemberNo}" />
					<input type="hidden" name="FriendInfomemberFriendNo" value="${FriendInfo.memberFriendNo}" />
					<input type="hidden" name="friendInfofriendStatusNo" value="${FriendInfo.friendStatusNo}" />
					<input type="hidden" name="friendInfofriendNo" value="${FriendInfo.friendNo}" />	
					<input type="hidden" name="friendInfofriendNo" value="${otherMemberNo}" />	
					
							<c:if test="${FriendInfo.friendNo==0}">
									<input type="submit" name="RelationBtn" value="Request">
							</c:if>
							<c:if
								test="${FriendInfo.memberFriendNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2101}">
								<input type="submit" name="RelationBtn" value="Delete">
								<input type="submit" name="RelationBtn" value="Block">
							</c:if>
							<c:if
								test="${FriendInfo.memberFriendNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2102}">
								<input type="submit" name="RelationBtn" value="UnBlock">
							</c:if>
							<c:if
								test="${FriendInfo.fdMemberNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2103}">
								<input type="submit" name="RelationBtn" value="BeFriend">
								<input type="submit" name="RelationBtn" value="Refuse">
							</c:if>
							<c:if
								test="${FriendInfo.memberFriendNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2103}">
								<input type="submit" name="RelationBtn" value="CancelRequest">
							</c:if>

						</c:if></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>