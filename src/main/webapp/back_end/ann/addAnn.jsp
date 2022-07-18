<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
AnnVO annVO = (AnnVO) request.getAttribute("annVO");//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
AnnService annSvc = new AnnService();
List<AnnVO> list = annSvc.getAll();
pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>公告管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

<!-- AddAnn_css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/ann/css/annback_add.css">



<!-- 文本編輯器，目前刪掉不用 -->
<script src="https://cdn.ckeditor.com/4.7.3/basic/ckeditor.js"></script>

<link rel="stylesheet"
	href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
<script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>


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
		<div class="all">
			<div class="main">

				<div class="guide1outer">
					<div class="guide1">
						<div>新增公告</div>
					</div>
				</div>

<%-- 				<c:if test="${not empty errorMsgs}"> --%>
<!-- 					<font style="color: red">請修正以下錯誤:</font> -->
<!-- 					<ul> -->
<%-- 						<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 							<li style="color: red">${message}</li> --%>
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
<%-- 				</c:if> --%>


				<FORM METHOD="post" enctype="multipart/form-data"
					ACTION="<%=request.getContextPath()%>/ann/ann.do" name="form1">

					<div class="TKouter">

						<table class="TKinner">
							<tr>
								<td></td>
								<td>輸入</td>
								<td></td>
							</tr>
							<tr>
								<td>日期:</td>
								<td><input style="text-align: center;" type="text"
									id="ann_date1" name="ann_date" size="95"
									value="${param.ann_date}"></td>
								<td>${errorMsgs.ann_date}</td>
							</tr>
							<tr>
								<td>標題:</td>
								<td><input type="text" name="ann_title"
									placeholder="請輸入標題，為了更好的展示效果，標題字數在50字以內"
									onkeyUp="textLimitCheck(this, 50);" size="90"
									value="${param.ann_title}"><span id="messageCount">0</span>/50</td>
								<td>${errorMsgs.ann_title}</td>
							</tr>
							<tr>
								<td>內容:</td>
								<td><textarea style="resize: none; margin: 20px;"
										id="ann_content" name="ann_content" cols="90" rows="30">${param.ann_content}</textarea></td>
								<td>${errorMsgs.ann_content}</td>
							</tr>
							<tr>
								<td>圖片:</td>
								<td><img id="img" src="<%=request.getContextPath()%>/back_end/act/annNoPhoto.jpg" style="width: 300px; height: 250px;">
								<input id="ann_picture" type="file" name="ann_picture"
									value="${param.ann_picture}"></td>
								<td>${errorMsgs.ann_picture}</td>
							</tr>
						</table>
					</div>


					<div class="btBlock">
						<input type="hidden" name="action" value="insert"> <input
							type="submit" class="bt" value="送出新增">
					</div>
				</FORM>

			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>

	<script type="text/javascript">
		function textLimitCheck(thisArea, maxLength) {
			if (thisArea.value.length > maxLength) {
				alert(maxLength + ' 個字限制。 \r 超出的將自動清除');
				thisArea.value = thisArea.value.substring(0, 50);
				thisArea.focus();
			}
			messageCount.innerText = thisArea.value.length;
// 			messageCount1.innerText = thisArea.value.length;
// 			messageCount2.innerText = thisArea.value.length;
		}//標題輸入框字數限制
	</script>


</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date ann_date = null;
try {
	ann_date = annVO.getAnn_date();
} catch (Exception e) {
	ann_date = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back_end/ann/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back_end/ann/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#ann_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=ann_date%>'
	// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
<script>
let updateFile = document.getElementById('ann_picture');
let updateImg = document.getElementById('img');

  updateFile.addEventListener('change', function(e){
    updateImg.src = URL.createObjectURL(e.target.files[0]);
  })

</script>



</html>