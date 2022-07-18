package com.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp_account.model.EmpAccountVO;
import com.emp_function.model.EmpFunctionService;
import com.emp_privilege.model.EmpPrivilegeService;
import com.emp_privilege.model.EmpPrivilegeVO;

//@WebFilter("/back_end/*")
public class EmpPrivilegeFilter extends HttpFilter {
	
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String uri = request.getRequestURI(); // 自己
		EmpAccountVO empAccount = (EmpAccountVO)request.getSession().getAttribute("empAccount");
		
		// login 頁面 & css & js pass
		if(uri.endsWith("empLogin.jsp") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
			chain.doFilter(request, response);	
			return;
		} 
		
		// 存放不可前往的頁面
		Set<String> priUri = new HashSet<String>();	
		
		if(empAccount != null) { // 避免未登入時空指標
			// 查詢權限號碼
			List<EmpPrivilegeVO> priList = new EmpPrivilegeService().getOneEmpPrivileges(empAccount.getEmp_no());
			// 將權限編號單獨拉出來
			List<Integer> canAccess = new ArrayList<Integer>();
			for(EmpPrivilegeVO pri: priList) {
				canAccess.add(pri.getFc_no());
			}
//			System.out.println(canAccess);
			// 與既有權限 1~21 的差異加入"無授權"
			List<Integer> canNotAccess = new ArrayList<Integer>();
			for(int i = 1; i <= 19; i++) {
				if(!canAccess.contains(i)) {
					canNotAccess.add(i);
				}
			}
//			System.out.println(canNotAccess);
			// 將權限號碼對應的網址放入 HashSet
			for(Integer numNot: canNotAccess) {
				EmpFunctionService empFcSvc = new EmpFunctionService();
				String accessUri = request.getContextPath() + empFcSvc.getOneFunc(numNot).getFc_description();
				priUri.add(accessUri);
//				System.out.println(accessUri);
			}
		} else {
			System.out.println("尚未登入");
		}
		
		// 判斷不可前往的頁面是否包含自己
		if(priUri.contains(uri)) { // 有包含，導回首頁 
			response.sendRedirect(request.getContextPath() + "/back_end/empIndex.jsp");
		} else { // 未包含，放行
			chain.doFilter(request, response);	
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
