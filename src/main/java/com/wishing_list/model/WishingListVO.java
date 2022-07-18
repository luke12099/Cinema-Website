package com.wishing_list.model;

public class WishingListVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer wish_no;
	private Integer mv_id;
	private Integer wish_count;
	
	public Integer getWish_no() {
		return wish_no;
	}
	public void setWish_no(Integer wish_no) {
		this.wish_no = wish_no;
	}
	public Integer getMv_id() {
		return mv_id;
	}
	public void setMv_id(Integer mv_id) {
		this.mv_id = mv_id;
	}
	public Integer getWish_count() {
		return wish_count;
	}
	public void setWish_count(Integer wish_count) {
		this.wish_count = wish_count;
	}
	
	// 取得電影資訊
	public com.movie.model.MovieVO getMvVO() {
		com.movie.model.MovieService mvSvc = new com.movie.model.MovieService();
		return mvSvc.findByPrimaryKey(mv_id);
	}
	// 取得活動資訊
	public com.wishing_pond.model.WishingPondVO getWishVO() {
		com.wishing_pond.model.WishingPondService wishSvc = new com.wishing_pond.model.WishingPondService();
		return wishSvc.getOneWishingPond(wish_no);
	}
	
}
