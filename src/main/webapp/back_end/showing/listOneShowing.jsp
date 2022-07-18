<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.showing.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ShowingVO showingVO = (ShowingVO) request.getAttribute("showingVO"); //ShowingServlet.java(Controller), 存入req的showingVO物件
%>

<html>
<head>
<title>員工資料 - listOneShowing.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneShowing.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/showing/showing_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>場次編號</th>
		<th>電影編號</th>
		<th>影廳編號</th>
		<th>場次狀態</th>
		<th>場次座位狀態</th>
		<th>時段</th>
		<th>電影播放類型</th>
	</tr>
	<tr>
		<td><%=showingVO.getSH_ID()%></td>
		<td><%=showingVO.getmvId()%></td>
		<td><%=showingVO.getHL_ID()%></td>
		<td><%=showingVO.getSH_STATE()%></td>
		<td><%=showingVO.getSH_SEAT_STATE()%></td>
		<td><%=showingVO.getSH_TIME()%></td>
		<td><%=showingVO.getSH_TYPE()%></td>
	</tr>
</table>

</body>
</html>