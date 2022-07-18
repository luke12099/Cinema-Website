package com.ann.model;

import java.util.List;


public class AnnService {

	private AnnDAO_interface dao;

	public AnnService() {
		dao = new AnnJDBCDAO();
	}

	public AnnVO addAnn(java.sql.Date ann_date, String ann_title, String ann_content, String ann_picture) {

		AnnVO annVO = new AnnVO();

		annVO.setAnn_date(ann_date);
		annVO.setAnn_title(ann_title);
		annVO.setAnn_content(ann_content);
		annVO.setAnn_picture(ann_picture);
		dao.insert(annVO);

		return annVO;
	}
	
//	public AnnVO addAnn(java.sql.Date ann_date, String ann_title, String ann_content) {
//
//		AnnVO annVO = new AnnVO();
//
//		annVO.setAnn_date(ann_date);
//		annVO.setAnn_title(ann_title);
//		annVO.setAnn_content(ann_content);
//		dao.insert(annVO);
//
//		return annVO;
//	}
	

	public AnnVO updateAnn(Integer ann_no, java.sql.Date ann_date, String ann_title, String ann_content, String ann_picture) {

		AnnVO annVO = new AnnVO();

		annVO.setAnn_no(ann_no);
		annVO.setAnn_date(ann_date);
		annVO.setAnn_title(ann_title);
		annVO.setAnn_content(ann_content);
		annVO.setAnn_picture(ann_picture);
		dao.update(annVO);

		return annVO;
	}

	public void deleteAnn(Integer ann_no) {
		dao.delete(ann_no);
	}

	public AnnVO getOneAnn(Integer ann_no) {
		return dao.findByPrimaryKey(ann_no);
	}

	public List<AnnVO> getAll() {
		return dao.getAll();
	}
	
	public List<AnnVO> getAnnfront(){
		
		return dao.getAnnfront();
	}

}
