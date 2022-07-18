package com.fd_inf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.fd_inf.model.FdInfService;
import com.fd_inf.model.FdInfVO;

//import org.json.JSONException;
//import org.json.JSONObject;

@WebServlet("/fd_inf/fd_inf.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class FdInfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FdInfServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("fdID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("fdID", "請輸入餐飲編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/fd_inf/allFdInf.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer fdID = null;
			try {
				fdID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("fdID", "餐飲編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/fd_inf/allFdInf.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			FdInfService fdInfSvc = new FdInfService();
			FdInfVO fdInfVO = fdInfSvc.getOneFdInf(fdID);
			if (fdInfVO == null) {
				errorMsgs.put("fdID", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/fd_inf/allFdInf.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("fdInfVO", fdInfVO); // 資料庫取出的fdInfVO物件,存入req
			String url = "/back_end/fd_inf/allFdInf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 allFdInf.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自allFdInf.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer fdID = Integer.valueOf(req.getParameter("fdID"));

			/*************************** 2.開始查詢資料 ****************************************/
			FdInfService fdInfSvc = new FdInfService();
			FdInfVO fdInfVO = fdInfSvc.getOneFdInf(fdID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("fdInfVO", fdInfVO);


			String url = "/back_end/fd_inf/updateFdInf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updateFdInf.jsp
			successView.forward(req, res);
		}
		if ("getPic".equals(action)) {

			Integer fdID = Integer.valueOf(req.getParameter("fdID").trim());
			FdInfService fdInfSvc = new FdInfService();
			FdInfVO fdInfVO = fdInfSvc.getOneFdInf(fdID);
			byte[] fdPicture = fdInfVO.getFdPicture();
			if (fdPicture.length != 0) {
				
				res.getOutputStream().write(fdPicture);
			}else {
				InputStream in = getServletContext().getResourceAsStream("/back_end/fd_inf/imges/123.jpg");
			    byte[] b = new byte[in.available()];
			    in.read(b);
			    res.getOutputStream().write(b);
			    in.close();
			}
		}

		if ("update".equals(action)) { // 來自updateFdInf.jsp的請求
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer fdID = Integer.valueOf(req.getParameter("fdID").trim());

			java.lang.Byte fdType = java.lang.Byte.valueOf(req.getParameter("fdType"));

			String fdName = req.getParameter("fdName");
			String fdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (fdName == null || fdName.trim().length() == 0) {
				errorMsgs.put("fdName", "餐飲名稱: 請勿空白");
			} else if (!fdName.trim().matches(fdNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("fdName", "餐飲名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
			}

			Integer fdprice = null;
			try {
				fdprice = Integer.valueOf(req.getParameter("fdprice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("fdprice", "價格請填數字");
			}

			String fdDT = req.getParameter("fdDT").trim();


			byte[] fdPicture = req.getPart("fdPicture").getInputStream().readAllBytes();
			if (fdPicture.length == 0) {
				FdInfService fdInfSvc = new FdInfService();
				FdInfVO fdInfVO = fdInfSvc.getOneFdInf(fdID);
				fdPicture = fdInfVO.getFdPicture();
			}


			java.lang.Byte fdState = java.lang.Byte.valueOf(req.getParameter("fdState"));

			FdInfVO fdInfVO = new FdInfVO();
			fdInfVO.setFdID(fdID);
			fdInfVO.setFdType(fdType);
			fdInfVO.setFdName(fdName);
			fdInfVO.setFdprice(fdprice);
			fdInfVO.setFdDT(fdDT);
			fdInfVO.setFdPicture(fdPicture);
			fdInfVO.setFdState(fdState);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("fdInfVO", fdInfVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/fd_inf/updateFdInf.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			FdInfService fdInfSvc = new FdInfService();
			fdInfVO = fdInfSvc.updateFdInf(fdID, fdType, fdName, fdprice, fdDT, fdPicture, fdState);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("fdInfVO", fdInfVO); // 資料庫update成功後,正確的的fdInfVO物件,存入req
			String url = "/back_end/fd_inf/allFdInf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交allFdInf.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addFdInf.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			java.lang.Byte fdType = java.lang.Byte.valueOf(req.getParameter("fdType"));

			String fdName = req.getParameter("fdName");
			String fdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
			if (fdName == null || fdName.trim().length() == 0) {
				errorMsgs.put("fdName", "餐飲名稱: 請勿空白");
			} else if (!fdName.trim().matches(fdNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("fdName", "餐飲名稱: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
			}

			Integer fdprice = null;
			try {
				fdprice = Integer.valueOf(req.getParameter("fdprice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("fdprice", "價格請填數字");
			}

			String fdDT = req.getParameter("fdDT").trim();

			Part part = req.getPart("fdPicture");
			InputStream in = part.getInputStream();
			byte[] fdPicture = new byte[in.available()];
			in.read(fdPicture);
			in.close();

			java.lang.Byte fdState = java.lang.Byte.valueOf(req.getParameter("fdState"));

			FdInfVO fdInfVO = new FdInfVO();
			fdInfVO.setFdType(fdType);
			fdInfVO.setFdName(fdName);
			fdInfVO.setFdprice(fdprice);
			fdInfVO.setFdDT(fdDT);
			fdInfVO.setFdPicture(fdPicture);
			fdInfVO.setFdState(fdState);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("fdInfVO", fdInfVO); // 含有輸入格式錯誤的VO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/fd_inf/addFdInf.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			FdInfService fdInfSvc = new FdInfService();
			fdInfSvc.addFdInf(fdType, fdName, fdprice, fdDT, fdPicture, fdState);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/fd_inf/allFdInf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交allFdInf.jsp
			successView.forward(req, res);
			

		
		}

		if ("delete".equals(action)) { // 來自allFdInf.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer fdID = Integer.valueOf(req.getParameter("fdID"));

			/*************************** 2.開始刪除資料 ***************************************/
			FdInfService fdInfSvc = new FdInfService();
			FdInfVO fdInfVO = fdInfSvc.getOneFdInf(fdID);
			fdInfSvc.deleteFdInf(fdID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/fd_inf/allFdInf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("updateStatus".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = res.getWriter();

			/*************************** 1.接收請求參數 ***************************************/
			Integer fdID = Integer.valueOf(req.getParameter("fdID"));

			/*************************** 2.開始修改資料 ***************************************/
			FdInfService fdInfSvc = new FdInfService();
			fdInfSvc.onOrDownFoodStatus(fdID);
			FdInfVO fdInfVO = fdInfSvc.getOneFdInf(fdID);

			// 將最新的狀態丟回去
			java.lang.Byte newStatus = fdInfVO.getFdState();
			newStatus.toString();
			JSONObject jsonobj = new JSONObject();
			try {
				jsonobj.put("newStatus", newStatus);
				out.print(jsonobj.toString());
				return;
			}catch(JSONException e) {
				e.printStackTrace();
			}finally {
				out.flush();
				out.close();
			}

			/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/fd_inf/allFdinf.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		
	}

}
