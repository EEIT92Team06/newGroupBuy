<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" href="css/jquery-ui.min.css"/>
<link rel="stylesheet" href="css/jquery-ui.structure.min.css"/>
<link rel="stylesheet" href="css/jquery-ui.theme.min.css"/>
<link rel="stylesheet" href="cutpicture/imgareaselect-default.css "/>
<style type="text/css">
.create {
	margin: 5px;
}
  .thumb{
 	    height:200px;margin:20px;
 	    	      }
  .thumb1{
         height:200px;margin:20px;
  } 
.groupName_input {
	font-size: 18px;
	color: #666;
	border: 2px solid #e0e1e1;
	padding: 10px;
	border-radius: 3px;
	width: 260px;
	margin: 15px;
	
}

.m {
	margin: 15px;
}

.file {
	color: #666;
	padding: 10px;
	border-radius: 3px;
	width: 260px;
	margin: 15px;
}

.submit {
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: #FF4500;
	color: white;
	line-height: 40px;
	text-align: center;
	width: 180px;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
}
</style>
</head>
    <script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="scripts/jquery.imgareaselect.pack.js"></script>
<script>
$(function(){
	
	
	var txtId = 1;
	//add input block in showBlock
	$("#btn").click(function () {
		if(txtId<5){
	    $("#showBlock").append("<div id="+txtId+"><span style='color: red'>*</span>品項:<input class='groupName_input' type='text'name='productName'><span style='color: red'>*</span>價格:<input class='groupName_input' type='text' name='productPrice'></div>");
	      txtId++;
	    }
	});
	
	//remove div
	$("#remove").click(function () {
		if(txtId>1){
		txtId--;
		}
		$("#"+txtId).remove();
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

<body>
	<jsp:include page="/Web_02/headline.jsp"></jsp:include>
	<div>
		<div style="width: 700px;margin: 38px auto">
			<form action="<c:url value="/createGroupServlet.do"/>"
				enctype="multipart/form-data" method="post">
				<div>
					<h1 style="font-size: 30">創立屬於你的團購</h1>
				</div>
				<div>
					<span style="color: red">*</span>團名 <input class="groupName_input" type="text"
						name="groupName"><font style="color: red">${errorMessages.groupNameError}</font>
				</div>
				<div>
					<span style="color: red">*</span>類型: <select name="productType" class="m">
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
				<div>
					<span style="color: red">*</span>數量下限: <input class="groupName_input" type="text"
						name="productNum"><font style="color: red">${errorMessages.productNumError}</font>
				</div>
				<div>
					<span style="color: red">*</span>結束日期: <input id="datepicker" name="deadLine"
						class="groupName_input" type="text"/> <select
						name="hours">
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
					</select> <span>點 </span> <select name="minutes">
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
					</select> <span>分</span><font style="color: red"> ${errorMessages.deadLineError}</font>
				</div>
				<div>
					<input type="button" id="btn" value="新增品項" >
					<input type='button' id="remove" value='移除品項' >
					<font style='margin-left:15px; color: red'>${errorMessages.productPriceError}${errorMessages.productError}</font>
				
				</div>
			 <div id="showBlock">
				<div>
					<span style="color: red">*</span>品項:<input class="groupName_input" type="text"
						name="productName"> <span style="color: red">*</span>價格:<input
						class="groupName_input" type="text" name="productPrice">
				</div>		
		    </div>
				<div>
					<span style="color: red">*</span>封面圖片:<font style="margin-left:15px;color: red">${errorMessages.pictureError}</font>
					<input
						class="file" type="file" name="cover" id="file2" accept="image/*"
						onchange="fileViewer1()">
				</div>
				<div id="newFile1">
				</div>
				<div>
					<span style="color: red">*</span>產品圖片 <input class="file" type="file"
						multiple="multiple" name="picture" id="file1" accept="image/*"
						onchange="fileViewer()">
				</div>
				<div id="newFile">
				</div>
				<div>
					<span>商品資訊:</span>
				</div>
				<div>
					<textarea rows="8" cols="95" name="productInfo"></textarea>
				</div>
				<div>
					<span style="color: red">*</span><span>*匯款資訊:</span> <input class="groupName_input" type="text"
						name="bankAccount"><font style="color: red">${errorMessages.bankAccountError}</font>
				</div>
				<div>
					<span style="color: red">*</span>寄送方式: <select name="shippingWay"
						class="groupName_input">
						<option value="post">郵局</option>
						<option value="blackCat">黑貓</option>
					</select><br>
				</div>
				<div style="padding-top: 50px; text-align: center; width: 700">
					<a href="<c:url value="/theindex.jsp"/>" style="color: lightblack">取消開團</a><input class="submit"
						type="submit" value="完成開團" />
				</div>
			</form>
		</div>
	</div>
</body>

</html>