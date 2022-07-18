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
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/act/css/main.css" />


<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>

<!-- Bootstrap CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">


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

	<!-- 	<======================================main===============================================> -->
	<div class="actpost1">
		<img class="actpost"
			src="${pageContext.request.contextPath}/act_pic/actpost.jpg"
			height="100%" width="100%"  >
<!-- 		<div class="slide-down"> -->
<!-- 			<p>影城&nbsp; &nbsp; &nbsp;活動</p> -->
<!-- 		</div> -->
	</div>


	<div class="title h1 text-center"></div>


	<!-- Card Start -->
	<section>
		<c:forEach var="actdtVO" items="${list}">
			<div class="container py-3">
				<div class="card">
					<div class="row ">
						<div class="col-md-4">
							<img src="<%=request.getContextPath()%>${actdtVO.act_picture}"
								class="w-100" width="100" height="300">
						</div>
						<div class="col-md-8 px-3">
							<div class="card-block px-3">
								<h4 class="card-title">${actdtVO.act_subtitle}</h4>
								<p class="card-text">${actdtVO.act_content}</p>
							</div>
						</div>

					</div>
				</div>
			</div>
		</c:forEach>
	</section>




	<!--   客服圖 請自行加連結-->
	<!--   <img class="cs" src="images/demo/cs.png" height="50px;" width="60px;" href="#"></img> -->

	<!-- <====================================footer=====================================> -->
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