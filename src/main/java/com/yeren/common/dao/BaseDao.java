package com.yeren.common.dao;

import java.io.Serializable;
import java.lang.reflect.Type;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

public class BaseDao<T> {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	private Class entityClass;

	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	public T find(Serializable id) {
		return (T) hibernateTemplate.get(entityClass, id);
	}

}
