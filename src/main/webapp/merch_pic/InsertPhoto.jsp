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
		<nav>
			<div id="logo">
				<img
					src="${pageContext.request.contextPath}/back_end/logo2noline.jpg">
			</div>
			<h2>員工後台操作系統</h2>
			<ul>
				<li>登出</li>
			</ul>
		</nav>
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
						<div>商品資訊管理</div>
					</div>
				</div>
				<div class="btBlock" style="color: red">${errorMsgs.merchID}</div>
				<div class="TKouter">
					<table class="TKinner">
						<tr>
							<td>一鍵新增:</td>
							<td><form
									action="${pageContext.request.contextPath}/merch/controller">
									<td><button class="tablebt" type="submit" name="action"
											value="addphoto">新增</button>
								</form></td>
						</tr>


					</table>
				</div>
				<div class="btBlock"></div>
			</div>
		</div>
		<div style="display: none" id="success">${success}</div>


	</main>
	<c:if test="${not empty success}">
		<script type="text/javascript" language="javascript">
			let success = document.getElementById("success");
			alert(success.textContent);
			// 			alert(${success});
		</script>
			${remove.success};
		
	</c:if>
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>

</body>

</html>