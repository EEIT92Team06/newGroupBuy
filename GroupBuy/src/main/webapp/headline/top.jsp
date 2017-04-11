<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/eDM.css" type="text/css" />
<%-- <c:set var='debug' value='true' scope='application' /> --%>
<table class='menuOuter'>
	<tr height="48px">
		<!--     <td width="60px" rowspan='2'><img width="60px" height="40px" -->
		<%--       src="${pageContext.request.contextPath}/images/BookStore.gif"> --%>
		<!--     </td> -->
		<td>
			<table class='menuInner'>
				<tr>
					<td class='menuData'>
						<div class='menu'>
							<%-- 			   <c:if test="${ funcName != 'SHO' }"> --%>
							<a href="<c:url value='/_03_listBooks/DisplayPageProducts' />">
								創團 </a>
						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>
							<%-- 			  <c:if test="${ funcName != 'CHE' }"> --%>
							<a href="<c:url value='/_04_ShoppingCart/ShowCartContent.jsp' />">
								許願池 </a>
							<%-- 			  </c:if> --%>
							<%-- 			  <c:if test="${ funcName == 'CHE' }">  --%>
							<!--                                                結帳 -->
							<%--               </c:if> --%>
						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>
							<%-- 			  <c:if test="${ funcName != 'ORD' }"> --%>
							<a href="<c:url value='/_05_orderProcess/OrderList.jsp' />">
								會員空間 </a>
							<%-- 			  </c:if> --%>
							<%-- 			  <c:if test="${ funcName == 'ORD' }">  --%>
							<!--                                                 訂單 -->
							<%--               </c:if> --%>
						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>
							<%-- 		      <c:if test="${ funcName != 'BMT' }"> --%>
							<a
								href="<c:url value='/_20_productMaintain/DisplayPageProducts' />">
								實體比價 <a
								href="<c:url value='/headline/SearchServlet0.controller' />">
									搜團 </a>
						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>
							<%-- 			  <c:if test="${ funcName != 'CHE' }"> --%>
							<a href="<c:url value='/_04_ShoppingCart/ShowCartContent.jsp' />">
								許願池 </a>
							<%-- 			  </c:if> --%>
							<%-- 			  <c:if test="${ funcName == 'CHE' }">  --%>
							<!--                                                結帳 -->
							<%--               </c:if> --%>
						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>
							<%-- 			  <c:if test="${ funcName != 'ORD' }"> --%>
							<a href="<c:url value='/test.jsp' />">
								會員空間 </a>
							<%-- 			  </c:if> --%>
							<%-- 			  <c:if test="${ funcName == 'ORD' }">  --%>
							<!--                                                 訂單 -->
							<%--               </c:if> --%>
						</div>
					</td>
					<td class='menuData'>
						<div class='menu'>
							<%-- 		      <c:if test="${ funcName != 'BMT' }"> --%>
							<a href="<c:url value='/Backstage/BackStageServlet.controller'/>">
								後臺管理 </a>
							<%--               </c:if> --%>
							<%-- 			  <c:if test="${ funcName == 'BMT' }">  --%>
							<!--                                              維護 -->
							<%--               </c:if> --%>
						</div>
					</td>

<!--                      到時候這裡加到會員專區裡 -->
                    <td class="memenuData">
                        <div class="menu">
                          <c:if test="${!empty loginToken}">
                            <a href="<c:url value="/overViewMailServlet.do"/>">信箱</a>
                          </c:if>
                        </div>
                    </td>
					<td class='menuData'>
						<div class='menu'>
							<c:if test="${ empty loginToken}">
								<a href="<c:url value="/secure/login.jsp"/>">登入</a>
							</c:if>
							<c:if test="${!empty loginToken}">
								<a href="<c:url value="/secure/logout.jsp"/>">登出</a>
							</c:if>
						</div>
					</td>
				</tr>
			</table>
	<tr>
		<td>
			<hr style="color: #f00; background-color: #f00; height: 2px;">
		</td>
	</tr>
</table>