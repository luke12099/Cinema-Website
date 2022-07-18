<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.merchandise_inf.model.*"%>
<%@ page import="com.merchandise_order.model.*"%>
<%
List<MerchOrdVO> list = (List<MerchOrdVO>) session.getAttribute("merchOrdlist");
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
						<div>全部訂單列表</div>
					</div>
				</div>
				<div class="TKouter">

					<table class="TKinner tablesorter" id="myTable">
						<thead>
							<tr>
								<th>會員編號</th>
								<th>訂單編號</th>
								<th>訂單狀態</th>
								<th>查詢</th>
								<th>刪除</th>
							</tr>
						</thead>
						<%@ include file="page1.file"%>
						<tbody>
							<c:forEach var="merchOrd" items="${merchOrdlist}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<%-- 						<c:forEach var="merch" items="${list}" > --%>
								<tr>
									<td><a class="tablebt"
										href="${pageContext.request.contextPath}/merchOrd/merchOrd.do?action=get_Member_Ordlist&memberID=${merchOrd.memberID}">${merchOrd.memberID}</td>
									<td>${merchOrd.merchOrdID}</td>
									<td>
									${merchOrd.merchOrdStatus==0?'備貨中':''}
									${merchOrd.merchOrdStatus==1?'可取貨':''}
									${merchOrd.merchOrdStatus==2?'已完成':''}
									${merchOrd.merchOrdStatus==3?'已取消':''}
									</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do">
											<input class="tablebt" type="hidden" name="memberID"
												value="${merchOrd.memberID}">
											<input class="tablebt" type="hidden" name="merchOrdID"
												value="${merchOrd.merchOrdID}">
											<button class="tablebt" type="submit" name="action"
												value="getOrder_For_Display">查詢</button>
										</form>
									</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/merchOrd/merchOrd.do">
											<input class="tablebt" type="hidden" name="merchOrdID"
												value="${merchOrd.merchOrdID}">
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
								href="${pageContext.request.contextPath}/back_end/merchandiseOrd/orderIndex.jsp"><button class="tablebt" style="margin-left:6px; margin-top:2px">返回</button></a>
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