package com.tk_ord_dt.model;

import java.util.List;

public class TkOrdDtService {
	
	private TkOrdDtDAO_interface dao;

	public TkOrdDtService() {
		dao = new TkOrdDtJDBCDAO();
//		dao = new TkOrdDtDAO();
	}

	public TkOrdDtVO addTkOrdDt(Long tkOrdID, Integer tkTypeID, Integer actID, java.lang.Byte state,
			String seat, Integer sellPrice) {

		TkOrdDtVO tkOrdDtVO = new TkOrdDtVO();

		tkOrdDtVO.setTkOrdID(tkOrdID);
		tkOrdDtVO.setTkTypeID(tkTypeID);
		tkOrdDtVO.setActID(actID);
		tkOrdDtVO.setState(state);
		tkOrdDtVO.setSeat(seat);
		tkOrdDtVO.setSellPrice(sellPrice);
		dao.insert(tkOrdDtVO);

		return tkOrdDtVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addTkOrdDt(TkOrdDtVO tkOrdDtVO) {
		dao.insert(tkOrdDtVO);
	}
	
	public TkOrdDtVO updateTkOrdDt(Long tkDtID, Long tkOrdID, Integer tkTypeID, Integer actID, java.lang.Byte state,
			String seat, Integer sellPrice) {

		TkOrdDtVO tkOrdDtVO = new TkOrdDtVO();

		tkOrdDtVO.setTkDtID(tkDtID);
		tkOrdDtVO.setTkOrdID(tkOrdID);
		tkOrdDtVO.setTkTypeID(tkTypeID);
		tkOrdDtVO.setActID(actID);
		tkOrdDtVO.setState(state);
		tkOrdDtVO.setSeat(seat);
		tkOrdDtVO.setSellPrice(sellPrice);
		dao.update(tkOrdDtVO);

		return dao.findByPrimaryKey(tkDtID);
	}
	
	//預留給 Struts 2 用的
	public void updateTkOrdDt(TkOrdDtVO tkOrdDtVO) {
		dao.update(tkOrdDtVO);
	}

	public void deleteTkOrdDt(Long tkDtID) {
		dao.delete(tkDtID);
	}

	public TkOrdDtVO getOneTkOrdDt(Long tkDtID) {
		return dao.findByPrimaryKey(tkDtID);
	}

	public List<TkOrdDtVO> getAll() {
		return dao.getAll();
	}

}
