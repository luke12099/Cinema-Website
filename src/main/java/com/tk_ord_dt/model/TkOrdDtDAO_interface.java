package com.tk_ord_dt.model;

import java.util.List;

public interface TkOrdDtDAO_interface {
	public void insert(TkOrdDtVO tkOrdDtVO);
    public void update(TkOrdDtVO tkOrdDtVO);
    public void delete(Long tkDtID);
    public TkOrdDtVO findByPrimaryKey(Long tkDtID);
    public List<TkOrdDtVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<TkOrdDtVO> getAll(Map<String, String[]> map); 
    
    public void insert2 (TkOrdDtVO tkOrdDtVO , java.sql.Connection con);

}
