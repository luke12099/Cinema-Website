package com.actdt.model;

import java.util.*;




public interface ActdtDAO_interface {
          public void insert(ActdtVO ActdtVO); //新增
          public void update(ActdtVO ActdtVO); //修改
          
          //public void delete(Integer act_id, Integer tkTypeID);//刪除
          public void delete(Integer act_id);//刪除
          
  		  public ActdtVO findByPrimaryKey(Integer act_id, Integer tkTypeID);//查詢
  		  
//  		  public ActdtVO findOneActdt(Integer act_id);//查詢單一活動(不重複)
  		  
  		  public List<ActdtVO> findOneActdt(Integer act_id);//查詢活動列表(不重複)
  		  
  		  
          public List<ActdtVO> getAll();
          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
          //public List<ActdtVO> getAll(Map<String, String[]> map);
          
          //查詢某狀態的員工(一對多)(回傳 Set)
          public List<ActdtVO> getActdtStatus();
          
          //查詢不重複資料的活動
          public List<ActdtVO> getOnlyAct();
	       
}