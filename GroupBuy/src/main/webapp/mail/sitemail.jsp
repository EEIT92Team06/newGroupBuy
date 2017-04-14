<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html  PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>SimpleOne - A Responsive Html5 Ecommerce Template</title>
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
<link href="../css/layer.css" rel="stylesheet">
<link rel="shortcut icon" href="../assets/ico/favicon.html">
<script src="https://use.fontawesome.com/72bc13eff4.js"></script>
<script src="../js/js/jquery.js"></script>
<script src="../js/layer/layer.js"></script>
<script type="text/javascript">
	//全部信件的全選
	function checkAll(obj, allMail, announceMail) {
		var checkboxs = document.getElementsByName("allMail");
		for (var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
		var checkboxs1 = document.getElementsByName("announceMail");
		for (var i = 0; i < checkboxs1.length; i++) {
			checkboxs1[i].checked = obj.checked;
		}
		check();
		check1()
	}
	//未讀信件的全選
	function checkAll2(obj, allMail1, announceMail1) {
		var checkboxs = document.getElementsByName("allMail1");
		for (var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
		var checkboxs1 = document.getElementsByName("announceMail1");
		for (var i = 0; i < checkboxs1.length; i++) {
			checkboxs1[i].checked = obj.checked;
		}
		//當勾選全選時執行下面兩個方法，表示有勾垃圾桶跑出來
		checkUnRead();
		checkUnRead1();
	}

	//以下兩個是全部信件
	function check1() {
		var announceMail = document.getElementsByName('announceMail');
		var trashCan = document.getElementById('trashCan');
		for (var i = 0; i < announceMail.length; i++) {
			if (announceMail[i].checked == true) {
				trashCan.setAttribute("style", "");//被勾選時垃圾桶跑出來
				break;
			} else {
				trashCan.setAttribute("style", "display: none;");//被勾選時垃圾桶消失
			}
		}
	}
	function check() {
		var allMail = document.getElementsByName('allMail');
		var trashCan = document.getElementById('trashCan');
		for (var i = 0; i < allMail.length; i++) {
			if (allMail[i].checked == true) {
				trashCan.setAttribute("style", "");
				break;
			} else {
				trashCan.setAttribute("style", "display: none;");
			}
		}
	}

	//以下兩個是未讀信件
	function checkUnRead1() {
		var announceMail = document.getElementsByName('announceMail1');
		var trashCan = document.getElementById('unReadtrashCan');
		for (var i = 0; i < announceMail.length; i++) {
			if (announceMail[i].checked == true) {
				trashCan.setAttribute("style", "");
				break;
			} else {
				trashCan.setAttribute("style", "display: none;");
			}
		}
	}
	function checkUnRead() {
		var allMail = document.getElementsByName('allMail1');
		var trashCan = document.getElementById('unReadtrashCan');
		for (var i = 0; i < allMail.length; i++) {
			if (allMail[i].checked == true) {
				trashCan.setAttribute("style", "");
				break;
			} else {
				trashCan.setAttribute("style", "display: none;");
			}
		}
	}
	function removeChecked() {
		var trashCan = document.getElementById('unReadtrashCan');
		trashCan.setAttribute("style", "display: none;");
		var trashCan1 = document.getElementById('trashCan');
		trashCan1.setAttribute("style", "display: none;");

		var checkboxs = document.getElementsByName("allMail1");
		for (var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
		var announceMail = document.getElementsByName('announceMail1');
		for (var i = 0; i < announceMail.length; i++) {
			announceMail[i].checked = false;
		}
		var allMail = document.getElementsByName('allMail');
		for (var i = 0; i < allMail.length; i++) {
			allMail[i].checked = false;
		}
		var announceMail = document.getElementsByName('announceMail');
		for (var i = 0; i < announceMail.length; i++) {
			announceMail[i].checked = false;
		}
		var cancel = document.getElementsByName('cancel');
		for (var i = 0; i < cancel.length; i++) {
			cancel[i].checked = false;
		}
		var cancel1 = document.getElementsByName('cancel1');
		for (var i = 0; i < cancel1.length; i++) {
			cancel1[i].checked = false;
		}
	}

	function allMailAddTr() {
  		
  }

	function announceMailAddTr() {

	}
	// 	function checkDelete(){
	// 		layer.confirm('確定刪除信件嗎?', {
	// 		  btn: ['確定','取消']
	// 		   ,function(){
	// 			  location.replace()
	// 		  },function(){

	// 		  }
	// 		});
	// 	}
</script>

</head>
<body>
	<jsp:include page="/Web_02/headline.jsp" />
	<div class="productdesc"
		style="width: 1195px; margin-left: 400px; margin-top: 80px">
		<form action="" style="float: right;">
			<i style="margin-right: 9px;" class="fa fa-search" aria-hidden="true"></i><input
				type="text" style="margin-bottom: 3px; width: 300px; height: 40px"
				name="searchKeyWord" class="input-medium search-query"
				placeholder="Search Here…">
		</form>
		<form action="<c:url value="/specificMailServlet.do"/>">
			<ul class="nav nav-tabs" id="myTab">
				<li class="" onclick="removeChecked()"><a href="#description"><font
						size="4">全部信件</font></a></li>
				<li class="" onclick="removeChecked()"><a href="#specification"><font
						size="4">未讀信件</font></a></li>
				<li id="trashCan" style="display: none">
					<button name="allMailBotton" value="allMailBotton"
						onclick="checkDelete()" style="margin-left: 30px" type="submit">
						<i class="fa fa-trash-o fa-2x" aria-hidden="true"></i>
					</button>
				</li>
				<li id="unReadtrashCan" style="display: none">
					<button name="unReadMailBotton" value="unReadMailBotton"
						onclick="checkDelete()" style="margin-left: 30px" type="submit">
						<i class="fa fa-trash-o fa-2x" aria-hidden="true"></i>
					</button>
				</li>
			</ul>
			<div class="tab-content" style="width: 1195px;">
				<!-- 這裡是全部信件 -->
				<div class="tab-pane" id="description">
					<div id="maincontainer">
						<section id="product">
							<div class="container">
								<div class="cart-info">
									<table class="table table-striped table-bordered">
										<tr>
											<th style="width: 117px; text-align: center;" class="image"><input
												style="width: 16px; height: 16px" type="checkbox"
												name="cancel"
												onclick="checkAll(this,'allMail','announceMail')"></th>
											<th style="width: 472px; text-align: center;" class="name">收件時間</th>
											<th style="width: 336px; text-align: center;"
												class="quantity">寄件者</th>
											<th style="width: 239px; text-align: center;" class="model">主旨</th>
										</tr>
										<c:forEach var="allMail" varStatus="time" items="${allMail}">
											<tr id="allMailAddTr${time.count}">
												<td style="text-align: center;"><input
													style="width: 16px; height: 16px" type="checkbox"
													onclick="check()" id="mail${time.count}" name="allMail"
													value="${allMail.siteMailNo}"></td>
												<td onclick="allMailAddTr()"><a
													href="<c:url value="/specificMailServlet.do?allMailsiteMailNo=${allMail.siteMailNo}"/>">
														<font>${allMail.siteMailTime}</font>
												</a></td>
												<td onclick="allMailAddTr()">GroupBuy團隊</td>
												<td onclick="allMailAddTr()"><a
													href="<c:url value="/specificMailServlet.do?allMailsiteMailNo=${allMail.siteMailNo}"/>">
														<font>${allMail.siteMailCanTitle}</font>
												</a></td>
											</tr>
										</c:forEach>
										<c:forEach var="announceMail" varStatus="time"
											items="${announceMail}">
											<tr>
												<td style="text-align: center;"><input
													style="width: 16px; height: 16px" type="checkbox"
													onclick="check1()" id="announceMail${time.count}"
													name="announceMail" value="${announceMail.siteMailNo}"></td>
												<td onclick="announceMailAddTr()"><a
													href="
<%-- 													<c:url value="/specificMailServlet.do?announceMailsiteMailNo=${announceMail.siteMailNo}"/> --%>
													">
														<font>${announceMail.siteMailTime}</font>
												</a></td>
												<td onclick="announceMailAddTr()">GroupBuy團隊</td>
												<td onclick="announceMailAddTr()"><a
													href="<c:url value="/specificMailServlet.do?announceMailsiteMailNo=${announceMail.siteMailNo}"/>">
														<font>系統公告</font>
												</a></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>

				<!-- 			test -->
				<!-- 這裡是未讀信件 -->
				<div class="tab-pane" id="specification" style="text-align: center;">
					<div id="maincontainer">
						<section id="product">
							<div class="container">
								<div class="cart-info">
									<table class="table table-striped table-bordered">
										<tr>
											<th style="width: 117px; text-align: center;" class="image"><input
												style="width: 16px; height: 16px" type="checkbox"
												name="cancel1"
												onclick="checkAll2(this,'allMail1','announceMail1')"></th>
											<th style="width: 472px; text-align: center;" class="name">收件時間</th>
											<th style="width: 336px; text-align: center;"
												class="quantity">寄件者</th>
											<th style="width: 239px; text-align: center;" class="model">主旨</th>
										</tr>
										<c:forEach var="unReadMailMail" varStatus="time"
											items="${unReadMailMail}">
											<tr>
												<td style="text-align: center;"><input
													style="width: 16px; height: 16px" type="checkbox"
													onclick="checkUnRead()" id="mail${time.count}"
													name="allMail1" value="${unReadMailMail.siteMailNo}">
												</td>
												<td><a
													href="<c:url value="/specificMailServlet.do?unReadAllMailsiteMailNo=${unReadMailMail.siteMailNo}"/>">
														<font>${unReadMailMail.siteMailTime}</font>
												</a></td>
												<td>GroupBuy團隊</td>
												<td><a
													href="<c:url value="/specificMailServlet.do?unReadAllMailsiteMailNo=${unReadMailMail.siteMailNo}"/>">
														<font>${unReadMailMail.siteMailCanTitle}</font>
												</a></td>
											</tr>
										</c:forEach>
										<c:forEach var="unReadannounceMail" varStatus="time"
											items="${unReadannounceMail}">
											<tr>
												<td style="text-align: center;"><input
													style="width: 16px; height: 16px" type="checkbox"
													onclick="checkUnRead1()" id="announceMail${time.count}"
													name="announceMail1"
													value="${unReadannounceMail.siteMailNo}"></td>
												<td class="td"><a
													href="<c:url value="/specificMailServlet.do?unReadAnnounceMailsiteMailNo=${unReadannounceMail.siteMailNo}"/>">
														<font>${unReadannounceMail.siteMailTime}</font>
												</a></td>
												<td>GroupBuy團隊</td>
												<td class="td"><a
													href="<c:url value="/specificMailServlet.do?unReadAnnounceMailsiteMailNo=${unReadannounceMail.siteMailNo}"/>">
														<font>系統公告</font>
												</a></td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>

			</div>
		</form>
	</div>
	<script src="../js/js/jquery.js"></script>
	<script src="../js/js/bootstrap.js"></script>
	<script src="../js/js/respond.min.js"></script>
	<script src="../js/js/application.js"></script>
	<script src="../js/js/bootstrap-tooltip.js"></script>
	<script defer src="../js/js/jquery.fancybox.js"></script>
	<script defer src="../js/js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="../js/js/jquery.tweet.js"></script>
	<script src="../js/js/cloud-zoom.1.0.2.js"></script>
	<script type="text/javascript" src="../js/js/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../js/js/jquery.carouFredSel-6.1.0-packed.js"></script>
	<script type="text/javascript" src="../js/js/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="../js/js/jquery.touchSwipe.min.js"></script>
	<script type="text/javascript"
		src="../js/js/jquery.ba-throttle-debounce.min.js"></script>
	<script defer src="../js/js/custom.js"></script>
</body>
</html>