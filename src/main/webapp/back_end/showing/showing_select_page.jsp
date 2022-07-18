<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>場次查詢</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/css/showing_select_page.css" />

<!-- TimePicker.css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/md-date-time-picker@2.3.0/dist/css/mdDateTimePicker.min.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/showing/css/jquery.timepicker.css" />
<!-- DatePicker.css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/css/daterangepicker.css" />
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

			<jsp:useBean id="showingSvc" scope="page" class="com.showing.model.ShowingService" />
			<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
			<jsp:useBean id="hallSvc" scope="page" class="com.hall.model.HallService" />

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font color='red'>請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/showing/showing.do"
				name="form1">
				<div id="title">場次查詢</div>
				<a href='<%=request.getContextPath()%>/back_end/showing/listAllShowing.jsp'>List</a>
				all Showings. <br>
				<div id="selectById" class="selectBy">
					<div id="selectById_title">
						<span>場次編號: </span>
					</div>
					<div id="selectByName_input">
						<input type="text" name="SH_ID" value=""><br>
					</div>
				</div>
				<div id="selectByName" class="selectBy">
					<div id="selectByName_title">
						<span>電影名稱: </span>
					</div>
					<div id="selectByName_input">
						<select size="1" name="MV_ID">
							<option value="">
								<c:forEach var="movieVO" items="${movieSvc.all}">
									<option value="${movieVO.mvId}">${movieVO.mvId} 【${movieVO.mvName}】
								</c:forEach>
						</select><br>
					</div>
				</div>
				<div id="selectByHall" class="selectBy">
					<div id="selectByHall_title">
						<span>影廳編號: </span>
					</div>
					<div id="selectByHall_input">
<!-- 						<input type="text" name="HL_ID" value=""><br> -->
							<select size="1" name="HL_ID">
							<option value="">
								<c:forEach var="hallVO" items="${hallSvc.all}">
									<option value="${hallVO.hlId}">${hallVO.hlId} 【${hallVO.hlName}】
								</c:forEach>
						</select><br>
					</div>
				</div>
				<div id="selectByDate" class="selectBy">
					<div id="selectByDate_title">
						<span>日期: </span>
					</div>
					<div id="selectByDate_input">
						<input name="SH_TIME" id="f_date1" type="text"> <input
							type="hidden" name="action" value="listShowings_ByCompositeQuery">
					</div>
				</div>
				<div id="search" class="">
					<input type="submit" value="送出" id="searchBtn">
				</div>
			</FORM>
		</div>


		<h3>場次管理</h3>

		<ul>
			<li><a
				href='<%=request.getContextPath()%>/back_end/showing/addShowing.jsp'>Add</a>
				a new Showing.</li>
		</ul>

<!-- 		<h3> -->
<!-- 			<font color=orange>電影管理</font> -->
<!-- 		</h3> -->

<!-- 		<ul> -->
<!-- 			<li><a -->
<%-- 				href='<%=request.getContextPath()%>/back_end/movie/listAllMovie.jsp'>List</a> --%>
<!-- 				all Movies.</li> -->
<!-- 		</ul> -->

	</main>
	<!-- <div id="tree"></div> -->
	<footer>嗨邇覓影城 &copy; HIREME CINEMA 2022</footer>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<%-- 	<script src="<%=request.getContextPath()%>/back_end/showing/emp_aside.js"></script> --%>

	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

	<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d 09:00:00',         //format:'Y-m-d H:i:s',
		   value: '',              // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>

	<!-- TimePicker.js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-clock-timepicker@2.5.0/jquery-clock-timepicker.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.timepicker.js"></script>
	<script>
      $(document).ready(function () {
        $(".timepicker").timepicker({
          timeFormat: "HH:mm",
          interval: 30,
        });
      });

      $(".timePicker").clockTimePicker({
        duration: true,
        durationNegative: true,
        precision: 15,
        i18n: {
          cancelButton: "Abbrechen",
        },
        onAdjust: function (newVal, oldVal) {
          //...
        },
      });
    </script>

	<!-- DatePicker.js -->
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/showing/js/moment.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/showing/js/daterangepicker.js"></script>
	<script>
      $('input[name="dates"]').daterangepicker({
        locale: {
          format: "YYYY-MM-DD",
        },
      });

      var DateDiff = function (sDate1, sDate2) {
        // sDate1 和 sDate2 是 2016-06-18 格式
        var oDate1 = new Date(sDate1);
        var oDate2 = new Date(sDate2);
        var iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); // 把相差的毫秒數轉換為天數
        return iDays;
      };

      // $("div").click(function(){
      //     console.log("aaaa")
      //     let tttt = document.getElementById('datePicker').value;
      //     let startDate = $("#datePicker").val().substr(0,10);
      //     let endDate = $("#datePicker").val().substr(-10,10);
      //     console.log(tttt);
      //     console.log(startDate);
      //     console.log(endDate);
      //     let GetDateDiff = DateDiff(startDate, endDate); // 轉換為天數 : 1
      //     console.log(GetDateDiff);
      // })
    </script>

	<script>
      // 取得日期相差天數
      $("div").click(function () {
        let startDate = $("#datePicker").val().substr(0, 10);
        let endDate = $("#datePicker").val().substr(-10, 10);
        console.log(startDate);
        console.log(endDate);
        let GetDateDiff = DateDiff(startDate, endDate); // 轉換為天數 : 1
        console.log(GetDateDiff);
      });
    </script>
</body>
</html>
