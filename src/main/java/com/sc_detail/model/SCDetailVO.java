package com.sc_detail.model;

import com.merchandise_inf.model.MerchService;
import com.merchandise_inf.model.MerchVO;

public class SCDetailVO implements java.io.Serializable {
	private Integer memberID;
	private Integer merchID;
	private Integer scCount;
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public Integer getMerchID() {
		return merchID;
	}
	public void setMerchID(Integer merchID) {
		this.merchID = merchID;
	}
	public Integer getScCount() {
		return scCount;
	}
	public void setScCount(Integer scCount) {
		this.scCount = scCount;
	}
	public MerchVO getMerchVO() {
		MerchService merchSvc = new MerchService();
		MerchVO merchVo = merchSvc.getOneMerch(merchID);
		return merchVo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((merchID == null) ? 0 : merchID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SCDetailVO other = (SCDetailVO) obj;
		if (merchID == null) {
			if (other.merchID != null)
				return false;
		} else if (!merchID.equals(other.merchID))
			return false;
		return true;
	}
}
