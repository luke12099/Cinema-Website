package com.fd_ord_dt.model;

import java.util.List;




public interface FdOrdDtDAO_interface {
	
	public void insert(FdOrdDtVO fdOrdDtVO);
    public void update(FdOrdDtVO fdOrdDtVO);
    public void delete(Long fdDtID);
    public FdOrdDtVO findByPrimaryKey(Long fdDtID);
    public List<FdOrdDtVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<FdOrdDtVO> getAll(Map<String, String[]> map); 
    
    public void insert2 (FdOrdDtVO fdOrdDtVO , java.sql.Connection con);
    
}