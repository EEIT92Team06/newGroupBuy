<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wish Form</title>
<style type="text/css">
#success_message {
	display: none;
}

.thumb {
	width: 450px;
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
<script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

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

	$(function() {

		var txtId = 1;
		var x = 1;
		//add input block in showBlock
		$("#btn")
				.click(
						function() {
							if (txtId < 5) {
								$("#showBlock")
										.append(
												"<div id="+txtId+"><label class='col-md-4 control-label'>*品項"
														+ txtId
														+ "</label><div  class='col-md-4 inputGroupContainer'><div class='input-group'> <span class='input-group-addon'><i class='fa fa-child' aria-hidden='true'></i></span> <input name='productName'style='height: 30px;' placeholder='品名' class='form-control'type='text' value='${param.productName}'></div><small data-bv-validator='notEmpty' data-bv-validator-for='title'class='help-block' style='color: red;'></small></div><div class='col-md-4 inputGroupContainer' style='display: inline;'><div class='input-group'><span class='input-group-addon'><i class='fa fa-child'aria-hidden='true'></i></span> <input name='productPrice'style='height: 30px;' placeholder='價格' class='form-control'type='text' value='${param.productPrice}'></div><small data-bv-validator='notEmpty' data-bv-validator-for='title'class='help-block' style='color: red;'></small></div></div>");
								txtId++;
							}
						});

		//remove div
		$("#remove").click(function() {
			if (txtId > 1) {
				txtId--;
			}
			$("#" + txtId).remove();
		});

		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			minDate : '-0'
		});//minDate表示最小可以選的日期為哪一天, maxDate則表最大可選天數
	});

	var count = 0;
	function fileViewer() {
		document.getElementById("newFile").innerHTML = "";
		var files = document.getElementById("file1").files
		var filelength = files.length;
		for (var i = 0; i < filelength; i++) {
			var reader = new FileReader();
			reader.readAsDataURL(files[i])
			reader.onload = function(e) {
				var filecontent = e.target.result;
				imageobj = document.createElement("img");
				imageobj.setAttribute("src", filecontent);
				imageobj.setAttribute("class", "thumb");
				document.getElementById("newFile").appendChild(imageobj);
			}
		}
	}
	function fileViewer1() {
		document.getElementById("newFile1").innerHTML = "";
		var files = document.getElementById("file2").files
		var filelength = files.length;
		for (var i = 0; i < filelength; i++) {
			var reader = new FileReader();
			reader.readAsDataURL(files[i])
			reader.onload = function(e) {
				var filecontent = e.target.result;
				imageobj = document.createElement("img");
				imageobj.setAttribute("src", filecontent);
				imageobj.setAttribute("class", "thumb");
				document.getElementById("newFile1").appendChild(imageobj);
			}
		}
	}
</script>
</head>
<body>
	<jsp:include page="/headline.jsp"></jsp:include>
	<div class="container">

		<form class="well form-horizontal"
			form action="<c:url value="/createGroupServlet.do"/>"
				enctype="multipart/form-data" method="post">
			<center>
				<fieldset>

					<!-- Form Name -->
					<center>
						<legend>
							<font style="font-weight: bold;" size="5em">創立屬於你的團購</font>
						</legend>
					</center>

					<!-- 團名 -->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">團名</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope"
									aria-hidden="true"></i></span> <input name="groupName"
									placeholder="團購團名" class="form-control" type="text"
									style="height: 30px;" value="${param.groupName}">
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.groupNameError}</small>
						</div>
					</div>
					<!-- 類型: -->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">*類型</label>
						<div class="col-md-4 selectContainer">
							<div class="input-group"
								style="width: 150px; margin-right: 150px">
								<span class="input-group-addon"><i
									class="fa fa-map-marker" aria-hidden="true"></i> </span> <select
									name="productType" class="m">
									<option value="freshFood">生鮮食品</option>
									<option value="delicious">團購美食</option>
									<option value="makeUp">保養美妝</option>
									<option value="clothes">服飾配件</option>
									<option value="kid">育兒親子</option>
									<option value="life">居家生活</option>
									<option value="relax">休閒娛樂</option>
									<option value="technology">3C家電</option>
								</select>
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.memberAddress}</small>
						</div>
					</div>

					<!--數量下限:-->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">*數量下限</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-key"
									aria-hidden="true"></i></span> <input name="productNum"
									style="height: 30px;" placeholder="開團數量" class="form-control"
									type="text" value="${param.productNum}" />
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.productNumError}</small>
						</div>
					</div>


					<!--結束日期-->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">*結束日期</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-key"
									aria-hidden="true"></i></span> <input id="datepicker" name="deadLine"
									style="height: 30px;" placeholder="格式為西元年月日yyyy-MM-dd"
									class="form-control" type="text" value="${param.deadLine}">
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.deadLineError}</small>
						</div>
						<div style="margin-right: 28%">
							<select name="hours" style="width: 60px;">
								<c:forEach var="hour" begin="1" end="23" step="1">
									<c:choose>
										<c:when test="${hour<10}">
											<option value="${hour}">0${hour}</option>
										</c:when>
										<c:otherwise>
											<option value="${hour}">${hour}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select><font style="">點 </font> <select name="minutes"
								style="width: 60px">
								<c:forEach var="minute" begin="0" end="59" step="1">
									<c:choose>
										<c:when test="${minute<10}">
											<option value="${minute}">0${minute}</option>
										</c:when>
										<c:otherwise>
											<option value="${minute}">${minute}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select> <span>分</span>
						</div>
					</div>

					<!-- 新增品項-->

					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label"></label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group" style="margin-right: 30%">
								<input type="button" id="btn" value="新增品項"> <input
									type='button' id="remove" value='移除品項'>
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.productPriceError}${errorMessages.productError}</small>
						</div>
					</div>

					<!-- 產品細節-->

					<div class="form-group" style="margin-left: 230px;" id="showBlock">
						<label class="col-md-4 control-label">*品項</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-child"
									aria-hidden="true"></i></span> <input name="productName"
									style="height: 30px;" placeholder="品名" class="form-control"
									type="text" value="${param.productName}">
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;"></small>
						</div>
						<div class="col-md-4 inputGroupContainer" style="display: inline;">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-child"
									aria-hidden="true"></i></span> <input name="productPrice"
									style="height: 30px;" placeholder="價格" class="form-control"
									type="text" value="${param.productPrice}">
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;"></small>
						</div>
					</div>


					<!-- 挑選封面圖片-->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">封面圖片</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-file-image-o" aria-hidden="true"></i></span> <input
									type="file" name="cover" id="file2" accept="image/*" value=""
									onchange="fileViewer1()" placeholder="選擇封面圖片">
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.pictureError}</small>
							<tr>
								<td>
								<td>
								<td><div id="newFile1"></div></td>
							</tr>
						</div>
					</div>

					<!-- 挑選商品圖片-->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">封面圖片</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-file-image-o" aria-hidden="true"></i></span> <input
									type="file" multiple="multiple" name="picture" id="file1"
									accept="image/*" value="" onchange="fileViewer()"
									placeholder="選擇商品圖片">
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;"></small>
							<tr>
								<td>
								<td>
								<td><div id="newFile"></div></td>
							</tr>
						</div>
					</div>

					<!-- Text area -->

					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">內容</label>
						<div class="col-md-4 inputGroupContainer">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-pencil"></i></span>
								<textarea class="form-control" name="productInfo"
									style="width: 500px; height: 300px" placeholder="介紹團購的商品"
									value=""></textarea>
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="text-align: left; color: red;"></small>
						</div>
					</div>

					<!-- *匯款資訊-->

					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label">*匯款資訊</label>
						<div class="col-md-4 selectContainer">
							<div class="input-group"
								style="width: 150px; margin-right: 150px">
								<span class="input-group-addon"><i
									class="fa fa-map-marker" aria-hidden="true"></i> </span> <select
									name="shippingWay" class="m">
									<option value="post">郵局</option>
									<option value="blackCat">黑貓</option>
								</select>
							</div>
							<small data-bv-validator="notEmpty" data-bv-validator-for="title"
								class="help-block" style="color: red;">${errorMessages.memberAddress}</small>
						</div>
					</div>


					<!-- Button -->
					<div class="form-group" style="margin-left: 230px;">
						<label class="col-md-4 control-label"></label>
						<center>
							<div class="col-md-4">
								<button type="submit" name="send" class="btn btn-warning"
									value="完成開團">
									完成開團<span class="glyphicon glyphicon-send"></span>
								</button>
								<a href="<c:url value="/theindex.jsp"/>" style="color: lightblack">取消開團</a>
							</div>
						</center>
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