<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fd_inf.model.*"%>

<%
FdInfService fdInfSvc = new FdInfService();
List<FdInfVO> list = fdInfSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>飲食資料管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/fd_inf/styles/FDINFBack.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
						<div>所有餐飲資訊</div>
					</div>
				</div>
				<div class="btBlock">
					<a class="bt"
						href='<%=request.getContextPath()%>/back_end/fd_inf/addFdInf.jsp'>新增</a>
				</div>
				<div class="TKouter">

					<table class="TKinner">
						<tr>
							<th>編號</th>
							<th>種類</th>
							<th>餐飲名稱</th>
							<th>價格</th>
							<th>備註</th>
							<th>圖片</th>
							<th>狀態</th>
							<th>修改</th>
							<th>狀態更動</th>

						</tr>
						<%@ include file="page1.file"%>
						<c:forEach var="fdinfVO" items="${list}" begin="<%=pageIndex%>"
							end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${fdinfVO.fdID}</td>
								<td><c:choose>
										<c:when test="${fdinfVO.fdType == 0}">
														飲料
													</c:when>
										<c:when test="${fdinfVO.fdType == 1}">
														熟食
													</c:when>
									</c:choose></td>
								<td>${fdinfVO.fdName}</td>
								<td>$ ${fdinfVO.fdprice}</td>
								<td>${fdinfVO.fdDT}</td>
								<td><img
									src="<%=request.getContextPath()%>/fd_inf/fd_inf.do?action=getPic&fdID=${fdinfVO.fdID}"
									></td>
								<td id="state${fdinfVO.fdID}" >${fdinfVO.fdState == 0 ? "下架" : "上架"}</td>
								<td><FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/fd_inf/fd_inf.do"
										style="margin-bottom: 0px;">
										<input class="tablebt" type="submit" value="修改"> <input
											type="hidden" name="fdID" value="${fdinfVO.fdID}"> <input
											type="hidden" name="action" value="getOne_For_Update">
									</FORM></td>
								<td>
									<div class="btBlock">
										<a id="${fdinfVO.fdID}" class="tablebt updateState">${fdinfVO.fdState== 1 ? "下架" : "上架"}</a>
									</div>
								</td>
							</tr>

						</c:forEach>


					</table>
				</div>
				<div class="btBlock">

					<%@ include file="page2.file"%>

				</div>


			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>


	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<script>
// 	$('.updateState').click(function(){
		$('.updateState').click(function(){
			
		let fdID = $(this).attr('id');
		console.log(fdID);
		
		let url = "${pageContext.request.contextPath}/fd_inf/fd_inf.do?action=updateStatus&fdID="+fdID;

		
		$.ajax({
				url: url,
		        type: 'post',
		        dataType: 'json',
		        async: false,
		        timeout: 15000,
		        success: function (data) {
				        
				let newStatus = data.newStatus;

				let s = '';
				let t = '';
				if(newStatus == "1"){
					s = "上架";
					t = "下架";

				}else{
					s = "下架";
					t = "上架";
				}
				$("#state"+ fdID).text(s);
				$("#"+ fdID).text(t);
				


			}
		 });
	});

	</script>
</body>
</html>