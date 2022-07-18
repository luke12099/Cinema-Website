package com.hall.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hall.model.HallService;
import com.hall.model.HallVO;


@WebServlet("/HallServlet.do")
public class HallServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* *************************錯誤處理**************************/
			String hlName = req.getParameter("hlName");
			String enameReg = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
			if (hlName == null || hlName.trim().length() == 0) {
				errorMsgs.put("hlName","請勿空白");
			} else if(!hlName.trim().matches(enameReg)) { 
				errorMsgs.put("hlName","限輸入中、英文、數字和_底線");
            }
			Integer hlType = Integer.valueOf(req.getParameter("hlType")) ;
			Integer hlCol = Integer.valueOf(req.getParameter("hlCol")) ;
			Integer hlRow = Integer.valueOf(req.getParameter("hlRow")) ;
			String hlSeat = req.getParameter("hlSeat");
			Integer hlSeatCount = null;
			if(req.getParameter("hlSeatCount").isEmpty()) {
				hlSeatCount = 0;
			}else {
				hlSeatCount = Integer.valueOf(req.getParameter("hlSeatCount")) ;
			}
			
			HallVO hallVO = new HallVO();
			hallVO.setHlName(hlName);
			hallVO.setHlType(hlType);
			hallVO.setHlCol(hlCol);
			hallVO.setHlRow(hlRow);
			hallVO.setHlSeat(hlSeat);
			hallVO.setHlSeatCount(hlSeatCount);
			/* **************************************************************** */
			HallService hlSvc = new HallService();
			hallVO = hlSvc.insert(hlName, hlSeat, hlRow, hlCol, hlType, hlSeatCount);
			
			String url ="/back_end/ManageHall/manageHall.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}	
		if("getOne_For_Update".equals(action)) {
			
			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			
			HallService hlSvc = new HallService();
			HallVO hallVO = hlSvc.findByPrimaryKey(hlId);
			
			req.setAttribute("hallVO", hallVO);
			String url ="/back_end/ManageHall/editHall.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		if("update".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/* *************************錯誤處理**************************/
			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			String hlName = req.getParameter("hlName");
//			String enameReg = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
//			if (hlName == null || hlName.trim().length() == 0) {
//				errorMsgs.put("hlName","請勿空白");
//			} else if(!hlName.trim().matches(enameReg)) { 
//				errorMsgs.put("hlName","限輸入中、英文、數字和_底線");
//            }
			Integer hlType = Integer.valueOf(req.getParameter("hlType")) ;
			Integer hlCol = Integer.valueOf(req.getParameter("hlCol")) ;
			Integer hlRow = Integer.valueOf(req.getParameter("hlRow")) ;
			String hlSeat = req.getParameter("hlSeat");
			Integer hlSeatCount = Integer.valueOf(req.getParameter("hlSeatCount")) ;
			
			/********************************************/
			HallVO hallVO = new HallVO();
			hallVO.setHlId(hlId);
			hallVO.setHlName(hlName);
			hallVO.setHlType(hlType);
			hallVO.setHlCol(hlCol);
			hallVO.setHlRow(hlRow);
			hallVO.setHlSeat(hlSeat);
			hallVO.setHlSeatCount(hlSeatCount);
			/* **************************************************************** */
//			if (!errorMsgs.isEmpty()) {
//				String url ="/back/ManageHall/editHall.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);
//				return;
//			}
			/********************************************/
			
			HallService hlSvc = new HallService();
			hallVO = hlSvc.update(hlId, hlName, hlSeat, hlRow, hlCol, hlType, hlSeatCount);
			req.setAttribute("hallVO", hallVO);
			
			String url ="/back_end/ManageHall/manageHall.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		if("editOriginalSeat".equals(action)) {
			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			
			HallService hlSvc = new HallService();
			HallVO hallVO = hlSvc.findByPrimaryKey(hlId);
			
			req.setAttribute("hallVO", hallVO);
			String url ="/back_end/ManageSeat/editOrigin.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			
		}
		if("UpdateOriginalSeat".equals(action)) {
			Integer hlId = Integer.valueOf(req.getParameter("hlId"));
			String hlName = req.getParameter("hlName");
			Integer hlType = Integer.valueOf(req.getParameter("hlType")) ;
			Integer hlCol = Integer.valueOf(req.getParameter("hlCol")) ;
			Integer hlRow = Integer.valueOf(req.getParameter("hlRow")) ;
			String hlSeat = req.getParameter("hlSeat");
			Integer hlSeatCount = Integer.valueOf(req.getParameter("hlSeatCount")) ;
			
			/********************************************/
			HallVO hallVO = new HallVO();
			hallVO.setHlId(hlId);
			hallVO.setHlName(hlName);
			hallVO.setHlType(hlType);
			hallVO.setHlCol(hlCol);
			hallVO.setHlRow(hlRow);
			hallVO.setHlSeat(hlSeat);
			hallVO.setHlSeatCount(hlSeatCount);
			/* **************************************************************** */
			HallService hlSvc = new HallService();
			hallVO = hlSvc.updateOriginSeat(hlId, hlName, hlSeat, hlRow, hlCol, hlType, hlSeatCount);
			req.setAttribute("hallVO", hallVO);
			
			String url ="/back_end/ManageSeat/manageSeat.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

}
