<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!--jQuery-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/merchandise/css/detail.css">
</head>
<body>
<form action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do" id="form1">
    <input type="hidden" name="action" value="getMember_Order_For_Display">
</form>
    <div class="mainDiv">
        <div class="upDiv">
            <table>
                <thead>
                    <tr>
                        <th colspan="5">你的所有訂單</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <th>訂單編號</th>
                        <th>訂單日期</th>
                        <th>訂單總價</th>
                        <th>訂單狀態</th>
                        <th>查詢</th>
                    </tr>
            <jsp:useBean id="merchOrdSvc" class="com.merchandise_order.model.MerchOrdService"/>
                    <c:forEach var="merchOrdVO" items="${merchOrdSvc.getAll(memberVO.member_ID)}">
                    <c:if test="${merchOrdVO.merchOrdStatus!=3}">
                    <tr>
                        <td>${merchOrdVO.merchOrdID}</td>
                        <td><fmt:formatDate value="${merchOrdVO.merchOrdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>$${merchOrdVO.merchOrdCount}</td>
                        <td>
							${merchOrdVO.merchOrdStatus==0?'備貨中':''}
							${merchOrdVO.merchOrdStatus==1?'待取貨':''}
                        	${merchOrdVO.merchOrdStatus==2?'已完成':''}
                        	${merchOrdVO.merchOrdStatus==3?'已取消':''}
                        </td>
                        <td><button type="submit" class="select" form="form1" name="merchOrdID" value="${merchOrdVO.merchOrdID}">查看訂單</button></td>
                    </tr>
                    </c:if>
					</c:forEach>

                </tbody>

            </table>
        </div>

<hr>

        <div class="btmDiv">

            <div class="detailTitle">
                訂單明細
            </div>

            <div id="cartBody">
            <c:if test="${orderDetailList!=''}">
             		<c:forEach var="orderDetailVo" items="${orderDetailList}">
             		<div class="eachItem">
             		<div class="check">${orderDetailVo.item}</div>
                    <div class="vl"></div>
                    <div class="pic"><img src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${orderDetailVo.merchID}&pic=1" alt=""></div>
                    <div class="vl"></div>
                    <div class="name">${orderDetailVo.merchVO.merchName}</div>
                    <div class="vl"></div>
                    <div class="info">${orderDetailVo.merchVO.merchDT}</div>
                    <div class="vl"></div>
                    <div class="price">${orderDetailVo.ordPrice}</div>
                    <div class="vl"></div>
                    <div class="count">
                        ${orderDetailVo.ordStatus == 0 ? '備貨中' : ''}
                        ${orderDetailVo.ordStatus == 1 ? '可取貨' : ''}
                        ${orderDetailVo.ordStatus == 2 ? '已完成' : ''}
                        ${orderDetailVo.ordStatus == 3 ? '已取消' : ''} 
                        </div>
                        </div>
             		</c:forEach>
             	</c:if>	
                </div>
			
                
            </div>

        </div>


    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
</body>
</html>