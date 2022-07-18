<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hall.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HallVO hallVO = (HallVO)request.getAttribute("hallVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理原始座位</title>
   	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
     <!-- *************************************************************************** -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/ManageSeat/createHall.css">
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
     
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
        <div id="main" class="main">
            <div class="container1">
              <div class="createform" >
                            <div class="alert alert-warning" role="alert">
                                請注意!
                                <br>
                                本次修改不會影響已排場次
                            </div>
                        <form class="form1" id="formId" method="post" action="${pageContext.request.contextPath}/HallServlet.do">
                            <div class="form-group">
                              <label class="alert alert-light" style="margin-bottom:5px;width:100%" >影廳名稱:${hallVO.hlName}</label>
                              <c:if test="${hallVO.hlType==0}">
								<label class="alert alert-light" style="margin-bottom:5px;width:100%">影廳類型:數位</label>
							  </c:if>
							  <c:if test="${hallVO.hlType==1}">
								<label class="alert alert-light" style="margin-bottom:5px;width:100%">影廳類型:IMAX</label>
							  </c:if>
							  <input  class="alert alert-light" id="showCount" value="" style="margin-bottom:5px;width:100%" disabled>
                             </div>
                            <div class="btnbox">
                              
                              <input type="hidden" id="hlId" name="hlId" value="${hallVO.hlId}">
                              <input type="hidden" id="hlName" name="hlName" value="${hallVO.hlName}">
                              <input type="hidden" id="hlType" name="hlType" value="${hallVO.hlType}">
                              <input type="hidden" id="hlCol" name="hlCol" value="${hallVO.hlCol}">
                              <input type="hidden" id="hlRow" name="hlRow" value="${hallVO.hlRow}">
                              <input type="hidden" id="hlSeat" name="hlSeat" value="${hallVO.hlSeat}">
                              <input type="hidden" id="hlSeatCount" name="hlSeatCount" value="${hallVO.hlSeatCount}">
                              <input type="hidden" name="action" value="UpdateOriginalSeat">
                              <a onclick="history.back()" class="btn btn-danger" id="submitBtn" >取消</a>
                              <button type="submit" class="btn btn-primary" id="submitBtn" >送出修改</a>
                              
                            </div>
                          </form>
              </div>
              <div class="rightbox">
                <span class="badge badge-primary" style="width:100%; display:block" id="screen">螢幕</span>
                
                <div class="showseat" id="prBox">
                </div>
              </div>
               <div class="statusbox">
                <div class="alert alert-light" role="alert" id="numberBox" >
                  目前已選:
              </div>
                  <button type="button" value="0" class="btn btn-secondary" style="display:block" id="colorsample1">設為走道</button>
                  <br>
                  <button type="button" value="1" class="btn btn-success" style="display:block" id="colorsample2">設為座位</button>
                  <br>
                  <button type="button" value="3" class="btn btn-warning"style="display:block" id="colorsample3">設為保留</button>
                  <br>
                  <button type="button" value="4" class="btn btn-danger"style="display:block" id="colorsample4">設為維修</button> 
                  <br>
                </div> 
              </div>
            </div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="${pageContext.request.contextPath}/back_end/ManageSeat/editOrigin.js"></script>
</body>

</html>