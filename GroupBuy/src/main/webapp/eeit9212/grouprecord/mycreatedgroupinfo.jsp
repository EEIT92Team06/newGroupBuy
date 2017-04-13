<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet">
</head>
<body>
	<jsp:include page="/Web_02/headline.jsp"></jsp:include>

	<div class="cart-info container">
		<table class="table table-striped table-bordered">
			<tr>
				<th class="image">圖片</th>
				<th class="name">創團日期</th>
				<th class="model">團名</th>
				<th class="model">內容</th>
				<th class="quantity">狀態</th>
				<th class="quantity">類型</th>
				<th class="quantity">目前產品數量</th>
				<th class="quantity">寄送方式</th>
				<th class="quantity">匯款帳號</th>
				<th class="quantity">結束日期</th>

			</tr>

			<tr>
				<td class="image"><a href="#"><img title="product"
						alt="product"
						src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}'/>"
						height="50" width="50"></a></td>
				<td class="name">${selectGroupInfoByGroupInfoNo.groupInfoStartDate}</td>
				<td class="model">${selectGroupInfoByGroupInfoNo.groupInfoName}</td>
				<td class="quantity">${selectGroupInfoByGroupInfoNo.groupInfoContent}</td>
				<td class="quantity">${selectGroupInfoByGroupInfoNo.groupStatus}</td>
				<td class="quantity">${selectGroupInfoByGroupInfoNo.productType}</td>
				<c:if
					test="${empty selectGroupInfoByGroupInfoNo.groupInfoTotalProductQt}">
					<c:set var="groupInfoTotalProductQt" value="0" />
				</c:if>
				<c:if
					test="${not empty selectGroupInfoByGroupInfoNo.groupInfoTotalProductQt}">
					<c:set var="groupInfoTotalProductQt"
						value="${selectGroupInfoByGroupInfoNo.groupInfoTotalProductQt}" />
				</c:if>
				<td class="quantity">${groupInfoTotalProductQt}/${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}</td>
				<td class="quantity">${selectGroupInfoByGroupInfoNo.groupInfoShippingWay}</td>
				<td class="quantity">${selectGroupInfoByGroupInfoNo.groupInfoBankAccount}</td>
				<td class="quantity">${selectGroupInfoByGroupInfoNo.groupInfoDeadLine}</td>

			</tr>
		</table>
	</div>

	<table border="1px">
		<tbody>
			<tr>

			</tr>
		</tbody>
	</table>

	<table border="1px">
		<thead>
			<tr>
				<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo==7}">
					<th>通知</th>
				</c:if>
				<th colspan="3">狀態</th>
				<th>買家</th>
				<th>出席率</th>
				<th>總金額</th>
				<!-- 	迴圈顯示每一個團有哪些產品 -->
				<c:forEach var="bean" items="${selectGroupInfoDetail}">
					<th>${bean.groupInfoDetailsProdcutName}，單價:${bean.groupInfoDetailsProductPrice}</th>
				</c:forEach>
				<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo>=7}">
					<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo==9}">
						<th>包裹編號</th>
					</c:if>
					<th>匯款時間</th>
					<th>電話</th>
					<th>寄送地址</th>
					<th>帳號末五碼</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<!-- 	jstl迴圈沒有break語法，逼不得已寫java程式片段做迴圈。 -->
			<%@ page import="eeit9212.model.*"%>
			<%@ page import="java.util.*"%>
			<%
				int i = 0;
				int len = ((List<GroupInfoDetailsBean>) request.getAttribute("selectGroupInfoDetail")).size();
				List<OrderInfoDetailsBean> list = (List<OrderInfoDetailsBean>) request
						.getAttribute("selectOneOrderInfoDetails");
			%>
			<c:forEach var="bean" items="${selectMyGroupOrderInfo}">
				<c:if
					test="${(bean.orderInfoStatusNo!=1002&&bean.orderInfoStatusNo!=1004)||bean.orderInfoStatusNo==1102||bean.orderInfoStatusNo==1103}">

					<tr>
						<input type="hidden" value="${bean.orderInfoNo}" />
						<c:if
							test="${selectGroupInfoByGroupInfoNo.groupStatusNo==7&&bean.orderInfoStatusNo==1102}">

							<td><button type="button" id="receivePayMoney">通知買家已收到匯款</button></td>
						</c:if>
						<c:if
							test="${selectGroupInfoByGroupInfoNo.groupStatusNo==7&&bean.orderInfoStatusNo==1105}">
							<td>已通知</td>
						</c:if>
						<c:if
							test="${selectGroupInfoByGroupInfoNo.groupStatusNo==7&&bean.orderInfoStatusNo==1101||bean.orderInfoStatusNo==1104}">
							<td></td>
						</c:if>
						<c:if test="${bean.orderInfoStatusNo==1001}">

							<td><button type="button" name="status" value="accept">接受</button></td>
							<td><button type="button" name="status" value="reject">拒絕</button></td>
							<td>${bean.orderInfoStatus}</td>
						</c:if>
						<c:if test="${bean.orderInfoStatusNo!=1001}">

							<td colspan="3" align="center">${bean.orderInfoStatus}</td>
						</c:if>
						<td>${bean.memberName}</td>
						<td>${bean.groupAttendanceTotalSuccess}/${bean.groupAttendanceTotalQt}</td>
						<td>${bean.orderInfoPriceTotal}</td>
						<%
							// 	 	對所有訂單明細的list做迴圈，依產品數量分批做。
									while (i < list.size()) {
										if (list.get(i) != null) {
						%>
						<td><%=list.get(i).getOrderInfoDetailsProductQt()%>&nbsp，&nbsp$<%=list.get(i).getProductTotalPriceByQt()%></td>
						<%
							} else {
						%>
						<td>X</td>
						<%
							}
										i++;
										// 	 		產品數量有幾個，迴圈就做幾次中斷，換下一筆訂單的明細
										if (i % len == 0) {
											break;
										}
									}
						%>
						<!-- 	 	如果狀態是已匯款之後的才要顯示這些 -->
						<c:if test="${bean.orderInfoStatusNo>=1102}">
							<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo==9}">
								<c:if test="${not empty bean.orderInfoAfterSuccessPackageNo}">
									<td>${bean.orderInfoAfterSuccessPackageNo}</td>
								</c:if>
								<c:if test="${empty bean.orderInfoAfterSuccessPackageNo}">
									<td><input type="hidden" value="${bean.orderInfoNo}" /> <input
										type="text" name="packageNo" /></td>
								</c:if>
							</c:if>
							<td>${bean.orderInfoAfterSuccessPayTime}</td>
							<td>${bean.orderInfoAfterSuccessPhone}</td>
							<td>${bean.orderInfoAfterSuccessDestination}</td>
							<td>${bean.orderInfoAfterSuccessBankAccount}</td>
						</c:if>
					</tr>

				</c:if>
			</c:forEach>
		</tbody>

	</table>
	<div id="deadLineDiv" style="display: none">
		<h3>
			截止日期已到。<br>
			的產品下限為${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>
			您的產品數量為${groupInfoTotalProductQt}，<br>
			數量未達標，有一次延期機會，延期時間為原截止日期加三天，或直接選擇流團。
		</h3>
		<button id="extension" value="extension">延期</button>
		<button id="noExtension" value="noExtension">流團</button>
	</div>
	<script src="<c:url value='/js/jquery-3.1.1.min.js'></c:url>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>
	<script type="text/javascript">
		
		$(function() {
// 			location.replace('url');使用他瀏覽器不會紀錄歷史紀錄，所以不能回到上一頁，
//			我的方法按上一頁如果回到剛剛調出視窗的頁面，會再彈出一次視窗，
//			所以為了避免這問題，以下我都用replace而不是用location.href

// 			建立webSocket連線
			var webSocket = new WebSocket('ws://localhost:8080/GroupBuy/groupsocket/${selectGroupInfoByGroupInfoNo.groupInfoNo}/null');//ServerEndpoint監聽的URL.
			
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
					location.reload();
// 			      alert("接收到訊息:"+event.data);
			    }
			//  建立與server的連接.
			    function onOpen(event) {
// 			      alert("已建立連接");
			    }
			//  連線錯誤時觸發
			    function onError(event) {
// 			      alert(event.data);
			    }

			var deadLine = new Date(
			"${selectGroupInfoByGroupInfoNo.groupInfoDeadLine}");
			var nowTime = new Date();
			console.log(deadLine - nowTime);
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==2}){
				if(${groupInfoTotalProductQt>=selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}){
					layer.alert('截止日期已到。<br>您的產品下限為${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的產品數量為${groupInfoTotalProductQt}，<br>數量已達標，按下確定後系統將自動發送站內信給報名您的團的買家請買家於三天內匯款。', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
							webSocket.send("sendAllOrder");
							location.replace('mycreatedgroupinfo.controller?locationFrom=groupStart&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
						}
					);
				}
				else{
					//第一次截止日期到，數量未達標，詢問是否延期
					var layerOpen = layer.open({
						type : 1,
						title : '截止日期已到',
						skin : 'layui-layer-rim', //加上边框
						area : [ '420px', '240px' ], //宽高
						content : $("#deadLineDiv"),
						closeBtn : 0 //不显示关闭按钮
					});
				}
			}
			
			$("#extension").click(function(){
// 				webSocket.send("sendAllOrder");
				location.replace('mycreatedgroupinfo.controller?locationFrom=extension&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}&deadLine=${selectGroupInfoByGroupInfoNo.groupInfoDeadLine}');			
			});
			
			$("#noExtension").click(function(){
// 				webSocket.send("sendAllOrder");
				location.replace('mycreatedgroupinfo.controller?locationFrom=noExtension&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}&deadLine=${selectGroupInfoByGroupInfoNo.groupInfoDeadLine}');			
			});
			
			
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==6}){
				if(${groupInfoTotalProductQt>=selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}){
					layer.alert('延期截止日期已到。<br>您的產品下限為${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的產品數量為${groupInfoTotalProductQt}，<br>數量已達標，按下確定後系統將自動發送站內信給報名您的團的買家請買家於三天內匯款。', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
// 							webSocket.send("sendAllOrder");
							location.replace('mycreatedgroupinfo.controller?locationFrom=groupStart&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
						}
					);
				}
				else{
				layer.alert('延期截止日期已到。<br>您的產品下限為${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的產品數量為${groupInfoTotalProductQt}，<br>數量未達標，您已經延期過一次，系統將判定您的團為:流團，數量未達標。', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					},function(){
// 						webSocket.send("sendAllOrder");
						location.replace('mycreatedgroupinfo.controller?locationFrom=noExtension&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
					});
				}
			}
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==8}){
				if(${groupInfoTotalProductQt>=selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}){
					layer.alert('匯款截止日期已到。<br>您的產品下限為${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的買家匯款產品總數量為${groupInfoTotalProductQt}，<br>數量已達標，系統將判定您的團為:開團中，寄貨中。<br>請盡快處理寄貨。', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
// 							webSocket.send("sendAllOrder");
							location.replace('mycreatedgroupinfo.controller?locationFrom=startSend&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
						}
					);
				}
				else{
					layer.alert('匯款截止日期已到。<br>您的產品下限為${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的買家匯款產品總數量為${groupInfoTotalProductQt}，<br>數量未達標，系統將判定您的團為:流團，數量未達標。<br>請盡快處理退款。', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
// 							webSocket.send("sendAllOrder");
							location.replace('mycreatedgroupinfo.controller?locationFrom=noExtension&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
						});
					}
			}
		
			
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==1}){
				var timeout = setTimeout(timeout, deadLine - nowTime);
			}
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==3}){
				var againTimeout = setTimeout(againTimeout, deadLine - nowTime);
			}
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==7}){
				var payTimeout = setTimeout(payTimeout, deadLine - nowTime);
			}
			function timeout() {
// 				webSocket.send("sendAllOrder");
				location.replace('mycreatedgroupinfo.controller?locationFrom=timeout&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
			}
			function againTimeout() {
// 				webSocket.send("sendAllOrder");
				location.replace('mycreatedgroupinfo.controller?locationFrom=againTimeout&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
			}
			function payTimeout() {
// 				webSocket.send("sendAllOrder");
				location.replace('mycreatedgroupinfo.controller?locationFrom=payTimeout&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
			}
			
			var statusArray = document.getElementsByName("status");
			for (var i = 0; i < statusArray.length; i++) {
				statusArray[i].onclick = click;
			}
			function click() {

				var orderInfoNo = $(this).parents("tr").find("input").val();
				var Status = this.value;
				if (Status == "accept") {
					var checkOrder=layer.confirm('確定要接受這筆訂單嗎', {
						  btn: ['確定','取消'] //按钮
						}, function(){
								
// 							location.replace("mycreatedgroupinfo.controller?orderInfoStatus="
// 									+ Status
// 									+ "&orderInfoNo="
// 									+ orderInfoNo
// 									+ "&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}");
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/mycreatedgroupinfo.controller",{"orderInfoStatus":Status,"orderInfoNo":orderInfoNo,"groupInfoNo":${selectGroupInfoByGroupInfoNo.groupInfoNo}},function(){						
								webSocket.send(orderInfoNo);
								layer.close(checkOrder);
								location.reload();
							});							
						});	
				} else if (Status == "reject") {
					var checkOrder=layer.confirm('確定要拒絕這筆訂單嗎', {
						  btn: ['確定','取消'] //按钮
						}, function(){
// 							location.replace("mycreatedgroupinfo.controller?orderInfoStatus="
// 									+ Status
// 									+ "&orderInfoNo="
// 									+ orderInfoNo
// 									+ "&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}");
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/mycreatedgroupinfo.controller",{"orderInfoStatus":Status,"orderInfoNo":orderInfoNo,"groupInfoNo":${selectGroupInfoByGroupInfoNo.groupInfoNo}},function(){						
								webSocket.send(orderInfoNo);
								layer.close(checkOrder);
								location.reload();
							});	
						});	
				}
				
			}
			$("input[name='packageNo']").change(function(){		
				var orderInfoNo=$(this).parent().find("input:hidden").val();
				var packageNo=$(this).val();
				layer.confirm('您輸入的包裹編號是:'+packageNo+'，確定後不能更改，系統將通知買家已寄貨', {
					  btn: ['確定','取消'] //按钮
					}, function(){
						$.get("updateajax",{"orderInfoNo":orderInfoNo,"packageNo":packageNo},function(){
							webSocket.send(orderInfoNo);
		 					location.reload();
		 				});
					});	
			});
			
			$("#receivePayMoney").click(function(){
				var orderInfoNo = $(this).parents("tr").find("input").val();
				layer.confirm('確定要通知買家已收到匯款嗎 ？', {
					  btn: ['確定','取消'] //按钮
					}, function(){
						$.get("updateajax",{"locationFrom":"receivePayMoney","orderInfoNo":orderInfoNo,"groupInfoNo":"${selectGroupInfoByGroupInfoNo.groupInfoNo}"},function(){
							webSocket.send(orderInfoNo);
		 					location.reload();
		 				});			
// 						location.replace('updateajax?locationFrom=receivePayMoney&orderInfoNo='+orderInfoNo+'groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
					});	
				
					
			});
		});
		
	</script>

</body>
</html>