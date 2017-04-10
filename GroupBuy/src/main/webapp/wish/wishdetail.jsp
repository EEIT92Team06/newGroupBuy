<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wish Detail</title>
<style type="text/css">
    #div1{
       border-color:orange;
       border-style:outset; 
       width:1200px;
       height:500px;
       padding-left:30px; 
    }
    #div2{
       border-color:orange;
       border-style:outset; 
       width:1200px;
       height:150px;
       
    }
    #div3{
       width:500px;
       height:460px;
       overflow:hidden;
       float:left;
       text-align: center;
       
    }
    #div4{
       width:670px;
       height:460px;
       overflow:hidden;
       float:right;
    }
    #div5{
       float:left;
    }
    #div6{
       float:left;
       overflow:hidden;
    }
    .memberPic{
       height:75px;
       width:75px;
    }
    
</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript" src="http://malsup.github.com/chili-1.7.pack.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.cycle.all.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.easing.1.3.js"></script>
<script type="text/javascript">
$.fn.cycle.defaults.speed   = 900;
$.fn.cycle.defaults.timeout = 6000;
$(function() {
    $('#demos pre code').each(function() {
        eval($(this).text());
    });

});
</script>
</head>
<body>
<jsp:include page="../headline/top.jsp" /> 
<div id="div1">
      <c:forEach var="wishDetail" items="${wishDetail}">
         <div id="div3">
             <br><br>
             <div id="demos">
					<div id="shuffle" class="pics">
						<c:forEach var="wishPics" items="${wishPics}">
						     <img src="../pictures/${wishPics.wishPicture}" width="420" height="300" />
						</c:forEach>
					</div>
					<script>
						$('#shuffle').cycle({
							fx : 'shuffle',
							easing : 'easeOutBack',
							delay : -4000
						});
					</script>
				</div>
			 <div id="likeArea">${like}</div>
			 <br>
			 <input id="wishNo" type="hidden" name="wishNo" value="${wishDetail.wishNo}"></input>
			
             <input id="status" type="button" name="send" value="${likeOrNot}" onclick="likeForWish()"></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

             <a href="<c:url value='/creategroup/createGroup.jsp' />"><input type="button" value="我要認領" /></a><br>
         </div>
         <div id="div4">
             <br><br>
             <font size="10">${wishDetail.title}</font><br><br>
                            產品名稱 : ${wishDetail.productName}<br><br>
             ${wishDetail.content}<br><br>
                            原價 : ${wishDetail.price}<br><br>
                            來源 : ${wishDetail.source}
         </div>   
      </c:forEach>   
</div>
<br>
<c:forEach var="msgDetail" items="${msgDetail}">
<div id="div2">
<div id="div5"><img src="../pictures/${msgDetail.memberPic}" class="memberPic" /></div>
<div id="div6">
    ${msgDetail.nickName}<br>
    ${msgDetail.wishMsgContent}
</div>
</div>
</c:forEach>
<br>
    <div id="div7">
		<form action="<c:url value="/wish/wishMsg.controller" />"
			method="post">
			<textarea name="content" rows="5" cols="169"></textarea>
			<br>
			<br>
			<c:forEach var="wishDetail" items="${wishDetail}">
				<input type="hidden" name="wishNo" value="${wishDetail.wishNo}"></input>
			</c:forEach>
			<tr>
				${errorMsg.message}
				<br>
				<td id="submit"><input type="submit" name="send" value="確認送出" /></td>
			</tr>
		</form>
	</div>
	
<script type="text/javascript">
function likeForWish(){
	var wishNo = document.getElementById("wishNo").value;
	var likeArea = document.getElementById("likeArea");

	var xhr = new XMLHttpRequest();
	xhr.open("POST","wishinterest.controller",true);
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xhr.send("wishNo="+wishNo);
	xhr.onreadystatechange = function(){
		// 向伺服器提出的請求已經收到回應 
		if(xhr.readyState===4){
			// 伺服器回應成功
			if (xhr.status === 200) {
				//likeArea.innerHTML=xhr.responseText;
				result = JSON.parse(xhr.responseText);
				likeArea.innerHTML=result.like;
				document.getElementById("status").value = result.likeOrNot;
			} 
// 			alert("xhr.status: "+xhr.status);
		}
// 		alert("xhr.readyState : " + xhr.readyState);
	}
}
</script>
</body>
</html>