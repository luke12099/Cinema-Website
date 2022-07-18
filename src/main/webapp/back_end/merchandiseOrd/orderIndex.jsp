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
						<div>商品訂單管理</div>
					</div>
				</div>
				<div class="btBlock" style="color:red">
					${errorMsgs.merchID}
				</div>
				<div class="TKouter">
					<table class="TKinner">
						<tr>
							<th></th>
							<th>
									<a class="tablebt" href="${pageContext.request.contextPath}/merchOrd/merchOrd.do?action=getAll_For_Display">全部訂單</a>
							</th>
							<th>	
									<a class="tablebt" href="${pageContext.request.contextPath}/back_end/merchandiseOrd/orderinsert.jsp">新增訂單</a>
							</th>
						</tr>
						<tr>
						<form action="${pageContext.request.contextPath}/merchOrd/merchOrd.do">
							<td>顯示會員訂單列表:</td>
							<td><input type="number" placeholder="請輸入會員編號"
								name="memberID" min=1 required></td>
							<td><button class="tablebt" type="submit" name="action"
									value="get_Member_Ordlist">確認</button></td>
						</form>
						</tr>
							<tr>
						<form action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do">
								<td>顯示訂單編號詳情:</td>
								<td><input type="number" name="merchOrdID" placeholder="請輸入訂單編號" min=1 required></td>
								<td><button class="tablebt" type="submit" name="action"
										value="getOrder_For_Display" >確認</button></td>
						</form>
							</tr>
					</table>
				</div>
				<div class="btBlock">
				</div>
			</div>
		</div>
	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>
	<aside id="aside">     
     <%@ include file="/back_end/aside_html.jsp"%>   
    </aside>
</body>

</html>