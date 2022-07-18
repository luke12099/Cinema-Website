package com.refundTicket;

import java.util.List;

import com.tk_ord_dt.model.TkOrdDtVO;

public interface RefundTicket_interface {
	// 讓後臺USER 使用電影票訂單編號 找到 底下全部的電影票明細
	public List<TkOrdDtVO> getDtByOrd(Long tkOrdID);
	
	// 對單一DT 進行UPDATE
	public void updateOneDt(Integer seatState,Long tkDtID);
	
	// 獲取單一票種的票名
	public String getTicketName(Integer tkTypeID);
	
	// 獲取單一方案的名稱
	public String getActTitle(Integer act_id);
}
