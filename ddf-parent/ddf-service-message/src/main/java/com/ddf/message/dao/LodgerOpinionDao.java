package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.LodgerOpinionQuery;
import com.ddf.entity.message.vo.LodgerOpinionVo;
import com.ddf.entity.message.dto.LodgerOpinion;

/**
 * lodger_opinion DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface LodgerOpinionDao extends CrudDao<LodgerOpinion,LodgerOpinionVo,LodgerOpinionQuery> {
	
}