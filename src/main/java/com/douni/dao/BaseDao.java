package com.douni.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao<T> {

	public Serializable save(T o);

	public void delete(T o);

	public void update(T o);

	public void saveOrupdate(T o);
	
	public Object merge(T o);

	public T get(String hql);

	public T get(String hql, Map<String, Object> params);

	public List<T> find(String hql);

	public List<T> find(String hql, Map<String, Object> param);

	public int executeHql(String hql);

	public int executeHql(String hql, Map<String, Object> param);

	public int executeSql(String sql);

	public int executeSql(String sql, Map<String, Object> param);

	public Session getCurrentSession();

	public int getTotalCount(String hql, Map map);

	public List<Object> findPageByQuery(int offset, int limit, String hql, Map map);

	public Map<String, Object> getPageData(int offset, int limit, String hql, Map map);
}
