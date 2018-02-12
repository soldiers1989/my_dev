package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.RechargeOrder;

/**
 * recharge_order EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class RechargeOrderQuery extends Query {

	private static final long serialVersionUID = 1L;

	public RechargeOrderQuery(){
		this.rechargeOrder = new RechargeOrder();
	}
	
	private RechargeOrder rechargeOrder;

	public RechargeOrder getRechargeOrder() {
		return rechargeOrder;
	}

	public void setRechargeOrder(RechargeOrder rechargeOrder) {
		this.rechargeOrder = rechargeOrder;
	}

}