package com.actdt.model;

import java.sql.Date;
import java.util.*;


public class ActdtService {

	private ActdtDAO_interface dao;

	public ActdtService() {
		dao = new ActdtJDBCDAO();
	}

	public ActdtVO insert(Integer act_id,Date act_date_start, String act_title, String act_subtitle, Integer tkTypeID, Double act_discount, Integer act_coupon, Byte act_status, String act_content, String act_picture) {

		ActdtVO actdtVO = new ActdtVO();
		
		actdtVO.setAct_id(act_id);
		actdtVO.setAct_date_start(act_date_start);
		actdtVO.setAct_title(act_title);
		actdtVO.setAct_subtitle(act_subtitle);
		actdtVO.setTkTypeID(tkTypeID);
		actdtVO.setAct_discount(act_discount);
		actdtVO.setAct_coupon(act_coupon);
		actdtVO.setAct_status(act_status);
		actdtVO.setAct_content(act_content);
		actdtVO.setAct_picture(act_picture);
		dao.insert(actdtVO);

		return actdtVO;
	}
	
//	public ActdtVO collectNameTo(List<String> names) {
//        Scanner scanner = new Scanner(System.in);
//        String name;
//        while(true) {
//            out.print("訪客名稱：");
//            name = scanner.nextLine();
//            if(name.equals("quit")) {
//                break;
//            }
//            names.add(name);
//        }
//    }
	
	
//	璟謙講解
//	List<e> list = new LinkedList<E>();
//	for(Integer TkTypeID : req.getParameter("tkTypeID")) {
//	for(Integer id : req.getParameter("name")) {
//		xxxVO xxx = new XXX();
//		xxx.set()
//		
//		list.add(xxx);
//	}
	
	
	
//	璟謙講解
//	List<e> list = new LinkedList<E>();
//	for(Integer TkTypeID : req.getParameter("tkTypeID")) {
//		actdtVO acttkTypeID = new acttkTypeID();
//		acttkTypeID.set()
//		
//		list.add(acttkTypeID);
//	}

	public ActdtVO update (Integer act_id,Date act_date_start, String act_title, String act_subtitle, Integer tkTypeID, Double act_discount, Integer act_coupon, Byte act_status, String act_content, String act_picture) {

		ActdtVO actdtVO = new ActdtVO();
		
		actdtVO.setAct_id(act_id);
		actdtVO.setAct_date_start(act_date_start);
		actdtVO.setAct_title(act_title);
		actdtVO.setAct_subtitle(act_subtitle);
		actdtVO.setTkTypeID(tkTypeID);
		actdtVO.setAct_discount(act_discount);
		actdtVO.setAct_coupon(act_coupon);
		actdtVO.setAct_status(act_status);
		actdtVO.setAct_content(act_content);
		actdtVO.setAct_picture(act_picture);
		dao.update(actdtVO);

		return actdtVO;
	}
	
//	public ActdtVO update (Integer act_id,Date act_date_start, String act_title, String act_subtitle, Double act_discount, Integer act_coupon, Byte act_status, String act_content, String act_picture) {
//
//		ActdtVO actdtVO = new ActdtVO();
//		
//		actdtVO.setAct_id(act_id);
//		actdtVO.setAct_date_start(act_date_start);
//		actdtVO.setAct_title(act_title);
//		actdtVO.setAct_subtitle(act_subtitle);
////		actdtVO.setTkTypeID(tkTypeID);
//		actdtVO.setAct_discount(act_discount);
//		actdtVO.setAct_coupon(act_coupon);
//		actdtVO.setAct_status(act_status);
//		actdtVO.setAct_content(act_content);
//		actdtVO.setAct_picture(act_picture);
//		dao.update(actdtVO);
//
//		return actdtVO;
//	}
//	
	

//	public void delete(Integer act_id, Integer tkTypeID) {
//		dao.delete(act_id, tkTypeID);
//	}
	
	public void delete(Integer act_id) {
		dao.delete(act_id);
	}

	public ActdtVO findByPrimaryKey(Integer act_id,Integer tkTypeID) {
		return dao.findByPrimaryKey(act_id, tkTypeID);
	}
	
	public ActdtVO getOneActdt(Integer act_id,Integer tkTypeID) {
		return dao.findByPrimaryKey(act_id, tkTypeID);
	}
	
//	public ActdtVO findOneActdt(Integer act_id) {
//		return dao.findOneActdt(act_id);
//	}
	
	public List<ActdtVO> findOneActdt(Integer act_id) {
		return dao.findOneActdt(act_id);
	}
	

	
	

	public List<ActdtVO> getAll() {
		return dao.getAll();
	}
	
	public List<ActdtVO> getActdtStatus() {
		return dao.getActdtStatus();
	}
	
	//顯示單一活動資料，不顯示重複值
	public List<ActdtVO> getOnlyAct() {
		return dao.getOnlyAct();
	}

	public ActdtVO getActdtVO(Integer ids) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}



