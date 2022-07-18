package com.cmt.controller;

import java.util.List;

import com.cmt.model.*;
import com.common.JedisPoolUtil;

import redis.clients.jedis.Jedis;

public class CmtLike {
	public static void main(String args[]) {
		
		int test = 1/0;
		System.out.println(test);
		
		
//		Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
//		
//		CmtService cmtSvc = new CmtService();
//		List<CmtVO> list = cmtSvc.getAll();
////		for (CmtVO cmtVO : list) {
////			System.out.println(cmtVO.getCM_ID() + " , " + cmtVO.getMEMBER_ID());
////			jedis.sadd("comment:" + cmtVO.getCM_ID().toString() + ":member", '"' + cmtVO.getMEMBER_ID().toString() + '"');
////			
////		}
//		
//		jedis.sadd("comment:1:member", "7");
//		jedis.sadd("comment:1:member", "1");
//		jedis.sadd("comment:1:member", "2");
//		jedis.scard("comment:1:member");
//		System.out.println(jedis.sismember("comment:1:member", "8"));
//		System.out.println(jedis.scard("comment:1:member"));
//		
//		
//		
//		jedis.close();
	}
	
	
}
