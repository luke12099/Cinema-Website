package com.showing.model;

import java.sql.Timestamp;
import java.util.*;

public class ShowingService {

	private ShowingDAO_interface dao;

	public ShowingService() {
		dao = new ShowingDAO();
	}
	
	public ShowingVO addShowing(Integer mvId, Integer HL_ID,
			Integer SH_STATE, String SH_SEAT_STATE, Timestamp SH_TIME, Integer SH_TYPE) {

		ShowingVO showingVO = new ShowingVO();

		showingVO.setmvId(mvId);
		showingVO.setHL_ID(HL_ID);
		showingVO.setSH_STATE(SH_STATE);
		showingVO.setSH_SEAT_STATE(SH_SEAT_STATE);
		showingVO.setSH_TIME(SH_TIME);
		showingVO.setSH_TYPE(SH_TYPE);
		dao.insert(showingVO);

		return showingVO;
	}
	
	public ShowingVO addShowing(ShowingVO showingVO) {
		dao.insert(showingVO);
		return showingVO;
	}
	
	public ShowingVO updateShowing(Integer SH_ID, Integer mvId, Integer HL_ID,
			Integer SH_STATE, String SH_SEAT_STATE, Timestamp SH_TIME, Integer SH_TYPE) {
		
		ShowingVO showingVO = new ShowingVO();
		
		showingVO.setSH_ID(SH_ID);
		showingVO.setmvId(mvId);
		showingVO.setHL_ID(HL_ID);
		showingVO.setSH_STATE(SH_STATE);
		showingVO.setSH_SEAT_STATE(SH_SEAT_STATE);
		showingVO.setSH_TIME(SH_TIME);
		showingVO.setSH_TYPE(SH_TYPE);
		dao.update(showingVO);
		
		return showingVO;
	}
	
	public void deleteShowing(Integer SH_ID) {
		dao.delete(SH_ID);
	}
	
	public ShowingVO getOneShowing(Integer SH_ID) {
		return dao.findByPrimaryKey(SH_ID);
	}

	public List<ShowingVO> getAll() {
		return dao.getAll();
	}

	public List<ShowingVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public List<ShowingVO> getShowingByDate(String SH_TIME){
		return dao.getShowingByDate(SH_TIME);
	}
}
