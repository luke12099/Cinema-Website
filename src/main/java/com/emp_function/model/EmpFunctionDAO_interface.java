package com.emp_function.model;

import java.util.*;

public interface EmpFunctionDAO_interface {
//	public void insert(EmpFunctionVO empFunctionVO);
//	public void delete(Integer empFunctionNo);
//	public void update(EmpFunctionVO empFunctionVO);
	public EmpFunctionVO findByPrimaryKey(Integer empFunctionNo);
	public List<EmpFunctionVO> getAll();
}
