<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:if test="${empty loginToken}">
  <c:redirect url="http://localhost:8080/GroupBuy/secure/login.jsp"/>
</c:if>
<style type="text/css">
 .td{
   border: 1px solid;
 }
</style>
</head>
<body>
	<a href="<c:url value="/overViewMailServlet.do"/>">信箱</a>
	<a href="<c:url value="/secure/logout.jsp"/>">登出</a>
	  <c:if test="${!empty mailBean}">
	  <table  style="border: 1px solid;width: 400px">
          <tr>
            <td class="td">寄件時間:${mailBean.siteMailTime}</td>
          </tr>
          <tr>
            <td class="td">主旨:${mailBean.siteMailCanTitle}</td>
          </tr>
          <tr>
            <td class="td">${mailBean.siteMailCanContent}</td>
          </tr>
        </table>
      </c:if>
      <c:if test="${!empty announcementBean}">
      <table  style="border: 1px solid;width: 400px">
          <tr>
            <td class="td">寄件時間:${announcementBean.siteMailTime}</td>
          </tr>
          <tr>
            <td class="td">主旨:${announcementBean.siteMailTitle}</td>
          </tr>
          <tr>
            <td class="td">${announcementBean.siteMailContent}</td>
          </tr>
        </table>
      </c:if>
	
</body>
</html>