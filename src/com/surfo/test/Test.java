package com.surfo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
		RedisTemplate<String,String> redisTemplate = (RedisTemplate)context.getBean("redisTemplate");
		ListOperations< String, String> blo =redisTemplate.opsForList();
		
		blo.leftPush("1", "1");
		blo.leftPush("2", "2");
		blo.leftPush("3", "3");

	}

}
