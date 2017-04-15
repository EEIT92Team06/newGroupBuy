<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wish Form</title>
<style type="text/css">
    #success_message{ display: none;}
     .thumb {
            height: 50px;
            margin: 5px;
        }
</style>
<link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
<link rel='stylesheet prefetch' href='//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
<script src="https://use.fontawesome.com/72bc13eff4.js"></script>
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<div class="container">

    <form class="well form-horizontal" action="<c:url value="/wish/wishform.controller" />" method="post" enctype="multipart/form-data" id="contact_form">
<center>
<fieldset>

<!-- Form Name -->
<center>
<legend>許下您的願望!</legend>
</center>
<!-- Text input-->

<div class="form-group"  style="margin-left: 230px;">
  <label class="col-md-4 control-label">標題</label>  
  <div class="col-md-4 inputGroupContainer">
  <div class="input-group">
  <span class="input-group-addon"><i class="fa fa-university" aria-hidden="true"></i></span>
  <input  name="title" placeholder="許願標題" class="form-control" style="height: 29.6px;"  type="text" value="${param.title}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="text-align: left;color: red;">${errorMsg.title}</small>
  </div>
</div>

<!-- Select Basic -->
   
<div class="form-group" style="margin-left: 230px;"> 
  <label class="col-md-4 control-label">產品類別</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select name="productType" class="form-control selectpicker">
        <option value="0">選擇產品類別</option>
		<option value="1">生鮮食品</option>
		<option value="2">團購美食</option>
		<option value="3">保養美妝</option>
		<option value="4">服飾配件</option>
		<option value="5">育兒親子</option>
		<option value="6">居家生活</option>
		<option value="7">休閒娛樂</option>
		<option value="8">3C家電</option>
    </select>
  </div>
  <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="text-align: left;color: red;">${errorMsg.type}</small>
</div>
</div>

<!-- Text input-->
       <div class="form-group" style="margin-left: 230px;">
  <label class="col-md-4 control-label">品名</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-product-hunt" aria-hidden="true"></i></span>
  <input name="name" placeholder="產品名稱" class="form-control" style="height: 29.6px;" type="text" value="${param.name}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="text-align: left;color: red;">${errorMsg.name}</small>
  </div>
</div>


<!-- Text input-->
       
<div class="form-group" style="margin-left: 230px;">
  <label class="col-md-4 control-label">原價</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-usd" aria-hidden="true"></i></span>
  <input name="price" placeholder="價格" class="form-control" style="height: 29.6px;" type="text" value="${param.price}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="text-align: left;color: red;">${errorMsg.price}</small>
  </div>
</div>

<!-- Text input-->
      
<div class="form-group" style="margin-left: 230px;">
  <label class="col-md-4 control-label">來源</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-newspaper-o" aria-hidden="true"></i></span>
  <input name="source" placeholder="來源" class="form-control" style="height: 29.6px;" type="text" value="${param.source}">
    </div>
  </div>
</div>

<!-- Text input-->
 
<div class="form-group" style="margin-left: 230px;">
  <label class="col-md-4 control-label">圖片上傳</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-file-image-o" aria-hidden="true"></i></span>
  <input id="file1" name="picUpload" placeholder="檔案上傳" value="upload" style="height: 29.6px;" class="form-control"  type="file" accept="image/*" multiple="multiple" onchange="fileViewer()">

    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="text-align: left;color: red;">${errorMsg.upload}</small>
		<tr>
		  <td></<td>
		  <td><div id="dropZone" ><img src="" name="pic" /></div></td>
		</tr>
  </div>
</div>
            
<!-- Text area -->
  
<div class="form-group" style="margin-left: 230px;">
  <label class="col-md-4 control-label">內容</label>
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
        	<textarea class="form-control" name="content" placeholder="簡單介紹這項產品" value="${param.content}"></textarea>
  </div>
  <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="text-align: left;color: red;">${errorMsg.content}</small>
  </div>
</div>
<!-- Button -->
<div class="form-group" style="margin-left: 230px;">
  <label class="col-md-4 control-label"></label>
  <center>
  <div class="col-md-4">
    <button type="submit" name="send" class="btn btn-warning" value="送出">送出<span class="glyphicon glyphicon-send"></span></button>
  </div>
  </center>
</div>
</fieldset>
</center>
</form>
</div>
    </div><!-- /.container -->
<!-- <script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script> -->
<script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script src='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
<script src='//cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script>  $(document).ready(function() {
    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        })
        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({ opacity: "show" }, "slow") // Do something ...
                $('#contact_form').data('bootstrapValidator').resetForm();

            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });
});


//# sourceURL=pen.js
</script>
<script>
        function fileViewer() {    //在DIV中顯示上傳的圖片
//         	var allPics = document.getElementById("file1").files;
//         	if(allPics.length>3){
//         		alert("只能上傳1~3張圖片");
//         		allPics.parentNode.removeChild(allPics); 
//         	}
        	
        	document.getElementById("dropZone").innerHTML='';  //重新上傳時,刪掉原先顯示的圖片
        	var count=0;  //id的初始值
            var files = document.getElementById("file1").files
            var filesLen = files.length;
            for (var i = 0; i < filesLen; i++) {
                
                var reader = new FileReader();
                reader.readAsDataURL(files[i]);
                reader.onload = function (e) {
                    var fileContent=e.target.result;

                    var imgObj = document.createElement("img");
                   
                    imgObj.setAttribute("id",count);
                    imgObj.setAttribute("src", fileContent);
                    imgObj.setAttribute("class", "thumb");
                    imgObj.setAttribute("name","pic");
                    imgObj.setAttribute("onclick","choseCover(this)"); //將這個圖片物件傳到choseCover
              
                    document.getElementById("dropZone").appendChild(imgObj);
                    count++; 
                }
            }
        }
         function choseCover(obj) {
        	id=obj.id;
        	var imgs = document.getElementsByTagName("img"); //取得img陣列
        	 console.log(imgs);
            for(var i=2;i<imgs.length;i++){ //移除原先選取的圖片的外框及value
            	imgs.item(i).setAttribute("style","");
            }
        	//附加外框
         	document.getElementById(id).setAttribute("style","border:5px outset yellowgreen");
        	
         	var xhr = new XMLHttpRequest();
    		var queryString = "picNo="+id;
    		var url = "wishform.controller?"+ queryString;
    		xhr.open("GET" , url , true);
    		xhr.send();
    		xhr.onreadystatechange = function(){ 
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