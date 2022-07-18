<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ann.model.*"%>

<%
AnnService annSvc = new AnnService();
List<AnnVO> list = annSvc.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>公告管理</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

<!-- 公告_css -->
<!-- twitter-bootstrap-wizard css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/act/static/css/prettify.css">

<!-- Bootstrap Css -->
<link href="${pageContext.request.contextPath}/back_end/act/static/css/bootstrap.min.css" id="bootstrap-style"
	rel="stylesheet" type="text/css">
<!-- Icons Css -->
<link href="${pageContext.request.contextPath}/back_end/act/static/css/icons.min.css" rel="stylesheet" type="text/css">
<!-- App Css-->
<link href="${pageContext.request.contextPath}/back_end/act/static/css/app.min.css" id="app-style" rel="stylesheet"
	type="text/css">
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
		<div class="col-lg-12" style="width: 90%;margin-left: 70px;margin-top: 20px;
}">
			<div class="card">
				<div class="card-body">
					<h4 class="card-title mb-4">Wizard with progressbar</h4>

					<div id="progrss-wizard" class="twitter-bs-wizard">
						<ul class="twitter-bs-wizard-nav nav-justified">
							<li class="nav-item"><a href="#progress-seller-details"
								class="nav-link" data-toggle="tab"> <span
									class="step-number">01</span> <span class="step-title">Seller
										Details</span>
							</a></li>
							<li class="nav-item"><a href="#progress-company-document"
								class="nav-link" data-toggle="tab"> <span
									class="step-number">02</span> <span class="step-title">Company
										Document</span>
							</a></li>

<!-- 							<li class="nav-item"><a href="#progress-bank-detail" -->
<!-- 								class="nav-link" data-toggle="tab"> <span -->
<!-- 									class="step-number">03</span> <span class="step-title">Bank -->
<!-- 										Details</span> -->
							</a></li>
							<li class="nav-item"><a href="#progress-confirm-detail"
								class="nav-link" data-toggle="tab"> <span
									class="step-number">04</span> <span class="step-title">Confirm
										Detail</span>
							</a></li>
						</ul>

						<div id="bar" class="progress mt-4">
							<div
								class="progress-bar bg-success progress-bar-striped progress-bar-animated"></div>
						</div>
						<div class="tab-content twitter-bs-wizard-tab-content">
							<div class="tab-pane" id="progress-seller-details">
								<form>
									<div class="row">
										<div class="col-lg-6">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-firstname-input">First name</label>
												<input type="text" class="form-control"
													id="progress-basicpill-firstname-input">
											</div>
										</div>
										<div class="col-lg-6">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-lastname-input">Last name</label> <input
													type="text" class="form-control"
													id="progress-basicpill-lastname-input">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-lg-6">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-phoneno-input">Phone</label> <input
													type="text" class="form-control"
													id="progress-basicpill-phoneno-input">
											</div>
										</div>
										<div class="col-lg-6">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-email-input">Email</label> <input
													type="email" class="form-control"
													id="progress-basicpill-email-input">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="mb-3">
												<label class="form-label"
													for="progress-basicpill-address-input">Address</label>
												<textarea id="progress-basicpill-address-input"
													class="form-control" rows="2"></textarea>
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
							<li class="previous"><a href="javascript: void(0);">Previous</a></li>
							<li class="next"><a href="javascript: void(0);">Next</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<!-- end row -->

	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>



	<!-- JAVASCRIPT -->
	<script src="${pageContext.request.contextPath}/back_end/act/static/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/back_end/act/static/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/back_end/act/static/js/metisMenu.min.js"></script>
	<script src="${pageContext.request.contextPath}/back_end/act/static/js/simplebar.min.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/back_end/act/static/js/waves.min.js"></script> --%>

	<!-- twitter-bootstrap-wizard js -->
	<script src="${pageContext.request.contextPath}/back_end/act/static/js/jquery.bootstrap.wizard.min.js"></script>

	<script src="${pageContext.request.contextPath}/back_end/act/static/js/prettify.js"></script>

	<!-- form wizard init -->
	<script src="${pageContext.request.contextPath}/back_end/act/static/js/form-wizard.init.js"></script>

	<script src="${pageContext.request.contextPath}/back_end/act/static/js/app.js"></script>

</body>
</html>