package com.ddf.capital.service.simple;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.capital.dao.UserWalletDao;
import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.UserWallet;
import com.ddf.entity.capital.eo.UserWalletStatus;
import com.ddf.entity.capital.query.UserWalletQuery;
import com.ddf.entity.capital.vo.UserWalletVo;
import com.ddf.entity.db.eo.TableName;
import com.ddf.entity.exception.BusinessException;
import com.ddf.reference.common.DateReference;
import com.ddf.reference.common.IdReference;
import com.ddf.util.ListUtil;
import com.github.pagehelper.PageHelper;

/**
 * user_wallet Service
 * @author robot
 * @version 2018-01-22
 */
@Service
public class UserWalletService extends CrudServiceImpl<UserWallet,UserWalletVo,UserWalletQuery>{
	@Autowired
	private UserWalletDao userWalletDao;
	
	@Autowired
	private IdReference idReference;
	
	@Autowired
	private DateReference dateReference;
	
	public Page<UserWalletVo> query4page(int pageNum, int pageSize){
		UserWalletQuery userWalletQuery = new UserWalletQuery();
		userWalletQuery.buildPageSql(pageNum, pageSize);
		List<UserWalletVo> list = this.findList(userWalletQuery);
		long totalCount = this.findCount(userWalletQuery);
		Page<UserWalletVo> page = new Page<UserWalletVo>(pageNum, pageSize, totalCount, list);
		return page;
	}
	

	public Page<UserWalletVo> query4pagehelper(int pageNum, int pageSize){
		UserWalletQuery userWalletQuery = new UserWalletQuery();
		PageHelper.startPage(pageNum, pageSize);
        List<UserWalletVo> list = this.findList(userWalletQuery);
        Page<UserWalletVo> page = new Page<UserWalletVo>(list);
        return page;
	}
	
	public void create(String userId,BigDecimal amount,UserWalletStatus userWalletStatus) {
		UserWallet userWallet=new UserWallet();
		Date currentDate=dateReference.queryCurrentDate().getData();
		userWallet.setId(idReference.createId(TableName.user_wallet).getData());
		userWallet.setUserId(userId);
		userWallet.setAmount(amount);
		userWallet.setFreezeAmount(new BigDecimal("0"));
		userWallet.setStatus(UserWalletStatus.SUCCESS);
		userWallet.setCreateDate(currentDate);
		userWallet.setUpdateDate(currentDate);
		dao.create(userWallet);
	}
	
	/**
	 * 根据用户id查询
	 * @param userId
	 * @param amount
	 * @return
	 */
	public UserWalletVo query4userId(String userId){
		UserWalletQuery userWalletQuery=new UserWalletQuery();
		userWalletQuery.getUserWallet().setUserId(userId);
		List<UserWalletVo> userWalletVoList=dao.findList(userWalletQuery);
		if(!ListUtil.isEmpty(userWalletVoList)) {
			return userWalletVoList.get(0);
		}
		return null;
	}
	
	/**
	 * 钱包充值
	 * @param userId
	 * @param amount
	 * @return
	 */
	public void recharge(String userId,BigDecimal amount) {
		UserWalletVo userWalletVo=query4userId(userId);
		if(userWalletVo==null) {
			create(userId, amount, UserWalletStatus.SUCCESS);
		}else {
			userWalletDao.addAmount(userId, amount);
		}
	}
	
	/**
	 * 钱包消费
	 * @param userId
	 * @param amount
	 * @return
	 */
	public void consume(String userId,BigDecimal amount) throws BusinessException{
		UserWalletVo userWalletVo=query4userId(userId);
		if(userWalletVo==null||userWalletVo.getAmount().compareTo(amount)==-1) {
			throw new BusinessException("金额不足");
		}
		userWalletDao.reduceAmount(userId, amount);
	}
	
	/**
	 * 冻结金额
	 * @param userId
	 * @param amount
	 * @return
	 */
	public void freezeAmount(String userId,BigDecimal amount) throws BusinessException{
		boolean result=userWalletDao.freezeAmount(userId, amount);
		if(!result) {
			throw new BusinessException("余额不足");
		}
	}
}