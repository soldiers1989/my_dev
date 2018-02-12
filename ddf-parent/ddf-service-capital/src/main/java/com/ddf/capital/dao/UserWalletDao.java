package com.ddf.capital.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.UserWalletQuery;
import com.ddf.entity.capital.vo.UserWalletVo;
import com.ddf.entity.capital.dto.UserWallet;

/**
 * user_wallet DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface UserWalletDao extends CrudDao<UserWallet,UserWalletVo,UserWalletQuery> {
	
	boolean addAmount(@Param("userId")String userId,@Param("amount")BigDecimal amount);
	
	boolean reduceAmount(@Param("userId")String userId,@Param("amount")BigDecimal amount);
	
	boolean freezeAmount(@Param("userId")String userId,@Param("amount")BigDecimal amount);
	
	boolean reduceFreezeAmount(@Param("userId")String userId,@Param("amount")BigDecimal amount);
}