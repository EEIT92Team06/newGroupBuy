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
	<form>
		<table>
			<tr>
				<td>選擇水桶會員</td>
			</tr>
			<c:forEach var='bean' items='${Allreports}'>
				<tr>
					<td>${bean.reportNo}</td>
					<td>${bean.memberName}</td>
					<td>${bean.reportType}</td>
					<c:if test="${bean.reportTypeNo <5}">
						<td><a href="<c:url value='/Backstage/backstage3.jsp' />">${bean.reportTarget}</a></td>
					</c:if>
					<c:if test="${bean.reportTypeNo >8}">
						<td><a href="<c:url value='/Backstage/backstage2.jsp' />">${bean.reportTarget}</a></td>
					</c:if>
					<!-- 許願池 -->
					<c:if test="${bean.reportTypeNo >=5 && bean.reportTypeNo <=8}">
						<td><a href="<c:url value='/Backstage/wishbackstage.jsp' />">${bean.reportTarget}</a></td>
					</c:if>

					<td>${bean.reportContent}</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>