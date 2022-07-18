<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.tk_inf.model.*"%>
<%@ page import="com.fd_inf.model.*"%>


<%
TkInfService tkInfSvc = new TkInfService();
List<TkInfVO> list = tkInfSvc.getAll();
pageContext.setAttribute("list", list);
%>

<%
FdInfService fdInfSvc = new FdInfService();
List<FdInfVO> list2 = fdInfSvc.getAll();
pageContext.setAttribute("list2", list2);
%>



<!-- <!DOCTYPE html> -->
<html lang="en" dir="ltr">

<head>
<title>票種與餐飲資訊</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/css/layout.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/foodAndTicket/styles/foodAndTicketInf.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>

<body>
	<!-- 置頂按鈕 -->
	<button type="button" id="BackTop" class="toTop-arrow"></button>
	<script>
		$(function() {
			$('#BackTop').click(function() {
				$('html,body').animate({
					scrollTop : 0
				}, 333);
			});
			$(window).scroll(function() {
				if ($(this).scrollTop() > 300) {
					$('#BackTop').fadeIn(222);
				} else {
					$('#BackTop').stop().fadeOut(222);
				}
			}).scroll();
		});
	</script>
	<header>

		<%@ include file="/front_end/header.jsp"%>
		<%@ include file="/front_end/header_css.jsp"%>

	</header>

	<div style="padding: 30px 30px; background-color: black;">


		<!--各自的內容--------------------->
		<div class="all">
			<div class="main">

				<div class="guide1outer">
					<div class="guide1">
						<div>票種資訊</div>
					</div>
				</div>
				<div class="TKouter">
					<table class="TKinner">
						<tr class="TKh">
							<th>票種</th>
							<th>價格</th>
							<th>描述</th>
						</tr>
						<c:forEach var="tkinfVO" items="${list}">

							<tr class="TKh">
								<td>${tkinfVO.tkType}</td>
								<td>$ ${tkinfVO.tkPrice}</td>
								<td>${tkinfVO.tkTypeDT}</td>
							</tr>

						</c:forEach>
					</table>
				</div>
				<div class="guide1outer">
					<div class="guide1">
						<div>餐飲資訊</div>
					</div>
				</div>

				<div class="FD">
					<div class="items">

						<c:forEach var="fdinfVO" items="${list2}">

							<c:if test="${fdinfVO.fdState == 1}">

								<div class="item">
									<div class="img_block">
										<img
											src="<%=request.getContextPath()%>/fd_inf/fd_inf.do?action=getPic&fdID=${fdinfVO.fdID}"
											style="width: 100px; height: 120px;">
									</div>
									<div class="iteminner">
										<div class="item_text">${fdinfVO.fdName}</div>
										<div class="item_text2">$ ${fdinfVO.fdprice}</div>
									</div>
								</div>
							</c:if>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>






	</div>
	<!--   <!--客服圖 請自行加連結-->

	<!--   <img class="cs" src="images/demo/cs.png" height="50px;" width="60px;" href="#"></img> -->

	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>
	</div>
</body>
</html>