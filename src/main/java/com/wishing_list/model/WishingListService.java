package com.wishing_list.model;

import java.util.List;

public class WishingListService {
	WishingListDAO_interface dao;
	
	public WishingListService() {
		dao = new WishingListJDBCDAO();
	}
	
	public WishingListVO addWishingList(Integer wish_no, Integer mv_id) {
		WishingListVO wishingListVO = new WishingListVO();
		wishingListVO.setWish_no(wish_no);
		wishingListVO.setMv_id(mv_id);
		dao.insert(wishingListVO);
		
		return wishingListVO;
	}
	
	public void addWishingList(WishingListVO wishingListVO) {
		dao.insert(wishingListVO);
	}
	// 好像多寫了
//	public void updateWishingList(Integer wish_no, Integer mv_id, Integer wish_count) {
//		WishingListVO wishingListVO = new WishingListVO();
//		wishingListVO.setWish_no(wish_no);
//		wishingListVO.setMv_id(mv_id);
//		wishingListVO.setWish_count(wish_count);
//		dao.update(wishingListVO);
//	}
	
	public void updateWishingList(WishingListVO wishingListVO) {
		dao.update(wishingListVO);
	}
	
	public void deleteWishingList(Integer wish_no, Integer mv_id) {
		dao.delete(wish_no, mv_id);
	}
	
	public List<WishingListVO> getOneWishingPond(Integer wish_no) {
		return dao.findByWishNo(wish_no);
	}

	public List<WishingListVO> getAll(){
		return dao.getAll();
	}

}
