package com.report.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReportVO {
	private Integer rpId;
	private Integer cmId;
	private Integer memberId;
	private String rpText;
	private String rpType;
	private Integer rpState;
	private Timestamp rpDate;
	
	
	
	public ReportVO() {
		super();
		
	}
	public ReportVO(Integer rpId, Integer rpState) {
		super();
		this.rpId = rpId;
		this.rpState = rpState;
	}
	public Integer getRpId() {
		return rpId;
	}
	public void setRpId(Integer rpId) {
		this.rpId = rpId;
	}
	public Integer getCmId() {
		return cmId;
	}
	public void setCmId(Integer cmId) {
		this.cmId = cmId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getRpText() {
		return rpText;
	}
	public void setRpText(String rpText) {
		this.rpText = rpText;
	}
	public String getRpType() {
		return rpType;
	}
	public void setRpType(String rpType) {
		this.rpType = rpType;
	}
	public Integer getRpState() {
		return rpState;
	}
	public void setRpState(Integer rpState) {
		this.rpState = rpState;
	}
	public Timestamp getRpDate() {
		return rpDate;
	}
	public void setRpDate(Timestamp rpDate) {
		this.rpDate = rpDate;
	}
	
	

	
	
	
	
}
