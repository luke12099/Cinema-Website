<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <!DOCTYPE html> -->
<html lang="en" dir="ltr">

<head>
  <title>HireMe</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/css/layout.css" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
  
 <jsp:include page="/front_end/header.jsp" />
    

 <div>

<!-- 內容放在這裡 -->
<iframe src="${pageContext.request.contextPath}/front_end/merchandise/mall.jsp" frameborder="0" width="100%" height="100%"></iframe>



  </div>
<!--   <!--客服圖 請自行加連結-->
<!--   <img class="cs" src="images/demo/cs.png" height="50px;" width="60px;" href="#"></img> -->

  <!-- Copyright -->
  <div class="wrapper row2">
    <footer id="copyright" class="clear">
      <p class="fl_left">Copyright &copy; 2022 - All Rights Reserved <a href="#"></a></p>
    </footer>
  </div>

</body>

</html>