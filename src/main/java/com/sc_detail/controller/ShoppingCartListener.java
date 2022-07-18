package com.sc_detail.controller;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.sc_detail.model.SCDetailService;
import com.sc_detail.model.SCDetailVO;


@WebListener
public class ShoppingCartListener implements HttpSessionBindingListener {

    ServletContext context;
    public ShoppingCartListener() {
    }
    
    public ShoppingCartListener(ServletContext context) {
        this.context = context;
    }

	
    public void valueBound(HttpSessionBindingEvent event)  { 
    	System.out.println("ShoppingCartListener.valueBound()");
         HttpSession session = event.getSession();
        System.out.println("session:" + session);
         List<SCDetailVO> buylist = (Vector<SCDetailVO>) session.getAttribute("shoppingcart");
         Integer memberID = (Integer)session.getAttribute("account");
         if(buylist==null) {
        	 buylist = new Vector<SCDetailVO>();
         }
         for(SCDetailVO scdVo : buylist) {
        	 if(scdVo.getMemberID() == null) {
        		 scdVo.setMemberID(memberID);
        	 }
         }
         SCDetailService scDetailSvc = new SCDetailService();
         List<SCDetailVO> scDetailList = scDetailSvc.getAll(memberID);
         if(scDetailList != null) {
         for(SCDetailVO scdVo: scDetailList) {
        	 scDetailSvc.deleteSCDetail(scdVo.getMemberID(), scdVo.getMerchID());
        	 if (buylist == null) {
					buylist = new Vector<SCDetailVO>();
					buylist.add(scdVo);
				} else {
					if (buylist.contains(scdVo)) {
						SCDetailVO innerSCDetailVo = buylist.get(buylist.indexOf(scdVo));
						innerSCDetailVo.setScCount((innerSCDetailVo.getScCount() + scdVo.getScCount()));
					} else {
						buylist.add(scdVo);
					}
				}
         }
         }
         session.setAttribute("shoppingcart",buylist);
         
         
    }

    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	System.out.println("valueunbind");
    }
	
}
