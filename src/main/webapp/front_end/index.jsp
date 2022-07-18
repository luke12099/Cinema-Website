<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en" dir="ltr">
<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件

MovieService mvSvc = new MovieService();
List<MovieVO> showingList = mvSvc.getShowingMV();
List<MovieVO> comingList = mvSvc.getComingMV();
pageContext.setAttribute("showingList", showingList);
pageContext.setAttribute("comingList", comingList);
%>


<%
MovieService movieSvc = new MovieService();
List<MovieVO> SellTkMVlist = movieSvc.getAll();
pageContext.setAttribute("SellTkMVlist", SellTkMVlist);
%>

<head>
<title>HireMe</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/css/layout.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/css/allMovie.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<!-- DatePicker.css -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/showing/css/daterangepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/css/sellTK.css">
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<%@ include file="/front_end/header.jsp"%>
<!-- 輪播圖片 css -->
<style type="text/css">
div, ul, li, a, span, img {
	margin: 0;
	padding: 0;
}

#banner {
	overflow: hidden;
	width: 100%;
	height: 400px;
	position: relative;
	background: #000;
/* 	float: left; */
/* 	padding-bottom: 10px; */
}

#tab>img:not(:first-child) {
	display: none;
}

.lunbo_btn {
	height: 15px;
	width: 100%;
	margin: 0px auto;
	margin-top: -40px;
	position: absolute;
	z-index: 3;
	text-align: center;
}

.lunbo_btn span {
	width: 14px;
	height: 14px;
	display: inline-block;
	background-color: #b4b5b7;
	border-radius: 50%;
	margin: 0px 2px;
	cursor: pointer;
}

.lunbo_btn span.hover {
	background-color: #ffb23c;
}

.arrow {
	display: none;
	width: 30px;
	height: 60px;
	background-color: rgba(0, 0, 0, 0.4);
	position: absolute;
	top: 50%;
	margin-top: -30px;
	z-index: 999;
}

.arrow span {
	display: block;
	width: 10px; & cross;
	height: 10px;
	border-bottom: 2px solid #fff;
	border-left: 2px solid #fff;
}

.slider_left {
	margin: 25px 0 0 10px;
	transform: rotate(45deg);
}

.prve {
	left: 0;
}

.next {
	right: 0;
}

.slider_right {
	margin: 25px 0 0 5px;
	transform: rotate(-135deg);
}

.arrow:hover {
	background: #444;
}

#banner:hover .arrow {
	display: block;
}


</style>

</head>


<body>
	<!-- 置頂按鈕 -->
	<button type="button" id="BackTop" class="toTop-arrow"></button>
	<script>
    $(function () {
      $('#BackTop').click(function () {
        $('html,body').animate({ scrollTop: 0 }, 333);
      });
      $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
          $('#BackTop').fadeIn(222);
        } else {
          $('#BackTop').stop().fadeOut(222);
        }
      }).scroll();
    });
  </script>

	<div id="banner">
		<!-- 輪播圖片 -->
		<div id="tab">
			<img class="tabImg"
				src="<%=request.getContextPath()%>/front_end/index/images/movie3.jpg"
				height="400" width="100%" /> <img class="tabImg"
				src="<%=request.getContextPath()%>/front_end/index/images/movie4.jpg"
				height="400" width="100%" /> <img class="tabImg"
				src="<%=request.getContextPath()%>/front_end/index/images/movie5.jpg"
				height="400" width="100%" /> <img class="tabImg"
				src="<%=request.getContextPath()%>/front_end/index/images/movie6.jpg"
				height="400" width="100%" />
		</div>
		<!-- 指示符 -->
		<div class="lunbo_btn">
			<span num="0" class="tabBtn hover"></span> <span num="1"
				class="tabBtn"></span> <span num="2" class="tabBtn"></span> <span
				num="3" class="tabBtn"></span>
		</div>
		<!-- 左右切換按鈕 -->
		<div class="arrow prve">
			<span class="slider_left"></span>
		</div>
		<div class="arrow next">
			<span class="slider_right"></span>
		</div>
	</div>


	<script type="text/javascript">
    
        //輪播圖
        var curIndex=0;//初始化
        var img_number = document.getElementsByClassName('tabImg').length;
        var _timer = setInterval(runFn,5000);//5秒
        function runFn(){ //運行定時器         
            curIndex = ++curIndex == img_number ? 0 : curIndex;//演算法 4為banner圖片數量
            slideTo(curIndex);
         }
         
         //圓點點擊切換輪播圖
         window.onload = function  () {    //為按鈕初始化onclick事件
            var tbs = document.getElementsByClassName("tabBtn");
            for(var i=0;i<tbs.length;i++){
                tbs[i].onclick = function  () {
                    clearInterval(_timer);//細節處理，關閉定時，防止點切圖和定時器函數衝突
                    slideTo(this.attributes['num'].value);
                    curIndex = this.attributes['num'].value
                    _timer = setInterval(runFn,2000);//點擊事件處理完成，繼續開啟定時輪播
                }
            }
        }
    
        var prve = document.getElementsByClassName("prve");
        prve[0].onclick = function () {//上一張
            clearInterval(_timer);//細節處理，關閉定時，防止點切圖和定時器函數衝突
            curIndex--;
            if(curIndex == -1){
                curIndex = img_number-1;
            }
            slideTo(curIndex);
            _timer = setInterval(runFn,2000);//點擊事件處理完成，繼續開啟定時輪播
        }
        
        var next = document.getElementsByClassName("next");
        next[0].onclick = function () {//下一張
            clearInterval(_timer);//細節處理，關閉定時，防止點切圖和定時器函數衝突
            curIndex++;
            if(curIndex == img_number){
                curIndex =0;
            }
            slideTo(curIndex);
            _timer = setInterval(runFn,2000);//點擊事件處理完成，繼續開啟定時輪播
        }
        
        //切換banner圖片 和 按鈕樣式
        function slideTo(index){
            
            var index = parseInt(index);//轉int類型
            var images = document.getElementsByClassName('tabImg');
            for(var i=0;i<images.length;i++){//遍歷每個圖片
                if( i == index ){
                    images[i].style.display = 'inline';//顯示            
                }else{
                    images[i].style.display = 'none';//隱藏
                }
            }
            var tabBtn = document.getElementsByClassName('tabBtn');
            for(var j=0;j<tabBtn.length;j++){//遍歷每個按鈕
                if( j == index ){
                    tabBtn[j].classList.add("hover");    //添加輪播按鈕hover樣式
                    curIndex=j;
                }else{
                    tabBtn[j].classList.remove("hover");//去除輪播按鈕hover樣式
                }
            }
            
        }
        
    </script>

	<div id="mainDiv">

<!-- 		<hr style="border-color: black"> -->

		<div id="testDiv">


			<div class="guide2outer">
				<div class="TKouter">
					<table class="TKinner">
						<tr>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/MovieServlet.do">
									<select size="1" name="MV_ID" class="MV_ID" style="width: 300px;">
										<option value=0>請選擇電影
											<c:forEach var="SellMovieVO" items="${SellTkMVlist}">
												<option value="${SellMovieVO.mvId}">${SellMovieVO.mvName}
											</c:forEach>
									</select>
								</FORM>
							</td>

							<td >
								<div id="date">
									<select name="" id="dateSelector" class="picker toRed" disabled="disabled" style="width: 300px;">
										<option>選擇日期</option>
									</select>
								</div>
							</td>

							<td>
								<div id="time">
									<select class="showTimeSelect" disabled="disabled" style="width: 300px;">
										<option value=0>請選擇場次</option>
									</select>
								</div>
							</td>

							<td>
								<FORM METHOD="post" class="checkInForm"	ACTION="<%=request.getContextPath()%>/front/tkOrd.do"
									style="margin-bottom: 0px;">
								<div class="tablebtBlock">
									<a class="tablebt checkIn"style="font-size: 18;">BOOKING!</a>
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




		</div>
		
<!-- 		<hr class="hr1"> -->
		
		<div class="fm1" style='padding: 10px 20px;'>
			<!--將內容存-->
			<div class="mytabs">
				<input type="radio" id="show" name="mytabs" checked="checked">
				<label for="show">上映中</label>
				<div class="container">
					<c:forEach var="showingVO" items="${showingList}">
						<div class="content">
							<div class="cover">
								<img
									src="${pageContext.request.contextPath}${showingVO.mvPicture}"
									alt="">
							</div>
							<div class="info_container">
								<div class="info">
									<div class="name">${showingVO.mvName}</div>
									<div class="ename">${showingVO.mvEName}</div>
									<div class="stDate">上映日期:${showingVO.mvStDate}</div>
									<div class="star" style="font-weight: bold;">
										<c:if test="${(showingVO.mvTtStar/showingVO.mvTtCm).isNaN()}">
                        	這部電影尚未有人評分
                            </c:if>
										<c:if test="${!(showingVO.mvTtStar/showingVO.mvTtCm).isNaN()}">
                        	<fmt:formatNumber value="${showingVO.mvTtStar/showingVO.mvTtCm}"
                        	minFractionDigits="1" maxFractionDigits="1"/>
                            <img
												src="/CGA102G1/front_end/showAllMovie/MV_ICON/star.png"
												alt="">
										</c:if>
									</div>
								</div>
								<div class="icon">
									<img
										src="/CGA102G1/front_end/showAllMovie/MV_ICON/level${showingVO.mvLevel}.jpg"
										alt="">
								</div>
							</div>
							<div class="bt">
								<form
									action="${pageContext.request.contextPath}/MovieServlet.do"
									method="post">
									<input type="hidden" name="mvId" value="${showingVO.mvId}">
									<input type="hidden" name="action" value="getOneForDisplay">
									<button type="submit">查看電影詳情</button>
								</form>
							</div>
						</div>
					</c:forEach>
				</div>

				<input type="radio" id="soon" name="mytabs"> <label
					for="soon">即將上映</label>
				<div class="container">
					<c:forEach var="comingVO" items="${comingList}">
						<div class="content">
							<div class="cover">
								<img
									src="${pageContext.request.contextPath}${comingVO.mvPicture}"
									alt="">
							</div>
							<div class="info_container">
								<div class="info">
									<div class="name">${comingVO.mvName}</div>
									<div class="ename">${comingVO.mvEName}</div>
									<div class="stDate">預計上映:${comingVO.mvStDate}</div>
								</div>
								<div class="icon">
									<img
										src="/CGA102G1/front_end/showAllMovie/MV_ICON/level${comingVO.mvLevel}.jpg"
										alt="">
								</div>
							</div>
							<div class="bt">
								<form
									action="${pageContext.request.contextPath}/MovieServlet.do"
									method="post">
									<input type="hidden" name="mvId" value="${comingVO.mvId}">
									<input type="hidden" name="action" value="getOneForDisplay">
									<button type="submit">查看電影詳情</button>
								</form>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>


		</div>





	</div>
	<!--客服圖 請自行加連結-->
<!-- 	<img class="cs" -->
<%-- 		src="<%=request.getContextPath()%>/front_end/index/images/demo/cs.png" --%>
<!-- 		height="50px;" width="60px;" href="#"></img> -->

	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>
	</div>





	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script>
	
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
	let SH_ID = '';

	$('.MV_ID').change((e) => {
		MV_ID = e.target.value;
		$('.inputMV_ID').val(MV_ID);
		$('#dateSelector').removeAttr("disabled");
		
	})
	
	
	$('#dateSelector').change((e) => {
		SH_TIME = e.target.value + " 09:00:00"; ;
		let url = "${pageContext.request.contextPath}/tkOrd/tkOrd.do?action=listShowings_ByCompositeQuery&MV_ID=" + MV_ID +"&SH_TIME=" + SH_TIME;
		$('.showTimeSelect').removeAttr("disabled");
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
	            	}
	        	
	        }
		
		})
				
		$('.inputHL_ID').val(HL_ID);

	})	
	
	
	$('.checkIn').click(function() {

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