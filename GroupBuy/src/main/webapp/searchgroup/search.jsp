<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="<c:url value="/searchgroup/search.controller" />"
		method="post">
		<table>
			<tr>
				<td>搜尋</td>
			</tr>
			<tr>
				<td><input type="text" name="name" placeholder="請輸入 商品 關鍵字" autocomplete="off" value="${param.name}"></td>
			<tr>
				<td>
					<input type="submit" name="searchGroupAction" value="搜尋">
				</td>
			</tr>

		</table>
	</form>
	<h3>熱門搜尋</h3>
	<c:forEach var="bean" items="${selectTop2Group}">
		<table border='2' witdth='820' margin='10px'>
		<tr><td><img height='100' width='80' src='${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover'><tr><td>
		<tr><td><a href="searchDetailsServlet.controller?groupInfoNo=${bean.groupInfoNo}">團名 : ${bean.groupInfoName}</a></td></tr>
		<tr><td>類別 : ${bean.productType}</td></tr>
		<tr><td>狀態 : ${bean.groupStatus}</td></tr>
		<tr><td>創團者 : ${bean.memberName}</td></tr>
		<tr><td>評分 : ${bean.grouperCreditResult}</td></tr>
		<tr><td>No: ${bean.groupInfoNo}</td></tr>
		</table>  
	</c:forEach>
	
	<h3>推薦團購</h3>
	<c:forEach var="bean" items="${recommendGroup}">
		<table border='2' witdth='820' margin='10px'>
		<tr><td><img height='100' width='80' src='${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoNo}&type=groupCover'><tr><td>
		<tr><td><a href="searchDetailsServlet.controller?groupInfoNo=${bean.groupInfoNo}">團名 : ${bean.groupInfoName}</a></td></tr>
		<tr><td>類別 : ${bean.productType}</td></tr>
		<tr><td>狀態 : ${bean.groupStatus}</td></tr>
		<tr><td>創團者 : ${bean.memberName}</td></tr>
		<tr><td>評分 : ${bean.grouperCreditResult}</td></tr>
		<tr><td>No: ${bean.groupInfoNo}</td></tr>
		</table>  
	</c:forEach>

</body>
</html>