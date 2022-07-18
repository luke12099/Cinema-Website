<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
AnnVO annVO = (AnnVO) request.getAttribute("annVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>

<%= annVO==null %>


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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/ann/css/annback_update.css">


<script src="https://cdn.ckeditor.com/4.7.3/basic/ckeditor.js"></script>

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
						<div>修改公告</div>
					</div>
				</div>
				
				<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

				<FORM METHOD="post" name="form1" enctype="multipart/form-data"
					ACTION="<%=request.getContextPath()%>/ann/ann.do" name="form1">
					<div class="TKouter">

						<table class="TKinner">
							<tr>
								<td></td>
								<td>輸入</td>
								<td></td>
							</tr>
							<tr>
								<td>編號:</td>
								<td>${annVO.ann_no}</td>
								<td></td>
							</tr>
							<tr>
								<td>日期:</td>
								<td><input style="text-align: center;" type="text"
									id="ann_date1" name="ann_date" size="95"
									value="${annVO.ann_date}"></td>
								<td>${errorMsgs.ann_date}</td>
							</tr>
							<tr>
								<td>標題:</td>
								<td><input type="text" name="ann_title"
									placeholder="請輸入標題，為了更好的展示效果，標題字數在50字以內"
									onkeyUp="textLimitCheck(this, 50);" size="90"
									value="${annVO.ann_title}"><span id="messageCount">0</span>/50</td>
								<td>${errorMsgs.ann_title}</td>
							</tr>
							<tr>
								<td>內容:</td>
								<td><textarea style="margin: 20px;" id="ann_content"
										name="ann_content" cols="90" rows="25">${annVO.ann_content}</textarea></td>
								<td>${errorMsgs.ann_content}</td>
							</tr>
							<tr>
								<td>圖片:</td>
								<td class="ann_picture"><img id="img" src=<%=request.getContextPath()%>${annVO.ann_picture} style="width: 300px; height: 250px;"> 
								<input id="ann_picture" type="file" name="ann_picture" size="45" value="<%=annVO.getAnn_picture()%>" onclick="show()"/></td>
								<td>${errorMsgs.ann_picture}</td>
							</tr>

						</table>
					</div>
					<div class="btBlock">
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="ann_no" value="${annVO.ann_no}"> <input
							type="submit" class="bt" value="送出修改">


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
	
	<script type="text/javascript" src="jquery-3.2.1.js"></script>
<script type="text/javascript">
		function show() {
        var fileTag = document.getElementById('file');
        fileTag.onchange = function () {
            var file = fileTag.files[0];
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    document.getElementById('img').setAttribute('src', fileReader.result);
                }
            };
            fileReader.readAsDataURL(file);
        };
    };
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
	src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
		   value: '<%=ann_date%>',
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