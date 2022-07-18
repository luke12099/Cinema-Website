<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
 MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

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
		 <h3>員工資料 - ListOneMember.jsp</h3>
		 <h4><a href="${pageContext.request.contextPath}/back/member/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員等級</th>
		<th>會員電子信箱</th>
		<th>會員密碼</th>
		<th>會員名稱</th>
		<th>會員電話</th>
		<th>會員地址</th>
		<th>會員照片</th>
		<th>會員狀態</th>
		<th>許願票張數</th>
		<th>紅利點數</th>
		<th>累計購票數</th>
	</tr>
	<tr>
	    <td><%=memberVO.getMember_ID() %></td>
	    <td><%=memberVO.getMember_Level() %></td>
	    <td><%=memberVO.getMember_Email() %></td>
		<td><%=memberVO.getMember_Password() %></td>
		<td><%=memberVO.getMember_Name() %></td>
		<td><%=memberVO.getMember_Phone() %></td>
		<td><%=memberVO.getMember_Address() %></td>
		<td><%=memberVO.getMember_Pic() %></td>
		<td><%=memberVO.getMember_Status() %></td>
		<td><%=memberVO.getWish_Ticket() %></td>
		<td><%=memberVO.getBonus_Points() %></td>
		<td><%=memberVO.getSum_Count() %></td>
	</tr>
</table>

</body>
</html>