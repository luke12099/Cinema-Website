package com.movie.model;
import java.util.*;

import com.showing.model.ShowingVO;


public interface MovieDAO_interface {
	public void insert(MovieVO movieVo);
	public void update(MovieVO movieVo);
	public void delete(Integer mvId);
	public MovieVO findByPrimaryKey(Integer mvId);
	public List<MovieVO> getAll();
	public List<MovieVO> getShowingMV();
	public List<MovieVO> getComingMV();
	public List<MovieVO> compositeQuery_Search(Map<String, String[]> map);
	
	//查詢某名稱的電影場次(一對多)(回傳 Set)
    public Set<ShowingVO> getShowingsBymvId(Integer mvId);
	
}
