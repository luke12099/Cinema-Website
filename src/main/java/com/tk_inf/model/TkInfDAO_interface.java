package com.tk_inf.model;

import java.util.List;


public interface TkInfDAO_interface {
	
	public void insert(TkInfVO tkInfVO);
    public void update(TkInfVO tkInfVO);
    public void delete(Integer tkTypeID);
    public TkInfVO findByPrimaryKey(Integer tkTypeID);
    public List<TkInfVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<TkInfVO> getAll(Map<String, String[]> map); 

}
