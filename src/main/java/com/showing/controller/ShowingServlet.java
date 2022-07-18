package com.showing.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.movie.model.MovieService;
import com.showing.model.*;
import com.google.gson.Gson;
import com.hall.model.*;

@WebServlet("/showing/showing.do")
public class ShowingServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("SH_ID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/showing/showing_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer SH_ID = null;
				try {
					SH_ID = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/showing/showing_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShowingService showingSvc = new ShowingService();
				ShowingVO showingVO = showingSvc.getOneShowing(SH_ID);
				if (showingVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/showing/showing_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("showingVO", showingVO); // 資料庫取出的showingVO物件,存入req
				String url = "/back_end/showing/listOneShowing.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllShowing.jsp 或  /movie/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/showing/listAllShowing.jsp】 或  【/movie/listEmps_ByDeptno.jsp】 或 【 /movie/listAllDept.jsp】		
			
				/***************************1.接收請求參數****************************************/
				Integer SH_ID = Integer.valueOf(req.getParameter("SH_ID"));
				
				/***************************2.開始查詢資料****************************************/
				ShowingService showingSvc = new ShowingService();
				ShowingVO showingVO = showingSvc.getOneShowing(SH_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("showingVO", showingVO); // 資料庫取出的showingVO物件,存入req
				String url = "/back_end/showing/update_showing_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_showing_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_showing_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/showing/listAllShowing.jsp】 或  【/movie/listEmps_ByDeptno.jsp】 或 【 /movie/listAllDept.jsp】
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer SH_ID = Integer.valueOf(req.getParameter("SH_ID"));
				try {
					SH_ID = Integer.valueOf(req.getParameter("SH_ID").trim());
				} catch (NumberFormatException e) {
					SH_ID = 0;
					errorMsgs.add("場次編號請填數字.");
				}
				
				Integer mvId = Integer.valueOf(req.getParameter("mvId"));
				try {
					mvId = Integer.valueOf(req.getParameter("mvId").trim());
				} catch (NumberFormatException e) {
					mvId = 0;
					errorMsgs.add("電影編號請填數字.");
				}
				
				Integer HL_ID = Integer.valueOf(req.getParameter("HL_ID"));
				try {
					HL_ID = Integer.valueOf(req.getParameter("HL_ID").trim());
				} catch (NumberFormatException e) {
					HL_ID = 0;
					errorMsgs.add("影廳編號請填數字.");
				}
				
				Integer SH_STATE = Integer.valueOf(req.getParameter("SH_STATE"));
				try {
					SH_STATE = Integer.valueOf(req.getParameter("SH_STATE").trim());
				} catch (NumberFormatException e) {
					SH_STATE = 0;
					errorMsgs.add("場次狀態請填數字.");
				}
				
				String SH_SEAT_STATE = req.getParameter("SH_SEAT_STATE").trim();
				if (SH_SEAT_STATE == null || SH_SEAT_STATE.trim().length() == 0) {
					errorMsgs.add("座位狀態請勿空白");
				}	
				
				Timestamp SH_TIME = null;
				try {
					SH_TIME = Timestamp.valueOf(req.getParameter("SH_TIME"));
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}
				
				Integer SH_TYPE = Integer.valueOf(req.getParameter("SH_TYPE"));
				try {
					SH_TYPE = Integer.valueOf(req.getParameter("SH_TYPE").trim());
				} catch (NumberFormatException e) {
					SH_STATE = 0;
					errorMsgs.add("電影播放類型請填數字.");
				}

				ShowingVO showingVO = new ShowingVO();
				showingVO.setSH_ID(SH_ID);
				showingVO.setmvId(mvId);
				showingVO.setHL_ID(HL_ID);
				showingVO.setSH_STATE(SH_STATE);
				showingVO.setSH_SEAT_STATE(SH_SEAT_STATE);
				showingVO.setSH_TIME(SH_TIME);
				showingVO.setSH_TYPE(SH_TYPE);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("showingVO", showingVO); // 含有輸入格式錯誤的showingVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/showing/update_showing_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShowingService showingSvc = new ShowingService();
				showingVO = showingSvc.updateShowing(SH_ID, mvId, HL_ID, SH_STATE, SH_SEAT_STATE,SH_TIME, SH_TYPE);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				MovieService movieSvc = new MovieService();
				if(requestURL.equals("/back_end/movie/listShowings_BymvId.jsp") || requestURL.equals("/back_end/movie/listAllMovie.jsp"))
					req.setAttribute("listShowings_BymvId",movieSvc.getShowingsBymvId(mvId)); // 資料庫取出的list物件,存入request
				
				if(requestURL.equals("/back_end/showing/listShowings_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<ShowingVO> list  = showingSvc.getAll(map);
					req.setAttribute("listShowings_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}

                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
		}

//        if ("insert".equals(action)) { // 來自addShowing.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer mvId = Integer.valueOf(req.getParameter("mvId"));
//				try {
//					mvId = Integer.valueOf(req.getParameter("mvId").trim());
//				} catch (NumberFormatException e) {
//					mvId = 0;
//					errorMsgs.add("電影編號請填數字.");
//				}
//				
//				Integer HL_ID = Integer.valueOf(req.getParameter("HL_ID"));
//				try {
//					HL_ID = Integer.valueOf(req.getParameter("HL_ID").trim());
//				} catch (NumberFormatException e) {
//					HL_ID = 0;
//					errorMsgs.add("影廳編號請填數字.");
//				}
//				
//				Integer SH_STATE = Integer.valueOf(req.getParameter("SH_STATE"));
//				try {
//					SH_STATE = Integer.valueOf(req.getParameter("SH_STATE").trim());
//				} catch (NumberFormatException e) {
//					SH_STATE = 0;
//					errorMsgs.add("場次狀態請填數字.");
//				}
//				
//				String SH_SEAT_STATE = req.getParameter("SH_SEAT_STATE").trim();
//				if (SH_SEAT_STATE == null || SH_SEAT_STATE.trim().length() == 0) {
//					errorMsgs.add("座位狀態請勿空白");
//				}	
//				
//				Timestamp SH_TIME = null;
//				try {
//					SH_TIME = Timestamp.valueOf(req.getParameter("SH_TIME"));
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				Integer SH_TYPE = Integer.valueOf(req.getParameter("SH_TYPE"));
//				try {
//					SH_TYPE = Integer.valueOf(req.getParameter("SH_TYPE").trim());
//				} catch (NumberFormatException e) {
//					SH_STATE = 0;
//					errorMsgs.add("電影播放類型請填數字.");
//				}
//
//				ShowingVO showingVO = new ShowingVO();
//				showingVO.setmvId(mvId);
//				showingVO.setHL_ID(HL_ID);
//				showingVO.setSH_STATE(SH_STATE);
//				showingVO.setSH_SEAT_STATE(SH_SEAT_STATE);
//				showingVO.setSH_TIME(SH_TIME);
//				showingVO.setSH_TYPE(SH_TYPE);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("showingVO", showingVO); // 含有輸入格式錯誤的showingVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/showing/addShowing.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				ShowingService showingSvc = new ShowingService();
//				showingVO = showingSvc.addShowing(mvId, HL_ID, SH_STATE, SH_SEAT_STATE, SH_TIME, SH_TYPE);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back_end/showing/listAllShowing.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllShowing.jsp
//				successView.forward(req, res);				
//		}
        if ("insert".equals(action)) { // 來自addShowing.jsp的請求  
        	
        	List<String> errorMsgs = new LinkedList<String>();
        	// Store this set in the request scope, in case we need to
        	// send the ErrorPage view.
        	req.setAttribute("errorMsgs", errorMsgs);
        	Map<String, String[]> map = req.getParameterMap();
        	        	
        	/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
        	Integer mvId = null;
        	try {
        		mvId = Integer.valueOf(map.get("mvId")[0].trim());
        	} catch (NumberFormatException e) {
        		mvId = 0;
        		errorMsgs.add("電影編號請填數字.");
        	}
        	
        	Integer HL_ID = null;
        	try {
        		HL_ID = Integer.valueOf(map.get("HL_ID")[0].trim());
        	} catch (NumberFormatException e) {
        		HL_ID = 0;
        		errorMsgs.add("影廳編號請填數字.");
        	}
        	
        	Integer SH_STATE = null;
        	try {
        		SH_STATE = Integer.valueOf(map.get("SH_STATE")[0].trim());
        	} catch (NumberFormatException e) {
        		SH_STATE = 0;
        		errorMsgs.add("場次狀態請填數字.");
        	}
        	
//        	String SH_SEAT_STATE = map.get("SH_SEAT_STATE")[0].trim();
//        	if (SH_SEAT_STATE == null || SH_SEAT_STATE.trim().length() == 0) {
//        		errorMsgs.add("座位狀態請勿空白");
//        	}	
        	
        	Timestamp SH_TIME = null;
//        	try {
//        		SH_TIME = Timestamp.valueOf(req.getParameter("SH_TIME"));
//        	} catch (IllegalArgumentException e) {
//        		errorMsgs.add("請輸入日期!");
//        	}
        	
        	Integer SH_TYPE = null;
        	try {
        		SH_TYPE = Integer.valueOf(map.get("SH_TYPE")[0].trim());
        	} catch (NumberFormatException e) {
        		SH_STATE = 0;
        		errorMsgs.add("電影播放類型請填數字.");
        	}
        	
        	
        	ShowingVO showingVO = null;
        	List<ShowingVO> list = new ArrayList<ShowingVO>();
        	for(int i = 0; i < map.get("SH_TIME1").length; i++) {
        		
        		HallService hlSvc = new HallService();
            	HallVO hallVO = hlSvc.findByPrimaryKey(HL_ID);
            			
        	showingVO = new ShowingVO();
        	showingVO.setmvId(mvId);
        	showingVO.setHL_ID(HL_ID);
        	showingVO.setSH_STATE(SH_STATE);
        	showingVO.setSH_SEAT_STATE(hallVO.getHlSeat());
        	System.out.println(map.get("SH_TIME1")[i]);
        	SH_TIME = Timestamp.valueOf(map.get("SH_TIME1")[i]);
        	showingVO.setSH_TIME(SH_TIME);
        	showingVO.setSH_TYPE(SH_TYPE);
        	list.add(showingVO);
        	}
        	// Send the use back to the form, if there were errors
        	if (!errorMsgs.isEmpty()) {
        		req.setAttribute("showingVO", showingVO); // 含有輸入格式錯誤的showingVO物件,也存入req
        		RequestDispatcher failureView = req
        				.getRequestDispatcher("/back_end/showing/addShowing.jsp");
        		failureView.forward(req, res);
        		return;
        	}
        	
        	/***************************2.開始新增資料***************************************/
        	ShowingService showingSvc = new ShowingService();
//        	showingVO = showingSvc.addShowing(mvId, HL_ID, SH_STATE, SH_SEAT_STATE, SH_TIME, SH_TYPE);
        	for(ShowingVO showingVO1: list) {
        	showingVO = showingSvc.addShowing(showingVO1);
        	}
        	/***************************3.新增完成,準備轉交(Send the Success view)***********/
        	String url = "/back_end/showing/listAllShowing.jsp";
        	RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllShowing.jsp
        	successView.forward(req, res);				
        }
		
       
		if ("delete".equals(action)) { // 來自listAllShowing.jsp 或  /movie/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/showing/listAllShowing.jsp】 或  【/movie/listShowings_BymvId.jsp】 或 【 /movie/listAllMovie.jsp】

				/***************************1.接收請求參數***************************************/
				Integer SH_ID = Integer.valueOf(req.getParameter("SH_ID"));
				
				/***************************2.開始刪除資料***************************************/
				ShowingService showingSvc = new ShowingService();
				ShowingVO showingVO = showingSvc.getOneShowing(SH_ID);
				showingSvc.deleteShowing(SH_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				MovieService movieSvc = new MovieService();
				if(requestURL.equals("/back_end/movie/listShowings_BymvId.jsp") || requestURL.equals("/back_end/movie/listAllMovie.jsp"))
					req.setAttribute("listShowings_BymvId",movieSvc.getShowingsBymvId(showingVO.getmvId())); // 資料庫取出的list物件,存入request
				
				if(requestURL.equals("/back_end/showing/listShowings_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<ShowingVO> list  = showingSvc.getAll(map);
					req.setAttribute("listShowings_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
		if ("listShowings_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				ShowingService showingSvc = new ShowingService();
				List<ShowingVO> list  = showingSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listShowings_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/showing/listShowings_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}	
		

		
		if ("getShowingByDate".equals(action)) { // 來自listAllShowing.jsp 或  /movie/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/showing/listAllShowing.jsp】 或  【/movie/listEmps_ByDeptno.jsp】 或 【 /movie/listAllDept.jsp】		
			
				/***************************1.接收請求參數****************************************/
			String SH_TIME = String.valueOf(req.getParameter("SH_TIME"));
			Integer mvId = Integer.valueOf(req.getParameter("mvId"));
				
				/***************************2.開始查詢資料****************************************/
				ShowingService showingSvc = new ShowingService();
				List<ShowingVO> list = showingSvc.getShowingByDate(SH_TIME);
				list.removeIf(e -> e.getmvId() != mvId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("list", list); // 資料庫取出的showingVO物件,存入req
				PrintWriter out = res.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(list));
		}
		
	}
}
