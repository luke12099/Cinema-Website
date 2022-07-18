<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*" %>
<%@ page import="com.member.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String changeCM_STATE[]={"正常顯示","暴雷隱藏","刪除隱藏"};
	request.setAttribute("changeCM_STATE",changeCM_STATE);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理檢舉</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_all.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/css/emp_footer.css">

    <!-- ****************************************************** -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back_end/ManageReport/editReport.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

</head>

<body>
     <header>
        <%@ include file="/back_end/header_html.jsp"%>   
    </header>
    <aside id="aside">
    	<%@ include file="/back_end/aside_html.jsp"%>         
    </aside>
    <main>
        <div id="main" class="mainBox">
       	<label class="col-sm-2 col-form-label" name="rpId" for="">檢舉單號:${reportVO.rpId}</label>
        <form METHOD="post" action="${pageContext.request.contextPath}/ReportServlet.do">
        <div class="custom-control custom-switch" style="margin-left: 10px">
				<input type="checkbox" class="custom-control-input" id="updateSameRP" name="updateSameRP"> 
				<label class="custom-control-label" for="updateSameRP">一次處理同則評論</label>
		</div>
        <hr size="8px" align="center" width="100%">
            <div class="statusBox">
                <div class="alert alert-warning" style="margin:5px ">檢舉原因:${reportVO.rpType}</div>
                <c:if test="${cmtVO.CM_STATE==0}">
                <div class="alert alert-warning" style="margin:5px ">文章狀態:正常顯示</div>
                </c:if>
                <c:if test="${cmtVO.CM_STATE==1}">
                <div class="alert alert-warning" style="margin:5px ">文章狀態:暴雷隱藏</div>
                </c:if>
                <c:if test="${cmtVO.CM_STATE==2}">
                <div class="alert alert-warning" style="margin:5px ">文章狀態:刪除隱藏</div>
                </c:if>
                <div class="selectBox">
                    <label for="" >選擇處理方式:</label>
                    <select class="custom-select" name="changeCmState" id="selectState"> 
                    <c:forEach var="item" items="${changeCM_STATE}" varStatus="index">
        						<option value="${index.index}" ${index.index == cmtVO.CM_STATE ? 'selected="selected"' : ''}>${item}</option>
    				</c:forEach>
                    </select>
					</div>
            </div>
            <hr size="8px" align="center" width="100%">
            <div class="badBox">
                <div class="pic">
                    <img src="${pageContext.request.contextPath}${cmtMemberVO.member_Pic}" class="rounded-circle" alt="">
                    <p>評論會員:${cmtMemberVO.member_Name}</p>
                </div>
                <div class="commInfo">
                    <div class="speech-bubble" style="word-break: break-all" >
                        <h5 id="commText">
                           ${cmtVO.CM_TEXT}
                        </h5>
                    </div>
                </div>
                <hr size="8px" align="center" width="100%">
                
                <div class="commInfo">
                    
                    <div class="speech-bubble2"style="word-break: break-all" >
                        <h5 id="rpText">
                        <c:if test="${reportVO.rpText==null}">
                           這位檢舉者沒有留下任何檢舉內容...
                        </c:if>   
                        <c:if test="${reportVO.rpText!=null}">
                           ${reportVO.rpText}
                        </c:if>   
                        </h5>
                    </div>
                </div>
                <div class="pic">
                    <img src="${pageContext.request.contextPath}${rpMemberVO.member_Pic}" class="rounded-circle" alt="">
                    <p>檢舉者:${rpMemberVO.member_Name}</p>
                </div>
            </div>
            <div class="editBox">
            	<input type="hidden" name="rpId" value="${reportVO.rpId}">
            	<input type="hidden" name="cmId" value="${reportVO.cmId}">
            	<input type="hidden" name="action" value="update">
                <button type="button" onclick="history.back()" class="btn btn-secondary btn-lg">取消</button>
                <button type="submit" class="btn btn-primary btn-lg">送出</button>
            </div>
            </form>
        </div>
    </main>
    <!-- <div id="tree"></div> -->
    <footer>
        嗨邇覓影城 &copy; HIREME CINEMA 2022
    </footer>
</body>

</html>