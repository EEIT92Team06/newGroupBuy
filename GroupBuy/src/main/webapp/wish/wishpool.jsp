<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wish Pool</title>
</head>
<body>
<jsp:include page="/Web_02/headline.jsp"></jsp:include>
	<tr>
		<td><a href="<c:url value='/wish/mywish.jsp' />">．我的許願</a></td>
		<td><a href="<c:url value='/wish/wishform.jsp' />">．發起許願</a></td>
	</tr>
	<hr>
	<form action="<c:url value='/wish/wishsearch.controller'/>" method="get">
		<select name="productType">
			<option value="0">全部</option>
			<option value="1">生鮮食品</option>
			<option value="2">團購美食</option>
			<option value="3">保養美妝</option>
			<option value="4">服飾配件</option>
			<option value="5">育兒親子</option>
			<option value="6">居家生活</option>
			<option value="7">休閒娛樂</option>
			<option value="8">3C家電</option>
		</select> 
		<input id="key" type="text" name="keyWord" value=""></input>
		<input type="submit" name="search" value="搜尋" onclick="warning()">
		
		<script>
		   function warning(){
			     if(document.getElementById("key").value==""){
			    	 alert("請輸入搜尋內容!");
			     }
		   }
		</script>
	</form>
	<br>
	<c:forEach var='wish' items="${wishCollection}">
	<form action="<c:url value="/wish/wishdetail.controller" />" method="get">
		<table border='1'>
			<div>
				<tr>
					<td><img src="../pictures/${wish.coverPic}" width="300" height="230" /></td>
					<td><a>${wish.title}</a><br> 
					    <a>${wish.price}</a><br>
					    <input type="hidden" name="wishNo" value="${wish.wishNo}"></input>
					    <input type="submit" name="detail" value="詳細資訊" />
					</td>
				</tr>
			</div>
		</table>
		<br>
		</form>
	</c:forEach>
	


</body>
</html>