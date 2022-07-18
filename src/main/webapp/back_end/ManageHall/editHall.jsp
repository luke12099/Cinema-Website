<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hall.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	HallVO hallVO = (HallVO)request.getAttribute("hallVO");
	String typeArr[] = {"數位","IMAX"};
	request.setAttribute("typeArr", typeArr);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>編輯影廳</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
   <!-- *************************************************************************** -->
     <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/ManageHall/createHall.css">
     
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
                        <form METHOD="post" ACTION="${pageContext.request.contextPath}/HallServlet.do" class="form1" id="formId">
                            <div class="form-group">
                              <label for="exampleFormControlInput1" >影廳名稱:</label>
                              <input type="text" class="form-control" id="nameId" name="hlName" value="${hallVO.hlName}"  placeholder="請輸入名稱" >
                            </div>
                            <div class="form-group">
                              <label for="exampleFormControlSelect1">影廳類型:</label>
                              <select class="form-control" id="exampleFormControlSelect1" name="hlType" value="${hallVO.hlType}">
                              	<c:forEach var="item" items="${typeArr}" varStatus="levelindex">
        						<option value="${levelindex.index}" ${levelindex.index == hallVo.hlType ? 'selected="selected"' : ''}>${item}</option>
    							</c:forEach>
                              </select>
                            </div>
                            <div class="btnbox">
                              <!-- <button type="reset" class="btn btn-success" id="previewBtn">重設</button> -->
                              <input type="hidden" name="action" value="update">
                    		  <input type="hidden" name="hlId" value="${hallVO.hlId}">
                    		  <input type="hidden" name="hlSeat" id="hlSeat" value="${hallVO.hlSeat}">
                              <input type="hidden" name="hlCol" id="hlCol" value="${hallVO.hlCol}">
                              <input type="hidden" name="hlRow" id="hlRow" value="${hallVO.hlRow}">
                              <input type="hidden" name="hlSeatCount" id="hlSeatCount" value="${hallVO.hlSeatCount}">
                    		  
                    		  <button type="submit" id="sumbitBtn" class="btn btn-primary">送出修改</button>
                    		  <a onclick="history.back()" class="btn btn-danger" id="submitBtn" >退出</a>
                            </div>
                          </form>
              </div>
              <div class="rightbox">
                <span class="badge badge-primary" style="width:100%; display:block" id="screen">螢幕</span>
               
                <div class="showseat" id="prBox">
                </div>
              </div>
               <div class="statusbox">
                  <button type="button" class="btn btn-secondary" style="display:block" id="colorsample1">走道</button>
                  <button type="button" class="btn btn-success" style="display:block" id="colorsample2">座位</button>
                  <button type="button" class="btn btn-warning"style="display:block" id="colorsample3">保留</button>
                  <button type="button" class="btn btn-danger"style="display:block" id="colorsample4">維修</button>
                </div> 
              </div>
            </div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/back_end/ManageHall/editHall.js"></script>
</body>

</html>