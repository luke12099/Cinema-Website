<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //存入req的memberVO物件
%>

<html lang="en" dir="ltr">

<head>
<meta charset="UTF-8">
<!---<title> Responsive Registration Form | CodingLab </title>--->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/register/css/register.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
	



<script type="text/javascript">
	function checkRegister() {
		var memberName = $("#memberName").val();
		var memberEmail = $("#memberEmail").val();
		var memberPhone = $("#memberPhone").val();
		var memberPassword = $("#memberPassword").val();
		var memberAddress = $("#memberAddress").val();
		var memberPassword2 = $("#memberPassword2").val();
		var str = '';
		if (memberName == "") {
			str += "尚未填寫會員名稱,";
		}
		if (memberPhone == "") {
			str += "尚未填寫電話,";
		}
		if (memberAddress == "") {
			str += "尚未填寫地址,";
		}
		if (memberPassword != memberPassword2) {
			str += "兩次輸入的密碼不一致！";
		}

		if (str !== "")
			alert(str);
	}
</script>


</head>


<body>
	<div class="container" name="register" id="register">
		<div class="title">註冊</div>
		<div class="content">
			<form method="post"
				ACTION="${pageContext.request.contextPath}/member.do"
				enctype="multipart/form-data">
				<div class="user-details">
					<div class="input-box">
						<div style="display: flex">
							<span class="username">會員名稱</span>
							<div style="color: red">${errorMsgs.member_Name}</div>
						</div>
						<input type="text" placeholder="請輸入您的使用者名稱" id="member_Name"
							name="member_Name"  value="<%=(memberVO == null) ? "" : memberVO.getMember_Name()%>">

					</div>

					<div class="input-box">
						<div style="display: flex">
							<span class="errMsgs">電子信箱</span>
							<div style="color: red">${errorMsgs.member_Email}</div>
						</div>
						<input type="text" placeholder="請輸入您的電子信箱" id="member_Email"
							name="member_Email" value="<%=(memberVO == null) ? "" : memberVO.getMember_Email()%>">
					</div>

					<div class="input-box">
						<div style="display: flex">
							<span class=>會員密碼</span>
							<div class="errMsgs" style="color: red">${errorMsgs.member_Password}</div>
						</div>
						<input type="password" placeholder="請輸入您的密碼" id="member_Password"
							name="member_Password">
					</div>

					<div class="input-box">
						<div style="display: flex">
							<span class="details">再次確認密碼</span>
							<div style="color: red">${errorMsgs.member_Password}</div>
						</div>
						<input type="password" placeholder="再次確認密碼" id="member_Password2"
							name="member_Password2">
					</div>


				
					<div class="input-box">
						<div style="display: flex; margin-top: 20px">
							<span>會員電話</span>
							<div class="errMsgs" style="color: red">${errorMsgs.member_Phone}</div>
						</div>
						<input type="text" placeholder="請輸入您的會員電話" id="member_Phone"
							name="member_Phone" style="width: 300px" value="<%=(memberVO == null) ? "" : memberVO.getMember_Phone()%>">
					</div>


					<div class="input-box">
						<span class="address">會員地址</span>
						<div class="errMsgs" style="color: red">${errorMsgs.member_Address}</div>
						<div id="twzipcode" name="address"></div>
						<div style="display: flex">
							<input type="text" placeholder="請輸入您的會員地址" id="member_Address1"
								name="member_Address1">
								<input type="hidden" name="member_Address" id="member_Address" value="<%=(memberVO == null) ? "" : memberVO.getMember_Address()%>">
						</div>



						<div class="input">
							<span class="details"></span>
							<div class="errMsgs"></div>
						</div>
					</div>

					<div class="input">
						<span class="details">會員照片</span>
						<div class="errMsgs" style="color: red">${errorMsgs.myUpfile}</div>
						<div class="errMsgs"></div>
						<input type="file" id="file-uploader" name="myUpfile">
					</div>
				</div>

				<div class="button">
					<input type="submit" id="" value="註冊" onclick="checkRegister();">
					<input type="hidden" name="action" value="insert">
				</div>
			</form>
		</div>
	</div>

	<!------------------------------------ 地址下拉式選單設定 ----------------------------->
	<script>
		$("#twzipcode").twzipcode({
			zipcodeIntoDistrict : true, // 郵遞區號自動顯示在區別選單中
			countySel : "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
			districtSel : "大安區", // 地區預設值
			css : [ "cityControl", "townControl" ], // 自訂 "城市"、"地別" class 名稱 
			countyName : "city", // 自訂城市 select 標籤的 name 值
			districtName : "town" // 自訂區別 select 標籤的 name 值
		});
		
		$("input").change(function(){
		$("#member_Address").val($(".cityControl").val() + $(".townControl").val() + $("#member_Address1").val());
		})
		$("select").change(function(){
		$("#member_Address").val($(".cityControl").val() + $(".townControl").val() + $("#member_Address1").val());
		})
		
	</script>
	
	
	
	




</body>
</html>