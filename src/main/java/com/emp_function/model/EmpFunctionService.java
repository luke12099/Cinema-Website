package com.emp_function.model;

import java.util.List;

public class EmpFunctionService {
	EmpFunctionDAO_interface dao;
	
	public EmpFunctionService() {
		dao = new EmpFunctionJDBCDAO();
		// 連線池版
//		dao = new EmpFunctionDAO();
	}
	
	public EmpFunctionVO getOneFunc(Integer fc_no) {
		return dao.findByPrimaryKey(fc_no);
	}

	public List<EmpFunctionVO> getAll(){
		return dao.getAll();
	}

}
