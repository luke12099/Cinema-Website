package com.wishing_pond.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wish_reply.model.WishReplyService;
import com.wish_reply.model.WishReplyVO;
import com.wishing_list.model.WishingListService;
import com.wishing_list.model.WishingListVO;
import com.wishing_pond.model.WishingPondService;
import com.wishing_pond.model.WishingPondVO;

@WebServlet("/wish/WishingPond.do")
public class WishingPondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		if("seeOneEvent".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer wish_no = Integer.valueOf(request.getParameter("wish_no"));
			Integer top_one = Integer.valueOf(request.getParameter("top_one"));
			
			/***************************2.開始查詢資料*****************************************/
			WishingListService wishListSvc = new WishingListService();
			List<WishingListVO> wishListVOs =  wishListSvc.getOneWishingPond(wish_no);
			if(wishListVOs == null) {
				errMsg.put("notFound", "查無此筆資料");
				request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
			}
			WishReplyService wishReplySvc = new WishReplyService();
			List<WishReplyVO> wishReplyVOs = wishReplySvc.getOneWishEvent(wish_no);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("wishListVOs", wishListVOs);
			request.setAttribute("wishReplyVOs", wishReplyVOs);
			request.setAttribute("top_one", top_one);
			request.getRequestDispatcher("/back_end/wish/wishDetail.jsp").forward(request, response);
		}
		
		if("multiSearch".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Map<String, String[]> map = request.getParameterMap();
			
			/***************************2.開始查詢資料*****************************************/
			WishingPondService wishSvc = new WishingPondService();
			List<WishingPondVO> wishVOs =  wishSvc.getAll(map);
			if(wishVOs.size() == 0) {
				errMsg.put("notFound", "無符合的資料");
				request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
				return;
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("list", wishVOs);
			request.setAttribute("listSize", wishVOs.size());
			request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
		}
		
		if("showAll".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************2.開始查詢資料*****************************************/
			WishingPondService wishSvc = new WishingPondService();
			List<WishingPondVO> wishVOs =  wishSvc.getAll();
			if(wishVOs.size() == 0) {
				errMsg.put("notFound", "查無資料");
				request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("list", wishVOs);
			request.setAttribute("listSize", wishVOs.size());
			request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
		}
		
		if("addWish".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String wish_name = request.getParameter("wish_name");
			if(wish_name == null || wish_name.length() == 0) {
				errMsg.put("wish_name", "請輸入活動名稱");
			}
			
			String start_date = request.getParameter("start_date");
			String dateReg = "^\\d{4}-\\d{2}-\\d{2}$";
			if(start_date == null || start_date.length() == 0) {
				errMsg.put("start_date", "請選擇起始日期");
			} else if(!start_date.matches(dateReg)) {
				errMsg.put("start_date", "日期格式不正確");
			}
			
			String end_date = request.getParameter("end_date");
			if(end_date == null || end_date.length() == 0) {
				errMsg.put("end_date", "請選擇結束日期");
			} else if(!end_date.matches(dateReg)) {
				errMsg.put("start_date", "日期格式不正確");
			}
			
			WishingPondVO wishVO = new WishingPondVO();
			wishVO.setWish_name(wish_name);
			
			String[] movies = request.getParameterValues("checkMovie");
			if(movies == null) {
				errMsg.put("checkMovie", "電影選項不可為空!");
			} else if(movies.length < 2){
				errMsg.put("checkMovie", "電影選項最少為2個!");
			} else if(movies.length > 6) {
				errMsg.put("checkMovie", "電影選項最多為6個!");
			}
			
			if(!errMsg.isEmpty()) {
				request.setAttribute("wishVO", wishVO);
				request.getRequestDispatcher("/back_end/wish/newWish.jsp").forward(request, response);
				return;
			}
			
			wishVO.setWish_start(java.sql.Date.valueOf(start_date));
			wishVO.setWish_end(java.sql.Date.valueOf(end_date));
			
			List<WishingListVO> list = new ArrayList<WishingListVO>();
			for(String movie: movies) {
				WishingListVO wishListVO = new WishingListVO();
				wishListVO.setMv_id(Integer.valueOf(movie));
				list.add(wishListVO);
			}
			
			/***************************2.開始新增資料*****************************************/
			WishingPondService wishSvc = new WishingPondService();
			wishVO = wishSvc.addWishingPondWithOption(wishVO, list);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("wishVO", wishVO);
			request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
		}
		
		if("updateEvent".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer wish_no = Integer.valueOf(request.getParameter("wish_no"));
			
			/***************************2.開始查詢資料*****************************************/
			WishingPondService wishSvc = new WishingPondService();
			WishingPondVO wishVO = wishSvc.getOneWishingPond(wish_no);
			WishingListService wishListSvc = new WishingListService();
			List<WishingListVO> wishListVOs =  wishListSvc.getOneWishingPond(wish_no);
			if(wishListVOs == null) {
				errMsg.put("notFound", "查無此筆資料");
				request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("wishVO", wishVO);
			request.setAttribute("wishListVOs", wishListVOs);
			request.getRequestDispatcher("/back_end/wish/updateWish.jsp").forward(request, response);
		}
		
		if("updateWish".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer wish_no = Integer.valueOf(request.getParameter("wish_no"));
			
			String wish_name = request.getParameter("wish_name");
			if(wish_name == null || wish_name.length() == 0) {
				errMsg.put("wish_name", "請輸入活動名稱");
			}
			
			String start_date = request.getParameter("start_date");
			String dateReg = "^\\d{4}-\\d{2}-\\d{2}$";
			if(start_date == null || start_date.length() == 0) {
				errMsg.put("start_date", "請選擇起始日期");
			} else if(!start_date.matches(dateReg)) {
				errMsg.put("start_date", "日期格式不正確");
			}
			
			String end_date = request.getParameter("end_date");
			if(end_date == null || end_date.length() == 0) {
				errMsg.put("end_date", "請選擇結束日期");
			} else if(!end_date.matches(dateReg)) {
				errMsg.put("start_date", "日期格式不正確");
			}
			
			WishingPondVO wishVO = new WishingPondVO();
			wishVO.setWish_no(wish_no);
			wishVO.setWish_name(wish_name);
			
			String[] movies = request.getParameterValues("checkMovie");
			if(movies == null) {
				errMsg.put("checkMovie", "電影選項不可為空!");
			} 
			
			if(!errMsg.isEmpty()) {
				request.setAttribute("wishVO", wishVO);
				request.getRequestDispatcher("/back_end/wish/updateWish.jsp").forward(request, response);
				return;
			}
			
			wishVO.setWish_start(java.sql.Date.valueOf(start_date));
			wishVO.setWish_end(java.sql.Date.valueOf(end_date));
			
			List<WishingListVO> list = new ArrayList<WishingListVO>();
			for(String movie: movies) {
				WishingListVO wishListVO = new WishingListVO();
				wishListVO.setWish_no(wish_no);
				wishListVO.setMv_id(Integer.valueOf(movie));
				list.add(wishListVO);
			}
			
			/***************************2.開始新增資料*****************************************/
			WishingPondService wishSvc = new WishingPondService();
			Integer lastUpdate = wishSvc.updateWishingPondWithOption(wishVO, list);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("lastUpdate", lastUpdate);
			request.getRequestDispatcher("/back_end/wish/wishPond.jsp").forward(request, response);
		}
	}

}
