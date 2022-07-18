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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/wish/css/wishPage.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/wish/css/wishNotify.css">
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
 	
	<!-- 許願池 -->
	<div id="main">
		<h1>許願池投票活動</h1>
		<span style="color: red;">${errMsg.isSuccess}</span>
<!-- 		取消 -->
<%--         <button id="historyWish"><a href="${pageContext.request.contextPath}/front_end/wish/wishHis.jsp">歷屆回顧</a></button> --%>
        <div id="wishList">
            <table>
                <tr>
                    <th>編號</th>
                    <th>名稱</th>
                    <th>起始時間</th>
                    <th>結束時間</th>
                    <th>查看詳情</th>
                    <th>參加投票</th>
                </tr>
                <jsp:useBean id="wishSvc" class="com.wishing_pond.model.WishingPondService"/>
                <c:forEach var="event" items="${wishSvc.avaliable}">
	                 <tr>
	                     <td>${event.wish_no}</td>
	                     <td>${event.wish_name}</td>
	                     <td>${event.wish_start}</td>
	                     <td>${event.wish_end}</td>
	                     <td>
	                     	<form action="${pageContext.request.contextPath}/wish/WishingVote.do" method="post">
		                      	<button type="submit" name="action" value="seeOneEvent"><img src="${pageContext.request.contextPath}/back_end/wish/icons8-detail-64.png" alt=""></button>
		                      	<input type="hidden" name="wish_no" value="${event.wish_no}">
		                      	<input type="hidden" name="wish_name" value="${event.wish_name}">
		                      	<input type="hidden" name="wish_start" value="${event.wish_start}">
		                      	<input type="hidden" name="wish_end" value="${event.wish_end}">
	                     	</form>
	                     </td>
	                     <td>
	                     	<form action="${pageContext.request.contextPath}/wish/WishingVote.do" method="post">
		                      	<button type="submit" name="action" value="voteOneEvent"><img src="${pageContext.request.contextPath}/front_end/wish/icons8-vote-64.png" alt=""></button>
		                      	<input type="hidden" name="member_id" value="${memberVO.member_ID}">
		                      	<input type="hidden" name="wish_no" value="${event.wish_no}">
	                     	</form>
	                     </td>
	                 </tr>
				</c:forEach>
            </table>
        </div>
        <div id="notifyBlock">
        	<img src="${pageContext.request.contextPath}/front_end/wish/icons8-message-64.png" >
        	<ul id="notifyUl" style="display: none;">
        		<li>通知</li>
        		<li>登入已逾時，請重新登入</li>
        		<li>登入已逾時，請重新登入</li>
        		<li>登入已逾時，請重新登入</li>
        		<li>登入已逾時，請重新登入</li>
        		<li>登入已逾時，請重新登入</li>
        		<li>登入已逾時，請重新登入</li>
        	</ul>
        </div>
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
	
	<script>
		const notifyUl = document.querySelector('#notifyUl');
		const notifyImg = document.querySelector('#notifyBlock>img');
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
			
			ws.onopen = function(){
				ws.send('giveme${memberVO.member_ID}');
			}
			
			ws.onmessage = function(e){
				if('refresh' == e.data){
					return;
				}
				// 刷新通知介面
				notifyUl.innerHTML = '<li>通知</li>';
				const jsonStrsObj = JSON.parse(e.data);
				let UnreadCount = 0;
				// 逆向取出
				for(let i = jsonStrsObj.length - 1; i >= 0; i--){
					let jsonObj = JSON.parse(jsonStrsObj[i]);
					console.log(jsonObj.message);
					const li = document.createElement('li');
					const spanText = document.createElement('span');
					const br = document.createElement('br');
					const spanTime = document.createElement('span');
					spanText.innerText = jsonObj.message;
					// 算出與現在的時間差
					const minuteDiff = new Date().getTime()/1000/60 - jsonObj.minute;
					if(parseInt(minuteDiff / 60) === 0){
						spanTime.innerText = '---' + parseInt(minuteDiff) + '分前';
					} else if(parseInt(minuteDiff / 60) < 24){
						spanTime.innerText = '---' + parseInt(minuteDiff / 60) + '小時前';
					} else if((parseInt(minuteDiff / 60) >= 24)){
						spanTime.innerText = '---' + parseInt(minuteDiff / 60 / 24) + '天前';
					}
					spanTime.style.color = 'gray';
					li.append(spanText);
					li.append(br);
					li.append(spanTime);
					if(jsonObj.status === 0){
						UnreadCount++;
					}
					notifyUl.append(li);
				}
				if(UnreadCount !== 0){
					notifyImg.className = 'unread';
				}
// 				const jsonObj = JSON.parse(jsonStr);
// 				console.log(jsonObj);
			}
			
			ws.onclose = function(){}
			
			ws.onerr = function(){}
			
			notifyImg.addEventListener('click', function(){
				ws.send('update${memberVO.member_ID}');
				notifyImg.classList.remove('unread');
				if(notifyUl.style.display === "none"){
					notifyUl.style.display = "block";
				}else{
					notifyUl.style.display = "none";
				}
			});
			
		}
		function sendMessage(){}
						
		function disconnect(){ ws.close();}
		
	</script>
</body>
</html>