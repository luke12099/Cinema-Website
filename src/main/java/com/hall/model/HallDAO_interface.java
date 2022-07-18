package com.hall.model;

import java.util.List;


public interface HallDAO_interface {
	public void insert(HallVO hallVO);
	public void update(HallVO hallVO);
	public void delete(Integer hlId);
	public HallVO findByPrimaryKey(Integer hlId);
	public List<HallVO> getAll();
}
