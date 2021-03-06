package com.aiiju.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.aiiju.dao.JedisClient;
/**
 * 
 * @ClassName: JedisClient 
 * @Description: redis客户端 实现
 * @author 小飞 
 * @date 2016年11月16日 上午11:25:32 
 *
 */
public class JedisClientImpl implements JedisClient{
	
	@Autowired
	private JedisPool jedisPool; 
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}


	@Override
	public Set<String> keys(String key) {
		Jedis jedis = jedisPool.getResource();
		Set<String> set = jedis.keys(key);
		return set;
	}


	@Override
	public boolean exists(String key) {
		
		Jedis jedis = jedisPool.getResource();
		boolean  flag= jedis.exists(key);
		return flag;
	}

	@Override
	public boolean setex(String key, int second, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.setex(key, second, value);
		jedis.close();
		return true;
	}

	@Override
	public void delList(String... key) {
		Jedis jedis = jedisPool.getResource();
		for (String string : key) {
			 jedis.del(key);
		}
		jedis.close();
		
	}

	@Override
	public String getAndDel(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.del(key);
		jedis.close();
		return string;
	}

}
