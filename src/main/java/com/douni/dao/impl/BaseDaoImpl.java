package com.douni.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douni.dao.BaseDao;

/**
 * 数据库操作Base类
 * @author huangdean
 */
@Transactional
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Serializable save(T o) {
		Serializable gg;
		gg = getCurrentSession().save(o);
		return gg;
	}
	
	@Override
	public void delete(T o) {
		getCurrentSession().delete(o);
	}
	
	@Override
	public void update(T o) {
		getCurrentSession().update(o);
	}
	
	@Override
	public T get(String hql) {
		Query query = getCurrentSession().createQuery(hql);
		List<T> list = query.list();
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public T get(String hql, Map<String, Object> params) {
		Query query = getCurrentSession().createQuery(hql);
		if (params!=null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> list = query.list();
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<T> find(String hql) {
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query query = getCurrentSession().createQuery(hql);
		if (params!=null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.list();
	}
	
	@Override
	public int executeHql(String hql) {
		Query query = getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}
	
	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		Query query = getCurrentSession().createQuery(hql);
		if (params!=null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}
	
	@Override
	public int executeSql(String sql) {
		Query query = getCurrentSession().createQuery(sql);
		return query.executeUpdate();
	}
	
	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		Query query = getCurrentSession().createQuery(sql);
		if (params!=null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}
	
	/** 
	 * @function 分页显示符合所有的记录数，将查询结果封装为Pager 
	 * @param pageNo 
	 *            当前页数 
	 * @param pageSize 
	 *            每页显示的条数 
	 * @param instance 
	 *            将查询条件封装为专家Bean 
	 * @return 查询结果Pager 
	 */  
	public List<Object> findPageByQuery(int offset, int limit, String hql,  
	        Map map)  
	{  
	    List<Object> result = null;  
	    try  
	    {  
	        Query query = this.getCurrentSession().createQuery(hql);  
	  
	        Iterator it = map.keySet().iterator();  
	        while (it.hasNext())  
	        {  
	            Object key = it.next();  
	            query.setParameter(key.toString(), map.get(key));  
	        }  
	  
	        query.setFirstResult(offset);  
	        query.setMaxResults(limit);  
	  
	        result = query.list();  
	  
	    } catch (RuntimeException re)  
	    {  
	        throw re;  
	    }  
	    return result;  
	} 
	
	/** 
	 * @function 根据查询条件查询记录数的个数 
	 * @param hql 
	 *            hql查询语句 
	 * @param map 
	 *            用map封装查询条件 
	 * @return 数据库中满足查询条件的数据的条数 
	 */  
	public int getTotalCount(String hql, Map map)  
	{  
	    try  
	    {  
	    	hql="select count(id) "+hql;
	        Query query = this.getCurrentSession().createQuery(hql);  
	        Iterator it = map.keySet().iterator();  
	        while (it.hasNext())  
	        {  
	            Object key = it.next();  
	            query.setParameter(key.toString(), map.get(key));  
	        }  
	        Long i = (Long) query.list().get(0);  
	        return Integer.valueOf(i.toString());  
	    } catch (RuntimeException re)  
	    {  
	        throw re;  
	    }  
	}

  //直接获取分页数据
 public  Map<String,Object> getPageData(int offset, int limit,String hql, Map map){
	 List<Object> data=findPageByQuery(offset, limit,hql,map);
	 int count=getTotalCount(hql,map);
	 Map<String,Object> result=new HashMap<String, Object>();
	 result.put("total", count);
	 result.put("rows", data);
	 return result;
  }

	@Override
	public void saveOrupdate(T o) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object merge(T o) {
		// TODO Auto-generated method stub
		return null;
	}
}
