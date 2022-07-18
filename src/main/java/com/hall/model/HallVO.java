package com.hall.model;
import java.sql.Date;

public class HallVO implements java.io.Serializable{
	private Integer hlId;
	private String hlName;
	private String hlSeat;
	private Integer hlRow;
	private Integer hlCol;
	private Integer hlType;
	private Integer hlSeatCount;
	
	public Integer getHlId() {
		return hlId;
	}
	public void setHlId(Integer hlId) {
		this.hlId = hlId;
	}
	public String getHlName() {
		return hlName;
	}
	public void setHlName(String hlName) {
		this.hlName = hlName;
	}
	public String getHlSeat() {
		return hlSeat;
	}
	public void setHlSeat(String hlSeat) {
		this.hlSeat = hlSeat;
	}
	public Integer getHlRow() {
		return hlRow;
	}
	public void setHlRow(Integer hlRow) {
		this.hlRow = hlRow;
	}
	public Integer getHlCol() {
		return hlCol;
	}
	public void setHlCol(Integer hlCol) {
		this.hlCol = hlCol;
	}
	public Integer getHlType() {
		return hlType;
	}
	public void setHlType(Integer hlType) {
		this.hlType = hlType;
	}
	public Integer getHlSeatCount() {
		return hlSeatCount;
	}
	public void setHlSeatCount(Integer hlSeatCount) {
		this.hlSeatCount = hlSeatCount;
	}
	
	
	
}
