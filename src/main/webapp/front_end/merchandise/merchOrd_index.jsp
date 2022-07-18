<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cmt.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.movie.model.*"%>

<%
MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
CmtService cmtSvc = new CmtService();
List<CmtVO> list = cmtSvc.getCmtsBymember_ID(memberVO.getMember_ID());
pageContext.setAttribute("list", list);
Integer item = 1;
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/merchandise/css/merchOrd.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/layout.css" type="text/css">
<link rel="stylesheet" href="https://fontawesome.com/v5/icons/edit?s=solid">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/merchandise/css/membercentre.css" />
</head>
<body>

<!-- 置頂按鈕 -->
	<button type="button" id="BackTop" class="toTop-arrow"></button>
	 <script>
	    $(function () {
	      $('#BackTop').click(function () {
	        $('html,body').animate({ scrollTop: 0 }, 333);
	      });
	      $(window).scroll(function () {
	        if ($(this).scrollTop() > 300) {
	          $('#BackTop').fadeIn(222);
	        } else {
	          $('#BackTop').stop().fadeOut(222);
	        }
	      }).scroll();
	    });
	  </script>
	 <div class="wrapper row1" style="height: 60px;">
	  <jsp:include page="/front_end/header.jsp" />
	 </div>


<main>
<div id="mainDiv">

		<div class="side-menu">
			<nav>
				<a
					href="${pageContext.request.contextPath}/front_end/membercentre/membermod.jsp">
					<i class="fa fa-edit" aria-hidden="true"></i> 會員修改資料
				</a> <a href="${pageContext.request.contextPath}/front_end/wish/wishPage.jsp"> <i class="fa fa-object-group" aria-hidden="true"></i>
					許願池
				</a> <a href="${pageContext.request.contextPath}/front_end/memberCmt/memberCmt.jsp"> <i class="fa fa-clone" aria-hidden="true"></i> 評論區
				</a>
				 <a href="#"> <i class="fa fa-gavel" aria-hidden="true"></i> 訂單明細</a>
			</nav>
		</div>
		

	<div id="cartMain">

<iframe src="${pageContext.request.contextPath}/front_end/merchandise/merchOrd.jsp" frameborder="0" width="100%" height="100%"></iframe>


	</div>

</div>


	
	
</main>

	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/memberCmt/js/memberCmt.js"></script>
</body>
</html>
