<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actdt.model.*"%>

<%
ActdtService actdtSvc = new ActdtService();
// List<ActdtVO> list = actdtSvc.getAll();
List<ActdtVO> list = actdtSvc.getOnlyAct();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>活動管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

<!-- 活動_css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/act/css/actback.css">

<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/back_end/act/css/FDINFBack.css"> --%>


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
						<div>所有活動</div>
					</div>
				</div>
				<div class="btBlock">
					<a class="bt"
						href='<%=request.getContextPath()%>/back_end/act/addAct.jsp'>新增</a>
<!-- 					<div></div> -->
<!-- 					<a class="bt" -->
<%-- 						href='<%=request.getContextPath()%>/back_end/act/select_page.jsp'>查詢</a> --%>



<%-- 					<FORM METHOD="post" ACTION=""<%=request.getContextPath()%>/act/act.do"> --%>
<!-- 						<b>選擇活動編號:</b> <select size="1" name="act_id"> -->
<%-- 							<c:forEach var="actdtVO" items="${list}"> --%>
<%-- 								<option value="${actdtVO.act_id}">${actdtVO.act_id} --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 						<input type="submit" value="送出"> -->
<!-- 					</FORM> -->

<%-- 					<FORM METHOD="post" ACTION=""<%=request.getContextPath()%>/act/act.do"> --%>
<!-- 						<b>輸入活動編號 (如1):</b> <input type="text" name="act_id" -->
<%-- 							value="${param.act_id}"><font color=red>${errorMsgs.act_id}</font> --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 						<input type="submit" value="送出"> -->
<!-- 					</FORM> -->

<%-- 					<FORM METHOD="post" ACTION=""<%=request.getContextPath()%>/act/act.do"> --%>
<!-- 						<b>選擇票種編號:</b> <select size="1" name="act_id"> -->
<%-- 							<c:forEach var="actdtVO" items="${list}"> --%>
<%-- 								<option value="${actdtVO.act_id}">${actdtVO.act_id} --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 						<input type="submit" value="送出"> -->
<!-- 					</FORM> -->
					
<%-- 					<FORM METHOD="post" ACTION=""<%=request.getContextPath()%>/act/act.do"> --%>
<!-- 						<b>輸入票種編號 (如1):</b> <input type="text" name="tkTypeID" -->
<%-- 							value="${param.tkTypeID}"><font color=red>${errorMsgs.tkTypeID}</font> --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 						<input type="submit" value="送出"> -->
<!-- 					</FORM> -->




				</div>



				<div class="TKouter">
					<table class="TKinner">
						<tr>
							<td style="width: 7%">編號</td>
							<td style="width: 15%">發布日期</td>
<!-- 							<td style="width: 8%">適用票種</td> -->
							<td style="width: 30%">標題</td>
							<td style="width: 20%">內容</td>
							<td style="width:  8%">圖片</td>
							<td style="width: 10%">修改</td>
							<td style="width: 10%">刪除</td>

						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="actdtVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${actdtVO.act_id}</td>
								<td>${actdtVO.act_date_start}</td>
<%-- 								<td>${actdtVO.tkTypeID}</td> --%>
								<td>${actdtVO.act_subtitle}</td>
								<td>${actdtVO.act_content}</td>
								<td><img src="<%=request.getContextPath()%>${actdtVO.act_picture}" style="width: 80px; height: 70px;"></td>
								<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/act/act.do"
										style="margin-bottom: 0px;">
										<input class="tablebt" type="submit" value="修改"> <input
											type="hidden" name="act_id" value="${actdtVO.act_id}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM></td>
								<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/act/act.do"
										style="margin-bottom: 0px;">
										<input class="tablebt" type="submit" value="刪除"> <input
											type="hidden" name="act_id" value="${actdtVO.act_id}">
										<input type="hidden" name="action" value="delete">
									</FORM></td>
							</tr>

						</c:forEach>


					</table>
				</div>

				<div class="btBlockpage">

					<%@ include file="page2.file"%>

				</div>

			</div>

		</div>
	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>

</body>
</html>