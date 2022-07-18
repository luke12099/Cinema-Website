package com.level.model;

import java.util.List;

public class LevelService {
	
	private LevelDAO_interface dao;
	
	public LevelService() {
//		dao = new EmpJDBCDAO();		
		dao = new LevelJDBCDAO();
	}
	
	public LevelVO addLevel(String member_level,Integer max_count,Integer min_count,String level_description) {

		LevelVO levelVO = new LevelVO();

		levelVO.setMember_level(member_level);
		levelVO.setMax_count(max_count);
		levelVO.setMin_count(min_count);
		levelVO.setLevel_description(level_description);
	
		dao.insert(levelVO);

		return levelVO;
	}

	//預留給 Struts 2 或 Spring MVC 用
	public void addLevel(LevelVO levelVO) {
		dao.insert(levelVO);
	}
	
	
	//預留給 Struts 2 用的
	public void updateLevel(LevelVO levelVO) {
		dao.update(levelVO);
	}

	public void deleteLevel(String member_level) {
		dao.delete(member_level);
	}

//	public LevelVO getOneLevel(String member_level) {
//		return dao.findByPrimaryKey(member_level);
//	}
//
//	public List<LevelVO> getAll() {
//		return dao.getAll();
//	}
}


