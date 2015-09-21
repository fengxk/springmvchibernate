package com.surfo.dao.impl;

import org.springframework.stereotype.Repository;

import com.surfo.common.BaseRedisDaoImpl;
import com.surfo.dao.RedisDao;

@Repository("redisDao")
public class RedisDaoImpl extends BaseRedisDaoImpl implements RedisDao{

}
