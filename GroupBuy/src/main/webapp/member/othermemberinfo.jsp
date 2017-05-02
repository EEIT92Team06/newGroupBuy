<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>
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
								<td>狀態</td>
								<td>${MemberInfo.memberStatus}</td>
							</tr>
							<tr>
								<td>姓名</td>
								<td>${MemberInfo.memberName}</td>
							</tr>
							<tr>
								<td>暱稱</td>
								<td>${MemberInfo.memberNickName}</td>
							</tr>
							<tr>
								<td>生日</td>
								<td>${MemberInfo.formatMemberBirthday}</td>
							</tr>
							<tr>
								<td>電話</td>
								<td>${MemberInfo.memberPhone}</td>
							</tr>
							<tr>
								<td>地址</td>
								<td>${MemberInfo.memberAccount}</td>
							</tr>
							<c:if test="${empty MemberInfo.groupAttendanceTotalQt}">
								<tr>
									<td>出席率</td>
									<td>尚未參與團購</td>
								</tr>
							</c:if>

							<c:if test="${not empty MemberInfo.groupAttendanceTotalQt}">
								<tr>
									<td>出席率</td>
									<td>${MemberInfo.groupAttendanceTotalSuccess}/
										${MemberInfo.groupAttendanceTotalQt}</td>
								</tr>
							</c:if>

							<c:if test="${not empty MemberInfo.groupCredit}">
								<tr>
									<td>開團評分</td>
									<td>${MemberInfo.groupCredit}</td>
								</tr>
							</c:if>
						</table>
					</div>
					<div style="margin: 10px 0px 0px 20px;">
						<img style="border-radius: 10px; height: 200px;"
							src="<c:url value='/pictures/${MemberInfo.memberPic}'/>"
							style="height: 250px" />
					</div>
				</div>
			</div>
			<div style="padding-top: 50px; text-align: center;">
				<ul class="productpagecart">

					<c:if test="${not empty FriendInfo}">
						<input type="hidden" name="memberInfomemberNo"
							value="${MemberInfo.memberNo}" />
						<input type="hidden" name="FriendInfofdMemberNo"
							value="${FriendInfo.fdMemberNo}" />
						<input type="hidden" name="FriendInfomemberFriendNo"
							value="${FriendInfo.memberFriendNo}" />
						<input type="hidden" name="friendInfofriendStatusNo"
							value="${FriendInfo.friendStatusNo}" />
						<input type="hidden" name="friendInfofriendNo"
							value="${FriendInfo.friendNo}" />
						<input type="hidden" name="friendInfofriendNo"
							value="${otherMemberNo}" />


						<c:if test="${FriendInfo.friendNo==null}">
							<li><input type="submit" name="RelationBtn" value="Request"
								class="button_s"></li>
							<li><input type="submit" name="RelationBtn" value="Block"
								class="button_b"></li>
						</c:if>
						<c:if
							test="${FriendInfo.memberFriendNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2101}">
							<li><input type="submit" name="RelationBtn" value="Delete"
								class="button_b"></li>
							<li><input type="submit" name="RelationBtn" value="Block"
								class="button_b"></li>
						</c:if>
						<c:if
							test="${FriendInfo.memberFriendNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2102}">
							<li><input type="submit" name="RelationBtn" value="UnBlock"
								class="button_s"></li>
						</c:if>
						<c:if
							test="${FriendInfo.fdMemberNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2103}">
							<li><input type="submit" name="RelationBtn" value="BeFriend"
								class="button_s"></li>
							<li><input type="submit" name="RelationBtn" value="Refuse"
								class="button_b"></li>
						</c:if>
						<c:if
							test="${FriendInfo.memberFriendNo==FriendInfo.memberNo && FriendInfo.friendStatusNo==2103}">
							<li><input type="submit" name="RelationBtn"
								value="CancelRequest" class="button_b"></li>
						</c:if>

					</c:if>
				</ul>
			</div>

		</form>
	</div>

</body>
</html>