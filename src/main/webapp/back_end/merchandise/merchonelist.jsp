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
						<div>商品查詢頁面</div>
					</div>
				</div>

					<div class="TKouter">

						<table class="TKinner tablesorter" id="myTable">
							<tr>
								<th></th>
								<th>輸入</th>
								<th></th>
							</tr>
							<tr>
								<td>商品編號:</td>
								<td>${merchVo.merchID}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品名稱:</td>
								<td>${merchVo.merchName}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品價格:</td>
								<td>${merchVo.merchPrice}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品類別:</td>
								<td>${merchVo.merchClass}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品銷售總數:</td>
								<td>${merchVo.soldTotal}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品庫存:</td>
								<td>${merchVo.merchStock}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品狀態:</td>
								<td>
								<c:choose>
    								<c:when test="${merchVo.merchStatus == '0'}">
       									下架
    								</c:when>
    								<c:when test="${merchVo.merchStatus == '1'}">
       									上架
    								</c:when>
    								<c:when test="${merchVo.merchStatus == '2'}">
       									主打
    								</c:when>
    							</c:choose>
    							</td>
								<td></td>
							</tr>
							<tr>
								<td>商品描述:</td>
								<td>${merchVo.merchDT}</td>
								<td></td>
							</tr>
							<tr>
								<td>商品圖一:</td>
								<td><img alt="" src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=1" style="width:200px;height:240px" class="updateimg"></td>
								<td></td>
							</tr>
							<tr>
								<td>商品圖二:</td>
								<td><img alt="" src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=2" style="width:200px;height:240px" class="updateimg"></td>
								<td></td>
							</tr>
							<tr>
								<td>商品圖三:</td>
								<td><img alt="" src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=3" style="width:200px;height:240px" class="updateimg"></td>
								<td></td>
							</tr>
							<tr>
								<td>商品圖四:</td>
								<td><img alt="" src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=4" style="width:200px;height:240px" class="updateimg"></td>
								<td></td>
							</tr>
							<tr>
								<td>商品圖五:</td>
								<td><img alt="" src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=5" style="width:200px;height:240px" class="updateimg"></td>
								<td></td>
							</tr>


						</table>
					</div>
					<div class="btBlock">
						<a class="tablebt"
								href="${pageContext.request.contextPath}/back_end/merchandise/mallIndex.jsp">首頁</a>
					</div>
				</FORM>
			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
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