<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fd_inf.model.*"%>

<%
FdInfVO fdInfVO = (FdInfVO) request.getAttribute("fdInfVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�\����ƺ޲z</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/fd_inf/styles/FDINFBack.css">
</head>

<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var imageTagID = input.getAttribute("targetID");
			var reader = new FileReader();
			reader.onload = function(e) {
				var img = document.getElementById(imageTagID);
				img.setAttribute("src", e.target.result)
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<body>
	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>

	<aside id="aside"></aside>
	<!-- �A�̪����e�Щ�b <main> ���Ҥ��A��L�����Ű�! -->
	<main>
		<div class="all">
			<div class="main">

				<div class="guide1outer">
					<div class="guide1">
						<div>�s�W�\��</div>
					</div>
				</div>

				<FORM METHOD="post" enctype="multipart/form-data"
					ACTION="<%=request.getContextPath()%>/fd_inf/fd_inf.do"
					name="form1">
					<div class="TKouter">

						<table class="TKinner">
							<tr>
								<th></th>
								<th>��J</th>
								<th></th>
							</tr>
							<tr>
								<td>�\�v����:</td>
								<td><select name="fdType">
										<option value="0" ${param.fdType=='0'? "selected" : ""}>����</option>
										<option value="1" ${param.fdType=='1'? "selected" : ""}>����</option>
								</select></td>
								<td></td>
							</tr>
							<tr>
								<td>�\���W��:</td>
								<td><input type="text" name="fdName" size="45"
									value="${param.fdName}"></td>
								<td style="color: red;">${errorMsgs.fdName}</td>
							</tr>
							<tr>
								<td>�w��:</td>
								<td><input type="text" name="fdprice" size="45"
									value="${param.fdprice}"></td>
								<td style="color: red;">${errorMsgs.fdprice}</td>
							</tr>
							<tr>
								<td>�Ƶ�:</td>
								<td><input type="text" name="fdDT" size="45"
									value="${param.fdDT}" /></td>
								<td style="color: red;">${errorMsgs.fdDT}</td>
							</tr>
							<tr>
								<td></td>
								<td>

								
								
								<img id="preview_img"
									src="<%=request.getContextPath()%>/back_end/fd_inf/imges/123.jpg" style="width: 100px; height: 120px;"><br>
									<input type="file" name="fdPicture" accept="image/*"
									onchange="readURL(this)" targetID="preview_img"
									value="${fdInfVO==null?'':fdInfVO.getFdPicture()}" />
								</td>
								<td>
								
								</td>
							</tr>

							<tr>
								<td>�P�⪬�A:</td>
								<td><select name="fdState">
										<option value="0" ${param.fdState=='0'? "selected" : ""}>�U�[</option>
										<option value="1" ${param.fdState=='1'? "selected" : ""}>�W�[</option>
								</select></td>
								<td></td>
							</tr>

						</table>
					</div>
					<div class="btBlock">
						<input type="hidden" name="action" value="insert"> <input
							type="submit" class="bt" value="�e�X�s�W">


					</div>
				</FORM>
			</div>

		</div>

	</main>
	<!-- <div id="tree"></div> -->
	<footer> ����V�v�� &copy; HIREME CINEMA 2022 </footer>



	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
</body>
</html>