<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="<c:url value='/eeit9212/grouprecord/mycreatedgroupinfo.controller'></c:url>">我創的團</a>
	<a href="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller'></c:url>">我參加的團</a>
	<a href="<c:url value='/creategroup/createGroup.jsp'></c:url>">創團</a>
</body>
</html>