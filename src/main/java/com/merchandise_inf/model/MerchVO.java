package com.merchandise_inf.model;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MerchVO implements java.io.Serializable{
	private Integer merchID;
	private String merchName;
	private String merchDT;
	private Blob merchPic1;
	private Blob merchPic2;
	private Blob merchPic3;
	private Blob merchPic4;
	private Blob merchPic5;
	private Timestamp merchDate;
	private Double merchPrice;
	private String merchClass;
	private Integer soldTotal;
	private Byte merchStatus;
	private Integer merchStock; 

	public Integer getSoldTotal() {
		return soldTotal;
	}
	public void setSoldTotal(Integer soldTotal) {
		this.soldTotal = soldTotal;
	}
	@Override
	public String toString() {
		return "MerchVO [merchID=" + merchID + ", merchName=" + merchName + ", merchDT=" + merchDT + ", merchDate="
				+ merchDate + ", merchPrice=" + merchPrice + ", merchClass=" + merchClass + ", soldTotal=" + soldTotal
				+ ", merchStatus=" + merchStatus + ", merchStock=" + merchStock + "]";
	}
	public Integer getMerchID() {
		return merchID;
	}
	public void setMerchID(Integer merchID) {
		this.merchID = merchID;
	}
	public String getMerchName() {
		return merchName;
	}
	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}
	public String getMerchDT() {
		return merchDT;
	}
	public void setMerchDT(String merchDT) {
		this.merchDT = merchDT;
	}
	public Blob getMerchPic1() {
		return merchPic1;
	}
	public void setMerchPic1(Blob merchPic1) {
		this.merchPic1 = merchPic1;
	}
	public Blob getMerchPic2() {
		return merchPic2;
	}
	public void setMerchPic2(Blob merchPic2) {
		this.merchPic2 = merchPic2;
	}
	public Blob getMerchPic3() {
		return merchPic3;
	}
	public void setMerchPic3(Blob merchPic3) {
		this.merchPic3 = merchPic3;
	}
	public Blob getMerchPic4() {
		return merchPic4;
	}
	public void setMerchPic4(Blob merchPic4) {
		this.merchPic4 = merchPic4;
	}
	public Blob getMerchPic5() {
		return merchPic5;
	}
	public void setMerchPic5(Blob merchPic5) {
		this.merchPic5 = merchPic5;
	}
	public Timestamp getMerchDate() {
		return merchDate;
	}
	public void setMerchDate(Timestamp merchDate) {
		this.merchDate = merchDate;
	}
	public Double getMerchPrice() {
		return merchPrice;
	}
	public void setMerchPrice(Double merchPrice) {
		this.merchPrice = merchPrice;
	}
	public String getMerchClass() {
		return merchClass;
	}
	public void setMerchClass(String merchClass) {
		this.merchClass = merchClass;
	}
	public void setMerchStatus(Byte merchStatus) {
		this.merchStatus = merchStatus;
	}
	public Byte getMerchStatus() {
		return merchStatus;
	}
	public Integer getMerchStock() {
		return merchStock;
	}
	public void setMerchStock(Integer merchStock) {
		this.merchStock = merchStock;
	}
	
}
