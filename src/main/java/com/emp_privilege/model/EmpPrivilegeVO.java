package com.emp_privilege.model;

import java.util.ArrayList;
import java.util.List;

public class EmpPrivilegeVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer emp_no;
	private Integer fc_no;
	
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public Integer getFc_no() {
		return fc_no;
	}
	public void setFc_no(Integer fc_no) {
		this.fc_no = fc_no;
	}
	
	// 取得功能名稱
	public String getFcName(){
		com.emp_function.model.EmpFunctionJDBCDAO dao = new com.emp_function.model.EmpFunctionJDBCDAO();
		return dao.findByPrimaryKey(fc_no).getFc_name();
	}
}
