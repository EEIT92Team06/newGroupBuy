<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>GroupBuy後台管理系統</title>

<!-- Bootstrap -->
<link href="vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<!-- NProgress -->
<link href="vendors/nprogress/nprogress.css" rel="stylesheet">
<!-- bootstrap-daterangepicker -->
<link href="vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="navbar nav_title" style="border: 0;">
						<a href="<c:url value="/Backstage/backstageindex.jsp" />" class="site_title"><i class="fa fa-paw"></i>
							<span><font size="3">GroupBuy後台管理系統</font></span></a>
					</div>

					<div class="clearfix"></div>

					<!-- menu profile quick info -->
					<div class="profile clearfix">
						<div class="profile_pic"></div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${sessionScope.managerLogin.memberNickName}</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>管理分類</h3>
							<ul class="nav side-menu">
								<li><a
									href="<c:url value='/Backstage/newbackstage3.jsp' />"><i
										class="fa fa-users"></i>創搜團管理</a></li>
								<li><a
									href="<c:url value='/Backstage/newwishbackstage.jsp' />"><i
										class="fa fa-star-o"></i>許願池管理</a></li>
								<li><a
									href="<c:url value='/Backstage/newbackstage2.jsp' />"><i
										class="fa fa-user"></i>會員狀態管理</a></li>
								<li><a
									href="<c:url value='/Backstage/newbackstage1.jsp' />"><i
										class="fa fa-envelope-o"></i>發送公告信</a></li>
								<li><a
									href="<c:url value='/Backstage/newbackstage4.jsp' />"><i
										class="fa fa-ban"></i>檢舉管理</a>
							</ul>
						</div>
						<div class="menu_section"></div>

					</div>
					<!-- /sidebar menu -->


					<!-- /menu footer buttons -->
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>

						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <span class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="<c:url value="/theindex.jsp" />">返回GroupBuy首頁</a></li>
									<li><a href="login.html"><i
											class="fa fa-sign-out pull-right"></i>登出</a></li>
								</ul></li>

							<li role="presentation" class="dropdown">
								<ul id="menu1" class="dropdown-menu list-unstyled msg_list"
									role="menu">
									
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			<!-- /top navigation -->
			<!-- 主要內容 -->
			<div class="right_col" role="main">
				<div class="productdesc"
					style="width: 800px; ;margin-left: 25%;margin-top: 6%">
					<form action="" style="float: right;">
						<i style="margin-right: 9px;" class="fa fa-search"
							aria-hidden="true"></i><input type="text"
							style="margin-bottom: 3px; width: 300px; height: 40px"
							name="searchKeyWord" class="input-medium search-query"
							placeholder="Search Here…">
					</form>
					<form action="<c:url value="/Backstage/banGroupServlet" />"
						method="post">
						<ul class="nav nav-tabs" id="myTab">
							<li class="" onclick="removeChecked()"><font size="5">選擇封鎖團</font></li>
							<li id="trashCan" style="display: none">
								<button name="allMailBotton" value="allMailBotton"
									onclick="checkDelete()" style="margin-left: 30px" type="submit">
									<i class="fa fa-trash-o fa-2x" aria-hidden="true"></i>
								</button>
							</li>
						</ul>
						<div class="tab-content">
							<!-- 這裡是全部信件 -->
							<div>
								<div id="maincontainer">
									<section id="product">
										<div class="container">
											<div class="cart-info">
												<table class="table table-striped table-bordered"
													style="font-size: medium; margin-top: 15px">
													<tr>
														<th style="width:; text-align: center;" class="image"><input
															style="width:; height: 16px" type="checkbox"
															name="cancel"
															onclick="checkAll(this,'allMail','announceMail')"></th>
														<th style="width:; text-align: center;" class="name">編號</th>
														<th style="width:; text-align: center;" class="quantity">團名</th>
														<th style="width:; text-align: center;" class="model">產品分類</th>
														<th style="width:; text-align: center;" class="model">團狀態</th>
														<th style="width:; text-align: center;" class="model">姓名</th>
														<th style="width:; text-align: center;" class="model">評分紀錄</th>
													</tr>
													<c:forEach var="bean" items="${Allgroup}">
														<tr>
															<c:if test="${bean.groupStatus != '封鎖'}">
																<td><input type="checkbox" name="checkbox"
																	value=${bean.groupInfoNo}></td>
															</c:if>
															<c:if test="${bean.groupStatus == '封鎖'}">
																<td></td>
															</c:if>
															<td>${bean.groupInfoNo}</td>
															<td>${bean.groupInfoName}</td>
															<td>${bean.productType}</td>
															<td>${bean.groupStatus}</td>
															<td>${bean.memberName}</td>
															<td>${bean.result}</td>
														</tr>
													</c:forEach>
												</table>
												<input type="submit" name="sendMail" value="送出">
											</div>
										</div>
									</section>
								</div>
							</div>
						</div>
					</form>
				</div>

			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right">
					<a href="" target="_blank" title=""></a> <a href="" title=""
						target="_blank"></a>
				</div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>

	<!-- jQuery -->
	<script src="../js/jquery.js"></script>
	<!-- Bootstrap -->
	<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script src="vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="vendors/nprogress/nprogress.js"></script>
	<!-- Chart.js -->
	<script src="vendors/Chart.js/dist/Chart.min.js"></script>

	<!-- Flot -->
	<script src="vendors/Flot/jquery.flot.js"></script>
	<script src="vendors/Flot/jquery.flot.pie.js"></script>
	<script src="vendors/Flot/jquery.flot.time.js"></script>
	<script src="vendors/Flot/jquery.flot.stack.js"></script>
	<script src="vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="vendors/DateJS/build/date.js"></script>
	<!-- bootstrap-daterangepicker -->

	<script src="vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="build/js/custom.min.js"></script>
</body>
</html>