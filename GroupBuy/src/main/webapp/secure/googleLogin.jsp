<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />
</head>
<body>
    <table>
      <tr><td>User ID</td><td>${googleData.id}</td></tr>
      <tr><td>Name</td><td>${googleData.email}</td></tr>
      <tr><td>Email</td><td>${googleData.name}</td></tr>
<!--       以下兩行因為有底線所以無法顯示，但是測試用故不先解決 -->
      <tr><td>GivenName</td>${googleData.given_name}<td></td></tr>
      <tr><td>FamilyName</td>${googleData.family_name}<td></td></tr>
      <tr><td>Gender</td><td>${googleData.gender}</td></tr>
    </table>
</body>
</html>