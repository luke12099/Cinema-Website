package com.wish_reply.model;

import java.util.List;

public class WishReplyService {
	WishReplyDAO_interface dao;
	
	public WishReplyService() {
		dao = new WishReplyJDBCDAO();
		// 連線池
//		dao = new WishReplyDAO();
	}
	
	public WishReplyVO addWishReply(Integer wish_no, Integer member_id, String wish_msg) {
		WishReplyVO wishReplyVO = new WishReplyVO();
		wishReplyVO.setWish_no(wish_no); 
		wishReplyVO.setMember_id(member_id); 
		wishReplyVO.setWish_msg(wish_msg);
		dao.insert(wishReplyVO);
		
		return wishReplyVO;
	}
	
	public void addWishReply(WishReplyVO wishReplyVO) {
		dao.insert(wishReplyVO);
	}
	
	public void deleteWishReply(Integer wish_reono) {
		dao.delete(wish_reono);
	}
	
	public List<WishReplyVO> getOneWishEvent(Integer wish_no){
		return dao.findByWishNo(wish_no);
	}
	
	public List<WishReplyVO> getAll(){
		return dao.getAll();
	}

}
