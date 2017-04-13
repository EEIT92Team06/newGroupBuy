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

img {
	width: 250px;
}
</style>
</head>
<body>
<jsp:include page="/Web_02/headline.jsp"></jsp:include>
	<form action="<c:url value='member.controller'/>"
		enctype="multipart/form-data" method="POST">
		<table id="table1">
			<thead>
				<tr>
					<th colspan="2"><h1>Information Updating</h1></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2">
					<div id="dropZone">
							<img src="<c:url value='/pictures/${MemberInfo.memberPic}'/>" name="pic" />
						</div></td>
				</tr>
				<tr>
				<td><input type="button" value="cancel" onclick="cancelChangePic()"></td>
					<td>
						<input id="file1" type="file" name="picUpload"
						value="upload" accept="image/*" onchange="fileViewer()">
					</td>
				</tr>
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
					<td><input type="text" name="memberNickname"
						value="${MemberInfo.memberNickName}"></td>
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
					<td><input type="text" name="memberAddress"
						value="${MemberInfo.memberAddress}"></td>
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
					<td><input id="back" type="button" name="cancel"
						value="CANCEL"></td>
					<td><input type="submit" name="updateInfo" value="UPDATE"></td>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("back").onclick = function() {
				location.href = "memberinfo.jsp"
			}
		}

		function cancelChangePic(){
			var a='<img src="../pictures/${MemberInfo.memberPic}" name="pic" />'
			document.getElementById("dropZone").innerHTML=a;
		
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
					imgObj.setAttribute("id", count);
					imgObj.setAttribute("src", fileContent);
					imgObj.setAttribute("class", "thumb");
					imgObj.setAttribute("name", "pic");
					

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