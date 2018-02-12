package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.WithdrawApplyBatchQuery;
import com.ddf.entity.capital.vo.WithdrawApplyBatchVo;
import com.ddf.entity.capital.dto.WithdrawApplyBatch;

/**
 * withdraw_apply_batch DAO接口
 * @author robot
 * @version 2018-02-02
 */
public interface WithdrawApplyBatchDao extends CrudDao<WithdrawApplyBatch,WithdrawApplyBatchVo,WithdrawApplyBatchQuery> {
	
}