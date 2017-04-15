<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.td {
	border: 1px solid;
}

</style>
<script type="text/javascript">
  function checkAll(obj,allMail,announceMail){
	  var checkboxs=document.getElementsByName("allMail");
	   for(var i=0;i<checkboxs.length;i++){
		   checkboxs[i].checked = obj.checked;
		   } 
	  var checkboxs1=document.getElementsByName("announceMail");
	  for(var i=0;i<checkboxs1.length;i++){
		  checkboxs1[i].checked = obj.checked;
	  }
  } 

</script>

</head>
<body>
    <jsp:include page="/Web_02/headline.jsp"/>
	<form action="<c:url value="/specificMailServlet.do"/>"><input type="text" name="searchKeyWord"><input type="submit" value="搜尋"></form>
	<form action="<c:url value="/specificMailServlet.do"/>">
		<table style="border: 1px solid;" id="table1">
			<thead>
				<tr>
					<th class="td">
					  <input type="checkbox" onclick="checkAll(this,'allMail','announceMail')"><font>全選</font>
					</th>
					<th class="td"></th>
					<th class="td">
					  <a href="<c:url value="/overViewMailServlet.do"/>"><input type="button" value="全部"></a>
                      <input type="submit" name="unRead" value="未讀">
					</th>
				<tr>
				<tr>
					<th class="td"></th>
					<th class="td">收件時間</th>
					<th class="td">主旨</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="allMail" varStatus="time" items="${allMail}">
					<tr>
						<td class="td"><input type="checkbox" id="mail${time.count}" name="allMail" value="${allMail.siteMailNo}"></td>
						<td class="td"><a
							href="<c:url value="/specificMailServlet.do?allMailsiteMailNo=${allMail.siteMailNo}"/>">
								<font>${allMail.siteMailTime}</font>
						</a></td>
						<td class="td"><a
							href="<c:url value="/specificMailServlet.do?allMailsiteMailNo=${allMail.siteMailNo}"/>">
								<font>${allMail.siteMailCanTitle}</font>
						</a>
						<td>
					</tr>
				</c:forEach>
				<c:forEach var="announceMail" varStatus="time" items="${announceMail}">
					<tr>
						<td class="td"><input type="checkbox" id="announceMail${time.count}" name="announceMail" value="${announceMail.siteMailNo}"></td>
						<td class="td"><a
							href="<c:url value="/specificMailServlet.do?announceMailsiteMailNo=${announceMail.siteMailNo}"/>">
								<font>${announceMail.siteMailTime}</font>
						</a></td>
						<td class="td"><a
							href="<c:url value="/specificMailServlet.do?announceMailsiteMailNo=${announceMail.siteMailNo}"/>">
								<font>系統公告</font>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${!empty allMail ||! empty announceMail}">
			<tr>
				<td><input type="submit" value="刪除"></td>
			</tr>
		</c:if>
	</form>
</body>
</html>