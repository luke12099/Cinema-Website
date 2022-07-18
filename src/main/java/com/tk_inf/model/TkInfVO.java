package com.tk_inf.model;

public class TkInfVO implements java.io.Serializable{
	
	private Integer tkTypeID;
	private String tkType;
	private Integer tkPrice;
	private Byte tkDI;
	private String tkTypeDT;
	public Integer getTkTypeID() {
		return tkTypeID;
	}
	public void setTkTypeID(Integer tkTypeID) {
		this.tkTypeID = tkTypeID;
	}
	public String getTkType() {
		return tkType;
	}
	public void setTkType(String tkType) {
		this.tkType = tkType;
	}
	public Integer getTkPrice() {
		return tkPrice;
	}
	public void setTkPrice(Integer tkPrice) {
		this.tkPrice = tkPrice;
	}
	public Byte getTkDI() {
		return tkDI;
	}
	public void setTkDI(Byte tkDI) {
		this.tkDI = tkDI;
	}
	public String getTkTypeDT() {
		return tkTypeDT;
	}
	public void setTkTypeDT(String tkTypeDT) {
		this.tkTypeDT = tkTypeDT;
	}


}
