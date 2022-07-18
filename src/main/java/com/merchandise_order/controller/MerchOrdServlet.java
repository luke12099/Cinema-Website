package com.merchandise_order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.naming.java.javaURLContextFactory;

import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.merchandise_inf.model.MerchService;
import com.merchandise_inf.model.MerchVO;
import com.merchandise_order.model.MerchOrdDAO;
import com.merchandise_order.model.MerchOrdService;
import com.merchandise_order.model.MerchOrdVO;

import com.order_detail.model.OrderDetailService;
import com.order_detail.model.OrderDetailVO;

import oracle.net.aso.g;
import oracle.net.aso.i;

@WebServlet("/merchOrd/merchOrd.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MerchOrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MerchOrdServlet() {
        super();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		
		if("getmerchNameByID".equals(action)) {
			Integer merchID = Integer.valueOf(req.getParameter("merchID"));
			MerchService merchSvc = new MerchService();
			MerchVO merchVo = merchSvc.getOneMerch(merchID);
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			out.print(gson.toJson(merchVo));
		}
		
		
		if("getAll_For_Display".equals(action)) {
			MerchOrdService merchOrdSvc = new MerchOrdService();
			List<MerchOrdVO> list = merchOrdSvc.getAll();
			HttpSession session = req.getSession();
			session.setAttribute("merchOrdlist", list);
			String url = "/back_end/merchandiseOrd/merchOrdlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("get_Member_Ordlist".equals(action)) {
			MerchOrdService merchOrdSvc = new MerchOrdService();
			/*=======================接受請求參數====================*/
			Integer memberID = Integer.valueOf(req.getParameter("memberID"));
			/*=======================開始查詢資料====================*/
			List<MerchOrdVO> list = merchOrdSvc.getAll(memberID);
			/*=======================查詢完畢,準備轉交===============*/
			req.setAttribute("memberID", memberID);
			HttpSession session = req.getSession();
			session.setAttribute("merchOrdlist", list);
			String url = "/back_end/merchandiseOrd/memberOrdlist.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("getOne_For_Display".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MerchOrdVO merchOrdVo = null;
			List<MerchOrdVO> list = null;
			/*==================================接收請求參數===========================*/
			try {
				Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
				
			/*==================================開始查詢資料===========================*/
				MerchOrdService merchOrdSvc = new MerchOrdService();
				MerchOrdVO MerchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
			if(merchOrdVo == null) {
				errorMsgs.put("merchID", "查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("????");
				failureView.forward(req, res);
				return;
			}
			/*===========================查詢完成,準備轉交==========================*/
			req.setAttribute("merchOrdVo", merchOrdVo);
			RequestDispatcher successView = req.getRequestDispatcher("????");
			successView.forward(req, res);
		}
		if("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*=========================接收請求參數====================================*/
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			/*=========================開始查詢資料====================================*/
			MerchOrdService merchOrdSvc = new MerchOrdService();
			MerchOrdVO merchOrdVo = merchOrdSvc.getOneMerchOrd(merchOrdID);
			req.setAttribute("merchOrdVo", merchOrdVo);
			/*=========================查詢完成,準備轉交================================*/
			String url = "????";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*=========================接受請求參數=====================================*/
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			Integer memberID = Integer.valueOf(req.getParameter("memberID"));
			java.sql.Timestamp merchOrdDate = java.sql.Timestamp.valueOf(req.getParameter("merchOrdDate"));
			try {
			Integer merchOrdCount = Integer.valueOf(req.getParameter("merchOrdCount"));
			if(merchOrdCount == null) {
				errorMsgs.put("merchOrdCount", "忘了填數量囉!");
			}
			}catch(NumberFormatException e) {
				errorMsgs.put("merchOrdCount", "請輸入數字格式");
			}
			Byte merchOrdStatus = Byte.valueOf(req.getParameter("merchOrdStatus"));
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("????");
				failureView.forward(req, res);
				return;
			}
			/*=========================開始修改資料=================================*/
			MerchOrdService merchOrdSvc = new MerchOrdService();
//			MerchOrdVO merchOrdVo = merchOrdSvc.updateMerchOrd(merchOrdID, memberID, merchOrdDate, merchOrdID, merchOrdStatus);
			
			/*=========================修改完成,準備轉交=============================*/
			req.setAttribute("success", "修改成功");
//			req.setAttribute("merchOrdVo", merchOrdVo);
			String url ="????";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*=========================接受請求參數=====================================*/
			Integer memberID = Integer.valueOf(req.getParameter("memberID"));
			java.sql.Timestamp merchOrdDate = java.sql.Timestamp.valueOf(req.getParameter("merchOrdDate"));
			try {
			Integer merchOrdCount = Integer.valueOf(req.getParameter("merchOrdCount"));
			if(merchOrdCount == null) {
				errorMsgs.put("merchOrdCount", "忘了填數量囉!");
			}
			}catch(NumberFormatException e) {
				errorMsgs.put("merchOrdCount", "請輸入數字格式");
			}
			Byte merchOrdStatus = Byte.valueOf(req.getParameter("merchOrdStatus"));
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("????");
				failureView.forward(req, res);
				return;
			}
			/*=========================開始修改資料=================================*/
			MerchOrdService merchOrdSvc = new MerchOrdService();
//			MerchOrdVO merchOrdVo = merchOrdSvc.addMerchOrd(memberID, merchOrdDate, memberID, merchOrdStatus);
			
			/*=========================修改完成,準備轉交=============================*/
			req.setAttribute("success", "新增成功");
//			req.setAttribute("merchOrdVo", merchOrdVo);
			String url ="????";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*==============接受請求參數=======================================*/
			Integer merchOrdID = Integer.valueOf(req.getParameter("merchOrdID"));
			Integer memberID = null;
			if(req.getParameter("memberID") != null) {
			memberID = Integer.valueOf(req.getParameter("memberID"));}
			/*==============開始刪除資料=======================================*/
			OrderDetailService orderDetailSvc = new OrderDetailService();
			List<OrderDetailVO> ordlist = orderDetailSvc.getALL(merchOrdID);
			for(OrderDetailVO orderDetailVo : ordlist) {
				orderDetailSvc.deleteOrderDetail(orderDetailVo.getMerchOrdID(), orderDetailVo.getItem());
			}
			MerchOrdService merchOrdSvc = new MerchOrdService();
			merchOrdSvc.deleteMerchOrd(merchOrdID);
			
			/*==============刪除完成,準備轉交===================================*/
			List<MerchOrdVO> list = merchOrdSvc.getAll();
			List<MerchOrdVO> memberlist = null;
			if(memberID != null)
			memberlist = merchOrdSvc.getAll(memberID);
			req.setAttribute("list", list);
			HttpSession session = req.getSession();
			session.setAttribute("list", list);
			req.setAttribute("merchOrdlist", memberlist);
			session.setAttribute("merchOrdlist", memberlist);
			req.setAttribute("memberID", memberID);
			String url = null;
			/*從會員列表刪除 || 從訂單列表刪除*/
			if(memberID != null) {
			url ="/back_end/merchandiseOrd/memberOrdlist.jsp";
			}else {
			url ="/back_end/merchandiseOrd/merchOrdlist.jsp";
			}
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("insert1".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*=========================接受請求參數=====================================*/
			Map<String, String[]> map = req.getParameterMap();
			List<OrderDetailVO> list = new LinkedList<OrderDetailVO>();
			MerchOrdService merchOrdSvc = new MerchOrdService();
			MerchService merchSvc = new MerchService();
			MemberService memberSvc = new MemberService();
			int item = 1 ;
			for(int i = 0; i > -1; i++) {
				/*訂單明細結束*/
				if("0".equals(map.get("merchID")[i])) {
					if(list.size()==0) {
						errorMsgs.put("orderDetail", "最少選擇一項商品明細!");
					}
					break;
				}
				/*選擇的訂單明細商品名稱數量不為零則進入增加*/
				if(!"".equals(map.get("merchCount")[i])){
					OrderDetailVO orderDetailVo = new OrderDetailVO();
					
					orderDetailVo.setItem(item);
					item++;
					Integer merchID = Integer.valueOf(map.get("merchID")[i]);
					orderDetailVo.setMerchID(merchID);
					
					Integer merchCount = Integer.valueOf(map.get("merchCount")[i]);
					orderDetailVo.setOrdCount(merchCount);
					
					MerchVO merchVo = merchSvc.getOneMerch(merchID);
					Double merchPrice = merchVo.getMerchPrice();
					orderDetailVo.setOrdPrice(merchPrice);
					
					/*0:備貨中 1:可取貨 2:已完成 3:已取消*/
					Byte ordStatus = 0;
					orderDetailVo.setOrdStatus(ordStatus);
					list.add(orderDetailVo);
				}
			}
			Integer memberID = 0;
			try {
			memberID = Integer.valueOf(map.get("memberID")[0]);
			MemberVO memberVo = memberSvc.getOneMem(memberID);
			if(memberVo == null || memberID == 0) {
				errorMsgs.put("memberID", "查無會員!");
			}
			}catch (NumberFormatException e) {
				errorMsgs.put("memberID", "請輸入會員編號!");
			}
			Double merchOrdCount = Double.valueOf(map.get("totalCount")[0]);
			
			/*0:處理中 1:可取貨 2:已完成 3:已取消*/
			Byte merchOrdStatus = 0;
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/merchandiseOrd/orderinsert.jsp");
				failureView.forward(req, res);
				return;
			}
			/*=========================開始修改資料=================================*/
			MerchOrdVO merchOrdVo = new MerchOrdVO(memberID,null,merchOrdCount,merchOrdStatus);
			merchOrdSvc.insertWithOrderDetail(merchOrdVo, list);
			/*=========================修改完成,準備轉交=============================*/
			req.setAttribute("success", "新增成功");
//			req.setAttribute("merchOrdVo", merchOrdVo);
			String url ="/back_end/merchandiseOrd/orderIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}
	

}
