package com.order_detail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVo);
	public void update(OrderDetailVO orderDetailVo);
	public void delete(Integer merchOrdID, Integer item);
	public OrderDetailVO findByPrimaryKey(Integer merchOrdID, Integer item);
	public List<OrderDetailVO> getAll();
	public List<OrderDetailVO> getAll(Integer merchOrdID);
	
	//同時新增訂單與訂單明細
	public void insert2(OrderDetailVO orderDetailVo, java.sql.Connection con);
	void resetItem(Integer newItem, OrderDetailVO orderDetailVo);
	
	//複合查詢
	//public List<MerchVO> getAll(Map<String, String[]> map);
}
