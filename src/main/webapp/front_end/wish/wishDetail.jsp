<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>許願投票活動</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/css/layout.css" type="text/css">
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://fontawesome.com/v5/icons/edit?s=solid">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/membercentre/css/membercentre.css" />
	<!-- 許願池 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/wish/css/wishDetail.css">
</head>
<body onload="connect();" onunload="disconnect();">
	<%@ include file="/front_end/header.jsp"%>

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
		<!-- <div id="content">
    <iframe src="" width="100%" height="100%" frameborder="0"></iframe>
  </div> -->
 	<% 
 		int i = 0;
 		java.util.List<Integer> tkCounts = (java.util.List<Integer>) request.getAttribute("tkCounts");
 	%>
	<!-- 許願池 -->
	<div id="main">
		<h1>許願池 - ${wish_name}</h1>
		<button id="return"><a href="${pageContext.request.contextPath}/front_end/wish/wishPage.jsp">返回</a></button>
		<h2>投票期間: ${wish_start} ~ ${wish_end}</h2>
		<h2>投票實況</h2>
		<div id="vote">
		 	<c:forEach var="eventOps" items="${eventMap}">
				<div>
		   			<h3>選項: ${eventOps.value}</h3>
						<div class="color" style='height: 20px; width: <%=tkCounts.get(i)*10%>px; background-color: rgb(160, 188, 194);'>
							<h5>票數: <%=tkCounts.get(i++)%></h5>
		    			</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${restTicket != null}">
			<div id="divPlate" onclick="windowClose()" style="display: block;"></div>
			<div id="dbConfirm" style="display: block;">
				<h3>投票成功! 剩餘票數為${restTicket}</h3>
				<div>
	        		<button class="enter" type="button" onclick="windowClose()">確認</button>
	    		</div>
			</div>
		</c:if>
	</div>
	<!-- 許願池 -->
	</div>
	
	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>
	</div>
	<script src="${pageContext.request.contextPath}/front_end/wish/js/voteWish.js"></script>
	<script>
		// web socket
		let ws;
		const self = 'memberId${memberVO.member_ID}';
		const MyPoint = "/FreshWhenVote/" + self;
		const host = window.location.host; // localhost:8081
		const path = window.location.pathname; // path of this page
		const webCtx = path.substring(0, path.indexOf('/', 1)); // get project name
		const endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			 			// ws://    localhost:8081         /Project /endpoint/${param}
						// ws 通訊協定
		function connect(){
			ws = new WebSocket(endPointURL);
			
			ws.onopen = function() {}
			
			ws.onmessage = function(e){
				if('refresh' == e.data){
					window.setTimeout(( () => location.reload() ), 500); // 收到推播後刷新頁面
				}
			}
			
			ws.onclose = function(){}
			
			ws.onerr = function(){}
		}
						
		function disconnect(){ ws.close();}
		
	</script>
</body>
</html>