<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //存入req的memberVO物件
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<title>HireMe</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/membercentre/css/layout.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://fontawesome.com/v5/icons/edit?s=solid">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/membercentre/css/membercentre.css" />
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<%@ include file="/front_end/header.jsp"%>
<%@ include file="/front_end/header_css.jsp"%>

<style>
#memInfo {
	/*       border: 2px solid purple; */
	width: 100%;
}

body {
	background-color: white;
	/* background: url("img/register_bg.png") no-repeat center; */
	/* padding-top: 25px; */
}

.rg_layout {
	width: 900px;
	height: 600px;
	border: 8px solid #EEEEEE;
	background-color: white;
	margin: auto;
}

.rg_left {
	/*border: 1px solid red;*/
	float: left;
	margin: 15px;
}

.rg_left>p:first-child {
	color: #FFD026;
	font-size: 20px;
}

.rg_left>p:last-child {
	color: #A6A6A6;
	font-size: 20px;
}

.rg_center {
	float: left;
	/* border: 1px solid red;*/
}

.rg_right {
	/*border: 1px solid red;*/
	float: right;
	margin: 15px;
}

.rg_right>p:first-child {
	font-size: 15px;
}

.rg_right p a {
	color: pink;
}

.td_left {
	width: 100px;
	text-align: right;
	height: 45px;
}

.td_right {
	padding-left: 50px;
}

#username, #password, #email, #name, #tel, #birthday, #checkcode {
	width: 251px;
	height: 32px;
	border: 1px solid #A6A6A6;
	/*設定邊框圓角*/
	border-radius: 5px;
	padding-left: 10px;
}

#img_check {
	height: 32px;
	vertical-align: middle;
}

#btn_sub {
	width: 150px;
	height: 40px;
	background-color: #FFD026;
	border: 1px solid #FFD026;
}

.error {
	color: red;
}
</style>
</head>


<body>


	<div id="mainDiv">

		<div class="side-menu">
			<nav>
				<a
					href="${pageContext.request.contextPath}/front_end/membercentre/membermod.jsp">
					<i class="fa fa-edit" aria-hidden="true"></i> 會員修改資料
				</a> <a href="${pageContext.request.contextPath}/front_end/wish/wishPage.jsp"> <i class="fa fa-object-group" aria-hidden="true"></i>
					許願池
				</a> <a href="${pageContext.request.contextPath}/front_end/memberCmt/memberCmt.jsp"> <i class="fa fa-clone" aria-hidden="true"></i> 評論區
				</a>
				 <a href="${pageContext.request.contextPath}/front_end/merchandise/merchOrd_index.jsp"> <i class="fa fa-gavel" aria-hidden="true"></i> 訂單明細</a>
			</nav>
		</div>
		<!-- <div id="content">
    <iframe src="" width="100%" height="100%" frameborder="0"></iframe>
  </div> -->
		<div id="memInfo">
			<div class="text-block" id="textone"><h1>會員中心</h1></div>
			<div class="rg_center">
				<div class="rg_form">
					<!--定義表單 form-->

					<form action="#" id="form" method="get">
						<table>
							<tr>
								<td class="td_left"><label for="MEMBER_ID">會員編號</label></td>
								<td class="td_right">${memberVO.member_ID}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="MEMBER_LEVEL">會員等級</label></td>
								<td class="td_right">${memberVO.member_Level}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="MEMBER_EMAIL">電子信箱</label></td>
								<td class="td_right">${memberVO.member_Email}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="MEMBER_NAME">會員名稱</label></td>
								<td class="td_right">${memberVO.member_Name}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="MEMBER_PHONE">會員電話</label></td>
								<td class="td_right">${memberVO.member_Phone}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="MEMBER_ADDRESS">會員地址</label></td>
								<td class="td_right">${memberVO.member_Address}</td>
							</tr>

							<tr>
                  <td class="td_left"><label for="MEMBER_PIC">會員照片</label></td>
                   <td class="td_right"><img src=<%=request.getContextPath()%>${memberVO.member_Pic}></td>
                  
              </tr>


							<tr>
								<td class="td_left"><label for="WISH_TICKET">許願票張數</label></td>
								<td class="td_right">${memberVO.wish_Ticket}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="BONUS_POINTS">紅利點數</label></td>
								<td class="td_right">${memberVO.bonus_Points}</td>
							</tr>

							<tr>
								<td class="td_left"><label for="SUM_COUNT">累計購票數</label></td>
								<td class="td_right">${memberVO.sum_Count}</td>
							</tr>


						</table>

					</form>

				</div>

			</div>
		</div>








	</div>

	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>
	</div>
	
	<script>
	<c:if test="${not empty situation.update}">
    Swal.fire(
               'YA',
               '修改成功!!',
               'success'
           )
         <% request.removeAttribute("update"); %>
    </c:if>
	
	</script>

</body>

</html>