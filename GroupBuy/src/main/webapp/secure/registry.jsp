<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.thumb {
	height: 250px;
}
</style>
<link rel="stylesheet" href="../css/jquery-ui.min.css" />
<link rel="stylesheet" href="../css/jquery-ui.structure.min.css" />
<link rel="stylesheet" href="../css/jquery-ui.theme.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript">

	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd'
		});//minDate表示最小可以選的日期為哪一天, maxDate則表最大可選天數
	})
	function fileViewer() {
		document.getElementById("memberPic").innerHTML = "";
		var files = document.getElementById("newFile").files
		var filelength = files.length;
		for (var i = 0; i < filelength; i++) {
			var reader = new FileReader();
			reader.readAsDataURL(files[i])
			reader.onload = function(e) {
				var filecontent = e.target.result;
				imageobj = document.createElement("img");
				imageobj.setAttribute("src", filecontent);
				imageobj.setAttribute("class", "thumb");
				document.getElementById("memberPic").appendChild(imageobj);
			}
		}
	}
</script>
<body>
	<form action="<c:url value="/registryServlet.do"/>" enctype="multipart/form-data" method="post">
		<div>
			<div style="float: left; border: 1px solid;">
				<div style="float: left; margin: 20px;">
					<div>
						<label>*電子郵件</label>
					</div>
					<div>
						<input type="text" name="memberAccount" value="${param.memberAccount}${googleData.email}" /><label>${errorMessages.memberAccount}</label>
					</div>
					<div>
						<label>*密碼</label>
					</div>
					<div>
						<input type="password" name="memberPassword"  /><label>${errorMessages.memberPassword}</label>
					</div>
					<div>
						<label>*確認密碼</label>
					</div>
					<div>
						<input type="password" name="checkPassword" /><label>${errorMessages.checkPassword}</label>
					</div>
					<div>
						<label>*姓名</label>
					</div>
					<div>
						<input type="text" name="memberName" value=""/><label>${errorMessages.memberName}</label>
					</div>
					<div>
						<label>暱稱</label>
					</div>
					<div>
						<input type="text" name="memberNickName" value="" /><label>${errorMessages.memberNickName}</label>
					</div>
					<div>
						<label>*地址</label>
					</div>
					<div>
						<select name="cityName" >
							<option value="台北市" >台北市</option>
							<option value="基隆市">基隆市</option>
							<option value="新北市" >新北市</option>
							<option value="宜蘭縣" >宜蘭縣</option>
							<option value="新竹市">新竹市</option>
							<option value="新竹縣">新竹縣</option>
							<option value="桃園縣">桃園縣</option>
							<option value="苗栗縣">苗栗縣</option>
							<option value="台中市">台中市</option>
							<option value="彰化縣">彰化縣</option>
							<option value="南投縣">南投縣</option>
							<option value="嘉義市">嘉義市</option>
							<option value="嘉義縣">嘉義縣</option>
							<option value="雲林縣">雲林縣</option>
							<option value="台南市">台南市</option>
							<option value="高雄市">高雄市</option>
							<option value="澎湖縣">澎湖縣</option>
							<option value="屏東縣">屏東縣</option>
							<option value="台東縣">台東縣</option>
							<option value="花蓮縣">花蓮縣</option>
							<option value="金門縣">金門縣</option>
							<option value="連江縣">連江縣</option>
						</select>	
					</div>
						<input type="text" name="address" value="" size="35"/><label>${errorMessages.memberAddress}</label>
					<div>
						<label>*生日</label>
					</div>
					<div>
						<input type="text" id="datepicker" name="memberBirth" value="" /><label>${errorMessages.memberBirth}</label>
					</div>
					<div>
						<label>*行動電話</label>
					</div>
					<div>
						<input type="text" name="memberPhone" value="" /><label>${errorMessages.memberPhone}</label>
					</div>
				</div>
				<div style="float: left; margin: 20px; border: 1px solid;">
					<div>
						<label>挑選一張大頭貼</label>
					</div>
					<div>
						<input type="file" name="memberPic" id="newFile" accept="image/*" value=""
							onchange="fileViewer()" /><label>${errorMessages.memberPic}</label>
					</div>
					<div id="memberPic"></div>
				</div>
			</div>
			<div style="clear: left;">
				<label><input type="submit" value="免費註冊"><input
					type="button" value="取消"></label>
			</div>
		</div>
	</form>
</body>
</html>