package com.merchandise_inf.model;

import java.sql.Blob;
import java.util.*;


import redis.clients.jedis.Jedis;

public class MerchService {
	private MerchDAO_interface dao;

	public MerchService() {

		dao = new MerchDAO();
	}
	
	//預留給Spring MVC
	public void addMerch(MerchVO merchVo) {
		dao.insert(merchVo);
	}
	
	public MerchVO addMerch(String merchName, String merchDT, Blob merchPic1, Blob merchPic2, Blob merchPic3, Blob merchPic4, Blob merchPic5, java.sql.Timestamp merchDate, Double merchPrice, String merchClass, Byte merchStatus, Integer merchStock) {
		MerchVO merchVo = new MerchVO();
		
		merchVo.setMerchName(merchName);
		merchVo.setMerchDT(merchDT);
		merchVo.setMerchPic1(merchPic1);
		merchVo.setMerchPic2(merchPic2);
		merchVo.setMerchPic3(merchPic3);
		merchVo.setMerchPic4(merchPic4);
		merchVo.setMerchPic5(merchPic5);
		merchVo.setMerchDate(merchDate);
		merchVo.setMerchPrice(merchPrice);
		merchVo.setMerchClass(merchClass);
		merchVo.setMerchStatus(merchStatus);
		merchVo.setMerchStock(merchStock);
		merchVo.setSoldTotal(merchStock);
		dao.insert(merchVo);
		
		return merchVo;
	}
	
	
	public MerchVO updateMerch(Integer merchID, String merchName, String merchDT, Blob merchPic1, Blob merchPic2, Blob merchPic3, Blob merchPic4, Blob merchPic5, java.sql.Timestamp merchDate, Double merchPrice, String merchClass, Byte merchStatus, Integer merchStock){
		MerchVO merchVo = new MerchVO();
		merchVo.setMerchID(merchID);
		merchVo.setMerchName(merchName);
		merchVo.setMerchDT(merchDT);
		merchVo.setMerchPic1(merchPic1);
		merchVo.setMerchPic2(merchPic2);
		merchVo.setMerchPic3(merchPic3);
		merchVo.setMerchPic4(merchPic4);
		merchVo.setMerchPic5(merchPic5);
		merchVo.setMerchDate(merchDate);
		merchVo.setMerchPrice(merchPrice);
		merchVo.setMerchClass(merchClass);
		merchVo.setMerchStatus(merchStatus);
		merchVo.setMerchStock(merchStock);
		merchVo.setSoldTotal(merchStock);
		dao.update(merchVo);
		
		return dao.findByPrimaryKey(merchID);
	}
	
	public List<MerchVO> getAll(){
		return dao.getAll();
	}
	public List<MerchVO> getByName(String searchName){
		return dao.getByName(searchName);
	}
	
	public MerchVO getOneMerch(Integer merchID) {
		return dao.findByPrimaryKey(merchID);
	}
	
	public void updateMerch(MerchVO merchVo) {
		dao.update(merchVo);
	}
	
	public void deleteMerch(Integer merchID) {
		dao.delete(merchID);
	}
	public List<MerchVO> getHotSell(){
		Double minPrice = 0.0;
		Double maxPrice = 100000.0;
		return dao.getAll(minPrice,maxPrice);
	}
	public List<MerchVO> getHotSell(Double minPrice, Double maxPrice){
		return dao.getAll(minPrice, maxPrice);
	}
	public List<MerchVO> getNewest(){
		Double minPrice = 0.0;
		Double maxPrice = 100000.0;
		return dao.getNewest(minPrice, maxPrice);
	}
	public List<MerchVO> getNewest(Double minPrice, Double maxPrice ){
		return dao.getNewest(minPrice, maxPrice);
	}
	public List<MerchVO> getMostSold(){
		Double minPrice = 0.0;
		Double maxPrice = 100000.0;
		return dao.getMostSell(minPrice, maxPrice);
	}
	public List<MerchVO> getMostSold(Double minPrice, Double maxPrice){
		return dao.getMostSell(minPrice, maxPrice);
	}
	public List<MerchVO> getByClass(String merchClass){
		return dao.getByclass(merchClass);
	}
	public List<MerchVO> getBySearch(String merchName, Double min, Double max){
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.incr("merchandise:search:"+merchName);
		jedis.close();
		return dao.getBySearch(merchName, min, max);
	}
	/*回傳熱門搜尋*/
	public List<String> getSearchName(){
		Jedis jedis = new Jedis("localhost", 6379);
		Set<String> aa= jedis.keys("merchandise:search:*");
		Map<String,Integer> map = new HashMap<>();
		for(String str : aa) {
			map.put(str, Integer.valueOf(jedis.get(str)));
			
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {	
			@Override
			public int compare(Map.Entry<String, Integer> o2, Map.Entry<String, Integer> o1) {
				return o1.getValue()-o2.getValue();
			}
		});
		int i = 0;
		List<String> returnList = new LinkedList<String>();
		for(Map.Entry<String, Integer> rr : list) {			
			System.out.println(rr.getKey().substring(rr.getKey().indexOf(":",12)+1));
			returnList.add(rr.getKey().substring(rr.getKey().indexOf(":",12)+1));
			if(i++ == 4) {
				break;
			}
		}
		
		jedis.close();
		return returnList;
	}

}
