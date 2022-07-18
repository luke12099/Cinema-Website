<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.tk_inf.model.*"%>


<%@ page import="com.movie.model.*"%>

<%
MovieService movieSvc = new MovieService();
List<MovieVO> list = movieSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>現場販售</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/tk_ord/styles/sellTKback.css">


<!-- DatePicker.css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/showing/css/daterangepicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</head>


<body>

	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>

	<aside id="aside"></aside>
	<!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
	<main>

		<div class="all">
			<div class="main">

				<div class="guide1outer">
					<div class="guide1">
						<div class="guide1inner">現場販售</div>
					</div>
				</div>

				<div class="guide2outer">
					<div class="TKouter">
						<table class="TKinner">
							<tr>

								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/MovieServlet.do">
										<select size="1" name="MV_ID" class="MV_ID"
											style="width: 250px;">
											<option value=0>請選擇電影</option>
											<c:forEach var="MovieVO" items="${list}">
												<option value="${MovieVO.mvId}">${MovieVO.mvName}
											</c:forEach>
										</select>
									</FORM>
								</td>

								<td>
									<div id="date">
										<select name="" id="dateSelector" class="picker toRed"
											disabled="disabled" style="width: 250px;">
											<option>選擇日期</option>
										</select>
									</div>
								</td>

								<td><select class="showTimeSelect" disabled="disabled"
									style="width: 250px;">
										<option value=0>請選擇場次</option>
								</select></td>

								<td>
									<div class="tablebtBlock">
										<a class="tablebt checkout">查看</a>
									</div>
								</td>
								<td>

									<FORM METHOD="post" class="checkInForm"
										ACTION="<%=request.getContextPath()%>/tkOrd/tkOrd.do"
										style="margin-bottom: 0px;">
										<div class="tablebtBlock">
											<a class="tablebt checkIn" style="font-size: 18;">購票</a>
										</div>
										<input type="hidden" name="MV_ID" class="inputMV_ID">
										<input type="hidden" name="SH_ID" class="inputSH_ID">
										<input type="hidden" name="HL_ID" class="inputHL_ID">
										<input type="hidden" name="action" value="go_To_TicketSelect">
									</FORM>

								</td>
							</tr>
						</table>
					</div>
				</div>

				<div class="SeatOuter" style="opacity: 0;">
					<div class="front">
						<div class="screen">
							<div class="screeninner">螢幕</div>
						</div>
					</div>
					<div>
						<!-- 座位start -->

						<div class="seatsChart"></div>

					</div>

				</div>
			</div>

			<!-- 座位end -->


		</div>


	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>


	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<script>	
	// 	Add Days
	Date.prototype.addDays = function (days) {
    const date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
	};
	
	// ingect date
	let today = new Date();
	const formatDate = (current_datetime)=>{
    	let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
    	return formatted_date;
	}

	for(let i = 0; i < 7; i++){
    	let date = today.addDays(i).toLocaleDateString('sv');
    	let day = today.addDays(i).getDay();
    	let week;
    	switch (day){
        	case 1:
            	week = "星期一";
            	break;
        	case 2:
            	week = "星期二";
            	break;
        	case 3:
            	week = "星期三";
            	break;
        	case 4:
            	week = "星期四";
            	break;
        	case 5:
            	week = "星期五";
            	break;
        	case 6:
            	week = "星期六";
            	break;
        	case 0:
            	week = "星期日";
            	break;
    	}
    	$("#dateSelector").append('<option value='+date+'>'+date + ' '+ week +'</option>');
	}
	
	$('.checkout').click(function() {
		$('.SeatOuter').fadeTo("fast",1);
	});
		
	let col = '';
	let row = '';
	let seat = '';
	let MV_ID = '';
	let SH_SEAT_STATE = '';
	let HL_ID = 0;
	let hallName = '';
	let SH_ID= '';

	$('.MV_ID').change((e) => {
		MV_ID = e.target.value;
		$('.inputMV_ID').val(MV_ID);
		$("#dateSelector").removeAttr("disabled");
		
	})
	
	
	$('#dateSelector').change((e) => {
		SH_TIME = e.target.value + " 09:00:00"; ;
		let url = "${pageContext.request.contextPath}/tkOrd/tkOrd.do?action=getShowingByDate&SH_TIME=" + SH_TIME;
		$(".showTimeSelect").removeAttr("disabled");
		$('.showTimeSelect').empty();
		$('.showTimeSelect').append('<option value=0>請選擇場次' );
		$.ajax({
	            url: url,
	            type: 'post',
	            dataType: 'json',
	            async: false,
	            timeout: 15000,
	            success: function (data) {

	            	for(let show of data){
	            		let showTimeStr = show.SH_TIME + " "; 

	            		let showTime = showTimeStr.slice(-12, -7) + showTimeStr.slice(-3, -1);	
						
	            		
	            		if((show.SH_TYPE === 0) && (show.mvId === parseInt(MV_ID))){
	            			if((parseInt(showTimeStr.slice(-12, -10))< 6 || (parseInt(showTimeStr.slice(-12, -10)) === 12)) && (showTimeStr.slice(-3, -1) === 'AM')){
	            				$('.showTimeSelect').append('<option value='+ show.SH_ID + '>跨夜'+showTime+'  (數位)');
	            			}else{
	            				$('.showTimeSelect').append('<option value='+ show.SH_ID + '>'+showTime+'  (數位)');          				
	            			} 		
	            		}else if ((show.SH_TYPE === 1) && (show.mvId === parseInt(MV_ID))){
	            			if((parseInt(showTimeStr.slice(-12, -10))< 6 || (parseInt(showTimeStr.slice(-12, -10)) === 12)) && (showTimeStr.slice(-3, -1) === 'AM')){
	            				$('.showTimeSelect').append('<option value='+ show.SH_ID + '>跨夜'+showTime+'  (IMAX)');
	            			}else{
		            			$('.showTimeSelect').append('<option value='+ show.SH_ID + '>'+showTime+'  (IMAX)');
	            			}		
	            		}
	            	}
	            	
	            }	
		
		})
		
	})

	$('.showTimeSelect').blur((e) => {
		SH_ID = e.target.value;
		$('.inputSH_ID').val(SH_ID);
		
		let url = "${pageContext.request.contextPath}/tkOrd/tkOrd.do?action=listShowings_ByCompositeQuery&MV_ID=" + MV_ID +"&SH_ID=" + SH_ID;
		
		$.ajax({
	    	url: url,
	        type: 'post',
	        dataType: 'json',
	    	async: false,
	        timeout: 15000,
	        success: function (data) {
	        	for(let show of data){
	            	HL_ID = show.HL_ID;
	            	seat = show.SH_SEAT_STATE;
	            	let showTimeStr = show.SH_TIME + " ";         		
	            	}
	        	
	        }
		
		})
		
		let url2 = "${pageContext.request.contextPath}/tkOrd/tkOrd.do?action=findHallByhlId&hlId=" + HL_ID;
		$('.inputHL_ID').val(HL_ID);
		$.ajax({
            url: url2,
            type: 'post',
            dataType: 'json',
            async: false,
            timeout: 15000,
            success: function (data2) {
            	hallName = data2.hlName;
            	row = data2.hlRow;
            	col = data2.hlCol;

            } 
	 
		})
		
		
		// 長出位置資訊
		let rowname = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
			const body = document.getElementsByClassName('seatsChart')[0];
			$('.seatsChart').empty();
			for (let j = 0; j < row; j++) {
				const singeRow = document.createElement('div');
				singeRow.className = 'seatRow';
				body.append(singeRow);

				const rowtitle = document.createElement('div');
				rowtitle.className = 'seatRowNumber';
				singeRow.append(rowtitle)
				

				for (let i = 4 + (j * 5 * col); i < seat.length; i += 5) {
					const input = document.createElement("div");
					input.setAttribute("role", "checkbox");
					input.setAttribute("aria-checked", "false");
					input.setAttribute("focusable", "true");

					// 用from
					input.innerHTML = (seat[i - 2] + seat[i - 1]);
					let state = seat[i];
					input.id = rowname[j] + "_" + (seat[i - 2] + seat[i - 1]);

					if (state === '1') {
						input.className = 'seatNumber';
					} else if (state === '0') {
						input.className = 'seatNumber aisle';
					} else if (state === '3') {
						input.className = 'seatNumber seatReserved';
					} else if (state === '4') {
						input.className = 'seatNumber seatConstruction';
					} else {
						input.className = 'seatNumber seatUnavailable';
					}

					singeRow.append(input);

					// 換排
					if (((i + 1) / 5) % col === 0) {

						break;
					}

				}
				
			};	
			
	})
	
	$('.checkIn').click(function() {
		movieName = $('.MV_ID option:selected').text();
		if('' === MV_ID){
			Swal.fire({
                icon: 'error',
                title: '很抱歉',
                text: '請確認您欲觀看的電影',
                footer: ''
            })
		}else if ('' === SH_ID || '0' === SH_ID ){
			Swal.fire({
                icon: 'error',
                title: '很抱歉',
                text: '請確認您欲觀看的場次',
                footer: ''
            })
		}else{
        
        $(".checkInForm").submit();
		}
		
	})
	
	
	</script>

</body>
</html>