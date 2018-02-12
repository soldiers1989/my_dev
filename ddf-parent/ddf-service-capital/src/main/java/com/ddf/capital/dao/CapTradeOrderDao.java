package com.ddf.capital.dao;

import com.ddf.component.mybatis.api.CrudDao;
import com.ddf.entity.capital.query.CapTradeOrderQuery;
import com.ddf.entity.capital.vo.CapTradeOrderVo;
import com.ddf.entity.capital.dto.CapTradeOrder;

/**
 * cap_trade_order DAO接口
 * @author robot
 * @version 2018-01-10
 */
public interface CapTradeOrderDao extends CrudDao<CapTradeOrder,CapTradeOrderVo,CapTradeOrderQuery> {
	
}