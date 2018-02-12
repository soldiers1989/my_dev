package com.ddf.sms.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.sms.query.ShortMessageQuery;
import com.ddf.entity.sms.vo.ShortMessageVo;
import com.ddf.entity.sms.dto.ShortMessage;

/**
 * short_message DAO接口
 * @author robot
 * @version 2018-01-11
 */
public interface ShortMessageDao extends CrudDao<ShortMessage,ShortMessageVo,ShortMessageQuery> {
	
}