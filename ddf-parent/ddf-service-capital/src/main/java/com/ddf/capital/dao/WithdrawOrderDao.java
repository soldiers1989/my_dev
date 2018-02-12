package com.ddf.capital.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.dto.WithdrawOrder;
import com.ddf.entity.capital.eo.WithdrawOrderStatus;
import com.ddf.entity.capital.query.WithdrawOrderQuery;
import com.ddf.entity.capital.vo.WithdrawOrderVo;

/**
 * withdraw_order DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface WithdrawOrderDao extends CrudDao<WithdrawOrder,WithdrawOrderVo,WithdrawOrderQuery> {
	
	List<WithdrawOrderVo> queryList4batchId4status(@Param("batchId")String batchId,@Param("status")WithdrawOrderStatus status);
	
	void updateStatus4applyId4PROCESSING(@Param("status")WithdrawOrderStatus status,@Param("updateDate")Date updateDate,@Param("applyId")String applyId);
}