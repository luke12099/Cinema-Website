package com.actdt.model;

import java.sql.Date;

//當作員工

public class ActdtVO implements java.io.Serializable{
	private Integer act_id;        //活動編號
	private Date act_date_start;   //活動發布日期
	private String act_title;      //活動標題
	private String act_subtitle;   //活動副標題
	private Integer tkTypeID;      //票種編號
	private Double act_discount;   //活動折價
	private Integer act_coupon;    //活動折扣
	private Byte act_status;       //活動狀態
	private String act_content;    //活動內容
	private String act_picture;    //活動圖片
	


	public Integer getAct_id() {
		return act_id;
	}




	public void setAct_id(Integer act_id) {
		this.act_id = act_id;
	}




	public Date getAct_date_start() {
		return act_date_start;
	}




	public void setAct_date_start(Date act_date_start) {
		this.act_date_start = act_date_start;
	}




	public String getAct_title() {
		return act_title;
	}




	public void setAct_title(String act_title) {
		this.act_title = act_title;
	}




	public String getAct_subtitle() {
		return act_subtitle;
	}




	public void setAct_subtitle(String act_subtitle) {
		this.act_subtitle = act_subtitle;
	}




	public Integer getTkTypeID() {
		return tkTypeID;
	}




	public void setTkTypeID(Integer tkTypeID) {
		this.tkTypeID = tkTypeID;
	}




	public Double getAct_discount() {
		return act_discount;
	}




	public void setAct_discount(Double act_discount) {
		this.act_discount = act_discount;
	}




	public Integer getAct_coupon() {
		return act_coupon;
	}




	public void setAct_coupon(Integer act_coupon) {
		this.act_coupon = act_coupon;
	}




	public Byte getAct_status() {
		return act_status;
	}




	public void setAct_status(Byte act_status) {
		this.act_status = act_status;
	}




	public String getAct_content() {
		return act_content;
	}




	public void setAct_content(String act_content) {
		this.act_content = act_content;
	}




	public String getAct_picture() {
		return act_picture;
	}




	public void setAct_picture(String act_picture) {
		this.act_picture = act_picture;
	}




	// 取得票種編號
	public com.tk_inf.model.TkInfVO getTkInfVO() {
		com.tk_inf.model.TkInfService TkInfSvc = new com.tk_inf.model.TkInfService();
		return TkInfSvc.getOneTkInf(tkTypeID);
	}

}
