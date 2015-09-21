package com.surfo.dao;

import java.util.List;

import com.surfo.common.BaseDao;
import com.surfo.entity.AcctUser;

/**  
 * @author fengxk
 * @version 1.0
 * 用户Dao接口
 */
public interface UserDao extends BaseDao {
	public List findAcctUserAll(String hql);
	public AcctUser findByUserName(String userName);
	public AcctUser getAcctUserById(String id);
}
