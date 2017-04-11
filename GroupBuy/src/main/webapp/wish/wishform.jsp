<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wish Form</title>
<style type="text/css">
    #dropZone {
            width: 600px;
            height: 90px;
            float: left;
        }
    .thumb {
            height: 75px;
            margin: 5px;
        }
    
</style>


</head>
<body>
<jsp:include page="/Web_02/headline.jsp"></jsp:include>
發起許願   <a>${errorMsg.fail}</a>
	<form action="<c:url value="/wish/wishform.controller" />" enctype="multipart/form-data" method="post">

		<table>
			<tr>
				<td>標題:</td>
				<td><input type="text" name="title" value="${param.title}"></td>
				<td>${errorMsg.title}</td>
			</tr>
			<tr>
				<td>產品類別:</td>
				<td><select name="productType">
				        <option value="0">選擇產品類別</option>
						<option value="1">生鮮食品</option>
						<option value="2">團購美食</option>
						<option value="3">保養美妝</option>
						<option value="4">服飾配件</option>
						<option value="5">育兒親子</option>
						<option value="6">居家生活</option>
						<option value="7">休閒娛樂</option>
						<option value="8">3C家電</option>
				</select></td>
				<td>${errorMsg.type}</td>
			</tr>
			<tr>
				<td>產品名稱:</td>
				<td><input type="text" name="name" value="${param.name}"></td>
				<td>${errorMsg.name}</td>
			</tr>
			<tr>
				<td>原價:</td>
				<td><input type="text" name="price" value="${param.price}"></td>
				<td>${errorMsg.price}</td>
			</tr>
			<tr>
				<td>來源:</td>
				<td><input type="text" name="source" value="${param.source}"></td>
				<td></td>
			</tr>
			<tr>
				<td>圖片上傳:</td>
				<td><input id="file1" type="file" name="picUpload" value="upload" accept="image/*" multiple="multiple" onchange="fileViewer()"></td>
				<td>${errorMsg.upload}</td>
			</tr>
			<tr>
			    <td></<td>
			    <td><div id="dropZone" ><img src="" name="pic" /></div></td>
			</tr>
			<tr>
				<td valign="top">內容:</td>
				<td><textarea name="content" cols="45" rows="10" value="${param.content}"></textarea></td>
				<td>${errorMsg.content}</td>
			</tr>
			<tr>
				<td><input type="submit" name="send" value="送出"></td>
				<td><a  href="<c:url value="/wish/wishpool.controller" />" >
				<input type="button" name="cancel" value="取消"></a></td>
			</tr>
		</table>
	</form>
<script>
        function fileViewer() {    //在DIV中顯示上傳的圖片
        	document.getElementById("dropZone").innerHTML='';  //重新上傳時,刪掉原先顯示的圖片
        	var count=0;  //id的初始值
            var files = document.getElementById("file1").files
            var filesLen = files.length;
            for (var i = 0; i < filesLen; i++) {
                
                var reader = new FileReader();
                reader.readAsDataURL(files[i]);
                reader.onload = function (e) {
                    var fileContent=e.target.result;

                    var imgObj = document.createElement("img");
                    imgObj.setAttribute("id",count);
                    imgObj.setAttribute("src", fileContent);
                    imgObj.setAttribute("class", "thumb");
                    imgObj.setAttribute("name","pic");
                    imgObj.setAttribute("onclick","choseCover(this)"); //將這個圖片物件傳到choseCover
              
                    document.getElementById("dropZone").appendChild(imgObj);
                    count++; 
                }
            }
        }
         function choseCover(obj) {
        	id=obj.id;
        	var imgs = document.getElementsByTagName("img"); //取得img陣列
            for(var i=0;i<imgs.length;i++){ //移除原先選取的圖片的外框及value
            	imgs.item(i).setAttribute("style","");
            }
        	//附加外框
         	document.getElementById(id).setAttribute("style","border:5px outset yellowgreen");
        	
         	var xhr = new XMLHttpRequest();
    		var queryString = "picNo="+id;
    		var url = "wishform.controller?"+ queryString;
    		xhr.open("GET" , url , true);
    		xhr.send();
    		xhr.onreadystatechange = function(){ 
    			// 向伺服器提出的請求已經收到回應 
    			if (xhr.readyState === 4) {   
    				// 伺服器回應成功
    				if (xhr.status === 200) {
    					
    				}             
    			}
//     			alert("xhr.status: "+xhr.status);
    		}
//     		alert("xhr.readyState : " + xhr.readyState);
         }
</script>
</body>
</html>