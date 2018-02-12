package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.ChatRecordQuery;
import com.ddf.entity.message.vo.ChatRecordVo;
import com.ddf.entity.message.dto.ChatRecord;

/**
 * chat_record DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface ChatRecordDao extends CrudDao<ChatRecord,ChatRecordVo,ChatRecordQuery> {
	
}