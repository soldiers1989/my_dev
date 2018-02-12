package com.ddf.capital.service.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddf.component.mybatis.api.impl.CrudServiceImpl;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.capital.dto.CapTradeOrder;
import com.ddf.entity.capital.query.CapTradeOrderQuery;
import com.ddf.entity.capital.vo.CapTradeOrderVo;
import com.ddf.capital.dao.CapTradeOrderDao;

/**
 * cap_trade_order Service
 * @author robot
 * @version 2018-01-10
 */
@Service
public class CapTradeOrderService extends CrudServiceImpl<CapTradeOrder,CapTradeOrderVo,CapTradeOrderQuery>{
	@Autowired
	private CapTradeOrderDao capTradeOrderDao;
}