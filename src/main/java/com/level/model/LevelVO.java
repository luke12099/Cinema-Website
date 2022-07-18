package com.level.model;

public class LevelVO implements java.io.Serializable{
	private String member_level; //Char
	private Integer max_count; //Int
	private Integer min_count; //Int
	private String level_description; //Varchar
	
	public String getMember_level() {
		return member_level;
	}
	public void setMember_level(String member_level) {
		this.member_level = member_level;
	}
	public Integer getMax_count() {
		return max_count;
	}
	public void setMax_count(Integer max_count) {
		this.max_count = max_count;
	}
	public Integer getMin_count() {
		return min_count;
	}
	public void setMin_count(Integer min_count) {
		this.min_count = min_count;
	}
	public String getLevel_description() {
		return level_description;
	}
	public void setLevel_description(String level_description) {
		this.level_description = level_description;
	}

	

}

