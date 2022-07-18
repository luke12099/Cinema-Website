package com.order_detail.model;

import java.util.List;

public class OrderDetailService {
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		
		dao = new OrderDetailDAO();
	}
	
	public void addOrderDetail(OrderDetailVO orderDetailVo) {
		dao.insert(orderDetailVo);
	}
	
	public OrderDetailVO addOrderDetail(Integer merchOrdID, Integer item, Integer merchID, Integer ordCount, Byte ordStatus, Double ordPrice) {
		OrderDetailVO orderDetailVo = new OrderDetailVO();
		orderDetailVo.setMerchOrdID(merchOrdID);
		orderDetailVo.setItem(item);
		orderDetailVo.setMerchID(merchID);
		orderDetailVo.setOrdCount(ordCount);
		orderDetailVo.setOrdStatus(ordStatus);
		orderDetailVo.setOrdPrice(ordPrice);
		dao.insert(orderDetailVo);
		
		return orderDetailVo;
	}
	
	public OrderDetailVO updatetOrderDetail(Integer merchOrdID, Integer item, Integer merchID, Integer ordCount, Byte ordStatus, Double ordPrice) {
		OrderDetailVO orderDetailVo = new OrderDetailVO();
		orderDetailVo.setMerchOrdID(merchOrdID);
		orderDetailVo.setItem(item);
		orderDetailVo.setMerchID(merchID);
		orderDetailVo.setOrdCount(ordCount);
		orderDetailVo.setOrdStatus(ordStatus);
		orderDetailVo.setOrdPrice(ordPrice);
		dao.update(orderDetailVo);
		
		return dao.findByPrimaryKey(merchOrdID, item);
	}
	
	public List<OrderDetailVO> getALL(){
		return dao.getAll();
	}
	public List<OrderDetailVO> getALL(Integer merchOrdID){
		return dao.getAll(merchOrdID);
	}
	
	public OrderDetailVO getOneOrderDedail(Integer merchOrdID, Integer item) {
		return dao.findByPrimaryKey(merchOrdID, item);
	}
	
	public void updateOrderDetail(OrderDetailVO orderDetailVo) {
		dao.update(orderDetailVo);
	}
	
	public void deleteOrderDetail(Integer merchOrdID, Integer item) {
		dao.delete(merchOrdID, item);
	}
	
	public void resetItem(List<OrderDetailVO> list) {
		Integer newItem = 1;
		for(OrderDetailVO orderDetailVo : list) {
			dao.resetItem(newItem++, orderDetailVo);
		}
	}
	
	
}
