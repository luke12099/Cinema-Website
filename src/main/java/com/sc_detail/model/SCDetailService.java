package com.sc_detail.model;

import java.util.List;

public class SCDetailService {
	private SCDetailDAO_interface dao;
	
	public SCDetailService() {
		dao = new SCDetailDAO();
	}
	
	public void addSCDetail(SCDetailVO scDetailVo) {
		dao.insert(scDetailVo);
	}
	
	public SCDetailVO addSCDetail(Integer memberID, Integer merchID, Integer scCount) {
		SCDetailVO scDetailVo = new SCDetailVO();
		
		scDetailVo.setMemberID(memberID);
		scDetailVo.setMerchID(merchID);
		scDetailVo.setScCount(scCount);
		dao.insert(scDetailVo);
		
		return scDetailVo;
	}
	
	public SCDetailVO updateSCDetail(Integer memberID, Integer merchID, Integer scCount) {
		SCDetailVO scDetailVo = new SCDetailVO();
		
		scDetailVo.setMemberID(memberID);
		scDetailVo.setMerchID(merchID);
		scDetailVo.setScCount(scCount);
		dao.update(scDetailVo);
		
		return dao.findByPrimaryKey(memberID, merchID);
		
	}

	public List<SCDetailVO> getAll(){
		return dao.getAll();
	}
	public List<SCDetailVO> getAll(Integer memberID){
		return dao.getAll(memberID);
	}
	
	public SCDetailVO getOneSCDetail(Integer memberID, Integer merchID) {
		
		return dao.findByPrimaryKey(memberID, merchID);
	}
	
	public void updateSCDetail(SCDetailVO SCDetailVo) {
		dao.update(SCDetailVo);
	}
	
	public void deleteSCDetail(Integer memberID, Integer merchID) {
		dao.delete(memberID, merchID);
	}
}
