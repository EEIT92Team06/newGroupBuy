<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<meta charset="utf-8">
<title>許願池</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href="../css/bootstrap.css" rel="stylesheet">

<link href="../css/bootstrap-responsive.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/flexslider.css" type="text/css" media="screen" rel="stylesheet"  />
<link href="../css/jquery.fancybox.css" rel="stylesheet">
<link href="../css/cloud-zoom.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!-- fav -->
<link rel="shortcut icon" href="assets/ico/favicon.html">
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
          <h1 class="heading1"><span class="maintext">許願專區</span><span class="subtext"> See All Wishes </span></h1>
         <ul class="thumbnails" style="width: 932px;">
         
         <c:forEach var='wish' items="${wishCollection}">
         <li class="span3" >
            <div class="thumbnail">
              <img alt="" src="../pictures/${wish.coverPic}" style="width:270px; height:200px"><span class="viewfancypopup">&nbsp;</span>
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

<!-- Footer -->
<footer id="footer">
  
  <section class="footerlinks">
    <div class="container">
      <div class="info">
        <ul>
          <li><a href="#">Privacy Policy</a>
          </li>
          <li><a href="#">Terms &amp; Conditions</a>
          </li>
          <li><a href="#">Affiliates</a>
          </li>
          <li><a href="#">Newsletter</a>
          </li>
        </ul>
      </div>
      <div id="footersocial">
        <a href="#" title="Facebook" class="facebook">Facebook</a>
        <a href="#" title="Twitter" class="twitter">Twitter</a>
        <a href="#" title="Linkedin" class="linkedin">Linkedin</a>
        <a href="#" title="rss" class="rss">rss</a>
        <a href="#" title="Googleplus" class="googleplus">Googleplus</a>
        <a href="#" title="Skype" class="skype">Skype</a>
        <a href="#" title="Flickr" class="flickr">Flickr</a>
      </div>
    </div>
  </section>

  <a id="gotop" href="#">Back to top</a>
</footer>
<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
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