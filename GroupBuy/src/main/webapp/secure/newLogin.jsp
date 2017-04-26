<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />

<title>GroupBuy團購網</title>
<style type="text/css">
.bg {
	display: none;
	position: fixed;
	width: 100%;
	height: 100%;
	background: #000;
	z-index: 2;
	top: 0;
	left: 0;
	opacity: 0.45;
}

.content {
	display: none;
	position: fixed;
	top: 30%;
	margin-top: -150px;
	z-index: 3;
	left: 50%;
	margin-left: -250px;
}
</style>
<link rel='stylesheet prefetch'
	href='https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css'>
<link rel='stylesheet prefetch'
	href='http://fonts.googleapis.com/css?family=Raleway'>
<link rel='stylesheet prefetch'
	href='http://weloveiconfonts.com/api/?family=fontawesome'>
<style class="cp-pen-styles">
[class*="fontawesome-"]:before {
	font-family: 'FontAwesome', sans-serif;
}

body {
	/*   background: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4gPHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSJncmFkIiBncmFkaWVudFVuaXRzPSJvYmplY3RCb3VuZGluZ0JveCIgeDE9IjAuMCIgeTE9IjAuNSIgeDI9IjEuMCIgeTI9IjAuNSI+PHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzI3MjgzOCIvPjxzdG9wIG9mZnNldD0iMzMuMzMzMzMlIiBzdG9wLWNvbG9yPSIjMmIyZDQxIi8+PHN0b3Agb2Zmc2V0PSI2Ni42NjY2NyUiIHN0b3AtY29sb3I9IiMzOTM1NDkiLz48c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM0MzM1NDUiLz48L2xpbmVhckdyYWRpZW50PjwvZGVmcz48cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSJ1cmwoI2dyYWQpIiAvPjwvc3ZnPiA='); */
	/*   background: -webkit-gradient(linear, 0% 50%, 100% 50%, color-stop(0%, #272838), color-stop(33.33333%, #2b2d41), color-stop(66.66667%, #393549), color-stop(100%, #433545)); */
	/*   background: -moz-linear-gradient(left, #272838, #2b2d41, #393549, #433545); */
	/*   background: -webkit-linear-gradient(left, #272838, #2b2d41, #393549, #433545); */
	/*   background: linear-gradient(to right, #272838, #2b2d41, #393549, #433545); */
	/*   font-family: 'Raleway', sans-serif; */
	
}

.groupName_input {
	font-size: 18px;
	color: #666;
	border: 2px solid #e0e1e1;
	padding: 10px;
	border-radius: 3px;
	width: 260px;
	margin: 15px;
}

form#login-form {
	background: #fff;
	width: 35em;
	margin: 2em auto;
	overflow: hidden;
	position: relative;
	-moz-border-radius: 0.3em;
	-webkit-border-radius: 0.3em;
	border-radius: 0.3em;
	-moz-box-shadow: 0 0 0.2em #000;
	-webkit-box-shadow: 0 0 0.2em #000;
	box-shadow: 0 0 0.2em #000;
}

form#login-form div.heading {
	background: #f85f64;
	color: #fff;
	text-align: center;
	text-transform: uppercase;
	font-weight: bold;
	padding: 1.5em;
}

form#login-form div.left {
	width: 50%;
	float: left;
	padding: 1.7em 1.5em 2.5em 1.5em;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

form#login-form div.right {
	width: 50%;
	float: right;
	padding: 1.7em 1.5em 2.5em 1.5em;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

form#login-form:before {
	/*   content: 'or'; */
	color: gray;
	position: absolute;
	top: 0;
	right: 0;
	left: 0;
	bottom: 0;
	margin: auto;
	height: 0.5em;
	width: 0.5em;
	left: -1.5em;
	top: 1.2em;
	z-index: 900;
}

form#login-form:after {
	content: '';
	position: absolute;
	background: rgba(128, 128, 128, 0.3);
	top: 0;
	right: 0;
	left: 0;
	bottom: 0;
	margin: auto;
	height: 7.25em;
	width: 0.1em;
	left: -0.85em;
	top: -6.8em;
	-moz-box-shadow: 0 8.8em 0 0 rgba(128, 128, 128, 0.3);
	-webkit-box-shadow: 0 8.8em 0 0 rgba(128, 128, 128, 0.3);
	box-shadow: 0 8.8em 0 0 rgba(128, 128, 128, 0.3);
}

div.left label {
	display: inline-block;
	color: gray;
	font-size: 1.1em;
	margin-top: 0.6em;
}

div.left input[type="email"], div.left input[type="password"] {
	border: 0.1em solid #dfdfdf;
	padding: 1em;
	margin: 0.6em 0;
	-moz-border-radius: 0.2em;
	-webkit-border-radius: 0.2em;
	border-radius: 0.2em;
	-moz-box-shadow: 0 0 0.2em rgba(223, 223, 223, 0.2);
	-webkit-box-shadow: 0 0 0.2em rgba(223, 223, 223, 0.2);
	box-shadow: 0 0 0.2em rgba(223, 223, 223, 0.2);
	-moz-transition: all 0.15s ease-in-out;
	-o-transition: all 0.15s ease-in-out;
	-webkit-transition: all 0.15s ease-in-out;
	transition: all 0.15s ease-in-out;
}

div.left input[type="email"]:focus, div.left input[type="password"]:focus
	{
	outline: none;
	border: 0.1em solid #bdbdbd;
}

div.left input[type="submit"] {
	background: #f85f64;
	color: #fff;
	outline: none;
	text-transform: uppercase;
	padding: 1.2em;
	overflow: hidden;
	border: none;
	letter-spacing: 0.1em;
	margin: 0.5em 0;
	font-weight: bold;
	-moz-border-radius: 0.4em;
	-webkit-border-radius: 0.4em;
	border-radius: 0.4em;
	-moz-transition: all 0.15s ease-in-out;
	-o-transition: all 0.15s ease-in-out;
	-webkit-transition: all 0.15s ease-in-out;
	transition: all 0.15s ease-in-out;
}

div.left input[type="submit"]:hover {
	background: rgba(248, 95, 100, 0.8);
}

div.right div.connect {
	color: gray;
	font-size: 1.1em;
	text-align: center;
	margin-top: 10px
}

div.right a {
	display: inline-block;
	font-size: 1.5em;
	text-decoration: none;
	color: white;
	width: 9em;
	padding: 0.55em 0.3em;
	clear: both;
	text-align: center;
	margin: 0.5em 0.1em;
	-moz-border-radius: 0.3em;
	-webkit-border-radius: 0.3em;
	border-radius: 0.3em;
}

div.right a.facebook {
	background: #3a589a;
	margin-top: 0.8em;
	-moz-transition: all 0.15s ease-in-out;
	-o-transition: all 0.15s ease-in-out;
	-webkit-transition: all 0.15s ease-in-out;
	transition: all 0.15s ease-in-out;
}

div.right a.facebook:hover {
	background: rgba(58, 88, 154, 0.8);
}

div.right a.twitter {
	background: #4099ff;
	-moz-transition: all 0.15s ease-in-out;
	-o-transition: all 0.15s ease-in-out;
	-webkit-transition: all 0.15s ease-in-out;
	transition: all 0.15s ease-in-out;
}

div.right a.twitter:hover {
	background: rgba(64, 153, 255, 0.8);
}

div.right a.google-plus {
	background: #e9544f;
	-moz-transition: all 0.15s ease-in-out;
	-o-transition: all 0.15s ease-in-out;
	-webkit-transition: all 0.15s ease-in-out;
	transition: all 0.15s ease-in-out;
}

div.right a.google-plus:hover {
	background: rgba(233, 84, 79, 0.8);
}
.submit{
	background: #f85f64;
	color: #fff;
	outline: none;
	text-transform: uppercase;
	padding: 1.2em;
	overflow: hidden;
	border: none;
	letter-spacing: 0.1em;
	margin: 0.5em 0;
	font-weight: bold;
	-moz-border-radius: 0.4em;
	-webkit-border-radius: 0.4em;
	border-radius: 0.4em;
	-moz-transition: all 0.15s ease-in-out;
	-o-transition: all 0.15s ease-in-out;
	-webkit-transition: all 0.15s ease-in-out;
	transition: all 0.15s ease-in-out;
}
</style>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap-modalmanager.js"></script>
<script src="../js/bootstrap-modal.js"></script>
<script type="text/javascript">
	$(function() {
		// 		$('.click').click(function() {
		// 			$('.bg').css({
		// 				'display' : 'block'
		// 			});
		// 			$('.content').css({
		// 				'display' : 'block'
		// 			});
		// 		});
		// 		$('.bg').click(function() {
		// 			//$('.bg').css({'display':'none'});
		// 			//$('.content').css({'display':'none'});
		// 		});
		$('.click1').click(function() {
			$('.bg').fadeIn(200);
			$('.content').fadeIn(400);
		});
		$('.bg').click(function() {
			$('.bg').fadeOut(200);
			$('.content').fadeOut(200);
		});
	});
</script>

</head>
<script type="text/javascript">
	function loginCheck() {
		var accountError = document.getElementById('accountError');
		var passwordError = document.getElementById('passwordError');
		var email = document.getElementById('email').value;
		var password = document.getElementById('pass').value;
		var xhr = new XMLHttpRequest();
		var queryString = "account=" + email + "&password=" + password;
		var url = "/GroupBuy/loginServlet.do?" + queryString;
		xhr.open("GET", url, true);
		xhr.send();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					result = JSON.parse(xhr.responseText);
					if(result.accountError != null && result.passwordError != null){
						accountError.innerHTML = "";
						passwordError.innerHTML ="";
						accountError.innerHTML = result.accountError;
						passwordError.innerHTML = result.passwordError;
					}else if(result.accountError == null && result.passwordError != null){
						accountError.innerHTML = "";
						passwordError.innerHTML = result.passwordError;
					}else if(result.passwordError == null &&result.accountError != null){
						passwordError.innerHTML ="";
						accountError.innerHTML = result.accountError;

					}else if(result.loginError !=null){
						accountError.innerHTML = "";
						passwordError.innerHTML ="";
						accountError.innerHTML =result.loginError;
					}else if(result.indexUrl!=null){
						//重新導向(成功登入)
						location.replace(result.indexUrl);
					}else if(result.banUrl!=null){
						//導向ban的畫面
						location.replace(result.banUrl);
					}else if(result.unFinishLogin!=null){
						location.replace(result.unFinishLogin);
					}else if(result.backStageUrl!=null){
						location.replace(result.backStageUrl);
					}
				}
			}
		}
	}
</script>
<body>

	<!-- 	<div class="click">点击这里</div> -->
	<!-- 	<div class="click1">效果增强版的</div> -->
	<div class="bg"></div>
	<div class="content">
		<form id="login-form">
			<div class="heading">GroupBuy會員登入</div>
			<div class="left">
				<label for="email">GroupBuy帳號</label><font size="2" color="red"
					style="margin-left: 15px" id="accountError"></font><br /> <input
					type="email" name="account" id="email" value="${param.account}">
				<br /> <label for="password">密碼</label><font size="2" id="passwordError"
					style="margin-left: 7px;"  color="red"></font><br /> <input type="password"
					name="password" id="pass" value="" /><br /> <input
					style="" class="submit" type="button" value="Login"
					onclick="loginCheck()" /> <a
					href="<c:url value="/secure/newRegistry.jsp"/>"
					style="color: gray; text-decoration: none; margin-top: 10px;margin-left: 10px"
					>免費註冊</a>
			</div>
			<div class="right">
				<div class="connect">透過Google帳號登入</div>
				<span></span><br />
				<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/GroupBuy/googleLoginServlet.do&response_type=code&client_id=111067976661-usc5t395cjrcsvfcsqnkvf4snj7q704c.apps.googleusercontent.com&approval_prompt=force"
				><img style="width: 110px"  src="pictures/google.png">	
				</a>
			</div>
		</form>
	</div>
</body>
</html>