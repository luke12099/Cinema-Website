package com.changeSeat;

import java.util.List;

import com.hall.model.HallVO;

public class ShowSeatService {
	
	private ShowSeatDAO_interface dao;
	
	public ShowSeatService() {
		dao = new ShowSeatDAO();
	}
	
	public HallVO getSeatByHL(Integer hlId){
		return dao.getSeatByHL(hlId);
	}
	
	public List getDate (Integer hlId) {
		return dao.getDate(hlId);
	}
	
	public List<ShowSeatVO> getTimeByDate(Integer hlId,String dateOption){
		return dao.getTimeByDate(hlId,dateOption);
	}
	
	public ShowSeatVO getShowByTime (Integer SH_ID) {
		return dao.getShowByTime(SH_ID);
	}
	
	public void updateShowSeat (String SH_SEAT_STATE,Integer SH_ID) {
		dao.updateShowSeat(SH_SEAT_STATE, SH_ID);
	}
}
