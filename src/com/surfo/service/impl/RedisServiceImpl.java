package com.surfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surfo.dao.RedisDao;
import com.surfo.service.RedisService;

@Service("redisService")
public class RedisServiceImpl implements RedisService{
	private static String CONTANTS_KEY="feng_key";
	@Autowired
	private RedisDao redisDao;
	public void leftin(String str){
		redisDao.leftPush(CONTANTS_KEY, str+"1");
	}
	
	public String rigthout(String str){
		return redisDao.rightPop(CONTANTS_KEY);
	}
	
	public void rightin(String str){
		redisDao.rightPush(str, str+"1");
	}
	
	public String leftout(String str){
		return redisDao.leftPop(str);
	}
}
