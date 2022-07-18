package com.fd_inf.model;

import java.util.List;
import java.util.Map;
public class FdInfService {
	

		
		private FdInfDAO_interface dao;

		public FdInfService() {
			dao = new FdInfJDBCDAO();
//			dao = new FdInfDAO();
		}

		public FdInfVO addFdInf(java.lang.Byte fdType, String fdName, Integer fdprice,
				String fdDT, byte[] fdPicture, java.lang.Byte fdState) {

			FdInfVO fdInfVO = new FdInfVO();

			fdInfVO.setFdType(fdType);
			fdInfVO.setFdName(fdName);
			fdInfVO.setFdprice(fdprice);
			fdInfVO.setFdDT(fdDT);
			fdInfVO.setFdPicture(fdPicture);
			fdInfVO.setFdState(fdState);
			dao.insert(fdInfVO);

			return fdInfVO;
		}

		//預留給 Struts 2 或 Spring MVC 用
		public void addFdInf(FdInfVO fdInfVO) {
			dao.insert(fdInfVO);
		}
		
		public FdInfVO updateFdInf(Integer fdID, java.lang.Byte fdType, String fdName, Integer fdprice,
				String fdDT, byte[] fdPicture, java.lang.Byte fdState) {

			FdInfVO fdInfVO = new FdInfVO();

			fdInfVO.setFdID(fdID);
			fdInfVO.setFdType(fdType);
			fdInfVO.setFdName(fdName);
			fdInfVO.setFdprice(fdprice);
			fdInfVO.setFdDT(fdDT);
			fdInfVO.setFdPicture(fdPicture);
			fdInfVO.setFdState(fdState);
			dao.update(fdInfVO);

			return dao.findByPrimaryKey(fdID);
		}
		
		//預留給 Struts 2 用的
		public void updateFdInf(FdInfVO fdInfVO) {
			dao.update(fdInfVO);
		}

		public void deleteFdInf(Integer fdID) {
			dao.delete(fdID);
		}

		public FdInfVO getOneFdInf(Integer fdID) {
			return dao.findByPrimaryKey(fdID);
		}

		public List<FdInfVO> getAll() {
			return dao.getAll();
		}
		
//		public List<FdInfVO> getAll(Map<String, String[]> map) {
//			return dao.getAll(map);
//		}
		public void onOrDownFoodStatus(Integer fdID) {
			dao.onOrDownFoodStatus(fdID);
		}

	}