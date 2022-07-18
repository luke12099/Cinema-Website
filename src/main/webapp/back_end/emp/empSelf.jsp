<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>個人資料維護</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/emp/css/empDetail.css">
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
    	// 登入才能用，未登入會 500
    	 Integer empNo = ((com.emp_account.model.EmpAccountVO)session.getAttribute("empAccount")).getEmp_no();
    	if(request.getAttribute("empVO") == null){
    		request.setAttribute("empVO", new com.emp_account.model.EmpAccountService().getOneEmp(empNo));
    	}
   	%>
    <main>
        <div id="main">
            <h1>個人資料維護</h1><span style="color: red;">${isOk}</span>
            <div id="emp_detail">
            	<form action="${pageContext.request.contextPath}/emp/EmpAccount.do" method="post" enctype="multipart/form-data">
	                <div id="left">
	                    <div id="emp_img">
	                        <img src="${pageContext.request.contextPath}/emp/ShowBlob?emp_no=${empVO.emp_no}" alt="" id="emp_photo">
	                    </div>
	                    <br>
	                    <label for="emp_id">員工編號: </label>
	                    <input id="emp_id" value="${empVO.emp_no}" disabled>
	                    <input type="hidden" value="${empVO.emp_no}" name="emp_no">
	                    <br>
	                    <label for="emp_name">員工姓名: </label>
	                    <input id="emp_name" value="${empVO.emp_name}" disabled>
	                    <input type="hidden" value="${empVO.emp_name}" name="emp_name" >
	                </div>
	                <div id="right">
	                    <label for="emp_email">電子郵箱: </label>
	                    <input id="emp_email" value="${empVO.emp_email}" name="emp_email" placeholder="${errMsg.emp_email}">
	                    <hr>
	                    <label for="emp_phone">連絡電話: </label>
	                    <input id="emp_phone" value="${empVO.emp_phone}" name="emp_phone" placeholder="${errMsg.emp_phone}">
	                    <hr>
	                    <label for="emp_address">聯絡地址: </label>
	                    <input id="emp_address" value="${empVO.emp_address}" name="emp_address" placeholder="${errMsg.emp_address}">
	                    <hr>
	                    <label for="emp_photo">上傳照片: </label>
	                    <input id="emp_photo" type="file" name="emp_photo">
	                    <hr>
	                    <input type="hidden" value="${empVO.emp_password}" name="emp_password">
	                    <input type="hidden" value="${empVO.emp_status}" name="emp_status" >
	                    <div id="btn_block">
	                        <button type="submit" name="action" value="updateSelf">修改</button>
	                    </div>
                	</div>
	        	</form>
            </div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="${pageContext.request.contextPath}/back_end/emp/js/empDetail.js"></script>
</body>

</html>