<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/flexslider.css" type="text/css" media="screen"
	rel="stylesheet" />
<link href="../css/jquery.fancybox.css" rel="stylesheet">
<link href="../css/cloud-zoom.css" rel="stylesheet">
<style>
	.deletemargin{
		margin:0px;
		padding:0px;
	}
</style>
</head>
<body>
	<jsp:include page="/Web_02/headline.jsp"></jsp:include>
	
<div id="maincontainer">
  <section id="product">
    <div class="container">      
      <!-- Product Details-->
      <div class="row">
       <!-- Left Image-->
        <div class="span5">
          <ul class="thumbnails mainimage">
            <li class="span5">
              <a  rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" class="thumbnail cloud-zoom" href="../pictures/aaa.jpg">
                <img style="width:470px;height:313px" src="../pictures/aaa.jpg" alt="" title="">
              </a>
            </li>
            <li class="span5">
              <a  rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" class="thumbnail cloud-zoom" href="../pictures/bbb.jpg">
                <img style="width:470px;height:313px" src="../pictures/bbb.jpg" alt="" title="">
              </a>
            </li>
            <li class="span5">
              <a  rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" class="thumbnail cloud-zoom" href="../pictures/ccc.jpg">
                <img style="width:470px;height:313px" src="../pictures/ccc.jpg" alt="" title="">
              </a>
            </li>
            <li class="span5">
              <a  rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" class="thumbnail cloud-zoom" href="../pictures/ddd.jpg">
                <img style="width:470px;height:313px" src="../pictures/ddd.jpg" alt="" title="">
              </a>
            </li>
          </ul>
          <ul class="thumbnails mainimage">
            <li class="producthtumb">
              <a class="thumbnail" >
                <img  src="../pictures/aaa.jpg" alt="" title="">
              </a>
            </li>
            <li class="producthtumb">
              <a class="thumbnail" >
                <img  src="../pictures/bbb.jpg" alt="" title="">
              </a>
            </li>
            <li class="producthtumb">
              <a class="thumbnail" >
                <img  src="../pictures/ccc.jpg" alt="" title="">
              </a>
            </li>
            <li class="producthtumb">
              <a class="thumbnail" >
                <img src="../pictures/ddd.jpg" alt="" title="">
              </a>
            </li>
          </ul>
        </div>
         <!-- Right Details-->
        <div class="span7">
          <div class="row">
            <div style="margin-left:50px;margin-top:50px" class="span7">
              <h1 class="productname"><span class="bgnone">Fliker搖擺滑板車-SP5 速度升級款</span></h1>
              <div class="quantitybox deletemargin">
                 <div style="float: left">
                   	 	<h2>格紋S號</h2>
                   	</div>
                   	<div style="float: left;margin-left: 10px">
                   		<h2>$ 500.00</h2>
                   	</div>
                   	<div style="float: left;margin-left: 10px">
                      <select class="selectqty">
                	 	 <option>Select</option>
              		  </select>
                  </div>
              </div>
              <div class="quantitybox deletemargin" >
                    <div style="float: left">
                   	 	<h2>格紋S號</h2>
                   	</div>
                   	<div style="float: left;margin-left: 10px">
                   		<h2>$ 500.00</h2>
                   	</div>
                   	<div style="float: left;margin-left: 10px">
                      <select class="selectqty">
                	 	 <option>Select</option>
              		  </select>
                    </div>
                </div>
              </div>
              <ul style="margin-left:20px" class="productpagecart">
                <li><a class="cart" href="#">報名此團購</a>
                </li>
              </ul>
         <!-- Product Description tab & comments-->
         <div  class="productdesc">
                <ul class="nav nav-tabs" id="myTab">
                  <li class="active"><a href="#description">Description</a>
                  </li>
                  <li><a href="#specification">Specification</a>
                  </li>
                  <li><a href="#review">Review</a>
                  </li>
                </ul>
                <div class="tab-content">
                  <div class="tab-pane active" id="description">
                    <h2>h2 tag will be appear</h2>
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum <br>
                    <br>

                  </div>
                  <div class="tab-pane " id="specification">
                    <ul class="productinfo">
                      <li>
                        <span class="productinfoleft"> Product Code:</span> Product 16 </li>
                      <li>
                        <span class="productinfoleft"> Reward Points:</span> 60 </li>
                      <li>
                        <span class="productinfoleft"> Availability: </span> In Stock </li>
                      <li>
                        <span class="productinfoleft"> Old Price: </span> $500.00 </li>
                      <li>
                        <span class="productinfoleft"> Ex Tax: </span> $500.00 </li>
                      <li>
                        <span class="productinfoleft"> Ex Tax: </span> $500.00 </li>
                      <li>
                        <span class="productinfoleft"> Product Code:</span> Product 16 </li>
                      <li>
                        <span class="productinfoleft"> Reward Points:</span> 60 </li>
                    </ul>
                  </div>
                  <div class="tab-pane" id="review">
                    <h3>Write a Review</h3>
                    <form class="form-vertical">
                      <fieldset>
                        <div class="control-group">
                          <label class="control-label">Text input</label>
                          <div class="controls">
                            <input type="text" class="span3">
                          </div>
                        </div>
                        <div class="control-group">
                          <label class="control-label">Textarea</label>
                          <div class="controls">
                            <textarea rows="3"  class="span3"></textarea>
                          </div>
                        </div>
                      </fieldset>
                      <input type="submit" class="btn btn-orange" value="continue">
                    </form>
                  </div>
         
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>
	

<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/respond.min.js"></script>
<script src="../js/application.js"></script>
<script src="../js/bootstrap-tooltip.js"></script>
<script defer src="../js/jquery.fancybox.js"></script>
<script defer src="../js/jquery.flexslider.js"></script>
<script type="text/javascript" src="../js/jquery.tweet.js"></script>
<script  src="../js/cloud-zoom.1.0.2.js"></script>
<script  type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript"  src="../js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script type="text/javascript"  src="../js/jquery.mousewheel.min.js"></script>
<script type="text/javascript"  src="../js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript"  src="../js/jquery.ba-throttle-debounce.min.js"></script>
<script defer src="../js/custom.js"></script>
</body>
</html>