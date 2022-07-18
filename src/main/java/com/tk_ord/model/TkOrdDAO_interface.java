package com.tk_ord.model;

import java.util.List;
import java.util.Set;

import com.fd_ord_dt.model.FdOrdDtVO;
import com.tk_ord_dt.model.TkOrdDtVO;

public interface TkOrdDAO_interface {
	public void insert(TkOrdVO tkOrdVO);
    public void update(TkOrdVO tkOrdVO);
    public void delete(Long tkOrdID);
    public TkOrdVO findByPrimaryKey(Long tkOrdID);
    public List<TkOrdVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<TkOrdVO> getAll(Map<String, String[]> map); 
    //查詢某部門的員工(一對多)(回傳 Set)
    public Set<TkOrdDtVO> getTkOrdDtsByTkOrdID(Long tkOrdID);
    
    public Set<FdOrdDtVO> getFdOrdDtsByTkOrdID(Long tkOrdID);
    
    //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insertWithTkOrdDtsAndFdOrdDts(TkOrdVO tkOrdVO , List<TkOrdDtVO> list , List<FdOrdDtVO> list2);
    
}
