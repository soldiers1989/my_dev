package com.ddf.component.mybatis.api;

import java.util.List;

import com.ddf.entity.base.dto.Page;

public interface CrudService<T,VO,QO> {

	

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T query4id(String id);
	
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public boolean create(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public boolean modify(T entity);
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	public boolean remove(String id);
	
	
	public List<VO> findList(QO qo);
	
	
	public long findCount(QO qo);
		
}
