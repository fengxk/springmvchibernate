package com.surfo.common;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.surfo.utils.Pager;
import com.surfo.utils.PubMethod;

@Repository("baseDao")
public class BaseDaoImpl implements BaseDao{
	
	private static final Logger LOGGER = Logger.getLogger(BaseDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void persist(Object o) {
		this.getCurrentSession().persist(o);
	}
	
	public void flush() {
		this.getCurrentSession().flush();

	}
	
	public Serializable save(Object o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(Object o) {
		this.getCurrentSession().delete(o);
		
	}

	public void update(Object o) {
		this.getCurrentSession().update(o);
		
	}

	public void saveOrUpdate(Object o) throws DataAccessException{
		this.getCurrentSession().saveOrUpdate(o);
		
	}

	@SuppressWarnings("unchecked")
	public Object get(Class c, Serializable id) {
		return this.getCurrentSession().get(c , id);
	}

	@SuppressWarnings("unchecked")
	public Object load(Class c, Serializable id) {
		return this.getCurrentSession().load(c , id);
	}

	public Integer executeHql(String hql) {
		PubMethod.debugHQL(LOGGER, hql);
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Map paramsMap) throws DataAccessException{
		Iterator iterator = paramsMap.keySet().iterator();
		String[] params = new String[paramsMap.keySet().size()];
		Object[] values = new Object[paramsMap.keySet().size()];
		Query q = this.getCurrentSession().createQuery(hql);
		int counter = 0;
		while (iterator.hasNext()) {
			String key = "" + iterator.next();
			q.setParameter(counter, paramsMap.get(key));
			params[counter] = key;
			values[counter] = paramsMap.get(key);
			counter++;
		}
		PubMethod.debugHQL(LOGGER, q, params, values);
		return q.executeUpdate();
	}
	// 带参数的HQL查询。
	public Pager getPage(String queryHQL, Map paramsMap, Pager pager) throws DataAccessException {
		Iterator iterator = paramsMap.keySet().iterator();
		String[] params = new String[paramsMap.keySet().size()];
		Object[] values = new Object[paramsMap.keySet().size()];

		int counter = 0;
		while (iterator.hasNext()) {
			String key = "" + iterator.next();
			params[counter] = key;
			values[counter] = paramsMap.get(key);
			counter++;
		}
		return getPageHQL(queryHQL, params, values, pager);
	}
	// 返回指定条数的记录。
	protected Pager getPageHQL(String queryName, final String[] params,

	final Object[] values, Pager pager)
			throws DataAccessException {
		if (params != null && values != null && params.length != values.length) {
			throw new IllegalArgumentException("参数名称和参数值数组长度不一致!");
		}

		try {
			Query queryObject = this.getCurrentSession()
					.createQuery(queryName);
			// debug HQL
			PubMethod.debugHQL(LOGGER, queryObject, params, values);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(params[i], values[i]);
				}
			}
			pager.init();//由于easyui的机制（每页的条数显示到了rows中），需要给pagenumber赋值
			int allRow = getPageHQL(queryName,params,values).size();//计算总记录数
			int pageStartRow = pager.countOffset(pager.getPageNumber(), pager.getPage());
			if (pageStartRow < 0)
	        {
	            pageStartRow = 0;
	        }
			final int offset = pageStartRow; // 当前页开始记录
		    final int length = pager.getPageNumber(); // 每页记录数
			
		    queryObject.setFirstResult(offset);
		    queryObject.setMaxResults(length);
			List list = queryObject.list();	
			pager.setTotal(allRow);
			pager.setRows(list);
			
			return pager;
		} catch (HibernateException he) {
			throw SessionFactoryUtils.convertHibernateAccessException(he);
		}
	}
	
	// 带参数的HQL查询。（返回满足条件的所有记录）
	public List getList(String queryHQL, Map paramsMap)
			throws DataAccessException {
		Iterator iterator = paramsMap.keySet().iterator();
		String[] params = new String[paramsMap.keySet().size()];
		Object[] values = new Object[paramsMap.keySet().size()];

		int counter = 0;
		while (iterator.hasNext()) {
			String key = "" + iterator.next();
			params[counter] = key;
			values[counter] = paramsMap.get(key);
			counter++;
		}
		return getPageHQL(queryHQL, params, values);
	}
	// 返回所有满足条件的记录。
	protected List getPageHQL(String queryName, final String[] params,
			final Object[] values) throws DataAccessException {
		if (params != null && values != null && params.length != values.length) {
			throw new IllegalArgumentException("参数名称和参数值数组长度不一致!");
		}

		try {
			Query queryObject = this.getCurrentSession()
					.createQuery(queryName);
			// Debug HQL
			PubMethod.debugHQL(LOGGER, queryObject, params, values);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(params[i], values[i]);
				}
			}
			List items = queryObject.list();
			return items;
		} catch (HibernateException he) {
			throw SessionFactoryUtils.convertHibernateAccessException(he);
		}
	}
	
	/**
	 * 查询表中记录总条数
	 * */
	public int getListCount(String queryHQL, Map paramsMap){
		return getList(queryHQL, paramsMap).size();
	}
	// 返回指定条数的记录。
	public Pager getPageSQL(String sql,Pager pager)
			throws DataAccessException {
		try {
			SQLQuery queryObject = this.getCurrentSession()
					.createSQLQuery(sql);
			pager.init();//由于easyui的机制（每页的条数显示到了rows中），需要给pagenumber赋值
			int allRow = querySql(sql).size();//计算总记录数
			int pageStartRow = pager.countOffset(pager.getPageNumber(), pager.getPage());
			if (pageStartRow < 0)
	        {
	            pageStartRow = 0;
	        }
			final int offset = pageStartRow; // 当前页开始记录
		    final int length = pager.getPageNumber(); // 每页记录数
			
		    queryObject.setFirstResult(offset);
		    queryObject.setMaxResults(length);
			List list = queryObject.list();	
			pager.setTotal(allRow);
			pager.setRows(list);
			
			return pager;
		} catch (HibernateException he) {
			throw SessionFactoryUtils.convertHibernateAccessException(he);
		}
	}
	public List querySql(String sql) throws DataAccessException {
		try {
			PubMethod.debugSQL(LOGGER, sql);
			return this.getCurrentSession().createSQLQuery(sql).list();
		} catch (HibernateException he) {
			throw SessionFactoryUtils.convertHibernateAccessException(he);
		}
	}
	/**
	 * 批量更新
	 * @param list
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(List list)throws DataAccessException{
		Session session = this.getCurrentSession();
		Iterator iterator = list.iterator();
		int index=0;
		while(iterator.hasNext()){
			index++;
			session.saveOrUpdate(iterator.next());
			if(index % 50 ==0)
				session.flush();
		}			
		session.flush();
	}
	
}
