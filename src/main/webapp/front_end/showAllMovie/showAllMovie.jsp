<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	MovieService mvSvc = new MovieService();
	List<MovieVO> showingList = mvSvc.getShowingMV();
	List<MovieVO> comingList = mvSvc.getComingMV();
	pageContext.setAttribute("showingList", showingList);
	pageContext.setAttribute("comingList", comingList);
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <title>showAllMovie</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/showAllMovie/styles/allMovie.css">
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>

<body>
  <!-- 置頂按鈕 -->
  <button type="button" id="BackTop" class="toTop-arrow"></button>
  <script>
    $(function () {
      $('#BackTop').click(function () {
        $('html,body').animate({ scrollTop: 0 }, 333);
      });
      $(window).scroll(function () {
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
    <div class="fm1" style = 'padding:10px 20px; background-color:#ECECEC;'>
      <!--將內容存-->
      <div class="mytabs">
        <input type="radio" id="show" name="mytabs" checked="checked">
        <label for="show">上映中</label>
        <div class="container">
         <c:forEach var="showingVO" items="${showingList}" >
            <div class="content">
                <div class="cover">
                    <img src="${pageContext.request.contextPath}${showingVO.mvPicture}" alt="">
                </div>
                <div class="info_container">
                    <div class="info">
                        <div class="name">${showingVO.mvName}</div>
                        <div class="ename">${showingVO.mvEName}</div>
                        <div class="stDate">上映日期:${showingVO.mvStDate}</div>
                        <div class="star" style="font-weight: bold;">
                        	<c:if test="${(showingVO.mvTtStar/showingVO.mvTtCm).isNaN()}">
                        	這部電影尚未有人評分
                            </c:if>
                        	<c:if test="${!(showingVO.mvTtStar/showingVO.mvTtCm).isNaN()}">
                        	<fmt:formatNumber value="${showingVO.mvTtStar/showingVO.mvTtCm}"
                        	minFractionDigits="1" maxFractionDigits="1"/>
                        	
                            <img src="/CGA102G1/front_end/showAllMovie/MV_ICON/star.png" alt="">
                            </c:if>
                        </div>
                    </div>
                    <div class="icon">
                        <img src="/CGA102G1/front_end/showAllMovie/MV_ICON/level${showingVO.mvLevel}.jpg" alt="">
                    </div>
                </div>
                <div class="bt">
                	<form action="${pageContext.request.contextPath}/MovieServlet.do" method="post">
                	<input type="hidden" name="mvId" value="${showingVO.mvId}">
                	<input type="hidden" name="action" value="getOneForDisplay">
                    <button type="submit">查看電影詳情</button>
                	</form>
                </div>
            </div>
            </c:forEach>
        </div>
        
        <input type="radio" id="soon" name="mytabs">
        <label for="soon">即將上映</label>
        <div class="container">
      		<c:forEach var="comingVO" items="${comingList}" >
            <div class="content">
                <div class="cover">
                    <img src="${pageContext.request.contextPath}${comingVO.mvPicture}" alt="">
                </div>
                <div class="info_container">
                    <div class="info">
                        <div class="name">${comingVO.mvName}</div>
                        <div class="ename">${comingVO.mvEName}</div>
                        <div class="stDate">預計上映:${comingVO.mvStDate}</div>
                    </div>
                    <div class="icon">
                        <img src="/CGA102G1/front_end/showAllMovie/MV_ICON/level${comingVO.mvLevel}.jpg" alt="">
                    </div>
                </div>
                <div class="bt">
                	<form action="${pageContext.request.contextPath}/MovieServlet.do" method="post">
                	<input type="hidden" name="mvId" value="${comingVO.mvId}">
                	<input type="hidden" name="action" value="getOneForDisplay">
                    <button type="submit">查看電影詳情</button>
                	</form>
                </div>
            </div>
            </c:forEach>
            
        </div>
      </div>
      
      
      </div>

  <!-- Copyright -->
  <div class="wrapper row2">
    <footer id="copyright" class="clear">
      <p class="fl_left">Copyright &copy; 2022 - All Rights Reserved <a href="#"></a></p>
    </footer>
  </div>

</body>

</html>