<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>許願池管理</title>
</head>
<body>
		<form action="<c:url value="/Backstage/BanWishServlet" />"method="post">
		<table>
			<tr>
				<td>刪除違規許願</td>
			</tr>
			<c:forEach var='bean' items='${AllWish}'> 
			<tr>
			    <td><input type="checkbox" name="checkbox" value=${bean.wishNo}></td>
				<td>${bean.wishNo} ,</td>
				<td>${bean.title} ,</td>
				<td>${bean.productName} </td>
			</tr>
		</c:forEach>
			<tr>
				<td><input type="submit" name="sendMail" value="送出"></td>
			</tr>
		</table>
	</form>
</body>
</html>