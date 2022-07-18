package com.movie.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class MovieVO implements java.io.Serializable{
	private Integer mvId;
	private String mvName;
	private String mvEName;
	private Integer mvLong;
	private Integer mvLevel;
	private String mvPicture;
	private String mvDt;
	private Date mvStDate;
	private Date mvEdDate;
	private String mvType;
	private String mvCast;
	private String mvDrt;
	private String mvTler;
	private Integer mvTtCm;
	private Integer mvTtStar;
	
	public Integer getMvId() {
		return mvId;
	}
	public void setMvId(Integer mvId) {
		this.mvId = mvId;
	}
	public String getMvName() {
		return mvName;
	}
	public void setMvName(String mvName) {
		this.mvName = mvName;
	}
	public String getMvEName() {
		return mvEName;
	}
	public void setMvEName(String mvEName) {
		this.mvEName = mvEName;
	}
	public Integer getMvLong() {
		return mvLong;
	}
	public void setMvLong(Integer mvLong) {
		this.mvLong = mvLong;
	}
	public Integer getMvLevel() {
		return mvLevel;
	}
	public void setMvLevel(Integer mvLevel) {
		this.mvLevel = mvLevel;
	}
	public String getMvPicture() {
		return mvPicture;
	}
	public void setMvPicture(String mvPicture) {
		this.mvPicture = mvPicture;
	}
	public String getMvDt() {
		return mvDt;
	}
	public void setMvDt(String mvDt) {
		this.mvDt = mvDt;
	}
	public Date getMvStDate() {
		return mvStDate;
	}
	public void setMvStDate(Date mvStDate) {
		this.mvStDate = mvStDate;
	}
	public String getMvType() {
		return mvType;
	}
	public void setMvType(String mvType) {
		this.mvType = mvType;
	}
	public String getMvCast() {
		return mvCast;
	}
	public void setMvCast(String mvCast) {
		this.mvCast = mvCast;
	}
	public String getMvDrt() {
		return mvDrt;
	}
	public void setMvDrt(String mvDrt) {
		this.mvDrt = mvDrt;
	}
	public Date getMvEdDate() {
		return mvEdDate;
	}
	public void setMvEdDate(Date mvEdDate) {
		this.mvEdDate = mvEdDate;
	}
	public String getMvTler() {
		return mvTler;
	}
	public void setMvTler(String mvTler) {
		this.mvTler = mvTler;
	}
	public Integer getMvTtCm() {
		return mvTtCm;
	}
	public void setMvTtCm(Integer mvTtCm) {
		this.mvTtCm = mvTtCm;
	}
	public Integer getMvTtStar() {
		return mvTtStar;
	}
	public void setMvTtStar(Integer mvTtStar) {
		this.mvTtStar = mvTtStar;
	}
	
	
	
	
}
