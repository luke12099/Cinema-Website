<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.wishing_pond.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>許願池管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/wish/css/wishPond.css">
</head>

<body>
    <header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">     
    	<%@ include file="/back_end/aside_html.jsp"%>   
    </aside>
    <!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
    <% 
		WishingPondService wishSvc = new WishingPondService();
		
		if(request.getAttribute("list") == null){
			request.setAttribute("list", wishSvc.getAll());
			request.setAttribute("listSize", wishSvc.getAll().size());
		}
		
		List<Boolean> updatables = new ArrayList();
		List<WishingPondVO> listVO = (List<WishingPondVO>) request.getAttribute("list");
		for(WishingPondVO vo: listVO){
			updatables.add(wishSvc.getUpdatable(vo.getWish_no()));
		}
		int i = 0;
	%>
<%--     <jsp:useBean id="wishSvc" scope="page" class="com.wishing_pond.model.WishingPondService"/> --%>
    <main>
        <div id="main">
            <h1>許願池管理</h1> ${lastUpdate }
            <button id="newWish"><a href="${pageContext.request.contextPath}/back_end/wish/newWish.jsp">新增</a></button>
    		<span style="color: red;">${errMsg.notFound}</span>
            <div id="multiSearch">
<%--                 <form action="${pageContext.request.contextPath}/wish/WishingPond.do" method="post"> --%>
<%--                 	資料總筆數: ${listSize} 筆 --%>
<!-- 	                <label for="perPage">每頁顯示: </label> -->
<!--                     <select name="" id="perPage"> -->
<!--                         <option value="10">10</option> -->
<!--                         <option value="20">20</option> -->
<!--                     </select> -->
<!--                     <label for="page">頁數: </label> -->
<!--                     <select name="" id="whichPage"> -->
<!--                         <option value="1">1</option> -->
<!--                         <option value="2">2</option> -->
<!--                     </select> -->
<!--                     <button>第一頁</button> -->
<!--                     <button>上一頁</button> -->
<!--                     <button>下一頁</button> -->
<!--                     <button>最末頁</button> -->
<!--                 </form> -->
<!--                     <br> -->
                <form action="${pageContext.request.contextPath}/wish/WishingPond.do" method="post">
                    <label for="searchPeriod">時間: </label>
                    <select name="searchPeriod" id="searchPeriod">
                        <option value="WISH_START">以起始時間搜尋</option>
                        <option value="WISH_END">以結束時間搜尋</option>
                    </select>
                    <input name="start_date" id="start_date" autocomplete="off"> ~ <input name="end_date" id="end_date" autocomplete="off">
                    <label for="searchName">名稱: </label>
                    <input name="searchName" id="searchName">
                    <button type="submit" name="action" value="multiSearch">搜尋</button>
                    <button type="submit" name="action" value="showAll">顯示全部</button>
                </form>
            </div>
            <div id="wishList">
                <table>
                    <tr>
                        <th>編號</th>
                        <th>名稱</th>
                        <th>起始時間</th>
                        <th>結束時間</th>
                        <th>冠軍</th>
                        <th>查看詳情</th>
                        <th>修改活動</th>
                    </tr>
                    <c:forEach var="event" items="${list}">
	                    <tr>
	                        <td>${event.wish_no}</td>
	                        <td>${event.wish_name}</td>
	                        <td>${event.wish_start}</td>
	                        <td>${event.wish_end}</td>
	                        <td>${event.mvVO.mvName == null ? "結果尚未出爐" : event.mvVO.mvName}</td>
	                        <td>
	                        	<form action="${pageContext.request.contextPath}/wish/WishingPond.do" method="post">
		                        	<button type="submit" name="action" value="seeOneEvent"><img src="${pageContext.request.contextPath}/back_end/wish/icons8-detail-64.png" alt=""></button>
		                        	<input type="hidden" name="wish_no" value="${event.wish_no}">
		                        	<input type="hidden" name="top_one" value="${event.top_one}">
	                        	</form>
	                        </td>
	                        <td>
	                        	<form action="${pageContext.request.contextPath}/wish/WishingPond.do" method="post">
	                        		<% 
		                        		if(updatables.get(i++)){
	                        				out.print("<button type='submit' name='action' value='updateEvent'><img src='" + request.getContextPath() + "/back_end/wish/icons8-edit-48.png' alt=''></button>");
		                        		} else{
		                        			out.print("<img src='" + request.getContextPath() + "/back_end/wish/icons8-no-edit-48.png' alt=''>");
		                        		}
	                        		%>
		                        	<input type="hidden" name="wish_no" value="${event.wish_no}">
		                        	<input type="hidden" name="wish_no" value="${event.wish_name}">
		                        	<input type="hidden" name="wish_no" value="${event.wish_start}">
		                        	<input type="hidden" name="wish_no" value="${event.wish_end}">
	                        	</form>
	                        </td>
	                    </tr>
    				</c:forEach>
                </table>
            </div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <%@ include file="/back_end/wish/dateTimePicker.jsp"%>   
</body>
</html>