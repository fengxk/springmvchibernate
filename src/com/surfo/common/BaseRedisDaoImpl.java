package com.surfo.common;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.stereotype.Repository;
@Repository("baseRedisDao")
public class BaseRedisDaoImpl implements BaseRedisDao{
	private static final Log logger = LogFactory.getLog("BaseRedisDaoImpl");
//    @Autowired
//	private RedisTemplate redisTemplate;

//	@Resource(name="redisTemplate")
	private ListOperations<String, String> listOps;
	public List getAll(){
//		Redis listOps.getAll();
		List list = new ArrayList();
		return list;
	}
	
	/**
	 * 压栈
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long leftPush(String key, String value) {
		return listOps.leftPush(key, value);
	}

	/**
	 * 出栈
	 * 
	 * @param key
	 * @return
	 */
	public String leftPop(String key) {
		return listOps.leftPop(key);
	}

	/**
	 * 入队
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long rightPush(String key, String value) {
		return listOps.rightPush(key, value);
	}

	/**
	 * 出队
	 * 
	 * @param key
	 * @return
	 */
	public String rightPop(String key) {
		return listOps.rightPop(key);
	}

	/**
	 * 栈/队列长
	 * 
	 * @param key
	 * @return
	 */
	public Long length(String key) {
		return listOps.size(key);
	}

	/**
	 * 范围检索
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> range(String key, int start, int end) {
		return listOps.range(key, start, end);
	}

	/**
	 * 移除
	 * 
	 * @param key
	 * @param i
	 * @param value
	 */
	public void remove(String key, long i, String value) {
		listOps.remove(key, i, value);
	}

	/**
	 * 检索
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String index(String key, long index) {
		return listOps.index(key, index);
	}

	/**
	 * 置值
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, String value) {
		listOps.set(key, index, value);
	}

	/**
	 * 裁剪
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, long start, int end) {
		listOps.trim(key, start, end);
	}  

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** *************************上面用的是list，下面用的是map*************************** */
	
	
	
//	
// // inject the actual template
// @Autowired
// private RedisTemplate<String, Object> template;
//
// // inject the template as ListOperations
// // can also inject as Value, Set, ZSet, and HashOperations
// @Resource(name="redisTemplate")
// private ListOperations<String, String> listOps;
//  
// public void addLink(String userId, URL url) {
// listOps.leftPush(userId, url.toExternalForm());
// // or use template directly
// // template.boundListOps(userId).leftPush(url.toExternalForm());
// }
// public void add(String userId, URL url) {
//    	listOps.leftPush(userId, url.toExternalForm());
//    }
//    /**
//	   * 写入缓存
//	   * 
//	   * @param key
//	   * @param value
//	   * @return
//	   */
//	  public boolean set(final String key, Object value) {
//	  	boolean result = false;
//	  	try {
//	  		ValueOperations<String, Object> operations = template.opsForValue();
//	  		operations.set(key, value);
//	  		result = true;
//	  	} catch (Exception e) {
//	  		logger.error(e.getMessage(), e);
//	  	}
//	  	return result;
//	  }
//
//	  
//	  /**
//	  * 批量删除对应的value
//	  * 
//	  * @param keys
//	  */
//	  public void remove(final String... keys) {
//		  for (String key : keys) {
//			  remove(key);
//		  }
//	  }
//
//	  /**
//	  * 批量删除key
//	  * 
//	  * @param pattern
//	  */
//	  public void removePattern(final String pattern) {
//		  Set<String> keys = template.keys(pattern);
//		  if (keys.size() > 0)
//			  template.delete(keys);
//	  }
//
//	  /**
//	  * 删除对应的value
//	  * 
//	  * @param key
//	  */
//	  public void remove(final String key) {
//		  if (exists(key)) {
//			  template.delete(key);
//		  }
//	  }
//
//	  /**
//	  * 判断缓存中是否有对应的value
//	  * 
//	  * @param key
//	  * @return
//	  */
//	  public boolean exists(final String key) {
//		  return template.hasKey(key);
//	  }
//
//	  /**
//	  * 读取缓存
//	  * 
//	  * @param key
//	  * @return
//	  */
//	  public Object get(String key) {
//		  Object result = null;
//		  ValueOperations<String, Object> operations = template.opsForValue();
//		  result = operations.get(key);
//		  return result;
//	  }
//
//	  /**
//	  * 写入缓存
//	  * 
//	  * @param key
//	  * @param value
//	  * @return
//	  */
//	  public boolean set(final String key, Object value,Long expireTime) {
//		  boolean result = false;
//		  try {
//			  ValueOperations<String, Object> operations = template.opsForValue();
//			  operations.set(key, value);
//			  template.expire(key,expireTime,TimeUnit.SECONDS);
//			  result = true;
//		  } catch (Exception e) {
//			  logger.error(e.getMessage(), e);
//		  }
//		  return result;
//	  }
//	@Override
//	public IdEntity getEntityByID(String id, Class<? extends IdEntity> clazz) {
//		if(id == null) return null;
//		Object obj = get(getEntityKey(id, clazz));
//		IdEntity entity = null;
//		if(obj == null){
//			obj = commonService.get(clazz, id);
//			if(obj != null){
//				entity = (IdEntity)obj;
//				setEntity(entity);
//			}
//		}else{
//			entity = (IdEntity)obj;
//		}
//		return entity;
//	}
//	@Override
//	public boolean setEntity(IdEntity entity) {
//		return set(getEntityKey(entity.getId(), entity.getClass()), entity, TIME_SECONDS_HOURS_5);
//	}
//	@Override
//	public void removeEntity(IdEntity entity) {
//		remove(getEntityKey(entity.getId(), entity.getClass()));
//	}
//	@Override
//	public boolean setEntity(IdEntity entity, Long expireTime) {
//		return set(getEntityKey(entity.getId(), entity.getClass()), entity, expireTime);
//	}
//	
//	public String getEntityKey(String id,Class<? extends IdEntity> class1){
//		return class1.getSimpleName() + "_" + id;
//	}
//	@Override
//	public List<TSType> getTSTypes(String code) {
//		Map<String, List<TSType>> allTypes = (Map<String, List<TSType>>) get(Globals.REDIS_KEY_ALL_TYPES);
//		List<TSType> types = allTypes.get(code);
//		if(types == null){
//			types = commonService.findByProperty(TSType.class, "TSType.typegroupcode", code);
//			allTypes.put(code, types);
//			set(Globals.REDIS_KEY_ALL_TYPEGROUPS, allTypes);
//		}
//		return types;
//	}
//	@Override
//	public TSTypegroup getTSTypegroup(String code) {
//		Map<String, TSTypegroup> allTypeGroups = (Map<String, TSTypegroup>) get(Globals.REDIS_KEY_ALL_TYPEGROUPS);
//		TSTypegroup group = allTypeGroups.get(code);
//		if(group == null){
//			List<TSTypegroup> groups = commonService.findByProperty(TSTypegroup.class, "typegroupcode", code);
//			if (groups != null && groups.size() > 0) {
//				group = groups.get(0);
//				allTypeGroups.put(code, group);
//				set(Globals.REDIS_KEY_ALL_TYPEGROUPS, allTypeGroups);
//			}
//		}
//		return group;
//	}
//	@Override
//	public TSDepart getTSDepartByCode(String code) {
//		TSDepart obj = (TSDepart)get(Globals.REDIS_KEY_TSDEPART+"_"+code);
//		if(obj == null){
//			List<TSDepart> objs = commonService.findByProperty(TSDepart.class, "orgCode", code);
//			if(objs != null && objs.size()>0){
//				obj = objs.get(0);
//				set(Globals.REDIS_KEY_TSDEPART+"_"+code,obj);
//			}
//		}
//		return obj;
//	}
//	@Override
//	public Object getEntityByID(Integer id,Class class1) {
//		if(id == null)
//			return null;
//		Object obj = get(class1.getSimpleName()+"_"+id);
//		if(obj == null){
//			obj = commonService.get(class1, id);
//			if(obj != null){
//				set(class1.getSimpleName()+"_"+id,obj,TIME_SECONDS_HOURS_5);
//			}
//		}
//		return obj;
//	}
}
