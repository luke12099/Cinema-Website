<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增許願活動</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/wish/css/newWish.css">
</head>

<body>
    <header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">     
    	<%@ include file="/back_end/aside_html.jsp"%>   
    </aside>
    <!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
    <jsp:useBean id="wishSvc" class="com.wishing_pond.model.WishingPondService"/>
    <main>
        <div id="main">
            <h1>新增許願活動</h1>
            <form action="${pageContext.request.contextPath}/wish/WishingPond.do" method="post">
                <button type="submit" name="action" value="addWish" id="addWish">新增活動</button>
                <button type="button" id="return"><a href="${pageContext.request.contextPath}/back_end/wish/wishPond.jsp">返回</a></button>
            	<span style="color: red;">${errMsg.checkMovie}</span>
                <h2>活動資訊</h2>
                <div id="event">
                    <label for="eventNo">活動編號: </label>
                    <input type="text" id="eventNo" value="${wishSvc.nextId}" disabled>
                    <br>
                    <label for="eventName">活動名稱: </label>
                    <input type="text" id="eventName" name="wish_name" value="${wishVO.wish_name}" placeholder='${errMsg.wish_name == null ? "字數限制不超過5" : errMsg.wish_name}' maxlength="5">
                    <br>
                    <label for="startDate">活動日期: </label>
                    <input name="start_date" id="start_date" autocomplete="off" onkeydown="return false" value="${wishVO.wish_start}" placeholder="${errMsg.start_date}"> ~ <input name="end_date" id="end_date" autocomplete="off" onkeydown="return false" value="${wishVO.wish_end}" placeholder="${errMsg.end_date}">
                </div>
                <h2>選擇電影(請選擇2~6個)</h2>
<!--                 <div id="multiSearch"> -->
<!--                     <label for="level">分級:</label> -->
<!--                     <select name="level" id="level"> -->
<!--                         <option value="0">請選擇</option> -->
<!--                         <option value="1">普遍級</option> -->
<!--                         <option value="2">保護級</option> -->
<!--                         <option value="3">輔導級</option> -->
<!--                         <option value="4">限制級</option> -->
<!--                     </select> -->
<!--                     <label for="type">類型:</label> -->
<!--                     <select name="type" id="type"> -->
<!--                         <option value="0">請選擇</option> -->
<!--                         <option value="動畫">動畫</option> -->
<!--                         <option value="奇幻">奇幻</option> -->
<!--                         <option value="動作">動作</option> -->
<!--                         <option value="科幻">科幻</option> -->
<!--                         <option value="劇情">劇情</option> -->
<!--                     </select> -->
<!--                     <label for="keyword">關鍵字:</label> -->
<!--                     <input id="keyword"> -->
<!-- <!--                     <button id="select">篩選</button> --> 
<!--                     <button id="selectAll">顯示全部</button> -->
<!--                 </div> -->
                <div id="movies">
                	<jsp:useBean id="movieSvc" class="com.movie.model.MovieService"/>
                	<c:forEach var="movieVO" items="${movieSvc.all}">
	                    <div class="movie" title="${movieVO.mvName}" >
	                        <input type="checkbox" name="checkMovie" id="movie${movieVO.mvId}" value="${movieVO.mvId}" onclick="colorChange(this)">
	                        <label for="movie${movieVO.mvId}">
	                            <div class="inner">
	                                <h3>${movieVO.mvName}</h3>
	                                <img src="${pageContext.request.contextPath}${movieVO.mvPicture}" alt="">
	                                <br> 類型: ${movieVO.mvType}
	                                <br> 分級: <c:if test="${movieVO.mvLevel == 0}">普遍級</c:if> 
					                      	  <c:if test="${movieVO.mvLevel == 1}">保護級</c:if> 
					                      	  <c:if test="${movieVO.mvLevel == 2}">輔導級(12)</c:if> 
					                      	  <c:if test="${movieVO.mvLevel == 3}">輔導級(15)</c:if> 
					                      	  <c:if test="${movieVO.mvLevel == 4}">限制級</c:if> 
	                            </div>
	                        </label>
	                    </div> 
                    </c:forEach>
                </div>
            </form>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="${pageContext.request.contextPath}/back_end/wish/js/newWish.js"></script>
    <%@ include file="/back_end/wish/dateTimePickerBlock.jsp"%>   
</body>
</html>