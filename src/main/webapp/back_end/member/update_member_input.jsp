<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%= memberVO==null %>--${memberVO.getMember_ID()}--${memberVO.member_ID}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_member_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料修改 - update_member_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back/member/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memberVO.getMember_ID()%></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="member_Password" size="45" value="<%=memberVO.getMember_Password()%>" /></td>
	</tr>
	<tr>
		<td>會員名稱:</td>
		<td><input type="TEXT" name="member_Name" size="45"	value="<%=memberVO.getMember_Name()%>" /></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="member_Phone" size="45"	value="<%=memberVO.getMember_Phone()%>" /></td>
	</tr>
	<tr>
		<td>會員地址:</td>
		<td><input type="TEXT" name="member_Address" size="45"	value="<%=memberVO.getMember_Address()%>" /></td>
	</tr>
	<tr>
		<td>會員照片:</td>
		<td><input type="file" name="member_Pic" size="45" value="<%=memberVO.getMember_Pic()%>" /></td>
<%-- 		<td><input type="TEXT" name="member_Pic" size="45" value="<%=memberVO.getMember_Pic()%>" /></td> --%>
	</tr>


<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="member_ID" value="<%=memberVO.getMember_ID()%>">
<input type="submit" value="送出修改"></FORM>
</body>
</html>