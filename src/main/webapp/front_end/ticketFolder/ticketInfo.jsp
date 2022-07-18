<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.model.*" %>
<%@ page import="com.tk_ord.model.*" %>
<%@ page import="com.changeSeat.*" %>
<%@ page import="com.hall.model.*" %>
<%@ page import="com.tk_ord_dt.model.*" %>
<%@ page import="com.changeSeat.*" %>
<%@ page import="com.fd_ord_dt.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	Integer member_ID= (Integer)request.getAttribute("member_ID");
	Long tkOrdID = (Long)request.getAttribute("tkOrdID");
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	List<TkOrdDtVO> ordDtList = (List<TkOrdDtVO>)map.get("ordDtList");
	List<String> tkNameList = (List<String>)map.get("tkNameList");
	List<String> actTitleList = (List<String>)map.get("actTitleList");
	List<String> seatNumberList = (List<String>)map.get("seatNumberList");
	ShowSeatVO showSeatVO = (ShowSeatVO)map.get("showSeatVO");
	MovieVO movieVO = (MovieVO)map.get("movieVO");
	HallVO hallVO = (HallVO)map.get("hallVO");
	List<FdOrdDtVO>foodOrdList = (List<FdOrdDtVO>)map.get("foodOrdList");
	List<String>foodNameList = (List<String>)map.get("foodNameList");
	
	request.setAttribute("ordDtList", ordDtList);
	request.setAttribute("tkNameList", tkNameList);
	request.setAttribute("actTitleList", actTitleList);
	request.setAttribute("seatNumberList", seatNumberList);
	request.setAttribute("showSeatVO", showSeatVO);
	request.setAttribute("movieVO", movieVO);
	request.setAttribute("hallVO", hallVO);
	request.setAttribute("foodOrdList", foodOrdList);
	request.setAttribute("foodNameList", foodNameList);
%>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
	<title>查看訂單詳情</title>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<style type="text/css">
		body.modal-open {
		overflow: auto !important;
	    padding: 0px!important;
	}
	</style>
</head>


<body>
  <!-- 置頂按鈕 -->
  <button type="button" id="BackTop" class="toTop-arrow"></button>
  <script>
    $(function () {
      $('#BackTop').click(function () {
        $('html,body').animate({ scrollTop: 0 }, 333);
      });
      $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
          $('#BackTop').fadeIn(222);
        } else {
          $('#BackTop').stop().fadeOut(222);
        }
      }).scroll();
    });
  </script>

<header>

<%@ include file="/front_end/header.jsp"%>
<%@ include file="/front_end/header_css.jsp"%>

</header>
 
 
  <div class="ord_info" style="padding:10px">
    <div class="ord_detail" style="display: flex">
		<div class="card">
		  <div class="card-body" style="font-weight: bolder;">
		    電影名稱: ${movieVO.mvName}
		  </div>
		</div>
		<div class="card" style="margin-left:10px;font-weight: bolder ">
		  <div class="card-body">
		    上映廳院: ${hallVO.hlName}
		  </div>
		</div>
		<div class="card" style="margin-left:10px;font-weight: bolder ">
		  <div class="card-body">
		    場次時間: 
		    <fmt:formatDate pattern="yyyy年MM月dd日 H點m分" value="${showSeatVO.SH_TIME}"/>
		  </div>
		</div>
    </div>
    <div class="ord_content" style="display: flex">
      <div class="ticket_container" style="width: 70%;margin-top: 10px">
        <div class="ticket_table">
        	<div class="card">
			  <div class="card-header" style="font-weight: bold;">
			    票卷資訊
			  </div>
			  <div class="card-body">
			  	
			  	<input type="hidden" id="tkOrdID" value="${tkOrdID}">
			  	
				<table id="tableAjax" class="table  table-hover" style="width:100%">
            	 <thead>
                        <tr>
                            <th>票種</th>
                            <th>活動方案</th>
                            <th>座位號碼</th>
                            <th>售價</th>
                            <th>座位狀態</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ordDtList}" var="ordDt" varStatus="loop">
                    	<tr>
                    		<td>${tkNameList[loop.index]}</td>
                    		<td>${actTitleList[loop.index]}</td>
                    		<td>${seatNumberList[loop.index]}</td>
                    		<td>${ordDt.sellPrice}</td>
                    		<td>
                    		<c:if test="${ordDt.state==0}">
                    		已付款
                    		</c:if>
                    		<c:if test="${ordDt.state==1}">
                    		已入場
                    		</c:if>
                    		<c:if test="${ordDt.state==2}">
                    		已退票
                    		</c:if>
                    		</td>
                    		<td>
                    		<c:if test="${ordDt.state==0}">
                    		<button class="btn btn-dark" id="${ordDt.tkDtID}" onclick="refund(this)">退票</button>
                    		</c:if>
                    		<input type="hidden" id="seatIndex${ordDt.tkDtID}" value="${ordDt.seat}">
                    		</td>
                    	</tr>
                    </c:forEach>
                    </tbody>
            	</table>
            	 <div class="ticket_btn">
            		※請入場時出示畫面給現場人員驗票
            		<br>
            		※購買學生票和敬老票請於入場時出示相關證件
          			<button type="button" id="tkChecking" onclick="tkChecking()" class="btn btn-success btn-lg btn-block" style="width:30%">驗票</button>
        		 </div>	
        		 <c:set var="exitId" value="0" />
			  </div>
			</div>
        	 
        </div>
       
      </div>
      <div class="food_container" style="width: 30%;margin: 10px 0px 0px 5px">
        <div class="food_table">
        	<div class="card">
			  <div class="card-header" style="font-weight: bold;">
			    餐飲資訊
			  </div>
			  <div class="card-body">
				<table id="tableAjax" class="table  table-hover" style="width:100%">
            	 <thead>
                        <tr>
                            <th>餐飲名稱</th>
                            <th>餐飲數量</th>
                            <th>售價</th>
                            <th>取餐狀態</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <c:forEach items="${foodOrdList}" var="foodOrdDt" varStatus="loop">
                     
                    	<tr>
                            <td>${foodNameList[loop.index]}</td>
                            <td>${foodOrdDt.fdCount}</td>
                            <td>${foodOrdDt.sellPrice}</td>
                            <td>
                            <c:if test="${foodOrdDt.fdState==0}">
                    		已付款
                    		</c:if>
                    		<c:if test="${foodOrdDt.fdState==1}">
                    		已取餐
                    		</c:if>
                    		<c:if test="${foodOrdDt.fdState==2}">
                    		已退餐
                    		</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                   
                    </tbody>
            	</table>
            	<c:if test="${foodOrdList.size()==0}">
                    	<div style="color:red">
                    	您本次沒有訂餐。
                    	<br>
                    	</div>
                </c:if>
                <c:if test="${foodOrdList.size()>0}">
            	※請在領餐櫃檯出示畫面
            	 <div class="food_btn" style="display:flex;justify-content: space-between;">
            	 	  <input type="hidden" id="tkOrdID" value="${tkOrdID}">
            	 	  
            	 	  <c:forEach items="${foodOrdList}" var="foodOrdDt" varStatus="loop" begin="0" end="0">
            	 	  
            	 	  <c:if test="${foodOrdDt.fdState==0}">
			          
			          <button type="button" id="takeMeal" onclick="takeMeal()" class="btn btn-success btn-lg btn-block" style="width:30%">兌換餐點</button>
			          <button type="button" id="cancelMeal" onclick="cancelMeal()" class="btn btn-danger" id="">退餐</button>
        		 	 
        		 	  </c:if>
        		 	 
        		 	  </c:forEach>
        		 	  
        		 </div>
        		 </c:if>
        		 
			  </div>
			</div>
        </div>
       
      </div>
    </div>
    <div class="btnBox" style="margin-top: 10px">
      <button type="button" class="btn btn-danger" onclick="history.back()">返回查看訂單</button>
    </div>
  </div>

  </div>
  <!-- Copyright -->
  <div class="wrapper row2">
    <footer id="copyright" class="clear">
      <p class="fl_left">Copyright &copy; 2022 - All Rights Reserved <a href="#"></a></p>
    </footer>
  </div>
  
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">選取要入場的座位</h5>
	      </div>
	      <div class="modal-body" id="modalBody">
	      	<table id="tableAjax" class="table  table-hover" style="width:100%">
            	 <thead>
                        <tr>
                            <th>票種</th>
                            <th>座位號碼</th>
                            <th>座位狀態</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ordDtList}" var="ordDt" varStatus="loop">
                    	<tr>
                    		<td>${tkNameList[loop.index]}</td>
                    		<td>${seatNumberList[loop.index]}</td>
                    		<td>
                    		<c:if test="${ordDt.state==0}">
                    		已付款
                    		</c:if>
                    		<c:if test="${ordDt.state==1}">
                    		已入場
                    		</c:if>
                    		<c:if test="${ordDt.state==2}">
                    		已退票
                    		</c:if>
                    		</td>
                    		<td>
                    		<c:if test="${ordDt.state==0}">
                    		<button class="btn btn-success" id="getin${ordDt.tkDtID}" onclick="admission(this)">入場</button>
                    		</c:if>
                    		<input type="hidden" id="tkDtId_getin${ordDt.tkDtID}" value="${ordDt.tkDtID}">
                    		</td>
                    	</tr>
                    </c:forEach>
                    </tbody>
            	</table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">返回</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</body>
<script src="${pageContext.request.contextPath}/front_end/ticketFolder/ticketInfo.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
</html>