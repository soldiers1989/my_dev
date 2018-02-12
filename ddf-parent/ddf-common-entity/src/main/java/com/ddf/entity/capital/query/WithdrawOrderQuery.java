package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.WithdrawOrder;

/**
 * withdraw_order EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class WithdrawOrderQuery extends Query {

	private static final long serialVersionUID = 1L;

	private int pageSize;
	
	private int pageNum;
	
	private String orderBySql;
	
	public WithdrawOrderQuery(){
		this.withdrawOrder = new WithdrawOrder();
	}
	
	private WithdrawOrder withdrawOrder;

	public WithdrawOrder getWithdrawOrder() {
		return withdrawOrder;
	}

	public void setWithdrawOrder(WithdrawOrder withdrawOrder) {
		this.withdrawOrder = withdrawOrder;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getOrderBySql() {
		return orderBySql;
	}

	public void setOrderBySql(String orderBySql) {
		this.orderBySql = orderBySql;
	}

}