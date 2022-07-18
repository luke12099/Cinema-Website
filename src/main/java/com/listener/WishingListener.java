package com.listener;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.common.JedisPoolUtil;
import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.movie.model.MovieService;
import com.wishing_list.model.WishingListService;
import com.wishing_list.model.WishingListVO;
import com.wishing_pond.model.WishingNotifyPOJO;
import com.wishing_pond.model.WishingPondService;
import com.wishing_pond.model.WishingPondVO;

import redis.clients.jedis.Jedis;

@WebListener
public class WishingListener implements ServletContextListener {
	Timer timer = null;

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("servlet context start...");
    	// 開啟 Timer
    	timer = new Timer();
    	TimerTask task = new TimerTask() {
			@Override
			public void run() {
				WishingPondService wishSvc = new WishingPondService();
				WishingListService wishListSvc = new WishingListService();
				Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
				Gson gson = new Gson();
				MemberService memSvc = new MemberService();
				MovieService mvSvc = new MovieService();
				List<WishingPondVO> list = wishSvc.getAll();
				for(WishingPondVO wish: list) {	
					// 票選活動開始時				開始時間		執行時間
					if(wish.getWish_start().getTime() == scheduledExecutionTime()) {
						// 將活動開始通知存進 Redis
						List<MemberVO> memList = memSvc.getAll();
						for(MemberVO mem: memList) {
							WishingNotifyPOJO msgPOJO = new WishingNotifyPOJO("notify", mem.getMember_ID(), "票選活動'" + wish.getWish_name() + "'已開始!", scheduledExecutionTime()/1000/60, 0);
							String msg = gson.toJson(msgPOJO, WishingNotifyPOJO.class);
							jedis.rpush("msg:member:" + mem.getMember_ID(), msg);
							while(jedis.llen("msg:member:" + mem.getMember_ID()) > 10) {
								jedis.lpop("msg:member:" + mem.getMember_ID());
							}
						}
						// 將活動資料從 DB 撈出並存入 Redis，以利投票進行中可撈 Redis 資料
						List<WishingListVO> wishOptionList = wishListSvc.getOneWishingPond(wish.getWish_no());
						for(WishingListVO wishOption: wishOptionList) {
							String jedisKey = new StringBuilder("wish:").append(wishOption.getWish_no()).toString();
							jedis.hset(jedisKey, wishOption.getMv_id().toString(), wishOption.getMvVO().getMvName());
						}
					}
					// 票選活動結束時             結束時間 + 一天                         執行時間
					if(wish.getWish_end().getTime() + 1 * 24 * 60 * 60 * 1000 == scheduledExecutionTime()) {
						// 將活動結束通知存進 Redis
						List<MemberVO> memList = memSvc.getAll();
						for(MemberVO mem: memList) {
							WishingNotifyPOJO msgPOJO = new WishingNotifyPOJO("notify", mem.getMember_ID(), "票選活動'" + wish.getWish_name() + "'已結束!", scheduledExecutionTime()/1000/60, 0);
							String msg = gson.toJson(msgPOJO, WishingNotifyPOJO.class);
							jedis.rpush("msg:member:" + mem.getMember_ID(), msg);
							while(jedis.llen("msg:member:" + mem.getMember_ID()) > 10) {
								jedis.lpop("msg:member:" + mem.getMember_ID());
							}
						}
						// 到 Redis 查詢明細將票數合計存進 DB 並刪除 Redis 資料
						// 在此為判斷是否無資料(代表已存進 DB 避免重複存資料)，應該在資料庫設計時多一個欄位紀錄活動狀態(未開始、進行中、已結束)
						String eventJedisKey = new StringBuilder("wish:").append(wish.getWish_no()).toString();
						Map<String, String> eventMap = jedis.hgetAll(eventJedisKey);
						if(eventMap.size() != 0) {
							// 紀錄最高票(票數重複會記錄後者)
							Integer topMovie = -1;
							Integer topCount = -1;
							// 到 redis 撈各選項票數
							Set<String> keys = eventMap.keySet();
							for(String key: keys) {
								// 將每個選項的票數裝進 WishingListVO
								WishingListVO wishListVO = new WishingListVO();
								wishListVO.setWish_no(wish.getWish_no());
								wishListVO.setMv_id(Integer.valueOf(key));
								String eventOptionKey = new StringBuilder("wish:").append(wish.getWish_no())
										.append(":movie:").append(key).append(":count").toString();
								if(jedis.get(eventOptionKey) == null) {
									wishListVO.setWish_count(0);
								} else {
									wishListVO.setWish_count(Integer.valueOf(jedis.get(eventOptionKey)));
								}
								// 將資料新增至 DB
								wishListSvc.updateWishingList(wishListVO);
								// 比較是否為最高票
								if(wishListVO.getWish_count() >= topCount) {
									topMovie = wishListVO.getMv_id();
									topCount = wishListVO.getWish_count();
								}
								// 刪除 redis 資料 
								jedis.del(eventOptionKey); // 票數 (String)
								jedis.hdel(eventJedisKey, key); // 清單 (Hash)
							}
							// 將最高票存進 wishingPond
							wishSvc.updateTopOne(wish.getWish_no(), topMovie);
							// 將投票結果存進 Redis
							for(MemberVO mem: memList) {
								WishingNotifyPOJO msgPOJO = new WishingNotifyPOJO("notify", mem.getMember_ID(), "恭喜'"+ mvSvc.findByPrimaryKey(topMovie).getMvName() + "'於票選活動中'" + wish.getWish_name() + "'拿到冠軍!", scheduledExecutionTime()/1000/60, 0);
								String msg = gson.toJson(msgPOJO, WishingNotifyPOJO.class);
								jedis.rpush("msg:member:" + mem.getMember_ID(), msg);
								while(jedis.llen("msg:member:" + mem.getMember_ID()) > 10) {
									jedis.lpop("msg:member:" + mem.getMember_ID());
								}
							}
						}
					}
				}
				if(jedis != null) {
					jedis.close();
				}
			}
    	};
    	Calendar cal = new GregorianCalendar(2022, Calendar.JUNE, 30, 0, 0, 0);
//    	Calendar cal = new GregorianCalendar(2022, Calendar.JULY, 2, 0, 0, 0);
    	timer.scheduleAtFixedRate(task, cal.getTime(), 12 * 60 * 60 * 1000);
    }
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("servlet context end...");
    	// 關閉 Redis Pool
    	JedisPoolUtil.shutdownJedisPool(); 
    	// 關閉 Timer
    	timer.cancel(); 
    }
}
