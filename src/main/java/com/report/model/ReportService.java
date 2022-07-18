package com.report.model;

import java.sql.Timestamp;
import java.util.List;

public class ReportService {
	private ReportDAO_interface dao;
	
	public ReportService() {
		dao = new ReportJDBCDAO();
	}
	
	public ReportVO insert(Integer cmId,Integer memberId,String rpText,String rpType,
			Integer rpState,Timestamp rpDate) {
		
		ReportVO reportVO = new ReportVO();
		reportVO.setCmId(cmId);
		reportVO.setMemberId(memberId);
		reportVO.setRpText(rpText);
		reportVO.setRpType(rpType);
		reportVO.setRpState(rpState);
		reportVO.setRpDate(rpDate);
		
		dao.insert(reportVO);
		return reportVO;
	}
	
	public ReportVO update(Integer rpId,Integer rpState) {
		
		ReportVO reportVO = new ReportVO();
		reportVO.setRpId(rpId);
		reportVO.setRpState(rpState);
		
		dao.update(reportVO);
		
		return reportVO;
		
	}
	
	public ReportVO findByPrimaryKey(Integer rpId) {
		
		return dao.findByPrimaryKey(rpId);
	}
	
	public List<ReportVO> getAll(){
		
		return dao.getAll();
	}
	
	public void updateSameRP(Integer cmId) {
		
		dao.updateSameRP(cmId);
	}
	
	public Integer countUndealRP() {
		
		return dao.countUndealRP();
	}
	
}

