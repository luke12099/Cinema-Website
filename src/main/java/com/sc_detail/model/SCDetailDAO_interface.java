package com.sc_detail.model;

import java.util.List;

import com.merchandise_inf.model.MerchVO;

public interface SCDetailDAO_interface {
	public void insert(SCDetailVO scDetailVo);
	public void update(SCDetailVO scDetailVo);
	public void delete(Integer memberID, Integer merchID);
	public SCDetailVO findByPrimaryKey(Integer memberID, Integer merchID);
	public List<SCDetailVO> getAll();
	public List<SCDetailVO> getAll(Integer memberID);
	//複合查詢
	//public List<MerchVO> getAll(Map<String, String[]> map);
}
