package com.ddf.rent.dao;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.RentDemandQuery;
import com.ddf.entity.rent.vo.RentDemandVo;
import com.ddf.entity.rent.dto.RentDemand;

/**
 * rent_demand DAO接口
 * @author robot
 * @version 2018-01-17
 */
public interface RentDemandDao extends CrudDao<RentDemand,RentDemandVo,RentDemandQuery> {

	int remove4id(@Param(value="id")String id);
	
}