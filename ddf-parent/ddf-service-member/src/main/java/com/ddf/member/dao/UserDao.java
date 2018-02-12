package com.ddf.member.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.query.UserQuery;
import com.ddf.entity.member.vo.UserVo;

/**
 * user DAO接口
 * @author robot
 * @version 2018-01-05
 */
public interface UserDao extends CrudDao<User,UserVo,UserQuery> {
	
	public UserVo query(UserQuery userQuery);
	
	public boolean updatePayPassword(User user);
	
	public boolean updatePassword(User user);
	
	public boolean updateRealNameFlag(User user);
	
	public boolean updateZhimaCreditFlag(User user);
	
	public boolean updateAlipayFlay(User user);
	
	public boolean updateBondFlag(User user);
	
	public boolean updateAnswerFlag(User user);
	
	public boolean modifyInfo(User user);
	
	public boolean modifyMobile(User user);
	
	public int checkUserName(UserQuery userQuery);
	
}