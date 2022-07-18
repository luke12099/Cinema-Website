package com.emp_account.model;

import java.util.*;

import com.emp_privilege.model.EmpPrivilegeVO;

public interface EmpAccountDAO_interface {
	public Integer insert(EmpAccountVO empAcocuntVO);
	public Integer insertWithFunction(EmpAccountVO empAccountVO, List<EmpPrivilegeVO> lists);
	public void delete(Integer empAcocuntNo);
	public void update(EmpAccountVO empAcocuntVO);
	public void updatePassword(Integer empAccountNo, String newPassword); 
	public void updateStatus(Integer empAccountNo, Integer empStatus); 
	public EmpAccountVO findByPrimaryKey(Integer empAcocuntNo);
	public List<EmpAccountVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	public List<EmpAccountVO> getAll(Map<String, String[]> map);
	public List<EmpAccountVO> getAllDesc(); 
	public Integer getNextId(); 
	public String getPassword(Integer empAcocuntNo);

}
