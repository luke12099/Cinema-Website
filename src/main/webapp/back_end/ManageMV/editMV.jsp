<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.movie.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MovieVO movieVO = (MovieVO)request.getAttribute("movieVO");
	String levelArr[] ={"普遍級","保護級","輔導級(12)","輔導級(15)","限制級"};
	String typeArr[] = {"動作", "劇情","動畫", "冒險", "奇幻" ,"科幻" ,"恐怖" ,"驚悚" ,"文藝","戰爭", "紀錄","喜劇", "懸疑"};
	request.setAttribute("levelArr", levelArr);
	request.setAttribute("typeArr", typeArr);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>編輯電影</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">
    <!-- ****************************************************** -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/ManageMV/editMV.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>

<body>
<script>

</script>
    <header>
       <%@ include file="/back_end/header_html.jsp"%> 
    </header>
    <aside id="aside">  
    	<%@ include file="/back_end/aside_html.jsp"%>  	      
    </aside>
    <!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
    <main>
        <div id="main">
            <div class="form1">
                <form id="" METHOD="post" action="${pageContext.request.contextPath}/MovieServlet.do" enctype="multipart/form-data" >
                    <div class="mvId">
                        <label class="col-sm-2 col-form-label"id="mvId" for="">電影編號:<%=movieVO.getMvId() %></label>
                    </div>
                    <div class="mvName">
                        <label class="col-sm-2 col-form-label" for="mvName">電影中文名稱:</label>
                        <input class="form-control" id="mvName" type="text" name="mvName" value="<%=movieVO.getMvName() %>" required>
                        <label class="col-sm-2 col-form-label" for="mvEname">電影英文名稱:</label>
                        <input class="form-control" id="mvEName" type="text" name="mvEName" value="<%=movieVO.getMvEName()%>" required>
                    </div>
                    <hr>
                    <div class="mvDate">
                        <label class="col-sm-2 col-form-label">上映日:</label>
                        <input class="form-control" id="stDate" type="date" name="mvStDate" value="<%=movieVO.getMvStDate() %>">
                        <label class="col-sm-2 col-form-label">預計下檔日:</label>
                        <input class="form-control" id="edDate" type="date" name="mvEdDate" value="<%=movieVO.getMvEdDate() %>">
                    </div>
                    <hr>
                    <div class="mvCast">
                        <label class="col-sm-2 col-form-label">導演:</label>
                        <input class="form-control" id="mvDrt" type="text" name="mvDrt" value="<%=movieVO.getMvDrt() %>" required>
                        <label class="col-sm-2 col-form-label">演員:</label>
                        <input class="form-control" id="mvCast" type="text" name="mvCast" value="<%=movieVO.getMvCast() %>" required>
                    </div>
                    <hr>
                    <div class="mvLevel">
                        <label class="col-sm-2 col-form-label" for="mvLong">片長(分鐘):</label>
                        <input class="form-control" id="mvLong" type="text" name="mvLong" value="<%=movieVO.getMvLong() %>" required>
                        <label class="col-sm-2 col-form-label">電影分級:</label>
                        <select class="custom-select" id="mvLevel" name="mvLevel" value="${movieVO.mvLevel}">
                        	<c:forEach var="item" items="${levelArr}" varStatus="levelindex">
        						<option value="${levelindex.index}" ${levelindex.index == movieVO.mvLevel ? 'selected="selected"' : ''}>${item}</option>
    						</c:forEach>
                        </select>
                        <label class="col-sm-2 col-form-label">電影類型:</label>
                          <select class="custom-select" name="mvType" id="mvType" value="${movieVO.mvType}">
                          	<c:forEach var="item2" items="${typeArr}" varStatus="typeindex">
        						<option value="${item2}" ${typeindex.index == movieVO.mvLevel ? 'selected="selected"' : ''}>${item2}</option>
    						</c:forEach>
                          </select>
                    </div>
                    <hr>
                    <div class="mvInfo">
                        <label class="col-sm-2 col-form-label">預告片連結:</label>
                        <input class="form-control" id="mvTler" type="text" name="mvTler" value='<%=movieVO.getMvTler()%>'>
                        <label class="col-sm-2 col-form-label">詳細資訊:</label>
                        <textarea class="form-control" id="mvDt" rows="3" name="mvDt" ><%=movieVO.getMvDt() %></textarea>
                    </div>
                    <hr>
                    <div class="mvPc">
                            <label for="exampleFormControlFile1">請選擇電影封面:</label>
                            <input type="file" class="form-control-file" id="mvPc" style="width:200px;" name="mvPicture">
                            <label for="exampleFormControlFile1">預覽:</label>
                            <img id="pvImg" src="">
                            <label for="exampleFormControlFile1">當前封面:</label>
                            <input type="hidden" name="Noreupload" value="${movieVO.mvPicture}">
                            <img src="${pageContext.request.contextPath}${movieVO.mvPicture}" id="oldPc" alt="">
                        </div>
                    <hr>
                    <div class="editBox">
                    	<input type="hidden" name="action" value="update">
                    	<input type="hidden" name="mvId" value="<%=movieVO.getMvId()%>">
                        <button type="button" id="cancelBtn" class="btn btn-danger btn-lg" onclick="history.back()">取消</button>
                        <button type="submit" id="sumbitBtn" class="btn btn-primary btn-lg">送出</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="${pageContext.request.contextPath}/back_end//ManageMV/regexMV.js"></script>
</body>

</html>