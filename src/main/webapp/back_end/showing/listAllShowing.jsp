<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.showing.model.*"%>
<%@ page import="com.hall.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ShowingService showingSvc = new ShowingService();
List<ShowingVO> list = showingSvc.getAll();
pageContext.setAttribute("list", list);

// HallService hallSvc = new HallService();
// List<HallVO> hallList = hallSvc.getAll();
// pageContext.setAttribute("hallList", hallList);

%>




<html>
<head>
<title>所有場次資料</title>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/showing/emp_all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/showing/emp_main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/showing/emp_footer.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/showing/css/listAllShowing.css">

</head>
<body bgcolor='white'>
	<header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">   
    	<%@ include file="/back_end/aside_html.jsp"%>     
    </aside>
	
	<main>
	
	<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
	
		<div id="main">
			<div id="title">
				<span>所有電影資料</span><br>
				<span><a href="<%=request.getContextPath()%>/back_end/showing/showing_select_page.jsp">回首頁</a></span>
			</div>

			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th>場次編號</th>
						<th>電影編號</th>
						<th>影廳編號</th>
						<th>場次狀態</th>
<!-- 						<th>場次座位狀態</th> -->
						<th>時段</th>
						<th>電影播放類型</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				<%@ include file="pages/page1.file"%>
				<tbody>
					<c:forEach var="showingVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 					<c:set var="STATE" scope="session" value="${showingVO.SH_STATE}"/> --%>
						<tr>
							<td>${showingVO.SH_ID}</td>
							<td>${showingVO.mvId} 【${showingVO.movieVO.mvName}】</td>
							<td>${showingVO.HL_ID} 【${showingVO.hallVO.hlName}】</td>
							<td>${showingVO.SH_STATE}</td>
<!-- 							<td> -->
<%-- 								<c:choose> --%>
<%-- 									<c:when test="${STATE = 0}"> --%>
<!-- 								       未滿位 -->
<%-- 								    </c:when> --%>
<%-- 									<c:when test="${STATE = 1}"> --%>
<!-- 								       已滿位 -->
<%-- 								    </c:when> --%>
<%-- 								</c:choose> --%>
<!-- 							</td> -->
							<td><fmt:formatDate value="${showingVO.SH_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${showingVO.SH_TYPE}</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/showing/showing.do" style="margin-bottom: 0px;">
									<input type="submit" value="修改">
									<input type="hidden" name="SH_ID" value="${showingVO.SH_ID}"> 
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->

									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/showing/showing.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> 
									<input type="hidden" name="SH_ID" value="${showingVO.SH_ID}"> 
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->

									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				<tbody>
			</table>
			<%@ include file="pages/page2.file"%>
		</div>
	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>
<%-- 	<script src="<%=request.getContextPath()%>/back_end/showing/emp_aside.js"></script> --%>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

	<!-- 表格排序 -->

	<!-- choose a theme file -->
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/tablesorter-master/dist/css/theme.blue.css"> --%>
	<!-- load jQuery and tablesorter scripts -->
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/tablesorter-master/dist/js/jquery-latest.js"></script> --%>
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/tablesorter-master/dist/js/jquery.tablesorter.js"></script> --%>

	<!-- tablesorter widgets (optional) -->
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/tablesorter-master/dist/js/jquery.tablesorter.widgets.js"></script> --%>

	<script src='//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/css/theme.bootstrap.min.css"></link>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js"></script>

	<script>
		$("#myTable").tablesorter({
			theme : "bootstrap",
			widthFixed: true,
		    widgets: ['zebra', 'filter']
		});
	</script>

</body>
</html>