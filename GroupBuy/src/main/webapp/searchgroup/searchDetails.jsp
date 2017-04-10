<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>searchDetails</title>


<script type="text/JavaScript">
var newmsgNoList;
var parameter;
window.onload = function(){  
	
	//取得一開始有幾個回復按鈕，為了在等一下新增回復按鈕時給index
	//並且給予一個<tbody id="here${counter}"> 標籤　讓使用者案回復時能加一列到這
	var initParameter;	
	parameter = document.getElementById("store").value;
	initParameter = parameter;
	
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
					newmsgNoList= data.msgNoList;   　
//		 	<tr> 
// 					<td>${bean.memberName}</td>
// 					<td>${bean.groupMsgContent}</td> 
// 					<td><input type="button" name="reply" value="回覆留言" onclick="btnreply(${counter})></td> 
// 			</tr>
// 			<tbody id="newRow"+initParameter></tbody>// 一開始存在一個
//         	<tbody id="here${counter}">
// 			<tbody id="there${counter}">
// 			<tbody id="newRow"+initParameter></tbody>
					var memberName = document.createElement("td"); 
					memberName.insertAdjacentHTML("BeforeEnd", "${memberBean.memberName}");
					var groupMsgContent = document.createElement("td");
					groupMsgContent.insertAdjacentHTML("BeforeEnd", data.groupMsg); 
					var button = document.createElement("td");
					var inputButton = document.createElement("input");
					inputButton.setAttribute("type","button");    
					inputButton.setAttribute("name","reply");  
					inputButton.setAttribute("value","回覆留言");         
					inputButton.setAttribute("onclick", "btnreply("+initParameter+")"); 
					button.appendChild(inputButton);
					var tr = document.createElement("tr");
					tr.appendChild(memberName); 
					tr.appendChild(groupMsgContent);  
					tr.appendChild(button); 
					var newRow = document.getElementById("newRow"+initParameter); 
					//每次重新新增一個newRow+initParameter讓他變成一包一包的
// 					<tbody id="newRow"+initParameter></tbody>
					newRow.appendChild(tr);  
					var thereid = "there" + initParameter;
					initParameter++;
					var newRowid = "newRow"+initParameter;
					newRow.insertAdjacentHTML('AfterEnd','<tbody id ='+newRowid+'></tbody>');
					newRow.insertAdjacentHTML('AfterEnd','<tbody id ='+thereid+'></tbody>');
					 
					
				}             
			}    
		}
	}    
//End//btn.onclick = function(){   
} 

//<tr>
//<td><textarea cols="95" id="replytext＋index"></textarea></td>
//<td><input type="button" id="replygo＋index" value="送出"  onclick="insertMsgreply(id)"></td>
//</tr>

function btnreply(index){
	var td1 = document.createElement("td");
	var textarea = document.createElement("textarea");
	textarea.setAttribute("cols" , "95");
	textarea.setAttribute("id" , "replytext"+index);
	td1.appendChild(textarea);
	
	var td2 = document.createElement("td");
	var input = document.createElement("input");
	input.setAttribute("type" , "button");
	input.setAttribute("value" , "送出");
	input.setAttribute("id","replygo"+index);
	input.setAttribute("onclick" , "insertMsgreply(id)");
	td2.appendChild(input);
	
	var tr = document.createElement("tr");
	tr.setAttribute("id" , index);
	tr.appendChild(td1);
	tr.appendChild(td2);
	var there = document.getElementById("there"+index);
	there.appendChild(tr);
	
}

function insertMsgreply(index){

	var index = index.substring(7);
	if(newmsgNoList == undefined){//代表還未按過留言Ｍｓｇ功能
		var msgNoList = ${msgNoList};
	}else{
		msgNoList = newmsgNoList;
	}
	var xhr = new XMLHttpRequest();
	replyMsg = document.getElementById("replytext"+index).value;
	var queryString = "msgNo="+msgNoList[index] +"&memberNo="+${memberBean.memberNo} +"&replyMsg="+replyMsg;
	var url = "GroupMsgReplyServlet.controller?"+ queryString;
	xhr.open("GET" , url , true);
	xhr.send();
	xhr.onreadystatechange = function(){
		// 向伺服器提出的請求已經收到回應 
		if (xhr.readyState === 4) {
			// 伺服器回應成功
			if (xhr.status === 200) {
// 				<tr>
// 					<td></td>	
// 					<td>${bean2.memberName}</td>
// 					<td>${bean2.groupMsgReplyContent}</td>
// 				</tr> 
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				td2.insertAdjacentHTML("BeforeEnd", "${memberBean.memberName}");
				var td3 = document.createElement("td");
				td3.insertAdjacentHTML("BeforeEnd", xhr.responseText);
				
				var tr = document.createElement("tr");
				tr.appendChild(td1);
				tr.appendChild(td2);
				tr.appendChild(td3);
// 				if(index < parameter){
					var there = document.getElementById("there"+index);
// 				}else{
// 					var there = document.getElementById("newRow");
// 				}
				there.appendChild(tr); 
				var removego = document.getElementById(index);
				removego.remove();   
			}
		}
	}
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
	
// 	var xx = confirm("確定報名此團購 ? ");
// 	if (xx) {
// 		document.forms[0].submit();
// 	} 
}
</script>
</head>
<body>
	<form id="orderItems" action="<c:url value="/searchgroup/order.controller"/>"method="post">
		<table border='2'>
			<tr>
				<td><img height='100' width='80'
					src='${pageContext.servletContext.contextPath}/pictures/${memberPic}'></td>
 			</tr>
			<tr>
				<c:forEach var="bean" items="${resultMulti2}">
					<td><img height='100' width='80'
						src='${pageContext.servletContext.contextPath}/searchImg/getImage?id=${bean.groupInfoPicNo}&type=groupPhoto'></td>
				</c:forEach>
			</tr>
			<tr>
				<td>創團者</td>
				<td>${result.memberName}</td>
			</tr>
			<tr>
				<td>團名</td>
				<td>${result.groupInfoName}</td>
			</tr>
			<tr>
				<td>評分</td>
				<td>${result.result}</td>
			</tr>
			<tr>
				<td>開始時間</td>
				<td>${result.groupInfoStartDate}</td>
			</tr>
			<tr>
				<td>截止時間</td>
				<td>${result.groupInfoDeadLine}</td>
			</tr>
			<tr>
				<td>簡介</td>
				<td>${result.groupInfoContent}</td>
			</tr>
			<tr>
				<td>寄送方式</td>
				<td>${result.groupInfoShippingWay}</td>
			</tr>
			<tr>
				<td>最低數量</td>
				<td>${result.groupInfoMinProductQt}</td>
			</tr>
			<tr>
				<td>團類別</td>
				<td>${result.productType}</td>
			</tr>
			<tr>
				<td>團狀態</td>
				<td>${result.groupStatus}</td>
			</tr>
			<tr>
				<td>開團人的No</td>
				<td>${result.memberNo}</td>
			</tr>


			<c:forEach var="bean" items="${resultMulti}">
				<tr>
					<td>產品:${bean.groupInfoDetailsProdcutName}</td>
				</tr>
				<tr>
					<td>價錢:${bean.groupInfoDetailsProductPrice}</td>
					<td><select name='quantity'>
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
					</select></td>
				</tr>
				<tr>
					<td>groupInfoDetailsNo:${bean.groupInfoDetailsNo}</td>
				</tr>
				<tr>
					<td><input type="hidden" name="groupInfoDetailsNo"
						value="${bean.groupInfoDetailsNo}"></td>
				</tr>
			</c:forEach>
			<tr>
				<td>groupInfoNo : ${groupInfoNo}</td>
			</tr>
			<tr>
				<td>memberNo : ${memberBean.memberNo}</td>
			</tr>


			<tr>
				<td><input type="hidden" name="memberNo" value="${memberBean.memberNo}"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="groupInfoNo" value="${groupInfoNo}"></td>
			</tr>
			<tr>
				<td><input type="button" name="searchDetailsAct" value="餐與合購" onclick="confirmOrder()"></td>
			</tr>
		</table>
	</form>
	
	<form>
		<table border='2'>
			<tr>
				<td>討論區</td>
			</tr>
			<tr>
				<td><textarea cols="95" id="leaveMsg" rows="5" name="groupMsg"></textarea></td>
				<td><input type="button" id="btn" name="groupMsg" value="留言"></td>
			</tr>
			<c:set var="counter" value="0"/>　
			<c:forEach var="bean" items="${selectMsg}">
<!-- 			第一層Msg -->
				<tr>
					<td>${bean.memberName}</td>
					<td>${bean.groupMsgContent}</td>
					<td><input type="button" id="btnReply${counter}" name="reply" value="回覆留言" onclick="btnreply(${counter})"></td>	
				</tr>  
<!-- 			第二層MsgReply -->			
				<c:forEach var="bean2" items="${bean.groupReplyMsg}">
				<tr>
					<td></td>	
					<td>${bean2.memberName}</td>
					<td>${bean2.groupMsgReplyContent}</td>
				</tr>  
				</c:forEach>
				<tbody id="there${counter}"></tbody>
				
				<c:set var="counter" value="${counter+1}"/> 
			</c:forEach>
			
			<input type="hidden" id="store" value="${counter}" >
			<tbody id="newRow${counter}"></tbody>
				
		</table>
	</form>
</body>
</html>