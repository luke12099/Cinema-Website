<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul>
	<li id="index"><span><a href="${pageContext.request.contextPath}/back_end/empIndex.jsp">首頁</a></span></li>
	<li><span>資訊管理</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/cinemaInfoManege/cinemaInfoManege.jsp">影城資訊管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/ManageMV/manageMV.jsp">電影資訊管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/tk_inf/allTkInf.jsp">票價資訊管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/fd_inf/allFdInf.jsp">餐飲資訊管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/ManageHall/manageHall.jsp">影廳資訊管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/showing/showing_select_page.jsp">場次管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/ManageSeat/manageSeat.jsp">座位管理</a></li>
		</ul></li>
	<li><span>票務管理</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/tk_ord/sellTK.jsp">現場購票/購餐</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/refundTicket/refundIndex.jsp">票務訂單管理</a></li>
		</ul></li>
	<li><span>評論相關</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/ManageReport/manageReport.jsp">舉報回應管理</a></li>
		</ul></li>
	<li><span>商城相關</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/merchandise/mallIndex.jsp">商品資訊</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/merchandiseOrd/orderIndex.jsp">商城訂單</a></li>
		</ul></li>
	<li><span>活動相關</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/act/allAct.jsp">促銷活動管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/wish/wishPond.jsp">許願池管理</a></li>
		</ul></li>
	<li><span>公告客服</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/ann/allAnn.jsp">公告管理</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/faq/allFaq.jsp">常見問題管理</a></li>
		</ul></li>
	<li><span>員工管理</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/emp/empAcc.jsp">帳號與權限</a></li>
			<li><a href="${pageContext.request.contextPath}/back_end/emp/empSelf.jsp">個人資料維護</a></li>
		</ul></li>
	<li><span>會員管理</span>
		<ul>
			<li><a href="${pageContext.request.contextPath}/back_end/member/listAllMember.jsp">會員帳號管理</a></li>
		</ul></li>
</ul>
<script>
	const privilege = [
		<%
			com.emp_account.model.EmpAccountVO eVO = (com.emp_account.model.EmpAccountVO)session.getAttribute("empAccount");
			// 取出員工權限
			if(eVO != null){
				com.emp_privilege.model.EmpPrivilegeService empPriSvc = new com.emp_privilege.model.EmpPrivilegeService();
				java.util.List<com.emp_privilege.model.EmpPrivilegeVO> empPriVOs = empPriSvc.getOneEmpPrivileges(eVO.getEmp_no());
				for(com.emp_privilege.model.EmpPrivilegeVO empPriVO: empPriVOs){
					out.print("'" + empPriVO.getFcName() + "', ");
				}
			}
		%>
	];
	// 如果沒有該權限就隱藏側邊欄
	const lis = document.querySelectorAll('ul>li>ul>li');
	for(let li of lis){
		if(!privilege.includes(li.innerText)){
			console.log(li.innerText);
			li.style.display = 'none';
		}
	}
</script>
