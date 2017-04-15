<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#table1 {
	width: 600px;
	border: 3px solid #46A3FF;
	margin: auto;
	padding: 10px;
}

td {
	border: 1px;
}

tr {
	border: 1px;
}

#div1 {
	height: 110px;
	width: 200px;
	border: 3px solid #FF7744;
	padding: 3px;
	margin: 5px;
}

img {
	margin: 3px;
	margin-right: 5px;
}
</style>
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
	<form action="<c:url value='friend.controller'/>">
		<table id="table1">
			<tbody>
				<tr>
					<%-- 				<a href="<c:url value='MemberInfo.jsp'/>">Search</a> --%>
					<td><a href="<c:url value='friend.controller?x=friend'/>">FriendList</a></td>
					<td><a href="<c:url value='friend.controller?x=requested'/>">Invitation</a></td>
					<td><a href="<c:url value='friend.controller?x=blockade'/>">Blockade</a></td>
					<td><input type="text" name="searchTxt" value=""> <input
						type="submit" name="RelationBtn" value="Search"></td>
				</tr>
			</tbody>
		</table>
	</form>

	<c:if test="${not empty requested || not empty requesting}">
		<a href="<c:url value='friend.controller?x=requested'/>">收到的邀請</a>
		<a href="<c:url value="/friend/friend.controller?x=requesting"/>">送出的邀請</a>
	</c:if>

	<c:if test="${not empty relationList}">
		<c:forEach var="member" items="${relationList}">
			<c:if
				test="${member.fdMemberNo!=member.memberNo || member.friendStatusNo!=2102}">
				<form id="f">
					<table id="div1">
						<tr>
							<td rowspan="5"><img
								style="float: left; width: 100px; height: 100px;"
								src="<c:url value='/pictures/${member.memberPic}'/>" /></td>
							<td><a
								href="<c:url value="/member/member.controller?memberNo=${member.memberNo}"/>">${member.memberName}</a>
								<input type="hidden" name="friendNo" value="${member.friendNo}" />
								<input type="hidden" name="memberFriendNo"
								value="${member.memberNo}" /> <input type="hidden"
								value="${member.friendStatusNo}" /><input type="hidden"
								value="${param.searchTxt}"></td>
						</tr>
						<tr>
							<td>${member.memberNickName}</td>
						</tr>
						<tr>
							<td><c:if test="${not empty search}">
							
									<input type="hidden" name="SearchMark" value="${searchMark}" />
									<c:if test="${member.friendNo==null}">
										<input type="submit" name="RelationBtn" value="Request">
									</c:if>
									<c:if
										test="${member.memberFriendNo==member.memberNo && member.friendStatusNo==2101}">
										<input type="submit" name="RelationBtn" value="Delete">
										<input type="submit" name="RelationBtn" value="Block">
									</c:if>
									<c:if
										test="${member.memberFriendNo==member.memberNo && member.friendStatusNo==2102}">
										<input type="submit" name="RelationBtn" value="UnBlock">
									</c:if>
									<c:if
										test="${member.fdMemberNo==member.memberNo && member.friendStatusNo==2103}">
										<input type="submit" name="RelationBtn" value="BeFriend">
										<input type="submit" name="RelationBtn" value="Refuse">
									</c:if>
									<c:if
										test="${member.memberFriendNo==member.memberNo && member.friendStatusNo==2103}">
										<input type="submit" name="RelationBtn"
											value="CancelRequest">
									</c:if>



								</c:if> <c:if test="${not empty friendlist}">
									<input type="submit" name="RelationBtn" value="Delete">
									<input type="submit" name="RelationBtn" value="Block">
								</c:if> <c:if test="${not empty blockade}">
									<input type="submit" name="RelationBtn" value="UnBlock">
								</c:if> <c:if test="${not empty requested}">
									<input type="submit" name="RelationBtn" value="BeFriend">
									<input type="submit" name="RelationBtn" value="Refuse">
								</c:if> <c:if test="${not empty requesting}">
									<input type="submit" name="RelationBtn" value="CancelRequest">
								</c:if></td>
						</tr>
					</table>
				</form>
			</c:if>
		</c:forEach>
	</c:if>


	<script>
		// 	window.onload=function(){
		// 		document.getElementById("a").onclick=function(){
		// 			document.getElementById("f").submit();
		// 		}		
		// 	}

		// 	window.onload = function() {
		// 		document.getElementById("back").onclick = function() {
		// 			location.href = "memberinfo.jsp"
		// 		}
		// 	}
	</script>


</body>


</html>