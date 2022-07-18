<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有會員資料</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/member/styles/FDINFBack.css">


</head>


<body>
	<header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
	<aside id="aside">     
     <%@ include file="/back_end/aside_html.jsp"%>   
    </aside>
	<!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
	<main>
		<div class="all">
			<div class="main">

				<div class="guide1outer">
					<div class="guide1">
						<div>所有會員資料</div>
					</div>
				</div>
				<div class="btBlock">
<!-- 					<a class="bt" -->
<%-- 						href='<%=request.getContextPath()%>/back/fd_inf/addFdInf.jsp'>新增</a> --%>
				</div>

				<div class="TKouter">

					<table class="TKinner">
						<tr>
							<td>編號</td>
							<td>名稱</td>
							<td>等級</td>
							<td>電子信箱</td>
							<td>密碼</td>
							<td>電話</td>
							<td>照片</td>
							<td>狀態</td>
							<td>許願票數</td>
							<td>紅利點數</td>
							<td>累計購票數</td>
							<td>啟用</td>
							<td>儲存</td>

						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${memberVO.member_ID}</td>
								<td>${memberVO.member_Name}</td>
								<td>${memberVO.member_Level}</td>
								<td>${memberVO.member_Email}</td>
								<td>${memberVO.member_Password}</td>
								<td>${memberVO.member_Phone}</td>
								<td><img src="<%=request.getContextPath()%>${memberVO.member_Pic}"></td>
								<%-- 			${pageContext.request.contextPath}<img src="\HireMe\image"> --%>
								<td>
									${memberVO.member_Status==0?'未啟用':''}
									${memberVO.member_Status==1?'啟用':''}
									${memberVO.member_Status==2?'停權':''}
								</td>
								<td>${memberVO.wish_Ticket}</td>
								<td>${memberVO.bonus_Points}</td>
								<td>${memberVO.sum_Count}</td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.do" style="margin-bottom: 0px;">
								<td>
								
								 <!--  selected是代表當下表格 -->
										<select name="member_Status">
											<option value="0" >未啟用</option> 
											<option value="1" >啟用</option> 
											<option value="2" >停權</option> 
																		
										</select> 



								</td>
								<td>

										<input type="hidden" name="member_ID" value="${memberVO.member_ID}">
										<input type="hidden" name="action" value="revise">
										<input type="submit" value="儲存">
								</td>
									</FORM>
							</tr>
							<tr>
								<td class="Addresshead">地址</td>
								<td></td>
								<td></td>
								<td class="Address">${memberVO.member_Address}</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>

						</c:forEach>


					</table>
				</div>
				<div class="btBlock">

					<%@ include file="page2.file"%>

				</div>


			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>
</body>
</html>