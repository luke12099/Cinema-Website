package com.tk_inf.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.tk_inf.model.*;

@WebServlet("/tk_inf/tk_inf.do")
public class TkInfServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("tkTypeID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("tkTypeID","請輸入票種編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/tk_inf/allTkInf.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer tkTypeID = null;
				try {
					tkTypeID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("tkTypeID","票種編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req		
							.getRequestDispatcher("/back_end/tk_inf/allTkInf.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TkInfService tkInfSvc = new TkInfService();
				TkInfVO tkInfVO = tkInfSvc.getOneTkInf(tkTypeID);
				if (tkInfVO == null) {
					errorMsgs.put("tkTypeID","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/tk_inf/allTkInf.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tkInfVO", tkInfVO); // 資料庫取出的tkInfVO物件,存入req
				String url = "/back_end/tk_inf/allTkInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 allTkInf.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自allTkInf.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer tkTypeID = Integer.valueOf(req.getParameter("tkTypeID"));
				
				/***************************2.開始查詢資料****************************************/
				TkInfService tkInfSvc = new TkInfService();
				TkInfVO tkInfVO = tkInfSvc.getOneTkInf(tkTypeID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?tkTypeID="  +tkInfVO.getTkTypeID()+
						       "&tkType="  +tkInfVO.getTkType()+
						       "&tkPrice="    +tkInfVO.getTkPrice()+
						       "&tkDI="+tkInfVO.getTkDI()+
						       "&tkTypeDT="    +tkInfVO.getTkTypeDT();
				String url = "/back_end/tk_inf/updateTkInf.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateTkInf.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自updateTkInf.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer tkTypeID = Integer.valueOf(req.getParameter("tkTypeID").trim());
				
				String tkType = req.getParameter("tkType");
				String tkTypeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (tkType == null || tkType.trim().length() == 0) {
					errorMsgs.put("tkType","票種名稱: 請勿空白");
				} else if(!tkType.trim().matches(tkTypeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("tkType","票種名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Integer tkPrice = null;
				try {
					tkPrice = Integer.valueOf(req.getParameter("tkPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tkPrice","票價請填數字");
				}
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/tk_inf/updateTkInf.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				java.lang.Byte tkDI = java.lang.Byte.valueOf(req.getParameter("tkDI"));
				
				String tkTypeDT = req.getParameter("tkTypeDT").trim();
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/tk_inf/updateTkInf.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TkInfService tkInfSvc = new TkInfService();
				TkInfVO tkInfVO = tkInfSvc.updateTkInf(tkTypeID, tkType, tkPrice, tkDI, tkTypeDT);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("tkInfVO", tkInfVO); // 資料庫update成功後,正確的的tkTypeVO物件,存入req
				String url = "/back_end/tk_inf/allTkInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交allTkInf.jsp
				successView.forward(req, res);
			}

        if ("insert".equals(action)) { // 來自addTkInf.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String tkType = req.getParameter("tkType");
				String tkTypeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (tkType == null || tkType.trim().length() == 0) {
					errorMsgs.put("tkType","票種名稱: 請勿空白");
				} else if(!tkType.trim().matches(tkTypeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("tkType","票種名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
			
				Integer tkPrice = null;
				try {
					tkPrice = Integer.valueOf(req.getParameter("tkPrice").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("tkPrice","票價請填數字");
				}
				java.lang.Byte tkDI = java.lang.Byte.valueOf(req.getParameter("tkDI"));
				
				String tkTypeDT = req.getParameter("tkTypeDT").trim();
				


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/tk_inf/addTkInf.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				TkInfService tkInfSvc = new TkInfService();
				tkInfSvc.addTkInf(tkType, tkPrice, tkDI, tkTypeDT);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/tk_inf/allTkInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交allTkInf.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自allTkInf.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer tkTypeID = Integer.valueOf(req.getParameter("tkTypeID"));
				
				/***************************2.開始刪除資料***************************************/
				TkInfService tkInfSvc = new TkInfService();
				tkInfSvc.deleteTkInf(tkTypeID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/tk_inf/allTkInf.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
	}
}
