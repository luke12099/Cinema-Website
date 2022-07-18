package com.fd_ord_dt.model;

import java.util.List;
public class FdOrdDtService {


	private FdOrdDtDAO_interface dao;

	public FdOrdDtService() {
		dao = new FdOrdDtJDBCDAO();
//		dao = new FdOrdDtDAO();
	}

	public FdOrdDtVO addFdOrdDt(Long tkOrdID, Integer fdID, Integer fdCount,
			java.lang.Byte fdState, Integer sellPrice) {

		FdOrdDtVO fdOrdDtVO = new FdOrdDtVO();

		fdOrdDtVO.setTkOrdID(tkOrdID);
		fdOrdDtVO.setFdID(fdID);
		fdOrdDtVO.setFdCount(fdCount);
		fdOrdDtVO.setFdState(fdState);
		fdOrdDtVO.setSellPrice(sellPrice);
		dao.insert(fdOrdDtVO);

		return fdOrdDtVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addFdOrdDt(FdOrdDtVO fdOrdDtVO) {
		dao.insert(fdOrdDtVO);
	}
	
	public FdOrdDtVO updateFdOrdDt(Long fdDtID, Long tkOrdID, Integer fdID, Integer fdCount,
			java.lang.Byte fdState, Integer sellPrice) {

		FdOrdDtVO fdOrdDtVO = new FdOrdDtVO();

		fdOrdDtVO.setFdDtID(fdDtID);
		fdOrdDtVO.setTkOrdID(tkOrdID);
		fdOrdDtVO.setFdID(fdID);
		fdOrdDtVO.setFdCount(fdCount);
		fdOrdDtVO.setFdState(fdState);
		fdOrdDtVO.setSellPrice(sellPrice);
		dao.update(fdOrdDtVO);

		return dao.findByPrimaryKey(fdDtID);
	}
	
	//預留給 Struts 2 用的
	public void updateFdOrdDt(FdOrdDtVO fdOrdDtVO) {
		dao.update(fdOrdDtVO);
	}

	public void deleteFdOrdDt(Long fdDtID) {
		dao.delete(fdDtID);
	}

	public FdOrdDtVO getOneFdOrdDt(Long fdDtID) {
		return dao.findByPrimaryKey(fdDtID);
	}

	public List<FdOrdDtVO> getAll() {
		return dao.getAll();
	}
}
