package com.ann.model;

import java.sql.Date;

public class AnnVO implements java.io.Serializable{
	private Integer ann_no;       //公告編號
	private Date ann_date;        //公告日期
	private String ann_title;     //公告標題
	private String ann_content;   //公告內容
//	private byte[] ann_picture;   //公告圖片
	private String ann_picture;   //公告圖片
	public Integer getAnn_no() {
		return ann_no;
	}
	public void setAnn_no(Integer ann_no) {
		this.ann_no = ann_no;
	}
	public Date getAnn_date() {
		return ann_date;
	}
	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}
	public String getAnn_title() {
		return ann_title;
	}
	public void setAnn_title(String ann_title) {
		this.ann_title = ann_title;
	}
	public String getAnn_content() {
		return ann_content;
	}
	public void setAnn_content(String ann_content) {
		this.ann_content = ann_content;
	}
	public String getAnn_picture() {
		return ann_picture;
	}
	public void setAnn_picture(String ann_picture) {
		this.ann_picture = ann_picture;
	}

	
}
