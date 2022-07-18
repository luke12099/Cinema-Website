package com.tk_ord.model;

import java.sql.Timestamp;

public class TkOrdVO implements java.io.Serializable{
	
	private Long tkOrdID;
	private Integer memberID;
	private Integer shID;
	private Timestamp ordTime;
	public Long getTkOrdID() {
		return tkOrdID;
	}
	public void setTkOrdID(Long tkOrdID) {
		this.tkOrdID = tkOrdID;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public Integer getShID() {
		return shID;
	}
	public void setShID(Integer shID) {
		this.shID = shID;
	}
	public Timestamp getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Timestamp ordDate) {
		this.ordTime = ordDate;
	}

}
