package com.surfo.service;

import java.util.List;
import java.util.Map;

import com.surfo.entity.AcctUser;
import com.surfo.utils.Pager;


/**
 * @author fengxk
 * @version 1.0
 *  userService接口
 */

public interface UserService {
	public List findAcctUserAll();
	public void saveOrUpdateAcctUser(AcctUser acctUser);
	public void deleteById(String id);
	public AcctUser getAcctUserById(String id);
	public AcctUser findByUserName(String userName);
	public Pager getAcctUserPageList(String queryHQL, Map paramsMap, Pager pager);
}
