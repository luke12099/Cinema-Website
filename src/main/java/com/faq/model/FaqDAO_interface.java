package com.faq.model;

import java.util.*;

public interface FaqDAO_interface {
	public void insert(FaqVO faqVO); // 新增

	public void update(FaqVO faqVO); // 修改

	public void delete(Integer faq_no); // 刪除

	public FaqVO findByPrimaryKey(Integer faq_no); // 查詢

	public List<FaqVO> getAll();

	public List<FaqVO> getFaqClass1(); // 會員相關問題

	public List<FaqVO> getFaqClass2(); // 影城相關問題

	public List<FaqVO> getFaqClass3(); // 電影上映相關問題

	public List<FaqVO> getFaqClass4(); // 其他問題

}