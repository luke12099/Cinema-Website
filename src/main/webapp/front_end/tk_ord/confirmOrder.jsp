<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>確認訂單</title>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/tk_ord/styles/confirmOrder.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
<!-- 置頂按鈕 -->
	<button type="button" id="BackTop" class="toTop-arrow"></button>
	<script>
		$(function() {
			$('#BackTop').click(function() {
				$('html,body').animate({
					scrollTop : 0
				}, 333);
			});
			$(window).scroll(function() {
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

	<div style="padding: 30px 30px; background-color: black;">


		<!--各自的內容--------------------->
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/tkOrd/tkOrd.do"
			style="margin-bottom: 0px;">
			<div class="all">
				<div class="main">

					<div class="showingInf">
						<div class="showmain">
							<div class="showname">${OrderMovieVO.mvName}</div>
						</div>
						<div class="showside">
							<div class="showtime"></div>
							<div class="hellname">${OrderHallVO.hlName}</div>
							<div class="seats">
								<div class="allSeat"></div>
							</div>
						</div>
					</div>
					<div class="guide1outer">
						<div class="guide1">
							<div>請確認訂單內容</div>
						</div>
					</div>
					<div class="TKouter">
						<table class="TKinner">
							<thead>
								<tr>
									<th>名稱</th>
									<th>數量</th>
									<th>價格</th>
									<th>優惠價</th>
									<th>活動方案</th>
									<th>總計</th>
								</tr>
							<thead>
							<tbody class="orderTable">
							</tbody>
							<tr>
								<th>總計</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th id="total"></th>
							</tr>
						</table>
					</div>



					<div class="checkout">
						<div class="btBlock">
							<a class="bt"
								href="<%=request.getContextPath()%>/front_end/tk_ord/creditCard.jsp">信用卡付款</a>
						</div>
					</div>
				</div>
			</div>
		</FORM>

	</div>
	<!--   <!--客服圖 請自行加連結-->

	<!--   <img class="cs" src="images/demo/cs.png" height="50px;" width="60px;" href="#"></img> -->

	<!-- Copyright -->
	<div class="wrapper row2">
		<footer id="copyright" class="clear">
			<p class="fl_left">
				Copyright &copy; 2022 - All Rights Reserved <a href="#"></a>
			</p>
		</footer>
	</div>
	<script>
// 	處理場次時間==================================
	
	let showtime = '';
	showtime = '${OrderShowingVO.SH_TIME}';
	
	$('.showtime').text(showtime.slice(0, 16));
	
//   取出訂單==============================================================
	
	 	const tbody = document.querySelector('tbody');
        const TKorderStr = sessionStorage.getItem('TKorder');
        const TKorder = JSON.parse(TKorderStr);
        

       let total = 0;
       for (let TK of TKorder) {
    	   if (TK.count === '' || TK.count === '0') {	
    		   
    	   }else{
           total += (TK.salePrice * TK.count);
           
           tbody.insertAdjacentHTML('beforeend','<tr><td>'+ TK.name+ '</td><td>' + TK.count +'</td><td style="text-decoration:line-through;">$' + TK.unitPrice + '</td><td>$'+ TK.salePrice+'</td><td>'+TK.saleName+'</td><td>$'+(TK.salePrice * TK.count)+'</td></tr>');         
    	   
    	   }
       }
       const FDorderStr = sessionStorage.getItem('FDorder');
       const FDorder = JSON.parse(FDorderStr);
       
       if (FDorder !== null){
    	   
      		for (let FD of FDorder) {
   	   		if (FD.count === '' || FD.count === '0') {	
   		   
   	   		}else{
          		total += (FD.unitPrice * FD.count);
          
          		tbody.insertAdjacentHTML('beforeend','<tr><td>'+ FD.name+ '</td><td>' + FD.count +'</td><td style="text-decoration:line-through;">$' + FD.unitPrice + '</td><td>$'+FD.unitPrice+'</td><td>未符合</td><td>$'+(FD.unitPrice * FD.count)+'</td></tr>');        
	   
   	   		}
      		}
      }else{
   	   
      }
              
       let showTotal = document.getElementById('total');
       showTotal.innerText ="$" + total;
              
//        取出已選座位==============================================================
		
       const seatSelectedStr = sessionStorage.getItem('seatSelected');
       const seatSelected = JSON.parse(seatSelectedStr);

       for (let seat of seatSelected){
    	   $(".allSeat").prepend("| " + seat + " ");
       }
            
       let order = { 'TKorder' : '',
    			'seatindex' : '',
    			'FDorder' : '',
    			'MemberID': '',
    			'SH_ID' : ''
    			}
    		        
       let seatindexStr = sessionStorage.getItem('seatindex');
       let seatindex = JSON.parse(seatindexStr);
//        完成訂單=========================================================================

       $('.bt').click(function() {
    	   order.TKorder= TKorder;
    	   order.seatindex = seatindex;
    	   order.FDorder = FDorder;
    	   order.MemberID = ${memberVO.member_ID};
    	   order.SH_ID = ${OrderShowingVO.SH_ID};	   
    	  
    	   let url = "${pageContext.request.contextPath}/OrderCompleteServlet";
    	   $.ajax({
    	       url: url,
    	       type: 'post',
    	       dataType: 'text',
    	       data: {'action': 'completeOrder','order':JSON.stringify(order)
    	       		},
    	       success: function (data) {
    	           console.log("成功")
    	       }
    	   })
  
       })
	
	</script>
</body>
</html>