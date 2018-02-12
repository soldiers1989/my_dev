package com.ddf.message.dao;

import java.util.List;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.dto.SysMessage;
import com.ddf.entity.message.dto.SysMessageAdmin;
import com.ddf.entity.message.query.SysMessageQuery;
import com.ddf.entity.message.vo.SysMessageVo;

/**
 * sys_message DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface SysMessageDao extends CrudDao<SysMessage,SysMessageVo,SysMessageQuery> {
	
	public int createBatch(List<SysMessage> list);
	
	public boolean updateRead(SysMessage sysMessage);
}