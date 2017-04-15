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

</head>
<body>
	<jsp:include page="/headline.jsp"></jsp:include>
    <br>
	<form action="<c:url value="/searchgroup/search.controller" />"
		method="post">
		<table>
		<div style="padding-left:70px ;">
			<div style="padding:15px;width: 200px;float:left;margin-left: 80px;">
				<input type="text" name="name" placeholder="請輸入 商品 關鍵字" autocomplete="off" value="${param.name}" style="width: 163.6px;">
			</div>
			<div style="float:left ;padding-top: 10px">
				<input type="submit" name="searchGroupAction" style="background-color:	#FFA488 ; color:white" value="搜尋">
			</div>
		</div>
		</table>
	</form>

<div id="maincontainer">
		<section id="product">
		<div class="container">
			<!--  breadcrumb -->
			<ul class="breadcrumb">
<!-- 				<li class="active">Blog</li> -->
			</ul>
			<div class="row">
				<aside class="span2"> 
				
				<div class="sidewidt">
					<h2 class="heading2">
						<span>Categories</span>
					</h2>
					<ul class="nav nav-list categories">
						<li><a href="category.html">生鮮食品 </a></li>
						<li><a href="category.html">團購美食 </a></li>
						<li><a href="category.html">保養美妝 </a></li>
						<li><a href="category.html">服飾配件</a></li>
						<li><a href="category.html">育兒親子 </a></li>
						<li><a href="category.html">居家生活</a></li>
						<li><a href="category.html">休閒娛樂</a></li>
						<li><a href="category.html">3C家電 </a></li>
					</ul>
				</div>
				</aside>
				<!-- Sidebar End-->
				<!-- Blog listing-->
				<div class="span10 bloggrid">
					<h1 class="heading1">
						<span class="maintext">Popular search</span><span class="subtext"></span>
					</h1>
					<c:forEach var="bean" items="${selectTop2Group}">
					<div style="float:left">
					<ul class="thumbnails">
						<li class="span3">
							<div class="thumbnail">
								<a
									href="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover"
									class="fancyboxpopup"><img alt=""
									src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover" style="width: 270px;height: 200px;">
									<span class="viewfancypopup">&nbsp;</span></a>
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
				<div class="span10 bloggrid">
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







<!-- 		<div class="wrapper style3"> -->
<!-- 		<div class="title">熱門搜尋</div> -->
<!-- 		<div id="highlights" class="container"> -->
<!-- 			<div class="row 150%"> -->
<%-- 				<c:forEach var="bean" items="${selectTop2Group}"> --%>
<!-- 				<div class="4u 12u(mobile)"> -->
<!-- 					<section class="highlight"> <a href="#" -->
<%-- 						class="image featured"><img src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover" alt="" /></a> --%>
<!-- 					<h3> -->
<%-- 						${bean.groupInfoName} --%>
<!-- 					</h3> -->
<%-- 					<p>類別 : ${bean.productType}</p> --%>
<%-- 					<p>狀態 : ${bean.groupStatus}</p> --%>
<%-- 					<p>創團者 : ${bean.memberName}</p> --%>
<%-- 					<p>評分 : ${bean.result}</p> --%>
<!-- 					<ul class="actions"> -->
<%-- 						<li><a href="searchDetailsServlet.controller?groupInfoNo=${bean.groupInfoNo}" class="button style1">前往團購</a></li> --%>
<!-- 					</ul> -->
<!-- 					</section> -->
<!-- 				</div> -->
<%-- 				</c:forEach> --%>
			
			
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 		<p>.</p> -->
<!-- 		<p>.</p> -->
<!-- 		<div class="wrapper style3"> -->
<!-- 		<div class="title">推薦團購</div> -->
<!-- 		<div id="highlights" class="container"> -->
<!-- 			<div class="row 150%"> -->
<%-- 				<c:forEach var="bean" items="${recommendGroup}"> --%>
<!-- 				<div class="4u 12u(mobile)"> -->
<!-- 					<section class="highlight"> <a href="#" -->
<%-- 						class="image featured"><img src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover" alt="" /></a> --%>
<!-- 					<h3> -->
<%-- 						${bean.groupInfoName} --%>
<!-- 					</h3> -->
<%-- 					<p>類別 : ${bean.productType}</p> --%>
<%-- 					<p>狀態 : ${bean.groupStatus}</p> --%>
<%-- 					<p>創團者 : ${bean.memberName}</p> --%>
<%-- 					<p>評分 : ${bean.result}</p> --%>
<!-- 					<ul class="actions"> -->
<%-- 						<li><a href="searchDetailsServlet.controller?groupInfoNo=${bean.groupInfoNo}" class="button style1">前往團購</a></li> --%>
<!-- 					</ul> -->
<!-- 					</section> -->
<!-- 				</div> -->
<%-- 				</c:forEach> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

</body>
</html>