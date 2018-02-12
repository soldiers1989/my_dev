package com.ddf.member.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.member.query.RealNameQuery;
import com.ddf.entity.member.vo.RealNameVo;
import com.ddf.entity.member.dto.RealName;

/**
 * real_name DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface RealNameDao extends CrudDao<RealName,RealNameVo,RealNameQuery> {
	
	public RealNameVo query(RealNameQuery realNameQuery);
	
	public boolean updateStatus(RealName realName);
}