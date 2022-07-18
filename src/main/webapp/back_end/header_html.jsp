<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<nav>
    <div id="logo">
        <img src="${pageContext.request.contextPath}/back_end/logo2noline.jpg">
    </div>
    <h2>員工後台操作系統</h2>
    <ul>
    	<li>${empAccount.emp_name == null ? "未登入" : empAccount.emp_name}</li>
        <li><a href="${pageContext.request.contextPath}/emp/EmpLogout">登出</a></li>
    </ul>
</nav>