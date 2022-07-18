<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hall.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String typeArr[] ={"數位","IMAX"};
	String colArr[] = {"1", "2","3", "4", "5" ,"6" ,"7" ,"8" ,"9","10", "11","12"};
	String rowArr[] = {"1", "2","3", "4", "5" ,"6" ,"7" ,"8" ,"9","10", "11","12","13","14", "15"};
	request.setAttribute("typeArr", typeArr);
	request.setAttribute("colArr", colArr);
	request.setAttribute("rowArr", rowArr);
%>
<!-- 這支是標準樣板 -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新建影廳</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <!-- *************************************************************************** -->
    
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
                        <form class="form1" id="formId" METHOD="post" action="${pageContext.request.contextPath}/HallServlet.do">
                            <div class="form-group">
                              <label for="nameId" >影廳名稱:</label>
                              <input type="text" class="form-control" id="nameId"  placeholder="請輸入名稱" name="hlName" value="" required>
                            </div>
                            <div class="form-group">
                              <label for="exampleFormControlSelect1">影廳類型:</label>
                              <select class="form-control" id="exampleFormControlSelect1" name="hlType" value="${param.hlType}">
                               <c:forEach var="item1" items="${typeArr}" varStatus="typeindex">
        						<option value="${typeindex.index}" ${typeindex.index == param.hlType ? 'selected="selected"' : ''}>${item1}</option>
    							</c:forEach>
                              </select>
                            </div>
                             <div class="form-group">
                              <label for="exampleFormControlSelect1">座位排數:</label>
                              <select class="form-control" id="inputRow" name="hlRow" value="${param.hlRow}">
                              	<c:forEach var="item3" items="${rowArr}" varStatus="typeindex">
        						<option value="${item3}" ${typeindex.index == param.hlRow ? 'selected="selected"' : ''}>${item3}</option>
    							</c:forEach>
                              </select>
                            </div>
                            <div class="form-group">
                              <label for="exampleFormControlSelect1">座位列數:</label>
                              <select class="form-control" id="inputCol" name="hlCol" value="${param.hlCol}">
                               <c:forEach var="item2" items="${colArr}" varStatus="typeindex">
        						<option value="${item2}" ${typeindex.index == param.hlCol ? 'selected="selected"' : ''}>${item2}</option>
    							</c:forEach>
                              </select>
                            </div>
                           
                            <input type="hidden" name="hlSeat" id="seatDBStr" value="${param.hlSeat}" >
                            <input type="hidden" name="hlSeatCount" id="seatDBCount" value="${param.hlSeatCount}" >
                            <input type="hidden" name="hlCol" id="hlCol" value="${param.hlCol}">
                            <input type="hidden" name="hlRow" id="hlRow" value="${param.hlRow}">
                              
                            <div class="btnbox">
                              <a onclick="history.back()" class="btn btn-danger">取消</a>
                              <input type="submit" value="確定新增" class="btn btn-primary" id="submitBtn" disabled>
                              <input type="hidden" name="action" value="insert">
                            </div>
                          </form>
                              <button  class="btn btn-success" id="previewBtn" style="margin-top: 10px">預覽座位</button>
              </div>
              <div class="rightbox">
                <span class="badge badge-primary" style="width:100%; display:none" id="screen">螢幕</span>
               
                <div class="showseat" id="prBox">
                </div>
              </div>
               <div class="statusbox">
                  <button type="button" class="btn btn-secondary" style="display:none" id="colorsample1">走道</button>
                  <button type="button" class="btn btn-success" style="display:none" id="colorsample2">座位</button>
                  <button type="button" class="btn btn-warning"style="display:none" id="colorsample3">保留</button>
                  <hr size="8px" align="center" width="100%">
                  <div class="statusinfo" id="rowStatus1" style="display:none">
                    <input type="text" id="setRow1"style="width:100px" placeholder="請輸入排號" >
                    <select name="" id="selectRow1">
                      <option value="0">設為走道</option>
                      <option value="1">設為座位</option>
                      <option value="3">設為保留</option>
                    </select>
                    <button type="button" id="changeRow1">更改整排</button>
                  </div>
                  <hr size="8px" align="center" width="100%">
                  <div class="statusinfo" id="colStatus1" style="display:none">
                    <input type="text" id="setCol1"style="width:100px" placeholder="請輸入列號" >
                    <select name="" id="selectCol1">
                      <option value="0">設為走道</option>
                      <option value="1">設為座位</option>
                      <option value="3">設為保留</option>
                    </select>
                    <button type="button" id="changeCol1">更改整列</button>
                  </div>
                </div> 
              </div>
            </div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/back_end/ManageHall/creatHall.js"></script>
</body>

</html>