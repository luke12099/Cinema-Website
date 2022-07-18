<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <!DOCTYPE html> -->
<html lang="en" dir="ltr">

<head>
<title>HireMe</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/css/layout.css"
	type="text/css">

<link href="${pageContext.request.contextPath}/front_end/contactus/css/style.css" rel='stylesheet' type='text/css' />

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- Bootstrap icons-->
<!-- <link -->
<!-- 	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" -->
<!-- 	rel="stylesheet" /> -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


<script type="front_end/contactus/application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

<!----webfonts---->
<link
	href='http//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<!----//webfonts---->
<script src="front_end/contactus/js/jquery.min.js"></script>
<!----start-alert-scroller---->
<script type="text/javascript"
	src="front_end/contactus/js/jquery.easy-ticker.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#demo').hide();
		$('.vticker').easyTicker();
	});
</script>
<!----start-alert-scroller---->
<!-- start menu -->
<link href="front_end/contactus/css/megamenu.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="front_end/contactus/js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<script src="front_end/contactus/js/menu_jquery.js"></script>
<!-- //End menu -->
<!---move-top-top---->
<script type="text/javascript" src="front_end/contactus/js/move-top.js"></script>
<script type="text/javascript" src="front_end/contactus/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>
<!---//move-top-top---->



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
		<!--- start-content---->
		<div class="content contact-main">
			<!----start-contact---->
			<div class="contact-info">
				<div class="map">
					<img
						src="https://www.tanghedesign.com/wp-content/uploads/page_contact-us_bn.jpg"
						alt="" style="width: 1263px; height: 200px;">
				</div>
				<div
					style="width: 80%; padding-top: 3%; padding-right: 10%; padding-left: 10%"
					class="wrap">
					<div class="contact-grids">
						<div class="col_1_of_bottom span_1_of_first1"
							style="display: block; float: left; margin: 1% 0 1% 3.6%; width: 29.5%;">
							<h5 style="font-size:1.2em;">地址</h5>
							<p></p>
							<ul class="list3">
								<li><img
									src="${pageContext.request.contextPath}/front_back/contactus/images/home.png"
									alt="">
									<div class="extra-wrap">
										<p style="font-size:1.0em;"><i class="bi bi-house-door-fill"></i>台北市某某區提拔米路1號</p>
									</div></li>
							</ul>
						</div>
						<div class="col_1_of_bottom span_1_of_first1"
							style="display: block; float: left; margin: 1% 0 1% 3.6%; width: 29.5%;">
							<h5 style="font-size:1.2em;">電話</h5>
							<ul class="list3">
								<p></p>
								<li><img
									src="${pageContext.request.contextPath}/front_back/contactus/images/phone.png"
									alt="">
									<div class="extra-wrap">
										<p style="font-size:1.0em;"><i class="bi bi-telephone-fill"></i>02-2345678</p>
									</div></li>
							</ul>
						</div>
						<div class="col_1_of_bottom span_1_of_first1"
							style="display: block; float: left; margin: 1% 0 1% 3.6%; width: 29.5%;">
							<h5 style="font-size:1.2em;">信箱</h5>
								<p></p>
							<ul class="list3">
								<li><i class="ri-home-line"></i><img
									src="${pageContext.request.contextPath}/front_back/contactus/images/email.png"
									alt="">
									<div class="extra-wrap">
										<p>
											<span style="font-size:1.0em;"><i class="bi bi-envelope-fill"></i>staff@hireme.com</span>
										</p>
									</div></li>
							</ul>
						</div>
						<div class="clear"
							style="clear: both; margin: 0; padding: 0; border: 0; font: inherit; vertical-align: baseline;"></div>
					</div>
					<%-- <form method="post" action="contact-post.html" style="margin: 0; padding: 0; border: 0;
    font: inherit;
    vertical-align: baseline;"> --%>
					<div class="contact-form"
						style="background-color: #fff; border-radius: 4px; -webkit-box-shadow: 0 0 20px 3px rgba(0, 0, 0, 0.05); box-shadow: 0 0 20px 3px rgba(0, 0, 0, 0.05); padding: 40px; margin: auto;">
						<div class="contact-to">
							<input id="name" style="width: 300px; height: 44px;" type="text"
								class="text" value="Name..." onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Name...';}">
							<input id="email" style="width: 300px; height: 44px;" type="text"
								class="text" value="Email..." onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Email...';}">
							<input id="sub" style="width: 300px; height: 44px;" type="text"
								class="text" value="Subject..." onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Subject...';}">
						</div>
						<div class="text2">
							<textarea
								style="resize: none; width: 906px; height: 200px; margin-top: 20px;"
								value="Message:" onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'Message';}">留言...</textarea>
						</div>
						<span><button
								style="margin-top: 20px; background: #E45D5D; color: #FFF; padding: 0.9em 3em; display: inline-block; text-transform: uppercase; transition: 10s all; -webkit-transition: 10s all; -moz-transition: 10s all; -o-transition: 10s all; border-radius: 0.3em; -webkit-border-radius: 0.3em; -moz-border-radius: 0.3em; -o-border-radius: 0.3em; font-size: 0.875em; border-top: none; border-right: none; border-left: none; border-bottom: 4px solid #B93838; outline: none; cursor: pointer; font-family: 'Open Sans', sans-serif;"
								id="button" value="Submit">送出</button></span>
						<div class="clear"></div>
					</div>
					<%-- </form> --%>
				</div>
			</div>
			<!----//End-contact---->
		</div>








		<!-- 	</div> -->
		<!--   客服圖 請自行加連結-->

		<!--   <img class="cs" src="images/demo/cs.png" height="50px;" width="60px;" href="#"></img> -->

		<!-- Copyright -->
		<div class="wrapper row2">
			<footer id="copyright" class="clear">
				<p class="fl_left">
					Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
				</p>
			</footer>

			<!-- Bootstrap core JS-->

		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

		<script>
			document.getElementById("button").addEventListener("click",
					function() {
						swal("送出成功!", "", "success");
						setTimeout(function(){
						window.location.reload();
						}, 2000);
					});
		</script>
</body>




<!-- $("#button").click(function(){ -->
<!-- 	$("#name").text("00000000"); -->
<!-- }) -->
</html>
