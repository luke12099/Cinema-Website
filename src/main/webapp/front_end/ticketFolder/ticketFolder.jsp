<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*" %>
<%@ page import="com.tk_ord.model.*" %>
<%@ page import="com.changeSeat.*" %>
<%@ page import="com.hall.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
 Integer member_ID= (Integer)request.getAttribute("member_ID");
 Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
 request.setAttribute("map", map);
 List<TkOrdVO> OrdVOList = (List<TkOrdVO>)map.get("OrdVOList");
 List<ShowSeatVO> showList = (List<ShowSeatVO>)map.get("showList");
 List<HallVO> hlList = (List<HallVO>)map.get("hlList");
 List<MovieVO> mvList = (List<MovieVO>)map.get("mvList");
 List<Integer> dtCountList =(List<Integer>)map.get("dtCountList");
 List<Integer> foodCountList =(List<Integer>)map.get("foodCountList");
 
 request.setAttribute("OrdVOList", OrdVOList);
 request.setAttribute("showList", showList);
 request.setAttribute("hlList", hlList);
 request.setAttribute("mvList", mvList);
 request.setAttribute("dtCountList", dtCountList);
 request.setAttribute("foodCountList", foodCountList);
 
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
  <title>我的票夾</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/ticketFolder/ticketFolder.css">
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
 <div class="order_container">
 	<div class="mytabs">
        <input type="radio" id="show" name="mytabs" checked="checked">
        <label for="show">查看票卷</label>
        <div class="container">
        	<c:forEach items="${OrdVOList}" var="OrdVO" varStatus="loop">
            <div class="content">
                <div class="cover">
                    <img src="${pageContext.request.contextPath}${mvList[loop.index].mvPicture}" alt="">
                </div>
                <div class="info_container">
                    <div class="info">
                        <div class="mvName">${mvList[loop.index].mvName}</div>
                        <div class="hlName">${hlList[loop.index].hlName}</div>
                        <div class="shDate">
                        場次:
                        <fmt:formatDate pattern="yyyy-MM-dd H點m分" value="${showList[loop.index].SH_TIME}"/>
                        </div>
                        <div class="icon_container">
	                    	<div class="ticket_icon">
	                    	<img src="/CGA102G1/front_end/ticketFolder/ticket2.png" alt="">
	                    	x ${dtCountList[loop.index]}
	                    	</div>
	                    	<div class="food_icon">
	                    	<img src="/CGA102G1/front_end/ticketFolder/food.png" alt="">
	                    	x ${foodCountList[loop.index]}
	                    	</div>
                    	</div>
                    </div>
                </div>
                <div class="bt">
                	<form action="${pageContext.request.contextPath}/TkFolderServlet.do" method="post">
                		<input type="hidden" name="tkOrdID" value="${OrdVOList[loop.index].tkOrdID}">
                		<input type="hidden" name="member_ID" value="${member_ID}">
                		<input type="hidden" name="action" value="getOneDetail">
                    	<button type="submit">查看訂單詳情</button>
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