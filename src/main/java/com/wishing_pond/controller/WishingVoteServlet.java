package com.wishing_pond.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.JedisPoolUtil;
import com.member.model.*;
import com.wish_reply.model.*;
import com.wishing_list.model.*;
import com.wishing_pond.model.WishingPondService;

import redis.clients.jedis.Jedis;

@WebServlet("/wish/WishingVote.do")
public class WishingVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		response.setContentType("text/html; charset=utf-8;");
		PrintWriter out = response.getWriter();
		
		if("seeOneEvent".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer wish_no = Integer.valueOf(request.getParameter("wish_no"));
			String wish_name = request.getParameter("wish_name");
			String wish_start = request.getParameter("wish_start");
			String wish_end = request.getParameter("wish_end");
			
			/***************************2.開始查詢資料*****************************************/
			// 到 redis 查詢明細
			String eventJedisKey = new StringBuilder("wish:").append(wish_no).toString();
			Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
			Map<String, String> eventMap = jedis.hgetAll(eventJedisKey);
			// 到 redis 撈各選項票數
			Set<String> keys = eventMap.keySet();
			List<Integer> tkCounts = new ArrayList<Integer>();
			for(String key: keys) {
				String eventOptionKey = new StringBuilder("wish:").append(wish_no)
											.append(":movie:").append(key).append(":count").toString();
				if(jedis.get(eventOptionKey) == null) {
					tkCounts.add(0);
				} else {
					tkCounts.add(Integer.valueOf(jedis.get(eventOptionKey)));
				}
			}
			if(jedis != null) {
				jedis.close();
			}
			// 從 DB 抓資料
//			List<WishingListVO> wishListVOs = new WishingListService().getOneWishingPond(wish_no);
//			if(wishListVOs == null) {
//				errMsg.put("notFound", "查無此筆資料");
//				request.getRequestDispatcher("/frond_end/wish/wishPage.jsp").forward(request, response);
//			}
//			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			// Redis
			request.setAttribute("wish_no", wish_no);
			request.setAttribute("wish_name", wish_name);
			request.setAttribute("wish_start", wish_start);
			request.setAttribute("wish_end", wish_end);
			request.setAttribute("eventMap", eventMap);
			request.setAttribute("tkCounts", tkCounts);
			// DB
//			request.setAttribute("wishListVOs", wishListVOs);
//			request.setAttribute("tkCounts", tkCounts);
			request.getRequestDispatcher("/front_end/wish/wishDetail.jsp").forward(request, response);
		}
		
		if("voteOneEvent".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 由按鈕點選，無錯誤處理
			Integer wish_no = Integer.valueOf(request.getParameter("wish_no"));
			Integer member_id = Integer.valueOf(request.getParameter("member_id"));

			/***************************2.開始查詢資料*****************************************/
			WishingListService wishListSvc = new WishingListService();
			List<WishingListVO> wishListVOs =  wishListSvc.getOneWishingPond(wish_no);
			if(wishListVOs == null) {
				errMsg.put("notFound", "查無此筆資料");
				request.getRequestDispatcher("/frond_end/wish/wishPage.jsp").forward(request, response);
			}
			MemberVO memberVO = new MemberService().getOneMem(member_id);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("wish_ticket", memberVO.getWish_Ticket());
			request.setAttribute("wishListVOs", wishListVOs);
			request.getRequestDispatcher("/front_end/wish/voteWish.jsp").forward(request, response);
		}
		
		if("voteMovie".equals(action)) {
			
			// 存放錯誤訊息
			Map<String, String> errMsg = new LinkedHashMap<String, String>();
			request.setAttribute("errMsg", errMsg);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			// 會員登入後才可使用，從session抓會員編號	，未登入會導回登入頁面，故無作錯誤處理
			Integer member_id = Integer.valueOf(request.getParameter("member_id"));
			// 活動編號及電影選項經由 controller 處理過才送往頁面，故無做錯誤處理
			Integer wish_no = Integer.valueOf(request.getParameter("wish_no"));
			Integer mv_id = Integer.valueOf(request.getParameter("mv_id"));
			// 可為空，判斷若為空字串則不會存至 DB
			String wish_msg = request.getParameter("wish_msg");
//			out.print(wish_no + ",,," + mv_id + ",,," + member_id + ",,," + wish_msg + ",,,end"); 
			
			// 查詢票數是否足夠?
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMem(member_id);
			if(memberVO.getWish_Ticket() <= 0) {
				errMsg.put("isSuccess", "許願票不足!");
				request.setAttribute("wish_ticket", memberVO.getWish_Ticket());
				
				List<WishingListVO> wishListVOs =  new WishingListService().getOneWishingPond(wish_no);
				request.setAttribute("wishListVOs", wishListVOs);
				
				request.getRequestDispatcher("/front_end/wish/voteWish.jsp").forward(request, response);
				return;
			}
			// 判斷投票日期是否在活動區間內                                                       結束日                     +  一天
			if(new Date().getTime() > new WishingPondService().getOneWishingPond(wish_no).getWish_end().getTime() + 1 * 24 * 60 * 60 * 1000) {
				errMsg.put("isSuccess", "該票選活動已超時!");
				
				request.getRequestDispatcher("/front_end/wish/wishPage.jsp").forward(request, response);
				return;
			}
			
			/***************************2.開始修改資料*****************************************/
			// 會員票數 -1 service
			Integer restTicket = memberVO.getWish_Ticket() - 1;
			memSvc.updateWishTicket(member_id, restTicket);
			// 活動.電影票數 +1 存 redis
			Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
			String jedisKey = new StringBuilder("wish:").append(wish_no)
								.append(":movie:").append(mv_id).append(":count").toString();
			jedis.incr(jedisKey);
			
			// 如果有留言，新增 wishReply
			if(wish_msg.length() != 0) {
				new WishReplyService().addWishReply(wish_no, member_id, wish_msg);
			}
			
			// 由按鈕點選，無錯誤處理
			String wish_name = request.getParameter("wish_name");
			String wish_start = request.getParameter("wish_start");
			String wish_end = request.getParameter("wish_end");
			
			/***************************2.開始查詢資料*****************************************/
			// 到 redis 查詢明細
			String eventJedisKey = new StringBuilder("wish:").append(wish_no).toString();
			Map<String, String> eventMap = jedis.hgetAll(eventJedisKey);
			// 到 redis 撈各選項票數
			Set<String> keys = eventMap.keySet();
			List<Integer> tkCounts = new ArrayList<Integer>();
			for(String key: keys) {
				String eventOptionKey = new StringBuilder("wish:").append(wish_no)
											.append(":movie:").append(key).append(":count").toString();
				if(jedis.get(eventOptionKey) == null) {
					tkCounts.add(0);
				} else {
					tkCounts.add(Integer.valueOf(jedis.get(eventOptionKey)));
				}
			}
			if(jedis != null) {
				jedis.close();
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			// Redis
			request.setAttribute("wish_no", wish_no);
			request.setAttribute("wish_name", wish_name);
			request.setAttribute("wish_start", wish_start);
			request.setAttribute("wish_end", wish_end);
			request.setAttribute("eventMap", eventMap);
			request.setAttribute("tkCounts", tkCounts);
			
			request.setAttribute("restTicket", restTicket);
			
			request.getRequestDispatcher("/front_end/wish/wishDetail.jsp").forward(request, response);
		}
	}

}
