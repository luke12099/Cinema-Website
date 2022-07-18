package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class MemberFilter
 */
//@WebFilter("/front_end/membercentre/*")
	//	,/front_end/ticketFolder/*"
public class MemberFilter extends HttpFilter implements Filter {
	
	public void destroy() {     //撤銷濾器時呼叫
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		Object account = req.getSession().getAttribute("account");
		
		
		// login 頁面 & css pass
		if(uri.endsWith("Login.jsp") || uri.endsWith(".css") || uri.endsWith(".js")) {
			
			chain.doFilter(request, response);	
//			System.out.println("1");
		// 判斷是否登入過?
		} else if(account == null) {  
			
		
//			System.out.println("2");

			req.getSession().setAttribute("lastPage", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front_end/login/login.jsp");
			
		} else {
			
//			System.out.println("3");
			chain.doFilter(request, response);

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
