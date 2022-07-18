package com.faq.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.faq.model.*;

@WebServlet("/faq/faq.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class FaqServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			//Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("faq_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入常見問題編號");
				
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/faq/allFaq.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer faq_no = null;
			try {
				faq_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("常見問題編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/faq/allFaq.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FaqService faqSvc = new FaqService();
			FaqVO faqVO = faqSvc.getOneFaq(faq_no);
			if (faqVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/faq/allFaq.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqVO", faqVO); // 資料庫取出的faqVO物件,存入req
			String url = "/back_end/faq/allFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllFaq.jsp的請求
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer faq_no = Integer.valueOf(req.getParameter("faq_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			FaqService faqSvc = new FaqService();
			FaqVO faqVO = faqSvc.getOneFaq(faq_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("faqVO", faqVO);
			String url = "/back_end/faq/updateFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateTkInf.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_faq_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer faq_no = Integer.valueOf(req.getParameter("faq_no").trim());
			
			java.lang.Byte faq_class = java.lang.Byte.valueOf(req.getParameter("faq_class"));

			String faq_title = req.getParameter("faq_title").trim();
			if (faq_title == null || faq_title.trim().length() == 0) {
				errorMsgs.add("標題請勿空白");
			}
			
//			String faq_content = req.getParameter("faq_content").trim();
//			if (faq_content == null || faq_content.trim().length() == 0) {
//				errorMsgs.add("內容請勿空白");
//			}
			
			String faq_content = req.getParameter("editor1").trim();

			FaqVO faqVO = new FaqVO();
			faqVO.setFaq_no(faq_no);
			faqVO.setFaq_class(faq_class);
			faqVO.setFaq_title(faq_title);
			faqVO.setFaq_content(faq_content);
			


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/faq/updateFaq.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			FaqService faqSvc = new FaqService();
			faqVO = faqSvc.updateFaq(faq_no, faq_class, faq_title, faq_content);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("faqVO", faqVO); // 資料庫update成功後,正確的的faqVO物件,存入req
			String url = "/back_end/faq/allFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneFaq.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addFaq.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			//Integer faq_no = Integer.valueOf(req.getParameter("faq_no").trim());
			
			java.lang.Byte faq_class = java.lang.Byte.valueOf(req.getParameter("faq_class"));

			String faq_title = req.getParameter("faq_title").trim();
			if (faq_title == null || faq_title.trim().length() == 0) {
				errorMsgs.add("標題請勿空白");
			}
			
			
//			String faq_content = req.getParameter("faq_content").trim();
//			if (faq_content == null || faq_content.trim().length() == 0) {
//				errorMsgs.add("內容請勿空白");
//			}
			
			String faq_content = req.getParameter("editor1").trim();
			
			
			FaqVO faqVO = new FaqVO();
			//faqVO.setFaq_no(faq_no);
			faqVO.setFaq_class(faq_class);
			faqVO.setFaq_title(faq_title);
			faqVO.setFaq_content(faq_content);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("faqVO", faqVO); // 含有輸入格式錯誤的faqVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/faq/addFaq.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			FaqService faqSvc = new FaqService();
			faqVO = faqSvc.addFaq(faq_class, faq_title, faq_content);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/faq/allFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllFaq.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllFaq.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer faq_no = Integer.valueOf(req.getParameter("faq_no"));

			/*************************** 2.開始刪除資料 ***************************************/
			FaqService faqSvc = new FaqService();
			faqSvc.deleteFaq(faq_no);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/faq/allFaq.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
