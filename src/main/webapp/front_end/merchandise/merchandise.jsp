<%@page import="com.merchandise_inf.model.MerchVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>HireMe</title>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/css/layout.css"
	type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/merchandise/css/merchandise.css"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/@sweetalert2/theme-default/default.css">

<script src="//cdn.jsdelivr.net/npm/sweetalert2/dist/sweetalert2.min.js"></script>
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
	<div class="wrapper row1" style="height: 60px;">
		<jsp:include page="/front_end/header.jsp" />
	</div>
	
	<main>
<div class="outer">	

	<div class="leftDiv">
	<img id="photo"
			src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=1"
			>
		<div id="product-aside">
			<img
				class="product-img <c:if test="${merchVo.merchPic1==null}">nullimg</c:if>"
				src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=1">
			<img
				class="product-img <c:if test="${merchVo.merchPic2==null}">nullimg</c:if>"
				src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=2">
			<img
				class="product-img <c:if test="${merchVo.merchPic3==null}">nullimg</c:if>"
				src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=3">
			<img
				class="product-img <c:if test="${merchVo.merchPic4==null}">nullimg</c:if>"
				src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=4">
			<img
				class="product-img <c:if test="${merchVo.merchPic5==null}">nullimg</c:if>"
				src="${pageContext.request.contextPath}/merch/controller?action=getPic&merchID=${merchVo.merchID}&pic=5">
		</div>



		
</div>

	<div class="product-title-all">
		<h1>${merchVo.merchName}</h1>
		<div class="product-content">
			<h2>商品詳情</h2>
			${merchVo.merchDT}
		</div>
		<h1 class="hr1"></h1>
		<span id="number-text">數量:</span>
		<form action="${pageContext.request.contextPath}/ShoppingCartServlet">
			<div class="select-area">
				<span class="down" onclick='decreaseCount(event, this)'><img
					class="arrow-pic"
					src="${pageContext.request.contextPath}/front_end/merchandise/images/LeftArrow.png"></span>
				<input id="input-amount" type="number" value="1" name="scCount" min=1>
				<span class="up" onclick='increaseCount(event, this)'><img
					class="arrow-pic"
					src="${pageContext.request.contextPath}/front_end/merchandise/images/RightArrow.png"></span>
			</div>
			<span id="number-text">價格:</span>
			<div class="select-area1">
				<input id="totalCount" type="number" value="${merchVo.merchPrice}"
					name="totalCount" readonly> 元整
			</div>
			<div class="purchase-area">
				<!-- 				<input class="purchase-btn" type="submit" value="前往購買" id="payit" -->
				<!-- 					form="pay">  -->
				<input class="addCartaction" type="hidden" name="action" value="add"> 
				<input	type="hidden" name="merchID" value="${merchVo.merchID}"> <input
					type="hidden" name="memberID" value="${memberVO.member_ID}">
				<input class="addcar-btn addCartButton" type="button" value="加入購物車">
				<input class="purchase-btn payMerch" type="button" value="前往購買">
			</div>
		</form>
		<form action="${pageContext.request.contextPath}/ShoppingCartServlet" id="pay">
			<input class="payMerchaction" type="hidden" name="action" value="payForOneMerch">
			<input class="payMerchscCount" type="hidden" name="scCount" value="1" id="paySCCount">
		</form>
	</div>
</div>
	</main>
	<!--  客服圖 請自行加連結-->
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
let productimgs = document.getElementsByClassName('product-img');
let nullimgs = document.getElementsByClassName('nullimg');
for(productimg of productimgs){
  productimg.addEventListener('click',changes);
}
for(nullimg of nullimgs){
	nullimg.setAttribute('style','display:none;')
}
let totalCount = document.getElementById('totalCount');

let payit = document.getElementById('payit');

let paySCCount = document.getElementById('paySCCount');
let inputamount = document.getElementById('input-amount');

inputamount.addEventListener('change',function(){

	paySCCount.value = inputamount.value;
	totalCount.value = ${merchVo.merchPrice} * inputamount.value;
})

// 圖片
function changes(e) {
  document.getElementById("photo").src = e.target.src;
}
let updateFiles = document.getElementsByClassName('updatefile');
let updateImgs = document.getElementsByClassName('updateimg');
for(let i = 0; i < updateFiles.length; i++){
  updateFiles[i].addEventListener('change', function(e){
    updateImgs[i].src = URL.createObjectURL(e.target.files[0]);
    updateFiles[i].textContent
  })
}

// 產品數量選擇器
function increaseCount(a, b) {
  var input = b.previousElementSibling;
  var value = parseInt(input.value, 10); 
  value = isNaN(value)? 0 : value;
  value ++;
  input.value = value;
  paySCCount.value = inputamount.value;
  totalCount.value = ${merchVo.merchPrice} * value;
  console.log(input.value);
}
function decreaseCount(a, b) {
  var input = b.nextElementSibling;
  var value = parseInt(input.value, 10); 
  if (value > 1) {
    value = isNaN(value)? 0 : value;
    value --;
    input.value = value;
    paySCCount.value = inputamount.value;
    totalCount.value = ${merchVo.merchPrice} * value;
    console.log(input.value);
  }
}

/*商品頁購物*/
 
 		let payMerch = document.getElementsByClassName('payMerch')[0];
 		payMerch.addEventListener('click',function(){
 			if(${memberVO==null?true:false}){
 				<%
 				session.setAttribute("location","http://localhost:8081/CGA102G1/merch/controller?action=getMerchInfo&merchID="+((MerchVO)request.getAttribute("merchVo")).getMerchID().toString());
 				%>
 				window.location.href="${pageContext.request.contextPath}/front_end/login/login.jsp";
 				return;
 			}
        	Swal.fire({
        		  title: '你確定嗎?',
        		  text: "按下確認後即生成訂單!",
        		  icon: 'warning',
        		  showCancelButton: true,
        		  confirmButtonColor: '#3085d6',
        		  cancelButtonColor: '#d33',
        		  confirmButtonText: '是的!我要購買'
        		}).then((result) => {
        		  if (result.isConfirmed) {
        			  let payMerchaction = document.getElementsByClassName('payMerchaction')[0];
        	            let addCartscCount = document.getElementsByClassName('payMerchscCount')[0];
        	            let url = "${pageContext.request.contextPath}/ShoppingCartServlet?"+"action=" + payMerchaction.value + "&merchID=" + ${merchVo.merchID} + "&memberID=${memberVO.member_ID}" + "&scCount=" + addCartscCount.value;
        	            $.ajax({
        	            url: url,
        	            type: 'post',
        	            dataType: 'text',
        	            async: false,
        	            timeout: 15000,
        	            success: function (data) {
        	            	console.log(data)
        	            	Swal.fire(
        	          		      '成功購買!',
        	          		      '您的訂單已生成.',
        	          		      'success'
        	          		    )
        	               }
        	        })
        		    
        		  }
        		})
           
        })
/*加入購物車*/
 
 let addCartButton = document.getElementsByClassName('addCartButton')[0];
 		addCartButton.addEventListener('click',function(){
 		
      			let addCartaction = document.getElementsByClassName('addCartaction')[0];
                let addCartscCount = document.getElementsByClassName('payMerchscCount')[0];
      	            let url = "${pageContext.request.contextPath}/ShoppingCartServlet?action=" + addCartaction.value + "&merchID=" + ${merchVo.merchID} + "&memberID=${memberVO.member_ID}"+"&scCount=" + addCartscCount.value;
      	            $.ajax({
      	            url: url,
      	            type: 'post',
      	            dataType: 'text',
      	            async: false,
      	            timeout: 15000,
      	            success: function (data) {
      	            	Swal.fire({
      	            	  position: 'center',
      	            	  icon: 'success',
      	            	  title: '您的商品已存入購物車',
      	            	  showConfirmButton: false,
      	            	  timer: 1500
      	            	})
      	          		
      	               }
      	        })
      		    
      		  })
        
</script>
</body>

</html>