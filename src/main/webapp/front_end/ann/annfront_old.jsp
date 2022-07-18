<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ann.model.*"%>
<%@ page import="java.util.*"%>

<%
AnnService annSvc = new AnnService();
List<AnnVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!-- <!DOCTYPE html> -->
<html lang="en" dir="ltr">
<head>
<title>HireMe</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/css/layout.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Ann_公告_css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/ann/css/annfront.css">


<link rel="icon" href="favicon.ico" type="image/x-icon">
<!-- Bootstrap v4.3.1 CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/lib/bootstrap/css/bootstrap.min.css">
<!-- Custom CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/css/theme.css">
<!-- Slick CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/ann/lib/slick/slick/slick.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/ann/lib/slick/slick/slick-theme.css">
<!-- Magnific Popup core CSS file -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/lib/Magnific-Popup-master/dist/magnific-popup.css">
<!-- Font Awesome Free 5.10.2 CSS JS -->
<link
	href="${pageContext.request.contextPath}/front_end/ann/lib/fontawesome-free-5.10.2-web/css/all.css"
	rel="stylesheet">
<script defer
	src="${pageContext.request.contextPath}/front_end/ann/lib/fontawesome-free-5.10.2-web/js/brands.min.js"></script>
<script defer
	src="${pageContext.request.contextPath}/front_end/ann/lib/fontawesome-free-5.10.2-web/js/solid.min.js"></script>
<script defer
	src="${pageContext.request.contextPath}/front_end/ann/lib/fontawesome-free-5.10.2-web/js/regular.min.js"></script>
<script defer
	src="${pageContext.request.contextPath}/front_end/ann/lib/fontawesome-free-5.10.2-web/js/fontawesome.min.js"></script>
<!-- Date picker -->
<link
	href="${pageContext.request.contextPath}/front_end/ann/lib/gijgo/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />







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

	<!--各自的內容--------------------->

	<!-- Content -->
	<div id="content">
		<div class="content-wrap page-news-list">
			<div class="subsite-banner">
				<img src="img/subsite-banner-5.jpg">
			</div>
			<div class="subsite subsite-with-banner">
				<div class="row">
					<div class="col-md-12">
						<div style="font-size:30px" class="subsite-heading" >影 城 公 告</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="search-form search-content">
							<div class="search-wrapper ">
<%-- 							<form action="${pageContext.request.contextPath}/ann/ann.do" method="post"> --%>
<!-- 								<input id="search" placeholder="Search..."> -->
<!-- 								<button class="ssubmit" type="submit" name="search_submit"> -->
<!-- 									<i class="fas fa-search"></i> -->
<!-- 								</button> -->
<!-- 								</form> -->
							</div>
						</div>
					</div>
				</div>
				<c:forEach var="annVO" items="${list}">
					<div class="row news-row">
						<div class="col-md-12">
							<div class="news-card">
								<div class="nc-top">
									<div class="nc-left">
										<div class="ncl-image">
										<td><img src="<%=request.getContextPath()%>${annVO.ann_picture}"></td>
										</div>
									</div>
									<div class="nc-right">
										<div style="font-size:15px" class="ncr-row-a">${annVO.ann_title}</div>
										<div style="font-size:15px" class="ncr-row-b">${annVO.ann_content}</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>


<!-- 				<div class="row car-row pagination-row"> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<nav aria-label="Page navigation example"> -->
<!-- 							<ul class="pagination justify-content-center"> -->
<!-- 								<li class="page-item"><a class="page-link" href="#" -->
<!-- 									aria-label="Previous"><span aria-hidden="true">&laquo;</span><span -->
<!-- 										class="sr-only">Previous</span></a></li> -->
<!-- 								<li class="page-item active"><a class="page-link" href="#">1</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#">4</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#">5</a></li> -->
<!-- 								<li class="page-item"><a class="page-link" href="#" -->
<!-- 									aria-label="Next"><span aria-hidden="true">&raquo;</span><span -->
<!-- 										class="sr-only">Next</span></a></li> -->
	
<!-- 							</ul> -->
<!-- 						</nav> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>
	<!-- .Content -->























	<!--   客服圖 請自行加連結-->
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