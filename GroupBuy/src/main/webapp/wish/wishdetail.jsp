<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="<c:url value='/pictures/groupicon.ico'/>" type="image/x-icon" />
<meta charset="utf-8">
<title>SimpleOne - A Responsive Html5 Ecommerce Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="../myWeb_01Main/css/bootstrap.css" rel="stylesheet">
<link href="../myWeb_01Main/css/bootstrap-responsive.css" rel="stylesheet">
<link href="../myWeb_01Main/css/style.css" rel="stylesheet">
<link href="../myWeb_01Main/css/flexslider.css" type="text/css" media="screen" rel="stylesheet"  />
<link href="../myWeb_01Main/css/jquery.fancybox.css" rel="stylesheet">
<link href="../myWeb_01Main/css/cloud-zoom.css" rel="stylesheet">

<link rel="shortcut icon" href="../myWeb_01Main/assets/ico/favicon.html">
<style>
    #leftImg{
       text-align: center;
    }
</style>
<script src="<c:url value='../js/jquery-3.1.1.min.js'/>"></script>
<script src="<c:url value='../js/layer/layer.js'/>"></script>
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
                <li><a class="comare" href="<c:url value='/creategroup/createGroup.jsp?wishNo=${wishDetail.wishNo}' />" >認養此商品</a>
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
                    <pre>${wishDetail.content}</pre><br>
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
					<form class="form-vertical">
						<fieldset>
							<div class="control-group">
								<label class="control-label">檢舉選項</label> <input
									type="hidden" value="${wishDetail.wishNo}" name="reportTarget" />
								<div class="controls">
									<select name="reportTypeNo">
										<option value="5">檢舉許願標題</option>
										<option value="6">檢舉許願照片</option>
										<option value="7">檢舉許願池留言</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">檢舉內容</label>
								<div class="controls">
									<textarea name="reportContent" rows="3" class="span3"></textarea>
								</div>
							</div>
						</fieldset>
						<input id="sendReport" class="btn btn-orange" type="button"
							value="送出檢舉" style="margin-left: 10px;"/>
					</form>
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


<!-- javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../myWeb_01Main/js/jquery.js"></script>
<script src="../myWeb_01Main/js/bootstrap.js"></script>
<script src="../myWeb_01Main/js/respond.min.js"></script>
<script src="../myWeb_01Main/js/application.js"></script>
<script src="../myWeb_01Main/js/bootstrap-tooltip.js"></script>
<script defer src="../myWeb_01Main/js/jquery.fancybox.js"></script>
<script defer src="../myWeb_01Main/js/jquery.flexslider.js"></script>
<script type="text/javascript" src="../myWeb_01Main/js/jquery.tweet.js"></script>
<script  src="../myWeb_01Main/js/cloud-zoom.1.0.2.js"></script>
<script  type="text/javascript" src="../myWeb_01Main/js/jquery.validate.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.carouFredSel-6.1.0-packed.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.mousewheel.min.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript"  src="../myWeb_01Main/js/jquery.ba-throttle-debounce.min.js"></script>
<script defer src="../myWeb_01Main/js/custom.js"></script>
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
$("#sendReport").click(function(){
	var reportTarget=$(this).parents("form").find("input[name='reportTarget']").val();
	var reportTypeNo=$(this).parents("form").find("select[name='reportTypeNo']").val();
	var reportContent=$(this).parents("form").find("textarea[name='reportContent']").val();

	$.get("${pageContext.request.contextPath}/reportajax",{"reportTarget":reportTarget,"reportTypeNo":reportTypeNo,"reportContent":reportContent},function(data){
		var reportAlert=layer.alert(data, {
			  skin: 'layui-layer-molv' //样式类名
			  ,closeBtn: 0
			},function(){
				layer.close(reportAlert);
				layer.close(reportOpen);					
			});
	});
});	
</script>
</body>
</html>