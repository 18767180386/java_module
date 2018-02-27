package com.aiiju.dao;

import java.util.Set;

/**
 * 
 * @ClassName: JedisClient 
 * @Description: redis客户端
 * @author 小飞 
 * @date 2016年11月16日 上午11:24:16 
 *
 */
public interface JedisClient {

	public String get(String key);
	
	public String set(String key, String value);
	
	public String hget(String hkey, String key);
	
	/**
	 * 命令用于返回哈希表中指定字段的值。
	 * 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 1 。 如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 0 。
	 * @param key
	 * @return
	 */
	public long hset(String hkey, String key, String value);
	
	/**
	 * 将 key 中储存的数字值增一。
	 * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
	 * @param key
	 * @return
	 */
	public long incr(String key);
	
	/**
	 * 设置过期时间
	 * @param key
	 * @return
	 */
	public long expire(String key, int second);
	
	/**
	 * 查看剩余生存时间
	 * @param key
	 * @return
	 */
	public long ttl(String key);
	
	/**
	 * 命令用于删除已存在的键，返回被删除 key 的数量。
	 * @param key
	 * @return
	 */
	
	public long del(String key);
	
	
	/**
	 * 命令用于删除已存在的多个键，
	 * @param key
	 * @return
	 */
	
	public void delList(String...key);
	
	
	/**
	 * 命令用于从存储在键散列删除指定的字段，
	 * 如果键不存在，它将被视为一个空的哈希与此命令将返回0。
	 * @param key
	 * @return
	 */
	public long hdel(String hkey, String key);
	
	/**
	 * 具有匹配模式的键列表，
	 * 可用占位符 *，? ; 如：keys s*
	 * @param key
	 * @return
	 */
	
	public Set<String> keys(String key);
	
	
	/**
	 * 命令用于检查键是否存在于Redis中 ;
	 * 如果键存在，返回 1;如果键不存在，返回 0
	 * @param key
	 * @return
	 */
	public boolean exists(String key);
	
	
	/**
	 * 为指定的 key 设置值及其过期时间。
	 * 如果 key 已经存在， SETEX 命令将会替换旧的值。
	 * @param key
	 * @return
	 */
	public boolean setex (String key,int second,String value);
	
	
	
	/**
	 * 获取指定key并在缓存中删除
	 * 
	 * @param key
	 * @return
	 */
	public String getAndDel (String key);
	
}
