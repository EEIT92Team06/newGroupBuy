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
<%-- <jsp:include page="/Backstage/backstageheadline.jsp"/> --%>
		<form action="<c:url value="/Backstage/banGroupServlet" />"method="post">
		<table>
			<tr>
				<td>選擇封鎖團</td>
			</tr>
			<c:forEach var='bean' items='${Allgroup}'> 
			<tr>
				<c:if test="${bean.groupStatus != '封鎖'}">
					<td><input type="checkbox" name="checkbox" value=${bean.groupInfoNo}></td>
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
			<tr>
				<td><input type="submit" name="sendMail" value="送出"></td>
			</tr>
		</table>
	</form>
</body>
</html>