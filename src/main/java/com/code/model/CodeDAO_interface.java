package com.code.model;

import java.util.List;

public interface CodeDAO_interface {
	
    public void update(CodeVO codeVO);
    public CodeVO findByPrimaryKey(Integer code);
    public List<CodeVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<CodeVO> getAll(Map<String, String[]> map); 
}
