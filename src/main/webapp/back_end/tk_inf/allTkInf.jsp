<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tk_inf.model.*"%>

<%
TkInfService tkInfSvc = new TkInfService();
List<TkInfVO> list = tkInfSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>���ظ�ƺ޲z</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/tk_inf/styles/TKINFBack.css">
</head>


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
						<div>�Ҧ����ظ�T</div>
					</div>
				</div>
				<div class="btBlock">
					<a class="bt"
						href='<%=request.getContextPath()%>/back_end/tk_inf/addTkInf.jsp'>�s�W</a>
				</div>
				<div class="TKouter">
					<table class="TKinner">
						<tr>
							<th>�s��</th>
							<th>���W</th>
							<th>����</th>
							<th>����</th>
							<th>�Ƶ�</th>
							<th>�ק�</th>

						</tr>
						<c:forEach var="tkinfVO" items="${list}">
							<tr>
								<td>${tkinfVO.tkTypeID}</td>
								<td>${tkinfVO.tkType}</td>
								<td>$ ${tkinfVO.tkPrice}</td>
								<td><c:choose>
										<c:when test="${tkinfVO.tkDI == 0}">
														�Ʀ�
													</c:when>
										<c:when test="${tkinfVO.tkDI == 1}">
														IMAX
													</c:when>
									</c:choose></td>
								<td>${tkinfVO.tkTypeDT}</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/tk_inf/tk_inf.do"
										style="margin-bottom: 0px;">
										<input class="tablebt" type="submit" value="�ק�"> <input
											type="hidden" name="tkTypeID" value="${tkinfVO.tkTypeID}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>

							</tr>

						</c:forEach>

					</table>
				</div>

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