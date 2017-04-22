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

<style>
.temp {
	
}
</style>
</head>
<body>
	<a>hello</a>
	<jsp:include page="/headline.jsp"></jsp:include>

	<div style="text-align: center;" class="cart-info container">
		<h1>您創的團</h1>
		<table class="table table-striped table-bordered">
			<tr>
				<th style="text-align: center;" class="image">圖片</th>
				<th style="text-align: center;" class="name">創團日期</th>
				<th style="text-align: center;" class="model">團名</th>
				<th style="text-align: center;" class="quantity">狀態</th>
				<th style="text-align: center;" class="quantity">類型</th>
				<th style="text-align: center;" class="quantity">目前產品數量</th>
				<th style="text-align: center;" class="quantity">寄送方式</th>
				<th style="text-align: center;" class="quantity">匯款帳號</th>
				<th style="text-align: center;" class="quantity">結束日期</th>

			</tr>

			<tr>
				<td style="text-align: center;" class="image"><a href="#"><img
						title="product" alt="product"
						src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}'/>"
						height="100" width="100"></a></td>
				<td style="text-align: center;" class="name">${selectGroupInfoByGroupInfoNo.groupInfoStartDate}</td>
				<td style="text-align: center;" class="model">${selectGroupInfoByGroupInfoNo.groupInfoName}</td>
				<td id="groupStatusId" style="text-align: center;" class="quantity">${selectGroupInfoByGroupInfoNo.groupStatus}</td>
				<td style="text-align: center;" class="quantity">${selectGroupInfoByGroupInfoNo.productType}</td>
				<c:if
					test="${empty selectGroupInfoByGroupInfoNo.groupInfoTotalProductQt}">
					<c:set var="groupInfoTotalProductQt" scope="request" value="0" />
				</c:if>
				<c:if
					test="${not empty selectGroupInfoByGroupInfoNo.groupInfoTotalProductQt}">
					<c:set var="groupInfoTotalProductQt" scope="request"
						value="${selectGroupInfoByGroupInfoNo.groupInfoTotalProductQt}" />
				</c:if>
				<input id="groupInfoTotalProductQtId" type="hidden" value="${groupInfoTotalProductQt}"/>
				<td id="productQtId" style="text-align: center;" class="quantity">${groupInfoTotalProductQt}/${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}</td>
				<td style="text-align: center;" class="quantity">${selectGroupInfoByGroupInfoNo.groupInfoShippingWay}</td>
				<td style="text-align: center;" class="quantity">${selectGroupInfoByGroupInfoNo.groupInfoBankAccount}</td>
				<td id="deadLineId" style="text-align: center;" class="quantity">${selectGroupInfoByGroupInfoNo.formatDeadLine}</td>

			</tr>
		</table>
	</div>
	<div style="text-align: center;" class="cart-info container">
		<h1>買家訂單</h1>
		<table id="orderTable" style="text-align: center;">
			<thead>
				<tr>
					<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo==7}">
						<th>通知</th>
					</c:if>
					<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo!=7}">
						<th id="receiveThId" style="display: none;">通知</th>
					</c:if>
					<th style="text-align: center;">狀態</th>
					<th style="text-align: center;">買家</th>
					<th style="text-align: center;">出席率</th>
					<th style="text-align: center;">總金額</th>
					<!-- 	迴圈顯示每一個團有哪些產品 -->
					<c:forEach var="bean" items="${selectGroupInfoDetail}">
						<c:set var="productCount" value="${productCount+1}"></c:set>
						<th style="text-align: center;">${bean.groupInfoDetailsProdcutName}，單價:${bean.groupInfoDetailsProductPrice}</th>
					</c:forEach>
					<th style="text-align: center;">詳細</th>
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

						<tr id="trId${bean.orderInfoNo}">
							<input type="hidden" value="${bean.orderInfoNo}" />
							<c:if
								test="${selectGroupInfoByGroupInfoNo.groupStatusNo==7&&bean.orderInfoStatusNo==1102}">
								<td><button type="button" name="receivePayMoney">通知買家已收到匯款</button></td>
							</c:if>
							<c:if
								test="${selectGroupInfoByGroupInfoNo.groupStatusNo!=7||bean.orderInfoStatusNo!=1102}">
								<td id="receiveTdId${bean.orderInfoNo}" style="display: none;"><button
										type="button" name="receivePayMoney">通知買家已收到匯款</button></td>
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
								<td>
									<button type="button" name="status" value="accept">接受</button>
									<button type="button" name="status" value="reject">拒絕</button>
									${bean.orderInfoStatus}
								</td>
							</c:if>
							<c:if test="${bean.orderInfoStatusNo!=1001}">

								<td id="statusId${bean.orderInfoNo}">${bean.orderInfoStatus}</td>
							</c:if>
							<td>${bean.memberName}</td>
							<c:if test="${not empty bean.groupAttendanceTotalQt}">
								<td>${bean.groupAttendanceTotalSuccess}/${bean.groupAttendanceTotalQt}</td>
							</c:if>
							<c:if test="${empty bean.groupAttendanceTotalQt}">
								<td>首次參與團購</td>
							</c:if>
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
							<td><a style="text-decoration: none;" href="#"
								class="fa fa-sort-desc"></a></td>
						</tr>

					</c:if>

						<tr id="afterPayId${bean.orderInfoNo}" style="display: none;">
							<td colspan="${productCount+6}">
								<table>
									<thead>	<tr>			
											<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo==9}">
												<th style="text-align: center;">包裹編號</th>
											</c:if>
<%-- 											<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo!=9}"> --%>
												<th id="packageNoThId${bean.orderInfoNo}" style="display:none;text-align: center;">包裹編號</th>
<%-- 											</c:if> --%>
											<th style="text-align: center;">匯款時間</th>
											<th style="text-align: center;">電話</th>
											<th style="text-align: center;">寄送地址</th>
											<th style="text-align: center;">帳號末五碼</th>
											</tr>
									</thead>
									<tbody>
										<tr>
											<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo==9}">
												<c:if
													test="${not empty bean.orderInfoAfterSuccessPackageNo}">
													<td>${bean.orderInfoAfterSuccessPackageNo}</td>
												</c:if>
												<c:if test="${empty bean.orderInfoAfterSuccessPackageNo}">
													<td><input type="hidden" value="${bean.orderInfoNo}" />
														<input type="text" name="packageNo" /><input
														name="statusId" type="hidden" value="${x.index}" /></td>
												</c:if>
											</c:if>
<%-- 											<c:if test="${selectGroupInfoByGroupInfoNo.groupStatusNo!=9}"> --%>
												
													<td id="packageNoTdId${bean.orderInfoNo}" style="display: none;"><input type="hidden" value="${bean.orderInfoNo}" />
														<input type="text" name="packageNo" /><input
														name="statusId" type="hidden" value="${x.index}" /></td>
											
<%-- 											</c:if> --%>
											<td id="payTime${bean.orderInfoNo}">${bean.formatPayTime}</td>
											<td id="phone${bean.orderInfoNo}">${bean.orderInfoAfterSuccessPhone}</td>
											<td id="destination${bean.orderInfoNo}">${bean.orderInfoAfterSuccessDestination}</td>
											<td id="bankAccount${bean.orderInfoNo}">${bean.orderInfoAfterSuccessBankAccount}</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
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
			    	var jsonEvent=JSON.parse(event.data);
				if(jsonEvent.change=="orderStatus"){
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"orderInfoNo":jsonEvent.fromKeyNo},function(data){
						var jsonObj = JSON.parse(data);					
						$("#statusId"+jsonEvent.fromKeyNo).empty().append(jsonObj.orderInfoStatus);
						
					});
				}
					if(jsonEvent.change=="payReady"){
						$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"orderInfoNo":jsonEvent.fromKeyNo},function(data){
							var jsonObj = JSON.parse(data);				
							$("#statusId"+jsonEvent.fromKeyNo).empty().append(jsonObj.orderInfoStatus);
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/selectajax",{"orderInfoNo":jsonEvent.fromKeyNo},function(data){
								var jsonOrder = JSON.parse(data);
								$("#payTime"+jsonEvent.fromKeyNo).empty().append(jsonObj.formatPayTime);
							});	
							
							$("#phone"+jsonEvent.fromKeyNo).empty().append(jsonObj.orderInfoAfterSuccessPhone);
							$("#destination"+jsonEvent.fromKeyNo).empty().append(jsonObj.orderInfoAfterSuccessDestination);
							$("#bankAccount"+jsonEvent.fromKeyNo).empty().append(jsonObj.orderInfoAfterSuccessBankAccount);
							$("#receiveThId").show();
							$("#receiveTdId"+jsonEvent.fromKeyNo).show();
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

			var deadLine = new Date(
			"${selectGroupInfoByGroupInfoNo.groupInfoDeadLine}");
			var nowTime = new Date();
			console.log(deadLine - nowTime);
			
			function groupStartAlert(){
				var groupStartAlert=layer.alert('截止日期已到。<br>您的產品下限為:${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的產品數量為:${groupInfoTotalProductQt}，<br>數量已達標，按下確定後系統將自動發送站內信給報名您的團的買家請買家於三天內匯款。', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					},function(){
						$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"groupStart","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"selectOrderInfo","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){							
								var jsonObj = JSON.parse(data);
								$.each(jsonObj,function(index,bean){
									if(bean.orderInfoStatusNo==1001||bean.orderInfoStatusNo==1002){
										$("#trId"+bean.orderInfoNo).remove();
									}
									if(bean.orderInfoStatusNo==1003||bean.orderInfoStatusNo==1101){
										$("#statusId"+bean.orderInfoNo).empty().append("尚未匯款");
									}
								});
									$("#groupStatusId").empty().append("開團中_等待匯款");
									
									
									$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"selectGroupInfo","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){							
										var jsonObj2 = JSON.parse(data);
										$("#deadLineId").empty().append(jsonObj2.formatDeadLine);
										var deadLine1=new Date(jsonObj2.formatDeadLine);
										var nowTime1=new Date();
										console.log(deadLine1 -nowTime1);
										var payTimeout = setTimeout(function(){							
											$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"payTimeout","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){
												$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"checkOrder","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){				
													location.reload();
												});								
											});		
										},deadLine1 -nowTime1);
										
									});
					
									
									
									var msg={
											"target":"sendAllOrder",
											"change":"groupStart"
									}
								webSocket.send(JSON.stringify(msg));
								layer.close(groupStartAlert);	
										
							});								
						});
					});
			}
			if(${selectGroupInfoByGroupInfoNo.groupStatusNo==2}){
				if(${groupInfoTotalProductQt>=selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}){
					groupStartAlert();				
				}
				else{
					//第一次截止日期到，數量未達標，詢問是否延期
					var layerOpen = layer.open({
						type : 1,
						title : '截止日期已到',
						skin : 'layui-layer-rim', //加上边框
						area : [ '600px', '400px' ], //宽高
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
					layer.alert('延期截止日期已到。<br>您的產品下限為:${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的產品數量為:${groupInfoTotalProductQt}，<br>數量已達標，按下確定後系統將自動發送站內信給報名您的團的買家請買家於三天內匯款。', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"groupStart","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){
								var jsonObj = JSON.parse(data);
		
								
							});
// 							webSocket.send("sendAllOrder");
// 							location.replace('mycreatedgroupinfo.controller?locationFrom=groupStart&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
						}
					);
				}
				else{
				layer.alert('延期截止日期已到。<br>您的產品下限為:${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的產品數量為:${groupInfoTotalProductQt}，<br>數量未達標，您已經延期過一次，系統將判定您的團為:流團，數量未達標。', {
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
					var startSendAlert=layer.alert('匯款截止日期已到。<br>您的產品下限為:${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的買家匯款產品總數量為:${groupInfoTotalProductQt}，<br>數量已達標，系統將判定您的團為:開團中，寄貨中。<br>請盡快處理寄貨。', {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"startSend","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){	
								$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"selectOrderInfo","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){							
									var jsonObj = JSON.parse(data);
									$.each(jsonObj,function(index,bean){
										if(bean.orderInfoStatusNo==1101||bean.orderInfoStatusNo==1104||bean.orderInfoStatusNo==1004){
											$("#trId"+bean.orderInfoNo).remove();
										}
										if(bean.orderInfoStatusNo==1102||bean.orderInfoStatusNo==1105||bean.orderInfoStatusNo==1201){
											$("#statusId"+bean.orderInfoNo).empty().append("尚未收貨");
											$("#packageNoThId"+bean.orderInfoNo).show();
											$("#packageNoTdId"+bean.orderInfoNo).show();					
										}
							
									});
										$("#groupStatusId").empty().append("開團中_寄貨中");
										var msg={
												"target":"sendAllOrder",
												"change":"startSend"
										}	
										webSocket.send(JSON.stringify(msg));
										layer.close(startSendAlert);	
											
								});									
							});
				
						}
					);
				}
				else{
					layer.alert('匯款截止日期已到。<br>您的產品下限為:${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}，<br>您的買家匯款產品總數量為:${groupInfoTotalProductQt}，<br>數量未達標，系統將判定您的團為:流團，數量未達標。<br>請盡快處理退款。', {

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
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"timeout","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){		
						location.reload();	
					});		
			}
			function againTimeout() {
// 				webSocket.send("sendAllOrder");
				location.replace('mycreatedgroupinfo.controller?locationFrom=againTimeout&groupInfoNo=${selectGroupInfoByGroupInfoNo.groupInfoNo}');
			}
			function payTimeout() {
				$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"payTimeout","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){
					$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/changegroupstatus",{"locationFrom":"checkOrder","groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){				
						location.reload();
					});								
				});	
			}
			
			var statusArray = document.getElementsByName("status");
			for (var i = 0; i < statusArray.length; i++) {
				statusArray[i].onclick = click;
			}
			
			$(".fa-sort-desc").click(function(){
				$(this).toggleClass("fa fa-sort-up");
				var orderInfoNo = $(this).parents("tr").find("input").val();
				//怎上下滑入滑出
// 				$("#afterPayId"+orderInfoNo).slideUp(1500,function(){
					
// 					$("#afterPayId"+orderInfoNo).slideDown(1500);
// 				});
				$("#afterPayId"+orderInfoNo).toggle();
				
				
			});
			
			function click() {
				var thisElement=$(this);
				var orderInfoNo = $(this).parents("tr").find("input").val();
				var Status = this.value;
				if (Status == "accept") {
					var checkOrder=layer.confirm('確定要接受這筆訂單嗎', {
						  btn: ['確定','取消'] //按钮
						}, function(){
							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/checkorderajax",{"orderInfoStatus":Status,"orderInfoNo":orderInfoNo,"groupInfoNo":'${selectGroupInfoByGroupInfoNo.groupInfoNo}'},function(data){													
								var jsonObj = JSON.parse(data);
									thisElement.parent().empty().append("已接受");
 								$("#productQtId").empty().append(jsonObj.groupInfoTotalProductQt+"/${selectGroupInfoByGroupInfoNo.groupInfoMinProductQt}");
								var msg={
										"target":orderInfoNo,
										"change":"orderStatus"
								}						
								webSocket.send(JSON.stringify(msg));
								layer.close(checkOrder);

							});							
						});	
				} else if (Status == "reject") {
					var checkOrder=layer.confirm('確定要拒絕這筆訂單嗎', {
						  btn: ['確定','取消'] //按钮
						}, function(){

							$.get("${pageContext.request.contextPath}/eeit9212/grouprecord/checkorderajax",{"orderInfoStatus":Status,"orderInfoNo":orderInfoNo},function(data){						
								if(data="success"){
									thisElement.parents("tr").remove();						
								}
								var msg={
										"target":orderInfoNo,
										"change":"orderStatus"
								}
								webSocket.send(JSON.stringify(msg));
								layer.close(checkOrder);
							});	
						});	
				}
				
			}
			$("input[name='packageNo']").change(function(){		
				var orderInfoNo=$(this).parent().find("input:hidden").val();
				var packageElement=$(this);
				
				var packageConfirm=layer.confirm('您輸入的包裹編號是:'+packageElement.val()+'，確定後不能更改，系統將通知買家已寄貨', {
					  btn: ['確定','取消'] //按钮
					}, function(){
						$.get("updateajax",{"orderInfoNo":orderInfoNo,"packageNo":packageElement.val()},function(data){
							packageElement.parent().empty().append(packageElement.val());
							$("#statusId"+orderInfoNo).empty().append("通知收貨中");
							var msg={
								"target":orderInfoNo,
								"change":"packageNo"
						}
						webSocket.send(JSON.stringify(msg));
							layer.close(packageConfirm);
		 				});
					});	
			});
			
			$("button[name='receivePayMoney']").click(function(){
				var thisElement=$(this);
				var orderInfoNo = $(this).parents("tr").find("input").val();
				var payConfirm=layer.confirm('確定要通知買家已收到匯款嗎 ？', {
					  btn: ['確定','取消'] //按钮
					}, function(){
						$.get("updateajax",{"locationFrom":"receivePayMoney","orderInfoNo":orderInfoNo,"groupInfoNo":"${selectGroupInfoByGroupInfoNo.groupInfoNo}"},function(data){					
							thisElement.parent().empty().append("已通知");
							$("#statusId"+orderInfoNo).empty().append("已通知收到匯款");
							var msg={
									"target":orderInfoNo,
									"change":"orderStatus"
							}
							webSocket.send(JSON.stringify(msg));
							layer.close(payConfirm);
		 				});			
					});	
				
					
			});
		});
		
	</script>
</body>
</html>