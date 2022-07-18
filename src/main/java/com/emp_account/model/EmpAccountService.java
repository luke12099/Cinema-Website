package com.emp_account.model;

import java.util.List;

import com.emp_privilege.model.EmpPrivilegeVO;

public class EmpAccountService {
	private EmpAccountDAO_interface dao;
	
	public EmpAccountService(){
		dao = new EmpAccountJDBCDAO();
		// 連線池板
//		dao = new EmpAccountDAO();
	}
	
	public EmpAccountVO addEmp(String emp_email, String emp_password, String emp_name, 
			String emp_phone, String emp_address, byte[] emp_photo, Integer emp_status) {
		
			EmpAccountVO empAccountVO = new EmpAccountVO();
			
			empAccountVO.setEmp_email(emp_email);
			empAccountVO.setEmp_password(emp_password);
			empAccountVO.setEmp_name(emp_name);
			empAccountVO.setEmp_phone(emp_phone);
			empAccountVO.setEmp_address(emp_address);
			empAccountVO.setEmp_photo(emp_photo);
			empAccountVO.setEmp_status(emp_status);
			Integer emp_no = dao.insert(empAccountVO);
			empAccountVO.setEmp_no(emp_no);
			
			return empAccountVO;
	}

	public EmpAccountVO addEmp(EmpAccountVO empAccountVO, List<EmpPrivilegeVO> lists) {
		Integer emp_no = dao.insertWithFunction(empAccountVO, lists);
		empAccountVO.setEmp_no(emp_no);
		return empAccountVO;
	}
	
	//預留給 Struts 2 或 Spring MVC 用
	public void addEmp(EmpAccountVO empAccountVO) {
		dao.insert(empAccountVO);
	}
	
	public EmpAccountVO updateEmp(Integer emp_no, String emp_email, String emp_password, String emp_name, 
			String emp_phone, String emp_address, byte[] emp_photo, Integer emp_status) {
		
		EmpAccountVO empAccountVO = new EmpAccountVO();
		
		empAccountVO.setEmp_no(emp_no);
		empAccountVO.setEmp_email(emp_email);
		empAccountVO.setEmp_password(emp_password);
		empAccountVO.setEmp_name(emp_name);
		empAccountVO.setEmp_phone(emp_phone);
		empAccountVO.setEmp_address(emp_address);
		empAccountVO.setEmp_photo(emp_photo);
		empAccountVO.setEmp_status(emp_status);
		dao.update(empAccountVO);
		
		return dao.findByPrimaryKey(emp_no);
	}
	
	public void updateEmp(EmpAccountVO empAccountVO) {
		dao.update(empAccountVO);
	}
	
	public void resetPassword(Integer empAccountNo) {
		dao.updatePassword(empAccountNo, "0000");
	}
	
	public void updateStatus(Integer empAccountNo, Integer empStatus) {
		dao.updateStatus(empAccountNo, empStatus);
	}
	
	public void deleteEmp(Integer emp_no) {
		dao.delete(emp_no);
	}
	
	public EmpAccountVO getOneEmp(Integer emp_no) {
		return dao.findByPrimaryKey(emp_no);
	}

	public List<EmpAccountVO> getAll(){
		return dao.getAll();
	}
	
	public List<EmpAccountVO> getAllDesc(){
		return dao.getAllDesc();
	}
	
	public Integer getNextId() {
		return dao.getNextId();
	}
	
	public String getPassword(Integer emp_no) {
		return dao.getPassword(emp_no);
	}
	
}
