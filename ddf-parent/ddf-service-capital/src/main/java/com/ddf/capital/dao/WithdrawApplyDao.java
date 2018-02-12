package com.ddf.capital.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.dto.WithdrawApply;
import com.ddf.entity.capital.query.WithdrawApplyQuery;
import com.ddf.entity.capital.vo.WithdrawApplyVo;

/**
 * withdraw_apply DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface WithdrawApplyDao extends CrudDao<WithdrawApply,WithdrawApplyVo,WithdrawApplyQuery> {
	
	BigDecimal sumAmountInapplyIds(@Param("applyIds") String[] applyIds);
	
	void updateBatchIdInapplyIds(@Param("batchId") String batchId, @Param("applyIds") String[] applyIds);
	
	void updateBatchId2Null4batchId(@Param("batchId") String batchId);
	
	List<WithdrawApplyVo> queryList4batchIdIsNull(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	List<WithdrawApplyVo> querysum4user(@Param("batchId")String batchId,@Param("userId")String userId);
	
	List<WithdrawApplyVo> queryList4batchIdInalipayAccount(@Param("batchId") String batchId, @Param("alipayAccountList") List<String> alipayAccountList);
	
	
}