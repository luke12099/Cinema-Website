package com.emp_account.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_account.model.EmpAccountService;
import com.emp_account.model.EmpAccountVO;

@WebServlet("/emp/ShowBlob")
public class ShowBlob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/gif");
		ServletOutputStream out = response.getOutputStream();
		
		Integer emp_no = Integer.valueOf(request.getParameter("emp_no"));
		
		EmpAccountService empSvc = new EmpAccountService();
		EmpAccountVO empVO = empSvc.getOneEmp(emp_no);
		
		byte[] photo = empVO.getEmp_photo();
		
		if(photo != null) { 
			// 如果有照片
			BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(photo));
			byte[] buff = new byte[4 * 1024]; // 4k
			int length;
			while((length = in.read(buff)) != -1) {
				out.write(buff, 0, length);
			}
			in.close();
		}else {
			// 如果沒照片
			InputStream in = getServletContext().getResourceAsStream("/back_end/emp/empNoPhoto.png");
			byte[] buff = new byte[in.available()];
			in.read(buff);
			out.write(buff);
		}
		
	}

}
