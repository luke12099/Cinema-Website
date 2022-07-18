<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>許願池明細</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/wish/css/wishDetail.css">
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
        <div id="main">
            <h1>許願池 - ${wishListVOs[0].wishVO.wish_name}</h1>
            <button id="return"><a href="${pageContext.request.contextPath}/back_end/wish/wishPond.jsp">返回</a></button>
            <h2>投票期間: ${wishListVOs[0].wishVO.wish_start} ~ ${wishListVOs[0].wishVO.wish_end}</h2>
            <h2>投票結果</h2>
            <div id="vote">
	            <c:forEach var="wishListVO" items="${wishListVOs}">
					<div>
				    	<h3 <c:if test="${wishListVO.mvVO.mvId == top_one}">style="color: red;"</c:if>>選項: ${wishListVO.mvVO.mvName}</h3>
				        <div class="color" style='height: 20px; width: ${wishListVO.wish_count * 10}px; background-color: rgb(160, 188, 194); <c:if test="${wishListVO.mvVO.mvId == top_one}"> background-color: rgb(71, 104, 112); </c:if>'>
				        	<h5 <c:if test="${wishListVO.mvVO.mvId == top_one}">style="color: white;"</c:if>>票數: ${wishListVO.wish_count}</h5>
				        </div>
				    </div>
			    </c:forEach>
            </div>
            <h2>許願訊息</h2>
            <div id="message">
                <table>
	                <tbody>
	                    <tr>
	                        <th id="memNo">會員編號</th>
	                        <th id="memMsg">訊息</th>
	                    </tr>
	                    <c:forEach var="wishReplyVO" items="${wishReplyVOs}">
	                    	<tr>
	                    		<td>${wishReplyVO.member_id}</td>
	                    		<td>${wishReplyVO.wish_msg}</td>
	                    	</tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
</body>
</html>