<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.showing.model.*"%>
<%@ page import="com.movie.model.*"%>
<%@ page import="com.cmt.model.*"%>
<%@ page import="com.showing.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="redis.clients.jedis.Jedis"%>
<%@ page import="redis.clients.jedis.JedisPool"%>

<%
//MovieServlet.java(Controller), 存入req的MovieVO物件
MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
//取得評論的VO
CmtService cmtSvc = new CmtService();
List<CmtVO> list = cmtSvc.getCmtsByMV_ID(movieVO.getMvId());
pageContext.setAttribute("list", list);
//該電影的總星數
int ttstar = 0;
for (CmtVO lis : list) {
	ttstar += lis.getCM_STAR();
	System.out.println(lis.getCM_ID() + " , " + lis.getCM_STAR());
}
	System.out.println(ttstar);
pageContext.setAttribute("ttstar", ttstar);
//該電影的評論數
int ttcmt = 0;
if(list.size() == 0){
	ttcmt = 0;
}else{
    ttcmt = list.size();
	
}
pageContext.setAttribute("ttcmt", ttcmt);
//該電影的平均星數
int avgstar = 0;
if(ttstar == 0 || ttcmt == 0){
	avgstar = 0;
}else{
avgstar = ttstar / ttcmt;	
}
pageContext.setAttribute("avgstar", avgstar);
//點讚的部分


%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<title>${movieVO.mvName}</title>
<meta charset="UTF-8">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/movieDetail/styles/layout.css" type="text/css"> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="<%=request.getContextPath()%>/front_end/movieDetail/css/bootstrap.css"
	rel='stylesheet' type='text/css' />
<script
	src="<%=request.getContextPath()%>/front_end/movieDetail/js/bootstrap.js"></script>
<script
	src="<%=request.getContextPath()%>/front_end/movieDetail/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/movieDetail/css/movie_detail.css">

<!-- icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
	 <div class="wrapper row1" style="height: 60px;">
	  <jsp:include page="/front_end/header.jsp" />
	  <jsp:include page="/front_end/header_css.jsp" />
	 </div>



	<!-- 內容 -->
	<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
<div class="main">
	<div id="movie_detail_main" style='padding: 50px 100px; color: #979797; background-color: black;'>
		<div id="movie">
			<div id="movie_img">
				<div id="pic">
					<img src="<%=request.getContextPath()%>${movieVO.mvPicture}" alt="">
				</div>
			</div>
			<div id="movie_detail_1">
				<div id="movie_title">
					<span>${movieVO.mvName}</span>
				</div>
				<br> <span class="whiteBg" id="movie_title_eng">${movieVO.mvEName}</span><br>
				<span class="whiteBg" id="movie_time">${movieVO.mvLong} 分鐘</span><br>
				<span class="whiteBg" id="movie_level">${movieVO.mvLevel}</span><br>
				<span class="whiteBg" id="movie_type">${movieVO.mvType}</span><br>
				<span class="whiteBg" id="movie_cast">${movieVO.mvCast}</span><br>
				<div id="movie_detail_2" class="whiteBg">
					<p id="movie_dt">${movieVO.mvDt}</p>
				</div>
			</div>
		</div>
		
		<hr class="hr-shadow">
		
		<div id="movie_tler">
			${movieVO.mvTler}
		</div>
		
		<hr class="hr-shadow">
		
		<div id="booking">
			<div id="date">
				<select name="" id="dateSelector" class="picker toRed">
				<option>選擇日期</option>
					<!-- inject date -->
				</select>
			</div>
			<div id="time">
				<select name="" id="showingTime" class="picker toRed">
				<option value=0>請選擇場次</option>
					<!-- inject showing time -->
				</select>
			</div>
			<div id="book">
				<button id="bookBtn" class="checkIn" type="button">BOOKING!</button>
				<FORM METHOD="post" class="checkInForm"
					ACTION="<%=request.getContextPath()%>/front/tkOrd.do"
					style="margin-bottom: 0px;">
					<input type="hidden" name="MV_ID" class="inputMV_ID"> 
					<input type="hidden" name="SH_ID" class="inputSH_ID"> 
					<input type="hidden" name="HL_ID" class="inputHL_ID"> 
					<input type="hidden" name="action" value="go_To_TicketSelect">
				</FORM>
			</div>
		</div>

		<div id="comment">
			<div id="cmt_info" value="0">
				<div id="cmt_title">
					<span>COMMENTS</span>
				</div>
				<div id="cmt_avg" value="${avgstar}" class="cmt_star">
					
				</div>
				<div id="cmt_ttcmt">
					<span>【${ttcmt}則評論】</span>
				</div>
				<div id="cmt_open">
					<span class="fa fa-plus"></span>
				</div>
			</div>


			<!--    載入評論   -------- -->
			<c:forEach var="cmtVO" items="${list}">
				<div class="cmt">
					<div class="member">
						<div class="member_pic">
							<img src="<%=request.getContextPath()%>${cmtVO.memberVO.member_Pic}" alt="">
						</div>
						<div class="member_name">
							<div class="member_nickname">
								<span>${cmtVO.memberVO.member_Name}</span>
							</div>
							<div class="member_id">
								<span>${cmtVO.MEMBER_ID}</span>
							</div>
						</div>
					</div>
					<div class="cmt_detail">
						<div class="cmt_text whiteBg" value="${cmtVO.CM_STATE}">
							${cmtVO.CM_TEXT}</div>
						<hr class="hr_cmt">
						<div class="cmt_like">
							<span class="fa fa-heart like">${cmtVO.CM_LIKE}</span>
							<input type="hidden" name="CM_ID" value="${cmtVO.CM_ID}">
						</div>
						<div class="cmt_date">
							<span><fmt:formatDate value="${cmtVO.CM_DATE}"
									pattern="yyyy-MM-dd HH:mm:ss" /></span>
						</div>
					</div>
					<div class="cmt_right">
						<div class="cmt_star" value="${cmtVO.CM_STAR}"></div>
						<div class="cmt_report">
							<button class="reportBtn">檢舉</button>
							<input type="hidden" name="CM_ID" value="${cmtVO.CM_ID}">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>


		<!--     新評論 -->
<hr class="hr-shadow">


		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cmt/cmt.do" name="form1">
		<div id="newCmt">
			<div class="member">
				<div class="member_pic">
					<img id="thisMemPic" src="<%=request.getContextPath()%>${memberVO.member_Pic}" alt="">
				</div>
				<div class="member_name">
					<div class="member_nickname">
						<span id="thisMemNickname">${memberVO.member_Name}</span>
					</div>
					<div class="member_id">
						<span id="thisMemId">${memberVO.member_ID}</span>
					</div>
				</div>
			</div>
			<div class="cmt_detail">
				<div class="cmt_text">
					<textarea name="CM_TEXT" id="" maxlength="500"></textarea>
				</div>
				<hr>
				<div id="conf">
					<div class="cmtBtnArea">
						<input type="submit" value="comment">
<!-- 					<p>comment</p> -->
					</div>
					<div id="netaArea">
						<div>評論是否劇透</div>
						<input type="checkbox" name="" id="netabare">
					</div>
				</div>
			</div>
			<div class="cmt_star">
				<div class="rating-box">
					<span class="rating-boxH1">Rating</span>
					<div class="rating">
						<span class="fa fa-star-o"></span> <span class="fa fa-star-o"></span>
						<span class="fa fa-star-o"></span> <span class="fa fa-star-o"></span>
						<span class="fa fa-star-o"></span>
					</div>
					<!-- <h4 id="rating-value"></h1> -->
				</div>
			</div>
		</div>
		<input type="hidden" name="MEMBER_ID" value="${memberVO.member_ID}">
		<input type="hidden" name="MV_ID" value="${movieVO.mvId}">
		<input type="hidden" name="CM_LIKE" value="0">
		<input type="hidden" name="CM_STAR" value="">
		<input type="hidden" name="CM_STATE" value="0">
		<input type="hidden" name="CM_DATE" value="">
		
		<input type="hidden" name="ttcmt" value="${ttcmt}">
		<input type="hidden" name="ttstar" value="${ttstar}">
		
		<input type="hidden" name="action" value="insert">
<!-- 		<input type="submit" value="送出新增"> -->
	</FORM>



	</div>

</div>
	<!--客服圖 請自行加連結-->
	<img class="cs"
		src="<%=request.getContextPath()%>/front_end/movieDetail/images/demo/cs.png"
		height="50px;" width="60px;" href="#"></img>

	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>
	</div>

	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$("#date button").click(function() {
			if ($(this).attr("class") == "") {
				$(this).attr("class", "clicked")
			} else {
				$(this).attr("class", "")
			}
		})
	</script>
	<script
		src="<%=request.getContextPath()%>/front_end/movieDetail//js/movie_detail.js"></script>
	<!-- rating system -->
	<script
		src="<%=request.getContextPath()%>/front_end/movieDetail//js/rating.js"></script>


	<!-- 加載Ajax -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
	<!-- Ajax的測試Script -->
	<script>
		//日期對應時段的Ajax
// 		$("#dateSelector").change(function(){
// 			$("#showingTime").html("");
// 			let SH_TIME1 = $("#dateSelector").val() + " 09:00:00";
// 			let mvId1 = ${movieVO.mvId};
// 			console.log(SH_TIME1);
// 			console.log(mvId1);
// 			let url = "${pageContext.request.contextPath}/showing/showing.do?action=getShowingByDate&SH_TIME=" + SH_TIME1 + "&mvId=" + mvId1;
// 			console.log(url);
// 			$.ajax({
// 			      type: "POST", //指定http參數傳輸格式為POST
// 			      url: url, //請求目標的url，可在url內加上GET參數，如 www.xxxx.com?xx=yy&xxx=yyy
// 			      dataType: "json",
// 			      async: false,
// 			      success: function (response) {
// 			    	  for(let res of response){
// 				        $("#showingTime").append("<option>" + res.SH_TIME + "</option>");  
// 				        console.log(res.SH_TIME);
// 			    	  }
// 			      },
// 			      //Ajax失敗後要執行的function，此例為印出錯誤訊息
// 			      error: function (xhr, ajaxOptions, thrownError) {
// // 			        alert(xhr.status + "\n" + thrownError);
// 			        alert("沒場ㄛ換一天ㄅ");
// 			      }
// 		    });
// 		});
		

		//評論點讚的Ajax
		$(document).ready(function(){			
			
		    $("#comment").on("click",".like", function(e){
		    	let MEMBER_ID1 = $('#thisMemId').text();
		    	let CM_ID1 = $(this).next().val();
		    	console.log("member ID: " + MEMBER_ID1);
                console.log("comment ID: " + CM_ID1);
		    	
		    	$.ajax({
				      type: "POST", //指定http參數傳輸格式為POST
				      url: "${pageContext.request.contextPath}/cmt/cmt.do?action=commentLike&MEMBER_ID=" + MEMBER_ID1 + "&CM_ID=" + CM_ID1, //請求目標的url，可在url內加上GET參數，如 www.xxxx.com?xx=yy&xxx=yyy
				      dataType: "json",
				      async: false,
				      success: function (response) {
				    	  console.log("total like: " + response);
				    	  console.log(e.target.textContent);
				    	  e.target.textContent = response;
					
				      },
				      //Ajax失敗後要執行的function，此例為印出錯誤訊息
				      error: function (xhr, ajaxOptions, thrownError) {
// 				        alert(xhr.status + "\n" + thrownError);
				        alert("登入啦");
				      }
			    });
		    	
		    })
		});
// 檢舉ajax按鈕
//新增視窗
let main = document.getElementsByClassName('main')[0];
let adds =  document.getElementsByClassName('reportBtn');
for(let add of adds){
add.onclick = addAccount;}	// 點擊後執行addAccount function

function addAccount(){
	console.log($(this).next().val())
	let cmId = $(this).next().val();
    const newAccount = document.querySelector('#reportDiv');
    if(newAccount){
        newAccount.remove();
    }
    // 區分前後區域
    const divNew = document.createElement('div');
    divNew.id = 'reportDiv';
    divNew.onclick = close;
    // 新增帳戶的視窗
    const divInput = document.createElement('div');
    divInput.id = 'dataInput';
    // 輸入名稱
    let inner = `
    <div id="reportDiv">
    <form action="${pageContext.request.contextPath}/ReportServlet.do">
    <table>
    <input type="hidden" name="mvId" value="${movieVO.mvId}">
        <tr>
            <td>評論編號</td>
            <td >` + cmId +`</td>
            <input type="hidden" name="cmId" value=` + cmId + `>
        </tr>

        <tr>
            <td>檢舉人</td>
            <td>${memberVO.member_ID} （${memberVO.member_Name}）</td>
            <input type="hidden" name="memberId" value="${memberVO.member_ID}">
        </tr>

        <tr>
            <td>檢舉類型</td>
            <td>
                <select name="rpType" id="">
                    <option value="其他">其他</option>
                    <option value="惡意暴雷">惡意暴雷</option>
                    <option value="謾罵">謾罵</option>
                    <option value="散佈廣告">散佈廣告</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>檢舉內容</td>
            <td><textarea name="rpText" id="" cols="30" rows="10" maxlength="50"></textarea></td>
        </tr>

        <tr>
            <td colspan="2">
            	<button name="action" value="insert" type="submit" >送出</button>
            	<button id="cancel" class="tablebt">取消</button>
            </td>

            <input type="hidden" name="rpState" value="0">
        </tr>
    </table>
</form>
</div>
`;
//    inner += `<div>
//        <button id="cancel" class="tablebt">取消</button>
//        <button id="enter" name="action" value="insert" type="submit" class="tablebt">確認</button>
//    </div></form>`;
    divInput.innerHTML = inner;
    main.append(divNew);
    main.append(divInput);
    const cancel = document.querySelector('#cancel');
    const cancel2 = document.querySelector('#dataInput');
    const enter = document.querySelector('#enter');
    cancel.onclick = close;
//     cancel2.onclick = close;
	$("#comment").hide();
	$("#copyright").hide();
    
};

//關閉新增帳戶的視窗
function close(){
    const newAccount = document.querySelector('#reportDiv');
    const dataInput = document.querySelector('#dataInput');
    if(newAccount !== null){
        newAccount.remove();
        dataInput.remove();
        $("#comment").show();
        $("#copyright").show();
    }
}
			
	
	</script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script>
	// 串接到訂票=====================
		
		let MV_ID = ${movieVO.mvId};
		$('.inputMV_ID').val(MV_ID);
		let SH_ID = '';
		let HL_ID = 0;
		
		
		
		
		$('#dateSelector').change((e) => {
			SH_TIME = e.target.value + " 09:00:00"; ;
			let url = "${pageContext.request.contextPath}/tkOrd/tkOrd.do?action=listShowings_ByCompositeQuery&MV_ID=" + MV_ID +"&SH_TIME=" + SH_TIME;
			$('#showingTime').removeAttr("disabled");
			$('#showingTime').empty();
			$('#showingTime').append('<option value=0>請選擇場次' );
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
		            				$('#showingTime').append('<option value='+ show.SH_ID + '>跨夜'+showTime+'  (數位)');
		            			}else{
		            				$('#showingTime').append('<option value='+ show.SH_ID + '>'+showTime+'  (數位)');          				
		            			} 		
		            		}else if ((show.SH_TYPE === 1) && (show.mvId === parseInt(MV_ID))){
		            			if((parseInt(showTimeStr.slice(-12, -10))< 6 || (parseInt(showTimeStr.slice(-12, -10)) === 12)) && (showTimeStr.slice(-3, -1) === 'AM')){
		            				$('#showingTime').append('<option value='+ show.SH_ID + '>跨夜'+showTime+'  (IMAX)');
		            			}else{
			            			$('#showingTime').append('<option value='+ show.SH_ID + '>'+showTime+'  (IMAX)');
		            			}		
		            		}
		            	
		            	}	
		            }
			})
			
		})
		
		
		
		
		
		$('#showingTime').blur((e) => {
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
		
		
		$('#bookBtn').click(function() {
			console.log("checkinnnnnnn")
			movieName = $('.MV_ID option:selected').text();
			if ('' === SH_ID || '0' === SH_ID ){
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