<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link
	href='http://fonts.useso.com/css?family=Open+Sans:400,300italic,400italic,600,600italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Crete+Round'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Crete+Round'
	rel='stylesheet' type='text/css'>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/flexslider.css" type="text/css" media="screen"
	rel="stylesheet" />
<link href="../css/jquery.fancybox.css" rel="stylesheet">
<link href="../css/cloud-zoom.css" rel="stylesheet">


<link rel="shortcut icon" href="../assets/ico/favicon.html">
</head>
<body>
<jsp:include page="/Web_02/headline.jsp"/>
	<div id="maincontainer">
		<section id="product">
			<div class="container">
				<div class="cart-info">
					<table class="table table-striped table-bordered">
						<tr>
							<th class="image"></th>
							<th class="name">寄件者</th>
							<th class="model">收件時間</th>
							<th class="quantity">主旨</th>
<!-- 							<th class="price">Unit Price</th> -->
<!-- 							<th class="total">Total</th> -->
						</tr>
						<tr>
							<td class="image"><a href="#"></a></td>
							<td class="name"><a href="#">Jeans</a></td>
							<td class="model">Purchased Product</td>
							<td class="quantity"><input type="text" size="1" value="1"
								name="quantity[40]" class="span1"></td>
						</tr>
						<tr>
							<td class="image"><a href="#"></a></td>
							<td class="name"><a href="#">Jeans</a></td>
							<td class="model">Purchased Product</td>
							<td class="quantity"><input type="text" size="1" value="1"
								name="quantity[40]" class="span1"></td>
						</tr>
					</table>
				</div>
			</div>
		</section>
	</div>

	<!-- javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../js/js/jquery.js"></script>
	<script src="../js/js/bootstrap.js"></script>
	<script src="../js/js/respond.min.js"></script>
	<script src="../js/js/application.js"></script>
	<script src="../js/js/bootstrap-tooltip.js"></script>
	<script defer src="../js/js/jquery.fancybox.js"></script>
	<script defer src="../js/js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="../js/js/jquery.tweet.js"></script>
	<script src="..js/js/cloud-zoom.1.0.2.js"></script>
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