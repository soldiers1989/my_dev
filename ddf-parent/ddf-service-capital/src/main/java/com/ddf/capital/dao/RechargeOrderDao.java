package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.RechargeOrderQuery;
import com.ddf.entity.capital.vo.RechargeOrderVo;
import com.ddf.entity.capital.dto.RechargeOrder;

/**
 * recharge_order DAO接口
 * @author robot
 * @version 2018-01-22
 */
public interface RechargeOrderDao extends CrudDao<RechargeOrder,RechargeOrderVo,RechargeOrderQuery> {
	
}