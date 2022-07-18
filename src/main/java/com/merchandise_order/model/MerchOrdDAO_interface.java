package com.merchandise_order.model;

import java.util.List;

import com.order_detail.model.OrderDetailVO;

public interface MerchOrdDAO_interface {
	public void insert(MerchOrdVO merchOrdVo);
	public void update(MerchOrdVO merchOrdVo);
	public void delete(Integer merchOrdID);
	public MerchOrdVO findByPrimaryKey(Integer merchOrdID);
	public List<MerchOrdVO> getAll();
	
	public List<MerchOrdVO> getAll(Integer memberID);
	
	//同時新增訂單與訂單明細
	public void insertWithOrderDetail(MerchOrdVO merchOrdVo, List<OrderDetailVO> list);
	//複合查詢
	//public List<MerchVO> getAll(Map<String, String[]> map);
}
