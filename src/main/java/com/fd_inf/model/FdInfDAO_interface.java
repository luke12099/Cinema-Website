package com.fd_inf.model;

import java.util.List;


public interface FdInfDAO_interface {
	public void insert(FdInfVO fdInfVO);
    public void update(FdInfVO fdInfVO);
    public void delete(Integer fdID);
    public FdInfVO findByPrimaryKey(Integer fdID);
    public List<FdInfVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<FdInfVO> getAll(Map<String, String[]> map);
    public void onOrDownFoodStatus(Integer fdID);
}
