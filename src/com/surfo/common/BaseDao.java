package com.surfo.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.surfo.utils.Pager;

/**
 * @author fengxk
 * @version 1.0
 * 
 * Dao公共接口
 */

public interface BaseDao {
	
	public void persist(Object o);
	
	
	public void flush();
	
	//扩展
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(Object o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(Object o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(Object o);

	/**
	 * 保存或更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(Object o);

	/**
	 * 分页查询
	 * @param queryHQL
	 * @param paramsMap
	 * @param pager
	 * @return
	 */
	public Pager getPage(String queryHQL, Map paramsMap, Pager pager);

	/**
	 * 获得一个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 * @return Object
	 */
	public Object load(Class c, Serializable id);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @return 响应数目
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行HQL语句
	 * 
	 * @param hql
	 * @param param
	 * @return 响应数目
	 */
	public Integer executeHql(String hql, Map paramsMap);
	/**
	 * 查询语句，by条件
	 * @param queryHQL
	 * @param paramsMap
	 * @return
	 */
	public List getList(String queryHQL, Map paramsMap);
	/**
	 * 根据条件查询条数
	 * @param queryHQL
	 * @param paramsMap
	 * @return
	 */
	public int getListCount(String queryHQL, Map paramsMap);
	
	
	public Pager getPageSQL(String sql,Pager pager);
	public List querySql(String sql);
	/**
	 * 批量更新
	 * @param list
	 * @throws DataAccessException
	 */
	public void saveOrUpdate(List list)throws DataAccessException;
}
