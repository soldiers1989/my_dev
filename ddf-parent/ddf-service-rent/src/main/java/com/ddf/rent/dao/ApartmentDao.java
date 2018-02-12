package com.ddf.rent.dao;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.ApartmentQuery;
import com.ddf.entity.rent.vo.ApartmentVo;
import com.ddf.entity.rent.dto.Apartment;

/**
 * apartment DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface ApartmentDao extends CrudDao<Apartment,ApartmentVo,ApartmentQuery> {

	int remove4id(@Param(value="id")String id);
	
}