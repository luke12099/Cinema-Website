package com.code.model;

import java.util.List;

public class CodeService {

	private CodeDAO_interface dao;

	public CodeService() {
		dao = new CodeJDBCDAO();
//		dao = new CodeDAO();
	}

	public CodeVO updateCode(Integer code) {

		CodeVO codeVO = new CodeVO();

		codeVO.setCode(code);

		dao.update(codeVO);

		return dao.findByPrimaryKey(code);
	}
	
	//預留給 Struts 2 用的
	public void updateCode(CodeVO codeVO) {
		dao.update(codeVO);
	}



	public List<CodeVO> getAll() {
		return dao.getAll();
	}
}
