package com.report.model;

import java.util.List;


public interface ReportDAO_interface {
	public void insert(ReportVO reportVO);
	public void update(ReportVO reportVO);
	public void delete(Integer hlId);
	public ReportVO findByPrimaryKey(Integer hlId);
	public List<ReportVO> getAll();
	// 一次修改指向同一則評論的檢舉
	public void updateSameRP(Integer cmId);
	// 計算未處理的檢舉數
	public Integer countUndealRP();
	
}
