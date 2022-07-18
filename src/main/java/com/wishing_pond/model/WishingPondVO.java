package com.wishing_pond.model;

import java.sql.*;

public class WishingPondVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer wish_no;
	private String wish_name;
	private Date wish_start;
	private Date wish_end;
	private Integer top_one;
	
	public String getWish_name() {
		return wish_name;
	}
	public void setWish_name(String wish_name) {
		this.wish_name = wish_name;
	}
	public Integer getTop_one() {
		return top_one;
	}
	public void setTop_one(Integer top_one) {
		this.top_one = top_one;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getWish_no() {
		return wish_no;
	}
	public void setWish_no(Integer wish_no) {
		this.wish_no = wish_no;
	}
	public Date getWish_start() {
		return wish_start;
	}
	public void setWish_start(Date wish_start) {
		this.wish_start = wish_start;
	}
	public Date getWish_end() {
		return wish_end;
	}
	public void setWish_end(Date wish_end) {
		this.wish_end = wish_end;
	}
	
	// 取得電影資訊
	public com.movie.model.MovieVO getMvVO() {
		com.movie.model.MovieService mvSvc = new com.movie.model.MovieService();
		return mvSvc.findByPrimaryKey(top_one);
	}
}
