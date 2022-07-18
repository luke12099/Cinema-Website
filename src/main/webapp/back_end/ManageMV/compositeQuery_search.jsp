<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.movie.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>搜尋電影</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

    <!-- ****************************************************** -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end//ManageMV/creatMV.css">
   
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
        <div id="main" style="width:100%">
            <div class="form1" style="width:100%">
                <form method="post" action="${pageContext.request.contextPath}/MovieServlet.do" enctype="multipart/form-data" >
                    <div class="mvName">
                            <label class="col-sm-2 col-form-label" for="mvName">電影中文名稱:</label>
                            <input class="form-control" id="mvName" type="text" name="MV_NAME" placeholder="請填入中文名稱" >
                            <label class="col-sm-2 col-form-label" for="mvEname">電影英文名稱:</label>
                            <input class="form-control" id="mvEName" type="text" name="MV_E_NAME"placeholder="請填入英文名稱" >
                        </div>
                    <hr>
                    <div class="mvLevel">
                        <label class="col-sm-2 col-form-label">電影分級:</label>
                        <select class="custom-select" id="mvLevel" name="MV_LEVEL">
                        	<option value="">可選擇分級</option>
                            <option value="0">普遍級</option>
                            <option value="1">保護級</option>
                            <option value="2">輔導級(12)</option>
                            <option value="3">輔導級(15)</option>
                            <option value="4">限制級</option>
                        </select>
                        <label class="col-sm-2 col-form-label">電影類型:</label>
                          <select class="custom-select" id="mvType" name="MV_TYPE">
                            <option value="">可選擇類型</option>
                            <option value="動作">動作</option>
                            <option value="劇情">劇情</option>
                            <option value="動畫">動畫</option>
                            <option value="冒險">冒險</option>
                            <option value="奇幻">奇幻</option>
                            <option value="科幻">科幻</option>
                            <option value="恐怖">恐怖</option>
                            <option value="驚悚">驚悚</option>
                            <option value="文藝">文藝</option>
                            <option value="戰爭">戰爭</option>
                            <option value="紀錄">紀錄</option>
                            <option value="喜劇">喜劇</option>
                            <option value="懸疑">懸疑</option>
                          </select>
                    </div>
                    <hr>
                    <div class="editBox">
                        <input type="hidden" name="action" value="listMovie_ByCompositeQuery">
                        <button type="submit" id="sumbitBtn" class="btn btn-primary btn-lg">搜尋</button>
                        <button type="button" onclick="history.back()" class="btn btn-danger btn-lg">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </main>
    
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="${pageContext.request.contextPath}/back_end//ManageMV/query_regex.js"></script>
</body>

</html>