<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>searchDetails</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="../myWeb_01Main/css/bootstrap.css" rel="stylesheet">
<link href="../myWeb_01Main/css/bootstrap-responsive.css" rel="stylesheet">
<link href="../myWeb_01Main/css/style.css" rel="stylesheet">
<link href="../myWeb_01Main/css/flexslider.css" type="text/css" media="screen"
	rel="stylesheet" />
<link href="../myWeb_01Main/css/jquery.fancybox.css" rel="stylesheet">
<link href="../myWeb_01Main/css/cloud-zoom.css" rel="stylesheet">
<style>
.deletemargin {
	margin: 0px;
	padding: 0px;
}

.reply {
	color: grey;
	font-size: 5px;
}
</style>
<script type="text/JavaScript">
var newmsgNoList;
var parameter;
window.onload = function(){  
	
	if("${orderFail}" != ""){
		orderFail(); 
		<% 
			session = request.getSession();
			session.removeAttribute("orderFail");
		%>
	}
	if("${ordersuc}" != ""){
		orderSuc();
		<% 
		session = request.getSession();
		session.removeAttribute("ordersuc");
		%>
		
	}
	
	
	//檢舉-----------------------------
	
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
	
	//---------------------------------
	
	
	//取得一開始有幾個回復按鈕，為了在等一下新增回復按鈕時給index
	//並且給予一個<tbody id="here${counter}"> 標籤　讓使用者案回復時能加一列到這
	var initParameter;	
// 	parameter = document.getElementById("store").value;
// 	initParameter = parameter;
	
	var btn = document.getElementById("btn");
	btn.onclick = function(){
		
		var xhr = new XMLHttpRequest();
		var Msg = document.getElementById("leaveMsg").value;
		var queryString = "groupInfoNo="+${groupInfoNo} +"&memberNo="+${memberBean.memberNo} +"&groupMsg="+Msg;
		var url = "GroupMsgServlet.controller?"+ queryString;
		xhr.open("GET" , url , true);
		xhr.send(); 
		xhr.onreadystatechange = function(){   
			// 向伺服器提出的請求已經收到回應  

			if (xhr.readyState === 4) {         
				// 伺服器回應成功  
				if (xhr.status === 200) { 
					var data = JSON.parse(xhr.responseText);    

// 				<li><a class="avtar thumbnail"><img class="img-circle" 
// 						src="../pictures/${bean.memberPic}" alt=""></a> 
// 						<a class="blogtitle">${bean.memberName}</a>
// 						<div style="width: 1000px;">
// 							<p>${bean.groupMsgContent}</p>
// 						</div> 
// 				</li>
					var aimg = document.createElement("a");
					aimg.setAttribute("class","avatar thumbnail");
					var img= document.createElement("img");
					img.setAttribute("class" , "img-circle");
					img.setAttribute("width" , "60px");
					img.setAttribute("src","../pictures/${memberBean.memberPic}");
					img.setAttribute("alt" ,"");
					img.setAttribute("align","left");
					aimg.appendChild(img); 
					
					var aa = document.createElement("a");
					aa.setAttribute("class","blogtitle");
					aa.insertAdjacentHTML("BeforeEnd","${memberBean.memberNickName}"); 
					aa.setAttribute("style","margin:10px");
					
					var divis = document.createElement("div");
					divis.setAttribute("style","width:1000px");
					var p = document.createElement("p");
					p.insertAdjacentText("BeforeEnd", data.groupMsg);
					p.setAttribute("style","margin-left:70px");
					divis.appendChild(p); 
					
					var li = document.createElement("Li");
					li.appendChild(aimg);
					li.appendChild(aa);
					li.appendChild(divis); 
					
					 
					var msgContent = document.getElementById("msgContent"); 
					msgContent.appendChild(li); 
					
				}             
			}     
		}
	}    
//End//btn.onclick = function(){   
} 

</script>
<script src="/GroupBuy/js/jquery-3.2.0.min.js"></script>
<script src="/GroupBuy/js/layer.js"></script>
<link rel="stylesheet" href="/GroupBuy/css/layer.css">
<script>
function confirmOrder(){

	layer.confirm('確定報名此團購？', {
		  btn: ['確定','取消'] //按钮
		}, function(){ 
			$("#orderItems").submit();
		}, function(){
		  layer.msg('真可惜', {
		    time: 20000, //20s后自动关闭
		    btn: ['確定']
		  });
	});
}
function orderFail(){
	layer.msg('報名失敗請輸入數量', {icon: 2});
}
function orderSuc(){
	layer.msg('報名成功', {icon: 1});
}
</script>
</head>
<body>
	<c:remove var="salary"/>

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
						<li class="span5" style="width: 420px;"><a
							rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4"
							class="thumbnail cloud-zoom"
							href="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${groupInfoNo}&type=groupCover">
								<img style="width:420px;height:300px;"
								src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${groupInfoNo}&type=groupCover"
								alt="" title="">
						</a></li>
						<c:forEach var="bean" items="${resultMulti2}">
							<li class="span5"><a
								rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4"
								class="thumbnail cloud-zoom"
								href='${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoPicNo}&type=groupPhoto'>
									<img style="width: 420px; height: 300px"
									src='${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoPicNo}&type=groupPhoto'
									alt="" title="">  
							</a></li>
						</c:forEach>
					</ul>
					<div style="text-align: center;"><span>點擊選取圖片</span></div>
					<ul class="thumbnails mainimage" style="padding-left: 0px;">
						<li class="producthtumb"><a class="thumbnail"> <img
								src="${pageContext.servletContext.contextPath}/searchImg/getImage?id=${groupInfoNo}&type=groupCover"
								alt="" title="">
						</a></li>
						<c:forEach var="bean" items="${resultMulti2}">
						
							<li class="producthtumb"><a class="thumbnail"> <img
									src='${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoPicNo}&type=groupPhoto'
									alt="" title="">
							</a></li>
							
						</c:forEach>
					</ul>
				</div>
				<!-- Right Details-->
				<div class="span7">
					<div class="row">
						<div style="margin-left: 120px;margin-top: 0px;" class="span7">
							<h1 class="productname">
								<span class="bgnone">${result.groupInfoName}</span>
							</h1>

							<form id="orderItems"
								action="<c:url value="/searchgroup/order.controller"/>"
								method="post">
                                <table>
								<c:forEach var="bean" items="${resultMulti}">
								<tr>
									<div class="quantitybox deletemargin" style="text-align:center;">
									    <td>
										<div style="float: left">
											<h3 style="margin-bottom: 0px;">${bean.groupInfoDetailsProdcutName}</h3>
										</div>
										</td>
										<td>
										<div style="float: left; margin-left: 10px">
											<h3 style="margin-bottom: 0px;">${bean.groupInfoDetailsProductPrice}</h3>
										</div>
										</td>
										<td>
										<div style="float: left; margin-left: 10px">
											<!--                       <select class="selectqty"> -->
											<!--                 	 	 <option>Select</option> -->
											<!--               		  </select> -->
											<select name='quantity' style="width: 63.6px;">
												<option value="0">0</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
												<option value="10">10</option>
											</select> <input type="hidden" name="groupInfoDetailsNo"
												value="${bean.groupInfoDetailsNo}">
										</div>
										</td>
									</div>
								</tr>
								</c:forEach>
								</table>
								<br><br>

								<ul class="productpagecart" style="padding-left: 0px;">
									<li><a class="cart" onclick="confirmOrder()"> 參與團購 </a></li>
								</ul>
								<input type="hidden" name="memberNo" value="${memberBean.memberNo}"> 
								<input type="hidden" name="groupInfoNo" value="${groupInfoNo}">

							</form>

							<!-- Product Description tab & comments-->
							<div class="productdesc">
								<ul class="nav nav-tabs" id="myTab" style="width: 532px;">
									<li class="active"><a href="#description">簡介</a></li>
									<li><a href="#specification">詳細資訊</a></li>
									<li><a href="#review">檢舉</a></li>
								</ul>
								<div class="tab-content" style="width: 532px;">
									<div class="tab-pane active" id="description">
										${result.groupInfoContent} <br>
									</div>
									<div class="tab-pane " id="specification">
										<ul class="productinfo">
											<li><span class="productinfoleft"> 創團者:</span>
												${result.memberName}</li>
											<li><span class="productinfoleft"> 團類別:</span>
												${result.productType}</li>
											<li><span class="productinfoleft"> 團狀態:</span>
												${result.groupStatus}</li>
											<li><span class="productinfoleft"> 評分:</span>
												${result.result}</li>
											<li><span class="productinfoleft"> 開始時間: </span>
												${result.groupInfoStartDate}</li>
											<li><span class="productinfoleft"> 截止時間: </span>
												${result.groupInfoDeadLine}</li>
											<li><span class="productinfoleft"> 寄送方式: </span>
												${result.groupInfoShippingWay}</li>
											<li><span class="productinfoleft"> 最低數量: </span>
												${result.groupInfoMinProductQt}</li>


										</ul>
									</div>
									<div class="tab-pane" id="review">
										<form class="form-vertical">
											<fieldset>
													<div class="control-group">
													<label class="control-label">檢舉選項</label>
													<input type="hidden"value="${groupInfoNo}"name="reportTarget" />
													<div class="controls">
														<select name="reportTypeNo">
															<option value="1">檢舉團名</option>
															<option value="2">檢舉團產品照片</option>
															<option value="3">檢舉開團留言</option>
															<option value="4">檢舉開團留言回覆</option>
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
											<input id="sendReport" class="btn btn-orange" type="button" value="送出檢舉" />
										</form>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>



		<!--  Related Products-->
		<section id="related" class="row">
		<div class="container">
			<h1 class="heading1" style="width: 1000px;margin-left: 150px;"><span class="maintext">留言專區</span><span class="subtext"> See All Comments</span></h1>

		</div>
		</section>

		<section class="commentsblog" style="margin-left: 150px;">
		<ul id="msgContent" class="comments" style="margin-left: 19%;">
			<c:forEach var="bean" items="${selectMsg}">
				<!--  			第一層Msg  -->
				<li><a class="avtar thumbnail"><img class="img-circle" 
						src="../pictures/${bean.memberPic}" alt=""></a>
						<a class="blogtitle">${bean.memberName}</a>
						<div style="width: 1000px;">
							<p>${bean.groupMsgContent}</p>
					</div></li>
			</c:forEach>
		</ul>
		<br>
		<div id="div7" style="margin-left: 10.5%;">
			<form style="margin-left: 175px">
				<textarea style="width: 1000px" id="leaveMsg" name="groupMsg"
					rows="3" cols="400"
					style="margin-top:0px; margin-bottom: 0px; height: 86px; width: 1180px;"></textarea>
				<br> <br>
				<tr>
					<td><input id="btn" class="btn btn-orange" type="button"
						name="groupMsg" value="留言" /></td>
				</tr>
			</form>
		</div>
		</section>

	</div>



	<script src="../myWeb_01Main/js/jquery.js"></script>
	<script src="../myWeb_01Main/js/bootstrap.js"></script>
	<script src="../myWeb_01Main/js/respond.min.js"></script>
	<script src="../myWeb_01Main/js/application.js"></script>
	<script src="../myWeb_01Main/js/bootstrap-tooltip.js"></script>
	<script defer src="../myWeb_01Main/js/jquery.fancybox.js"></script>
	<script defer src="../myWeb_01Main/js/jquery.flexslider.js"></script>
	<script type="text/javascript" src="../myWeb_01Main/js/jquery.tweet.js"></script>
	<script src="../myWeb_01Main/js/cloud-zoom.1.0.2.js"></script>
	<script type="text/javascript" src="../myWeb_01Main/js/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../myWeb_01Main/js/jquery.carouFredSel-6.1.0-packed.js"></script>
	<script type="text/javascript" src="../myWeb_01Main/js/jquery.mousewheel.min.js"></script>
	<script type="text/javascript" src="../myWeb_01Main/js/jquery.touchSwipe.min.js"></script>
	<script type="text/javascript"
		src="../myWeb_01Main/js/jquery.ba-throttle-debounce.min.js"></script>
	<script defer src="../myWeb_01Main/js/custom.js"></script>
</body>
</html>