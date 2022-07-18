<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	href="${pageContext.request.contextPath}/back_end/merchandise/css/merchandise.css">
</head>
<jsp:useBean id="merchSvc" scope="page"
	class="com.merchandise_inf.model.MerchService" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.js"></script>
	
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
						<div>訂單新增頁面</div>
					</div>
				</div>

				<form
					action="${pageContext.request.contextPath}/merchOrd/merchOrd.do"
					method="post">
					<div class="TKouter">
						<table class="TKinner" id="table">

							<tr>
								<th style="color:red">${errorMsgs.orderDetail}</th>
								<th></th>
								<th></th>
							</tr>

							<tr>
								<td>會員編號: </td><td>
								<input type="text" placeholder="請輸入會員編號"
									name="memberID" value="${merchOrdVo.memberID}" required></td>
									<td style="color:red">${errorMsgs.memberID}</td>
							</tr>
							<tr>
								<td style="width: 300px;">商品名稱</td>
								<td>商品數量</td>
								<td>商品價格</td>
							</tr>
							<tr>
								<td ><select name="merchID" id="select" style="width: 300px;">
										<option value="0">請選擇</option>
										<c:forEach var="merchVo" items="${merchSvc.all}">
											<option value="${merchVo.merchID}">${merchVo.merchName}</option>
										</c:forEach>
								</select></td>
								<td><input type="number" name="merchCount"
									class="totalCount" min=0 placeholder="0"
									value="${orderDetailVo.ordCount}" required></td>
								<td class="merchPrice"></td>
						</table>
					</div>
					<div class="btBlock">
						<input type="hidden" name="totalCount" value="0" id="totalCount">
						<input type="hidden" name="action" value="insert1"> <input
							type="submit" class="bt" value="送出新增"> <input
							class="tablebt" form="1234" type="submit" value="放棄新增">
					</div>
				</FORM>
				<form
					action="${pageContext.request.contextPath}/back_end/merchandiseOrd/orderIndex.jsp"
					id="1234"></form>
			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>
	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<%@ include
		file="/back_end/merchandiseOrd/javascript/orderinsertjs.jsp"%>

</body>

</html>