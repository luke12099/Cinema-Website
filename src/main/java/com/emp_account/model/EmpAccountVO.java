package com.emp_account.model;

public class EmpAccountVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer emp_no;
	private String emp_email;
	private String emp_password;
	private String emp_name;
	private String emp_phone;
	private String emp_address;
	private byte[] emp_photo;
	private Integer emp_status;
	
	public Integer getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(Integer emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	public byte[] getEmp_photo() {
		return emp_photo;
	}
	public void setEmp_photo(byte[] emp_photo) {
		this.emp_photo = emp_photo;
	}
	public Integer getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}

}
