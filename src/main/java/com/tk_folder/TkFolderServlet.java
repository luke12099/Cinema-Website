package com.tk_folder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fd_ord_dt.model.FdOrdDtVO;
import com.google.gson.Gson;
import com.refundTicket.RefundTicketService;

@WebServlet("/TkFolderServlet.do")
public class TkFolderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("listAllOrdInf".equals(action)) {
			
			Integer member_ID = null;
			
			if(req.getParameter("member_ID").isEmpty()) {
				String url = "/front_end/login/login.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			}else {
				
				member_ID = Integer.valueOf(req.getParameter("member_ID"));
				Map<String,Object> map = new TkFolderService().listAllOrdInf(member_ID);
				
				req.setAttribute("map", map);
				req.setAttribute("member_ID", member_ID);
				String url = "/front_end/ticketFolder/ticketFolder.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			};
			
		}
		
		if("getOneDetail".equals(action)) {
			
			Long tkOrdID = Long.valueOf(req.getParameter("tkOrdID"));
			Integer member_ID = Integer.valueOf(req.getParameter("member_ID"));
			
			Map<String,Object> map = new TkFolderService().getOneDetail(tkOrdID);
			
			req.setAttribute("tkOrdID", tkOrdID);
			req.setAttribute("member_ID", member_ID);
			req.setAttribute("map", map);
			String url = "/front_end/ticketFolder/ticketInfo.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		
		if("changeMealState".equals(action)) {
			
			Long tkOrdID = Long.valueOf(req.getParameter("tkOrdID"));
			Byte fdState = Byte.valueOf(req.getParameter("fdState"));
			// 回傳一個已更新資料的foodOrdDt list
			Map<String,Object> map =new TkFolderService().updateFoodOrd(fdState, tkOrdID);
			
			Gson gson = new Gson();
			
			res.setContentType("text/json; charset=UTF-8");
			res.getWriter().write(gson.toJson(map));
		}
		
		if("verifyCode".equals(action)) {
			
			Integer verifyEmpCode = null;
			Integer verifyResult = null;
			if(!req.getParameter("code").isEmpty() && req.getParameter("code").matches("[+-]?\\d*(\\.\\d+)?")) {
				
				verifyEmpCode =Integer.valueOf(req.getParameter("code"));
				verifyResult = new TkFolderService().verifyCode(verifyEmpCode);
				
				Gson gson = new Gson();
				
				res.setContentType("text/json; charset=UTF-8");
				res.getWriter().write(gson.toJson(verifyResult));
			}else {
				
				verifyResult=0;
				Gson gson = new Gson();
				
				res.setContentType("text/json; charset=UTF-8");
				res.getWriter().write(gson.toJson(verifyResult));
				return;
			}
		}
		// 入場時更改訂單明細用
		if("updateAdmission".equals(action)) {
			
			Long tkDtID = Long.valueOf(req.getParameter("tkDtID"));
			Integer seatState = Integer.valueOf(req.getParameter("seatState"));
			
			new RefundTicketService().updateOneDt(seatState,tkDtID);
			res.setContentType("text/json; charset=UTF-8");
			// 成功update後 給前端一個回應
			res.getWriter().write(new Gson().toJson(seatState));

		}
		
	}

}
