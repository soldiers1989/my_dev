package com.ddf.student.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.student.query.UserQuery;
import com.ddf.entity.student.vo.UserVo;
import com.ddf.entity.student.dto.User;

/**
 * user DAO接口
 * @author robot
 * @version 2018-01-02
 */
public interface UserDao extends CrudDao<User,UserVo,UserQuery> {
	
}