<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>
<style>
td {
	border: 1px;
}

tr {
	border: 1px;
}

.div1 {
	height: 150px;
	width: 305px;
	border-radius: 10px;
	border: 3px solid #FF7744;
	padding: 3px;
	margin: 5px;
}

img {
	margin: 3px;
	margin-right: 5px;
}

.button_s {
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: #F25C27;
	color: white;
	/* 	line-height: 40px; */
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
	font-weight: normal;
}

.button_b {
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: gray;
	color: white;
	/* 	line-height: 40px; */
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
}

.title1 {
	border-radius: 5px;
	background-color: #FDEECE;
	text-align: center;
	font-size: 20px;
	width: 700px;
	margin: auto;
	padding: 10px;
}

form {
	margin: 0px;
	display: inline;
}

</style>
</head>
<body>
	<jsp:include page="/headline.jsp"></jsp:include>
	<style>
a {
	color: #F25C27;
	style ="text-decoration: none"
}

</style>
	<form action="<c:url value='friend.controller'/>">
		<table class="title1" style="margin-top: 20px; margin-bottom: 20px;">
			<tbody>
				<tr>
					<c:if test="${not empty friendlist}">
						<td><a
							style="text-decoration: none; font-size: 22px; font-weight: bold;"
							href="<c:url value='friend.controller?x=friend'/>">FriendList</a></td>
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=requested'/>">Invitation</a></td>
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=blockade'/>">Blockade</a></td>
					</c:if>

					<c:if test="${not empty requested || not empty requesting}">
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=friend'/>">FriendList</a>
						</td>
						<td><a
							style="text-decoration: none; font-size: 22px; font-weight: bold;"
							href="<c:url value='friend.controller?x=requested'/>">Invitation</a></td>
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=blockade'/>">Blockade</a></td>
					</c:if>
					<c:if test="${not empty blockade}">
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=friend'/>">FriendList</a>
						</td>
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=requested'/>">Invitation</a></td>
						<td><a
							style="text-decoration: none; font-size: 22px; font-weight: bold;"
							href="<c:url value='friend.controller?x=blockade'/>">Blockade</a></td>
					</c:if>

					<c:if test="${not empty search}">
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=friend'/>">FriendList</a>
						</td>
						<td><a style="text-decoration: none"
							href="<c:url value='friend.controller?x=requested'/>">Invitation</a></td>
						<td><a style="text-decoration: none;"
							href="<c:url value='friend.controller?x=blockade'/>">Blockade</a></td>

					</c:if>
					<td><input type="text" name="searchTxt" value=""
						placeholder="尋找朋友..." style="height: 30px; margin: 0px;">
						<input type="submit" name="RelationBtn" value="Search"
						class="button_s" style="font-size: 15px; line-height: 30px;"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<c:if test="${not empty requested || not empty requesting}">
		<c:if test="${not empty requested}">
			<div style="margin-left: 120px;">
				<a id="requested" style="font-size: 18px; font-weight: bold;">收到的邀請</a> <a
					id="requesting">送出的邀請</a>
			</div>
		</c:if>
		<c:if test="${not empty requesting}">
			<div style="margin-left: 120px;">
				<a id="requested">收到的邀請</a>
				 <a id="requesting" style="font-size: 18px; font-weight: bold;">送出的邀請</a>
			</div>
		</c:if>

	</c:if>
	<div>
		<ul id="ulId" class="thumbnails"
			style="margin: 0px 0px 0px 130px; text-align: center;">
			<c:if test="${not empty relationList}">
				<c:forEach var="member" items="${relationList}" varStatus="x">

					<c:if
						test="${member.fdMemberNo!=member.memberNo || member.friendStatusNo!=2102}">
						<form class="div1" style="float: left; margin: 20px;">
							<input type="hidden" value="${x.index}" />
							<table>
								<input id="memberFriendNo${x.index}" type="hidden"
									name="memberFriendNo" value="${member.memberNo}" />
								<tr>
									<td rowspan="5"><img
										style="width: 130px; height: 130px; border-radius: 10px;"
										src="<c:url value='/pictures/${member.memberPic}'/>" /></td>
									<td><a style="text-decoration: none; font-size: 20px;"
										href="<c:url value="/member/member.controller?memberNo=${member.memberNo}"/>">${member.memberNickName}</a>


										<input type="hidden" value="${member.friendStatusNo}" /> <input
										type="hidden" value="${param.searchTxt}"></td>
								</tr>
								<tr>
									<td>${member.memberName}</td>
								</tr>


								<tr>
									<input id="friendNo${x.index}" type="hidden" name="friendNo"
										value="${member.friendNo}" />
									<td><c:if test="${not empty search}">

											<input type="hidden" name="SearchMark" value="${searchMark}" />
											<c:if test="${member.friendNo==null}">
												<input type="button" name="RequestBtn" value="Request"
													class="button_s">
												<input id="block${x.index}" type="submit" name="RelationBtn" value="Block"
													class="button_b">
											</c:if>
											<c:if
												test="${member.memberFriendNo==member.memberNo && member.friendStatusNo==2101}">
												<input type="button" name="DeleteBtn" value="Delete"
													class="button_b">
												<input type="submit" name="RelationBtn" value="Block"
													class="button_b">
											</c:if>
											<c:if
												test="${member.memberFriendNo==member.memberNo && member.friendStatusNo==2102}">
												<input type="submit" name="RelationBtn" value="UnBlock"
													class="button_s">
											</c:if>
											<c:if
												test="${member.fdMemberNo==member.memberNo && member.friendStatusNo==2103}">
												<input type="submit" name="RelationBtn" value="BeFriend"
													class="button_s">
												<input type="submit" name="RelationBtn" value="Refuse"
													class="button_b">
											</c:if>
											<c:if
												test="${member.memberFriendNo==member.memberNo && member.friendStatusNo==2103}">
												<input type="submit" name="RelationBtn"
													value="CancelRequest" class="button_b">
											</c:if>



										</c:if> <c:if test="${not empty friendlist}">
											<input type="button" name="DeleteBtn" value="Delete"
												class="button_b">
											<input type="submit" name="RelationBtn" value="Block"
												class="button_b">
										</c:if> <c:if test="${not empty blockade}">
											<input type="submit" name="RelationBtn" value="UnBlock"
												class="button_s">
										</c:if> <c:if test="${not empty requested}">
											<input type="submit" name="RelationBtn" value="BeFriend"
												class="button_s">
											<input type="submit" name="RelationBtn" value="Refuse"
												class="button_b">
										</c:if> <c:if test="${not empty requesting}">
											<input type="submit" name="RelationBtn" value="CancelRequest"
												class="button_b">
										</c:if>
										
<%-- 										<c:if test="${empty requesting}"> --%>
<%-- 											<input id="requesting${x.index}" style="display: none;" type="submit" name="RelationBtn" value="CancelRequest" --%>
<!-- 												class="button_b"> -->
<%-- 										</c:if> --%>
										
										</td>
								</tr>
							</table>
						</form>
					</c:if>
				</c:forEach>
			</c:if>
		</ul>
	</div>

	<script src="<c:url value='/js/jquery-3.1.1.min.js'/>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>
	<script type="text/javascript">
		$(function() {
			$("a[id='requested']").click(function(){
				var ulId=$("#ulId");
				ulId.empty();
				$('#requested').css({'font-size':'18px','font-weight':'bold'});
				$('#requesting').removeAttr('style');
				$.get("${pageContext.request.contextPath}/statusajax",{
					"RelationBtn":"requested"
				},function(data){
					var dataJson=JSON.parse(data);
					$.each(dataJson,function(index,value){
						var theform=$("<form></form>").addClass("div1").css({'float':'left','margin':'20px'});
						var thetable=$("<table></table>");
						var thetr=$("<tr></tr>");
						var imgtd=$("<td></td>").attr('rowspan','5');
						var img1=$("<img />").css({'width':'130px','height':'130px','border-radius':'10px'}).attr('src','${pageContext.request.contextPath}/pictures/'+value.memberPic);		
						imgtd.append(img1);
						var td2=$("<td></td>");
						var namelink=$("<a></a>").css({'text-decoration':'none','font-size':'20px'}).attr('href','${pageContext.request.contextPath}/member/member.controller?memberNo='+value.memberNo).append(value.memberNickName);
						td2.append(namelink);
						thetr.append([imgtd,td2]);
						var tr2=$("<tr></tr>");
						var td3=$("<td></td>").append(value.memberName);
						tr2.append(td3);
						var tr3=$("<tr></tr>");
						var td4=$("<td></td>");
						var befriend=$("<input />").attr({'type':'submit','value':'BeFriend'}).addClass("button_s");
						var refuse=$("<input />").attr({'type':'submit','value':'Refuse'}).addClass("button_b");
						td4.append([befriend,refuse]);
						tr3.append(td4);
						thetable.append([thetr,tr2,tr3]);
						theform.append(thetable);
						ulId.append(theform);
					});
				});
			});
			
			$("a[id='requesting']").click(function(){
				var ulId=$("#ulId");
				ulId.empty();
				$('#requesting').css({'font-size':'18px','font-weight':'bold'});
				$('#requested').removeAttr("style");
				$.get("${pageContext.request.contextPath}/statusajax",{
					"RelationBtn":"requesting"
				},function(data){
					var dataJson=JSON.parse(data);
					$.each(dataJson,function(index,value){
						var theform=$("<form></form>").addClass("div1").css({'float':'left','margin':'20px'});
						var thetable=$("<table></table>");
						var thetr=$("<tr></tr>");
						var imgtd=$("<td></td>").attr('rowspan','5');
						var img1=$("<img />").css({'width':'130px','height':'130px','border-radius':'10px'}).attr('src','${pageContext.request.contextPath}/pictures/'+value.memberPic);		
						imgtd.append(img1);
						var td2=$("<td></td>");
						var namelink=$("<a></a>").css({'text-decoration':'none','font-size':'20px'}).attr('href','${pageContext.request.contextPath}/member/member.controller?memberNo='+value.memberNo).append(value.memberNickName);
						td2.append(namelink);
						thetr.append([imgtd,td2]);
						var tr2=$("<tr></tr>");
						var td3=$("<td></td>").append(value.memberName);
						tr2.append(td3);
						var tr3=$("<tr></tr>");
						var td4=$("<td></td>");
						var cancelRequest=$("<input />").attr({'type':'submit','value':'CancelRequest'}).addClass("button_b");
						td4.append(cancelRequest);
						tr3.append(td4);
						thetable.append([thetr,tr2,tr3]);
						theform.append(thetable);
						ulId.append(theform);
					});
				});
			});
			
			$("input[name='DeleteBtn']").click(function() {
				var deleteBtn = $(this);
				var nowIndex = deleteBtn.parents("form").find("input").val();
				var friendNo = $("#friendNo" + nowIndex).val();
				var memberFriendNo = $("#memberFriendNo" + nowIndex).val();
				// 		var friendNo=deleteBtn.parents("tr").find("input").val();
				// 		var memberFriendNo=deleteBtn.parents("table").find("input").val();
				var deleteConfirm = layer.confirm('確定要刪除好友嗎?', {
					title:'提示',
					btn : [ '確定', '取消' ]
				//按钮
				}, function() {
					$.get("${pageContext.request.contextPath}/statusajax", {
						"RelationBtn" : "Delete",
						"friendNo" : friendNo,
						"memberFriendNo" : memberFriendNo
					}, function() {
						deleteBtn.parents("form").remove();
						layer.close(deleteConfirm);
					});
				});
			});
			$("input[name='RequestBtn']").click(function() {
				var requestBtn = $(this);
				
				var nowIndex = requestBtn.parents("form").find("input").val();
				var blockBtn = $("#block"+nowIndex);
				var memberFriendNo = $("#memberFriendNo" + nowIndex).val();
				$.get("${pageContext.request.contextPath}/statusajax", {
					"RelationBtn" : "Request",
					"memberFriendNo" : memberFriendNo
				}, function() {
					requestBtn.replaceWith("<input type='submit' name='RelationBtn' value='CancelRequest' class='button_b'>");
					blockBtn.remove();
					// 					requestBtn.remove();
// 					$("#requesting"+nowIndex).show();
				});
			});
			
			
			
			
			
			
			
		})
		
	</script>




</body>
</html>