package com.emp_function.model;

public class EmpFunctionVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer fc_no;
	private String fc_name;
	private String fc_description;
	private Integer fc_category;
	
	public Integer getFc_no() {
		return fc_no;
	}
	public void setFc_no(Integer fc_no) {
		this.fc_no = fc_no;
	}
	public String getFc_name() {
		return fc_name;
	}
	public void setFc_name(String fc_name) {
		this.fc_name = fc_name;
	}
	public String getFc_description() {
		return fc_description;
	}
	public void setFc_description(String fc_description) {
		this.fc_description = fc_description;
	}
	public Integer getFc_category() {
		return fc_category;
	}
	public void setFc_category(Integer fc_category) {
		this.fc_category = fc_category;
	}
}
