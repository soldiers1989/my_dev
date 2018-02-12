package com.ddf.message.dao;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.MessageTaskQuery;
import com.ddf.entity.message.vo.MessageTaskVo;
import com.ddf.entity.message.dto.MessageTask;
import com.ddf.entity.message.eo.MessageTaskBizType;

/**
 * message_task DAO接口
 * @author robot
 * @version 2018-01-12
 */
public interface MessageTaskDao extends CrudDao<MessageTask,MessageTaskVo,MessageTaskQuery> {
	public MessageTask findByBizTypeLimit1(@Param(value="bizType")MessageTaskBizType bizType);
}