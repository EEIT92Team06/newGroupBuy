<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />
<meta charset="utf-8">
<title>GroupBuy團購網</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="../myWeb_01Main/css/bootstrap.css" rel="stylesheet">
<link href="../myWeb_01Main/css/bootstrap-responsive.css" rel="stylesheet">
<link href="../myWeb_01Main/css/style.css" rel="stylesheet">
<link href="../myWeb_01Main/css/flexslider.css" type="text/css" media="screen" rel="stylesheet"  />
<link href="../myWeb_01Main/css/jquery.fancybox.css" rel="stylesheet">
<link href="../myWeb_01Main/css/cloud-zoom.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- fav -->
<link rel="shortcut icon" href="../myWeb_01Main/assets/ico/favicon.html">
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<br>


<div id="maincontainer">
  <section id="product">
    <div class="container">
      <div class="row">
        <!-- Sidebar Start-->
        <aside  class="span3">  
         <!-- Category-->    
          <div class="sidewidt">
            <h2 class="heading2"><span>Wish Categories</span></h2>
            <ul class="nav nav-list categories">
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=0'/>">全部</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=1'/>">生鮮食品</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=2'/>">團購美食</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=3'/>">保養美妝</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=4'/>">服飾配件</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=5'/>">育兒親子</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=6'/>">居家生活</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=7'/>">休閒娛樂</a>
              </li>
              <li>
                <a href="<c:url value='/wish/wishsearch.controller?productType=8'/>">3C家電</a>
              </li>
            </ul>
          </div>
        </aside>
        <!-- Sidebar End--> 
        
        
        <!-- Blog listing-->      
        <div class="span9 bloggrid" style="margin-left: 100px;">
          <h1 class="heading1" style="width: 750px;">
            <span class="maintext">許願專區</span><span class="subtext"> See All Wishes </span>
          </h1>
         <ul class="thumbnails" style="width: 932px;">
         
         <c:forEach var='wish' items="${wishCollection}">
         <li class="span3" style="margin-right: 30px;height: 280px;">
            <div class="thumbnail">
              <img alt="" src="../pictures/${wish.coverPic}" style="width:270px; height:170px"><span class="viewfancypopup">&nbsp;</span>
              <div class="caption">
                <a href="<c:url value="/wish/wishdetail.controller?wishNo=${wish.wishNo}" />" class="bloggridtitle">${wish.title}</a>    
              </div>
          </li>
          </c:forEach>
          
        </ul>
        
        </div>
      </div>
    </div>
  </section>
</div>


<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../myWeb_01Main/js/jquery.js"></script>
<script src="../myWeb_01Main/js/bootstrap.js"></script>
<script src="../myWeb_01Main/js/respond.min.js"></script>
<script src="../myWeb_01Main/js/application.js"></script>
<script src="../myWeb_01Main/js/bootstrap-tooltip.js"></script>
<script defer src="../myWeb_01Main/js/jquery.fancybox.js"></script>
<script defer src="../myWeb_01Main/js/jquery.flexslider.js"></script>
<script type="text/javascript" src="../myWeb_01Main/js/jquery.tweet.js"></script>
<script  src="../myWeb_01Main/js/cloud-zoom.1.0.2.js"></script>
<script  type="text/javascript" src="../myWeb_01Main/js/jquery.validate.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.mousewheel.min.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.ba-throttle-debounce.min.js"></script>
<script defer src="../myWeb_01Main/js/custom.js"></script>
</body>
</html>