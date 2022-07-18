<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tk_inf.model.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>票種資料管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/tk_inf/styles/addTKINFBack.css">
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
						<div>新增票種</div>
					</div>
				</div><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tk_inf/tk_inf.do" name="form1">
				<div class="TKouter">
					
						<table class="TKinner">
							<tr>
								<th></th>
								<th>輸入</th>
								<th></th>
							</tr>
							<tr>
								<td>票種名稱:</td>
								<td><input type="text" name="tkType" size="45"
									value="${param.tkType}"></td>
								<td style="color:red;">${errorMsgs.tkType}</td>
							</tr>
							<tr>
								<td>定價:</td>
								<td><input type="text" name="tkPrice" size="45"
									value="${param.tkPrice}"></td>
								<td style="color:red;">${errorMsgs.tkPrice}</td>
							</tr>
							<tr>
								<td>播放種類:</td>
								<td><select name="tkDI">
										<option value="0">數位</option>
										<option value="1">IMAX</option>
								</select></td>
								<td></td>
							</tr>
							<tr>
								<td>備註:</td>
								<td><input type="text" name="tkTypeDT" size="45"
									value="${param.tkTypeDT}" /></td>
								<td style="color:red;">${errorMsgs.tkTypeDT}</td>
							</tr>

						</table>
				</div>
				<div class="btBlock">
					<input type="hidden" name="action" value="insert"> <input
						type="submit" class="bt" value="送出新增">

				
			</div></FORM>
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