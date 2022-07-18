package com.emp_privilege.model;

import java.sql.Connection;
import java.util.List;

public class EmpPrivilegeService {
	EmpPrivilegeDAO_interface dao;
	
	public EmpPrivilegeService() {
		dao = new EmpPrivilegeJDBCDAO();
		// 連線池版
//		dao = new EmpPrivilegeDAO();
	}
	
	public EmpPrivilegeVO addPrivilege(Integer emp_no, Integer fc_no) {
		EmpPrivilegeVO empPrivilegeVO = new EmpPrivilegeVO();
		empPrivilegeVO.setEmp_no(emp_no);
		empPrivilegeVO.setFc_no(fc_no);
		dao.insert(empPrivilegeVO);
		
		return empPrivilegeVO;
	}
	
	public void addPrivilege(EmpPrivilegeVO empPrivilegeVO) {
		dao.insert(empPrivilegeVO);
	}
	
	public void deletePrivilege(EmpPrivilegeVO empPrivilegeVO) {
		dao.delete(empPrivilegeVO);
	}
	
	public void updatePrivilege(List<EmpPrivilegeVO> empPrivilegeVOs) {
		dao.update(empPrivilegeVOs);
	}
	
	public List<EmpPrivilegeVO> getOneEmpPrivileges(Integer emp_no) {
		return dao.findByEmpNo(emp_no);
	}

	public List<EmpPrivilegeVO> getAll(){
		return dao.getAll();
	}
}
