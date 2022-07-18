<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.showing.model.*"%>

<%
ShowingVO showingVO = (ShowingVO) request.getAttribute("showingVO");
%>
<%=showingVO == null%>--${showingVO.getmvId()}--${showingVO.mvId}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>場次資料新增</title>

<style>
table#table-1 {
	background-color: antiquewhite;
	/* 	border: 2px solid black; */
	text-align: center;
	margin-bottom: 10px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

#datePicker {
	text-align: center;
}
</style>

<style>
table {
	width: 450px;
	background-color: antiquewhite;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	/* 	border: 1px solid black; */
	white-space: nowrap;
}

th, td {
	padding-top: 5px;
	padding-bottom: 5px;
	padding-left: 5px;
	padding-right: 5px;
	border: 1px solid rgb(198, 155, 123);
}

.timePicker {
	width: 60px;
	text-align: center;
}

#datePicker {
	width: 180px;
}

input, select {
	border: 1px solid black;
	background-color: white;
	border-radius: 10px;
	outline-style: none;
	text-align: justify;
	padding: 5px 10px;
}

input[name="HL_ID"] {
	width: 50px;
	text-align: center;
}

input[name="SH_STATE"] {
	width: 50px;
	text-align: center;
}

input[name="SH_SEAT_STATE"] {
	/* 	text-align: center; */
	color: gray;
}

input[name="SH_TYPE"] {
	width: 50px;
	text-align: center;
}
</style>

<!-- TimePicker.css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/timepicker@1.13.18/jquery.timepicker.css">
<!-- DatePicker.css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/emp_all.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/emp_main.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/emp_footer.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/css/daterangepicker.css" />
</head>
<body bgcolor='white'>
	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>
	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<main id="main">

		<table id="table-1">
			<tr>
				<td>
					<h3>場次資料新增</h3> <a
					href="<%=request.getContextPath()%>/back_end/showing/showing_select_page.jsp">回首頁</a>
				</td>
			</tr>
		</table>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/showing/showing.do"
			name="form1" id="1234">
			<table>
				<jsp:useBean id="movieSvc" scope="page"
					class="com.movie.model.MovieService" />
				<tr>
					<td>電影編號:</td>
					<td><select size="1" name="mvId">
							<c:forEach var="movieVO" items="${movieSvc.all}">
								<option value="${movieVO.mvId}"
									${(showingVO.mvId==movieVO.mvId)? 'selected':'' }>${movieVO.mvId}
									- 【${movieVO.mvName}】
							</c:forEach>
					</select></td>
				</tr>

				<jsp:useBean id="hallSvc" scope="page"
					class="com.hall.model.HallService" />
				<tr>
					<td>影廳編號:</td>
					<td><select size="1" name="HL_ID">
							<c:forEach var="hallVO" items="${hallSvc.all}">
								<option value="${hallVO.hlId}">${hallVO.hlId}-
									【${hallVO.hlName}】
							</c:forEach>
					</select></td>
				</tr>

				<tr>
					<td>場次狀態:</td>
					<td><select size="1" name="SH_STATE">
							<option value="0">0
							<option value="1">1
					</select></td>
					<td>(未滿位0(預設)/已滿位1)</td>
				</tr>

				<tr>
					<td>日期:</td>
					<td><input name="SH_TIME" id="datePicker" type="text"
						class="refresh"
						value="<%=(showingVO == null) ? "" : showingVO.getSH_TIME()%>" />
					</td>
				</tr>

				<tr>
					<td>時段:</td>
					<td><input id="time0" type="text" class="timePicker refresh">
						<input id="time1" type="text" class="timePicker refresh">
						<input id="time2" type="text" class="timePicker refresh">
						<input id="time3" type="text" class="timePicker refresh">
						<input id="time4" type="text" class="timePicker refresh">
						<input id="time5" type="text" class="timePicker refresh">
						<input id="time6" type="text" class="timePicker refresh">
						<input id="time7" type="text" class="timePicker refresh">
						<input id="time8" type="text" class="timePicker refresh">
						<input id="time9" type="text" class="timePicker refresh">
					</td>
				</tr>

				<tr>
					<td>電影播放類型:</td>
					<td><select size="1" name="SH_TYPE">
							<option value="0">0
							<option value="1">1
					</select></td>
					<td>(0-數位/1-IMAX)</td>
				</tr>



			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</FORM>
	</main>
	<!-- <div id="tree"></div> -->
	<footer>嗨邇覓影城 &copy; HIREME CINEMA 2022</footer>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<%-- 	<script	src="<%=request.getContextPath()%>/back_end/showing/emp_aside.js"></script> --%>
</body>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- TimePicker.js -->
<script
	src="https://cdn.jsdelivr.net/npm/timepicker@1.13.18/jquery.timepicker.js"></script>
<script>

    $('.timePicker').timepicker({
    	'timeFormat': 'H:i',
    	'step': 30,
    	'disableTimeRanges': [
    		['2am', '9am']
    		]
    	
    });
    </script>


<!-- DatePicker.js -->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/back_end/showing/js/moment.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/back_end/showing/js/daterangepicker.js"></script>
<script>
        $('#datePicker').daterangepicker({
            locale: {
                format: 'YYYY-MM-DD',
            }
        });

        var DateDiff = function (sDate1, sDate2) { // sDate1 和 sDate2 是 2016-06-18 格式
            var oDate1 = new Date(sDate1);
            var oDate2 = new Date(sDate2);
            var iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); // 把相差的毫秒數轉換為天數
            return iDays;
        };
    </script>

<script>
        // 取得日期相差天數
        let startDate, endDate, GetDateDiff = "";
        
        function dateDiff(){
        	startDate = $("#datePicker").val().substr(0,10);
            endDate = $("#datePicker").val().substr(-10,10); 
            GetDateDiff = DateDiff(startDate, endDate); // 轉換為天數 : 1
        }
        dateDiff();
        
        // Add Days
		Date.prototype.addDays = function (days) {
		  const date = new Date(this.valueOf());
		  date.setDate(date.getDate() + days);
		  return date;
		};
		
		//const date = new Date('2020-12-02');
		//let newDay = date.addDays(1).toLocaleDateString('sv');
		//console.log(newDay);
		// 取得所有場次時間 "yyyy-mm-dd hh:mm:ss"
			let temp = [];
			let timeArr = [];
			let dateTimeArr = [];
			function GetAllSH_TIME(){
				let inputDiv = document.createElement('div');
			    let newDiv = document.getElementById('newDiv');
			    if(newDiv){
			        newDiv.remove();
			    }
			    inputDiv.id = "newDiv";
			    //取得時段的陣列
			    for(let i = 0; i <= 9; i++){
			    temp.push($("#time" + i).val())
			    };
			    let timeArr = temp.filter(function(e){
			        return e && e.trim()
			    })
			    //取得日期加時段的陣列
			    for(let j = 0; j <= GetDateDiff; j++){
			        for(let k = 0; k < timeArr.length; k++){
			            let time = new Date(startDate);
			            let showing = time.addDays(j).toLocaleDateString('sv') + " " + timeArr[k] + ":00" 
			            dateTimeArr.push(showing)
			        }
			    }
// 			    $("#test").html(dateTimeArr);
			    
			    console.log(dateTimeArr);
			    for(let i = 0; i < dateTimeArr.length; i++){
			    let newInput = document.createElement('input');
			    newInput.name = "SH_TIME1";
			    newInput.value = dateTimeArr[i];
			    newInput.type = "hidden";
			    newInput.setAttribute("form","1234");
			    inputDiv.append(newInput);
			    }
			    let main = document.getElementById('main');
			    main.append(inputDiv);
			}
		
		
		$(document).ready(function(){
			$(".refresh").on("change",function (){
				temp = [];
				timeArr = [];
				dateTimeArr = [];
				dateDiff()
				GetAllSH_TIME();
			})
		})
		
    </script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
Timestamp SH_TIME = null;
try {
	SH_TIME = showingVO.getSH_TIME();
} catch (Exception e) {
	SH_TIME = new Timestamp(System.currentTimeMillis());
}
%>

</html>