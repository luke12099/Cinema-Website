package com.wishing_pond.model;

public class WishingNotifyPOJO {
	private String type; // message type : notify / vote
	private Integer memberId;
	private String message;
	private long minute; 
	private Integer status; // 0:未讀，1:已讀
	
	public WishingNotifyPOJO(String type, Integer memberId, String message, long timeMillis, Integer status) {
		super();
		this.type = type;
		this.memberId = memberId;
		this.message = message;
		this.minute = timeMillis;
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimeMillis() {
		return minute;
	}
	public void setTimeMillis(long timeMillis) {
		this.minute = timeMillis;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
//	// 處理 Redis 資料
//	public String getNotifyString(String type, Integer memberId, String message, long timeMillis, Integer status) {
//		return new StringBuilder("{'type':'").append(type).append("', 'memberId':'").append(memberId)
//				.append("', 'message':'").append(message).append("', 'timeMillis':'").append(timeMillis)
//				.append("', 'status':'").append(status).append("'}").toString();
//	}
}
