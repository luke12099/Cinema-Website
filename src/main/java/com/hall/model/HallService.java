package com.hall.model;

import java.util.List;

public class HallService {
	private HallDAO_interface dao;
	
	public HallService() {
		dao = new HallJDBCDAO();
	}
	
	public HallVO insert(String hlName, String hlSeat, Integer hlRow, Integer hlCol, Integer hlType,
			Integer hlSeatCount) {
		HallVO hallVO = new HallVO();
		hallVO.setHlName(hlName);
		hallVO.setHlSeat(hlSeat);
		hallVO.setHlRow(hlRow);
		hallVO.setHlCol(hlCol);
		hallVO.setHlType(hlType);
		hallVO.setHlSeatCount(hlSeatCount);
		
		dao.insert(hallVO);
		return hallVO;
	}
	
	public HallVO update(Integer hlId,String hlName, String hlSeat, Integer hlRow, Integer hlCol, Integer hlType,
			Integer hlSeatCount) {
		HallVO hallVO = new HallVO();
		hallVO.setHlId(hlId);
		hallVO.setHlName(hlName);
		hallVO.setHlSeat(hlSeat);
		hallVO.setHlRow(hlRow);
		hallVO.setHlCol(hlCol);
		hallVO.setHlType(hlType);
		hallVO.setHlSeatCount(hlSeatCount);
		
		dao.update(hallVO);
		
		return hallVO;
		
	}
	public HallVO updateOriginSeat(Integer hlId,String hlName, String hlSeat, Integer hlRow, Integer hlCol, Integer hlType,
			Integer hlSeatCount) {
		HallVO hallVO = new HallVO();
		hallVO.setHlId(hlId);
		hallVO.setHlName(hlName);
		hallVO.setHlSeat(hlSeat);
		hallVO.setHlRow(hlRow);
		hallVO.setHlCol(hlCol);
		hallVO.setHlType(hlType);
		hallVO.setHlSeatCount(hlSeatCount);
		
		dao.update(hallVO);
		
		return hallVO;
		
	}
	
	public HallVO findByPrimaryKey(Integer hlId) {
		
		return dao.findByPrimaryKey(hlId);
	}
	
	public List<HallVO> getAll(){
		
		return dao.getAll();
	}
}

