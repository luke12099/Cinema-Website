package com.changeSeat;

import java.util.List;

import com.hall.model.HallVO;

public interface ShowSeatDAO_interface {
	
	// 獲取 該廳當天日期
	public List getDate(Integer hlId); 
	
	// 使用當天的日期獲取場次時段
	public List<ShowSeatVO> getTimeByDate(Integer hlId,String dateOption);
	
	// 使用場次時段獲取場次
	public ShowSeatVO getShowByTime(Integer SH_ID);
	
	// 使用hlId 獲取原始廳院資源
	public HallVO getSeatByHL(Integer hlId);
	
	// 使用SH_ID 更新場次座位狀態
	public void updateShowSeat(String SH_SEAT_STATE,Integer SH_ID);
	
}
