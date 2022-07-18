package com.showing.model;

import java.util.*;


public interface ShowingDAO_interface {
	public void insert(ShowingVO showingVO);
	public void update(ShowingVO showingVO);
	public void delete(Integer SH_ID);
    public ShowingVO findByPrimaryKey(Integer SH_ID);
    public List<ShowingVO> getAll();
    public List<ShowingVO> getShowingByDate(String SH_TIME);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<ShowingVO> getAll(Map<String, String[]> map);
    
  //查詢某部門的員工(一對多)(回傳 Set)
//    public Set<ShowingVO> getShowingsByMV_ID(Integer MV_ID);

}
