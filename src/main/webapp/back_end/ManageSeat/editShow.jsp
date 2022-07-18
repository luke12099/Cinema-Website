<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.hall.model.*" %>
<%@ page import="com.changeSeat.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List dateList = (List)request.getAttribute("dateList");
	HallVO hallVO = (HallVO)request.getAttribute("hallVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>依場次管理座位</title>
   	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <!-- ************************************************************** -->
    <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/ManageSeat/createHall.css">
    
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
				<div class="createform">
					<div class="selectShow">
						<select class="form-control" id="datePick">
							<option value="" >請先選擇場次日期</option>
							<c:forEach var="date" items="${dateList}" varStatus="dateIndex">
								<option value="${date}" >${date}</option>
							</c:forEach>
						</select> 
						<select class="form-control" id="showPick">
							<option value="" >請先選擇場次日期</option>
						</select>
					</div>
					<form class="form1" id="formId">
						<div class="form-group" style="margin-top: 5px">
							<div class="alert alert-warning" style="margin-bottom: 0px">影廳名稱:${hallVO.hlName}</div>
							<c:if test="${hallVO.hlType==0}">
							<div class="alert alert-warning" style="margin-bottom: 5px">影廳類型:數位</div>
							</c:if>
							<c:if test="${hallVO.hlType==1}">
							<div class="alert alert-warning" style="margin-bottom: 5px">影廳類型:IMAX</div>
							</c:if>
							<input type="hidden" id="showSeatSource" value="">
							<input type="hidden" id="hlRow" value="${hallVO.hlRow}">
							<input type="hidden" id="hlCol" value="${hallVO.hlCol}">
							<input type="hidden" id="hlId" value="${hallVO.hlId}">
						</div>
						<div class="btnbox">
							<button type="button" class="btn btn-success"
								style="margin-bottom: 5px; display: block;" id="countBtn">計算所有可賣座位</button>
							<a onclick="history.back()" class="btn btn-danger" id="submitBtn" >退出</a>

						</div>
					</form>
				</div>
				<div class="rightbox">
					<span class="badge badge-primary" style="width: 100%; display: none" id="screen">螢幕</span>

					<div class="showseat" id="prBox"></div>
				</div>
				<div class="statusbox">
					<button type="button" value="" class="btn btn-outline-danger"
						style="display: none" id="nowYouchoose" disabled>目前選中</button>
					<br>
					<button type="button" value="0" class="btn btn-secondary"
						style="display: none" id="colorsample1" disabled>走道</button>
					<br>
					<button type="button" value="2" class="btn btn-primary"
						style="display: none" id="colorsample5" disabled>已售出</button>
					<br>
					<button type="button" value="1" class="btn btn-success"
						style="display: none" id="colorsample2">設為座位</button>
					<br>
					<button type="button" value="3" class="btn btn-warning"
						style="display: none" id="colorsample3">設為保留</button>
					<br>
					<button type="button" value="4" class="btn btn-danger"
						style="display: none" id="colorsample4">設為維修</button>
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
    <script src="${pageContext.request.contextPath}/back_end/ManageSeat/editShow.js"></script>
    <script src="${pageContext.request.contextPath}/back_end/ManageSeat/socketScript.js"></script>
</body>

</html>