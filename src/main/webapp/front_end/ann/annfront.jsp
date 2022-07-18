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
<title>HireMe - 影城公告</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/css/layout.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Ann_公告_css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/owl.carousel.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/magnific-popup.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/animate.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/boxicons.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/flaticon.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/meanmenu.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/style.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/ann/static/css/responsive.css">







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

	<div class="inner-banner inner-bg4">
		<div class="container">
			<div class="inner-title text-center">
				<h3>Hire Me 影城公告</h3>
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><i class='bx bxs-chevrons-right'></i></li>
					<li>影城公告</li>
				</ul>
			</div>
		</div>
	</div>


	<div class="blog-area pt-100 pb-70">
		<div class="container">
			<div class="section-title text-center">
<!-- 				<span>公告</span> -->
				<h2>Our Announcement</h2>
<!-- 				<p>It is a long established fact that a reader will be -->
<!-- 					distracted by the readable content of a page when looking at its -->
<!-- 					layout.</p> -->
			</div>
			
				<div class="row pt-45">
						<c:forEach var="annVO" items="${list}">
					<div class="col-lg-4 col-md-6">
						<div class="blog-card">
							<div class="blog-img">
								<a href="blog-details.html"> <img
									src="<%=request.getContextPath()%>${annVO.ann_picture}" >
								</a>
							</div>
							<div class="blog-content">
								<div class="blog-tag" style="top: 0px;">
									<a href="#"><span style="font-size:medium;" >${annVO.ann_title}</span></a>
								</div>
								<div>
									<h3 style="font-size:medium;">${annVO.ann_content}</h3>
								</div>
<!-- 								<a href="blog-details.html" class="read-btn">Read More</a> -->
							</div>
						</div>
					</div>
							</c:forEach>
				</div>
			
		</div>





		<div class="col-lg-12">
			<div class="pagination-area">
				<nav aria-label="Page navigation example text-center">
					<ul class="pagination">
						<li class="page-item"><a class="page-link page-links"
							href="#"> <i class='bx bx-chevrons-left'></i>
						</a></li>
						<li class="page-item current"><a class="page-link" href="#">1</a>
						</li>
<!-- 						<li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!-- 						<li class="page-item"><a class="page-link" href="#">3</a></li> -->
						<li class="page-item"><a class="page-link" href="#"> <i
								class='bx bx-chevrons-right'></i>
						</a></li>
					</ul>
				</nav>
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

	<script data-cfasync="false"
		src="${pageContext.request.contextPath}/front_end/ann/static/js/email-decode.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/jquery-3.5.0.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/bootstrap.bundle.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/meanmenu.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/owl.carousel.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/jquery.magnific-popup.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/wow.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/jquery.ajaxchimp.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/form-validator.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/contact-form-script.js"></script>

	<script
		src="${pageContext.request.contextPath}/front_end/ann/static/js/custom.js"></script>

</body>

</html>