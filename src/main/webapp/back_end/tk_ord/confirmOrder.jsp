<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>�T�{�q��</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_all.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_main.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/css/emp_footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back_end/tk_ord/styles/confirmOrder.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
	<header>
		<%@ include file="/back_end/header_html.jsp"%>
	</header>

	<aside id="aside"></aside>
	<!-- �A�̪����e�Щ�b <main> ���Ҥ��A��L�����Ű�! -->
	<main>
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
							<div>�нT�{�q�椺�e</div>
						</div>
					</div>
					<div class="TKouter">
						<table class="TKinner">
							<thead>
								<tr>
									<th>�W��</th>
									<th>�ƶq</th>
									<th>����</th>
									<th>�u�f��</th>
									<th>���ʤ��</th>
									<th>�`�p</th>
								</tr>
							<thead>
							<tbody class="orderTable">
							</tbody>
							<tr>
								<th>�`�p</th>
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
							href="<%=request.getContextPath()%>/back_end/tk_ord/creditCard.jsp"
							>�H�Υd�I��</a>
						</div>
						<div class="btBlock">
							<a class="bt" 
							href="<%=request.getContextPath()%>/back_end/tk_ord/completeOrder.jsp"
							>�{���I��</a>
						</div>

					</div>
				</div>
			</div>
		</FORM>
	</main>
	<!-- <div id="tree"></div> -->
	<footer> ����V�v�� &copy; HIREME CINEMA 2022 </footer>


	<aside id="aside">
		<%@ include file="/back_end/aside_html.jsp"%>
	</aside>

	<script>
// 	�B�z�����ɶ�==================================
	
	let showtime = '';
	showtime = '${OrderShowingVO.SH_TIME}';
	
	$('.showtime').text(showtime.slice(0, 16));
	
//   ���X�q��==============================================================
	
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
           
           		tbody.insertAdjacentHTML('beforeend','<tr><td>'+ FD.name+ '</td><td>' + FD.count +'</td><td style="text-decoration:line-through;">$' + FD.unitPrice + '</td><td>$'+FD.unitPrice+'</td><td>���ŦX</td><td>$'+(FD.unitPrice * FD.count)+'</td></tr>');        
	   
    	   		}
       		}
       }else{
    	   
       }
       
              
       let showTotal = document.getElementById('total');
       showTotal.innerText ="$" + total;
              
//        ���X�w��y��==============================================================
		
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
//        �����q��=========================================================================

       $('.bt').click(function() {
    	   order.TKorder= TKorder;
    	   order.seatindex = seatindex;
    	   order.FDorder = FDorder;
    	   order.MemberID = "1";
    	   order.SH_ID = ${OrderShowingVO.SH_ID};	   
    	  
    	   let url = "${pageContext.request.contextPath}/OrderCompleteServlet";
    	   $.ajax({
    	       url: url,
    	       type: 'post',
    	       dataType: 'text',
    	       data: {'action': 'completeOrder','order':JSON.stringify(order)
    	       		},
    	       success: function (data) {
    	           console.log("���\")
    	       }
    	   })
  
       })
	
	</script>
</body>
</html>