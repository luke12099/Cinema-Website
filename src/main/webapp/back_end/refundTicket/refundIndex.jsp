<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>票務訂單管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <!-- -------------------------------------------------------- -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        	<div class="searchbox" style="width:20%; padding:5px">
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="searchInput"
						placeholder="請輸入電影票訂單編號"
						aria-label="Recipient's username" aria-describedby="searchBtn">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button" id="searchBtn">搜尋</button>
					</div>
				</div>
			</div>
        	<div class="tablebox">
        	  <table id="tableAjax" class="table table-hover table-bordered single-ellipsis" style="width:100%">
            	 <thead>
                        <tr>
                            <th>電影票訂單編號</th>
                            <th>電影明細編號</th>
                            <th>票種</th>
                            <th>活動方案</th>
                            <th>座位號碼</th>
                            <th>售價</th>
                            <th>座位狀態</th>
                            <th>功能區</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
            </table>
        	</div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="${pageContext.request.contextPath}/back_end/refundTicket/refundIndex.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="${pageContext.request.contextPath}/back_end/refundTicket/socketScript.js"></script>
</body>

</html>