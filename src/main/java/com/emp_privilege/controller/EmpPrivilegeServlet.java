package com.emp_privilege.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_privilege.model.EmpPrivilegeService;
import com.emp_privilege.model.EmpPrivilegeVO;

@WebServlet("/emp/EmpPrivilege.do")
public class EmpPrivilegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if("updatePrivilege".equals(action)) {
 			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
			String[] emp_fcs = request.getParameterValues("fc_nos");
			
			List<EmpPrivilegeVO> emp_fc_list = new ArrayList<>();
			
			if(emp_fcs == null) {
				request.setAttribute("isOk", "權限不可為空!");
				// 撈原本的資料
				List<EmpPrivilegeVO> empPriVOs = new EmpPrivilegeService().getOneEmpPrivileges(emp_no);
				if(empPriVOs == null) {
					empPriVOs = new ArrayList<EmpPrivilegeVO>();
					empPriVOs.add(new EmpPrivilegeVO());
				}
				request.setAttribute("emp_no", emp_no);
				request.setAttribute("empPriVOs", empPriVOs);
				request.getRequestDispatcher("/back_end/emp/empPrivilege.jsp").forward(request, response);
				return;
			}
			
			for(String emp_fc: emp_fcs) {
				EmpPrivilegeVO empPrivilegeVO = new EmpPrivilegeVO();
				empPrivilegeVO.setFc_no(Integer.valueOf(emp_fc));
				emp_fc_list.add(empPrivilegeVO);
			}
			/***************************2.開始刪除/新增權限**********************************/
			EmpPrivilegeService empPriSvc = new EmpPrivilegeService();
			List<EmpPrivilegeVO> updateList = new ArrayList<EmpPrivilegeVO>();
			for(String emp_fc: emp_fcs) {
				EmpPrivilegeVO empPriVO = new EmpPrivilegeVO();
				empPriVO.setEmp_no(emp_no);
				empPriVO.setFc_no(Integer.valueOf(emp_fc));
				updateList.add(empPriVO);
			}
			empPriSvc.updatePrivilege(updateList);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			request.getSession().setAttribute("lastUpdateEmpNo", emp_no);
			request.getSession().setAttribute("isSuccess", "員工編號:" + emp_no + "，權限更新成功!");
			response.sendRedirect(request.getContextPath() + "/back_end/emp/empAcc.jsp");
		}
		
	}

}
