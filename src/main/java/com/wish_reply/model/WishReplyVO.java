package com.wish_reply.model;

public class WishReplyVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer wish_reono;
	private Integer wish_no;
	private Integer member_id;
	private String wish_msg;
	
	public Integer getWish_reono() {
		return wish_reono;
	}
	public void setWish_reono(Integer wish_reono) {
		this.wish_reono = wish_reono;
	}
	public Integer getWish_no() {
		return wish_no;
	}
	public void setWish_no(Integer wish_no) {
		this.wish_no = wish_no;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public String getWish_msg() {
		return wish_msg;
	}
	public void setWish_msg(String wish_msg) {
		this.wish_msg = wish_msg;
	}

}
