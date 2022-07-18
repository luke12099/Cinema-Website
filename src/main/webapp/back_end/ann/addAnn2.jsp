<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
AnnVO annVO = (AnnVO) request.getAttribute("annVO");//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
AnnService annSvc = new AnnService();
List<AnnVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>公告管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">


<!-- twitter-bootstrap-wizard css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back_end/ann/static/css/prettify.css">

<!-- Bootstrap Css -->
<link
	href="${pageContext.request.contextPath}/back_end/ann/static/css/bootstrap.min.css"
	id="bootstrap-style" rel="stylesheet" type="text/css">
<!-- Icons Css -->
<link
	href="${pageContext.request.contextPath}/back_end/ann/static/css/icons.min.css"
	rel="stylesheet" type="text/css">
<!-- App Css-->
<link
	href="${pageContext.request.contextPath}/back_end/ann/static/css/app.min.css"
	id="app-style" rel="stylesheet" type="text/css">


</head>


<body>
	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>
	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>
	<!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
	<main>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="col-lg-12"
			style="width: 90%; margin-left: 70px; margin-top: 20px;">
			<div class="card">
				<div class="card-body">
<!-- 					<h4 class="card-title mb-4">公 告 管 理</h4> -->

					<div id="progrss-wizard" class="twitter-bs-wizard">
						<ul class="twitter-bs-wizard-nav nav-justified">
							<li class="nav-item"><a href="#progress-seller-details"
								class="nav-link" data-toggle="tab"> <span
									class="step-number">01</span> <span class="step-title">新增內容</span>
							</a></li>
							<li class="nav-item"><a href="#progress-company-document"
								class="nav-link" data-toggle="tab"> <span
									class="step-number">02</span> <span class="step-title">新增圖片</span>
							</a></li>
<!-- 							<li class="nav-item"><a href="#progress-bank-detail" -->
<!-- 								class="nav-link" data-toggle="tab"> <span -->
<!-- 									class="step-number">03</span> <span class="step-title">Bank -->
<!-- 										Details</span> -->
<!-- 							</a></li> -->
<!-- 							<li class="nav-item"><a href="#progress-confirm-detail" -->
<!-- 								class="nav-link" data-toggle="tab"> <span -->
<!-- 									class="step-number">04</span> <span class="step-title">Confirm -->
<!-- 										Detail</span> -->
<!-- 							</a></li> -->
						</ul>

						<div id="bar" class="progress mt-4">
							<div
								class="progress-bar bg-success progress-bar-striped progress-bar-animated"></div>
						</div>
						<div class="tab-content twitter-bs-wizard-tab-content">
							<div class="tab-pane" id="progress-seller-details">
								<form action="${pageContext.request.contextPath}/ann/ann.do"
									method="post">
									<div class="row">
										<div class="col-lg-12">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-firstname-input">公告日期</label> <input
													style="text-align: center;" type="text" id="ann_date1"
													name="ann_date" size="128" value="${param.ann_date}">
													<p>${errorMsgs.ann_dadt}</p>
											</div>
										</div>
										<!-- 										<div class="col-lg-6"> -->
										<!-- 											<div class="mb-3"> -->
										<!-- 												<label class="form-label" -->
										<!-- 													for="progress-basicpill-lastname-input">Last name</label> <input -->
										<!-- 													type="text" class="form-control" -->
										<!-- 													id="progress-basicpill-lastname-input"> -->
										<!-- 											</div> -->
										<!-- 										</div> -->
									</div>

									<div class="row">
										<div class="col-lg-12">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-phoneno-input">公告標題</label> <input
													type="text" class="form-control"
													id="progress-basicpill-phoneno-input">
													<p>${errorMsgs.ann_title}</p>
											</div>
										</div>
										<!-- 										<div class="col-lg-6"> -->
										<!-- 											<div class="mb-3"> -->
										<!-- 												<label class="form-label" -->
										<!-- 													for="progress-basicpill-email-input">Email</label> <input -->
										<!-- 													type="email" class="form-control" -->
										<!-- 													id="progress-basicpill-email-input"> -->
										<!-- 											</div> -->
										<!-- 										</div> -->
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-address-input">公告內容</label>
												<textarea id="progress-basicpill-address-input"
													class="form-control" rows="5"></textarea>
													<p>${errorMsgs.ann_content}</p>
											</div>
										</div>
									</div>
								</form>
							</div>
							<div class="tab-pane" id="progress-company-document">
								<div>
									<form>
										<div class="row">
											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-pancard-input">PAN Card</label> <input
														type="text" class="form-control"
														id="progress-basicpill-pancard-input">
												</div>
											</div>

											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-vatno-input">VAT/TIN No.</label> <input
														type="text" class="form-control"
														id="progress-basicpill-vatno-input">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-cstno-input">CST No.</label> <input
														type="text" class="form-control"
														id="progress-basicpill-cstno-input">
												</div>
											</div>

											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-servicetax-input">Service
														Tax No.</label> <input type="text" class="form-control"
														id="progress-basicpill-servicetax-input">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-companyuin-input">Company
														UIN</label> <input type="text" class="form-control"
														id="progress-basicpill-companyuin-input">
												</div>
											</div>

											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-declaration-input">Declaration</label>
													<input type="text" class="form-control"
														id="progress-basicpill-declaration-input">
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="tab-pane" id="progress-bank-detail">
								<div>
									<form>
										<div class="row">
											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-namecard-input">Name on
														Card</label> <input type="text" class="form-control"
														id="progress-basicpill-namecard-input">
												</div>
											</div>

											<div class="col-lg-6">
												<div class="mb-3">
													<label>Credit Card Type</label> <select class="form-select">
														<option selected=" ">Select Card Type</option>
														<option value="AE">American Express</option>
														<option value="VI">Visa</option>
														<option value="MC">MasterCard</option>
														<option value="DI">Discover</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-cardno-input">Credit Card
														Number</label> <input type="text" class="form-control"
														id="progress-basicpill-cardno-input">
												</div>
											</div>

											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-card-verification-input">Card
														Verification Number</label> <input type="text"
														class="form-control"
														id="progress-basicpill-card-verification-input">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6">
												<div class="mb-3">
													<label class="form-label"
														for="progress-basicpill-expiration-input">Expiration
														Date</label> <input type="text" class="form-control"
														id="progress-basicpill-expiration-input">
												</div>
											</div>

										</div>
									</form>
								</div>
							</div>
							<div class="tab-pane" id="progress-confirm-detail">
								<div class="row justify-content-center">
									<div class="col-lg-6">
										<div class="text-center">
											<div class="mb-4">
												<i
													class="mdi mdi-check-circle-outline text-success display-4"></i>
											</div>
											<div>
												<h5>Confirm Detail</h5>
												<p class="text-muted">If several languages coalesce, the
													grammar of the resulting</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<ul class="pager wizard twitter-bs-wizard-pager-link">
							<li class="previous" style="float:none;"><a href="javascript: void(0);">上一頁</a></li>
							<li class="next" style="float:none;"><a href="javascript: void(0);">下一頁</a></li>
							<li class="done" style="float:none;" name="action" value="insert"><a href="javascript: void(0);">送出新增</a></li>
							<li type="hidden" name="action" value="insert"> <input type="submit" class="bt" value="送出新增">
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- end row -->

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>

	<script type="text/javascript">
		function textLimitCheck(thisArea, maxLength) {
			if (thisArea.value.length > maxLength) {
				alert(maxLength + ' 個字限制。 \r 超出的將自動清除');
				thisArea.value = thisArea.value.substring(0, 50);
				thisArea.focus();
			}
			messageCount.innerText = thisArea.value.length;
// 			messageCount1.innerText = thisArea.value.length;
// 			messageCount2.innerText = thisArea.value.length;
		}//標題輸入框字數限制
	</script>


	<!-- JAVASCRIPT -->
	<script
		src="${pageContext.request.contextPath}/back_end/ann/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back_end/ann/static/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back_end/ann/static/js/metisMenu.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back_end/ann/static/js/simplebar.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/back_end/ann/static/js/waves.min.js"></script>

	<!-- twitter-bootstrap-wizard js -->
	<script
		src="${pageContext.request.contextPath}/back_end/act/static/js/jquery.bootstrap.wizard.min.js"></script>

	<script
		src="${pageContext.request.contextPath}/back_end/act/static/js/prettify.js"></script>

	<!-- form wizard init -->
	<script
		src="${pageContext.request.contextPath}/back_end/act/static/js/form-wizard.init.js"></script>

	<script
		src="${pageContext.request.contextPath}/back_end/act/static/js/app.js"></script>


</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date ann_date = null;
try {
	ann_date = annVO.getAnn_date();
} catch (Exception e) {
	ann_date = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<!-- 下面加了會跑版 -->
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/back_end/ann/datetimepicker/jquery.js"></script> --%>
<script
	src="<%=request.getContextPath()%>/back_end/ann/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        $('#ann_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=ann_date%>'
	// value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
</script>




</html>