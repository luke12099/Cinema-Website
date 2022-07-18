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
		<div class="content-wrap page-news">
			<div class="subsite">
				<div class="row">
					<div class="col-md-12">
						<div class="subsite-heading">${annVO.ann_title}</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						<img src="<%=request.getContextPath()%>${annVO.ann_picture}" alt="image" class="border-radius">
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-12">
						${annVO.ann_content}
					</div>
				</div>
				<div class="row">
				</div>
			</div>
		</div>
	</div>
	<!-- .Content -->

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
		
		    <!-- jQuery v3.4.1 -->
    <script src="${pageContext.request.contextPath}/front_end/annlib/jquery/jquery-3.4.1.min.js"></script><!-- Bootstrap v4.3.1 JS -->
    <script src="${pageContext.request.contextPath}/front_end/annlib/bootstrap/js/bootstrap.min.js"></script><!-- Magnific Popup core JS file -->
    <script src="${pageContext.request.contextPath}/front_end/annlib/Magnific-Popup-master/dist/jquery.magnific-popup.js"></script><!-- Slick JS -->
    <script src="${pageContext.request.contextPath}/front_end/annlib/slick/slick/slick.min.js"></script><!-- Date Picker -->
    <script src="${pageContext.request.contextPath}/front_end/annlib/gijgo/js/gijgo.min.js" type="text/javascript"></script><!-- Custom JS -->
    <script src="${pageContext.request.contextPath}/front_end/annjs/theme.js"></script>
</body>

</html>