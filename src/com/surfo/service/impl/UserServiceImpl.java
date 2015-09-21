package com.surfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surfo.dao.UserDao;
import com.surfo.entity.AcctUser;
import com.surfo.service.UserService;
import com.surfo.utils.Pager;

/**
 * @author fengxk
 * @version 1.0 
 * UserService 实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	
//	@SuppressWarnings("rawtypes")
	public List findAcctUserAll() {
		return userDao.findAcctUserAll(" from AcctUser where 1=1 ");
	}
	
	public Pager getAcctUserPageList(String queryHQL, Map paramsMap, Pager pager) {
		return userDao.getPage(queryHQL, paramsMap, pager);
	}

	public void saveOrUpdateAcctUser(AcctUser acctUser){
		userDao.saveOrUpdate(acctUser);
	}
	public void deleteById(String id){
		AcctUser acctUser = (AcctUser)userDao.getAcctUserById(id);
		userDao.delete(acctUser);
	}
	
	public AcctUser getAcctUserById(String id){
		return userDao.getAcctUserById(id);
	}

	public AcctUser findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}
	
}
