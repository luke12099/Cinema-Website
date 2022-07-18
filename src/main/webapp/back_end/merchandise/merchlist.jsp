<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.merchandise_inf.model.*"%>
<%
List<MerchVO> list = (List<MerchVO>) session.getAttribute("merchlist");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/merchandise/css/merchlist.css">
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
						<div>所有商品</div>
					</div>
				</div>
				<div class="TKouter">

					<table class="TKinner tablesorter" id="myTable">
						<thead>
							<tr>
								<th>商品編號</th>
								<th>商品名稱</th>
								<th>商品狀態</th>
								<th>修改</th>
								<th>查詢</th>
								<th>刪除</th>
							</tr>
						</thead>
						<%@ include file="page1.file"%>
						<tbody>
							<c:forEach var="merch" items="${merchlist}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${merch.merchID}</td>
									<td>${merch.merchName}</td>
									<td>${merch.merchStatus == 0? '下架':''} ${merch.merchStatus == 1? '上架':''}
										${merch.merchStatus == 2? '主打':''}</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/merch/controller">
											<input class="tablebt" type="hidden" name="merchID"
												value="${merch.merchID}">
											<button class="tablebt" type="submit" name="action"
												value="getOne_For_Update">修改</button>
										</form>
									</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/merch/controller">
											<input class="tablebt" type="hidden" name="merchID"
												value="${merch.merchID}">
											<button class="tablebt" type="submit" name="action"
												value="getOne_For_Display">查詢</button>
										</form>
									</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/merch/controller">
											<input class="tablebt" type="hidden" name="merchID"
												value="${merch.merchID}">
											<button class="tablebt" type="submit" name="action"
												value="delete">刪除</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="btBlock">

					<%@ include file="page2.file"%>

						<a
								href="${pageContext.request.contextPath}/back_end/merchandise/mallIndex.jsp"><button class="tablebt" style="margin-left:6px; margin-top:2px">返回</button></a>

				</div>
			</div>

		</div>





	</main>

	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>
	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<script
		src='//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'></script>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/css/theme.bootstrap.min.css"></link>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.3/js/jquery.tablesorter.min.js"></script>

	<script>
		$("#myTable").tablesorter({
			theme : "",
			widgets : [ 'zebra' ]
		});
	</script>
</body>

</html>