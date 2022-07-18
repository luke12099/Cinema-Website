package com.refundTicket;

import java.util.List;

import com.tk_ord_dt.model.TkOrdDtVO;

public class RefundTicketService {
	private RefundTicket_interface dao;
	
	public RefundTicketService() {
		dao = new RefundTicketDAO();
	}
	
	public List<TkOrdDtVO> getDtByOrd(Long tkOrdID){
		
		return dao.getDtByOrd(tkOrdID);
	}
	
	public String getTicketName(Integer tkTypeID) {
		
		return dao.getTicketName(tkTypeID);
	}
	
	public String getActTitle(Integer act_id) {
		
		return dao.getActTitle(act_id);
	}
	
	public void updateOneDt(Integer seatState,Long tkDtID) {
		
		dao.updateOneDt(seatState,tkDtID);
	}
}
