package com.cnm_inf.model;

import java.util.*;

public class Cnm_infService {

	private Cnm_infDAO_interface dao;

	public Cnm_infService() {
		dao = new Cnm_infJDBCDAO();
	}
	
	public Cnm_infVO addCnm_inf(String CNM_DT, String CNM_TEL, String CNM_EM,
			String CNM_LC, String CNM_TRP) {

		Cnm_infVO cnm_infVO = new Cnm_infVO();

		cnm_infVO.setCNM_DT(CNM_DT);
		cnm_infVO.setCNM_TEL(CNM_TEL);
		cnm_infVO.setCNM_EM(CNM_EM);
		cnm_infVO.setCNM_LC(CNM_LC);
		cnm_infVO.setCNM_TRP(CNM_TRP);
		dao.insert(cnm_infVO);

		return cnm_infVO;
	}
	
	public Cnm_infVO updateCnm_inf(String CNM_DT, String CNM_TEL, String CNM_EM,
			String CNM_LC, String CNM_TRP, Integer CNM_INF_ID) {
		
		Cnm_infVO cnm_infVO = new Cnm_infVO();
		
		cnm_infVO.setCNM_DT(CNM_DT);
		cnm_infVO.setCNM_TEL(CNM_TEL);
		cnm_infVO.setCNM_EM(CNM_EM);
		cnm_infVO.setCNM_LC(CNM_LC);
		cnm_infVO.setCNM_TRP(CNM_TRP);
		cnm_infVO.setCNM_TRP(CNM_TRP);
		cnm_infVO.setCNM_INF_ID(CNM_INF_ID);
		dao.update(cnm_infVO);
		
		return cnm_infVO;
	}

	public List<Cnm_infVO> getAll() {
		return dao.getAll();
	}

	public Cnm_infVO getOneCnm_inf(Integer CNM_INF_ID) {
		return dao.findByPrimaryKey(CNM_INF_ID);
	}


	public void deleteCnm_inf(Integer CNM_INF_ID) {
		dao.delete(CNM_INF_ID);
	}

}
