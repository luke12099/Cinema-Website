package com.merchandise_inf.model;

import java.util.List;

public interface MerchDAO_interface {
	public void insert(MerchVO merchVo);
	public void update(MerchVO mercVo);
	public void delete(Integer merchID);
	public MerchVO findByPrimaryKey(Integer merchID);
	public List<MerchVO> getAll();//複合查詢
	public List<MerchVO> getByName(String searchName);
	public List<MerchVO> getAll(Double minPrice, Double maxPrice);
	public List<MerchVO> getNewest(Double minPrice, Double maxPrice);
	public List<MerchVO> getMostSell(Double minPrice, Double maxPrice);
	public List<MerchVO> getByclass(String merchClass);
	public List<MerchVO> getBySearch(String merchName, Double min, Double max);
}
