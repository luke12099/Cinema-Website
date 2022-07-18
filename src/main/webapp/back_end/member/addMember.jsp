<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
 MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_member_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
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
		 <h3>會員資料新增 - addmember.jsp</h3></td><td>
		 <h4><a href="/back/member/select_page.jsp">
		 <img src="" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/member.do" name="form1" enctype="multipart/form-data">

<table>

<tr>
		<td>會員名稱:</td>
		<td><input type="TEXT" name="member_Name" size="45" 
	               value="${param.member_Name}"/></td>
	</tr>

	<tr>
		<td>電子信箱:</td>
		<td><input type="TEXT" name="member_Email" size="45" 
	               value="${param.member_Email}"/></td>
	</tr>
	
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="member_Phone" size="45"
			 value="${param.member_Phone}"/></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="member_Password" size="45"
			 value="${param.member_Password}"/></td>
	</tr>
	
	<tr>
		<td>會員地址:</td>
		<td><input type="TEXT" name="member_Address" size="45"
			 value="${param.member_Address}"/></td>
	</tr>
	
	<tr>
		<td>再次密碼:</td>
		<td><input type="TEXT" name="member_Password" size="45"
			 value="${param.member_Password}"/></td>
	</tr>
	
	<tr>
		<td>會員照片:</td>
		<td> <input type="file"  name="myUpfile"></td>

	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="新增"></FORM>
</body>        
</html>