package com.cnm_inf.model;

import java.util.*;

public interface Cnm_infDAO_interface {
          public void insert(Cnm_infVO cnm_infVO);
          public void update(Cnm_infVO cnm_infVO);
          public void delete(Integer CNM_INF_ID);
          public Cnm_infVO findByPrimaryKey(Integer CNM_INF_ID);
          public List<Cnm_infVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<Cnm_infVO> getAll(Map<String, String[]> map); 
}
