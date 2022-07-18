package com.tk_ord.controller;


import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.actdt.model.ActdtService;
import com.actdt.model.ActdtVO;
import com.fd_inf.model.FdInfService;
import com.fd_inf.model.FdInfVO;
import com.fd_ord_dt.model.FdOrdDtVO;
import com.google.gson.Gson;
import com.hall.model.HallService;
import com.hall.model.HallVO;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;
import com.showing.model.*;
import com.tk_inf.model.TkInfService;
import com.tk_inf.model.TkInfVO;
import com.tk_ord.model.TkOrdVO;
import com.tk_ord_dt.model.TkOrdDtVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/tkOrd/tkOrd.do")
public class TkOrdServlet extends HttpServlet{
	
	
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		
	
		if ("listShowings_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			 String[] MV_ID = {req.getParameter("MV_ID")};
			 String[] SH_TIME = {req.getParameter("SH_TIME")};
			 String[] SH_ID = {req.getParameter("SH_ID")};

			 Map<String, String[]> map = new LinkedHashMap<String, String[]>();
			 map.put("MV_ID", MV_ID);
			 map.put("SH_TIME", SH_TIME);
			 map.put("SH_ID", SH_ID);
			 
			 ShowingService showingSvc = new ShowingService();
			 List<ShowingVO> list  = showingSvc.getAll(map);
			   
			 
			  PrintWriter out = res.getWriter();
			  Gson gson = new Gson();
			  out.print(gson.toJson(list));
		}
		
		if ("getShowingByDate".equals(action)) { // 來自listAllShowing.jsp 或  /movie/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			String SH_TIME = String.valueOf(req.getParameter("SH_TIME"));
				
			/***************************2.開始查詢資料****************************************/
			ShowingService showingSvc = new ShowingService();
			List<ShowingVO> list = showingSvc.getShowingByDate(SH_TIME);

								
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("list", list); // 資料庫取出的showingVO物件,存入req
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(list));
		}
		
			
		if ("findHallByhlId".equals(action)) { // 來自select_page.jsp的複合查詢請求

			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			
			HallService hallService = new HallService();

			HallVO hallVO = hallService.findByPrimaryKey(hlId);
			 PrintWriter out = res.getWriter();
			   Gson gson = new Gson();
			   out.print(gson.toJson(hallVO));
			   res.setCharacterEncoding("UTF-8");
			
		}
		
		if ("findDiscountPrice".equals(action)) { // 來自select_page.jsp的複合查詢請求

			
			Integer tkTypeID = Integer.valueOf(req.getParameter("tkTypeID"));
			
			
			TkInfService tkInfService = new TkInfService();
			
			TkInfVO tkInfVO = tkInfService.getOneTkInf(tkTypeID);
			
			ActdtService actdtService = new ActdtService();
				
			List<ActdtVO> list = actdtService.getAll();
			
			double sum = 99999;
			int id = 0;
			for(ActdtVO aActdt : list) {

				if ((aActdt.getTkTypeID() == tkTypeID) && (aActdt.getAct_status() == java.lang.Byte.valueOf("1"))) {

					
					double price = 0;
					price = tkInfVO.getTkPrice() * aActdt.getAct_discount() + aActdt.getAct_coupon();
					
					if(sum > price) {
						sum = price;
						id = aActdt.getAct_id();
					}
				}			
			}

			ActdtVO actdtVO = actdtService.findByPrimaryKey(id, tkTypeID);
			

			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(actdtVO));
			res.setCharacterEncoding("UTF-8");
			
		}
		
		
		
		if ("go_To_TicketSelect".equals(action)) { // 來自allFdInf.jsp的請求

			

			/*************************** 1.接收請求參數 ****************************************/
			Integer MV_ID = Integer.valueOf(req.getParameter("MV_ID"));
			Integer SH_ID = Integer.valueOf(req.getParameter("SH_ID"));
			
			Integer HL_ID = Integer.valueOf(req.getParameter("HL_ID"));
			
			
			/*************************** 2.開始查詢資料 ****************************************/
			MovieService movieSvc = new MovieService();
			MovieVO movieVO = movieSvc.findByPrimaryKey(MV_ID);


			ShowingService showingSvc = new ShowingService();
			ShowingVO showingVO = showingSvc.getOneShowing(SH_ID);
			
			HallService hallSvc = new HallService();
			HallVO hallVO = hallSvc.findByPrimaryKey(HL_ID);
			
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("movieVO", movieVO);
			session.setAttribute("OrderMovieVO", movieVO);
			
			req.setAttribute("showingVO", showingVO);
			session.setAttribute("OrderShowingVO", showingVO);
			
			req.setAttribute("hallVO", hallVO);
			session.setAttribute("OrderHallVO", hallVO);
			
			String url = "/back_end/tk_ord/chooseTk.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateFdInf.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("updateShowingByShowingID".equals(action)) { // 來自select_page.jsp的複合查詢請求
			

			
			
			Integer SH_ID = Integer.valueOf(req.getParameter("SH_ID"));
			String SH_SEAT_STATE = req.getParameter("SH_SEAT_STATE");
			
			ShowingService showingSvc = new ShowingService();

			ShowingVO showingVO = showingSvc.getOneShowing(SH_ID);
					

			Integer mvId = showingVO.getmvId();
			Integer HL_ID = showingVO.getHL_ID();
			Integer SH_STATE = showingVO.getSH_STATE();
			Timestamp SH_TIME = showingVO.getSH_TIME();
			Integer SH_TYPE = showingVO.getSH_TYPE();
			

			ShowingVO showingVO2 = showingSvc.updateShowing(SH_ID, mvId, HL_ID, SH_STATE, SH_SEAT_STATE, SH_TIME, SH_TYPE);
			
			
			
			PrintWriter out = res.getWriter();
			   Gson gson = new Gson();
			   out.print(gson.toJson(showingVO2));
			   res.setCharacterEncoding("UTF-8");
			
		}
		
		if ("findShowingByShowingID".equals(action)) { // 來自select_page.jsp的複合查詢請求
			

			
			
			Integer SH_ID = Integer.valueOf(req.getParameter("SH_ID"));
			
			ShowingService showingSvc = new ShowingService();

			ShowingVO showingVO = showingSvc.getOneShowing(SH_ID);
					

		
			
			PrintWriter out = res.getWriter();
			   Gson gson = new Gson();
			   out.print(gson.toJson(showingVO));
			   res.setCharacterEncoding("UTF-8");
			
		}
		
	}				
	
}
