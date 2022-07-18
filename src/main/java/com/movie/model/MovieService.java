package com.movie.model;

import java.sql.Date;
import java.util.*;

import com.showing.model.ShowingVO;

public class MovieService {
	private MovieDAO_interface dao;
	
	public MovieService() {
		dao = new MovieJDBCDAO();
	}
	
	public MovieVO insert(String mvName,String mvEName,Integer mvLong,Integer mvLevel,
			String mvPicture,String mvDt,Date mvStDate,Date mvEdDate,String mvType,
			String mvCast,String mvDrt,String mvTler) {
		
		MovieVO movieVO = new MovieVO();
		movieVO.setMvName(mvName);
		movieVO.setMvEName(mvEName);
		movieVO.setMvLong(mvLong);
		movieVO.setMvLevel(mvLevel);
		movieVO.setMvPicture(mvPicture);
		movieVO.setMvDt(mvDt);
		movieVO.setMvStDate(mvStDate);
		movieVO.setMvEdDate(mvEdDate);
		movieVO.setMvType(mvType);
		movieVO.setMvCast(mvCast);
		movieVO.setMvDrt(mvDrt);
		movieVO.setMvTler(mvTler);
		
		dao.insert(movieVO);
		
		return movieVO;
	}
	
	public MovieVO update(Integer mvId,String mvName,String mvEName,Integer mvLong,Integer mvLevel,
			String mvPicture,String mvDt,Date mvStDate,Date mvEdDate,String mvType,
			String mvCast,String mvDrt,String mvTler) {
		
		MovieVO movieVO = new MovieVO();
		movieVO.setMvId(mvId);
		movieVO.setMvName(mvName);
		movieVO.setMvEName(mvEName);
		movieVO.setMvLong(mvLong);
		movieVO.setMvLevel(mvLevel);
		movieVO.setMvPicture(mvPicture);
		movieVO.setMvDt(mvDt);
		movieVO.setMvStDate(mvStDate);
		movieVO.setMvEdDate(mvEdDate);
		movieVO.setMvType(mvType);
		movieVO.setMvCast(mvCast);
		movieVO.setMvDrt(mvDrt);
		movieVO.setMvTler(mvTler);
		dao.update(movieVO);
		
		return movieVO;
	}
	
	public void delete(Integer mvId) {
		
		dao.delete(mvId);
	}
	
	public MovieVO findByPrimaryKey(Integer mvId) {
		
		return dao.findByPrimaryKey(mvId);
	}
	
	public List<MovieVO> getAll() {
		
		return dao.getAll();
	}
	
	public List<MovieVO> compositeQuery_Search(Map<String, String[]> map){
		  return dao.compositeQuery_Search(map);
		 }
	
	public List<MovieVO> getShowingMV(){
		
		return dao.getShowingMV();
	};
	
	public List<MovieVO> getComingMV(){
		
		return dao.getComingMV();
	};
	
	public MovieVO getOneMovie(Integer mvId) {
		return dao.findByPrimaryKey(mvId);
	}
	
	public Set<ShowingVO> getShowingsBymvId(Integer mvId) {
		return dao.getShowingsBymvId(mvId);
	}
}
	