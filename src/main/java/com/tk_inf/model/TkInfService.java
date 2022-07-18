package com.tk_inf.model;

import java.util.List;

public class TkInfService {
	
	private TkInfDAO_interface dao;

	public TkInfService() {
		dao = new TkInfJDBCDAO();
//		dao = new TkInfDAO();
	}

	public TkInfVO addTkInf(String tkType, Integer tkPrice, java.lang.Byte tkDI,
			String tkTypeDT) {

		TkInfVO tkInfVO = new TkInfVO();

		tkInfVO.setTkType(tkType);
		tkInfVO.setTkPrice(tkPrice);
		tkInfVO.setTkDI(tkDI);
		tkInfVO.setTkTypeDT(tkTypeDT);
		dao.insert(tkInfVO);

		return tkInfVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addTkInf(TkInfVO tkInfVO) {
		dao.insert(tkInfVO);
	}
	
	public TkInfVO updateTkInf(Integer tkTypeID, String tkType, Integer tkPrice, java.lang.Byte tkDI,
			String tkTypeDT) {

		TkInfVO tkInfVO = new TkInfVO();

		tkInfVO.setTkTypeID(tkTypeID);
		tkInfVO.setTkType(tkType);
		tkInfVO.setTkPrice(tkPrice);
		tkInfVO.setTkDI(tkDI);
		tkInfVO.setTkTypeDT(tkTypeDT);
		dao.update(tkInfVO);

		return dao.findByPrimaryKey(tkTypeID);
	}
	
	//預留給 Struts 2 用的
	public void updateTkInf(TkInfVO tkInfVO) {
		dao.update(tkInfVO);
	}

	public void deleteTkInf(Integer tkTypeID) {
		dao.delete(tkTypeID);
	}

	public TkInfVO getOneTkInf(Integer tkTypeID) {
		return dao.findByPrimaryKey(tkTypeID);
	}

	public List<TkInfVO> getAll() {
		return dao.getAll();
	}

}
