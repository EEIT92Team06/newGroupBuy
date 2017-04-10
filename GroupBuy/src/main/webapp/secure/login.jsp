<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap-social.css" rel="stylesheet">
<link href="bootstrap.min.css" rel="stylesheet">
<link href="social-buttons.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form action="<c:url value="/loginServlet.do"/>" method="get">
 <div style="width: 800px;height: 300px;border: 1px solid;">
   <div style="float: left;border: 1px solid;width: 350px;margin:10px;">
      <h5>雿輻�沁roupBuy撣唾���餃��</h5>
      <div>
        <input type="text" name="account" value="${param.account}" placeholder="隢�頛詨�亙董��"/><label>${errorMessages.accountError}${errorMessages.loginError}</label>
      </div>
      <div>
        <input type="password" name="password" value=""placeholder="隢�頛詨�亙�蝣�"/><label>${errorMessages.passwordError}</label>
      </div>
      <div>
        <label><input name="remember" type="checkbox" checked="checked" />閮�雿���</label>
      </div>
      <div>
        <input type="submit" value="�餃��"><input type="button" value="��瘨�">
      </div>
      <div>
        <a href="">敹�閮�撖�蝣�</a>
        <a href="<c:url value="/secure/registry.jsp"/>">��鞎餉酉��</a>
       
      </div>
   </div>
   <div style="float: left;border: 1px solid;margin:10px;">
     <h5>雿輻�函洵銝��孵董���餃��</h5>
      <label>�餃�亥酉����隤�霅�嚗�蝡��喲����嚗�</label><br>
          <a class="btn  btn-social btn-google"   href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/GroupBuy/googleLoginServlet.do&response_type=code&client_id=111067976661-usc5t395cjrcsvfcsqnkvf4snj7q704c.apps.googleusercontent.com&approval_prompt=force">
          <i class="fa fa-google-plus" aria-hidden="true"></i>Google撣唾���餃��
          </a>
   </div>
  </div>
  </form>
</body>
</html>