package com.cmt.model;
import java.sql.Timestamp;

import com.member.model.*;
import com.movie.model.*;

public class CmtVO implements java.io.Serializable{
	private Integer CM_ID;
	private Integer MEMBER_ID;
	private Integer MV_ID;
	private String CM_TEXT;
	private Integer CM_LIKE;
	private Integer CM_STAR;
	private Integer CM_STATE;
	private Timestamp CM_DATE;
	
	
	public Integer getCM_ID() {
		return CM_ID;
	}
	public void setCM_ID(Integer cM_ID) {
		CM_ID = cM_ID;
	}
	public Integer getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(Integer mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public Integer getMV_ID() {
		return MV_ID;
	}
	public void setMV_ID(Integer mV_ID) {
		MV_ID = mV_ID;
	}
	public String getCM_TEXT() {
		return CM_TEXT;
	}
	public void setCM_TEXT(String cM_TEXT) {
		CM_TEXT = cM_TEXT;
	}
	public Integer getCM_LIKE() {
		return CM_LIKE;
	}
	public void setCM_LIKE(Integer cM_LIKE) {
		CM_LIKE = cM_LIKE;
	}
	public Integer getCM_STAR() {
		return CM_STAR;
	}
	public void setCM_STAR(Integer cM_STAR) {
		CM_STAR = cM_STAR;
	}
	public Integer getCM_STATE() {
		return CM_STATE;
	}
	public void setCM_STATE(Integer cM_STATE) {
		CM_STATE = cM_STATE;
	}
	public Timestamp getCM_DATE() {
		return CM_DATE;
	}
	public void setCM_DATE(Timestamp cM_DATE) {
		CM_DATE = cM_DATE;
	}
	public MemberVO getMemberVO() {
		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.getOneMember(MEMBER_ID);
		return memberVO;
	}
	public MovieVO getMovieVO() {
		MovieService movieSvc = new MovieService();
		MovieVO movieVO = movieSvc.getOneMovie(MV_ID);
		return movieVO;
	}
	
	
	
}