package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.MobileMessageQuery;
import com.ddf.entity.message.vo.MobileMessageVo;
import com.ddf.entity.message.dto.MobileMessage;

/**
 * mobile_message DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface MobileMessageDao extends CrudDao<MobileMessage,MobileMessageVo,MobileMessageQuery> {
	
}