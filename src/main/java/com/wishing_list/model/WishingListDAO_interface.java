package com.wishing_list.model;

import java.sql.Connection;
import java.util.*;

public interface WishingListDAO_interface {
	public void insert(WishingListVO wishingListVO);
	public void insert(WishingListVO wishingListVO, Connection con);
	public void delete(Integer wishNo, Integer mvId);
	public void delete(WishingListVO wishingListVO, Connection con);
	public void update(WishingListVO wishingListVO);
	public List<WishingListVO> findByWishNo(Integer wishNo); 
	public List<WishingListVO> getAll(); 
}
