<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我是TITLE</title>
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<!-- <link rel="stylesheet" -->
<!-- 	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="<c:url value='/Web_01Main/assets/sss.css'/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/Web_01Main/assets/css/amazeui.css'/>">
<link rel="stylesheet"
	href="<c:url value='/Web_01Main/assets/css/common.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/Web_01Main/assets/css/index.min.css'/>">

<!-- icon的 -->
<link href="<c:url value='/Web_01Main/assets/font-awesome.min.css'/>"
	rel="stylesheet">

<style>
#ss1 li {
	display: inline;
}

.dropdown-menu:before {
	position: absolute;
	top: -7px;
	left: 9px;
	display: inline-block;
	border-right: 7px solid transparent;
	border-bottom: 7px solid #E7E7E7;
	border-left: 7px solid transparent;
	border-bottom-color: rgba(0, 0, 0, 0.2);
	content: '';
}

.dropdown-menu:after {
	position: absolute;
	top: -6px;
	left: 10px;
	display: inline-block;
	border-right: 6px solid transparent;
	border-bottom: 6px solid #ffffff;
	border-left: 6px solid transparent;
	content: '';
}

.dropup, .dropdown {
	position: relative;
}

.dropdown-menu {
	top: 97%;
}

.open>.dropdown-menu {
	display: block;
}

.dropdown-menu {
	position: absolute;
	top: 97%;
	z-index: 99999;
	display: none;
	float: left;
	min-width: 120px;
	list-style: none;
	background-color: #ffffff;
	border: 1px solid #DDDDDD;
	border-bottom: 4px solid #F25C27;
	border-radius: 0;
	-moz-border-radius: 0;
	-webkit-border-radius: 0;
}
</style>
<style>
.notifications {
	display: inline-block;
	list-style: none;
	margin: -13px 17px 0 0;
	padding: 0 8px;
}

.navbar-cart-inner .fa {
	color: #ffffff;
	font-size: 18px;
	line-height: 20px;
}

.fa {
	display: inline-block;
	font-family: FontAwesome;
	font-style: normal;
	font-weight: normal;
	line-height: inherit;
	-webkit-font-smoothing: antialiased;
}

.cart-item-number {
	position: absolute;
	top: 24px;
	right: initial;
	left: 11px;
	width: auto;
	min-width: 14px;
	border-radius: 2px;
	color: #fff;
	background: #337ab7;
	font-size: 11px;
	line-height: 14px;
	text-align: center;
}

.topbar .container .am-g .topbar-right i {
	opacity: 1;
	margin: 0 5px;
}

.fa-envelope:before {
	content: "\f0e0"
}

.notification-icon {
	background: #FFF;
	border-radius: 50%;
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.3);
	display: inline-block;
	height: 30px;
	position: relative;
	width: 30px;
	text-align: center;
	margin: 0 5px;
	padding-top: 5px;
}

.notification-icon .badge {
	background: #D2312D;
	color: #FFF;
	font-size: 10px;
	font-weight: normal;
	height: 18px;
	padding: 3px 5px 3px 5px;
	position: absolute;
	right: -8px;
	top: -5px;
}

.dropdown-menu-header {
	padding: .5rem 0;
	border-bottom: 1px solid #E6E6E6
}

.topbar .container .am-g {
	/*  	padding-top: 11px;  */
	font-size: 15px;
	color: #fff;
}

.topbar .container .am-g .topbar-right {
	line-height: 29px;
	width: 75%;
}

.topbar {
	width: 100%;
	background-color: #f25c27;
	/* padding: 5px; */
	padding-top: 15px;
}

.profileImg img {
	width: 55px;
	height: 55px;
	border-radius: 50%;
	object-fit: cover;
}
</style>


<script type="text/javascript">
	$(document).ready(function() {
		$.get("/GroupBuy/mailAjaxServlet.do", function(data) {
			result = JSON.parse(data);
			if (result.unReadNum > 0) {
				$("#mailNotify").html(result.unReadNum);
			}
		})

	})
</script>
</head>
<body>

	<div class="layout">
		<!--===========layout-header================-->
		<!-- 全部變成漢堡  -->
		<div class="layout-header am-hide-sm-only" style="border: 10px black">
			<!--topbar start-->
			<div class="topbar" style="padding-top: 15px;">
				<div class="container">
					<div class="am-g">
						<!-- 最左  -->
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
						<!-- 最右 -->
						<div class="am-u-md-9">
							<div class="topbar-right am-text-right am-fr">
								Follow us <i class="am-icon-facebook"
									style="padding-left: 10px; padding-right: 25px; opacity: .3;"></i>

								<c:if test="${empty loginToken}">
									<%-- 									<a href="<c:url value='/secure/newLogin.jsp'/>" --%>
									<label class="click1" style="color: white">登入</label>
									<!-- 										</a> -->
								</c:if>
								<c:if test="${not empty loginToken}">
									<!-- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 -->
									<ul class="notifications" id="ss1">
										<li class="dropdown"><a
											href="<c:url value="/overViewMailServlet.do"/>"
											class="dropdown-toggle notification-icon">
												<p class="fa fa-envelope" style="color: #f25c27"></p> <span
												class="badge" id="mailNotify" style="line-height: 13px"></span>
										</a> <!-- 下拉選單 --></li>
										<!-- 信封結束 -->
										<!-- 鈴鐺開始 -->
										<!-- 								
										<!-- 鈴鐺結束-->
										<!-- 頭像開始 -->
										<li class="dropdown" style="margin: 3px;"><a href="#"
											class="profileImg" data-toggle="dropdown"> <%-- <img src="<c:url value='/Web_01Main/testPic.jpg'/>" --%>
												<img
												src="<c:url value='/pictures/${loginToken.memberPic}'/>"></a>
											<ul class="dropdown-menu" style="margin-top: 20px">
												<li class="menu-item">
												<li><a
													href="<c:url value='/member/member.controller?memberNo=${loginToken.memberNo}'/>">基本資料</a></li>
												<li class="menu-item"><a
													href="<c:url value='/friend/friend.controller?x=friend'/>">好友</a></li>
												<li class="menu-item"><a
													href="<c:url value='/secure/logout.jsp'/>">登出</a></li>
											</ul></li>
										<!-- 頭像結束 -->
									</ul>

								</c:if>
							</div>
							<!-- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 ---- 通知 -->
						</div>
					</div>
				</div>
			</div>
			<!--topbar end-->

			<!-- 	staybar start  -->
			<div class="am-sticky-placeholder">
				<div class="am-sticky-placeholder" style="margin: 0px;">
					<div class="header-box" data-am-sticky="" style=""margin: 0 px">
						<div class="nav-contain">
							<div class="nav-inner">
								<ul class="am-nav am-nav-pills am-nav-justify notifications"
									style="padding-top: 17px; padding-bottom: 0px;">
									<li class=""><a href="<c:url value='/theindex.jsp'/>">
											<img src="<c:url value='/pictures/icon.png'/>"
											style="width: 75px">
									</a></li>

									<li><c:choose>
											<c:when test="${empty loginToken}"> 
												<a style="font-size: 22px" class="click1">創團</a></li>
									</c:when>
									<c:when test="${!empty loginToken}">
										<a style="font-size: 22px"
											href="<c:url value='/creategroup/createGroup.jsp'/>">創團</a>
										</li>
									</c:when>
									</c:choose>
									<li><c:choose>
											<c:when test="${empty loginToken}">
												<a style="font-size: 22px" class="click1">搜團</a></li>
									</c:when>
									<c:when test="${!empty loginToken}">
										<a style="font-size: 22px"
											href="<c:url value='/headline/SearchServlet0.controller'/>">搜團</a>
										</li>
									</c:when>
									</c:choose>
									<li><c:choose>
											<c:when test="${empty loginToken}">
												<a style="font-size: 22px" class="click1">許願池</a></li>
									</c:when>
									<c:when test="${!empty loginToken}">
										<a style="font-size: 22px"
											href="<c:url value='/wish/wishpool.controller'/>">許願池</a>
										<ul class="sub-menu">
											<li class="menu-item"><a
												href="<c:url value='/wish/wishform.jsp'/>">發起許願</a></li>
										</ul>
									</c:when>
									</c:choose>

									<li><c:choose>
											<c:when test="${empty loginToken}">
												<a style="font-size: 22px"  class="click1"
													>我的團購</a>
												<!-- sub-menu start--></li>
									</c:when>
									<c:when test="${!empty loginToken}">
										<a style="font-size: 22px"
											href="#">我的團購</a>

										<!-- sub-menu start-->
										<ul class="sub-menu">
											<li class="menu-item"><a
												href="<c:url value='/eeit9212/grouprecord/mycreatedgroupinfo.controller'/>">我創的團</a></li>
											<li class="menu-item"><a
												href="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller'/>">我參加的團</a></li>
										</ul>
										</li>
									</c:when>
									</c:choose>


									<!-- sub-menu end-->
								</ul>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!--  staybar end -->
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
								<li class=""><a href="html/contact.html" class="">登入</a></li>
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
								<div class="container01">
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

	<script src="<c:url value='/Web_01Main/assets/js/jquery-2.1.0.js'/>"
		charset="utf-8"></script>
	<script src="<c:url value='/Web_01Main/assets/js/amazeui.js'/>"
		charset="utf-8"></script>
	<script src="<c:url value='/Web_01Main/assets/js/common.js'/>"
		charset="utf-8"></script>
</body>
</html>