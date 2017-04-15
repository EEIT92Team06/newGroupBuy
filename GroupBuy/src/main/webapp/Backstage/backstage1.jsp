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
		<form action="<c:url value="/Backstage/SendMailServlet" />"
			method="post">
		<table>
			<tr>
				<td>系統公告</td>
			</tr>
			<tr>
				<td>${errorMsg.errorMsg}</td>
			</tr>
			<tr>
				<td><input type="text" name="title" placeholder="請輸入標題" autocomplete="off" value="${param.title}"></td>
			</tr>
			<tr>
				<td><textarea cols="95" id="siteMailId" placeholder="請輸入內容" rows="5" name="siteMail"></textarea></td>
			</tr>
			<tr>
				<td>選擇對象會員</td>
			</tr>
		<c:forEach var='bean' items='${members}'> 
		<tr>
			<td><input type="checkbox" name="checkbox" value=${bean.memberNo}></td>
			<td>${bean.memberNo}</td>
			<td>${bean.memberName}</td> 
		</tr>
		</c:forEach>
			<tr>
				<td><input type="submit" name="sendMail" value="送出"></td>
			</tr>
		
		</table>
		</form>
		
</body>
</html>