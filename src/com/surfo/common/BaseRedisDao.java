package com.surfo.common;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.ValueOperations;

public interface BaseRedisDao {
	
	
	
	/**
	 * 压栈
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long leftPush(String key, String value) ;

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public String leftPop(String key) ;

	/**
	 * 入队
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rightPush(String key, String value);

	/**
	 * 出队
	 * 
	 * @param key
	 * @return
	 */
	public String rightPop(String key) ;

	/**
	 * 栈/队列长
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) ;

	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> range(String key, int start, int end);

	/**
	 * 移除
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, String value) ;

	/**
	 * 检索
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public Object index(String key, long index) ;

	/**
	 * 置值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, String value);

	/**
	 * 裁剪
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) ;

	
	
	
	
	
	
	
	
	
	
	
//	
//	 public void addLink(String userId, URL url);
//
//	public void add(String userId, URL url);
//
//	/**
//	 * 写入缓存
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value);
//
//	/**
//	 * 批量删除对应的value
//	 * 
//	 * @param keys
//	 */
//	public void remove(final String... keys);
//
//	/**
//	 * 批量删除key
//	 * 
//	 * @param pattern
//	 */
//	public void removePattern(final String pattern);
//
//	/**
//	 * 删除对应的value
//	 * 
//	 * @param key
//	 */
//	public void remove(final String key);
//
//	/**
//	 * 判断缓存中是否有对应的value
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public boolean exists(final String key);
//
//	/**
//	 * 读取缓存
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public Object get(String key);
//
//	/**
//	 * 写入缓存
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value, Long expireTime);
}
