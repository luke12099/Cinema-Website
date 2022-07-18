package com.tk_ord_dt.model;

public class TkOrdDtVO implements java.io.Serializable{
	
	private Long tkDtID;
	private Long tkOrdID;
	private Integer tkTypeID;
	private Integer actID;
	private Byte state;
	private String seat;
	private Integer sellPrice;
	public Long getTkDtID() {
		return tkDtID;
	}
	public void setTkDtID(Long tkDtID) {
		this.tkDtID = tkDtID;
	}
	public Long getTkOrdID() {
		return tkOrdID;
	}
	public void setTkOrdID(Long tkOrdID) {
		this.tkOrdID = tkOrdID;
	}	
	public Integer getTkTypeID() {
		return tkTypeID;
	}
	public void setTkTypeID(Integer tkTypeID) {
		this.tkTypeID = tkTypeID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Byte getState() {
		return state;
	}
	public void setState(Byte state) {
		this.state = state;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public Integer getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}
	
}
