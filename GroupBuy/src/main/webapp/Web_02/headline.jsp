<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>我是TITLE</title>
<link rel="stylesheet" href="<c:url value='/assets/css/amazeui.css'/>">
<link rel="stylesheet"
	href="<c:url value='/assets/css/common.min.css'/>">
<link rel="stylesheet" href="<c:url value='/assets/css/index.min.css'/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/secure/newLogin.jsp"/>
	<div class="layout">
		<!--===========layout-header================-->
		<div class="layout-header am-hide-sm-only">
			<!--topbar start-->
			<div class="topbar">
				<div class="container">
					<div class="am-g">
						<div class="am-u-md-3">
							<div class="topbar-left">
								<i class="am-icon-globe"></i>
								<div class="am-dropdown" data-am-dropdown="">
									<button class="am-btn am-btn-primary am-dropdown-toggle"
										data-am-dropdown-toggle="">
										Language <span class="am-icon-caret-down"></span>
									</button>
									<ul class="am-dropdown-content">
										<li><a href="#">English</a></li>
										<li class="am-divider"></li>
										<li><a href="#">Chinese</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="am-u-md-9">
							<div class="topbar-right am-text-right am-fr">
								Follow us <i class="am-icon-facebook"></i> <i
									class="am-icon-twitter"></i> <i class="am-icon-google-plus"></i>
								<i class="am-icon-pinterest"></i> <i class="am-icon-instagram"></i>
								<i class="am-icon-linkedin"></i> <i class="am-icon-youtube-play"></i>
								<i class="am-icon-rss"></i>
								<c:if test="${empty loginToken}">
								    <label class="click1">LOGIN</label>
								</c:if>
								<a href="html/register.html">SIGN UP</a>
							</div>
						</div>
					</div>
				</div>
			</div>



			<div class="am-sticky-placeholder" style="height: 76px;">
				<div class="am-sticky-placeholder"
					style="margin: 0px; height: 76px;">
					<div class="header-box" data-am-sticky="" style="margin: 0px;">
						<div class="nav-contain">
							<div class="nav-inner">
								<ul class="am-nav am-nav-pills am-nav-justify">
									<li class=""><a href="<c:url value='/index.jsp'/>">我們的LOGO</a></li>

									<!-- sub-menu end-->
									<li>
									<c:choose>
									  <c:when test="${empty loginToken}">
									     <a class="click1">創團</a></li>
									  </c:when>
									  <c:when test="${!empty loginToken}">
									    <a href="<c:url value='/creategroup/createGroup.jsp'/>">創團</a></li>
									  </c:when>
									</c:choose>
									
									<li>
									 <c:choose>
									   <c:when test="${empty loginToken}">
									      <a class="click1">搜團</a></li>
									   </c:when>
									   <c:when test="${!empty loginToken}">
									    <a href="<c:url value='/headline/SearchServlet0.controller'/>">搜團</a></li>
									   </c:when>
						             </c:choose>
						             
						             <li>
						             <c:choose>
									   <c:when test="${empty loginToken}">
									      <a class="click1">許願池</a></li>
									   </c:when>
									   <c:when test="${!empty loginToken}">
									    <a href="<c:url value='/wish/wishpool.controller'/>">許願池</a></li>
									   </c:when>
									 </c:choose>
									 
									 <li>
									  <c:choose>
									   <c:when test="${empty loginToken}">
									      <a class="click1">會員空間</a></li>
									   </c:when>
									   
									   <c:when test="${!empty loginToken}">
									    <a href="<c:url value='/test'/>">會員空間</a> <!-- sub-menu start-->
										<ul class="sub-menu">
											<li class="menu-item"><a
												href="<c:url value='/member/member.controller?memberNo=${loginToken.memberNo}'/>">基本資料</a></li>
											<li class="menu-item"><a
												href="<c:url value='/friend/friend.controller?x=friend'/>">好友</a></li>
											<li class="menu-item"><a
												href="<c:url value='/eeit9212/grouprecord/mycreatedgroupinfo.controller'/>">我創的團</a></li>
											<li class="menu-item"><a
												href="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller'/>">我參加的團</a></li>
										</ul> <!-- sub-menu end--></li>
									  </c:when>
						             </c:choose>
						             
						             <li>
                                     <c:choose>
									   <c:when test="${empty loginToken}">
									      <a class="click1">後臺管理</a></li>
									   </c:when>
									   <c:when test="${!empty loginToken}">
									      <a href="<c:url value='/Backstage/BackStageServlet.controller'/>">後臺管理</a></li>
									   </c:when>
									 </c:choose>
						
									<c:if test="${!empty loginToken}">
										<li><a href="<c:url value='/secure/logout.jsp'/>">登出</a></li>
									</c:if>
<!-- 									這裡是暫時信箱到時再改 -->
									<li><a href="<c:url value='/overViewMailServlet.do'/>">信箱</a></li>
								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

	</div>
	</div>
	</div>
	</div>

	<!--mobile header start-->
	<div class="m-header">
		<div class="am-g am-show-sm-only">
			<div class="am-u-sm-2">
				<div class="menu-bars">
					<a href="#doc-oc-demo1" data-am-offcanvas="{effect: 'push'}"><i
						class="am-menu-toggle-icon am-icon-bars"></i></a>
					<!-- 側邊欄內容 -->
					<nav data-am-widget="menu"
						class="am-menu  am-menu-offcanvas1 am-no-layout"
						data-am-menu-offcanvas=""> <a href="javascript: void(0)"
						class="am-menu-toggle am-active"></a>

					<div class="am-offcanvas"
						style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
						<div class="am-offcanvas-bar am-offcanvas-bar-overlay">
							<ul class="am-menu-nav am-avg-sm-1">
								<li><a href="./index.html" class="">HOMEPAGE</a></li>
								<li class=""><a href="html/about.html" class="">創團</a></li>

								<li class=""><a href="html/example.html" class="">搜團</a></li>
								<li class=""><a href="html/solution.html" class="">許願池</a></li>
								<li class="am-parent"><a
									href="<c:url value='/creategroup/createGroup.jsp'/>">會員空間</a>
									<ul class="am-menu-sub am-collapse" style="height: 0.8px;">
										<li class=""><a href="html/product1.html" class="">基本資料</a>
										</li>
										<li class=""><a href="html/product2.html" class="">好友</a>
										</li>
										<li class=""><a href="html/product3.html" class="">我創的團</a>
										</li>
										<li class=""><a href="html/product3.html" class="">參加的團</a>
										</li>
									</ul></li>
								<li class=""><a href="html/join.html" class="">後台管理</a></li>
								<c:if test="${empty loginToken}">
								  <li class=""><a href="html/contact.html" class="">登入</a></li>
								</c:if>
								<li class="am-parent"><a href=""
									class="nav-icon nav-icon-globe">Language</a>
									<ul class="am-menu-sub am-collapse" style="height: 0.8px;">
										<li><a href="#">English</a></li>
										<li class=""><a href="#">Chinese</a></li>
									</ul></li>
								<li class="nav-share-contain">
									<div class="nav-share-links">
										<i class="am-icon-facebook"></i> <i class="am-icon-twitter"></i>
										<i class="am-icon-google-plus"></i> <i
											class="am-icon-pinterest"></i> <i class="am-icon-instagram"></i>
										<i class="am-icon-linkedin"></i> <i
											class="am-icon-youtube-play"></i> <i class="am-icon-rss"></i>
									</div>
								</li>
							</ul>

						</div>
					</div>
					</nav>

				</div>
			</div>
			<div class="am-u-sm-5 am-u-end">
				<div class="m-logo">
					<a href=""><img src="<c:url value='/assets/images/logo.png'/>"
						alt=""></a>

				</div>
			</div>
		</div>
		<!--mobile header end-->
	</div>




	<!--===========layout-container================-->
	<div class="layout-container">
		<div class="index-page">
			<div data-am-widget="tabs"
				class="am-tabs am-tabs-default am-no-layout">
				<div class="am-tabs-bd"
					style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
					<div data-tab-panel-0="" class="am-tab-panel am-active"></div>
					<div data-tab-panel-1="" class="am-tab-panel ">
						<div class="index-banner">
							<div class="index-mask">
								<div class="container">
									<div class="am-g">
										<div class="am-u-md-10 am-u-sm-centered">
											<h1 class="slide_simple--title">企业移动化，首选云适配</h1>
											<p class="slide_simple--text am-text-left">
												全球领先的HTML5企业移动化解决方案供应商，安全高效的帮助您的企业移动化。云适配企业浏览器Enterploer,让企业安全迈进移动办公时代！
											</p>
											<div class="slide_simple--buttons">
												<button type="button" class="am-btn am-btn-danger">了解更多</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div data-tab-panel-2="" class="am-tab-panel ">
						<div class="index-banner">
							<div class="index-mask">
								<div class="container">
									<div class="am-g">
										<div class="am-u-md-10 am-u-sm-centered">
											<h1 class="slide_simple--title">企业移动化，首选云适配</h1>
											<p class="slide_simple--text am-text-left">
												全球领先的HTML5企业移动化解决方案供应商，安全高效的帮助您的企业移动化。云适配企业浏览器Enterploer,让企业安全迈进移动办公时代！
											</p>
											<div class="slide_simple--buttons">
												<button type="button" class="am-btn am-btn-danger">了解更多</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div></div>
	<script src="<c:url value='/assets/js/jquery-2.1.0.js'/>"
		charset="utf-8"></script>
	<script src="<c:url value='/assets/js/amazeui.js'/>" charset="utf-8"></script>
	<script src="<c:url value='/assets/js/common.js'/>" charset="utf-8"></script>



	</div>
</body>
</html>