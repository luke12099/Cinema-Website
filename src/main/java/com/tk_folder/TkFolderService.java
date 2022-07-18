package com.tk_folder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.changeSeat.ShowSeatService;
import com.changeSeat.ShowSeatVO;
import com.fd_ord_dt.model.FdOrdDtVO;
import com.hall.model.HallService;
import com.hall.model.HallVO;
import com.movie.model.MovieService;
import com.movie.model.MovieVO;
import com.refundTicket.RefundTicketService;
import com.tk_ord.model.TkOrdService;
import com.tk_ord.model.TkOrdVO;
import com.tk_ord_dt.model.TkOrdDtVO;

public class TkFolderService {
	private TkFolder_interface dao;
	
	public TkFolderService (){
		dao = new TkFolderDAO();
	}
	
	public Map<String,Object> listAllOrdInf(Integer member_ID) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		// 找到該會員全部的訂單VO 裝進list
		List<TkOrdVO> OrdVOList = dao.getAllTkOrd(member_ID);
		// 設置一個裝場次VO的list
		List<ShowSeatVO> showList = new ArrayList<ShowSeatVO>();
		// 設置一個裝電影VO的list
		List<MovieVO> mvList = new ArrayList<MovieVO>();
		// 設置一個裝廳院VO的list
		List<HallVO> hlList = new ArrayList<HallVO>();
		// 設置一個裝該筆訂單有幾張票的list
		List<Integer> dtCountList = new ArrayList<Integer>();
		// 設置一個裝該筆訂單有幾份食物的list
		List<Integer> foodCountList = new ArrayList<Integer>();
		// 使用tkOrdVO的SH_ID找到場次的時間 廳院 播放電影
		ShowSeatService ssSvc = new ShowSeatService();
		HallService hlSvc = new HallService();
		MovieService mvSvc = new MovieService();
		TkFolderService tkSvc = new TkFolderService();
		
		for (TkOrdVO tkOrdVO : OrdVOList) {
			
			// 找到場次VO (取場次時間)
			ShowSeatVO showSeatVO = ssSvc.getShowByTime(tkOrdVO.getShID());
			showList.add(showSeatVO);
			
			// 利用場次VO的SH_ID找到廳院VO (取廳院名)
			HallVO hallVO = hlSvc.findByPrimaryKey(showSeatVO.getHL_ID());
			hlList.add(hallVO);
			
			// 利用場次VO的MV_ID找到電影VO
			MovieVO movieVO = mvSvc.findByPrimaryKey(showSeatVO.getmvId());
			mvList.add(movieVO);
			
			// 算出有幾張票
			Integer dtCount = tkSvc.getDtCount(tkOrdVO.getTkOrdID());
			dtCountList.add(dtCount);
			// 算出有幾份食物
			Integer foodCount = tkSvc.getFoodCount(tkOrdVO.getTkOrdID());
			foodCountList.add(foodCount);
		}
		map.put("OrdVOList", OrdVOList);
		map.put("showList", showList);
		map.put("hlList", hlList);
		map.put("mvList", mvList);
		map.put("dtCountList", dtCountList);
		map.put("foodCountList", foodCountList);
		
		return map;
	}
	
	public Integer getDtCount(Long tkOrdID) {
		
		return dao.getDtCount(tkOrdID);
	}
	
	public Integer getFoodCount(Long tkOrdID) {
		
		return dao.getFoodCount(tkOrdID);
	}
	
	public Map<String,Object> getFoodOrdDt(Long tkOrdID) {
		
		return dao.getFoodOrdDt(tkOrdID);
	}
	
	public Map<String,Object> getOneDetail (Long tkOrdID){
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		// 獲取單張訂單 全部明細資訊
		RefundTicketService rtSvc = new RefundTicketService();
		List<TkOrdDtVO> ordDtList = rtSvc.getDtByOrd(tkOrdID);
		// 獲取這張訂單內的場次座位字串
		TkOrdService tkOrdSvc = new TkOrdService();
		TkOrdVO ordVO = tkOrdSvc.getOneTkInf(tkOrdID);
		// 取出該張訂單的場次VO
		ShowSeatService ssSvc = new ShowSeatService();
		ShowSeatVO showSeatVO = ssSvc.getShowByTime(ordVO.getShID());
		
		// 用場次VO 取出電影VO
		MovieVO movieVO = new MovieService().findByPrimaryKey(showSeatVO.getmvId());
		// 用場次VO 取出廳院VO
		HallVO hallVO = new HallService().findByPrimaryKey(showSeatVO.getHL_ID());
		// 取出該場次的座位字串 , 使用座位號的索引值取回完整的座號
		String SH_SEAT_STATE = showSeatVO.getSH_SEAT_STATE();
		
		// 取得票種名list
		List<String> tkNameList = new ArrayList<String>();
		// 取得活動方案名list
		List<String> actTitleList = new ArrayList<String>();
		// 存取轉換完成的座位號碼
		List<String> seatNumberList = new ArrayList<String>();
		for (TkOrdDtVO dt : ordDtList) {

			// 取回每一張票的票名
			String tkName = rtSvc.getTicketName(dt.getTkTypeID());
			tkNameList.add(tkName);
			// 獲取每一張票的活動方案名
			String actTitle = rtSvc.getActTitle(dt.getActID());
			actTitleList.add(actTitle);
			// 轉換座位索引值成中文座位號碼
			Integer rewSeatNumber =Integer.valueOf(dt.getSeat());
			String seatNumber = 
					SH_SEAT_STATE.substring(rewSeatNumber-4,rewSeatNumber-2) + "排" + 
					SH_SEAT_STATE.substring(rewSeatNumber-2,rewSeatNumber) + "號";
			seatNumberList.add(seatNumber);
		}
		
		// 取得餐飲名和餐飲明細
		Map<String,Object> foodOrdMap = new TkFolderService().getFoodOrdDt(tkOrdID);
		List<FdOrdDtVO>foodOrdList = (List<FdOrdDtVO>) foodOrdMap.get("foodOrdList");
		List<String>foodNameList = (List<String>) foodOrdMap.get("foodNameList");
		
		map.put("ordDtList", ordDtList); // 1. 票卷售價 2. 取票狀態
		map.put("tkNameList", tkNameList); // 3. 票種
		map.put("actTitleList", actTitleList); // 4. 方案名
		map.put("seatNumberList", seatNumberList); // 5.座位號碼
		map.put("showSeatVO", showSeatVO); // 6. 場次時間
		map.put("movieVO", movieVO); // 7. 電影名稱
		map.put("hallVO", hallVO); // 8. 廳院名稱
		map.put("foodOrdList", foodOrdList); // 9. 餐飲明細
		map.put("foodNameList", foodNameList); // 10. 餐飲名稱
		
		return map;
	}
	public Map<String,Object> updateFoodOrd(Byte fdState, Long tkOrdID) {
		// 先傳UPDATE 再回查
		dao.updateFoodOrd(fdState, tkOrdID);
		Map<String,Object> foodOrdMap = new TkFolderService().getFoodOrdDt(tkOrdID);
		return foodOrdMap;
	}
	
	public Integer verifyCode(Integer verifyEmpCode) {
		
		return dao.verifyCode(verifyEmpCode);
	}
}
