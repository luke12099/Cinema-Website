package com.member.model;

import java.sql.Date;

public class MemberVO implements java.io.Serializable {
	private Integer member_ID; // Int MEMBER_ID x
	private String member_Level; // Char MEMBER_LEVEL x
	private String member_Email; // Varchar MEMBER_EMAIL x
	private String member_Password; // Varchar MEMBER_PASSWORD x
	private String member_Name; // Varchar MEMBER_NAME x
	private String member_Phone; // Varchar MEMBER_PHONE x
	private String member_Address; // Varchar MEMBER_ADDRESS x
	private String member_Pic; // Varchar MEMBER_PIC x
	private Integer member_Status; // Tinyint MEMBER_STATUS x
	private Integer wish_Ticket; // Int WISH_TICKET x
	private Integer bonus_Points; // Int BONUS_POINTS x
	private Integer sum_Count; // Int SUM_COUNT

	public Integer getMember_ID() {
		return member_ID;
	}

	public void setMember_ID(Integer member_ID) {
		this.member_ID = member_ID;
	}

	public String getMember_Level() {
		return member_Level;
	}

	public void setMember_Level(String member_Level) {
		this.member_Level = member_Level;
	}

	public String getMember_Email() {
		return member_Email;
	}

	public void setMember_Email(String member_Email) {
		this.member_Email = member_Email;
	}

	public String getMember_Password() {
		return member_Password;
	}

	public void setMember_Password(String member_Password) {
		this.member_Password = member_Password;
	}

	public String getMember_Name() {
		return member_Name;
	}

	public void setMember_Name(String member_Name) {
		this.member_Name = member_Name;
	}

	public String getMember_Phone() {
		return member_Phone;
	}

	public void setMember_Phone(String member_Phone) {
		this.member_Phone = member_Phone;
	}

	public String getMember_Address() {
		return member_Address;
	}

	public void setMember_Address(String member_Address) {
		this.member_Address = member_Address;
	}

	public String getMember_Pic() {
		return member_Pic;
	}

	public void setMember_Pic(String member_Pic) {
		this.member_Pic = member_Pic;
	}

	public Integer getMember_Status() {
		return member_Status;
	}

	public void setMember_Status(Integer member_Status) {
		this.member_Status = member_Status;
	}

	public Integer getWish_Ticket() {
		return wish_Ticket;
	}

	public void setWish_Ticket(Integer wish_Ticket) {
		this.wish_Ticket = wish_Ticket;
	}

	public Integer getBonus_Points() {
		return bonus_Points;
	}

	public void setBonus_Points(Integer bonus_Points) {
		this.bonus_Points = bonus_Points;
	}

	public Integer getSum_Count() {
		return sum_Count;
	}

	public void setSum_Count(Integer sum_Count) {
		this.sum_Count = sum_Count;
	}

}
