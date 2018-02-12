package com.ddf.member.service.simple;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.constant.Constant;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.member.dto.User;
import com.ddf.entity.member.eo.AlipayStatus;
import com.ddf.entity.member.eo.AlipayZhimaCreditStatus;
import com.ddf.entity.member.eo.RealNameStatus;
import com.ddf.entity.member.eo.UserStatus;
import com.ddf.entity.member.query.UserQuery;
import com.ddf.entity.member.vo.UserVo;
import com.ddf.member.dao.UserDao;
import com.ddf.reference.cache.CacheReference;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.DesUtil;
import com.ddf.util.JeesitePwdUtil;
import com.ddf.util.StringUtil;
import com.github.pagehelper.PageHelper;

/**
 * user Service
 * @author robot
 * @version 2018-01-15
 */
@Service
public class UserService extends CrudServiceImpl<User,UserVo,UserQuery>{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CacheReference cacheReference;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<UserVo> query4page(int pageNum, int pageSize){
		UserQuery userQuery = new UserQuery();
		userQuery.buildPageSql(pageNum, pageSize);
		List<UserVo> list = this.findList(userQuery);
		long totalCount = this.findCount(userQuery);
		Page<UserVo> page = new Page<UserVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	public Page<UserVo> query4page(UserQuery userQuery){
		if(userQuery==null){
			userQuery = new UserQuery();
		}
		if(StringUtil.isEmpty(userQuery.getSortSql())){
			userQuery.buildSortSql("order by a.create_date desc");
		}else{
			userQuery.buildSortSql(userQuery.getSortSql());
		}
		userQuery.buildPageSql(userQuery.getPageNum(), userQuery.getPageSize());
		List<UserVo> list = this.findList(userQuery);
		long totalCount = this.findCount(userQuery);
		Page<UserVo> page = new Page<UserVo>(userQuery.getPageNum(), userQuery.getPageSize(), totalCount, list);
		return page;
	}

	public Page<UserVo> query4pagehelper(int pageNum, int pageSize){
		UserQuery userQuery = new UserQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<UserVo> list = this.findList(userQuery);
        Page<UserVo> page = new Page<UserVo>(list);
        return page;
	}
	
	public Page<UserVo> batchqueryByCreateDateAsc(int pageNum, int pageSize){
		UserQuery userQuery = new UserQuery();
		userQuery.buildSortSql("order by a.create_date");
		userQuery.buildPageSql(pageNum, pageSize);
		List<UserVo> list = this.findList(userQuery);
		long totalCount = this.findCount(userQuery);
		Page<UserVo> page = new Page<UserVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	
	/**
	 * 添加
	 */
	public boolean create(User user){
		user.setId(idReference.createId(TableName.user).getData());
		user.setCreateDate(dateReference.queryCurrentDate().getData());
		return dao.create(user);
	}
	
	/**
	 * 修改
	 */
	public boolean modify(User user){
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return dao.modify(user);
	}
	
	/**
	 * 修改
	 */
	public boolean modifyInfo(User user){
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.modifyInfo(user);
	}
	
	public UserVo queryByMobile(String mobile){
		UserQuery userQuery = new UserQuery();
		userQuery.getUser().setMobile(mobile);
		return userDao.query(userQuery);
	}
	
	
	public UserVo query(UserQuery userQuery){
		return userDao.query(userQuery);
	}
	
	public boolean checkUserName(String userName){
		if(StringUtil.isNotEmpty(userName)){
			UserQuery userQuery = new UserQuery();
			userQuery.getUser().setUserName(userName);
			userQuery.getUser().setMobile(userName);
			int i = userDao.checkUserName(userQuery);
			if(i>0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断账号是否冻结
	 * @param userName
	 * @return
	 */
	public boolean checkUserFrozen(String userName){
		if(StringUtil.isNotEmpty(userName)){
			UserQuery userQuery = new UserQuery();
			userQuery.getUser().setUserName(userName);
			UserVo vo = this.query(userQuery);
			if(vo!=null && vo.getStatus()==UserStatus.normal){
				return false;
			}
		}
		return true;
	}
	
	
	public UserVo queryByUserName(String userName){
		if(StringUtil.isNotEmpty(userName)){
			UserQuery userQuery = new UserQuery();
			userQuery.getUser().setUserName(userName);
			return this.query(userQuery);
		}
		return null;
	}
	
	/**
	 * 注册
	 * @param userName
	 * @param password 用户密码-md5
	 * @return
	 */
	public UserVo register(String userName,String password,String deviceNo){
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password) ) {
			return null;
		}
		User user = new User();
		user.setId(idReference.createId(TableName.user).getData());
		user.setMobile(userName);
		user.setUserName(userName);
		user.setNickName(userName);
		user.setPassword(password);
		user.setStatus(UserStatus.normal);
		user.setCreateDate(dateReference.queryCurrentDate().getData());
		boolean bool = dao.create(user);
		if(bool==true){
			return queryByUserName(userName);
		}
		return null;
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param password  用户密码-md5
	 * @return
	 */
	public UserVo login(String userName,String password){
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password) ) {
			return null;
		}
		UserVo vo = queryByUserName(userName);
		if(vo==null) {
			return null;
		}
		// 验证密码是否一致
//		boolean validatePassword = JeesitePwdUtil.validatePassword( password , vo.getPassword() );
//		if (!validatePassword) {
//			return null;
//		}
		if(!password.equals(vo.getPassword())){
			return null;
		}
		return vo;
	}
	
	/**
	 * 退出登录
	 * @param userId
	 * @return
	 */
	public boolean loginOut(String user_token){
		if(StringUtil.isEmpty(user_token)) {
			return false;
		}
		String openUserToken = DesUtil.decrypt(user_token);
		cacheReference.remove(openUserToken);
		return true;
	}
	
	/**
	 * 忘记登录密码
	 * @param userName
	 * @param password 用户密码-md5
	 * @return
	 */
	public boolean forgetPassword(String userName,String password){
		if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(password) ) {
			return false;
		}
		UserVo vo = queryByUserName(userName);
		if(vo==null) {
			return false;
		}
		User user = vo;
		user.setPassword(password);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updatePassword(user);
	}
	
	public boolean checkSelfByUserName(String id,String userName){
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(userName)) {
			return false;
		}
		User user = dao.query4id(id);
		if(user==null) {
			return false;
		}
		if(userName.equals(user.getUserName()) || userName.equals(user.getMobile())){
			return true;
		}
		return false;
	}
	
	/**
	 * 修改手机号
	 * @param id
	 * @param mobile
	 * @return
	 */
	public boolean modifyMobile(String id,String mobile){
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(mobile)) {
			return false;
		}
		User user = dao.query4id(id);
		if(user==null) {
			return false;
		}
		user.setMobile(mobile);
		user.setUserName(mobile);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.modifyMobile(user);
	}
	
	/**
	 * 修改登录密码
	 * @param userName
	 * @param oldPassword 密码-md5
	 * @param newPassword 密码-md5
	 * @return
	 */
	public boolean resetPassword(String id,String oldPassword,String newPassword){
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(oldPassword) || StringUtil.isEmpty(newPassword) ) {
			return false;
		}
		User user = dao.query4id(id);
		if(user==null) {
			return false;
		}
		// 验证密码是否一致
//		boolean validatePassword = JeesitePwdUtil.validatePassword( oldPassword , user.getPassword() );
//		if (!validatePassword) {
//			return false;
//		}
		if(!oldPassword.equals(user.getPassword())){
			return false;
		}
		user.setPassword(newPassword);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updatePassword(user);
	}
	
	/**
	 * 修改登录密码
	 * @param userName
	 * @param newPassword 密码-md5
	 * @return
	 */
	public boolean resetPassword(String id,String newPassword){
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(newPassword) ) {
			return false;
		}
		User user = dao.query4id(id);
		if(user==null) {
			return false;
		}
		user.setPassword(newPassword);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updatePassword(user);
	}
	
	/**
	 * 修改支付密码
	 * @param id
	 * @param oldPayPassword 密码-md5
	 * @param newPayPassword 密码-md5
	 * @return
	 */
	public boolean resetPayPassword(String id,String oldPayPassword,String newPayPassword){
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(oldPayPassword) || StringUtil.isEmpty(newPayPassword) ) {
			return false;
		}
		User user = dao.query4id(id);
		if(user==null) {
			return false;
		}
		// 验证密码是否一致
//		boolean validatePassword = JeesitePwdUtil.validatePassword( oldPayPassword , user.getPayPassword() );
//		if (!validatePassword) {
//			return false;
//		}
		if(!oldPayPassword.equals(user.getPayPassword())){
			return false;
		}
		user.setPayPassword(newPayPassword);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updatePayPassword(user);
	}
	
	/**
	 * 设置支付密码
	 * @param id
	 * @param newPayPassword
	 * @return
	 */
	public boolean setPayPassword(String id,String newPayPassword){
		if(StringUtil.isEmpty(id) || StringUtil.isEmpty(newPayPassword) ) {
			return false;
		}
		User user = dao.query4id(id);
		if(user==null) {
			return false;
		}
		user.setPayPassword(newPayPassword);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updatePayPassword(user);
	}
	
	/**
	 * 设置实名状态
	 * @param userId
	 * @param realNameFlag
	 * @return
	 */
	public boolean setRealNameFlag(String userId,RealNameStatus realNameFlag){
		if(StringUtil.isEmpty(userId)) {
			return false;
		}
		User user = dao.query4id(userId);
		if(user==null) {
			return false;
		}
		user.setRealNameFlag(realNameFlag);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updateRealNameFlag(user);
	}
	
	/**
	 * 根据user_token获取用户
	 * @param user_token
	 * @return
	 */
	public User queryUser4userToken(String user_token){
		String openUserToken = DesUtil.decrypt(user_token);
		String userId = cacheReference.get(openUserToken).getData();
		return this.query4id(userId);
	}
	/**
	 * 设置芝麻信用状态
	 * @param userId
	 * @param zhimaCreditFlag
	 * @return
	 */
	public boolean setZhimaCreditFlag(String userId,AlipayZhimaCreditStatus zhimaCreditFlag){
		if(StringUtil.isEmpty(userId)) {
			return false;
		}
		User user = dao.query4id(userId);
		if(user==null) {
			return false;
		}
		user.setZhimaCreditFlag(zhimaCreditFlag);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updateZhimaCreditFlag(user);
	}
	
	
	/**
	 * 设置支付宝认证状态
	 * @param userId
	 * @param alipayStatus
	 * @return
	 */
	public boolean setAlipayFlay(String userId,AlipayStatus alipayStatus){
		if(StringUtil.isEmpty(userId)) {
			return false;
		}
		User user = dao.query4id(userId);
		if(user==null) {
			return false;
		}
		user.setAlipayFlay(alipayStatus);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updateAlipayFlay(user);
	}
	
	
	
	/**
	 * 设置保证金状态
	 * @param userId
	 * @param realNameFlag
	 * @return
	 */
	public boolean setBondFlag(String userId,boolean bondFlag){
		if(StringUtil.isEmpty(userId)) {
			return false;
		}
		User user = dao.query4id(userId);
		if(user==null) {
			return false;
		}
		user.setBondFlag(bondFlag);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updateBondFlag(user);
	}
	
	
	/**
	 * 设置接听状态
	 * @param userId
	 * @param answerFlag
	 * @return
	 */
	public boolean setAnswerFlag(String userId,boolean answerFlag){
		if(StringUtil.isEmpty(userId)) {
			return false;
		}
		User user = dao.query4id(userId);
		if(user==null) {
			return false;
		}
		user.setAnswerFlag(answerFlag);
		user.setUpdateDate(dateReference.queryCurrentDate().getData());
		return userDao.updateAnswerFlag(user);
	}
	
	
	
	/**
	 * 缓存用户信息
	 * @param vo
	 * @return
	 */
	public String cacheLoginUser(String userId,String deviceNo){
		//删除老设备号的用户
		clearLoginUser(userId);
		//生成openUserToken    login_user_123456_device_no_aaaaaaa,123456
		String openUserToken = Constant.login_prefix + userId+Constant._device_no_+deviceNo;
		//保存userId
		cacheReference.put(openUserToken, userId);
		//返回加密user_token
		return DesUtil.encrypt(openUserToken);
	}
	
	/**
	 * 清除用户缓存信息
	 * @param userId
	 */
	private void clearLoginUser(String userId){
		cacheReference.remove4begin(Constant.login_prefix+userId);
	}
	
}