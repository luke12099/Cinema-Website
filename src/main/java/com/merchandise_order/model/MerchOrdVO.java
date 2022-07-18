package com.merchandise_order.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MerchOrdVO implements java.io.Serializable {
	private Integer merchOrdID;
	private Integer memberID;
	private Timestamp merchOrdDate;
	private Double merchOrdCount;
	private Byte merchOrdStatus;
	
	public MerchOrdVO() {
		super();
	}
	public MerchOrdVO(Integer memberID, Timestamp merchOrdDate, Double merchOrdCount,
			Byte merchOrdStatus) {
		super();
		this.memberID = memberID;
		this.merchOrdDate = merchOrdDate;
		this.merchOrdCount = merchOrdCount;
		this.merchOrdStatus = merchOrdStatus;
	}
	public Byte getMerchOrdStatus() {
		return merchOrdStatus;
	}
	public void setMerchOrdStatus(Byte merchOrdStatus) {
		this.merchOrdStatus = merchOrdStatus;
	}
	public Integer getMerchOrdID() {
		return merchOrdID;
	}
	public void setMerchOrdID(Integer merchOrdID) {
		this.merchOrdID = merchOrdID;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public Timestamp getMerchOrdDate() {
		return merchOrdDate;
	}
	public void setMerchOrdDate(Timestamp merchOrdDate) {
		this.merchOrdDate = merchOrdDate;
	}
	public Double getMerchOrdCount() {
		return merchOrdCount;
	}
	public void setMerchOrdCount(Double merchOrdCount) {
		this.merchOrdCount = merchOrdCount;
	}

}
