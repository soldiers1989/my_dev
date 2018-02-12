package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.DepositOrder;

/**
 * deposit_order EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class DepositOrderQuery extends Query {

	private static final long serialVersionUID = 1L;

	private int pageSize;
	
	private int pageNum;
	
	private String orderBySql;
	
	public DepositOrderQuery(){
		this.depositOrder = new DepositOrder();
	}
	
	private DepositOrder depositOrder;

	public DepositOrder getDepositOrder() {
		return depositOrder;
	}

	public void setDepositOrder(DepositOrder depositOrder) {
		this.depositOrder = depositOrder;
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