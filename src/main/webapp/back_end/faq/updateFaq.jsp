<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.faq.model.*"%>

<%
FaqVO faqVO = (FaqVO) request.getAttribute("faqVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
	href="<%=request.getContextPath()%>/back_end/faq/css/faqback_update.css">

<!-- 內容文本 -->
<script src="https://cdn.ckeditor.com/4.7.3/basic/ckeditor.js"></script>

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
						<div>修改常見問題</div>
					</div>
				</div>

				<FORM METHOD="post" name="form1" enctype="multipart/form-data"
					ACTION="<%=request.getContextPath()%>/faq/faq.do" name="form1">
					<div class="TKouter">

						<table class="TKinner">
							<tr>
								<td></td>
								<td>輸入</td>
							</tr>
							<tr>
								<td>常見問題編號:</td>
								<td>${faqVO.faq_no}</td>
							</tr>
							<tr>
								<td>類別:</td>
								<td><select name="faq_class">
										<option value="0">會員相關問題</option>
										<option value="1">影城相關問題</option>
										<option value="2">電影上映相關問題</option>
										<option value="3">其他問題</option>
								</select></td>
							</tr>
							<tr>
								<td>標題:</td>
								<td><input style="text-align: center;" type="text" name="faq_title" size="45"
									value="${faqVO.faq_title}"></td>
							</tr>
							<tr>
								<td>內容:</td>
								<td><textarea id="faq_content" name="editor1" cols="50"
										rows="10">${faqVO.faq_content}</textarea> <script>
											CKEDITOR.replace('editor1');
										</script></td>
							</tr>

						</table>
					</div>
					<div class="btBlock">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="faq_no" value="${faqVO.faq_no}">
						<input type="submit" class="bt" value="送出修改">


					</div>
				</FORM>
			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>


</body>

</html>