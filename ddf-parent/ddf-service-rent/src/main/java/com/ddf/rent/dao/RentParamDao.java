package com.ddf.rent.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.rent.query.RentParamQuery;
import com.ddf.entity.rent.vo.RentParamVo;
import com.ddf.entity.rent.dto.RentParam;

/**
 * rent_param DAO接口
 * @author robot
 * @version 2018-01-16
 */
public interface RentParamDao extends CrudDao<RentParam,RentParamVo,RentParamQuery> {
	
}