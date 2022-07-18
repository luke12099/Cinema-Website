package com.cmt.model;

import java.util.*;

import com.movie.model.MovieVO;


public interface CmtDAO_interface {
	public void insert(CmtVO cmtVO);
	public void update(CmtVO cmtVO);
	public void delete(Integer CM_ID);
    public CmtVO findByPrimaryKey(Integer CM_ID);
    public List<CmtVO> getAll();
    public List<CmtVO> getCmtsByMV_ID(Integer MV_ID);
    public List<CmtVO> getCmtsBymember_ID(Integer member_ID);
    public MovieVO updateMovieTT(MovieVO movieVO);

    
    // for檢舉控制器 -更改評論狀態
    public void updateCmtState(Integer CM_ID,Integer CM_STATE);

}
