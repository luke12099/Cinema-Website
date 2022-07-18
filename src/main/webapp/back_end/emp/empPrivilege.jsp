<%@page import="java.util.*"%>
<%@page import="com.emp_privilege.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>個人資料維護</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/emp/css/empPrivilege.css">
</head>

<body>
    <header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">   
    	<%@ include file="/back_end/aside_html.jsp"%>     
    </aside>
    
<%--     <%  --%>
<!-- //     	List<Integer> hasFcNo = new ArrayList(); -->
<!-- //     	List<EmpPrivilegeVO> empPriVOs = (List<EmpPrivilegeVO>)request.getAttribute("empPriVOs"); -->
<!-- //     	if(empPriVOs != null){ -->
<!-- //     		for(EmpPrivilegeVO empPriVO: empPriVOs){ -->
<!-- //     			hasFcNo.add(empPriVO.getFc_no()); -->
<!-- //     		} -->
<!-- //     	} -->
<%--     %> --%>
    <!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
    <main>
        <div id="main">
        	<form method="post" action="${pageContext.request.contextPath}/emp/EmpPrivilege.do">
	            <h1>查詢/修改權限功能 - 員工編號: ${emp_no}</h1>
	            <button type="button" id="back"><a href="${pageContext.request.contextPath}/back_end/emp/empAcc.jsp">返回</a></button>
	            <span style="color: red;">${isOk}</span>
			    <div id="auth">
			    	<input type="checkbox" id="funcs0" onclick="chooseAll(this)">
			        <label for="funcs0" class="big">一般職員權限</label>
			    	<input type="checkbox" id="funcs1" onclick="chooseAll(this)">
			        <label for="funcs1" class="big">管理員權限</label>
			        <button type="button" id="clearAll">清空</button>
	                <button type="submit" name="action" value="updatePrivilege">修改</button>
	                <input type="hidden" name="emp_no" value="${emp_no}">
		        	<hr>
	<!-- 			        從BD抓所有權限 -->
			    	<jsp:useBean id="listF" scope="page" class="com.emp_function.model.EmpFunctionService"/>
			    	<div class="fc_block">
				    	<c:forEach var="function" items="${listF.all}" begin="0" step="3">
				    		<input type="checkbox" name="fc_nos" value="${function.fc_no}" class="funcs${function.fc_category}" id="func${function.fc_no}" <c:forEach var="empPriVO" items="${empPriVOs}"><c:if test="${function.fc_no == empPriVO.fc_no}">checked</c:if></c:forEach>>
	           				<label for="func${function.fc_no}">${function.fc_name}</label>
	           				<br>
				    	</c:forEach>
			    	</div>
			    	<div class="fc_block">
				    	<c:forEach var="function" items="${listF.all}" begin="1" step="3">
				    		<input type="checkbox" name="fc_nos" value="${function.fc_no}" class="funcs${function.fc_category}" id="func${function.fc_no}" <c:forEach var="empPriVO" items="${empPriVOs}"><c:if test="${function.fc_no == empPriVO.fc_no}">checked</c:if></c:forEach>>
	           				<label for="func${function.fc_no}">${function.fc_name}</label>
	           				<br>
				    	</c:forEach>
			    	</div>
			    	<div class="fc_block">
				    	<c:forEach var="function" items="${listF.all}" begin="2" step="3">
				    		<input type="checkbox" name="fc_nos" value="${function.fc_no}" class="funcs${function.fc_category}" id="func${function.fc_no}" <c:forEach var="empPriVO" items="${empPriVOs}"><c:if test="${function.fc_no == empPriVO.fc_no}">checked</c:if></c:forEach>>
	           				<label for="func${function.fc_no}">${function.fc_name}</label>
	           				<br>
				    	</c:forEach>
			    	</div>
			    </div>
		    </form>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="${pageContext.request.contextPath}/back_end/emp/js/empPrivilege.js"></script>
</body>

</html>