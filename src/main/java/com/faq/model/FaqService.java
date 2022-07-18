package com.faq.model;

import java.util.List;


public class FaqService {

	private FaqDAO_interface dao;

	public FaqService() {
		dao = new FaqJDBCDAO();
	}

	public FaqVO addFaq(Byte faq_class, String faq_title, String faq_content) {

		FaqVO faqVO = new FaqVO();

		//faqVO.setFaq_no(faq_no);
		faqVO.setFaq_class(faq_class);
		faqVO.setFaq_title(faq_title);
		faqVO.setFaq_content(faq_content);
		dao.insert(faqVO);

		return faqVO;
	}

	public FaqVO updateFaq(Integer faq_no, Byte faq_class, String faq_title, String faq_content) {

		FaqVO faqVO = new FaqVO();

		faqVO.setFaq_no(faq_no);
		faqVO.setFaq_class(faq_class);
		faqVO.setFaq_title(faq_title);
		faqVO.setFaq_content(faq_content);
		dao.update(faqVO);

		return faqVO;
	}

	public void deleteFaq(Integer faq_no) {
		dao.delete(faq_no);
	}

	public FaqVO getOneFaq(Integer faq_no) {
		return dao.findByPrimaryKey(faq_no);
	}

	public List<FaqVO> getAll() {
		return dao.getAll();
	}
	
	
	public List<FaqVO> getFaqClass1(){ //會員相關問題
		
		return dao.getFaqClass1();
	};
	
	public List<FaqVO> getFaqClass2(){ //影城相關問題
		
		return dao.getFaqClass2();
	};
	
	public List<FaqVO> getFaqClass3(){ //電影上映相關問題
		
		return dao.getFaqClass3();
	};
	
	public List<FaqVO> getFaqClass4(){ //其他問題
		
		return dao.getFaqClass4();
	};
}


