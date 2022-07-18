package com.emp_account.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_account.model.EmpAccountService;
import com.emp_account.model.EmpAccountVO;

@WebServlet("/emp/OrderBy")
public class OrderBy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderType = request.getParameter("orderType");
		
		if("asc".equals(orderType)) {
			EmpAccountService empSvc = new EmpAccountService();
			List<EmpAccountVO> listAll = empSvc.getAllDesc();
			request.setAttribute("listAll", listAll);
			request.setAttribute("orderSign", "&#8593;");
			request.setAttribute("orderType", "desc");
			request.getRequestDispatcher("/back_end/emp/empAcc.jsp").forward(request, response);
		} else {
			EmpAccountService empSvc = new EmpAccountService();
			List<EmpAccountVO> listAll = empSvc.getAll();
			request.setAttribute("listAll", listAll);
			request.setAttribute("orderSign", "&#8595;");
			request.setAttribute("orderType", "asc");
			request.getRequestDispatcher("/back_end/emp/empAcc.jsp").forward(request, response);
		}
	}

}
