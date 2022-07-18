package com.fd_ord_dt.model;


public class FdOrdDtVO implements java.io.Serializable{
	private Long fdDtID;
	private Long tkOrdID;
	private Integer fdID;
	private Integer fdCount;
	private Byte fdState;
	private Integer sellPrice;
	public Long getFdDtID() {
		return fdDtID;
	}
	public void setFdDtID(Long fdDtID) {
		this.fdDtID = fdDtID;
	}
	public Long getTkOrdID() {
		return tkOrdID;
	}
	public void setTkOrdID(Long tkOrdID) {
		this.tkOrdID = tkOrdID;
	}
	public Integer getFdID() {
		return fdID;
	}
	public void setFdID(Integer fdID) {
		this.fdID = fdID;
	}
	public Integer getFdCount() {
		return fdCount;
	}
	public void setFdCount(Integer fdCount) {
		this.fdCount = fdCount;
	}
	public Byte getFdState() {
		return fdState;
	}
	public void setFdState(Byte fdState) {
		this.fdState = fdState;
	}
	public Integer getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}
}
