package com.common;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
// Jedis 共用資源
// 使用方式: Jedis jedis = JedisPoolUtil.getJedisPool().getResource(); 就可以使用 jedis 操做，記得要歸還連線 jedis.close();
public class JedisPoolUtil {
	private static JedisPool pool = null;

	public static JedisPool getJedisPool() {  
		if(pool == null) {
			synchronized(JedisPoolUtil.class) { // 如果第一次請求有多人時會讓另一個等待，保證只會建立一個連線池
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(8); // 最大連線數
				config.setMaxIdle(8); // 設置空閒連線數
				config.setMaxWaitMillis(10000); // 最大阻塞時間(毫秒)
				pool = new JedisPool(config, "localhost", 6379);
			}
		}
		return pool;
	}
	
	public static void shutdownJedisPool() {
		if(pool != null) {
			pool.destroy();
		}
	}

}
