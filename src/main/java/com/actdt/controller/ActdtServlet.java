package com.actdt.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.actdt.model.*;

@WebServlet("/act/act.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class ActdtServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("null")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//
//			Integer act_id = null;
//			try {
//				act_id = Integer.valueOf(act_id);
//			} catch (Exception e) {
//				errorMsgs.put("act_id", "活動方案編號格式不正確");
//			}
//			
////			Integer tkTypeID = null;
////			try {
////				tkTypeID = Integer.valueOf(tkTypeID);
////			} catch (Exception e) {
////				errorMsgs.put("act_id", "票種編號格式不正確");
////			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/act/allAct.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			ActdtService actdtSvc = new ActdtService();
//			ActdtVO actdtVO = actdtSvc.findOneActdt(act_id);
////			ActdtVO actdtVO = actdtSvc.findOneActdt();
////			List<ActdtVO> actdtVO = actdtSvc.findOneActdt();
//			if (actdtVO == null) {
//				errorMsgs.put("act_id", "查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/act/allAct.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("actdtVO", actdtVO); // 資料庫取出的empVO物件,存入req
//			String url = "/back_end/act/allAct.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			List<ActdtVO> list = new ArrayList<ActdtVO>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer act_id = Integer.valueOf(req.getParameter("act_id"));
			//Integer tkTypeID = Integer.valueOf(req.getParameter("tkTypeID"));

			/*************************** 2.開始查詢資料 ****************************************/
			ActdtService actdtSvc = new ActdtService();
//			ActdtVO actdtVO = actdtSvc.findByPrimaryKey(act_id, tkTypeID);
			list = actdtSvc.findOneActdt(act_id);
			ActdtVO actdtVO = null;
			for(ActdtVO actdt: list) {
				System.out.println("123");
				actdtVO = actdt;
			}
//			List<ActdtVO> actdtVO = actdtSvc.findOneActdt();

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("actdtVO", actdtVO);
			req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/act/updateAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String act_idstr = req.getParameter("act_id");
			System.out.println(act_idstr);
			Integer act_id = null;

			if (act_idstr == null || (act_idstr.trim()).length() == 0) {
			    errorMsgs.put("act_id", "請輸入活動編號");
			   } else {
			    try {
			    	act_id = Integer.valueOf(act_idstr);
			    } catch (NumberFormatException e) {
			     e.printStackTrace();
			     errorMsgs.put("act_id", "活動編號格式不正確");
			    }
			   }
			
			
			
			java.sql.Date act_date_start = null;
			try {
				act_date_start = java.sql.Date.valueOf(req.getParameter("act_date_start").trim());
			} catch (IllegalArgumentException e) {
				act_date_start = new java.sql.Date(System.currentTimeMillis());
			}
			
			String act_title = req.getParameter("act_title");
			if (act_title == null || act_title.trim().length() == 0) {
				errorMsgs.put("act_title","活動方案標題: 請勿空白");
			}
			
			String act_subtitle = req.getParameter("act_subtitle");
			if (act_subtitle == null || act_subtitle.trim().length() == 0) {
				errorMsgs.put("act_subtitle","活動方案副標題: 請勿空白");
			}

//			Integer tkTypeID = Integer.valueOf(req.getParameter("tk_type_id").trim());
//			Integer TkTypeID = Integer.valueOf(req.getParameter("TkTypeID").trim());
//			//新增票種 用 checkbox → foreach
//			String[] TkTypeIDStr = req.getParameterValues("TkTypeID");  //input type以陣列的方式取回,所以可以宣告一個String的陣列來承接
//			if ( TkTypeIDStr != null) { 
//				//前端的使用者,如果沒打勾的話，request.getParameterValues("tk_type_idStr")會接收到null值    
//				int size =java.lang.reflect.Array.getLength(TkTypeIDStr);    
//				//取得這個陣列大小    
//				  for (int i=0;i<size;i++)    
//				  {    
//				    System.out.println(TkTypeIDStr[i]+"<br>");    
//				  }    
//				//利用一個for迴圈將陣列資料取出    
//				}  


			String act_discountstr = req.getParameter("act_discount");
			Double act_discount = null;

			if (act_discountstr == null || (act_discountstr.trim()).length() == 0) {
			    errorMsgs.put("act_discount", "活動折扣請輸入0<?<1");
			   } else {
			    try {
			    	act_discount = Double.valueOf(act_discountstr);
			    } catch (NumberFormatException e) {
			     e.printStackTrace();
			     errorMsgs.put("act_discount", "活動折扣格式不正確");
			    }
			   }

			
			String act_couponstr = req.getParameter("act_coupon");
			Integer act_coupon = null;

			if (act_couponstr == null || (act_couponstr.trim()).length() == 0) {
			    errorMsgs.put("act_coupon", "活動折價請輸入：-數字");
			   } else {
			    try {
			    	act_coupon = Integer.valueOf(act_couponstr);
			    } catch (NumberFormatException e) {
			     e.printStackTrace();
			     errorMsgs.put("act_coupon", "活動折價格式不正確");
			    }
			   }
			
			java.lang.Byte act_status = java.lang.Byte.valueOf(req.getParameter("act_status"));
			
			String act_content = req.getParameter("act_content").trim();
			
//			Part part = req.getPart("act_picture");
//			byte[] act_picture = null;
//			InputStream in = null;
//			if(part.getSize() == 0) {
//				ActdtService actSvc = new ActdtService();
//				act_picture = actSvc.getOneActdt(act_id,tkTypeID).getAct_picture();
//			} else {
//				in = part.getInputStream();
//				act_picture = new byte[in.available()];
//				in.read(act_picture);
//				in.close();
//			}
			
			String act_picture=null;
			Part photo = req.getPart("act_picture");
			
			if (photo.getSize() != 0) { // 如果照片不為空
				String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片
				
				// 利用File物件,寫入目地目錄,上傳成功
				String saveDirectory = "/act_pic/";
				String realPath = getServletContext().getRealPath(saveDirectory);
				photo.write(realPath + "\\" + fileName); // 是寫入硬碟的程式指令P.116 寫出照片路徑
				act_picture = "/act_pic/" + photo.getSubmittedFileName();
			} else {
				act_picture = req.getParameter("noUpload");
			}
			//System.out.println("photoName: "+act_picture);
			
			ActdtVO actdtVO = new ActdtVO();
			actdtVO.setAct_id(act_id);
			actdtVO.setAct_date_start(act_date_start);
			actdtVO.setAct_title(act_title);
			actdtVO.setAct_subtitle(act_subtitle);
//			actdtVO.setTkTypeID(tkTypeID);
			actdtVO.setAct_discount(act_discount);
			actdtVO.setAct_coupon(act_coupon);
			actdtVO.setAct_status(act_status);
			actdtVO.setAct_content(act_content);
			actdtVO.setAct_picture(act_picture);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/act/updateAct.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ActdtService actdtSvc = new ActdtService();
			//actdtVO = actdtSvc.update(act_id,act_date_start, act_title, act_subtitle, tkTypeID, act_discount, act_coupon, act_status, act_content, act_picture);
			//actdtVO = actdtSvc.update(act_id,act_date_start, act_title, act_subtitle, act_discount, act_coupon, act_status, act_content, act_picture);

			
		
			String[] TkTypeIDStr = req.getParameterValues("TkTypeID");  //input type以陣列的方式取回,所以可以宣告一個String的陣列來承接
			if ( TkTypeIDStr != null) { 
				int size = TkTypeIDStr.length;
				  for (int i=0;i<size;i++)    
				  {    
//				    System.out.println(TkTypeIDStr[i]+"<br>");
//				    actdtVO.setTkTypeID(Integer.valueOf(TkTypeIDStr[i]));
				    actdtSvc.update(act_id,act_date_start, act_title, act_subtitle, Integer.valueOf(TkTypeIDStr[i]), act_discount, act_coupon, act_status, act_content, act_picture);
				   
				  }    
				}   
			
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actdtVO", actdtVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/act/allAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String act_idstr = req.getParameter("act_id");
			Integer act_id = null;
			if (act_idstr == null || (act_idstr.trim()).length() == 0) {
			    errorMsgs.put("act_id", "請輸入活動編號");
			   } else {
			    try {
			    	act_id = Integer.valueOf(act_idstr);
			    } catch (NumberFormatException e) {
			     e.printStackTrace();
			     errorMsgs.put("act_id", "活動編號格式不正確");
			    }
			   }
		
			java.sql.Date act_date_start = null;
			try {
				act_date_start = java.sql.Date.valueOf(req.getParameter("act_date_start").trim());
			} catch (IllegalArgumentException e) {
				act_date_start = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.put("act_date_start","請輸入日期!");
			}
			
			String act_title = req.getParameter("act_title");
			
			String act_subtitle = req.getParameter("act_subtitle");
			if (act_subtitle == null || act_subtitle.trim().length() == 0) {
				errorMsgs.put("act_subtitle","活動方案副標題: 請勿空白");
			}

			
			
			
			
//			Integer tk_type_id = null;
//			for(String id:tk_type_idStr){
//				Integer ids = Integer.valueOf(id);
//				ActdtService actdtSvc = new ActdtService();
//				ActdtVO actdtVO = actdtSvc.getActdtVO(ids);
//				
////				List.add(ids, id);
//				List.add(1, id);
//				List.add(2, id);
//				List.add(3, id);
//				List.add(4, id);
//				List.add(5, id);
//				List.add(6, id);
//				
//			}
////			req.getParameter("name")[0]
//			List<e> list = new LinkedList<E>();
////			for(int i = 0; i < req.getParameterValues("tk_type_id").length; i++)
//			for(String id : xxStrings) {
//				Integer ids = Integer.valueOf(id);
//				ActdtService actdtSvc = new ActdtService();
//				ActdtVO actdtVO = actdtSvc.getOneActdt(ids);
//				
//				list.add(Actdt);
//			}
			
			
			
			
			

			String act_discountstr = req.getParameter("act_discount");
			Double act_discount = null;

			if (act_discountstr == null || (act_discountstr.trim()).length() == 0) {
			    errorMsgs.put("act_discount", "活動折扣請輸入0<?<1");
			   } else {
			    try {
			    	act_discount = Double.valueOf(act_discountstr);
			    } catch (NumberFormatException e) {
			     e.printStackTrace();
			     errorMsgs.put("act_discount", "活動折扣格式不正確");
			    }
			   }

			String act_couponstr = req.getParameter("act_coupon");
			Integer act_coupon = null;

			if (act_couponstr == null || (act_couponstr.trim()).length() == 0) {
			    errorMsgs.put("act_coupon", "活動折價請輸入：數字");
			   } else {
			    try {
			    	act_coupon = Integer.valueOf(act_couponstr);
			    } catch (NumberFormatException e) {
			     e.printStackTrace();
			     errorMsgs.put("act_coupon", "活動折價格式不正確");
			    }
			   }
			
			java.lang.Byte act_status = java.lang.Byte.valueOf(req.getParameter("act_status"));
			
			String act_content = req.getParameter("act_content").trim();
			
			// String photoName = "";
			Part photo = req.getPart("act_picture");
			String act_picture = "/act_pic/" + photo.getSubmittedFileName(); //
			System.out.println(act_picture);
			if (photo.getSubmittedFileName() == null || photo.getSubmittedFileName().trim().length() == 0) {
				errorMsgs.put("act_picture","act_picture 活動圖片請上傳");
				
			}
			
			String fileName = photo.getSubmittedFileName(); // 先宣告一個檔案變數 並取得照片
			// 利用File物件,寫入目地目錄,上傳成功
			String saveDirectory = "/act_pic";
			String realPath = getServletContext().getRealPath(saveDirectory);
			
			InputStream in = getServletContext().getResourceAsStream("/act_pic/123.jpg"); //輸入流獲取


			
			
			ActdtVO actdtVO = new ActdtVO();
			actdtVO.setAct_id(act_id);
			actdtVO.setAct_date_start(act_date_start);
			actdtVO.setAct_title(act_title);
			actdtVO.setAct_subtitle(act_subtitle);
			actdtVO.setAct_discount(act_discount);
			actdtVO.setAct_coupon(act_coupon);
			actdtVO.setAct_status(act_status);
			actdtVO.setAct_content(act_content);
			actdtVO.setAct_picture(act_picture);
			
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/act/addAct.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*************************** 2.開始新增資料 ***************************************/
			ActdtService actdtSvc = new ActdtService();

			String[] TkTypeIDStr = req.getParameterValues("TkTypeID");  //input type以陣列的方式取回,所以可以宣告一個String的陣列來承接
			if ( TkTypeIDStr != null) { 
				int size = TkTypeIDStr.length;
				  for (int i=0;i<size;i++)    
				  {    
//				    System.out.println(TkTypeIDStr[i]+"<br>");
//				    actdtVO.setTkTypeID(Integer.valueOf(TkTypeIDStr[i]));
				    actdtSvc.insert(act_id,act_date_start, act_title, act_subtitle, Integer.valueOf(TkTypeIDStr[i]), act_discount, act_coupon, act_status, act_content, act_picture);
				   
				  }    
				}    

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/act/allAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer act_id = Integer.valueOf(req.getParameter("act_id"));
			//Integer tkTypeID = Integer.valueOf(req.getParameter("tkTypeID"));

			/*************************** 2.開始刪除資料 ***************************************/
			ActdtService actdtSvc = new ActdtService();
//			actdtSvc.delete(act_id, tkTypeID);
			actdtSvc.delete(act_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/act/allAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		
	
	}
}
