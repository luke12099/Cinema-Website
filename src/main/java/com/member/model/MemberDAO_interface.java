package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
    public void insert(MemberVO memberVO);					//新增
    public void update(MemberVO memberVO);					//修改
    public void delete(Integer memberVO);					//刪除
    public void updateStatus(Integer member_ID,Integer member_Status);  //修正會員狀態資料
    public MemberVO loginMember(MemberVO memberVO);  //會員登入
    public MemberVO findByPrimaryKey(Integer member_ID);		//查詢
    public List<MemberVO> getAll();						//查詢(全部)
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    
// wish
 	public void updateWishTicket(Integer member_id, Integer wish_ticket);

// 會員註冊時會傳送信件，並修改會員狀態(藉由Email搜尋會員ID)
 	public MemberVO register(MemberVO memberVO);
 	
//會員忘記密碼 	
	public Integer verifyEmail(String usrEmail) ;
 	
 	

 	
 	
}
