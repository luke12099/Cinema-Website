package com.wish_reply.model;

import java.util.*;

public interface WishReplyDAO_interface {
	public void insert(WishReplyVO wishReplyVO);
//	public void update(WishReplyVO wishReplyVO);
	public void delete(Integer replyNo);
	public List<WishReplyVO> findByWishNo(Integer wishNo);
	public List<WishReplyVO> getAll();
}
