<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />

<title>GroupBuy團購網</title>

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="../myWeb_01Main/css/bootstrap.css" rel="stylesheet">
<link href="../myWeb_01Main/css/bootstrap-responsive.css" rel="stylesheet">
<link href="../myWeb_01Main/css/style.css" rel="stylesheet">
<link href="../myWeb_01Main/css/flexslider.css" type="text/css" media="screen"
	rel="stylesheet" />
<link href="../myWeb_01Main/css/jquery.fancybox.css" rel="stylesheet">
<link href="../myWeb_01Main/css/cloud-zoom.css" rel="stylesheet">
<!-- -------------------------- -->



<link
    href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
    rel="stylesheet" type="text/css" />
<style TYPE="text/css" media="all">
.ui-autocomplete { 
    position: absolute; 
    cursor: default; 
    height: 200px; 
    overflow-y: scroll; 
    overflow-x: hidden;}
</style>


 

</head>
<body> 
	<jsp:include page="/headline.jsp"></jsp:include>
    <br>
	<form name="as400samplecode" action="<c:url value="/searchgroup/search.controller" />"
		method="post">

		
		<div style="padding-left:70px">
			<div style="padding:15px;width: 200px;float:left;margin-left: 215px;">
<%-- 				<input type="text" name="name" placeholder="請輸入 商品 關鍵字" autocomplete="off" value="${param.name}" style="width: 163.6px;"> --%>

              	<input  type="text" id="autoText" name="name" placeholder="請輸入 商品 關鍵字" autocomplete="off" maxlength="20" style="width: 163.6px "/>     
    
            
			</div>  	 	
			<div style="float:left ;padding-top: 10px">
				<input type="submit" name="searchGroupAction" style="background-color:	#FFA488 ; color:white" value="搜尋"> 
			</div>
		</div>
		
	</form>

<div id="maincontainer">
		<section id="product">
		<div class="container">
			<!--  breadcrumb -->
			<ul class="breadcrumb">
<!-- 				<li class="active">Blog</li> -->
			</ul>
			<div class="row" style="float: left;">
				<aside class="span2"> 
				<div class="sidewidt">
					<h2 class="heading2">
						<span>Categories</span>
					</h2>
					<ul class="nav nav-list categories">
						<li><a>生鮮食品 </a></li>
						<li><a>團購美食 </a></li>
						<li><a>保養美妝 </a></li>
						<li><a>服飾配件</a></li>
						<li><a>育兒親子 </a></li>
						<li><a>居家生活</a></li>
						<li><a>休閒娛樂</a></li>
						<li><a>3C家電 </a></li>
					</ul>
				</div>
				</aside>
				<!-- Sidebar End-->
				<!-- Blog listing-->
				<div class="span10 bloggrid" style="margin-left: 120px;">
					<h1 class="heading1">
						<span class="maintext">Popular search</span><span class="subtext"></span>
					</h1>
					<c:forEach var="bean" items="${selectTop2Group}">
					<div style="float:left">
					<ul class="thumbnails">
						<li class="span3">
							<div class="thumbnail">
								<img alt=""	src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover" style="width: 220px;height: 170px;">
									<span class="viewfancypopup">&nbsp;</span>
								<div class="caption">
									<a href="#" class="bloggridtitle">${bean.groupInfoName}</a>
									<div class="author">
										類別 : <a href="#"> ${bean.productType}</a>
									</div>
									<div class="author">
										狀態 : <a href="#"> ${bean.groupStatus}</a>
									</div>
									<div class="author">
										評分 : <a href="#"> ${bean.result}</a>
									</div>
									<div>

										<span class="mr10"> <a href="searchDetailsServlet.controller?groupInfoNo=${bean.groupInfoNo}"> Go !</a>
										</span>
									</div>

								</div>
							</div>
						</li>

					</ul>
					</div>
					</c:forEach>
				</div>
				<aside class="span2"> 
				
				<div class="sidewidt" hidden>
					<h2 class="heading2">
						<span>Blog Categories</span>
					</h2>
					<ul class="nav nav-list categories">
						<li><a href="category.html">Women </a></li>
						<li><a href="category.html">Men </a></li>
						<li><a href="category.html">Children </a></li>
						<li><a href="category.html">Computers</a></li>
						<li><a href="category.html">Jewellery </a></li>
					</ul>
				</div>
				</aside>
				<div class="span10 bloggrid" style="margin-left: 120px;">
					<h1 class="heading1">
						<span class="maintext">Recommend search</span><span class="subtext"></span>
					</h1>
					<c:forEach var="bean" items="${recommendGroup}">
					<div style="float:left">
					<ul class="thumbnails">
						<li class="span3">
							<div class="thumbnail">
								<img alt=""
									src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover" style="width: 270px;height: 200px;">
									<span class="viewfancypopup">&nbsp;</span>
								<div class="caption">
									<a href="#" class="bloggridtitle">${bean.groupInfoName}</a>
									<div class="author">
										類別 : <a href="#"> ${bean.productType}</a>
									</div>
									<div class="author">
										狀態 : <a href="#"> ${bean.groupStatus}</a>
									</div>
									<div class="author">
										評分 : <a href="#"> ${bean.result}</a>
									</div>
									<div>

										<span class="mr10"> <a href="searchDetailsServlet.controller?groupInfoNo=${bean.groupInfoNo}"> Go !</a>
										</span>
									</div>

								</div>
							</div>
						</li>

					</ul>
					</div>
					</c:forEach>
				</div>
				
			</div>
		</div>
		</section>
	</div>
	
<script src="../js/jquery-1.12.3.min.js"></script>
<script
    src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
    type="text/javascript">
</script>

<script type="text/javascript">
$(document).ready(function() {
    $("input#autoText").autocomplete({
        width: 300,
        max: 10,
        delay: 100,
        minLength: 1,
        autoFocus: true,
        cacheLength: 1,
        scroll: true,
        highlight: false,
        source: function(request, response) {
            $.ajax({
                url: "auto",
                dataType: "json",
                data: request,
                success: function( data, textStatus, jqXHR) {
                    console.log( data);  
                    var items = data;
                    response(items);
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('111'); 
                	console.log( textStatus);
                }
            });
        }
 
    });
});

</script>

</body>
</html>