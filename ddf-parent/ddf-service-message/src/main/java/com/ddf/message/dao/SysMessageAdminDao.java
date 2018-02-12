package com.ddf.message.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.message.query.SysMessageAdminQuery;
import com.ddf.entity.message.vo.SysMessageAdminVo;
import com.ddf.entity.message.dto.SysMessageAdmin;

/**
 * sys_message_admin DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface SysMessageAdminDao extends CrudDao<SysMessageAdmin,SysMessageAdminVo,SysMessageAdminQuery> {
	
}