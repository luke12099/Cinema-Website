<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tk_inf.model.*"%>
<%@ page import="com.fd_inf.model.*"%>


<%
TkInfService tkInfSvc = new TkInfService();
List<TkInfVO> list = tkInfSvc.getAll();
pageContext.setAttribute("list", list);
%>

<%
FdInfService fdInfSvc = new FdInfService();
List<FdInfVO> list2 = fdInfSvc.getAll();
pageContext.setAttribute("list2", list2);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>選擇票種</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/tk_ord/styles/chooseTK.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</head>

<body>

	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>

	<aside id="aside"></aside>
	<!-- 你們的內容請放在 <main> 標籤內，其他部分勿動! -->
	<main>


		<div class="all">
			<div class="main">

				<div class="showingInf">
					<div class="showmain">
						<div class="showname">${movieVO.mvName}</div>
					</div>
					<div class="showside">
						<div class="showtime"></div>
						<div class="hellname">${hallVO.hlName}</div>
					</div>
				</div>

				<div class="guide1outer">
					<div class="guide1">
						<div class="guide1inner">請選擇電影票</div>
					</div>
				</div>
				<div class="TKouter">
					<table class="TKinner">
						<tr class="TKh">
							<td>票種</td>
							<td>價格</td>
							<td>數量</td>
						</tr>
						<c:forEach var="tkinfVO" items="${list}">

							<c:if test="${(showingVO.SH_TYPE == tkinfVO.tkDI)}">

								<tr class="TKh">
									<td>${tkinfVO.tkType}</td>
									<td>$ ${tkinfVO.tkPrice}</td>
									<td><input class="TK${tkinfVO.tkTypeID}" type="number"
										onkeydown="return false;" min="0" max="5" step="1" value="0" />
									</td>
								</tr>

							</c:if>


						</c:forEach>
					</table>

				</div>
				<div class="guide2outer">
					<div class="guide2">
						<div class="guide2inner">請選擇餐飲</div>
					</div>
				</div>
				<div class="FD">
					<div class="items">

						<c:forEach var="fdinfVO" items="${list2}">

							<c:if test="${fdinfVO.fdState == 1}">

								<div class="item">
									<div class="img_block">
										<img
											src="<%=request.getContextPath()%>/fd_inf/fd_inf.do?action=getPic&fdID=${fdinfVO.fdID}"
											style="width: 100px; height: 120px;">
									</div>
									<div class="iteminner">
										<div class="item_text">${fdinfVO.fdName}</div>
										<div class="item_text2">$ ${fdinfVO.fdprice}</div>
										<div>
											<input class="FD${fdinfVO.fdID}" type="number" min="0"
												onkeydown="return false;" max="5" step="1" value="0" />
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<!-- 				</FORM> -->
				<div class="btBlock">
					<a class="bt"
						style="text-decoration: none;">繼續</a>
				</div>
			</div>
			<div>
				<div class="temporaryInf">

					<div class="temporaryTable">
						<div class="tablehead">
							<font size="5"> 預選資訊</font>
						</div>
						<table class="sidetable">
							<tr>
								<td>名稱</td>
								<td>數量</td>
								<td>價格</td>
							</tr>

							<tr class="TKtable">
								<td></td>
								<td></td>
								<td></td>
							</tr>

						</table>
					</div>

				</div>
			</div>
		</div>


	</main>
	<!-- <div id="tree"></div> -->
	<footer> 嗨邇覓影城 &copy; HIREME CINEMA 2022 </footer>


	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>

	<script>
	
	$().ready(function(){
		sessionStorage.clear();
	})
// 	處理場次時間==================================
	let showtime = '';
	showtime = '${showingVO.SH_TIME}';
	let TotalCount = 0;
	
	$('.showtime').text(showtime.slice(0, 16));

	

let TKorder = []; 
let FDorder = [];


// 點擊單一票種	
<c:forEach var="tkinfVO" items="${list}">					
	

	
	let TK${tkinfVO.tkTypeID} = { 'id' : '',
			 'name' : '', 
			 'unitPrice' : '',
			 'count' : '',
			 'actId' : '',
			 'saleName' : '',
			 'salePrice' : '',
			};
	$(".TK${tkinfVO.tkTypeID}").change((e) => {
// 		覆蓋之前的
	 TK${tkinfVO.tkTypeID}.id=("${tkinfVO.tkTypeID}");
	 TK${tkinfVO.tkTypeID}.name=("${tkinfVO.tkType}");
	 TK${tkinfVO.tkTypeID}.unitPrice=("${tkinfVO.tkPrice}");
	 TK${tkinfVO.tkTypeID}.count=(e.target.value);
	 
	 
	    
		$(".trTK${tkinfVO.tkTypeID}").remove();
	    $(".TKtable").before('<tr  class="trTK${tkinfVO.tkTypeID}"><td>${tkinfVO.tkType}</td><td>x ' + e.target.value + '</td><td>$ ' + (parseInt(e.target.value) * ${tkinfVO.tkPrice}) + '</td></tr>');
	    if (e.target.value === '0') {
	        $(".trTK${tkinfVO.tkTypeID}").remove();
	    }
	    
	    
	});
// 		點擊加入sessionStorage
	$('.bt').click(function () {
		if(TK${tkinfVO.tkTypeID}.count.length === 0 ) {	
			
		}else{
		
		
		
		let url = "${pageContext.request.contextPath}/tkOrd/tkOrd.do?action=findDiscountPrice&tkTypeID=" + TK${tkinfVO.tkTypeID}.id;

		
		$.ajax({
			url: url,
            type: 'post',
            dataType: 'json',
            async: false,
            timeout: 15000,
            success: function (data) {
				

            	
            	let discount = data.act_discount;
            	let minus = data.act_coupon;
            	

            	
            	TK${tkinfVO.tkTypeID}.salePrice = Math.round((TK${tkinfVO.tkTypeID}.unitPrice * discount) + minus);
            	
            	TK${tkinfVO.tkTypeID}.actId = data.act_id;
            	TK${tkinfVO.tkTypeID}.saleName = data.act_title;
		    
            }
		       
			 
		})	
			
		
			
		TKorder.push(TK${tkinfVO.tkTypeID});		
        sessionStorage.setItem('TKorder', JSON.stringify(TKorder));
        TotalCount += TK${tkinfVO.tkTypeID}.count;

        
		}
    });   
</c:forEach>




//點擊單一餐飲	
<c:forEach var="fdinfVO" items="${list2}">				
	
	let FD${fdinfVO.fdID} = { 'id' : '',
			 'name' : '', 
			 'unitPrice' :'',
			 'count' : '' };
	$(".FD${fdinfVO.fdID}").change((e) => {
// 		覆蓋之前的
	 FD${fdinfVO.fdID}.id=("${fdinfVO.fdID}");
	 FD${fdinfVO.fdID}.name=("${fdinfVO.fdName}");
	 FD${fdinfVO.fdID}.unitPrice=("${fdinfVO.fdprice}");
	 FD${fdinfVO.fdID}.count=(e.target.value);
	    
	 	
	    $(".trFD${fdinfVO.fdID}").remove();
	    $(".TKtable").before('<tr  class="trFD${fdinfVO.fdID}"><td>${fdinfVO.fdName}</td><td>x ' + e.target.value + '</td><td>$ ' + (parseInt(e.target.value) * ${fdinfVO.fdprice}) + '</td></tr>');
	    if (e.target.value === '0') {
	        $(".trFD${fdinfVO.fdID}").remove();
	    }
	    
	    
	});
// 		點擊加入sessionStorage
	$('.bt').click(function () {
		if(FD${fdinfVO.fdID}.count.length === 0 ) {	
			
		}else{
        FDorder.push(FD${fdinfVO.fdID});
        sessionStorage.setItem('FDorder', JSON.stringify(FDorder));
		
		}
    });   
</c:forEach>

$('.bt').click(function () {
	
	
	if (TotalCount < 1 ) {
       Swal.fire({
           icon: 'error',
           title: '很抱歉',
           text: '至少需要選購一張電影票',
           footer: '請繼續選擇您的票種與數量'
       })
	}else{	

		document.location.href="<%=request.getContextPath()%>/back_end/tk_ord/chooseSeat.jsp";

	}
});   

	</script>
</body>
</html>