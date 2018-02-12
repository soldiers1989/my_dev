package com.ddf.student.service.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.User;
import com.ddf.entity.student.query.UserQuery;
import com.ddf.entity.student.vo.UserVo;
import com.ddf.student.dao.UserDao;

/**
 * user Service
 * @author robot
 * @version 2018-01-02
 */
@Service
public class UserService extends CrudServiceImpl<User,UserVo,UserQuery>{
	@Autowired
	private UserDao userDao;
}