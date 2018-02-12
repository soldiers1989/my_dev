package com.ddf.entity.capital.query;

import com.ddf.entity.base.query.Query;
import com.ddf.entity.capital.dto.BondOrderRefundApply;

/**
 * bond_order_refund_apply EntityQuery
 * @author robot
 * @version 2018-01-22
 */
public class BondOrderRefundApplyQuery extends Query {

	private static final long serialVersionUID = 1L;

	private int pageSize;
	
	private int pageNum;
	
	private String orderBySql;
	
	public BondOrderRefundApplyQuery(){
		this.bondOrderRefundApply = new BondOrderRefundApply();
	}
	
	private BondOrderRefundApply bondOrderRefundApply;

	public BondOrderRefundApply getBondOrderRefundApply() {
		return bondOrderRefundApply;
	}

	public void setBondOrderRefundApply(BondOrderRefundApply bondOrderRefundApply) {
		this.bondOrderRefundApply = bondOrderRefundApply;
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