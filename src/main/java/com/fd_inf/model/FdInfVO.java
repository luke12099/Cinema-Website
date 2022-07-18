package com.fd_inf.model;


public class FdInfVO implements java.io.Serializable{
	private Integer fdID;
	private Byte fdType;
	private String fdName;
	private Integer fdprice;
	private String fdDT;
	private byte[] fdPicture;
	private Byte fdState;
	public Integer getFdID() {
		return fdID;
	}
	public void setFdID(Integer fdID) {
		this.fdID = fdID;
	}
	public Byte getFdType() {
		return fdType;
	}
	public void setFdType(Byte fdType) {
		this.fdType = fdType;
	}
	public String getFdName() {
		return fdName;
	}
	public void setFdName(String fdName) {
		this.fdName = fdName;
	}
	public Integer getFdprice() {
		return fdprice;
	}
	public void setFdprice(Integer fdprice) {
		this.fdprice = fdprice;
	}
	public String getFdDT() {
		return fdDT;
	}
	public void setFdDT(String fdDT) {
		this.fdDT = fdDT;
	}
	public byte[] getFdPicture() {
		return fdPicture;
	}
	public void setFdPicture(byte[] fdPicture) {
		this.fdPicture = fdPicture;
	}
	public Byte getFdState() {
		return fdState;
	}
	public void setFdState(Byte fdState) {
		this.fdState = fdState;
	}
}
