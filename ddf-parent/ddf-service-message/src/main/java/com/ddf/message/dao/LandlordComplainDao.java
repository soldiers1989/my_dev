package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.LandlordComplainQuery;
import com.ddf.entity.message.vo.LandlordComplainVo;
import com.ddf.entity.message.dto.LandlordComplain;

/**
 * landlord_complain DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface LandlordComplainDao extends CrudDao<LandlordComplain,LandlordComplainVo,LandlordComplainQuery> {
	
}