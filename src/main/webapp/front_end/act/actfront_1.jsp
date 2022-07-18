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
<!-- <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> -->

<!-- ********************************************************************** -->

<script type="text/javascript"
	src="${pageContext.request.contextPath}/front_end/act/booklet/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/front_end/act/booklet/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/front_end/act/booklet/jquery.booklet.1.1.0.min.js"></script>
<link
	href="${pageContext.request.contextPath}/front_end/act/booklet/jquery.booklet.1.1.0.css"
	type="text/css" rel="stylesheet" media="screen" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/act/css/style.css"
	type="text/css" media="screen" />


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

	<div class="book_wrapper">
		<a id="next_page_button"></a> <a id="prev_page_button"></a>
		<div id="loading" class="loading">Loading pages...</div>
		<div id="mybook" style="display: none;">
			<div class="b-load">
				<div>
					<img src="images/1.jpg" alt="" />
					<h1>Slider Gallery</h1>
					<p>This tutorial is about creating a creative gallery with a
						slider for the thumbnails. The idea is to have an expanding
						thumbnails area which opens once an album is chosen. The
						thumbnails will scroll to the end and move back to the first
						image. The user can scroll through the thumbnails by using the
						slider controls. When a thumbnail is clicked, it moves to the
						center and the full image preview opens.</p>
					<a href="http://sc.chinaz.com/" target="_blank" class="article">Article</a>
					<a href="http://sc.chinaz.com/" target="_blank" class="demo">Demo</a>
				</div>
			</div>
		</div>
	</div>

	<!-- 	<======================================main end===============================================> -->


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

	<!-- The JavaScript -->

	<script type="text/javascript">
		(function() {
			var $mybook = $('#mybook');
			var $bttn_next = $('#next_page_button');
			var $bttn_prev = $('#prev_page_button');
			var $loading = $('#loading');
			var $mybook_images = $mybook.find('img');
			var cnt_images = $mybook_images.length;
			var loaded = 0;
			//preload all the images in the book,
			//and then call the booklet plugin

			$mybook_images.each(function() {
				var $img = $(this);
				var source = $img.attr('src');
				$('<img/>').load(function() {
					++loaded;
					if (loaded == cnt_images) {
						$loading.hide();
						$bttn_next.show();
						$bttn_prev.show();
						$mybook.show().booklet({
							name : null, // name of the booklet to display in the document title bar
							width : 800, // container width
							height : 500, // container height
							speed : 600, // speed of the transition between pages
							direction : 'LTR', // direction of the overall content organization, default LTR, left to right, can be RTL for languages which read right to left
							startingPage : 0, // index of the first page to be displayed
							easing : 'easeInOutQuad', // easing method for complete transition
							easeIn : 'easeInQuad', // easing method for first half of transition
							easeOut : 'easeOutQuad', // easing method for second half of transition

							closed : true, // start with the book "closed", will add empty pages to beginning and end of book
							closedFrontTitle : null, // used with "closed", "menu" and "pageSelector", determines title of blank starting page
							closedFrontChapter : null, // used with "closed", "menu" and "chapterSelector", determines chapter name of blank starting page
							closedBackTitle : null, // used with "closed", "menu" and "pageSelector", determines chapter name of blank ending page
							closedBackChapter : null, // used with "closed", "menu" and "chapterSelector", determines chapter name of blank ending page
							covers : false, // used with  "closed", makes first and last pages into covers, without page numbers (if enabled)

							pagePadding : 10, // padding for each page wrapper
							pageNumbers : true, // display page numbers on each page

							hovers : false, // enables preview pageturn hover animation, shows a small preview of previous or next page on hover
							overlays : false, // enables navigation using a page sized overlay, when enabled links inside the content will not be clickable
							tabs : false, // adds tabs along the top of the pages
							tabWidth : 60, // set the width of the tabs
							tabHeight : 20, // set the height of the tabs
							arrows : false, // adds arrows overlayed over the book edges
							cursor : 'pointer', // cursor css setting for side bar areas

							hash : false, // enables navigation using a hash string, ex: #/page/1 for page 1, will affect all booklets with 'hash' enabled
							keyboard : true, // enables navigation with arrow keys (left: previous, right: next)
							next : $bttn_next, // selector for element to use as click trigger for next page
							prev : $bttn_prev, // selector for element to use as click trigger for previous page

							menu : null, // selector for element to use as the menu area, required for 'pageSelector'
							pageSelector : false, // enables navigation with a dropdown menu of pages, requires 'menu'
							chapterSelector : false, // enables navigation with a dropdown menu of chapters, determined by the "rel" attribute, requires 'menu'

							shadows : true, // display shadows on page animations
							shadowTopFwdWidth : 166, // shadow width for top forward anim
							shadowTopBackWidth : 166, // shadow width for top back anim
							shadowBtmWidth : 50, // shadow width for bottom shadow

							before : function() {
							}, // callback invoked before each page turn animation
							after : function() {
							} // callback invoked after each page turn animation
						});
					}
				}).attr('src', source);
			});

		});
	</script>

</body>

</html>