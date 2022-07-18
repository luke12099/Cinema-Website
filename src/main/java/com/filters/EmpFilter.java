package com.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/back_end/*")
public class EmpFilter extends HttpFilter {
	
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String uri = request.getRequestURI();
		Object empAccount = request.getSession().getAttribute("empAccount");
		
		// login 頁面 & css pass
		if(uri.endsWith("empLogin.jsp") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
			chain.doFilter(request, response);	
			
		// 判斷是否登入過?
		} else if(empAccount == null) {  
			
			request.getSession().setAttribute("lastPage", request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/back_end/empLogin.jsp");
			
		} else {
			
			chain.doFilter(request, response);

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
