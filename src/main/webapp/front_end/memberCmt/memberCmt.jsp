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
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/memberCmt/css/memberCmt.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/layout.css" type="text/css">
<link rel="stylesheet" href="https://fontawesome.com/v5/icons/edit?s=solid">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/membercentre/css/membercentre.css" />
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
<!-- 		<hr class="line" /> -->
		<div id="cartTitle">
			<div id="titleCheck" class="check">
				<input type="checkbox" name="" id="checkAll" />
			</div>
			<div id="titleNo" class="no">評論項次</div>
			<div id="titleName" class="name">電影名稱</div>
			<div id="titleInfo" class="info">評論內容</div>
			<div id="titleStatus" class="status">狀態</div>
			<div id="titleEdit" class="edit">編輯</div>
		</div>
<!-- 		<hr class="line" /> -->

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cmt/cmt.do" id="form1">
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="hidden" name="action" value="selectedDelete">
								</FORM>
		<div id="cartBody">
		<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
			<c:forEach var="cmtVO" items="${list}">
			<c:if test="${cmtVO.CM_STATE != 2}">
				<div class="eachItem">
					<div class="check">
						<input type="checkbox" name="cmtID" class="checkOne" value="${cmtVO.CM_ID}" form="form1" />
					</div>
					<div class="vl"></div>
					<div class="no"><%= item++ %></div>
					<div class="vl"></div>
					<div class="name">${cmtVO.movieVO.mvName}</div>
					<div class="vl"></div>
					<div class="info">${cmtVO.CM_TEXT}</div>
					<div class="vl"></div>
					<div class="status">${cmtVO.CM_STATE == 0? '一般留言':''}${cmtVO.CM_STATE == 1?'劇透留言':''}</div>
					<div class="vl"></div>
					<div class="edit">
<!-- 						<button>編輯</button> -->
<!-- 						<button>刪除</button> -->
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/cmt/cmt.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> 
									<input type="hidden" name="CM_ID" value="${cmtVO.CM_ID}"> 
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
									<input type="hidden" name="action" value="delete">
								</FORM>
					</div>
				</div>
				</c:if>
			</c:forEach>
		</div>

		<hr class="line" style="margin-top: 20px" />
		<div id="cartArea">
			<div id="total">已選擇: 0 筆評論</div>
			<div id="cartBtn">
				<button class="button" form="form1" type="submit">
					<span>刪除</span>
				</button>
			</div>
		</div>


	</div>

</div>	
</main>

	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/memberCmt/js/memberCmt.js"></script>
</body>
</html>
