<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	ReportService rpSvc = new ReportService();
	List<ReportVO> reportList = rpSvc.getAll();
	pageContext.setAttribute("reportList", reportList);
	int undealCount = rpSvc.countUndealRP();
	pageContext.setAttribute("undealCount", undealCount);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>檢舉管理</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

    <!-- ***************************自己的CSS****************************** -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/ManageReport/report_main.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome-animation/0.2.1/font-awesome-animation.min.css">
    <!-- ***************************自己的CSS****************************** -->
</head>

<body>
     <header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">
    	<%@ include file="/back_end/aside_html.jsp"%>         
    </aside>
    <!-- ********************************************* -->
    <main>
        <div id="main">
            <div class="notifybox" >
        <c:if test="${undealCount > 0}">
                <div class="alertbox">
                    <div class="alert fade alert-simple alert-warning alert-dismissible text-left font__family-montserrat font__size-16 font__weight-light brk-library-rendered rendered show" role="alert" data-brk-library="component__alert">
                    <i class="start-icon fa fa-exclamation-triangle faa-flash animated"></i>
                    <strong class="font__weight-semibold">請注意!</strong>您有<strong class="font__weight-semibold">${undealCount}</strong>則檢舉尚未處理 !
                    </div>
                </div>
        </c:if>
        <c:if test="${undealCount == 0}">
                <div class="alertbox">
                    <div class="alert fade alert-simple alert-success alert-dismissible text-left font__family-montserrat font__size-16 font__weight-light brk-library-rendered rendered show">
                        <i class="start-icon far fa-check-circle faa-tada animated"></i>
                        <strong class="font__weight-semibold">太棒了!</strong> 目前沒有檢舉需要處理 !
                    </div>
                </div>
        </c:if>
            </div>
            <div class="tablebox" style="border:solid 0px red ;">
                <table id="maintable" class="table table-hover table-bordered single-ellipsis">
                    <thead>
                        <tr>
                            <th>檢舉單號</th>
                            <th>評論編號</th>
                            <th>檢舉人編號</th>
                            <th>檢舉類別</th>
                            <th>檢舉日期</th>
                            <th>檢舉狀態狀態</th>
                            <th>功能區</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%@ include file="page1.file" %> 
                    <c:forEach var="reportVO" items="${reportList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                        <tr>
                            <td>${reportVO.rpId}</td>
                            <td>${reportVO.cmId}</td>
                            <td>${reportVO.memberId}</td>
                            <td>${reportVO.rpType}</td>
                            <td><fmt:formatDate value="${reportVO.rpDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <c:if test="${reportVO.rpState==0}">
                            <td>未處理</td>
                            </c:if>
                            <c:if test="${reportVO.rpState==1}">
                            <td>已處理</td>
                            </c:if>
                            <td>
                            	<form METHOD="post" action="${pageContext.request.contextPath}/ReportServlet.do">
                            		<input type="hidden" name="action" value="getOne_For_Update">
                            		<input type="hidden" name="rpId" value="${reportVO.rpId}">
                            		<input type="hidden" name="cmId" value="${reportVO.cmId}">
                            		<input type="hidden" name="memberId" value="${reportVO.memberId}">
                            		<input type="submit" value="管理檢舉"  class="btn btn-secondary">
                            	</form>
                            </td> 
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <%@ include file="page2.file" %>
            </div>
        </div>
    </main>
    
    
    
    <!-- ********************************************* -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
</body>

</html>