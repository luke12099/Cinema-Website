package com.emp_account.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.emp_account.model.EmpAccountService;
import com.emp_account.model.EmpAccountVO;
import com.emp_privilege.model.EmpPrivilegeService;
import com.emp_privilege.model.EmpPrivilegeVO;

@WebServlet("/emp/EmpAccount.do")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=5*1024*1024)
public class EmpAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		response.setContentType("text/html; charset=utf-8;");
//		PrintWriter out = response.getWriter();
		
		// 新增員工
		if("addEmp".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String emp_password = request.getParameter("emp_password").trim();
			String empPWReg = "^[(a-zA-Z0-9)]{5,10}$";
			if(emp_password == null || emp_password.length() == 0) { // 確認密碼是否為空
				errMsg.put("emp_password", "請輸入密碼");
			}else if(!emp_password.matches(empPWReg)) { // 確認密碼是否符合格式
				emp_password = "";
				errMsg.put("emp_password", "長度需介於5~10字");
			}
			
			String emp_name = request.getParameter("emp_name").trim();
			String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if(emp_name == null || emp_name.length() == 0) { // 確認名字是否為空
				errMsg.put("emp_name", "請輸入名字");
			}else if(!emp_name.matches(empNameReg)) { // 確認名字是否符合格式
				emp_name = "";
				errMsg.put("emp_name", "長度需介於2~10字");
			}
			
			// 下拉選單無錯誤處理
			Integer emp_status = Integer.valueOf(request.getParameter("emp_status"));
			
			// 一對多權限
			boolean fcIsNotEmpty = false;
			String[] emp_fcs = request.getParameterValues("fc_nos");
			List<EmpPrivilegeVO> emp_fc_list = new ArrayList<>();
			
			if(emp_fcs != null) {
				fcIsNotEmpty = true;
				for(String emp_fc: emp_fcs) {
					EmpPrivilegeVO empPrivilegeVO = new EmpPrivilegeVO();
					empPrivilegeVO.setFc_no(Integer.valueOf(emp_fc));
					emp_fc_list.add(empPrivilegeVO);
				}
			}
			
			// 資料暫存，如有以上錯誤而導致頁面跳轉可將原先的資料記憶
			EmpAccountVO empVO = new EmpAccountVO();
			empVO.setEmp_name(emp_name);
			empVO.setEmp_password(emp_password);
			empVO.setEmp_status(emp_status);
			
			// 如有任何錯誤
			if(!errMsg.isEmpty()) {
				request.setAttribute("isSuccess", "密碼或姓名格式不正確，請重新新增!");
				request.setAttribute("empVO", empVO);
				request.getRequestDispatcher("/back_end/emp/empAcc.jsp").forward(request, response);
				return;
			}
			
			/***************************2.開始新增資料*****************************************/
			EmpAccountService empSvc = new EmpAccountService();
			empVO.setEmp_email("");
			empVO.setEmp_phone("");
			empVO.setEmp_address("");
			empVO.setEmp_photo(null);
			
			if(fcIsNotEmpty) { // 有勾選權限
				empVO = empSvc.addEmp(empVO, emp_fc_list);
			} else { // 無勾選權限
				empVO = empSvc.addEmp("", emp_password, emp_name, "", "", null, emp_status);
			}
			
			/***************************3.新增完成,準備轉交(Send the Success view)*************/
			request.getSession().setAttribute("lastUpdateEmpNo", empVO.getEmp_no());
			request.getSession().setAttribute("isSuccess", "員工:" + emp_name + "新增成功!");
			response.sendRedirect(request.getContextPath() + "/back_end/emp/empAcc.jsp");
		}
		
		// 查看單一員工資料
		if("seeEmp".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			
			/***************************2.開始查詢資料*****************************************/
			EmpAccountService empSvc = new EmpAccountService();
			EmpAccountVO empVO = empSvc.getOneEmp(emp_no);
			
			if(empVO == null) {
				errMsg.put("emp_no", "查無資料");
			}
			// 錯誤轉交
			if(!errMsg.isEmpty()) {
				request.getRequestDispatcher("/back_end/emp/empAcc.jsp").forward(request, response);
				return;
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("empVO", empVO);
			request.getRequestDispatcher("/back_end/emp/empDetail.jsp").forward(request, response);
		}
		
		// 查看單一員工權限
		if("seeEmpPrivilege".equals(action)) {
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			
			/***************************2.開始查詢資料*****************************************/
			EmpPrivilegeService empPriSvc = new EmpPrivilegeService();
			List<EmpPrivilegeVO> empPriVOs = empPriSvc.getOneEmpPrivileges(emp_no);
			// 避免 empPrivilege.jsp 產生 null point exception
			if(empPriVOs == null) {
				empPriVOs = new ArrayList<EmpPrivilegeVO>();
				empPriVOs.add(new EmpPrivilegeVO());
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("emp_no", emp_no);
			request.setAttribute("empPriVOs", empPriVOs);
			request.getRequestDispatcher("/back_end/emp/empPrivilege.jsp").forward(request, response);
		}
		
		// 更新員工資料
		if("updateEmp".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			String emp_name = request.getParameter("emp_name");
			String emp_password = request.getParameter("emp_password");
			Integer emp_status = Integer.valueOf(request.getParameter("emp_status"));
			
			String emp_email = request.getParameter("emp_email").trim();
			String empEmailReg = "^[A-Za-z0-9+_.-]+@(.+)$";
			if(emp_email == null || emp_email.length() == 0) { // 確認 E-mail 是否為空
				emp_email = "";
				errMsg.put("emp_email", "E-mail不可為空");
			}else if(!emp_email.matches(empEmailReg)) { // 確認 E-mail 格式是否相符
				emp_email = "";
				errMsg.put("emp_email", "E-mail格式不正確");
			}
			
			String emp_phone = request.getParameter("emp_phone").trim();
			String empPhoneReg = "^[0-9]{4}[-][0-9]{6}$";
			if(emp_phone == null || emp_phone.length() == 0) { // 確認電話是否為空
				emp_phone = "";
				errMsg.put("emp_phone", "電話號碼不可為空");
			}else if(!emp_phone.matches(empPhoneReg)) { // 確認電話是否符合規格
				emp_phone = "";
				errMsg.put("emp_phone", "請輸入09XX-XXXXXX");
			}
			
			String emp_address = request.getParameter("emp_address").trim();
			if(emp_address == null || emp_address.length() == 0) { // 確認地址是否為空
				emp_address = "";
				errMsg.put("emp_address", "地址不可為空");
			}
			
			// 可為空值
			Part part = request.getPart("emp_photo");
			byte[] emp_photo = null;
			InputStream in = null;
			if(part.getSize() == 0) { // 若無上傳資料，則進資料庫抓資料再存回去
				EmpAccountService empSvc = new EmpAccountService();
				emp_photo = empSvc.getOneEmp(emp_no).getEmp_photo();
			} else { // 上傳照片
				in = part.getInputStream();
				emp_photo = new byte[in.available()];
				in.read(emp_photo);
				in.close();
			}
			
			// 資料暫存，如有以上錯誤而導致頁面跳轉可將原先的資料記憶
			EmpAccountVO empVO = new EmpAccountVO();
			empVO.setEmp_no(emp_no);
			empVO.setEmp_name(emp_name);
			empVO.setEmp_password(emp_password);
			empVO.setEmp_status(emp_status);
			empVO.setEmp_email(emp_email);
			empVO.setEmp_phone(emp_phone);
			empVO.setEmp_address(emp_address);
			
			// 錯誤轉交
			if(!errMsg.isEmpty()) {
				request.setAttribute("isOk", "資料有誤，修改失敗!");
				request.setAttribute("empVO", empVO);
				request.getRequestDispatcher("/back_end/emp/empDetail.jsp").forward(request, response);
				return;
			}
			
			/***************************2.開始更新資料*****************************************/
			EmpAccountService empSvc = new EmpAccountService();
			empVO = empSvc.updateEmp(emp_no, emp_email, emp_password, emp_name, emp_phone, emp_address, emp_photo, emp_status);
			
			/***************************3.更新完成,準備轉交(Send the Success view)*************/
			request.getSession().setAttribute("lastUpdateEmpNo", emp_no);
			request.getSession().setAttribute("isSuccess", "員工編號:" + emp_no + "，員工姓名:" + emp_name + "，更新成功!");
			response.sendRedirect(request.getContextPath() + "/back_end/emp/empAcc.jsp");
		}
		
		// 更新個人資料
		if("updateSelf".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			String emp_name = request.getParameter("emp_name");
			String emp_password = request.getParameter("emp_password");
			Integer emp_status = Integer.valueOf(request.getParameter("emp_status"));
			
			String emp_email = request.getParameter("emp_email").trim();
			String empEmailReg = "^[A-Za-z0-9+_.-]+@(.+)$";
			if(emp_email == null || emp_email.length() == 0) { // 確認 E-mail 是否為空
				emp_email = "";
				errMsg.put("emp_email", "E-mail不可為空");
			}else if(!emp_email.matches(empEmailReg)) { // 確認 E-mail 是否符合格式
				emp_email = "";
				errMsg.put("emp_email", "E-mail格式不正確");
			}
			
			String emp_phone = request.getParameter("emp_phone").trim();
			String empPhoneReg = "^[0-9]{4}[-][0-9]{6}$";
			if(emp_phone == null || emp_phone.length() == 0) { // 確認電話是否為空
				emp_phone = "";
				errMsg.put("emp_phone", "電話號碼不可為空");
			}else if(!emp_phone.matches(empPhoneReg)) { // 確認電話是否符合格式
				emp_phone = "";
				errMsg.put("emp_phone", "請輸入09XX-XXXXXX");
			}
			
			String emp_address = request.getParameter("emp_address").trim();
			if(emp_address == null || emp_address.length() == 0) { // 確認地址是否為空
				emp_address = "";
				errMsg.put("emp_address", "地址不可為空");
			}
			
			// 可為空值
			Part part = request.getPart("emp_photo");
			byte[] emp_photo = null;
			InputStream in = null;
			if(part.getSize() == 0) { // 若無上傳資料，則進資料庫抓資料再存回去
				EmpAccountService empSvc = new EmpAccountService();
				emp_photo = empSvc.getOneEmp(emp_no).getEmp_photo();
			} else { // 上傳照片
				in = part.getInputStream();
				emp_photo = new byte[in.available()];
				in.read(emp_photo);
				in.close();
			}
			
			// 資料暫存，如有以上錯誤而導致頁面跳轉可將原先的資料記憶
			EmpAccountVO empVO = new EmpAccountVO();
			empVO.setEmp_no(emp_no);
			empVO.setEmp_name(emp_name);
			empVO.setEmp_password(emp_password);
			empVO.setEmp_status(emp_status);
			empVO.setEmp_email(emp_email);
			empVO.setEmp_phone(emp_phone);
			empVO.setEmp_address(emp_address);
			
			// 錯誤轉交
			if(!errMsg.isEmpty()) {
				request.setAttribute("isOk", "資料錯誤，更新失敗!!");
				request.setAttribute("empVO", empVO);
				request.getRequestDispatcher("/back_end/emp/empSelf.jsp").forward(request, response);
				return;
			}
			
			/***************************2.開始更新資料*****************************************/
			EmpAccountService empSvc = new EmpAccountService();
			empVO = empSvc.updateEmp(emp_no, emp_email, emp_password, emp_name, emp_phone, emp_address, emp_photo, emp_status);
			
			/***************************3.更新完成,準備轉交(Send the Success view)*************/
			request.setAttribute("isOk", "資料已更新!!");
			request.getRequestDispatcher("/back_end/emp/empSelf.jsp").forward(request, response);
		}
		
		// 刪除員工資料 (無法刪除)
//		if("deleteEmp".equals(action)) {
//			// 存放錯誤訊息
//			Map<String, String> errMsg = new LinkedHashMap<String, String>();
//			request.setAttribute("errMsg", errMsg);
//			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
//			/***************************2.開始刪除資料*****************************************/
//			EmpAccountService empSvc = new EmpAccountService();
//			empSvc.deleteEmp(emp_no);
//			/***************************3.刪除完成,準備轉交(Send the Success view)*************/
//			request.getSession().setAttribute("lastUpdateEmpNo", null); // 清除最後一次更新/新增的資料
//			request.getSession().setAttribute("isSucess", ""); // 清除最後一次更新/新增的提示訊息
//			response.sendRedirect(request.getContextPath() + "/back_end/emp/empAcc.jsp");
//		}
		
		// 重設密碼
		if("resetPassword".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			
			/***************************2.開始刪除資料*****************************************/
			EmpAccountService empSvc = new EmpAccountService();
			empSvc.resetPassword(emp_no); // 重設密碼為 0000
			
			/***************************3.刪除完成,準備轉交(Send the Success view)*************/
			request.getSession().setAttribute("lastUpdateEmpNo", emp_no);
			request.getSession().setAttribute("isSuccess", "員工編號:" + emp_no + "，員工姓名:" + empSvc.getOneEmp(emp_no).getEmp_name() + "，更新成功!");
			response.sendRedirect(request.getContextPath() + "/back_end/emp/empAcc.jsp");
		}
		
		// 更新員工狀態
		if("updateStatus".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			Integer emp_status = Integer.valueOf(request.getParameter("emp_status"));
			
			/***************************2.開始刪除資料*****************************************/
			EmpAccountService empSvc = new EmpAccountService();
			empSvc.updateStatus(emp_no, emp_status);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)*************/
			request.getSession().setAttribute("lastUpdateEmpNo", emp_no);
			request.getSession().setAttribute("isSuccess", "員工編號:" + emp_no + "，員工姓名:" + empSvc.getOneEmp(emp_no).getEmp_name() + "，更新成功!");
			response.sendRedirect(request.getContextPath() + "/back_end/emp/empAcc.jsp");
		}
	}
}
