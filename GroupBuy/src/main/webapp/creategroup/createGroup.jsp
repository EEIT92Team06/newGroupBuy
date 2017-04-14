<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/jquery-ui.min.css"/>
<link rel="stylesheet" href="../css/jquery-ui.structure.min.css"/>
<link rel="stylesheet" href="../css/jquery-ui.theme.min.css"/>
<style type="text/css">
  .thumb{
 	    height:150px;margin:20px;
 	    	      }
  .thumb1{
         height:150px;margin:20px;
  }  
</style>
</head>
<body>
<jsp:include page="/Web_02/headline.jsp"></jsp:include>
<script type="text/javascript" src="../js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

<script>

$(function(){
	  $("#datepicker").datepicker({dateFormat:'yy-mm-dd',minDate : '-0'});//minDate表示最小可以選的日期為哪一天, maxDate則表最大可選天數
})
  var productCount=0;
  var ProductPriceCount=0;
  var tr;
  function addProduct(){
	  if(productCount<10){
	  productCount++;
	  ProductPriceCount++; 
	  tr = document.getElementById("table").insertRow(6+productCount-1);//表示在table的第六個tr後再加tr
	  tr.setAttribute("id","lastTr"+productCount);
	  td = tr.insertCell(tr.cells.length)//建立新的td 而Tr.cells.length就是這個tr目前的td數
	  var product=document.createTextNode("*品項:");
	  var price=document.createTextNode("*價格:");
	  td.innerHTML="<label>*品項:</label><input type='text' name='productName'><label>*價格:</label><input  type='text' name='productPrice'>";
	  }
  }
  function removeProduct(){
	 
	  var tablerow=document.getElementById("table").rows.length;
	  if(productCount>0){
        document.getElementById("table").deleteRow(5+productCount)
        productCount--;
        ProductPriceCount--;
	  }

  }

  var count=0;
  function fileViewer(){
	  document.getElementById("newFile").innerHTML="";
	  var files = document.getElementById("file1").files
      var filelength = files.length;
      for (var i = 0; i < filelength; i++) {
          var reader = new FileReader();
          reader.readAsDataURL(files[i])
          reader.onload = function (e) {
              var filecontent = e.target.result;          
              imageobj = document.createElement("img");
              imageobj.setAttribute("src", filecontent);
              imageobj.setAttribute("class", "thumb");
              document.getElementById("newFile").appendChild(imageobj);
          }
      }
  }
  function fileViewer1(){
	  document.getElementById("newFile1").innerHTML="";
	  var files = document.getElementById("file2").files
      var filelength = files.length;
      for (var i = 0; i < filelength; i++) {
          var reader = new FileReader();
          reader.readAsDataURL(files[i])
          reader.onload = function (e) {
              var filecontent = e.target.result;          
              imageobj = document.createElement("img");
              imageobj.setAttribute("src", filecontent);
              imageobj.setAttribute("class", "thumb");
              document.getElementById("newFile1").appendChild(imageobj);
          }
      }
  }

  
</script>

<form action="<c:url value="/createGroupServlet.do"/>"  enctype="multipart/form-data" method="post">

    <table id="table">
      <tbody>   
      <tr>
          <td>*團名:
            <input type="text" name="groupName">${errorMessages.groupNameError}
          </td>
          
        </tr>
      <tr>
          <td>*類型:
            <select  name="productType">
              <option value="freshFood">生鮮食品</option>
              <option value="delicious">團購美食</option>
              <option value="makeUp">保養美妝</option>
              <option value="clothes">服飾配件</option>
              <option value="kid">育兒親子</option>
              <option value="life">居家生活</option>
              <option value="relax">休閒娛樂</option>
              <option value="technology">3C家電</option>
            </select>
          
        </tr>
      <tr>
          <td>*數量下限:
            <input type="text" name="productNum" >${errorMessages.productNumError}
          </td>
           
        </tr>
       <tr>
          <td>*結束日期:<input id="datepicker" name="deadLine" type="text" />
          	  <select name="hours">
          	    <c:forEach var="hour" begin="1" end="23" step="1"> 
          	     <c:choose>	     
          	       <c:when test="${hour<10}">
                     <option value="${hour}">0${hour}</option>
                   </c:when>
                   <c:otherwise>
                      <option value="${hour}">${hour}</option>
                   </c:otherwise>
                 </c:choose>
                </c:forEach>
              </select>點
              <select name="minutes">
          	    <c:forEach var="minute" begin="1" end="59" step="1">
          	      <c:choose>	     
          	       <c:when test="${minute<10}">
                     <option value="${minute}">0${minute}</option>
                   </c:when>
                   <c:otherwise>
                      <option value="${minute}">${minute}</option>
                   </c:otherwise>
                 </c:choose>
                </c:forEach>
              </select>分
          ${errorMessages.deadLineError}</td>
        </tr>
       <tr>
          <td>
            <input type="button"  value="新增品項"  onclick="addProduct()">
             <input type="button"  value="移除品項"  onclick="removeProduct()">
          </td>
        </tr>
        <tr id="productTr">
          <td>*品項:<input type="text" name="productName">*價格:<input type="text" name="productPrice">
          ${errorMessages.productPriceError}${errorMessages.productError}
          </td>
        </tr>
        <tr>
          <td>
          <label>封面圖片</label>
            <input type="file"name="cover" id="file2" accept="image/*" onchange="fileViewer1()" >
           ${errorMessages.pictureError}
          </td>
        </tr>
        <tr>
          <td id="newFile1"></td>
        </tr>
        <tr>
          <td>
              <label>產品圖片</label>
              <input type="file" multiple="mutiple"name="picture" id="file1" accept="image/*" onchange="fileViewer()" >
          </td>
          
        </tr>
        <tr>
          <td id="newFile"></td>   
        </tr>
        <tr>
          <td>
            <label>商品資訊:</label>
          </td>
        </tr>
        <tr>
          <td>
           <textarea rows="8" cols="50" name="productInfo"></textarea>
          </td> 
        </tr>
        <tr>
          <td>
           <label>*匯款資訊:</label>
           <input type="text" name="bankAccount">${errorMessages.bankAccountError}<br>
          </td>
        </tr>
        <tr> 
          <td>
           <label>*寄送方式:</label>
           <select name="shippingWay">
             <option value="post">郵局</option>
             <option value="blackCat">黑貓</option>
           </select><br>
          </td>
         </tr>
         <tr>
           <td>
             <input type="submit" value="確認團購"/>
           </td>
         </tr>  
     </tbody>   
    </table>  
 </form>
</body>
</html>