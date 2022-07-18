package com.merchandise_inf.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import com.google.gson.Gson;
import com.merchandise_inf.model.MerchService;
import com.merchandise_inf.model.MerchVO;

@WebServlet("/merch/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MerchServlet() {
		super();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
//		System.out.println(req.getParameter("action"));
//		System.out.println(req.getReader().readLine());
//		res.getWriter().println(action); 測試是否有收到
//		res.getWriter().println("測試是否有進此畫面");
		if ("addphoto".equals(action)) {
			for (int x = 1; x < 999; x++) {
				String dir = "/C:\\CGA102_MOVIE\\CGA102G1\\src\\main\\webapp\\merch_pic\\photo\\" + x;
				File dirs = new File(dir);
				if (!dirs.exists()) {
					break;
				}
				for (int i = 1; i < 6; i++) {
					String url = "/C:\\CGA102_MOVIE\\CGA102G1\\src\\main\\webapp\\merch_pic\\photo\\" + x + "\\" + i + ".png";
					File photo = new File(url);
					System.out.println(photo.getAbsolutePath());
					if (!photo.exists()) {
						break;
					}
					FileInputStream fis = new FileInputStream(photo);
					byte[] byteArray = new byte[fis.available()];
					fis.read(byteArray);
					Blob blob = null;
					try {
						blob = new SerialBlob(byteArray);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					MerchService merchSvc = new MerchService();
					MerchVO merchVo = merchSvc.getOneMerch(x);
					if (blob != null) {
						switch (i) {
						case 1:
							merchVo.setMerchPic1(blob);
							break;
						case 2:
							merchVo.setMerchPic2(blob);
							break;
						case 3:
							merchVo.setMerchPic3(blob);
							break;
						case 4:
							merchVo.setMerchPic4(blob);
							break;
						case 5:
							merchVo.setMerchPic5(blob);
							break;
						default:
							break;
						}
					}
					merchSvc.updateMerch(merchVo);
					fis.close();
				}
			}
			String url = "/back_end/merchandise/mallIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getAll_For_Display".equals(action)) {
			MerchService merchSvc = new MerchService();
			List<MerchVO> list = merchSvc.getAll();
			HttpSession session = req.getSession();
			session.setAttribute("merchlist", list);
			String url = "/back_end/merchandise/merchlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getOne_For_Display".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MerchVO merchVo = null;
			List<MerchVO> list = null;
			/* ====================================1.接收請求參數=========================== */
			try {
				Integer merchID = Integer.valueOf(req.getParameter("merchID").trim());
				/* ====================================2.開始查詢資料=========================== */
				MerchService merchSvc = new MerchService();
				merchVo = merchSvc.getOneMerch(merchID);
			} catch (NumberFormatException e) {
				String merchName = req.getParameter("merchID").trim();
				MerchService merchSvc = new MerchService();
				list = merchSvc.getByName(merchName);
			}
			if (merchVo == null && list == null) {
				errorMsgs.put("merchID", "查無資料!!!");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/merchandise/mallIndex.jsp");
				failureView.forward(req, res);
				return;
			}
			/* ====================================3.查詢完成,準備轉交=========================== */
			if (list == null) {
				req.setAttribute("merchVo", merchVo);
				String url = "/back_end/merchandise/merchonelist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("merchlist", list);
				String url = "/back_end/merchandise/merchlist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		}

		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* ====================================1.接收請求參數=========================== */
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));
			/* ====================================2.開始查詢資料=========================== */
			MerchService merchSvc = new MerchService();
			MerchVO merchVo = merchSvc.getOneMerch(merchID);
//			req.setAttribute("merchVo", merchVo);
			/* ====================================3.查詢完成,準備轉交=========================== */
			String param = "?merchID=" + merchVo.getMerchID() + "&merchName=" + merchVo.getMerchName() + "&merchDT="
					+ merchVo.getMerchDT() + "&merchDate=" + merchVo.getMerchDate() + "&merchPrice="
					+ merchVo.getMerchPrice() + "&merchClass=" + merchVo.getMerchClass() + "&soldTotal="
					+ merchVo.getSoldTotal() + "&merchStatus=" + merchVo.getMerchStatus() + "&merchStock="
					+ merchVo.getMerchStock();
			String url = "/back_end/merchandise/merchupdate.jsp" + param;
//			String url = "/back_end/merchandise/merchupdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getPic".equals(action)) {
			res.setContentType("image/png;charset=UTF-8");
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));
			MerchService merchSvc = new MerchService();
			MerchVO merchVo = merchSvc.getOneMerch(merchID);
			Blob blob = null;
			String pic = req.getParameter("pic");
			switch (pic) {
			case "1":
				blob = merchVo.getMerchPic1();
				break;
			case "2":
				blob = merchVo.getMerchPic2();
				break;
			case "3":
				blob = merchVo.getMerchPic3();
				break;
			case "4":
				blob = merchVo.getMerchPic4();
				break;
			case "5":
				blob = merchVo.getMerchPic5();
				break;
			default:
				System.out.println("無圖片");
			}
			try {
				byte[] bb = blob.getBytes(1, (int) blob.length());
				res.getOutputStream().write(bb);
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/back_end/merchandise/images/noimage.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				res.getOutputStream().write(b);
				in.close();
			}

		}

		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* ========================接受請求參數============================== */
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));

			String merchName = req.getParameter("merchName");
			if (merchName == null || merchName.trim().length() == 0) {
				errorMsgs.put("merchName", "請輸入商品名稱");
			}
			String merchDT = req.getParameter("merchDT");
			if (merchDT == null || merchDT.trim().length() == 0) {
				errorMsgs.put("merchDT", "請輸入商品描述");
			}
			java.sql.Timestamp merchDate = null;
			MerchServlet merchSvt = new MerchServlet();
			MerchService merSvc = new MerchService();
			Blob merchPic1 = null;
			Blob merchPic2 = null;
			Blob merchPic3 = null;
			Blob merchPic4 = null;
			Blob merchPic5 = null;
//			Blob[] merchPicArray = {merchPic1, merchPic2, merchPic3, merchPic4, merchPic5};
//			for(int i = 1; i <= 5; i++) {
//				String pic = "merchPic" + i;
//				if(req.getPart(pic).getSize()!=0) {
//					merchPicArray[i-1] = merSvc.getOneMerch(req.getPart(pic));
//				}else {
//					merchPicArray[i-1] = merSvc.getOneMerch(merchID).getMerchPic();
//				}
//			}

			if (req.getPart("merchPic1").getSize() != 0) {
				merchPic1 = merchSvt.insertPicToDB(req.getPart("merchPic1"));
			} else {
				merchPic1 = merSvc.getOneMerch(merchID).getMerchPic1();
			}
			if (req.getPart("merchPic2").getSize() != 0) {
				merchPic2 = merchSvt.insertPicToDB(req.getPart("merchPic2"));
			} else {
				merchPic2 = merSvc.getOneMerch(merchID).getMerchPic2();
			}
			if (req.getPart("merchPic3").getSize() != 0) {
				merchPic3 = merchSvt.insertPicToDB(req.getPart("merchPic3"));
			} else {
				merchPic3 = merSvc.getOneMerch(merchID).getMerchPic3();
			}
			if (req.getPart("merchPic4").getSize() != 0) {
				merchPic4 = merchSvt.insertPicToDB(req.getPart("merchPic4"));
			} else {
				merchPic4 = merSvc.getOneMerch(merchID).getMerchPic4();
			}
			if (req.getPart("merchPic5").getSize() != 0) {
				merchPic5 = merchSvt.insertPicToDB(req.getPart("merchPic5"));
			} else {
				merchPic5 = merSvc.getOneMerch(merchID).getMerchPic5();
			}
//			try {
//				merchDate = java.sql.Timestamp.valueOf(req.getParameter("merchDate"));
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("merchDate", "請輸入日期");
//			}
			Double merchPrice = null;
			try {
				merchPrice = Double.valueOf(req.getParameter("merchPrice"));
				if (merchPrice == null || merchPrice <= 0) {
					merchPrice = 0d;
					errorMsgs.put("merchPrice", "價格異常!");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("merchPrice", "請輸入正確數字");
			}
			String merchClass = req.getParameter("merchClass");
			Integer soldTotal = null;
			try {
				soldTotal = Integer.valueOf(req.getParameter("soldTotal"));
				if (soldTotal < 0)
					errorMsgs.put("soldTotal", "銷量異常!");
			} catch (NumberFormatException e) {
				errorMsgs.put("soldTotal", "總銷售請輸入數字");
			}
			Byte merchStatus = Byte.valueOf(req.getParameter("merchStatus"));
			Integer merchStock = null;
			try {
				merchStock = Integer.valueOf(req.getParameter("merchStock"));
				if (merchStock == null || merchStock < 0) {
					errorMsgs.put("merchStock", "請輸入正確的庫存數");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("merchStock", "庫存請輸入數字");
			}
			MerchVO merchVo = new MerchVO();
			merchVo.setMerchDT(merchDT);
			merchVo.setMerchID(merchID);
			merchVo.setMerchName(merchName);
			merchVo.setMerchPrice(merchPrice);
			merchVo.setMerchStatus(merchStatus);
			merchVo.setMerchClass(merchClass);
			merchVo.setMerchStock(merchStock);
			merchVo.setSoldTotal(soldTotal);
			req.setAttribute("merchVo", merchVo);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/merchandise/merchupdate.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/* ==============開始修改資料================================ */
			MerchService merchSvc = new MerchService();
			merchVo = merchSvc.updateMerch(merchID, merchName, merchDT, merchPic1, merchPic2, merchPic3, merchPic4,
					merchPic5, merchDate, merchPrice, merchClass, merchStatus, merchStock);
			/* ==============修改完成,準備轉交================================ */
			req.setAttribute("success", "修改成功");
			req.setAttribute("merchVo", merchVo); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/merchandise/mallIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MerchVO merchVo = new MerchVO();

			/* ========================接受請求參數============================== */

			String merchName = req.getParameter("merchName");
			if (merchName == null || merchName.trim().length() == 0) {
				errorMsgs.put("merchName", "請輸入商品名稱");
			}
			String merchDT = req.getParameter("merchDT");
			if (merchDT == null || merchDT.trim().length() == 0) {
				errorMsgs.put("merchDT", "請輸入商品描述");
			}
			// 取上傳圖片
			MerchServlet merchSvt = new MerchServlet();
			Blob merchPic1 = null;
			Blob merchPic2 = null;
			Blob merchPic3 = null;
			Blob merchPic4 = null;
			Blob merchPic5 = null;
			if (req.getPart("merchPic1").getSize() != 0) {
				merchPic1 = merchSvt.insertPicToDB(req.getPart("merchPic1"));
			}
			if (req.getPart("merchPic2").getSize() != 0) {
				merchPic2 = merchSvt.insertPicToDB(req.getPart("merchPic2"));
			}
			if (req.getPart("merchPic3").getSize() != 0) {
				merchPic3 = merchSvt.insertPicToDB(req.getPart("merchPic3"));
			}
			if (req.getPart("merchPic4").getSize() != 0) {
				merchPic4 = merchSvt.insertPicToDB(req.getPart("merchPic4"));
			}
			if (req.getPart("merchPic5").getSize() != 0) {
				merchPic5 = merchSvt.insertPicToDB(req.getPart("merchPic5"));
			}
			java.sql.Timestamp merchDate = null;
//			try {
//				merchDate = java.sql.Timestamp.valueOf(req.getParameter("merchDate"));
//			} catch (IllegalArgumentException e) {
//				errorMsgs.put("merchDate", "請輸入日期");
//			}
			Double merchPrice = null;
			try {
				merchPrice = Double.valueOf(req.getParameter("merchPrice"));
				if (merchPrice == null || merchPrice <= 0) {
					errorMsgs.put("merchPrice", "錯誤的價格");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("merchPrice", "請輸入正確數字");
			}
			String merchClass = req.getParameter("merchClass");
			Integer soldTotal = null;
			try {
				soldTotal = Integer.valueOf(req.getParameter("soldTotal"));
				if (soldTotal < 0) {
					errorMsgs.put("soldTotal", "請輸入正確的總銷售數");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("soldTotal", "總銷售請輸入數字或默認為0");
			}
			Byte merchStatus = Byte.valueOf(req.getParameter("merchStatus"));
			Integer merchStock = null;
			try {
				merchStock = Integer.valueOf(req.getParameter("merchStock"));
				if (merchStock == null || merchStock < 0) {
					errorMsgs.put("merchStock", "請輸入正確的庫存數");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("merchStock", "庫存請輸入數字");
			}
			merchVo.setMerchDT(merchDT);
			merchVo.setMerchName(merchName);
			merchVo.setMerchPrice(merchPrice);
			merchVo.setMerchStatus(merchStatus);
			merchVo.setMerchClass(merchClass);
			merchVo.setMerchStock(merchStock);
			merchVo.setSoldTotal(soldTotal);
			req.setAttribute("merchVo", merchVo);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/merchandise/merchinsert.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/* ==============開始修改資料================================ */
			MerchService merchSvc = new MerchService();
			merchSvc.addMerch(merchName, merchDT, merchPic1, merchPic2, merchPic3, merchPic4, merchPic5, merchDate,
					merchPrice, merchClass, merchStatus, merchStock);
			/* ==============修改完成,準備轉交================================ */
			req.setAttribute("success", "新增成功!");
			String url = "/back_end/merchandise/mallIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* ==============接受請求參數============================================= */
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));

			/* ==============開始刪除資料============================================= */

			MerchService merchSvc = new MerchService();
			merchSvc.deleteMerch(merchID);
			/* ==============刪除完成,準備轉交============================================= */
			merchSvc = new MerchService();
			List<MerchVO> list = merchSvc.getAll();
			HttpSession session = req.getSession();
			req.setAttribute("list", list);
			session.setAttribute("merchlist", list);
			String url = "/back_end/merchandise/merchlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/*
		 * ===================================前台========================================
		 * =====
		 */
		if ("getMerchInfo".equals(action)) {
			/* 接收資料 */
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));
			MerchService merchSvc = new MerchService();
			MerchVO merchVo = merchSvc.getOneMerch(merchID);
			req.setAttribute("merchVo", merchVo);
			String url = "/front_end/merchandise/merchandise.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("changePrice".equals(action)) {
			if ("0".equals(req.getParameter("index"))) {
				Double min = Double.valueOf(req.getParameter("min"));
				Double max = Double.valueOf(req.getParameter("max"));
				MerchService merchSvc = new MerchService();
				List<MerchVO> list = merchSvc.getHotSell(min, max);
				Gson gson = new Gson();
				String str = gson.toJson(list);
				res.setContentType("application/json; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.print(str);
			}
			if ("1".equals(req.getParameter("index"))) {
				Double min = Double.valueOf(req.getParameter("min"));
				Double max = Double.valueOf(req.getParameter("max"));
				MerchService merchSvc = new MerchService();
				List<MerchVO> list = merchSvc.getNewest(min, max);
				Gson gson = new Gson();
				String str = gson.toJson(list);
				res.setContentType("application/json; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.print(str);
			}
			if ("2".equals(req.getParameter("index"))) {
				Double min = Double.valueOf(req.getParameter("min"));
				Double max = Double.valueOf(req.getParameter("max"));
				MerchService merchSvc = new MerchService();
				List<MerchVO> list = merchSvc.getMostSold(min, max);
				Gson gson = new Gson();
				String str = gson.toJson(list);
				res.setContentType("application/json; charset=UTF-8");
				PrintWriter out = res.getWriter();
				out.print(str);
			}

		}
		if ("getSearchList".equals(action)) {

			Double min = Double.valueOf(req.getParameter("min"));
			Double max = Double.valueOf(req.getParameter("max"));
			String search = req.getParameter("search");
			MerchService merchSvc = new MerchService();
			List<MerchVO> list = merchSvc.getBySearch(search, min, max);
			Gson gson = new Gson();
			String str = gson.toJson(list);
			res.setContentType("application/json; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print(str);

		}

	}

	public Blob insertPicToDB(Part merchPic1) {
		Blob blob = null;
		try {
			InputStream is = merchPic1.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(is);
			byte[] b = new byte[buf.available()];
			buf.read(b);
			blob = new SerialBlob(b);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return blob;
	}

}
