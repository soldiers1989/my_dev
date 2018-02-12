package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.HouseOpinionQuery;
import com.ddf.entity.message.vo.HouseOpinionVo;
import com.ddf.entity.message.dto.HouseOpinion;

/**
 * house_opinion DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface HouseOpinionDao extends CrudDao<HouseOpinion,HouseOpinionVo,HouseOpinionQuery> {
	
}