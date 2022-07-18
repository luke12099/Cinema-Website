<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>

<%
FaqService faqSvc = new FaqService();
List<FaqVO> list = faqSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>常見問題管理</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/faq/css/faqback.css">

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
						<div>所有常見問題</div>
					</div>
				</div>
				<div class="btBlock">
					<a class="bt"
						href='<%=request.getContextPath()%>/back_end/faq/addFaq.jsp'>新增</a>
				</div>

<%-- 				<jsp:useBean id="faqSvc" scope="page" class="com.faq.model.FaqService" /> --%>
<!-- 				<li> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/faq/faq.do"> --%>
<!-- 						<b>選擇常見問題類別:</b> <select size="1" name="faq_class"> -->
<%-- 							<c:forEach var="faqVO" items="${faqSvc.all}"> --%>
<%-- 								<option value="${faqVO.faq_no}">${faqVO.faq_no} --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> <input type="submit" value="送出"> <input type="hidden" -->
<!-- 							name="action" value="getOne_For_Display"> -->
<!-- 					</FORM> -->
<!-- 				</li> -->

				<div class="TKouter">

					<table class="TKinner">
						<tr>
							<td style="width: 7%">編號</td>
							<td style="width: 15%">類別</td>
							<td style="width: 23%">標題</td>
							<td style="width: 30%">內容</td>
							<td style="width: 10%">修改</td>
							<td style="width: 10%">刪除</td>

						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="faqVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${faqVO.faq_no}</td>
								<td><c:choose>
										<c:when test="${faqVO.faq_class == 0}">
														會員相關問題
													</c:when>
										<c:when test="${faqVO.faq_class == 1}">
														影城相關問題
													</c:when>
										<c:when test="${faqVO.faq_class == 2}">
														電影上映相關問題
													</c:when>
										<c:when test="${faqVO.faq_class == 3}">
														其他問題
													</c:when>
									</c:choose></td>
								<td>${faqVO.faq_title}</td>
								<td>${faqVO.faq_content}</td>
								<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/faq/faq.do"
										style="margin-bottom: 0px;">
										<input class="tablebt" type="submit" value="修改"> <input
											type="hidden" name="faq_no" value="${faqVO.faq_no}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM></td>
								<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/faq/faq.do"
										style="margin-bottom: 0px;">
										<input class="tablebt" type="submit" value="刪除"> <input
											type="hidden" name="faq_no" value="${faqVO.faq_no}">
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