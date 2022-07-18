<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.showing.model.*"%>

<%
ShowingVO showingVO = (ShowingVO) request.getAttribute("showingVO"); //ShowingServlet.java (Concroller) �s�Jreq��showingVO���� (�]�A�������X��showingVO, �]�]�A��J��ƿ��~�ɪ�showingVO����)
%>
<%=showingVO == null%>--${showingVO.getmvId()}--${showingVO.mvId}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>������ƭק�</title>

<style>
table#table-1 {
	background-color: antiquewhite;
	/*     border: 2px solid black; */
	text-align: center;
	margin-bottom: 10px;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: antiquewhite;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
	white-space: nowrap;
}

th, td {
	padding-top: 5px;
	padding-bottom: 5px;
	padding-left: 5px;
	padding-right: 5px;
	border: 1px solid rgb(198, 155, 123);
}

input, select {
	border: 1px solid black;
	background-color: white;
	border-radius: 10px;
	outline-style: none;
	text-align: justify;
	padding: 5px 10px;
}

input[name="SH_SEAT_STATE"] {
	color: rgb(180, 180, 180);
	/* 	color: transparent; */
	/*     text-shadow: 0 0 10px #000; */
}
</style>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">

</head>
<body bgcolor='white'>
	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>
	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<main>

		<table id="table-1">
			<tr>
				<td>
					<h3>������ƭק�</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/back_end/showing/showing_select_page.jsp">�^����</a>
					</h4>
				</td>
			</tr>
		</table>


		<%-- ���~��C --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">�Эץ��H�U���~:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<jsp:useBean id="movieSvc" scope="page"
			class="com.movie.model.MovieService" />
		<jsp:useBean id="hallSvc" scope="page"
			class="com.hall.model.HallService" />
		<jsp:useBean id="showingSvc" scope="page"
			class="com.showing.model.ShowingService" />

		<FORM METHOD="post" ACTION="showing.do" name="form1">
			<table>
				<tr>
					<td>�����s��:<font color=red><b>*</b></font></td>
					<td><%=showingVO.getSH_ID()%></td>
				</tr>
				<tr>
					<td>�q�v�s��:</td>
					<td><select size="1" name="mvId">
							<c:forEach var="movieVO" items="${movieSvc.all}">
								<option value="${movieVO.mvId}"
									${(showingVO.mvId==movieVO.mvId)? 'selected':'' }>${movieVO.mvId}�i${movieVO.mvName}�j
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>�v�U�s��:</td>
					<td><select size="1" name="HL_ID">
							<c:forEach var="hallVO" items="${hallSvc.all}">
								<option value="${hallVO.hlId}">${hallVO.hlId}
									�i${hallVO.hlName}�j
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>�������A:</td>
					<%-- 		<td><input type="TEXT" name="SH_STATE" size="45"	value="<%=showingVO.getSH_STATE()%>" /></td> --%>
					<td><select size="1" name="SH_STATE">
							<option value="0">0�i������j
							<option value="1">1�i�w����j
					</select></td>
				</tr>
				<tr>
					<td>�����y�쪬�A:</td>
					<td><input type="TEXT" name="SH_SEAT_STATE" size="45"
						value="<%=showingVO.getSH_SEAT_STATE()%>" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>�ɬq:</td>
					<td><input name="SH_TIME" id="f_date1" type="text"
						value="<%=(showingVO == null) ? "2022-01-01 00:00:00" : showingVO.getSH_TIME()%>" /></td>
				</tr>
				<tr>
					<td>�q�v��������:</td>
					<%-- 		<td><input type="TEXT" name="SH_TYPE" size="45"	value="<%=showingVO.getSH_TYPE()%>" /></td> --%>
					<td><select size="1" name="SH_TYPE">
							<option value="0">0�i�Ʀ�j
							<option value="1">1�iIMAX�j
					</select></td>
				</tr>



			</table>

			<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="SH_ID" value="<%=showingVO.getSH_ID()%>">
			<input type="hidden" name="requestURL"
				value="<%=request.getParameter("requestURL")%>">
			<!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
			<input type="hidden" name="whichPage"
				value="<%=request.getParameter("whichPage")%>">
			<!--�u�Ω�:istAllEmp.jsp-->
			<input type="submit" value="�e�X�ק�">
		</FORM>

		<!-- <br>�e�X�ק諸�ӷ��������|:<br><b> -->
		<%-- <font color=blue>request.getParameter("requestURL"):</font> <%=request.getParameter("requestURL")%><br> --%>
		<%-- <font color=blue>request.getParameter("whichPage"): </font> <%=request.getParameter("whichPage")%> (���d�ҥثe�u�Ω�:istAllEmp.jsp))</b> --%>
	</main>
	<!-- <div id="tree"></div> -->
	<footer> ����V�v�� &copy; HIREME CINEMA 2022 </footer>
	<script src="<%=request.getContextPath()%>/back_end/showing/emp_aside.js"></script>
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 30,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d H:i:00',         //format:'Y-m-d H:i:s',
 		   value: '<%=showingVO.getSH_TIME()%>',

			//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
			//startDate:	            '2017/07/10',  // �_�l��
			//minDate:               '-1970-01-01', // �h������(���t)���e
			//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>