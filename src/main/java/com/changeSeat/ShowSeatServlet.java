package com.changeSeat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hall.model.HallService;
import com.hall.model.HallVO;

@WebServlet("/ShowSeatServlet.do")
public class ShowSeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String action = req.getParameter("action");
		
		if("getDate".equals(action)) {
			
			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			
			HallService hallSvc = new HallService();
			HallVO hallVO = hallSvc.findByPrimaryKey(hlId);
			
			ShowSeatService ssSvc = new ShowSeatService();
			List dateList = ssSvc.getDate(hlId);
			
			req.setAttribute("hallVO", hallVO);
			req.setAttribute("dateList", dateList);
			
			String url ="/back_end/ManageSeat/editShow.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		if("getTimeByDate".equals(action)) {
			
			String dateOption = req.getParameter("dateOption");
			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			ShowSeatService ssSvc = new ShowSeatService();
			List<ShowSeatVO> preparedList = ssSvc.getTimeByDate(hlId, dateOption);
			
			Gson gson = new Gson();
			String list = gson.toJson(preparedList);
			
			res.setContentType("text/json; charset=UTF-8");
			res.getWriter().write(list);
			
		}
		
		if("getShowByTime".equals(action)) {
			
		Integer SH_ID =Integer.valueOf(req.getParameter("SH_ID"));
		Integer hlId =Integer.valueOf(req.getParameter("hlId"));
		
		System.out.println("SHID:"+SH_ID);
		System.out.println("hlId:"+hlId);
		
		ShowSeatService ssSvc = new ShowSeatService();
		
		// 依照使用者選取的場次 調回場次VO
		ShowSeatVO preparedShowSeatVO = ssSvc.getShowByTime(SH_ID);
		// 依照使用者一開始選擇的影廳 調回影廳原始的座位字串,預備使用
		HallVO preparedHallVO = ssSvc.getSeatByHL(hlId);
		
		Map voMap = new HashMap();
		voMap.put("showSeatVO", preparedShowSeatVO);
		voMap.put("hallVO", preparedHallVO);
		
		Gson gson = new Gson();
		
		res.setContentType("application/json; charset=UTF-8");
		res.getWriter().print(gson.toJson(voMap));
			
		}
		
		if("updateShowSeat".equals(action)) {
			Integer SH_ID =Integer.valueOf(req.getParameter("SH_ID"));
			String SH_SEAT_STATE = req.getParameter("SH_SEAT_STATE");
			
			ShowSeatService ssSvc = new ShowSeatService();
			
			ssSvc.updateShowSeat(SH_SEAT_STATE, SH_ID);
			ShowSeatVO showSeatVO =ssSvc.getShowByTime(SH_ID);
			
			Gson gson = new Gson();
			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().print(gson.toJson(showSeatVO));
		}
	}

}
