package com.level.model;

import java.util.*;

public interface LevelDAO_interface {
    public void insert(LevelVO memberLevel);				    //新增
    public void update(LevelVO memberLevel);				    //修改
    public void delete(String memberLevel	);				    //刪除
//    public LevelVO findByPrimaryKey(String Member_Level);     //查詢
//    public List<LevelVO> getAll();							    //查詢(全部)
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<LevelVO> getAll(String memberLevel); 
}


