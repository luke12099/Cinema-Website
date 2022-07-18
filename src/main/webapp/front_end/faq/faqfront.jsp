<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.faq.model.*"%>
<%@ page import="java.util.*"%>


<%
FaqService faqSvc = new FaqService();
List<FaqVO> FaqClass1 = faqSvc.getFaqClass1();
List<FaqVO> FaqClass2 = faqSvc.getFaqClass2();
List<FaqVO> FaqClass3 = faqSvc.getFaqClass3();
List<FaqVO> FaqClass4 = faqSvc.getFaqClass4();
pageContext.setAttribute("FaqClass1", FaqClass1);
pageContext.setAttribute("FaqClass2", FaqClass2);
pageContext.setAttribute("FaqClass3", FaqClass3);
pageContext.setAttribute("FaqClass4", FaqClass4);
%>


<!-- <!DOCTYPE html> -->
<html lang="en" dir="ltr">

<head>
<title>HireMe - 常見問題</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/css/layout.css"
	type="text/css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/faq/css/faq_front.css">
<!-- Core theme CSS (includes Bootstrap)-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/faq/js/faq_front.js">

<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>



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

	<div>


		<!--各自的內容--------------------->
		<!-- Page Content-->
		<div style="padding: 100px 100px;">

			<div class="text-center mb-5" >
				<h1 class="fw-bolder">常見問題</h1>
				<p class="lead fw-normal text-muted mb-0">F A Q</p>
			</div>

			<div class="row gx-5">
				<div class="col-xl-8">

					<!-- FAQ Accordion 1-->

					<h2 class="fw-bolder mb-3">會員相關問題</h2>
					
					<div class="accordion mb-5" id="accordionExample">
					
						<div class="accordion-item">
						<c:forEach var="faqVO" items="${FaqClass1}">
								<h3 class="accordion-header" id="headingOne">
									<button class="accordion-button" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseOne_${faqVO.faq_title}"
										aria-expanded="true" aria-controls="collapseOne">${faqVO.faq_title}</button>
								</h3>
					
								<div class="accordion-collapse collapse" id="collapseOne_${faqVO.faq_title}"
									aria-labelledby="headingOne" data-bs-parent="#accordionExample">
									<div class="accordion-body">
										<strong>${faqVO.faq_content}</strong>
									</div>
								</div>
								</c:forEach>
								</div>
						</div>
						</div>
						
					</div>

					<!-- FAQ Accordion 2-->
					<h2 class="fw-bolder mb-3">影城相關問題</h2>
					<div class="accordion mb-5" id="accordionExample">
						<div class="accordion-item">
							<c:forEach var="faqVO" items="${FaqClass2}">
								<h3 class="accordion-header" id="headingOne">
									<button class="accordion-button" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseOne_${faqVO.faq_title}"
										aria-expanded="true" aria-controls="collapseOne">${faqVO.faq_title}</button>
								</h3>
								<div class="accordion-collapse collapse" id="collapseOne_${faqVO.faq_title}"
									aria-labelledby="headingOne" data-bs-parent="#accordionExample">
									<div class="accordion-body">
										<strong>${faqVO.faq_content}</strong>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
					<!-- FAQ Accordion 3-->
					<h2 class="fw-bolder mb-3">電影上映相關問題</h2>
					<div class="accordion mb-5" id="accordionExample">
						<div class="accordion-item">
							<c:forEach var="faqVO" items="${FaqClass3}">
								<h3 class="accordion-header" id="headingOne">
									<button class="accordion-button" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseOne_${faqVO.faq_title}"
										aria-expanded="true" aria-controls="collapseOne">${faqVO.faq_title}</button>
								</h3>
								<div class="accordion-collapse collapse" id="collapseOne_${faqVO.faq_title}"
									aria-labelledby="headingOne" data-bs-parent="#accordionExample">
									<div class="accordion-body">
										<strong>${faqVO.faq_content}</strong>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
					<!-- FAQ Accordion 4-->
					<h2 class="fw-bolder mb-3">其他問題</h2>
					<div class="accordion mb-5" id="accordionExample">
						<div class="accordion-item">
							<c:forEach var="faqVO" items="${FaqClass4}">
								<h3 class="accordion-header" id="headingOne">
									<button class="accordion-button" type="button"
										data-bs-toggle="collapse" data-bs-target="#collapseOne_${faqVO.faq_title}"
										aria-expanded="true" aria-controls="collapseOne">${faqVO.faq_title}</button>
								</h3>
								<div class="accordion-collapse collapse" id="collapseOne_${faqVO.faq_title}"
									aria-labelledby="headingOne" data-bs-parent="#accordionExample">
									<div class="accordion-body">
										<strong>${faqVO.faq_content}</strong>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
					
				</div>
			</div>

	







	<!-- 	</div> -->
	<!--   <!--客服圖 請自行加連結-->

	<!--   <img class="cs" src="images/demo/cs.png" height="50px;" width="60px;" href="#"></img> -->

	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>

		<!-- Bootstrap core JS-->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Core theme JS-->
		<script src="/front_end/faq/js/scripts.js"></script>


	</div>
</body>

</html>