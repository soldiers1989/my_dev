package com.ddf.component.jdbc.base;

import org.springframework.beans.factory.annotation.Autowired;

public class CrudService<T,TV> {
	
	@Autowired
	private CrudDao<T,TV> baseDao;
	
	
	
	public T query4id(String id){
		return baseDao.query4id(id);
	}
	
	public boolean create(T entity){
		return baseDao.create(entity);
	}
	
	
	public boolean modify(T entity){
		return baseDao.modify(entity);
	}
	
	
	public boolean remove(String id){
		return baseDao.remove(id);
	}
	
	

}
