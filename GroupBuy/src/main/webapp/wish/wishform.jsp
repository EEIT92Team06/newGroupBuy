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
</style>
<link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<link rel='stylesheet prefetch' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css'>
<link rel='stylesheet prefetch' href='//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css'>
<script src="https://use.fontawesome.com/72bc13eff4.js"></script>
</head>
<body>
<jsp:include page="/Web_02/headline.jsp"></jsp:include>
<div class="container">

    <form class="well form-horizontal" action="<c:url value="/wish/wishform.controller" />" method="post" enctype="multipart/form-data" id="contact_form">
<fieldset>

<!-- Form Name -->
<legend>Contact Us Today!</legend>

<!-- Text input-->

<div class="form-group">
  <label class="col-md-4 control-label">標題</label>  
  <div class="col-md-4 inputGroupContainer">
  <div class="input-group">
  <span class="input-group-addon"><i class="fa fa-university" aria-hidden="true"></i></span>
  <input  name="title" placeholder="許願標題" class="form-control"  type="text" value="${param.title}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="color: red;">${errorMsg.title}</small>
  </div>
</div>

<!-- Select Basic -->
   
<div class="form-group"> 
  <label class="col-md-4 control-label">產品類別</label>
    <div class="col-md-4 selectContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
    <select name="productType" class="form-control selectpicker" >
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
  <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="color: red;">${errorMsg.type}</small>
</div>
</div>

<!-- Text input-->
       <div class="form-group">
  <label class="col-md-4 control-label">品名</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-product-hunt" aria-hidden="true"></i></span>
  <input name="name" placeholder="產品名稱" class="form-control"  type="text" value="${param.name}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="color: red;">${errorMsg.name}</small>
  </div>
</div>


<!-- Text input-->
       
<div class="form-group">
  <label class="col-md-4 control-label">原價</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-usd" aria-hidden="true"></i></span>
  <input name="price" placeholder="價格" class="form-control" type="text" value="${param.price}">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="color: red;">${errorMsg.price}</small>
  </div>
</div>

<!-- Text input-->
      
<div class="form-group">
  <label class="col-md-4 control-label">來源</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-newspaper-o" aria-hidden="true"></i></span>
  <input name="source" placeholder="來源" class="form-control" type="text" value="${param.source}">
    </div>
  </div>
</div>

<!-- Text input-->
 
<div class="form-group">
  <label class="col-md-4 control-label">圖片上傳</label>  
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="fa fa-file-image-o" aria-hidden="true"></i></span>
  <input id="file1" name="picUpload" placeholder="檔案上傳" value="upload" class="form-control"  type="file" accept="image/*" multiple="multiple">
    </div>
    <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="color: red;">${errorMsg.upload}</small>
  </div>
</div>

<!-- Text area -->
  
<div class="form-group">
  <label class="col-md-4 control-label">內容</label>
    <div class="col-md-4 inputGroupContainer">
    <div class="input-group">
        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
        	<textarea class="form-control" name="content" placeholder="簡單介紹這項產品" value="${param.content}"></textarea>
  </div>
  <small data-bv-validator="notEmpty" data-bv-validator-for="title" class="help-block" style="color: red;">${errorMsg.content}</small>
  </div>
</div>
<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label"></label>
  <div class="col-md-4">
    <button type="submit" name="send" class="btn btn-warning" value="送出">送出<span class="glyphicon glyphicon-send"></span></button>
  </div>
</div>
</fieldset>
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
</body>
</html>