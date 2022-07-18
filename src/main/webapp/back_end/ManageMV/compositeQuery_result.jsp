<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.movie.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listMovie_ByCompositeQuery" scope="request" type="java.util.List<MovieVO>" />
<jsp:useBean id="mvSvc" scope="page" class="com.movie.model.MovieService" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增電影</title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

    <!-- ****************************************************** -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end//ManageMV/creatMV.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
   
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
        <c:if test="${listMovie_ByCompositeQuery.size()==0}">
			<p>查無結果...</p>
		</c:if>
		<c:if test="${listMovie_ByCompositeQuery.size()!=0}">
		
    	<table class="table table-hover table-bordered single-ellipsis">
		<tr>
			<th>電影名稱</th>
			<th>電影英文名稱</th>
			<th>電影分級</th>
			<th>電影類型</th>
			<th></th>
		</tr>
	
		<c:forEach var="movieVO" items="${listMovie_ByCompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${movieVO.mvName}</td>
			<td>${movieVO.mvEName}</td>
			<c:if test="${movieVO.mvLevel==0}">
							<td>普遍級</td>
			</c:if>
			<c:if test="${movieVO.mvLevel==1}">
							<td>保護級</td>
			</c:if>
			<c:if test="${movieVO.mvLevel==2}">
							<td>輔導級(12)</td>
			</c:if>
			<c:if test="${movieVO.mvLevel==3}">
							<td>輔導級(15)</td>
			</c:if>
			<c:if test="${movieVO.mvLevel==4}">
							<td>限制級</td>
			</c:if>
			<td>${movieVO.mvType}</td>
			<td>
				<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/MovieServlet.do">
			     	<input type="submit" value="查看/修改"  class="btn btn-primary">
			     	<input type="hidden" name="mvId"  value="${movieVO.mvId}">
			     	<input type="hidden" name="action"	value="getOne_For_Update">
			     </FORM>
			</td>
		</tr>
	</c:forEach>
	</table>
	</c:if>
<button type="button" onclick="history.back()" class="btn btn-danger btn-lg">返回搜尋頁</button>
        </div>
    </main>
    
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>

</html>