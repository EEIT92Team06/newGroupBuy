<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>
<style type="text/css">
 .full{
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            margin-left: 0;
            margin-top: 150px;
            text-align: center;
        }
</style>
</head>

<body>
<jsp:include page="/headline.jsp"></jsp:include>
	<jsp:include page="/secure/newLogin.jsp"/>
<div class="full">   
   <h1><font size="14">${sessionScope.successMessage.createSuccess}</font></h1>
   <h2><a href="<c:url value="/eeit9212/grouprecord/mycreatedgroupinfo.controller"/>">看看你創的團購</a></h2>
</div>

</body>
</html>