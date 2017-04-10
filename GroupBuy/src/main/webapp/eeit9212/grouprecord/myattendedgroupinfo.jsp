<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1px" id="groupTable">
		<thead>
			<tr>
				<th>圖片</th>
				<th>創團日期</th>
				<th>主揪</th>
				<th>主揪評分</th>
				<th>團名</th>
				<th>狀態</th>
				<th>類型</th>
				<th>目前產品數量</th>
				<th>結束日期</th>
				<th>訂單狀態</th>
				<th>寄送方式</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><img
					src="<c:url value='/eeit9212/getimage?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}'/>" /></td>
				<td>${selectMyAttendedByGroupInfoNo.groupInfoStartDate}</td>
				<td>${selectMyAttendedByGroupInfoNo.memberName}</td>
				<c:if test="${selectMyAttendedByGroupInfoNo.grouperCredit!=0}">
					<td>${selectMyAttendedByGroupInfoNo.grouperCredit}</td>
				</c:if>
				<c:if test="${selectMyAttendedByGroupInfoNo.grouperCredit==0}">
					<td>主揪第一次開團</td>
				</c:if>
				<td>${selectMyAttendedByGroupInfoNo.groupInfoName}</td>
				<td>${selectMyAttendedByGroupInfoNo.groupStatus}</td>
				<td>${selectMyAttendedByGroupInfoNo.productType}</td>
				<td>${selectMyAttendedByGroupInfoNo.groupInfoTotalProductQt}/${selectMyAttendedByGroupInfoNo.groupInfoMinProductQt}</td>
				<td>${selectMyAttendedByGroupInfoNo.groupInfoDeadLine}</td>
				<td>${selectMyAttendedByGroupInfoNo.orderStatus}</td>
				<td>${selectMyAttendedByGroupInfoNo.groupInfoShippingWay}
					<button id="report">檢舉</button>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="float: left;">
		<c:forEach var="bean" items="${selectGroupInfoPic}">
			<img
				src="<c:url value='/eeit9212/getimage?groupInfoPicNo=${bean.groupInfoPicNo}'/>" />
		</c:forEach>
	</div>
	<div style="display: inline;">賣家敘述:${selectMyAttendedByGroupInfoNo.groupInfoContent}</div>
	<c:if
		test="${selectMyAttendedByGroupInfoNo.groupStatusNo>=8&&selectMyAttendedByGroupInfoNo.groupStatusNo!=11&&selectMyAttendedByGroupInfoNo.orderStatusNo!=1004}">
		<div>賣家帳戶:${selectMyAttendedByGroupInfoNo.groupInfoBankAccount}</div>
	</c:if>
	<c:if test="${selectMyAttendedByGroupInfoNo.orderStatusNo!=1005}">
		<table border="1px">
			<thead>
				<tr>
					<th>品名</th>
					<th>價錢</th>
					<th>數量</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="bean" items="${selectOrderInfoDetails}">
					<tr>
						<td>${bean.groupInfoDetailsProdcutName}</td>
						<td>${bean.productTotalPriceByQt}</td>
						<td>${bean.orderInfoDetailsProductQt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="price">總價:${selectTotalPrice}</div>
	</c:if>

	<c:if
		test="${selectMyAttendedByGroupInfoNo.orderStatusNo==1101||selectMyAttendedByGroupInfoNo.orderStatusNo==1104}">

		<form id="payForm"
			action="<c:url value='/eeit9212/grouprecord/myattendedgroupinfo.controller'/>"
			method="post">
			<div>

				<input type="hidden" name="groupInfoNo"
					value="${selectMyAttendedByGroupInfoNo.groupInfoNo}" /> <input
					type="hidden" name="orderInfoNo"
					value="${selectMyAttendedByGroupInfoNo.orderInfoNo}" /> <label
					for="account">帳號末五碼:</label><input id="account" type="text"
					name="account" value="${param.account}" /><span style="color: red"
					id="accountSp"></span>
			</div>
			<div>
				<label for="phone">連絡電話:</label><input id="phone" type="text"
					name="phone" value="${param.phone}" /><span style="color: red"
					id="phoneSp"></span>
			</div>
			<div>
				<label for="address">寄送地址:</label><input id="address" type="text"
					name="address" value="${param.address}" /><span style="color: red"
					id="addressSp"></span>
			</div>
			<input id="paySub" type="button" name="paySubmit" value="通知賣家已匯款" />
		</form>
	</c:if>
	<c:if
		test="${selectMyAttendedByGroupInfoNo.orderStatusNo>1101&&selectMyAttendedByGroupInfoNo.orderStatusNo!=1104}">
		<div>
			<c:if
				test="${not empty selectMyOrderInfoByNo.orderInfoAfterSuccessPackageNo}">
				<h3>包裹編號:${selectMyOrderInfoByNo.orderInfoAfterSuccessPackageNo}</h3>
			</c:if>
			<c:if
				test="${empty selectMyOrderInfoByNo.orderInfoAfterSuccessPackageNo}">
				<h3>包裹編號:主揪尚未寄貨。</h3>
			</c:if>
		</div>
		<div>匯款時間:${selectMyOrderInfoByNo.orderInfoAfterSuccessPayTime}
		</div>
		<div>
			帳號末五碼:${selectMyOrderInfoByNo.orderInfoAfterSuccessBankAccount}</div>
		<div>連絡電話:${selectMyOrderInfoByNo.orderInfoAfterSuccessPhone}</div>
		<div>
			寄送地址:${selectMyOrderInfoByNo.orderInfoAfterSuccessDestination}</div>
		<c:if test="${selectMyAttendedByGroupInfoNo.orderStatusNo==1203}">
			<input id="stuffSub" type="button" name="paySubmit" value="通知賣家已收貨" />
		</c:if>

	</c:if>
	<div id="scoreDiv" style="display: none">
		<input type="radio" name="score" value="1" checked="checked" />1 <input
			type="radio" name="score" value="2" />2 <input type="radio"
			name="score" value="3" />3 <input type="radio" name="score"
			value="4" />4 <input type="radio" name="score" value="5" />5 <input
			type="button" value="評分" id="scoreButton" />
	</div>
	<div id="reportDiv" style="display: none">
			<input id="reportTarget" type="hidden" value="${selectMyAttendedByGroupInfoNo.groupInfoNo}" name="reportTarget"/>
			<div>
				<select id="reportTypeNo" name="reportTypeNo">
					<option value="1">檢舉團名</option>
					<option value="2">檢舉團產品照片</option>
					<option value="3">檢舉開團留言</option>
					<option value="4">檢舉開團留言回覆</option>
				</select>
			</div>
			<label for="reportContent">檢舉內容</label>
			<textarea id="reportContent" name="reportContent" rows="5" cols="50"></textarea>
			<input id="sendReport" type="button" value="送出"/>
	</div>
	
	<script src="<c:url value='/js/jquery-3.1.1.min.js'/>"></script>
	<script src="<c:url value='/js/layer/layer.js'/>"></script>
	<script type="text/javascript">
	
		$(function() {
			
			
			var webSocket = new WebSocket('ws://localhost:8080/GroupBuy/groupsocket');//ServerEndpoint監聽的URL.
			
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
			      alert("接收到訊息:"+event.data);
			    }
			//  建立與server的連接.
			    function onOpen(event) {
			      alert("已建立連接");
			    }
			//  連線錯誤時觸發
			    function onError(event) {
			      alert(event.data);
			    }
			//  按下按鈕後觸發,發送訊息給server
			    function start() {
			      webSocket.send('hello');
			    }

			
			
		
			//判斷這團的時間，即時更新團的狀態。
			var deadLine = new Date(
			"${selectMyAttendedByGroupInfoNo.groupInfoDeadLine}");
			var nowTime = new Date();
			console.log(deadLine - nowTime);
			
			if(${selectMyAttendedByGroupInfoNo.groupStatusNo==1}){
				var timeout = setTimeout(timeout, deadLine - nowTime);
			}
			if(${selectMyAttendedByGroupInfoNo.groupStatusNo==3}){
				var againTimeout = setTimeout(againTimeout, deadLine - nowTime);
			}
			function timeout() {
				layer.alert('截止日期已到，等待主揪處理中。', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					},function(){
						location.replace('myattendedgroupinfo.controller?locationFrom=timeout&groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}&orderInfoNo=${selectMyAttendedByGroupInfoNo.orderInfoNo}');
					});
				
			}
			function againTimeout() {
				layer.alert('延期截止日期已到，等待主揪處理中。', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					},function(){
						location.replace('myattendedgroupinfo.controller?locationFrom=againTimeout&groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}&orderInfoNo=${selectMyAttendedByGroupInfoNo.orderInfoNo}');
						
					});
							
			}
			
					//彈出評分視窗
			$("#stuffSub").click(function() {
				var layerOpen = layer.open({
					type : 1,
					title:'評分',
					skin : 'layui-layer-rim', //加上边框
					area : [ '420px', '240px' ], //宽高
					content : $("#scoreDiv"),
					closeBtn : 0 //不显示关闭按钮
				});
				//特地用Ajax練習，更新完評分根狀態直接重新載入當前頁面
				$('#scoreButton').one('click', function() {
					$.get("creditajax",{"score":$(":checked[name='score']").val(),"groupInfoNo":"${selectMyAttendedByGroupInfoNo.groupInfoNo}",
										"groupInfoMemberNo":"${selectMyAttendedByGroupInfoNo.groupInfoMemberNo}"},function(data){
						layer.close(layerOpen);
						location.replace('myattendedgroupinfo.controller?groupInfoNo=${selectMyAttendedByGroupInfoNo.groupInfoNo}&orderInfoNo=${selectMyAttendedByGroupInfoNo.orderInfoNo}');
						
					});				
				});
			});
			
					
			//按下檢舉按鈕
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
				$.get("${pageContext.request.contextPath}/reportajax",{"reportTarget":$("#reportTarget").val(),"reportTypeNo":$("#reportTypeNo").val(),"reportContent":$("#reportContent").val()},function(data){
					var reportAlert=layer.alert(data, {
						  skin: 'layui-layer-molv' //样式类名
						  ,closeBtn: 0
						},function(){
							layer.close(reportAlert);
							layer.close(reportOpen);					
						});
				});
			});		
					
					
					
					
			//簡單的做一些驗證
			$("#paySub").click(
					function() {
						var account = $("#account");
						var phone = $("#phone");
						var address = $("#address");
						var accountSp = $("#accountSp");
						var phoneSp = $("#phoneSp");
						var addressSp = $("#addressSp");
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
							layer.confirm('檢查資料確認後請按確定', {
								  btn: ['確定','取消'] //按钮
								}, function(){		
									$("#payForm").submit();
								});	
						}

					})

		});
	</script>


</body>
</html>