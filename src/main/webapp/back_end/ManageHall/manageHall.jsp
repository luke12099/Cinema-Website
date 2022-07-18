<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hall.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	HallService hallSvc = new HallService();
	List<HallVO> list = hallSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>影廳資訊管理</title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <!-- ----------------------------------------------------------------------------------------------- -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
	    
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
        <div id="main">

            <div class="table_body" >
                <div class="btnbox" style="margin-bottom:10px">
                    <a href="${pageContext.request.contextPath}/back_end/ManageHall/createHall.jsp" class="btn btn-outline-success"  >新建影廳</a>
                    <a href="${pageContext.request.contextPath}/back_end/ManageSeat/manageSeat.jsp" class="btn btn-outline-info"  >座位管理</a>
                </div>    
                <div class="table_container">
                   <table class="table table-hover table-bordered single-ellipsis"">
                       <thead>
                           <tr>
                               <th>影廳編號</th>
                               <th>影廳名稱</th>
                               <th>影廳排數</th>
                               <th>影廳列數</th>
                               <th>影廳類型</th>
                               <th>影廳座位數</th>
                               <th>功能區</th>
                           </tr>
                       </thead>
                       <tbody>
                       <%@ include file="page1.file" %>
                       <c:forEach var="HallVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	                       	<tr>
	                               <td>${HallVO.hlId}</td>
	                               <td>${HallVO.hlName}</td>
	                               <td>${HallVO.hlRow}</td>
	                               <td>${HallVO.hlCol}</td>
	                               <c:if test="${HallVO.hlType==0}">
                          				<td>數位</td>
                      			   </c:if> 
                      			   <c:if test="${HallVO.hlType==1}">
                          				<td>IMAX</td>
                      	           </c:if> 
	                               <td>${HallVO.hlSeatCount}</td>
	                               <td >
	                               		<form METHOD="post" action="${pageContext.request.contextPath}/HallServlet.do">
	                               		<input type="submit" value="編輯資訊"  class="btn btn-outline-primary">
			     						<input type="hidden" name="hlId"  value="${HallVO.hlId}">
			     						<input type="hidden" name="action"	value="getOne_For_Update">
	                               		</form>
	                               </td>
	                           </tr>
                       
                       
                       </c:forEach> 
                           
                       </tbody>
                   </table>
                   <%@ include file="page2.file" %>
                   </div>
            </div>

        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
</body>

</html>