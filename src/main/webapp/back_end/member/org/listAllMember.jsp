<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
//     pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllMember.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back/member/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
<%-- <%@ include file="page1.file" %>  --%>
	<c:forEach var="memberVO" items="<%=list %>">
		
		<tr>
			<td>${memberVO.member_ID}</td>
			<td>${memberVO.member_Level}</td>
			<td>${memberVO.member_Email}</td>
			<td>${memberVO.member_Password}</td> 
			<td>${memberVO.member_Name}</td>
			<td>${memberVO.member_Phone}</td>
			<td>${memberVO.member_Address}</td>
			<td><img src="/HireMe/image/${memberVO.member_Pic}"></td>
<%-- 			${pageContext.request.contextPath}<img src="\HireMe\image"> --%>
			
			
			<td>${memberVO.member_Status}</td>
			<td>${memberVO.wish_Ticket}</td>
			<td>${memberVO.bonus_Points}</td>
			<td>${memberVO.sum_Count}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.do" style="margin-bottom: 0px;">
<select>
	<option>停權</option>
	<option>復權</option>
</select>			     
			     <input type="hidden" name="member_ID"  value="${memberVO.member_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="儲存">
			     <input type="hidden" name="member_ID"  value="${memberVO.member_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		</c:forEach>
<!-- </table> -->
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>