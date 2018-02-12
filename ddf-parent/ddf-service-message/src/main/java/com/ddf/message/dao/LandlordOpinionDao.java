package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.LandlordOpinionQuery;
import com.ddf.entity.message.vo.LandlordOpinionVo;
import com.ddf.entity.message.dto.LandlordOpinion;

/**
 * landlord_opinion DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface LandlordOpinionDao extends CrudDao<LandlordOpinion,LandlordOpinionVo,LandlordOpinionQuery> {
	
}