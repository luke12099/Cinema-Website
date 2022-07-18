package com.merchandise_order.model;

import java.util.List;

import com.order_detail.model.OrderDetailService;
import com.order_detail.model.OrderDetailVO;

public class MerchOrdService {
	private MerchOrdDAO_interface dao;
	public MerchOrdService() {
		dao = new MerchOrdDAO();
	}
	
	public void addMerchOrd(MerchOrdVO merchOrdVo) {
		dao.insert(merchOrdVo);
	}
	
	public MerchOrdVO addMerchOrd(Integer memberID, java.sql.Timestamp merchOrdDate,Double merchOrdCount,Byte merchOrdStatus) {
		MerchOrdVO merchOrdVo = new MerchOrdVO();
		
		merchOrdVo.setMemberID(memberID);
		merchOrdVo.setMerchOrdCount(merchOrdCount);
		merchOrdVo.setMerchOrdDate(merchOrdDate);
		merchOrdVo.setMerchOrdStatus(merchOrdStatus);
		dao.insert(merchOrdVo);
		
		return merchOrdVo;
	}
	public MerchOrdVO updateMerchOrd(Integer merchOrdID, Integer memberID, java.sql.Timestamp merchOrdDate,Double merchOrdCount,Byte merchOrdStatus) {
		MerchOrdVO merchOrdVo = new MerchOrdVO();
		
		merchOrdVo.setMerchOrdID(merchOrdID);
		merchOrdVo.setMemberID(memberID);
		merchOrdVo.setMerchOrdCount(merchOrdCount);
		merchOrdVo.setMerchOrdDate(merchOrdDate);
		merchOrdVo.setMerchOrdStatus(merchOrdStatus);
		dao.update(merchOrdVo);
		
		return dao.findByPrimaryKey(merchOrdID);
		
	}
	
	public List<MerchOrdVO> getAll(){
		return dao.getAll();
	}
	public List<MerchOrdVO> getAll(Integer merberID){
		return dao.getAll(merberID);
	}
	
	public MerchOrdVO getOneMerchOrd(Integer merchOrdID) {
		return dao.findByPrimaryKey(merchOrdID);
	}

	public void updateMerchOrd(MerchOrdVO merchOrdVo) {
		dao.update(merchOrdVo);
	}
	
	public void deleteMerchOrd(Integer MerchOrdID) {
		dao.delete(MerchOrdID);
	}
	public void insertWithOrderDetail(MerchOrdVO merchOrdVo, List<OrderDetailVO> list) {	
		dao.insertWithOrderDetail(merchOrdVo, list);
	}
	
	public void resetMerchOrdCount(Integer MerchOrdID) {
		MerchOrdVO merchOrdVo = dao.findByPrimaryKey(MerchOrdID);
		OrderDetailService orderDetailSvc = new OrderDetailService();
		List<OrderDetailVO> list = orderDetailSvc.getALL(MerchOrdID);
		Double merchOrdCount = 0.0;
		for(OrderDetailVO orderDetailVo : list) {
			merchOrdCount += (orderDetailVo.getOrdCount())*(orderDetailVo.getOrdPrice());
		}
		merchOrdVo.setMerchOrdCount(merchOrdCount);
		dao.update(merchOrdVo);
	}
	
}
