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
.img1{
/*  style=" */
 border-radius: 10px; height: 180px;"
}
</style>
	<div>

		<form action="<c:url value='member.controller'/>"
			enctype="multipart/form-data" method="POST">
			<div
				style="border-radius: 10px; background-color: #FDEECE; padding: 20px 2px 12px 2px; margin: 20px auto; width: 700px; text-align: center">
				<h1 style="font-size: 30">會員資料</h1>
				<div class="ccle">
					<div>
						<table style="font-size: 18px;">
							<tr>
								<td>狀態</td>
								<td>${MemberInfo.memberStatus}</td>
								<td rowspan="15"></td>
							</tr>
							<tr>
								<td>帳號</td>
								<td>${MemberInfo.memberAccount}</td>
							</tr>
							<tr>
								<td>姓名</td>
								<td>${MemberInfo.memberName}</td>
							</tr>
							<tr>
								<td>暱稱</td>
								<td><input style="margin-bottom: 0px" type="text"
									name="memberNickname" value="${MemberInfo.memberNickName}"></td>
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
								<td><input style="margin-bottom: 0px" type="text"
									name="memberAddress" value="${MemberInfo.memberAddress}"></td>
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
						<div id="dropZone">
							<img class="img1" src="<c:url value='/pictures/${MemberInfo.memberPic}'/>" />
						</div>

						<input id="file1" class="button_s" style="margin-top: 5px" type="file"
							name="picUpload" value="upload" accept="image/*"
							onchange="fileViewer()"> <input class="button_b"
							style="float: right" type="button" value="cancel"
							onclick="cancelChangePic()">
					</div>
				</div>

			</div>
			<div style="text-align: center;">
				<ul class="productpagecart">
					<li><input id="back" type="button" name="cancel"
						value="CANCEL" class="button_b"></li>
					<li><input type="submit" name="updateInfo" value="UPDATE"
						class="button_s"></li>
				</ul>
			</div>
		</form>

	</div>
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("back").onclick = function() {
				location.href = "memberinfo.jsp"
			}
		}

		function cancelChangePic() {
			var a = '<img src="../pictures/${MemberInfo.memberPic}" name="pic" />'
			document.getElementById("dropZone").innerHTML = a;
		}

		function fileViewer() { //在DIV中顯示上傳的圖片
			document.getElementById("dropZone").innerHTML = ''; //重新上傳時,刪掉原先顯示的圖片
			var count = 0; //id的初始值
			var files = document.getElementById("file1").files;
			var filesLen = files.length;
			for (var i = 0; i < filesLen; i++) {

				var reader = new FileReader();
				reader.readAsDataURL(files[i]);
				reader.onload = function(e) {
					var fileContent = e.target.result;

					var imgObj = document.createElement("img");
					imgObj.setAttribute("src", fileContent);
					imgObj.setAttribute("name", "pic");
					imgObj.setAttribute("class", "img1");
					
					document.getElementById("dropZone").appendChild(imgObj);
					count++;
				}
			}
		}
		function choseCover(obj) {
			id = obj.id;
			var imgs = document.getElementsByTagName("img"); //取得img陣列
			for (var i = 0; i < imgs.length; i++) { //移除原先選取的圖片的外框及value
				imgs.item(i).setAttribute("style", "");
			}

			var xhr = new XMLHttpRequest();
			var queryString = "picNo=" + id;
			var url = "wishform.controller?" + queryString;
			xhr.open("GET", url, true);
			xhr.send();
			xhr.onreadystatechange = function() {
				// 向伺服器提出的請求已經收到回應 
				if (xhr.readyState === 4) {
					// 伺服器回應成功
					if (xhr.status === 200) {

					}
				}
				//     			alert("xhr.status: "+xhr.status);
			}
			//     		alert("xhr.readyState : " + xhr.readyState);
		}
	</script>
</body>
</html>