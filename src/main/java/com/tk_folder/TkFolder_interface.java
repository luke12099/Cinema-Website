package com.tk_folder;

import java.util.List;
import java.util.Map;

import com.tk_ord.model.TkOrdVO;

public interface TkFolder_interface {
	// 調取在查看訂單畫面需要的全部訂單資訊
	public List<TkOrdVO> getAllTkOrd(Integer member_ID);
	
	// 協助getAllTkOrd 調取 有幾張票
	public Integer getDtCount(Long tkOrdID);
	
	// 協助getAllTkOrd 調取 有幾份餐點
	public Integer getFoodCount(Long tkOrdID);
	
	// 調取該張訂單的全部餐飲訂單明細
	public Map<String,Object> getFoodOrdDt(Long tkOrdID);
	
	// 更改餐飲訂單為 已退票 或 已取餐
	public void updateFoodOrd(Byte fdState,Long tkOrdID);
	
	// 驗證員工驗證碼
	public Integer verifyCode(Integer verifyEmpCode);
}
