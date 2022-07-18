package com.order_detail.controller;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.merchandise_inf.model.MerchService;
import com.merchandise_inf.model.MerchVO;
import com.merchandise_order.model.MerchOrdDAO;
import com.merchandise_order.model.MerchOrdService;
import com.merchandise_order.model.MerchOrdVO;
import com.order_detail.model.OrderDetailService;
import com.order_detail.model.OrderDetailVO;

@WebServlet("/OrderDetail/OrderDetail.do")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderDetailServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		

		if ("getOrder_For_Display".equals(action)) {
			OrderDetailService orderDetailSvc = new OrderDetailService();
			/* =========================接收資料=============================== */
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			/* =========================開始查詢============================== */
			List<OrderDetailVO> list = orderDetailSvc.getALL(merchOrdID);
			/* =========================開始轉交=============================== */
			//移除重複元素
			MerchService merchSvc = new MerchService();
			List<MerchVO> insertlist = merchSvc.getAll();
			for(OrderDetailVO orderDetailVo : list) {
				for(int i = 0; i < insertlist.size(); i++) {
					if(insertlist.get(i).getMerchID() == orderDetailVo.getMerchID()) {
						insertlist.remove(i);
					}
				}
			}
			HttpSession session = req.getSession();
			session.setAttribute("insertlist", insertlist);
			session.setAttribute("orderDetailList", list);
			MerchOrdService merchOrdSvc = new MerchOrdService();
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			session.setAttribute("merchOrdVo", merchOrdVo);
			String url = "/back_end/OrderDetail/orderDetailList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			OrderDetailService orderDetailSvc = new OrderDetailService();
			/* ==========================接收資料============================ */
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			Integer item = Integer.valueOf(req.getParameter("item"));
			Integer ordCount = 0;
			try {
				ordCount = Integer.valueOf(req.getParameter("ordCount"));
				if (ordCount <= 0 || ordCount == null)
					errorMsgs.put("ordCount", "數量異常!");
			} catch (NumberFormatException e) {
				errorMsgs.put("ordCount", "數量請輸入數字!");
			}
			Byte ordStatus = Byte.valueOf(req.getParameter("ordStatus"));
			Double ordPrice = 0.0;
			try {
				ordPrice = Double.valueOf(req.getParameter("ordPrice"));
				if (ordPrice <= 0 || ordPrice == null)
					errorMsgs.put("ordPrice", "價格異常!");
			} catch (NumberFormatException e) {
				errorMsgs.put("ordPrice", "價格請輸入數字");
			}
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));
			/* ========================開始修改資料=============================== */
			OrderDetailVO orderDetailVo = orderDetailSvc.updatetOrderDetail(merchOrdID, item, merchID, ordCount,
					ordStatus, ordPrice);
			MerchOrdService merchOrdSvc = new MerchOrdService();
			/* 重新確定總價 */
			merchOrdSvc.resetMerchOrdCount(merchOrdID);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("????");
				failureView.forward(req, res);
				return;
			}
			/* ======================修改完成,準備轉交============================ */
			List<OrderDetailVO> list = orderDetailSvc.getALL(merchOrdID);
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			/*由訂單明細判斷訂單狀態*/
			int status = 1;
			for(OrderDetailVO ordDVo:list) {
				/*只要有一個可取貨 則訂單顯示可取貨*/
				if(ordDVo.getOrdStatus()==1) {
					merchOrdVo.setMerchOrdStatus((byte)1);
					merchOrdSvc.updateMerchOrd(merchOrdVo);
					break;
				}
				/*全部已取貨 且 沒有備貨中細項*/
				if(ordDVo.getOrdStatus()==2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)2);
					status = 2;
				}
				/*沒有可取貨 且 有未取貨細項*/
				if(ordDVo.getOrdStatus()==0) {
					merchOrdVo.setMerchOrdStatus((byte)0);
					status = 0;
				}
				/*全部已取消 則取消*/
				if(ordDVo.getOrdStatus()==3 && status != 2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)3);
				}
				merchOrdSvc.updateMerchOrd(merchOrdVo);	
			}
			HttpSession session = req.getSession();
			session.setAttribute("orderDetailList", list);
			session.setAttribute("merchOrdVo", merchOrdVo);
			String url = "/back_end/OrderDetail/orderDetailList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getOne_For_Delete".equals(action)) {
			OrderDetailService orderDetailSvc = new OrderDetailService();
			/* ======================開始接收資料======================= */
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			Integer item = Integer.valueOf(req.getParameter("item"));
			/* ========================刪除資料========================= */
			orderDetailSvc.deleteOrderDetail(merchOrdID, item);
			/* ========================準備轉交========================= */
			MerchOrdService merchOrdSvc = new MerchOrdService();
			/* 重新計算總價 */
			merchOrdSvc.resetMerchOrdCount(merchOrdID);
			List<OrderDetailVO> list = orderDetailSvc.getALL(merchOrdID);
			/* 重新設定項次 */
			orderDetailSvc.resetItem(list);
			/* 再拿一次訂單細項 */
			list = orderDetailSvc.getALL(merchOrdID);
			//移除重複元素
			MerchService merchSvc = new MerchService();
			List<MerchVO> insertlist = merchSvc.getAll();
			for(OrderDetailVO orderDetailVo : list) {
				for(int i = 0; i < insertlist.size(); i++) {
					if(insertlist.get(i).getMerchID() == orderDetailVo.getMerchID()) {
						insertlist.remove(i);
					}
				}
			}
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			/*由訂單明細判斷訂單狀態*/
			int status = 1;
			for(OrderDetailVO ordDVo:list) {
				/*只要有一個可取貨 則訂單顯示可取貨*/
				if(ordDVo.getOrdStatus()==1) {
					merchOrdVo.setMerchOrdStatus((byte)1);
					merchOrdSvc.updateMerchOrd(merchOrdVo);
					break;
				}
				/*全部已取貨 且 沒有備貨中細項*/
				if(ordDVo.getOrdStatus()==2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)2);
					status = 2;
				}
				/*沒有可取貨 且 有未取貨細項*/
				if(ordDVo.getOrdStatus()==0) {
					merchOrdVo.setMerchOrdStatus((byte)0);
					status = 0;
				}
				/*全部已取消 則取消*/
				if(ordDVo.getOrdStatus()==3 && status != 2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)3);
				}
				merchOrdSvc.updateMerchOrd(merchOrdVo);	
			}
			HttpSession session = req.getSession();
			session.setAttribute("insertlist", insertlist);
			session.setAttribute("orderDetailList", list);
			merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			session.setAttribute("merchOrdVo", merchOrdVo);
			String url = "/back_end/OrderDetail/orderDetailList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}
		if("getItem_For_Update".equals(action)) {
			OrderDetailService orderDetailSvc = new OrderDetailService();
			Map<String, String[]> map = req.getParameterMap();
			/*=======================接收資料,並更新=========================*/
			Integer merchOrdID = Integer.valueOf(map.get("merchOrdID")[0]);
			if(map.get("item") != null) {
			for(int i = 0; i < map.get("item").length ; i++) {
				Integer item = Integer.valueOf(map.get("item")[i]);
				OrderDetailVO orderDetailVo = orderDetailSvc.getOneOrderDedail(merchOrdID, item);
				if("1".equals(map.get("ordStatus")[0])) {
				orderDetailVo.setOrdStatus((byte) 1);
				}else if ("2".equals(map.get("ordStatus")[0])) {
				orderDetailVo.setOrdStatus((byte) 2);
				}else if ("3".equals(map.get("ordStatus")[0])) {
				orderDetailVo.setOrdStatus((byte) 3);
				}
				orderDetailSvc.updateOrderDetail(orderDetailVo);
			}
			}
			/*========================開始轉交==============================*/
			List<OrderDetailVO> list = orderDetailSvc.getALL(merchOrdID);
			HttpSession session = req.getSession();
			MerchOrdService merchOrdSvc = new MerchOrdService();
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			/*由訂單明細判斷訂單狀態*/
			int status = 1;
			for(OrderDetailVO ordDVo:list) {
				/*只要有一個可取貨 則訂單顯示可取貨*/
				if(ordDVo.getOrdStatus()==1) {
					merchOrdVo.setMerchOrdStatus((byte)1);
					merchOrdSvc.updateMerchOrd(merchOrdVo);
					break;
				}
				/*全部已取貨 且 沒有備貨中細項*/
				if(ordDVo.getOrdStatus()==2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)2);
					status = 2;
				}
				/*沒有可取貨 且 有未取貨細項*/
				if(ordDVo.getOrdStatus()==0) {
					merchOrdVo.setMerchOrdStatus((byte)0);
					status = 0;
				}
				/*全部已取消 則取消*/
				if(ordDVo.getOrdStatus()==3 && status != 2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)3);
				}
				merchOrdSvc.updateMerchOrd(merchOrdVo);	
			}
			session.setAttribute("orderDetailList", list);
			session.setAttribute("merchOrdVo", merchOrdVo);
			String url = "/back_end/OrderDetail/orderDetailList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			OrderDetailService orderDetailSvc = new OrderDetailService();
			/* ==========================接收資料============================ */
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			Integer item = 9999;
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));
			Integer ordCount = Integer.valueOf(req.getParameter("ordCount"));
			if(ordCount <= 0) {
				ordCount = 0;
				errorMsgs.put("ordCount", "數量請大於0!");
			}
			Byte ordStatus = Byte.valueOf(req.getParameter("ordStatus"));
			MerchService merchSvc = new MerchService();
			Double ordPrice = merchSvc.getOneMerch(merchID).getMerchPrice();
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("????");
				failureView.forward(req, res);
				return;
			}
			/*==========================新增資料============================== */
			orderDetailSvc.addOrderDetail(merchOrdID, item, merchID, ordCount, ordStatus, ordPrice);
			/*==========================轉接資料============================== */
			List<OrderDetailVO> list = orderDetailSvc.getALL(merchOrdID);
			//重新設定項次
			orderDetailSvc.resetItem(list);
			//重新計算總價
			MerchOrdService merchOrdSvc = new MerchOrdService();
			merchOrdSvc.resetMerchOrdCount(merchOrdID);
			list = orderDetailSvc.getALL(merchOrdID);
			List<MerchVO> insertlist = merchSvc.getAll();
			for(OrderDetailVO orderDetailVo : list) {
				for(int i = 0; i < insertlist.size(); i++) {
					if(insertlist.get(i).getMerchID() == orderDetailVo.getMerchID()) {
						insertlist.remove(i);
					}
				}
			}
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			/*由訂單明細判斷訂單狀態*/
			int status = 1;
			for(OrderDetailVO ordDVo:list) {
				/*只要有一個可取貨 則訂單顯示可取貨*/
				if(ordDVo.getOrdStatus()==1) {
					merchOrdVo.setMerchOrdStatus((byte)1);
					merchOrdSvc.updateMerchOrd(merchOrdVo);
					break;
				}
				/*全部已取貨 且 沒有備貨中細項*/
				if(ordDVo.getOrdStatus()==2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)2);
					status = 2;
				}
				/*沒有可取貨 且 有未取貨細項*/
				if(ordDVo.getOrdStatus()==0) {
					merchOrdVo.setMerchOrdStatus((byte)0);
					status = 0;
				}
				/*全部已取消 則取消*/
				if(ordDVo.getOrdStatus()==3 && status != 2 && status != 0) {
					merchOrdVo.setMerchOrdStatus((byte)3);
				}
				merchOrdSvc.updateMerchOrd(merchOrdVo);	
			}
			HttpSession session = req.getSession();
			session.setAttribute("insertlist", insertlist);
			session.setAttribute("orderDetailList", list);
			session.setAttribute("merchOrdVo", merchOrdVo);
			String url = "/back_end/OrderDetail/orderDetailList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		
		}
		/*前台訂單細項查詢*/
		if ("getMember_Order_For_Display".equals(action)) {
			System.out.println("eee");
			OrderDetailService orderDetailSvc = new OrderDetailService();
			/* =========================接收資料=============================== */
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			/* =========================開始查詢============================== */
			List<OrderDetailVO> list = orderDetailSvc.getALL(merchOrdID);
			/* =========================開始轉交=============================== */
			//移除重複元素
			MerchService merchSvc = new MerchService();
			List<MerchVO> insertlist = merchSvc.getAll();
			for(OrderDetailVO orderDetailVo : list) {
				for(int i = 0; i < insertlist.size(); i++) {
					if(insertlist.get(i).getMerchID() == orderDetailVo.getMerchID()) {
						insertlist.remove(i);
					}
				}
			}
			HttpSession session = req.getSession();
			session.setAttribute("insertlist", insertlist);
			session.setAttribute("orderDetailList", list);
			MerchOrdService merchOrdSvc = new MerchOrdService();
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			session.setAttribute("merchOrdVo", merchOrdVo);
//			res.setContentType("application/json; charset=UTF-8");
//			Gson gson = new Gson();
//			gson.toJson(list);
//			PrintWriter pt = res.getWriter();
//			pt.print(list);
			String url = "/front_end/merchandise/merchOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
