package com.ann.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.ann.model.*;
import com.emp_account.model.EmpAccountService;
import com.fd_inf.model.FdInfService;
import com.fd_inf.model.FdInfVO;



@WebServlet("/ann/ann.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class AnnServlet extends HttpServlet {

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
		res.setHeader("Content-Type", "text/html;charset=UTF-8"); // 靠19行通知瀏覽器
		PrintWriter out = res.getWriter();

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("ann_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("ann_no", "請輸入公告編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ann/allAnn.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer ann_no = null;
			try {
				ann_no = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("ann_no", "公告編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ann/allAnn.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			AnnService annSvc = new AnnService();
			AnnVO annVO = annSvc.getOneAnn(ann_no);
			if (annVO == null) {
				errorMsgs.put("ann_no", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ann/allAnn.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("annVO", annVO); // 資料庫取出的tkInfVO物件,存入req
			String url = "/back_end/ann/allAnn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 allTkInf.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自allTkInf.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer ann_no = Integer.valueOf(req.getParameter("ann_no").trim());

			/*************************** 2.開始查詢資料 ****************************************/
			AnnService annSvc = new AnnService();
			AnnVO annVO = annSvc.getOneAnn(ann_no);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("annVO", annVO);
			String url = "/back_end/ann/updateAnn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateTkInf.jsp
			successView.forward(req, res);
		}
		

		if ("update".equals(action)) { // 來自updateTkInf.jsp的請求

			//Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer ann_no = Integer.valueOf(req.getParameter("ann_no").trim());

			java.sql.Date ann_date = null;
			try {
				ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
			} catch (IllegalArgumentException e) {
				ann_date = new java.sql.Date(System.currentTimeMillis());
			}

			String ann_title = req.getParameter("ann_title");
			if (ann_title == null || ann_title.trim().length() == 0) {
				errorMsgs.add("公告標題: 請勿空白");
			}

			String ann_content = req.getParameter("ann_content").trim();
			
			
			String ann_picture=null;
			Part photo = req.getPart("ann_picture");
			
			if (photo.getSize() != 0) { // 如果照片不為空
				String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片
				
				// 利用File物件,寫入目地目錄,上傳成功
				String saveDirectory = "/ann_pic";
				String realPath = getServletContext().getRealPath(saveDirectory);
				photo.write(realPath + "\\" + fileName); // 是寫入硬碟的程式指令P.116 寫出照片路徑
				ann_picture = "/ann_pic/" + photo.getSubmittedFileName();
			} else {
				ann_picture = req.getParameter("noUpload");
			}
			System.out.println("photoName: "+ann_picture);
			
			AnnVO annVO = new AnnVO();
			annVO.setAnn_no(ann_no);
			annVO.setAnn_date(ann_date);
			annVO.setAnn_title(ann_title);
			annVO.setAnn_content(ann_content);
			annVO.setAnn_picture(ann_picture);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的newspostVO物件,也存入req
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ann/updateAnn.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}


			/*************************** 2.開始修改資料 *****************************************/
			AnnService annSvc = new AnnService();
			annVO = annSvc.updateAnn(ann_no, ann_date, ann_title, ann_content, ann_picture);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			//req.setAttribute("annVO", annVO); // 資料庫update成功後,正確的的tkTypeVO物件,存入req
			req.setAttribute("annVO", annSvc.getOneAnn(ann_no)); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/ann/allAnn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交allTkInf.jsp
			successView.forward(req, res);
			return;
		}

		if ("insert".equals(action)) { // 來自addTkInf.jsp的請求

			//List<String> errorMsgs = new LinkedList<String>();
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			//Integer ann_no = Integer.valueOf(req.getParameter("ann_no").trim());
			//AnnService annSvc = new AnnService();

			java.sql.Date ann_date = null;
			try {
				ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
			} catch (IllegalArgumentException e) {
				ann_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("ann_date","請輸入日期!!");
			}

			String ann_title = req.getParameter("ann_title");
			if (ann_title == null || ann_title.trim().length() == 0) {
				errorMsgs.put("ann_title","公告標題: 請勿空白");
			}

			String ann_content = req.getParameter("ann_content").trim();
			
			
//			   String ann_picture = req.getParameter("ann_picture");
//			   Part part = req.getPart("ann_picture");
//			   InputStream in = part.getInputStream();
//			   byte [] pic = new byte[in.available()];
//			   in.read(pic);
//			   in.close();
			
			
			
			
			// String photoName = "";
//			Part photo = req.getPart("myUpfile");
//			String ann_picture = "/ann_pic/" + photo.getSubmittedFileName(); //
//			//System.out.println(ann_picture);
//			if (photo.getSubmittedFileName() == null || photo.getSubmittedFileName().trim().length() == 0) {
//				errorMsgs.put("myUpfile","ann_picture 公告圖片請上傳");
//				
//			}
//			
//			String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片
//			// 利用File物件,寫入目地目錄,上傳成功
//			String saveDirectory = "/ann_pic";
//			String realPath = getServletContext().getRealPath(saveDirectory);
//			
//			InputStream in = getServletContext().getResourceAsStream("/ann_pic/123.jpg"); //輸入流獲取
			
			// String photoName = "";
			Part photo = req.getPart("ann_picture");
			String ann_picture = "/ann_pic/" + photo.getSubmittedFileName(); //
			System.out.println(ann_picture);
			if (photo.getSubmittedFileName() == null || photo.getSubmittedFileName().trim().length() == 0) {
				errorMsgs.put("ann_picture","活動圖片請上傳");
				
			}
			
			String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片
			// 利用File物件,寫入目地目錄,上傳成功
			String saveDirectory = "/ann_pic";
			String realPath = getServletContext().getRealPath(saveDirectory);
			
			InputStream in = getServletContext().getResourceAsStream("/ann_pic/annNoPhoto.jpg"); //輸入流獲取
			
			

			AnnVO annVO = new AnnVO();
			//annVO.setAnn_no(ann_no);
			annVO.setAnn_date(ann_date);
			annVO.setAnn_title(ann_title);
			annVO.setAnn_content(ann_content);
			annVO.setAnn_picture(ann_picture);
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的newspostVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/ann/addAnn.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			AnnService annSvc = new AnnService();
			annSvc.addAnn(ann_date, ann_title, ann_content, ann_picture);
			//annSvc.addAnn(ann_date, ann_title, ann_content, ann_picture);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/ann/allAnn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交allTkInf.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自allTkInf.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer ann_no = Integer.valueOf(req.getParameter("ann_no"));

			/*************************** 2.開始刪除資料 ***************************************/
			AnnService annSvc = new AnnService();
			annSvc.deleteAnn(ann_no);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/ann/allAnn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
