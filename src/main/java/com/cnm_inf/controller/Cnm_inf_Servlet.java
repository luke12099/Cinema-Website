package com.cnm_inf.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.RepaintManager;

import com.cnm_inf.model.*;
import com.google.gson.Gson;


@WebServlet("/cnm_inf/cnm_inf.do")
public class Cnm_inf_Servlet  extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page_cnm_inf.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer CNM_INF_ID = Integer.valueOf(req.getParameter("CNM_INF_ID"));
				
				/***************************2.開始查詢資料*****************************************/
				Cnm_infService cnm_infSvc = new Cnm_infService();
				Cnm_infVO cnm_infVO = cnm_infSvc.getOneCnm_inf(CNM_INF_ID);
				if (cnm_infVO == null) {
					errorMsgs.add("查無資料");
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				res.setContentType("application/json; charset=UTF-8");
				req.setAttribute("cnm_infVO", cnm_infVO); // 資料庫取出的showingVO物件,存入req
				PrintWriter out = res.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(cnm_infVO));
				
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllCnm_inf.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer CNM_INF_ID = Integer.valueOf(req.getParameter("CNM_INF_ID"));
				
				/***************************2.開始查詢資料****************************************/
				Cnm_infService cnm_infSvc = new Cnm_infService();
				Cnm_infVO cnm_infVO = cnm_infSvc.getOneCnm_inf(CNM_INF_ID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("cnm_infVO", cnm_infVO);         // 資料庫取出的cnm_infVO物件,存入req
				String url = "/back/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer CNM_INF_ID = Integer.valueOf(req.getParameter("CNM_INF_ID").trim());
							
				String CNM_DT = req.getParameter("CNM_DT").trim();
				if (CNM_DT == null || CNM_DT.trim().length() == 0) {
					errorMsgs.add("影城敘述請勿空白");
				}	
				String CNM_TEL = req.getParameter("CNM_TEL").trim();
				if (CNM_TEL == null || CNM_TEL.trim().length() == 0) {
					errorMsgs.add("影城電話請勿空白");
				}	
				String CNM_EM = req.getParameter("CNM_EM").trim();
				if (CNM_EM == null || CNM_EM.trim().length() == 0) {
					errorMsgs.add("電子信箱請勿空白");
				}	
				String CNM_LC = req.getParameter("CNM_LC").trim();
				if (CNM_LC == null || CNM_LC.trim().length() == 0) {
					errorMsgs.add("影城地址請勿空白");
				}	
				String CNM_TRP = req.getParameter("CNM_TRP").trim();
				if (CNM_TRP == null || CNM_TRP.trim().length() == 0) {
					errorMsgs.add("交通資訊請勿空白");
				}	
				


				Cnm_infVO cnm_infVO = new Cnm_infVO();
				cnm_infVO.setCNM_INF_ID(CNM_INF_ID);
				cnm_infVO.setCNM_DT(CNM_DT);
				cnm_infVO.setCNM_TEL(CNM_TEL);
				cnm_infVO.setCNM_EM(CNM_EM);
				cnm_infVO.setCNM_LC(CNM_LC);
				cnm_infVO.setCNM_TRP(CNM_TRP);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cnm_infVO", cnm_infVO); // 含有輸入格式錯誤的cnm_infVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/update_cnm_inf_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Cnm_infService cnm_infSvc = new Cnm_infService();
				cnm_infVO = cnm_infSvc.updateCnm_inf(CNM_DT, CNM_TEL, CNM_EM, CNM_LC,CNM_TRP,CNM_INF_ID);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("cnm_infVO", cnm_infVO); // 資料庫取出的showingVO物件,存入req
				PrintWriter out = res.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(cnm_infVO));
		}

        if ("insert".equals(action)) { // 來自addCnm_inf.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String CNM_DT = req.getParameter("CNM_DT").trim();
			if (CNM_DT == null || CNM_DT.trim().length() == 0) {
				errorMsgs.add("影城敘述請勿空白");
			}	
			String CNM_TEL = req.getParameter("CNM_TEL").trim();
			if (CNM_TEL == null || CNM_TEL.trim().length() == 0) {
				errorMsgs.add("影城電話請勿空白");
			}	
			String CNM_EM = req.getParameter("CNM_EM").trim();
			if (CNM_EM == null || CNM_EM.trim().length() == 0) {
				errorMsgs.add("電子信箱請勿空白");
			}	
			String CNM_LC = req.getParameter("CNM_LC").trim();
			if (CNM_LC == null || CNM_LC.trim().length() == 0) {
				errorMsgs.add("影城地址請勿空白");
			}	
			String CNM_TRP = req.getParameter("CNM_TRP").trim();
			if (CNM_TRP == null || CNM_TRP.trim().length() == 0) {
				errorMsgs.add("交通資訊請勿空白");
			}	
			


			Cnm_infVO cnm_infVO = new Cnm_infVO();
			cnm_infVO.setCNM_DT(CNM_DT);
			cnm_infVO.setCNM_TEL(CNM_TEL);
			cnm_infVO.setCNM_EM(CNM_EM);
			cnm_infVO.setCNM_LC(CNM_LC);
			cnm_infVO.setCNM_TRP(CNM_TRP);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cnm_infVO", cnm_infVO); // 含有輸入格式錯誤的cnm_infVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Cnm_infService cnm_infSvc = new Cnm_infService();
				cnm_infVO = cnm_infSvc.addCnm_inf(CNM_DT, CNM_TEL, CNM_EM, CNM_LC, CNM_TRP);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("cnm_infVO", cnm_infVO); // 資料庫取出的showingVO物件,存入req
				PrintWriter out = res.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(cnm_infVO));			
		}
		
		
		if ("delete".equals(action)) { // 來自listAllCnm_inf.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer CNM_INF_ID = Integer.valueOf(req.getParameter("CNM_INF_ID"));
				
				/***************************2.開始刪除資料***************************************/
				Cnm_infService cnm_infSvc = new Cnm_infService();
				cnm_infSvc.deleteCnm_inf(CNM_INF_ID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back/listAllCnm_inf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
		if ("set".equals(action)) { // 來自select_page_cnm_inf.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer CNM_INF_ID = Integer.valueOf(req.getParameter("CNM_INF_ID"));
				if(CNM_INF_ID == null) {
					req.getServletContext().setAttribute("theater", 1);
				}else {
					req.getServletContext().setAttribute("theater", CNM_INF_ID);
				}
				
				
				
		}
		
	}
}