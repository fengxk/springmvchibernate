package com.surfo.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.surfo.common.BaseDao;
import com.surfo.common.BaseDaoImpl;
import com.surfo.dao.UserDao;
import com.surfo.entity.AcctUser;
import com.surfo.utils.PubMethod;

/**
 * @author fengxk
 * @version 1.0
 * UserDao实现类
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	
	public AcctUser getAcctUserById(String id) {
		return (AcctUser) this.get(AcctUser.class, Integer.valueOf(id));
	}
	
	public List findAcctUserAll(String hql) {
		return this.getList(hql, new HashMap());
	}

	public AcctUser findByUserName(String userName){
		StringBuffer sb = new StringBuffer();
		//组织查询条件
		Map paramsMap = new HashMap();
		//// 基本HQL语句。
		sb.append(" from AcctUser where  1=1 ");
		if(!PubMethod.isEmpty(userName)){
			sb.append(" and userName=:userName");
			paramsMap.put("userName", userName);
		}
		List list =  this.getList(sb.toString(), paramsMap);
		if(list!=null && list.size()>0) return (AcctUser)list.get(0);
		else return null;    
	}

	

}
