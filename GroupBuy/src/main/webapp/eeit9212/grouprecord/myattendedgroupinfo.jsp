<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GroupBuy團購網</title>

<link href="../../css/bootstrap.css" rel="stylesheet">
<link href="../../css/style.css" rel="stylesheet">
<link href="../../css/flexslider.css" type="text/css" media="screen" rel="stylesheet" />
<jsp:include page="/headline.jsp"></jsp:include>
<style type="text/css">
.tablink {
     background-color: white;
    
/*     color: white; */
/*     float: left; */
     border-top: none; 
      border-left: none;
       border-right: none;
       margin-left:35px; 
/*       border-bottom: 1px;  
/*      outline: none;  */
/*     cursor: pointer; */
/*     padding: 14px 16px; */
/*     font-size: 17px; */
/*     width: 25%; */
}
.tablink1 {
     background-color: white;
    
/*     color: white; */
/*     float: left; */
     border-bottom: none; 
 
       margin-left:35px; 
/*       border-bottom: 1px;  
/*      outline: none;  */
/*     cursor: pointer; */
/*     padding: 14px 16px; */
/*     font-size: 17px; */
/*     width: 25%; */
}
.button_s {
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: #F25C27;
	color: white;
	line-height: 30px;
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
}

.button_b {
	font-size: 15px;
	font-weight: normal;
	border-radius: 5px;
	background-color: gray;
	color: white;
	line-height: 30px;
	text-align: center;
	display: inline-block;
	margin: 0 5px;
	text-align: center;
}
</style>
</head>
<body>
	
	<div class="cart-info container">
		<h1 style="text-align: center;">您參加的團</h1>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th style="text-align: center;vertical-align: middle;">圖片</th>
					<th style="text-align: center;vertical-align: middle;">創團日期</th>
					<th style="text-align: center;vertical-align: middle;">主揪</th>
					<th style="text-align: center;vertical-align: middle;">主揪評分</th>
					<th style="text-align: center;vertical-align: middle;">狀態</th>
					<th style="text-align: center;vertical-align: middle;">類型</th>
					<th style="text-align: center;vertical-align: middle;">目前產品數量</th>
					<th style="text-align: center;vertical-align: middle;">結束日期</th>
					<th style="text-align: center;vertical-align: middle;">訂單狀態</th>
					
				</tr>
			</thead>
			<tbody>

				<tr>

				
					<td style="text-align: center;vertical-align: middle;"><a href="#"><img title="product" alt="product"
							src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>"
							height="50" width="50"></a></td>
					<td style="text-align: center;vertical-align: middle;">${selectMyAttendedByGroupInfoNo.formatStartDate}</td>
					<td style="text-align: center;vertical-align: middle;">${selectMyAttendedByGroupInfoNo.memberName}</td>
					<td style="text-align: center;vertical-align: middle;" id="creditTd">${selectMyAttendedByGroupInfoNo.formatGrouperCredit}</td>
					<td style="text-align: center;vertical-align: middle;" id="groupStatusId">${selectMyAttendedByGroupInfoNo.groupStatus}</td>
					<td style="text-align: center;vertical-align: middle;">${selectMyAttendedByGroupInfoNo.productType}</td>
					<c:if
						test="${empty selectMyAttendedByGroupInfoNo.groupInfoTotalProductQt}">
						<c:set var="groupInfoTotalProductQt" value="0" />
					</c:if>
					<c:if
						test="${not empty selectMyAttendedByGroupInfoNo.groupInfoTotalProductQt}">
						<c:set var="groupInfoTotalProductQt"
							value="${selectMyAttendedByGroupInfoNo.groupInfoTotalProductQt}" />
					</c:if>
					<td style="text-align: center;vertical-align: middle;" id="productQtId">${groupInfoTotalProductQt}/${selectMyAttendedByGroupInfoNo.groupInfoMinProductQt}</td>
					<td style="text-align: center;vertical-align: middle;" id="deadLineId">${selectMyAttendedByGroupInfoNo.formatDeadLine}</td>
					<td style="text-align: center;vertical-align: middle;" id="orderStatus">${selectMyAttendedByGroupInfoNo.orderStatus}</td>
				</tr>
			</tbody>
		</table>
		<c:if test="${selectMyAttendedByGroupInfoNo.orderStatusNo!=1005}">
			<h1 style="text-align: center;">訂單明細</h1>
			<table border="1px">
				<thead>
					<tr>
						<th style="text-align: center;">品名</th>
						<th style="text-align: center;">單價</th>
						<th style="text-align: center;">數量</th>
						<th style="text-align: center;">總價</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="bean" items="${selectOrderInfoDetails}">
						<tr>
							<td style="text-align: center;">${bean.groupInfoDetailsProdcutName}</td>
							<td style="text-align: center;">${bean.groupInfoDetailsProductPrice}</td>
							<td style="text-align: center;">${bean.orderInfoDetailsProductQt}</td>
							<td style="text-align: center;">${bean.productTotalPriceByQt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</c:if>
<!-- 		<div class="span5"> -->
<!-- 			<ul class="thumbnails mainimage"> -->
<!-- 				<li class="span5"><a -->
<!-- 					rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" -->
<!-- 					class="thumbnail cloud-zoom" -->
<%-- 					href="<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>"> --%>
<!-- 						<img style="width: 300px; height: 250px" -->
<%-- 						src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>" --%>
<!-- 						alt="" title=""> -->
<!-- 				</a></li> -->
<%-- 				<c:forEach var="bean" items="${selectGroupInfoPic}"> --%>
<!-- 					<li class="span5"><a -->
<!-- 						rel="position: 'inside' , showTitle: false, adjustX:-4, adjustY:-4" -->
<!-- 						class="thumbnail cloud-zoom" -->
<%-- 						href='<c:url value='/eeit9212/getimage?groupInfoPicNo=${bean.groupInfoPicNo}'/>'> --%>
<!-- 							<img style="width: 470px; height: 313px" -->
<%-- 							src='<c:url value='/eeit9212/getimage?groupInfoPicNo=${bean.groupInfoPicNo}'/>' --%>
<!-- 							alt="" title=""> -->
<!-- 					</a></li> -->
<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 			<ul class="thumbnails mainimage"> -->
<!-- 				<li class="producthtumb"><a class="thumbnail"> <img -->
<%-- 						src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>" --%>
<!-- 						alt="" title=""> -->
<!-- 				</a></li> -->
<%-- 				<c:forEach var="bean" items="${selectGroupInfoPic}"> --%>

<!-- 					<li class="producthtumb"><a class="thumbnail"> <img -->
<%-- 							src='<c:url value='/eeit9212/getimage?groupInfoPicNo=${bean.groupInfoPicNo}'/>' --%>
<!-- 							alt="" title=""> -->
<!-- 					</a></li> -->

<%-- 				</c:forEach> --%>
<!-- 			</ul> -->
<!-- 		</div> -->
		<div class="span5">
		<div><img id="bigImg" style="width: 300px; height: 250px;border-radius: 5px"
					src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>"
					alt="" title=""></div>
					<div style="margin-top: 5px">
						<a><img name="smallImgs" src='<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>'
							style="width: 100px;border-radius: 2.5px"></a>		
				<c:forEach var="bean" items="${selectGroupInfoPic}">
					<a><img name="smallImgs" src='<c:url value='/eeit9212/getimage?groupInfoPicNo=${bean.groupInfoPicNo}'/>'
							style="width: 100px;border-radius: 2.5px"></a>
				
				</c:forEach>
					</div>
		</div>
		
		
		
		<div style="font-size:20px;margin-left:480px">

<%-- 			<div>賣家敘述:${selectMyAttendedByGroupInfoNo.groupInfoContent}</div> --%>
			<div id="price">訂單總價:${selectTotalPrice}</div>
			<c:if
				test="${selectMyAttendedByGroupInfoNo.groupStatusNo>=8&&selectMyAttendedByGroupInfoNo.groupStatusNo!=11&&selectMyAttendedByGroupInfoNo.orderStatusNo!=1004}">
				<div>主揪帳戶:${selectMyAttendedByGroupInfoNo.groupInfoBankAccount}</div>
			</c:if>
			<c:if test="${selectMyAttendedByGroupInfoNo.groupStatusNo<9}">
						<div>
						<h3 style="display: none;" id="PackageNo">包裹編號:主揪尚未寄貨。</h3>
						</div>
			</c:if>

			<c:if
				test="${selectMyAttendedByGroupInfoNo.orderStatusNo==1101||selectMyAttendedByGroupInfoNo.orderStatusNo==1104}">
				<form id="payForm"
					action="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller'/>"
					method="post">
					<input type="hidden" name="groupInfoNo"
						value="${selectMyAttendedByGroupInfoNo.groupInfoNo}" /> <input
						type="hidden" name="orderInfoNo"
						value="${selectMyAttendedByGroupInfoNo.orderInfoNo}" />
					<div>
						<label for="account">帳號末五碼:</label><input id="account" type="text"
							name="account" value="${param.account}" /><span
							style="color: red" id="accountSp"></span>
					</div>
					<div>
						<label for="phone">連絡電話:</label><input id="phone" type="text"
							name="phone" value="${param.phone}" /><span style="color: red"
							id="phoneSp"></span>
					</div>
					<div>
						<label for="address">寄送地址:</label><input id="address" type="text"
							name="address" value="${param.address}" /><span
							style="color: red" id="addressSp"></span>
					</div>			
					<input class='button_s' id="paySub" type="button" name="paySubmit" value="通知主揪已匯款" />				
					<input id="payData" type="button" name="payData" value="一鍵輸入" />
				</form>
			</c:if>
			<c:if
				test="${selectMyAttendedByGroupInfoNo.orderStatusNo!=1101&&selectMyAttendedByGroupInfoNo.orderStatusNo!=1104}">
				<form id="payForm" style="display: none;"
					action="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller'/>"
					method="post">
					<input type="hidden" name="groupInfoNo"
						value="${selectMyAttendedByGroupInfoNo.groupInfoNo}" /> <input
						type="hidden" name="orderInfoNo"
						value="${selectMyAttendedByGroupInfoNo.orderInfoNo}" />
					<div>
						<label for="account">帳號末五碼:</label><input id="account" type="text"
							name="account" value="${param.account}" /><span
							style="color: red" id="accountSp"></span>
					</div>
					<div>
						<label for="phone">連絡電話:</label><input id="phone" type="text"
							name="phone" value="${param.phone}" /><span style="color: red"
							id="phoneSp"></span>
					</div>
					<div>
						<label for="address">寄送地址:</label><input id="address" type="text"
							name="address" value="${param.address}" /><span
							style="color: red" id="addressSp"></span>
					</div>			
					<input class='button_s' id="paySub" type="button" name="paySubmit" value="通知主揪已匯款" />	
					<input id="payData" type="button" name="payData" value="一鍵輸入" />	
								
				</form>
			</c:if>
			<c:if test="${selectMyAttendedByGroupInfoNo.groupStatusNo>=9}">
				<div>
					<c:if
						test="${not empty selectMyOrderInfoByNo.orderInfoAfterSuccessPackageNo}">
						<h3>包裹編號:${selectMyOrderInfoByNo.orderInfoAfterSuccessPackageNo}</h3>
					</c:if>
					<c:if
						test="${empty selectMyOrderInfoByNo.orderInfoAfterSuccessPackageNo}">
						<h3 id="PackageNo">包裹編號:主揪尚未寄貨。</h3>
					</c:if>
				</div>
			</c:if>
			


			<c:if
				test="${selectMyAttendedByGroupInfoNo.orderStatusNo>1101&&selectMyAttendedByGroupInfoNo.orderStatusNo!=1104}">

				<div>匯款時間:${selectMyOrderInfoByNo.formatPayTime}
				</div>
				<div>
					帳號末五碼:${selectMyOrderInfoByNo.orderInfoAfterSuccessBankAccount}</div>
				<div>連絡電話:${selectMyOrderInfoByNo.orderInfoAfterSuccessPhone}</div>
				<div id="destinationDiv">
					寄送地址:${selectMyOrderInfoByNo.orderInfoAfterSuccessDestination}</div>			
				<c:if test="${selectMyAttendedByGroupInfoNo.orderStatusNo==1203}">
					<input class='button_s' id="stuffSub" type="button" name="scoreBtn" value="通知主揪已收貨" />
				</c:if>
			</c:if>
			<c:if test="${selectMyAttendedByGroupInfoNo.orderStatusNo!=1203}">
					<input class='button_s' style="display: none" id="stuffSub" type="button" name="scoreBtn" value="通知主揪已收貨" />
				</c:if>
		</div>
					<!-- Product Description tab & comments-->
							<div style="margin-left:450px;margin-top: 20px">
									
								<button class="tablink" id="defaultOpen" value="content">主揪敘述</button>
								<button class="tablink" id="reviewBtn" value="review">檢舉</button>
								<div style="height:350px;width: 532px;padding-left: 35px;">
									<div style="display: none;" id="description">
									<br>
									 	團名:${selectMyAttendedByGroupInfoNo.groupInfoName}<br><br>
										寄送方式:${selectMyAttendedByGroupInfoNo.groupInfoShippingWay}<br><br>
										主揪敘述:<br>${selectMyAttendedByGroupInfoNo.groupInfoContent}<br>
									</div>						
									<div style="display: none;" id="review">
										<h3>Write a Review</h3>
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
													<label class="control-label">Textarea</label>
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
<!-- 		================================================= -->
		<div id="scoreDiv" style="display: none">
			<div style="text-align: center;vertical-align: middle;margin-top: 5px">
			<input type="radio" name="score" value="1" checked="checked" />1 <input
				type="radio" name="score" value="2" />2 <input type="radio"
				name="score" value="3" />3 <input type="radio" name="score"
				value="4" />4 <input type="radio" name="score" value="5" />5 
			</div>
			<div style="text-align: center;vertical-align: middle;margin-top: 5px">
				<input class='button_s' type="button" value="評分" id="scoreButton" />
				</div>
		</div>
		
<!-- 	</div> -->
<!-- 	<div id="reportDiv" style="display: none;"> -->
<!-- 		<form> -->
<!-- 			<input type="hidden" -->
<%-- 				value="${selectMyAttendedByGroupInfoNo.groupInfoNo}" --%>
<!-- 				name="reportTarget" /> -->
<!-- 			<div> -->
<!-- 				<select name="reportTypeNo"> -->
<!-- 					<option value="1">檢舉團名</option> -->
<!-- 					<option value="2">檢舉團產品照片</option> -->
<!-- 					<option value="3">檢舉開團留言</option> -->
<!-- 					<option value="4">檢舉開團留言回覆</option> -->
<!-- 				</select> -->
<!-- 			</div> -->
<!-- 			檢舉內容 -->
<!-- 			<textarea name="reportContent" rows="5" cols="50"></textarea> -->
<!-- 			<input id="sendReport" type="button" value="送出" /> -->
<!-- 			</form> -->
		</div>	
	<script src="<c:url value='/js/jquery-3.1.1.min.js'/>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>

	<script type="text/javascript">
		$(function() {

			var webSocket = new WebSocket(
					'ws://localhost:8080/GroupBuy/groupsocket/${selectMyAttendedByGroupInfoNo.orderInfoNo}/${selectMyAttendedByGroupInfoNo.groupInfoNo}');//ServerEndpoint監聽的URL.

			webSocket.onerror = function(event) {
				onError(event)
			};

			webSocket.onopen = function(event) {
				onOpen(event)
			};

			webSocket.onmessage = function(event) {
				onMessage(event)
			};
			//  接收到server訊息時觸發.
			function onMessage(event) {
				var jsonEvent=JSON.parse(event.data);
				
			
				if(jsonEvent.change=="orderStatus"){
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"groupInfoNo":${selectMyAttendedByGroupInfoNo.groupInfoNo}},function(data){
						var jsonObj = JSON.parse(data);
						$("#productQtId").empty().append(jsonObj.groupInfoTotalProductQt+"/${selectMyAttendedByGroupInfoNo.groupInfoMinProductQt}");
						$("#orderStatus").empty().append(jsonObj.orderStatus);
					});
				};
				if(jsonEvent.change=="packageNo"){
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"groupInfoNo":${selectMyAttendedByGroupInfoNo.groupInfoNo}},function(data){
						var jsonObj = JSON.parse(data);			
						$("#orderStatus").empty().append(jsonObj.orderStatus);
						$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"orderInfoNo":${selectMyAttendedByGroupInfoNo.orderInfoNo}},function(data){
							var jsonOrder = JSON.parse(data);
							$("#PackageNo").empty().append("包裹編號:"+jsonOrder.orderInfoAfterSuccessPackageNo);		
						});	
						
					});	
					$("#stuffSub").show();
					//不知道怎麼綁定動態元件事件所以用上面的方法。
// 					$("#destinationDiv").after('<input id="stuffSub" type="button" name="paySubmit" value="通知賣家已收貨" />');
				}
				if(jsonEvent.change=="groupStart"){
					$("#orderStatus").empty().append("尚未匯款");
					$("#groupStatusId").empty().append("開團中_等待匯款");
					$("#payForm").show();
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"selectGroupInfo","groupInfoNo":'${selectMyAttendedByGroupInfoNo.groupInfoNo}'},function(data){							
						var jsonObj2 = JSON.parse(data);
						$("#deadLineId").empty().append(jsonObj2.formatDeadLine);
		
					});
				}
				if(jsonEvent.change=="startSend"){
					$("#orderStatus").empty().append("尚未收貨");
					$("#groupStatusId").empty().append("開團中_寄貨中");
					$("#PackageNo").show();
				}
				if(jsonEvent.change=="extension"){			
					$("#groupStatusId").empty().append("創建中已延期");
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"selectGroupInfo","groupInfoNo":'${selectMyAttendedByGroupInfoNo.groupInfoNo}'},function(data){							
						var jsonObj2 = JSON.parse(data);
						$("#deadLineId").empty().append(jsonObj2.formatDeadLine);
		
					});
				}
				
			}
			//  建立與server的連接.
			function onOpen(event) {
				// 			      alert("已建立連接");
			}
			//  連線錯誤時觸發
			function onError(event) {
				// 			      alert(event.data);
			}
			//加入評分CSS
			$("input[name='score']").css({'margin':'0px 8px 0px 8px'});
			//切換檢舉和賣家敘述
			$("button.tablink").click(changeDiv);
			$("#defaultOpen").click();
			function changeDiv(){
				var thisBtn=$(this);
				
				if(thisBtn.val()=="content"){
					thisBtn.attr('class','tablink1');
					$("#reviewBtn").attr('class','tablink');
					$("#description").show();
					$("#review").hide();
				}
				else if(thisBtn.val()=="review"){
					thisBtn.attr('class','tablink1');
					$("#defaultOpen").attr('class','tablink');
					$("#review").show();
					$("#description").hide();		
				}
			}
			
			//一鍵輸入
			$("#payData").click(function(){
				
				$("#account").val("11854");
				$("#phone").val("0987654321");
				$("#address").val("新北市中和區中山路三段");
				
			});
			//小圖變大圖
			var bigImg=$("#bigImg");
			$("img[name='smallImgs']").click(function(){
				bigImg.attr('src',$(this).attr('src'));		
			});
			//判斷這團的時間，即時更新團的狀態。
			var deadLine = new Date(
					"${selectMyAttendedByGroupInfoNo.groupInfoDeadLine}");
			var nowTime = new Date();
			console.log(deadLine - nowTime);

			// 			if(${selectMyAttendedByGroupInfoNo.groupStatusNo==1}){
			// 				var timeout = setTimeout(timeout, deadLine - nowTime);
			// 			}
			// 			if(${selectMyAttendedByGroupInfoNo.groupStatusNo==3}){
			// 				var againTimeout = setTimeout(againTimeout, deadLine - nowTime);
			// 			}
			// 			function timeout() {
			// 				layer.alert('截止日期已到，等待主揪處理中。', {
			// 					  skin: 'layui-layer-molv' //样式类名
			// 					  ,closeBtn: 0
			// 					},function(){
			// 						location.replace('myattendedgroupinfo.controller?locationFrom=timeout&groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}&orderInfoNo=${selectMyAttendedByGroupInfoNo.orderInfoNo}');
			// 					});

			// 			}
			// 			function againTimeout() {
			// 				layer.alert('延期截止日期已到，等待主揪處理中。', {
			// 					  skin: 'layui-layer-molv' //样式类名
			// 					  ,closeBtn: 0
			// 					},function(){
			// 						location.replace('myattendedgroupinfo.controller?locationFrom=againTimeout&groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}&orderInfoNo=${selectMyAttendedByGroupInfoNo.orderInfoNo}');

			// 					});

			// 			}

			//彈出評分視窗
		$(":input[name='scoreBtn']").on('click',function() {
			var thisBtn=$(this);
				var layerOpen = layer.open({
					type : 1,
					title : '評分',
					skin : 'layui-layer-rim', //加上边框
					area : [ '420px', '150px' ], //宽高
					content : $("#scoreDiv"),
					closeBtn : 0
					//不显示关闭按钮
				});
								//特地用Ajax練習，更新完評分根狀態直接重新載入當前頁面
			$('#scoreButton').one('click',function() {
				
			
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/creditajax",{"score" : $(":checked[name='score']").val(),
							"groupInfoMemberNo" : "${selectMyAttendedByGroupInfoNo.groupInfoMemberNo}"},function(data) {																				
									$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"groupInfoNo":${selectMyAttendedByGroupInfoNo.groupInfoNo}},function(data){
										var jsonObj = JSON.parse(data);
										$("#creditTd").empty().append(jsonObj.formatGrouperCredit);
										$("#orderStatus").empty().append("已收貨");
										thisBtn.remove();
										
										var msg={
												"target":'${selectMyAttendedByGroupInfoNo.groupInfoNo}',
												"change":"orderStatus"
										}						
										webSocket.send(JSON.stringify(msg));
									layer.close(layerOpen);
									});													
								});
					});
			});
			//按下檢舉按鈕
// 			var reportOpen;
// 			$("#report").on('click',function() {
// 				reportOpen = layer.open({
// 					type : 1,
// 					title : '檢舉',
// 					skin : 'layui-layer-rim', //加上边框
// 					area : [ '400px', '300px' ], //宽高
// 					content : $("#reportDiv")
// 				});
// 			});
			$("#sendReport").on('click',
					function() {
						var reportTarget = $(this).parents("form").find(
								"input[name='reportTarget']").val();
						var reportTypeNo = $(this).parents("form").find(
								"select[name='reportTypeNo']").val();
						var reportContent = $(this).parents("form").find(
								"textarea[name='reportContent']").val();

						$.get("${pageContext.request.contextPath}/reportajax",
								{
									"reportTarget" : reportTarget,
									"reportTypeNo" : reportTypeNo,
									"reportContent" : reportContent
								}, function(data) {
									var reportAlert = layer.alert(data, {
										skin : 'layui-layer-molv' //样式类名
											,btn:'確定'
										,
										closeBtn : 0
									}, function() {
										layer.close(reportAlert);
										layer.close(reportOpen);
									});
								});
					});

			//簡單的做一些驗證
			$("#paySub")
					.click(
							function() {
								var account = $("#account");
								var phone = $("#phone");
								var address = $("#address");
								var accountSp = $("#accountSp");
								var phoneSp = $("#phoneSp");
								var addressSp = $("#addressSp");
								var groupInfoNo = $(this).parent().find(
										"input[name='groupInfoNo']").val();
								var orderInfoNo = $(this).parent().find(
										"input[name='orderInfoNo']").val();

								if (account.val().length == 0) {
									accountSp.text("請輸入帳號末5碼");
								} else {
									accountSp.text("");
								}
								if (phone.val().length == 0) {
									phoneSp.text("請輸入連絡電話");
								} else {
									phoneSp.text("");
								}
								if (address.val().length == 0) {
									addressSp.text("請輸入寄送地址");
								} else {
									addressSp.text("");
								}
								if (account.val().length != 0
										&& phone.val().length != 0
										&& address.val().length != 0) {
									var payConfirm = layer
											.confirm(
													'檢查資料確認後請按確定',
													{
														btn : [ '確定', '取消' ]
													//按钮
													},
													function() {
														$
																.get(
																		"${pageContext.request.contextPath}/eeit9212/grouprecord/aftersuccessajax",
																		{"orderInfoNo" :orderInfoNo,
																			"account" :account.val(),
																			"phone" :phone.val(),
																			"address" :address.val()
																		},
																		function(
																				data) {
																			
																			$("#orderStatus").empty().append("已匯款");
																			account.parent().before("<div>匯款時間:"+data+"</div");
																			account.parent().empty().append("帳號末五碼:"+account.val());
																			phone.parent().empty().append("連絡電話:"+phone.val());
																			address.parent().empty().append("寄送地址:"+address.val());
																			$("#paySub").remove();
																			$("#payData").remove();
																			
																			var msg={
																					"target":'${selectMyAttendedByGroupInfoNo.groupInfoNo}',
																					"change":"payReady"
																			}						
																			webSocket.send(JSON.stringify(msg));
																			layer.close(payConfirm);
																			
																		});

													});
								}

							});

		});
		
	</script>


</body>
</html>