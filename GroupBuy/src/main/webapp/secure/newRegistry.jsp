<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>
<style type="text/css">
#success_message {
	display: none;
}

.thumb {
	width:450px;
	margin: 5px;
}
</style>
<link rel="stylesheet" href="../css/jquery-ui.min.css" />
<link rel="stylesheet" href="../css/jquery-ui.structure.min.css" />
<link rel="stylesheet" href="../css/jquery-ui.theme.min.css" />
<link rel='stylesheet prefetch'
	href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<link rel='stylesheet prefetch'
	href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
<link rel='stylesheet prefetch'
	href='//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
<script src="https://use.fontawesome.com/72bc13eff4.js"></script>
	<script>
// 	$(function() {
// 		$("#datepicker").datepicker({
// 			dateFormat : 'yy-mm-dd'
// 		});//minDate表示最小可以選的日期為哪一天, maxDate則表最大可選天數
// 	})
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
	
	
	function enter(){
		document.getElementById("memberPassword").setAttribute("value", "sa123456");
		document.getElementById("checkPassword").setAttribute("value", "sa123456");
		document.getElementById("memberName").setAttribute("value", "劉得滑");
		document.getElementById("memberNickName").setAttribute("value", "Andy");
		var x=document.getElementById("cityName").children[2];
		x.setAttribute("selected", "selected");
		document.getElementById("address").setAttribute("value", "土城區中華路105號69樓-2");
		document.getElementById("datepicker").setAttribute("value", "1992-09-09");
		document.getElementById("memberPhone").setAttribute("value", "0933445566");
	}
	</script>
</head>
<body>
	<jsp:include page="/headline.jsp"></jsp:include>
	
	<div class="container">

		<form class="well form-horizontal"
			action="<c:url value="/registryServlet.do"/>" enctype="multipart/form-data" method="post" >
			<center>
			<fieldset>

				<!-- Form Name -->
				<center>
					<legend>
						<font style="font-weight: bold;" size="5em">成為GroupBuy會員</font>
					</legend>
				</center>

				<!-- 電子郵件 -->
				<div class="form-group"  style="margin-left: 230px;">
					<label class="col-md-4 control-label">GroupBuy帳號</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-envelope"
								aria-hidden="true"></i></span> <input name="memberAccount" id="memberAccount"
								placeholder="電子郵件" class="form-control" type="text"  style="height: 30px;"
								value="${param.memberAccount}${googleData.email}">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberAccount}</small>
					</div>
				</div>


				<!--密碼-->
				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">密碼</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key"
								aria-hidden="true"></i></span> <input name="memberPassword" style="height: 30px;" id="memberPassword"
								placeholder="輸入8到16碼含中英文及特殊符號" class="form-control"
								type="password" />
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberPassword}</small>
					</div>
				</div>


				<!--確認密碼-->
				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">*確認密碼</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-key"
								aria-hidden="true"></i></span> <input name="checkPassword" style="height: 30px;" id="checkPassword"
								placeholder="輸入一致的密碼" class="form-control" type="password"
								value="">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.checkPassword}</small>
					</div>
				</div>

				<!-- *姓名-->

				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">*姓名</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-user-o"
								aria-hidden="true"></i> </span> <input name="memberName" style="height: 30px;" id="memberName"
								placeholder="中文全名" class="form-control" type="text"
								value="${param.memberName}">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberName}</small>
					</div>
				</div>

				<!-- 暱稱-->

				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">暱稱</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-child"
								aria-hidden="true"></i></span> <input name="memberNickName" style="height: 30px;" id="memberNickName"
								placeholder="綽號" class="form-control" type="text"
								value="${param.memberNickName}">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberNickName}</small>
					</div>
				</div>

				<!-- *地址 -->
				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">*地址</label>
					<div class="col-md-4 selectContainer">
						<div class="input-group"  style="width: 150px;margin-right: 150px">
							<span class="input-group-addon"><i class="fa fa-map-marker" aria-hidden="true"></i>
							</span> 
							<select name="cityName" class="form-control selectpicker" id="cityName">
								<option value="台北市">台北市</option>
								<option value="基隆市">基隆市</option>
								<option value="新北市">新北市</option>
								<option value="宜蘭縣">宜蘭縣</option>
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
						<div class="input-group" >
							<input style="width: 338px;height: 30px;"name="address" id="address" placeholder="完整住址" class="form-control" type="text" value="${param.address}">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberAddress}</small>
					</div>
				</div>


				<!-- *生日-->

				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">*生日</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-birthday-cake" aria-hidden="true"></i>
                            </span> <input name="memberBirth"  id="datepicker"
								placeholder="格式為西元年月日yyyy-MM-dd" class="form-control" type="text" style="height: 30px;"
								value="${param.memberBirth}">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberBirth}</small>
					</div>
				</div>


               <!-- *行動電話-->
				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">*行動電話</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i class="fa fa-phone" aria-hidden="true"></i>
                            </span><input name="memberPhone" id="memberPhone"
								placeholder="例:09xx-xxx-ooo" class="form-control" type="text" style="height: 30px;"
								value="${param.memberPhone}">
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberPhone}</small>
					</div>
				</div>

				<!-- 挑選一張大頭貼-->

				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label">挑選一張大頭貼</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-file-image-o" aria-hidden="true"></i></span> <input type="file" name="memberPic" id="newFile" accept="image/*" value=""
							onchange="fileViewer()"  placeholder="檔案上傳" >
						</div>
						<small data-bv-validator="notEmpty" data-bv-validator-for="title"
							class="help-block" style="color: red;">${errorMessages.memberPic}</small>
						<tr>
							<td>
							<td>
							<td><div id="memberPic">
				
							</div></td>
						</tr>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group" style="margin-left: 230px;">
					<label class="col-md-4 control-label"></label>
					<center>
						<div class="col-md-4">
							<button type="submit" name="send" class="btn btn-warning"
								value="免費註冊">
								免費註冊<span class="glyphicon glyphicon-send"></span>
							</button>
						</div>

					</center>
							<button type="button"  onclick="enter()" value="">
								一鍵輸入
							</button>
				</div>
				
			</fieldset>
			</center>
			
		</form>
		
	</div>
	</div>
	<!-- /.container -->
	<!-- <script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script> -->
	<script
		src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script
		src='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
	<script
		src='//cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
	<script>
		$(document).ready(
				function() {
					$('#contact_form').bootstrapValidator({
						// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
					}).on(
							'success.form.bv',
							function(e) {
								$('#success_message').slideDown({
									opacity : "show"
								}, "slow") // Do something ...
								$('#contact_form').data('bootstrapValidator')
										.resetForm();

								// Prevent form submission
								e.preventDefault();

								// Get the form instance
								var $form = $(e.target);

								// Get the BootstrapValidator instance
								var bv = $form.data('bootstrapValidator');

								// Use Ajax to submit form data
								$.post($form.attr('action'), $form.serialize(),
										function(result) {
											console.log(result);
										}, 'json');
							});
				});

		//# sourceURL=pen.js
	</script>

</body>
</html>