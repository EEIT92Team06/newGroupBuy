<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<title>SimpleOne - A Responsive Html5 Ecommerce Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- <link href='http://fonts.useso.com/css?family=Open+Sans:400,300italic,400italic,600,600italic' rel='stylesheet' type='text/css'> -->
<!-- <link href='http://fonts.useso.com/css?family=Crete+Round' rel='stylesheet' type='text/css'> -->
<!-- <link href='http://fonts.useso.com/css?family=Crete+Round' rel='stylesheet' type='text/css'> -->
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/bootstrap-responsive.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/flexslider.css" type="text/css" media="screen" rel="stylesheet"  />
<link href="../css/jquery.fancybox.css" rel="stylesheet">
<link href="../css/cloud-zoom.css" rel="stylesheet">

<link rel="shortcut icon" href="../assets/ico/favicon.html">
<style>
    #leftImg{
       text-align: center;
    }
</style>
</head>
<body>
<jsp:include page="/headline.jsp"></jsp:include>
<br><br>
<div id="maincontainer">
  <section id="product">
    <div class="container">      
      <!-- Product Details-->
      <div class="row" style="margin-left: 130px;">
       <!-- Left Image-->
        <div class="span5">
          <ul class="thumbnails mainimage" style="padding-left: 0px;">
            <c:forEach var="wishPics" items="${wishPics}">
                  <li class="span5">
                     <a  rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" class="thumbnail cloud-zoom" style="width:420px ;height :300px" href="../pictures/${wishPics.wishPicture}">
                          <img src="../pictures/${wishPics.wishPicture}" alt="" title="" style="width:420px ;height :300px">
                     </a>
                  </li>         
			</c:forEach>
          </ul>
          <div id="leftImg"><span>點擊選取圖片</span></div>
<!--           =============小圖===============         -->
          <ul class="thumbnails mainimage" style="padding-left: 0px;">
             <c:forEach var="wishPics" items="${wishPics}">
                <li class="producthtumb">
                  <a class="thumbnail" >
                     <img  src="../pictures/${wishPics.wishPicture}" alt="" title="">
                  </a>
                </li>
             </c:forEach>
        
          </ul>
        </div>
        
<!--         =================圖片&右邊明細區塊分隔線================= -->
         <!-- Right Details-->
        <c:forEach var="wishDetail" items="${wishDetail}">
        <div class="span7">
          <div class="row">
            <div class="span7" style="margin-left: 120px;">
              <h1 class="productname"><span class="bgnone">${wishDetail.title}</span></h1>
              <div class="productprice">
                <div class="productpageprice">
                  <span class="spiral"></span>$${wishDetail.price}</div>
                <ul class="rate" style="padding-left: 0px;">
                  <li class="on"></li>
                  <li class="on"></li>
                  <li class="on"></li>
                  <li class="on"></li>
                  <li class="on"></li>
                </ul><br>
                <div id="likeArea">${like}</div>
              </div>
              <br>
              <ul class="productpagecart" style="padding-left: 0px;">
                <input id="wishNo" type="hidden" name="wishNo" value="${wishDetail.wishNo}"></input>
                <li><a id="status" name="send" class="wish" href="#" onclick="likeForWish()" value="">${likeOrNot}</a>
                </li>
                <li><a class="comare" href="<c:url value='/creategroup/createGroup.jsp' />" >認養此商品</a>
                </li>
              </ul>
         <!-- Product Description tab & comments-->
         <div class="productdesc">
                <ul class="nav nav-tabs" id="myTab" style="width: 532px;">
                  <li class="active"><a href="#description">簡介</a>
                  </li>
                  <li><a href="#specification">來源</a>
                  </li>
                  <li><a href="#review">檢舉許願池</a>
                  </li>
                </ul>
                <div class="tab-content" style="width: 532px;">
                  <div class="tab-pane active" id="description">
                    <h2>${wishDetail.title}</h2>
                    ${wishDetail.content}<br>
                    <br>
                  </div>
                  <div class="tab-pane " id="specification">
                    <ul class="productinfo">
                      ${wishDetail.source}
                    </ul>
                  </div>
                  <div class="tab-pane " id="specification">
                    <ul class="productinfo">
                      ${wishDetail.source}
                    </ul>
                  </div>
                  <div class="tab-pane" id="review">
                      <button id="report">檢舉</button>
                      <div id="reportDiv" style="display: none">
			          <input id="reportTarget" type="hidden" value="${wishDetail.wishNo}" name="reportTarget"/>
			            <div>
				          <select id="reportTypeNo" name="reportTypeNo">
					         <option value="5">檢舉許願標題</option>
					         <option value="6">檢舉許願照片</option>
					         <option value="7">檢舉許願池留言</option>
				          </select>
			            </div>
                     </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        </c:forEach>
      </div>
    </div>
  </section>
  <!--  Related Products-->
  <section id="related" class="row">
    <div class="container">
      <h1 class="heading1" style="width: 1000px;margin-left: 150px;"><span class="maintext">留言專區</span><span class="subtext"> See All Comments</span></h1>
      <section class="commentsblog">
	<ul class="comments" style="margin-left: 0px;padding-left: 0px;">
	<c:forEach var="msgDetail" items="${msgDetail}">
		<li style="margin-left: 150px;"><a class="avtar thumbnail">
		<img src="../pictures/${msgDetail.memberPic}"alt=""></a>
			<div class="commentdetail">
				<p class="blogtitle" href="#">${msgDetail.nickName}</p>
				<div >
				<p>${msgDetail.wishMsgContent}</p>
				</div>
			</div></li>
	</c:forEach>
	</ul><br>
	<div id="div7">
		<form action="<c:url value="/wish/wishMsg.controller" />" style="margin-left: 0px; method="post">
			<textarea name="content" rows="3" cols="500" style="margin-top: 0px;margin-bottom: 0px;height: 86px;width: 1013.6px;margin-left: 150px;"></textarea>
			<br>
			<br>
			<c:forEach var="wishDetail" items="${wishDetail}">
				<input type="hidden" name="wishNo" value="${wishDetail.wishNo}"></input>
			</c:forEach>
			<tr>
				${errorMsg.message}
				<td id="submit"><input type="submit" class="btn btn-orange" name="send" value="留言" style="margin-left: 150px;" /></td>
			</tr>
		</form>
	</div>
	</section>
    </div>
  </section>
</div>
	
	
<!-- Footer -->
<footer id="footer">
  <section class="footerlinks">
    <div class="container">
      <div class="info">
        <ul>
          <li><a href="#">Privacy Policy</a>
          </li>
          <li><a href="#">Terms &amp; Conditions</a>
          </li>
          <li><a href="#">Affiliates</a>
          </li>
          <li><a href="#">Newsletter</a>
          </li>
        </ul>
      </div>
      <div id="footersocial">
        <a href="#" title="Facebook" class="facebook">Facebook</a>
        <a href="#" title="Twitter" class="twitter">Twitter</a>
        <a href="#" title="Linkedin" class="linkedin">Linkedin</a>
        <a href="#" title="rss" class="rss">rss</a>
        <a href="#" title="Googleplus" class="googleplus">Googleplus</a>
        <a href="#" title="Skype" class="skype">Skype</a>
        <a href="#" title="Flickr" class="flickr">Flickr</a>
      </div>
    </div>
  </section>
  <a id="gotop" href="#">Back to top</a>
</footer>
<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/respond.min.js"></script>
<script src="../js/application.js"></script>
<script src="../js/bootstrap-tooltip.js"></script>
<script defer src="../js/jquery.fancybox.js"></script>
<script defer src="../js/jquery.flexslider.js"></script>
<script type="text/javascript" src="../js/jquery.tweet.js"></script>
<script  src="../js/cloud-zoom.1.0.2.js"></script>
<script  type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript"  src="../js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script type="text/javascript"  src="../js/jquery.mousewheel.min.js"></script>
<script type="text/javascript"  src="../js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript"  src="../js/jquery.ba-throttle-debounce.min.js"></script>
<script defer src="../js/custom.js"></script>
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
				document.getElementById("status").innerHTML = result.likeOrNot;
			} 
// 			alert("xhr.status: "+xhr.status);
		}
// 		alert("xhr.readyState : " + xhr.readyState);
	}
}
</script>
<!-- 以下是檢舉的js -->
<script>
$(function() {
	
var reportOpen;
$("#report").click(function(){
	reportOpen = layer.open({
		type : 1,
		title:'檢舉',
		skin : 'layui-layer-rim', //加上边框
		area : [ '400px', '250px' ], //宽高
		content : $("#reportDiv")
	});
});
$("#sendReport").click(function(){
	$.get("<%=request.getContextPath()%>/reportajax", {
				"reportTarget" : $("#reportTarget").val(),
				"reportTypeNo" : $("#reportTypeNo").val(),
				"reportContent" : $("#reportContent").val()
			}, function(data) {
				var reportAlert = layer.alert(data, {
					skin : 'layui-layer-molv' //样式类名
					,
					closeBtn : 0
				}, function() {
					layer.close(reportAlert);
					layer.close(reportOpen);
				});
			});
		});
	});
</script>
</body>
</html>