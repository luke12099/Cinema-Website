<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actdt.model.*"%>
<!-- List cannot be resolved to a type表示List不能被定義為一個類型，這個錯誤要導入java.util.*包。 -->
<!-- 在jsp中使用List前加上java.util.*就可以解決問題。 -->
<%@ page import="java.util.*"%>

<%
ActdtService actSvc = new ActdtService();
List<ActdtVO> list = actSvc.getOnlyAct();
System.out.println(list);
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<title>HireMe</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/css/layout.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/act/css/main.css">

<!-- 活動標題 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/act/css/acttitle.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/act/js/acttitle.js">

<!-- 活動列表 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/act/css/actlist.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/act/js/actlist.js">

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

	<div class="content">

		<!-- 	<div class="mainacttitle" style='padding: 10px 20px; background-color: #ECECEC;'> -->
		<!--將內容存-->

		<!-- 		<div style="padding: 500px 100px;"> -->

		<!-- 活動標題 -->
		<div class="container">
			<div class="slide-bar" id="slidebar">
				<div class="bar" id="bar"></div>
			</div>
			<div class="text-block" id="textone">
				<h1>活 動 好 康</h1>
			</div>
			<div class="text-block" id="texttwo">
				<h1>Activity</h1>
			</div>
		</div>
		<button id="changeBtn">&nbsp;</button>
		<!-- 	</div> -->


		<!-- 活動列表 -->
		<c:forEach var="actVO" items="${list}">
		<section id="cd-timeline" class="cd-container">
			<div class="cd-timeline-block">
				
				<div class="cd-timeline-img cd-picture"></div>
				<!-- cd-timeline-img -->
					<div class="cd-timeline-content">
						<h2>${actVO.act_subtitle}</h2>
						<p>${actVO.act_content}</p>
						<p>${actVO.act_date_start}</p>
<!-- 						<a href="#0" class="cd-read-more">Read more</a> <span -->
<!-- 							class="cd-date"></span> -->
							<p></p>
					</div>
					<!-- cd-timeline-content -->
				
			</div>
			</c:forEach>
			<!-- cd-timeline-block -->

			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-movie">
				</div>
				<!-- cd-timeline-img -->

				<div class="cd-timeline-content">
					<h2>Title of section 2</h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Iusto, optio, dolorum provident rerum aut hic quasi placeat iure
						tempora laudantium ipsa ad debitis unde?</p>
					<a href="#0" class="cd-read-more">Read more</a> <span
						class="cd-date">Jan 18</span>
				</div>
				<!-- cd-timeline-content -->
			</div>
			<!-- cd-timeline-block -->

			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-picture">
				</div>
				<!-- cd-timeline-img -->

				<div class="cd-timeline-content">
					<h2>Title of section 3</h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Excepturi, obcaecati, quisquam id molestias eaque asperiores
						voluptatibus cupiditate error assumenda delectus odit similique
						earum voluptatem doloremque dolorem ipsam quae rerum quis. Odit,
						itaque, deserunt corporis vero ipsum nisi eius odio natus ullam
						provident pariatur temporibus quia eos repellat consequuntur
						perferendis enim amet quae quasi repudiandae sed quod veniam
						dolore possimus rem voluptatum eveniet eligendi quis fugiat
						aliquam sunt similique aut adipisci.</p>
					<a href="#0" class="cd-read-more">Read more</a> <span
						class="cd-date">Jan 24</span>
				</div>
				<!-- cd-timeline-content -->
			</div>
			<!-- cd-timeline-block -->

			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-location">
				</div>
				<!-- cd-timeline-img -->

				<div class="cd-timeline-content">
					<h2>Title of section 4</h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Iusto, optio, dolorum provident rerum aut hic quasi placeat iure
						tempora laudantium ipsa ad debitis unde? Iste voluptatibus minus
						veritatis qui ut.</p>
					<a href="#0" class="cd-read-more">Read more</a> <span
						class="cd-date">Feb 14</span>
				</div>
				<!-- cd-timeline-content -->
			</div>
			<!-- cd-timeline-block -->

			<div class="cd-timeline-block">
				<div class="cd-timeline-img cd-location">
				</div>
				<!-- cd-timeline-img -->

				<div class="cd-timeline-content">
					<h2>Title of section 5</h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Iusto, optio, dolorum provident rerum.</p>
					<a href="#0" class="cd-read-more">Read more</a> <span
						class="cd-date">Feb 18</span>
				</div>
				<!-- cd-timeline-content -->
			</div>
			<!-- cd-timeline-block -->

			<div class="cd-timeline-block">
				<!-- 					<div class="cd-timeline-img cd-movie"> -->
				<!-- 						<img -->
				<!-- 							src="https://codyhouse.co/demo/vertical-timeline/img/cd-icon-movie.svg" -->
				<!-- 							alt="Movie"> -->
				<!-- 					</div> -->
				<!-- cd-timeline-img -->

				<div class="cd-timeline-content">
					<h2>Final Section</h2>
					<p>This is the content of the last section</p>
					<span class="cd-date">Feb 26</span>
				</div>
				<!-- cd-timeline-content -->
			</div>
			<!-- cd-timeline-block -->
		</section>
		<!-- cd-timeline -->








		<!-- </div> -->
	</div>
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