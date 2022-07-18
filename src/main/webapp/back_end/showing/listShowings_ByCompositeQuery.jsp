<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.showing.model.*"%>


<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listShowings_ByCompositeQuery" scope="request" type="java.util.List<ShowingVO>" />
<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />


<html>
<head>
<title>複合查詢</title>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/css/listAllShowing.css">

</head>
<body bgcolor='white'>
	<header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">   
    	<%@ include file="/back_end/aside_html.jsp"%>     
    </aside>
	<main>
		<div id="main">

			<div id="title">
				<span>所有電影資料</span><br> <span><a
					href="<%=request.getContextPath()%>/back_end/showing/showing_select_page.jsp">回首頁</a></span>
			</div>


			<table id="myTable" class="tablesorter">
				<thead>
					<tr>
						<th>場次編號</th>
						<th>電影編號</th>
						<th>影廳編號</th>
						<th>場次狀態</th>
						<!-- 		<th>場次座位狀態</th> -->
						<th>時段</th>
						<th>電影播放類型</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				<%@ include file="pages/page1_ByCompositeQuery.file" %>
				<tbody>	
					<c:forEach var="showingVO" items="${listShowings_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align='center' valign='middle' ${(showingVO.SH_ID==param.SH_ID) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
							<td>${showingVO.SH_ID}</td>
							<td><c:forEach var="movieVO" items="${movieSvc.all}">
									<c:if test="${showingVO.mvId==movieVO.mvId}">${movieVO.mvId}【${movieVO.mvName}】
                    </c:if>
								</c:forEach></td>
							<td>${showingVO.HL_ID} 【${showingVO.hallVO.hlName}】</td>
							<td>${showingVO.SH_STATE}</td>
							<td><fmt:formatDate value="${showingVO.SH_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>${showingVO.SH_TYPE}</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/showing/showing.do" style="margin-bottom: 0px;">
									<input type="submit" value="修改">
									<input type="hidden" name="SH_ID" value="${showingVO.SH_ID}"> 
									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     					<input type="hidden" name="whichPage"	value="<%=whichPage%>"> 			  <!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/showing/showing.do" style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> 
									<input type="hidden" name="SH_ID" value="${showingVO.SH_ID}"> 
									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     					<input type="hidden" name="whichPage"	value="<%=whichPage%>"> 			  <!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				<tbody>
			</table>
			<%@ include file="pages/page2_ByCompositeQuery.file" %>
		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>
<%-- 	<script src="<%=request.getContextPath()%>/back_end/showing/emp_aside.js"></script> --%>

	<!-- 表格排序 -->
	<script
		src='//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'></script>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/css/theme.bootstrap.min.css"></link>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js"></script>

	<script>
		$("#myTable").tablesorter({
			theme : "bootstrap",
			widgets : [ 'zebra' ]
		});
	</script>
</body>
</html>