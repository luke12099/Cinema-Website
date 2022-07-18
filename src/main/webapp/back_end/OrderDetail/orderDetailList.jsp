<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.merchandise_inf.model.*"%>
<%@ page import="com.order_detail.model.*"%>
<%
List<OrderDetailVO> list = (List<OrderDetailVO>) session.getAttribute("orderDetailList");
List<MerchVO> insertlist = (List<MerchVO>) session.getAttribute("insertlist");
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
	href="${pageContext.request.contextPath}/back_end/OrderDetail/css/merchandise.css">
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
						<div>訂單編號 ${merchOrdVo.merchOrdID} 號細項列表</div>
					</div>
				</div>
				<div class="TKouter">

					<table class="TKinner tablesorter" id="myTable">
						<thead>
							<tr>
								<th><input type="checkbox" id="allcheckbox" form="checkbox checkbox1 checkbox2">全選</th>
								<th>訂單項次</th>
								<th>商品名稱</th>
								<th>購買數量</th>
								<th>產品狀態</th>
								<th>價格(單價)</th>
								<th>修改</th>
								<th>查看</th>
								<th>刪除</th>
							</tr>
						</thead>
						<%@ include file="page1.file"%>
						<tbody>
							<c:forEach var="orderDetail" items="${orderDetailList}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<%-- 						<c:forEach var="merch" items="${list}" > --%>
								<tr>
									<form
										action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do">
									<td><input type="checkbox" form="checkbox"
											class="forcheckbox forcheckbox1" name="item" value="${orderDetail.item}"></td>
									<td>    ${orderDetail.item}    </td>
									<td>${orderDetail.merchVO.merchName}</td>
									<td><input type="number" name="ordCount"
										value="${orderDetail.ordCount}" min=1></td>
									<td>
									<select name="ordStatus">
									<option value="0"${orderDetail.ordStatus == 0? 'selected':''}>備貨中</option>
									<option value="1"${orderDetail.ordStatus == 1? 'selected':''}>可取貨</option>
									<option value="2"${orderDetail.ordStatus == 2? 'selected':''}>已取貨</option>
									<option value="3"${orderDetail.ordStatus == 3? 'selected':''}>已取消</option>
									</select>
									</td>
									<td><input type="number" name="ordPrice"
										value="${orderDetail.ordPrice}" min=1></td>
									<td>
										<input class="tablebt" type="hidden" name="merchID" value="${orderDetail.merchID}">
										<input class="tablebt" type="hidden" name="item"
										value="${orderDetail.item}">
										<input class="tablebt" type="hidden" name="merchOrdID"
										value="${orderDetail.merchOrdID}">
										<button class="tablebt" type="submit" name="action"
											value="getOne_For_Update">修改</button></td>
									</form>
									<td>
									<a 
										href="${pageContext.request.contextPath}/merch/controller?action=getOne_For_Display&merchID=${orderDetail.merchID}"><button class="tablebt">查看</button></a>
									</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do">
											<input class="tablebt" type="hidden" name="item"
											value="${orderDetail.item}">
											<input class="tablebt" type="hidden" name="merchOrdID"
											value="${orderDetail.merchOrdID}">
											<button class="tablebt" type="submit" name="action"
												value="getOne_For_Delete">刪除</button></td>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="btBlock">
				<h1>總價:${merchOrdVo.merchOrdCount}元</h1>
						<input type="hidden" name="merchOrdID" value="${merchOrdVo.merchOrdID}" form="checkbox">
						<input type="hidden" name="merchOrdID" value="${merchOrdVo.merchOrdID}" form="checkbox1">
						<input type="hidden" name="merchOrdID" value="${merchOrdVo.merchOrdID}" form="checkbox2">
						<input type="hidden" name="action" value="getItem_For_Update" form="checkbox">
						<input type="hidden" name="action" value="getItem_For_Update" form="checkbox1">
						<input type="hidden" name="action" value="getItem_For_Update" form="checkbox2">
						<form action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do" id="checkbox">
						<input type="hidden" name="ordStatus" value="1">
						<input type="submit" class="tablebt" value="可取貨"></form>
						<form action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do" id="checkbox1">
						<input type="hidden" name="ordStatus" value="2"> 
						<input type="submit" class="tablebt" value="已取貨"></form>
						<form action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do" id="checkbox2">
						<input type="hidden" name="ordStatus" value="3"> 
						<input type="submit" class="tablebt" value="已取消"></form>
						<br>
						<button class="tablebt" id="add">新增明細</button>
						<input type="submit" class="tablebt" form="1234" value="訂單首頁">
				</div>
				<form
					action="${pageContext.request.contextPath}/back_end/merchandiseOrd/orderIndex.jsp"
					id="1234"></form>
				<div class="inputnone">
				
				</div>
				<div class="btBlock">
					<%@ include file="page2.file"%>
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
	<%@ include file="/back_end/OrderDetail/javascript/orderDetailjs.jsp"%>
</body>

</html>