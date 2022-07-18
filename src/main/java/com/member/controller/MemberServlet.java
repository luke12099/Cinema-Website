package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;

import com.member.model.*;
import com.sc_detail.model.SCDetailService;
import com.sc_detail.model.SCDetailVO;

import MemberSendEmail.MailService;

@MultipartConfig()
@WebServlet("/member.do")

public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setHeader("Content-Type", "text/html;charset=UTF-8"); // 靠19行通知瀏覽器
		PrintWriter out = res.getWriter();

// 單一查詢，差圖片查詢
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID"));

			/*************************** 2.開始查詢資料 *****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMem(member_ID);

			if (memberVO == null) {
				errorMsgs.put("member_ID", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/member/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
//					System.out.print(action); // 測試印出是否有抓到參數值 印出 getOne_For_Display

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
			successView.forward(req, res);
			return;
		}
//單一查詢更新
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求 單一資料修改
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID"));
			/*************************** 2.開始查詢資料 ****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMem(member_ID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/member/update_member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
			return;
		}

//更新會員資料		
		if ("update".equals(action)) { // 來自membermod.jsp的請求 成功修改完成頁面
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
/*---------------- 更新會員資料時會員資料跳出修改成功 ---------------------------*/			
			Map<String, String> situation = new LinkedHashMap<String, String>();
			req.setAttribute("situation", situation);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID").trim());

			String member_Password = req.getParameter("member_Password");
			String member_PasswordReg = "^(?![a-zA-Z]+$)(?![0-9]+$)[0-9A-Za-z]{2,8}$"; // 至少八個字符，至少一個字母和一個數字：
			if (member_Password == null || member_Password.trim().length() == 0) {
				errorMsgs.add("會員密碼: 請勿空白");
			} else if (!member_Password.trim().matches(member_PasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員密碼: 至少八個字符，至少一個字母和一個數字, 且長度必需2到8之間");
			}

			String member_Name = req.getParameter("member_Name");
			if (member_Name == null || member_Name.trim().length() == 0) {
				errorMsgs.add("會員名稱請勿空白");
			}

			String member_Phone = req.getParameter("member_Phone");
			String member_PhoneReg = "09[0-9]{8}"; // 字串裡面必須全是 0~9 的數字，字串長度必須是 10
			if (member_Phone == null || member_Phone.trim().length() == 0) {
				errorMsgs.add("會員電話: 請勿空白");
			}

			else if (!member_Phone.trim().matches(member_PhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				System.out.println(member_Phone.trim().matches(member_PhoneReg));
				errorMsgs.add("會員電話: 字串裡面必須全是 0~9 的數字，字串長度必須是 10");
			}

			String member_Address = req.getParameter("member_Address");
			if (member_Address == null || member_Phone.trim().length() == 0) {
				errorMsgs.add("會員地址: 請勿空白");
			}

			String photoName = null;
			Part photo = req.getPart("myUpfile");

			if (photo.getSize() != 0) { // 如果照片不為空
				String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片

				// 利用File物件,寫入目地目錄,上傳成功
				String saveDirectory = "/member_pic";
				String realPath = getServletContext().getRealPath(saveDirectory);
				photo.write(realPath + "\\" + fileName); // 是寫入硬碟的程式指令P.116 寫出照片路徑
				photoName = "/member_pic/" + photo.getSubmittedFileName();
			} else {
				photoName = req.getParameter("noUpload");
			}

			System.out.println("photoName: " + photoName);
			MemberVO memberVO = new MemberVO();
			memberVO.setMember_ID(member_ID);
			memberVO.setMember_Password(member_Password);
			memberVO.setMember_Name(member_Name);
			memberVO.setMember_Phone(member_Phone);
			memberVO.setMember_Address(member_Address);
			memberVO.setMember_Pic(photoName);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/membercentre/membermod.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.update(memberVO);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("memberVO", memberSvc.getOneMem(member_ID)); // 資料庫取出的empVO物件,存入req
			situation.put("update", "修改成功");
			String url = "/front_end/membercentre/membercentre.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			return;
		}

//刪除資料
		if ("delete".equals(action)) { // 來自listAllMember.jsp 刪除資料

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID"));

			/*************************** 2.開始刪除資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.deleteMem(member_ID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
			return;
		}

//更新會員狀態
		if ("updateStatus".equals(action)) { // 來自listAllMember.jsp 修正會員狀態資料
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);			
			/*************************** 1.接收請求參數 ***************************************/
			Integer member_Status = Integer.valueOf(req.getParameter("member_Status"));
			Integer member_Id = Integer.valueOf(req.getParameter("member_Id"));
			// System.out.println(member_Status,member_Id);
			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.updateStatus(member_Id, member_Status);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			// req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的memberVO物件,存入req
			String url = "/back_end/member/listAllMember.jsp";
			res.sendRedirect(url);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllMember.jsp
			successView.forward(req, res);
			return;
		}

//後台會員(下拉)狀態列，儲存後直接修改會員狀態		
		if ("revise".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID"));
			Integer member_Status = Integer.valueOf(req.getParameter("member_Status"));
			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.updateStatus(member_ID, member_Status);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/back_end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllMember.jsp
			successView.forward(req, res);
			return;
		}

//會員登入	控制器	
		if ("login".equals(action)) {
			System.out.println("start");
			
/*------------------ 會員登入權限如果以停權  ----------------------------*/	
			Map<String, String> situation = new LinkedHashMap<String, String>();
			req.setAttribute("situation", situation);		
			/*************************** 1.接收請求參數 ***************************************/
			String member_Email = String.valueOf(req.getParameter("email"));       // 請輸入email
			String member_Password = String.valueOf(req.getParameter("password")); // 請輸入密碼
			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService(); 
			MemberVO memberVo = new MemberVO();            
			memberVo.setMember_Email(member_Email);        
			memberVo.setMember_Password(member_Password);  
			System.out.println(member_Email);              
			System.out.println(member_Password);           
			HttpSession session = req.getSession();        
			String location = (String) session.getAttribute("location");   
			// =========================
			memberVo = memberSvc.loginMember(memberVo);  
			Integer memberId = memberVo.getMember_ID(); 
			if (memberId != null) {				
				memberVo = memberSvc.getOneMember(memberId); 
			}
//			System.out.println("會員狀態 : "+memberVo.getMember_Status());
//			System.out.println("會員是否被停權 : " + memberVo.getMember_Status().equals(2));
			String url = "";
			System.out.println(memberId);
			if (memberId == null) {                               //如果帳號密碼有可能輸入錯誤
				situation.put("error", "帳號或密碼錯誤");             //如果會員輸入 帳號密碼錯誤
				url = "/front_end/login/login.jsp";	
			}else if(memberVo.getMember_Status().equals(2)) {     //如果會員狀態 帳號已被停權
				situation.put("login", "您被停權了");                //跳出您被停權了
				url = "/front_end/login/login.jsp";	
			}else if(memberVo.getMember_Status().equals(0)) {     //如果會員狀態 帳號未啟用
				situation.put("enabled", "您帳號未被啟用，煩請收信!");    //跳出您帳號未被啟用，煩請收信!
				url = "/front_end/login/login.jsp";	
			}else{
				url = "/front_end/index.jsp";   //登入成功
				session.setAttribute("account", memberVo.getMember_ID()); // 在session內設定屬性 Attribte，指已登入過的標示與值
				session.setAttribute("memberVO", memberVo);	//在session內設定屬性(Attribute)註冊的Email.password	
			}
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			if(session.getAttribute("location") != null){  
				res.sendRedirect((String) session.getAttribute("location"));
				return;
			}
			
			System.out.println("account");
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllMember.jsp
			successView.forward(req, res); // 轉送
			return;
		}

//會員登出	控制器			
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			SCDetailService scDetailSvc = new SCDetailService();
		     List<SCDetailVO> buylist = (Vector<SCDetailVO>) session.getAttribute("shoppingcart");
		     if(buylist != null) {
		    	 for(SCDetailVO scdVO : buylist) {
		    		 scDetailSvc.addSCDetail(scdVO);
		    	 }
		     }
		    session.invalidate();
			String location = req.getContextPath() + "/front_end/index.jsp";
			res.sendRedirect(location);
			return;
		}

//新增會員 map方法
		if ("insert".equals(action)) { // 來自register.jsp的請求 新增會員資料

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
/*---------------- 會員註冊時會跳出註冊成功 ---------------------------*/			
			Map<String, String> situation = new LinkedHashMap<String, String>();
			req.setAttribute("situation", situation);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String member_Name = req.getParameter("member_Name");
			if (member_Name == null || member_Name.trim().length() == 0) {
				errorMsgs.put("member_Name", "：請勿空白");
			}
			String member_Email = req.getParameter("member_Email");

			String memeber_EmailReg = "^(.+)@(\\S+)$";
			if (member_Email == null || member_Email.trim().length() == 0) {
				errorMsgs.put("member_Email", "：請勿空白");
			} else if (!member_Email.trim().matches(memeber_EmailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("member_Email", "電子信箱: (英文 大小寫皆可)@後面是「英文 or 數字」 + 「.」 ");
			}
			
			
			String member_Password = req.getParameter("member_Password");
			System.out.println(member_Password);
			String member_PasswordReg = "^(?![a-zA-Z]+$)(?![0-9]+$)[0-9A-Za-z]{2,8}$"; // 至少八個字符，至少一個字母和一個數字：
			if (member_Password == null || member_Password.trim().length() == 0) {
				errorMsgs.put("member_Password", "：請勿空白");
			} else if (!member_Password.trim().matches(member_PasswordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("member_Password", "會員密碼: 至少八個字符，至少一個字母和一個數字, 且長度必需2到8之間");
			}


			String member_Phone = req.getParameter("member_Phone");
			String member_PhoneReg = "09[0-9]{8}"; // 字串裡面必須全是 0~9 的數字，字串長度必須是 10
			if (member_Phone == null || member_Phone.trim().length() == 0) {
				errorMsgs.put("member_Phone", "：請勿空白");
			}

			else if (!member_Phone.trim().matches(member_PhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				System.out.println(member_Phone.trim().matches(member_PhoneReg));
				errorMsgs.put("member_Phone", "會員電話: 字串裡面必須全是 0~9 的數字，字串長度必須是 10");
			}

			
			String member_Address = req.getParameter("member_Address");
			if (member_Address == null || member_Phone.trim().length() == 0) {
				errorMsgs.put("member_Address", "：請勿空白");
			}
			
			// String photoName = "";
			Part photo = req.getPart("myUpfile");
			String photoName = "/member_pic/" + photo.getSubmittedFileName(); //
			System.out.println(photoName);
			if (photo.getSubmittedFileName() == null || photo.getSubmittedFileName().trim().length() == 0) {
				errorMsgs.put("myUpfile", "會員請上傳照片");

			}

//			InputStream in = getServletContext().getResourceAsStream("/member_pic/6月課表.jpg"); // 輸入流獲取照片路徑

			if (photo.getSize() != 0) { // 如果照片不為空
				String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片

				// 利用File物件,寫入目地目錄,上傳成功
				String saveDirectory = "/member_pic";
				String realPath = getServletContext().getRealPath(saveDirectory);
				photo.write(realPath + "\\" + fileName); // 是寫入硬碟的程式指令P.116 寫出照片路徑
				photoName = "/member_pic/" + photo.getSubmittedFileName();
			} else {
				photoName = req.getParameter("noUpload");
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMember_Email(member_Email);
			memberVO.setMember_Password(member_Password);
			memberVO.setMember_Name(member_Name);
			memberVO.setMember_Phone(member_Phone);
			memberVO.setMember_Address(member_Address);
			memberVO.setMember_Pic(photoName);

			System.out.println(errorMsgs);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/register/register.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember("C", member_Email, member_Password, member_Name, member_Phone,
					member_Address, photoName, 0, 0, 0, 0);
			memberVO = memberSvc.Register(memberVO);//回傳包含 member_ID 的 memberVO
			/*************************** 寄送給會員驗證信 ***************************************/
			MailService mailService = new MailService(); // 寄送給會員驗證信物件
			String emailAddress = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/member.do?member_ID="+ memberVO.getMember_ID()+"&action=register";
			mailService.sendMail(member_Email, "會員認證信", "<a href='" + emailAddress + "'>點我啟用</a>");
//			mailService.sendMail(member_Email, "會員認證信",
//					req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath()+"/member.do?member_ID="+ memberVO.getMember_ID()+"&action=register");
//					"http://localhost:8081/CGA102G1/member.do?member_ID="+ memberVO.getMember_ID()+"&action=register ");
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			situation.put("join", "註冊成功");      //// 會員註冊成功
			String url = "/front_end/login/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交login.jsp
			successView.forward(req, res);
			return;
		}

//會員註冊後，會員會收到信件藉由連結進入		
		if ("register".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID"));
			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			memberSvc.updateStatus(member_ID, 1);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/front_end/login/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交login.jsp
			successView.forward(req, res);
			return;
		}
		
		
		
		
		
		
		

	}
}

