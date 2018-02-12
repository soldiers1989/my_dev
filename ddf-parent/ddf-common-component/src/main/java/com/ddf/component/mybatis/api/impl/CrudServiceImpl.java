package com.ddf.component.mybatis.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.component.mybatis.api.CrudService;

public class CrudServiceImpl<T,VO,QO> implements CrudService<T,VO,QO> {

	@Autowired
	protected CrudDao<T,VO,QO> dao;

	@Override
	public T query4id(String id) {
		return dao.query4id(id);
	}

	@Override
	public boolean create(T entity) {
		return dao.create(entity);
	}

	@Override
	public boolean modify(T entity) {
		return dao.modify(entity);
	}

	@Override
	public boolean remove(String id) {
		return dao.remove(id);
	}

	@Override
	public List<VO> findList(QO query) {
		return dao.findList(query);
	}

	@Override
	public long findCount(QO query) {
		return dao.findCount(query);
	}

}
